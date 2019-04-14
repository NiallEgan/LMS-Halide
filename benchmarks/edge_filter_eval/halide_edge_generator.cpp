#include "Halide.h"

#include "halide_image_io.h"
using namespace Halide::Tools;

enum class BlurGPUSchedule {
    Inline,         // Fully inlining schedule.
    Cache,          // Schedule caching intermedia result of blur_x.
    Slide,          // Schedule enabling sliding window opt within each
                    // work-item or cuda thread.
    SlideVectorize, // The same as above plus vectorization per work-item.
};

std::map<std::string, BlurGPUSchedule> blurGPUScheduleEnumMap() {
    return {
        {"inline",        BlurGPUSchedule::Inline},
        {"cache",         BlurGPUSchedule::Cache},
        {"slide",         BlurGPUSchedule::Slide},
        {"slide_vector",  BlurGPUSchedule::SlideVectorize},
    };
};


class HalideBlur : public Halide::Generator<HalideBlur> {
public:
    GeneratorParam<BlurGPUSchedule> schedule{
        "schedule",
        BlurGPUSchedule::SlideVectorize,
        blurGPUScheduleEnumMap()
    };
    Func edge_thin_stages[11];
    Func blur_y;
    Func blur_x;
    Func gxx, gxy,
         gyx, gyy;
    Func g;

    Var x{"x"}, y{"y"}, xi{"xi"}, yi{"yi"};
    Var c{"c"};

    GeneratorParam<int> tile_x{"tile_x", 32}; // X tile.
    GeneratorParam<int> tile_y{"tile_y", 8};  // Y tile.

    Input<Buffer<uint8_t>>  input{"input", 3};
    Output<Buffer<uint8_t>> final{"final", 3};
    float sigma = 1.5f;

