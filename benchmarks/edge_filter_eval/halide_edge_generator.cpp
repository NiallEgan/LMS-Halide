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
    GeneratorParam<int> tile_x{"tile_x", 32}; // X tile.
    GeneratorParam<int> tile_y{"tile_y", 8};  // Y tile.

    Input<Buffer<uint8_t>>  input{"input", 3};
    Output<Buffer<uint8_t>> edge_detection{"edge_detection", 3};
    float sigma = 1.5f;
    void generate() {
        Func blur_y("blur_y");
        Func blur_x("blur_x");

        Var x("x"), y("y"), xi("xi"), yi("yi");
        Var c("c");

        Func kernel;
        kernel(x) = cast<double>(exp(-x*x/(2*sigma*sigma)) / (sqrtf(2*M_PI)*sigma));
        assert(kernel.output_types()[0] == Float(64));
        Func in_bounded = Halide::BoundaryConditions::repeat_edge(input);
        // The algorithm
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
        /*blur_y(x, y, c) = (cast<uint16_t>(in_bounded(x, y-1, c)) + cast<uint16_t>(in_bounded(x, y, c)) + in_bounded(x, y+1, c)) / 3;
        blur_x(x, y, c) = (blur_y(x-1, y, c) + blur_y(x, y, c) + blur_y(x+1, y, c)) / 3;*/

      //  assert(blur_x.output_types()[0] == Float(64));
        //assert(blur_y.output_types()[0] == Float(64));


        edge_detection(x, y, c) = cast<uint8_t>(blur_x(x-1, y, c) + blur_x(x+1, y, c) +
          blur_x(x, y-1, c) + blur_x(x, y+1, c) - 4 * blur_x(x, y, c));


        // How to schedule it

        // CPU schedule.
        //blur_y.split(y, y, yi, 8).parallel(y).vectorize(x, 8);
        //blur_x.store_at(blur_y, y).compute_at(blur_y, yi).vectorize(x, 8);

    }
};



HALIDE_REGISTER_GENERATOR(HalideBlur, halide_edge)