    void generate() {
        Func kernel("kernel");
        kernel(x) = cast<double>(exp(-x*x/(2*sigma*sigma)) / (sqrtf(2*M_PI)*sigma));
        assert(kernel.output_types()[0] == Float(64));
        Func in_bounded("bounded") ;
        in_bounded = Halide::BoundaryConditions::repeat_edge(input);
        // The algorithm
        // Gaussian blur
        blur_y(x, y, c) = (kernel(0) * in_bounded(x, y, c) +
                           kernel(1) * (cast<uint16_t>(in_bounded(x, y-1, c)) +
                                    in_bounded(x, y+1, c)) +
                           kernel(2) * (cast<uint16_t>(in_bounded(x, y-2, c)) +
                                    in_bounded(x, y+2, c)) +
                           kernel(3) * (cast<uint16_t>(in_bounded(x, y-3, c)) +
                                    in_bounded(x, y+3, c)));
        blur_x(x, y, c) = (kernel(0) * blur_y(x, y, c) +
                   kernel(1) * (cast<uint16_t>(blur_y(x-1, y, c)) +
                                blur_y(x+1, y, c)) +
                   kernel(2) * (cast<uint16_t>(blur_y(x-2, y, c)) +
                                blur_y(x+2, y, c)) +
                   kernel(3) * (cast<uint16_t>(blur_y(x-3, y, c)) +
                                blur_y(x+3, y, c)));


        // Sobel
        gxx(x, y, c) = -1 * blur_x(x-1, y, c) + blur_x(x+1, y, c);
        gxy(x, y, c) = 1 * gxx(x, y-1, c) + 2 * gxx(x, y, c) + 1 * gxx(x, y+1, c);

        gyx(x, y, c) = 1 * blur_x(x-1, y, x) + 2 * blur_x(x, y, c) + 1 * blur_x(x+1, y, c);
        gyy(x, y, c) = -1 * gyx(x, y-1, c) + 1 * gyx(x, y+1, c);
        g(x, y, c) = Halide::sqrt(gxy(x, y, c) * gxy(x, y, c) + gyy(x, y, c) * gyy(x, y, c));

        // Thresholding
        Func thresh("thresh");
        thresh(x, y, c) = Halide::select(g(x, y, c) < Expr(70.0), 0 , 1);

        // Edge thinning
        Expr p[8] = {
          thresh(x, y-1, c), thresh(x+1, y-1, c), thresh(x+1, y, c),
          thresh(x+1, y+1, c), thresh(x, y+1, c), thresh(x-1, y+1, c),
          thresh(x-1, y, c), thresh(x-1, y-1, c)
        };
        Expr n = p[0] + p[1] + p[2] + p[3] + p[4] + p[5] + p[6] + p[7];
        Expr sBuffer[7];
        for (int i = 0; i < 7; i++) {
          sBuffer[i] = Halide::select(p[i] != p[i+1], 1, 0);
        }
        Expr s = sBuffer[0] + sBuffer[1] + sBuffer[2] +
                 sBuffer[3] + sBuffer[4] + sBuffer[5] +
                 sBuffer[6];

        Expr should_remove = (thresh(x, y, c) == 1) && (2 <= n && n <= 6)
          && s == 1 && (p[0] * p[2] * p[4] == 0) && (p[2] * p[4] * p[6] == 0)
          && p[5] != 0;


        Func first_thin("first_thin");
        first_thin(x, y, c) = Halide::select(should_remove || thresh(x, y, c) == 0, 0, 1);

        int NTHINS = 8;
        edge_thin_stages[0] = first_thin;
        for (int i = 0; i < NTHINS; i++) {
          Func prev = edge_thin_stages[i];
          Expr p2[8] = {
            prev(x, y-1, c), prev(x+1, y-1, c), prev(x+1, y, c),
            prev(x+1, y+1, c), prev(x, y+1, c), prev(x-1, y+1, c),
            prev(x-1, y, c), prev(x-1, y-1, c)
          };
          Expr n2 = p2[0] + p2[1] + p2[2] + p2[3] + p2[4] + p2[5] + p2[6] + p2[7];
          Expr sBuffer2[7];
          for (int i = 0; i < 7; i++) {
            sBuffer2[i] = Halide::select((p[i] != p[i+1]), 1, 0);
          }
          Expr s2 = sBuffer2[0] + sBuffer2[1] + sBuffer2[2] +
                    sBuffer2[3] + sBuffer2[4] + sBuffer2[5] +
                    sBuffer2[6];

          Expr should_remove2 = (prev(x, y, c) == 1) && (2 <= n2 && n2 <= 6)
            && s2 == 1 && (p2[0] * p2[2] * p2[4] == 0) && (p2[2] * p2[4] * p2[6] == 0)
            && p2[5] != 0;
          Func f("f_" + std::to_string(i));
          f(x, y, c) = Halide::select(should_remove2 || prev(x, y, c) == 0, 0, 1);
          edge_thin_stages[i+1] = f;
        }

        //Func final("final");
        final(x, y, c) = cast<uint8_t>(edge_thin_stages[NTHINS](x, y, c) * 255);

        // How to schedule it

        // CPU schedule.

        if (auto_schedule) {
          input.dim(0).set_bounds_estimate(0, 1500);
          input.dim(1).set_bounds_estimate(0, 2500);
          input.dim(2).set_bounds_estimate(0, 3);
        } else {
          //blur_x.compute_at(gyx, y);
          //blur_y.compute_at(blur_x, y).store_root();
          for (int i = 0; i < NTHINS; i++) {
            edge_thin_stages[i].compute_root();
            Var x_outer, x_inner, y_outer, y_inner;
            edge_thin_stages[i].tile(x, y, x_outer, y_outer, x_inner, y_inner, 8, 8);
            edge_thin_stages[i].parallel(y_outer);
            edge_thin_stages[i].vectorize(x_inner, 8);

          }
          // par => computeRoot
          gyx.compute_root();
          gyx.parallel(y).vectorize(x, 8);
          gyy.compute_root();
          gyy.parallel(y).vectorize(x, 8);

          gxx.parallel(y).vectorize(x, 8);
          gxx.compute_root();
          gxy.parallel(y).vectorize(x, 8);
          gxy.compute_root();


          Var yo;
          final.reorder(c, x, y).split(y, yo, y, 64).parallel(yo).vectorize(x, 8);
        }
        //blur_y.split(y, y, yi, 8).parallel(y).vectorize(x, 8);
        //blur_x.store_at(blur_y, y).compute_at(blur_y, yi).vectorize(x, 8);
    }
};



HALIDE_REGISTER_GENERATOR(HalideBlur, halide_edge)
