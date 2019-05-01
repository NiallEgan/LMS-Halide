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

class HalideLaplace: public Halide::Generator<HalideLaplace> {
public:

  Input<Buffer<uint8_t>>  input{"input", 3};
  Output<Buffer<uint8_t>> laplace{"laplace", 3};
  void generate() {

    Func clamped = Halide::BoundaryConditions::repeat_edge(input);

    Func inGPyramid[3];

    inGPyramid[0](x, y, c) = cast<float>(clamped(x, y, c));
    for (int j = 1; j < 3; j++) {
      inGPyramid[j](x, y, c) = downsample(inGPyramid[j-1])(x, y, c);
    }

    Func lPyramid[3];
    lPyramid[2](x, y, c) = inGPyramid[2](x, y, c);
    for (int j = 3 - 2; j >= 0; j--) {
      lPyramid[j](x, y, c) = inGPyramid[j](x, y, c) - upsample(inGPyramid[j+1])(x, y, c);
    }

    laplace(x, y, c) = cast<uint8_t>(lPyramid[0](x, y, c));
  }

private:
  Var x, y, c, k;

  Func downsample(Func f) {
    using Halide::_;

    Func downx, downy;
    downx(x, y, c) = (f(2*x-1, y, c) + 3.0f * (f(2*x, y, c) + f(2*x+1, y, c)) + f(2*x+2, y, c)) / 8.0f;
    downy(x, y, c) = (downx(x, 2*y-1, c) + 3.0f * (downx(x, 2*y, c) + downx(x, 2*y+1, c)) + downx(x, 2*y+2, c)) / 8.0f;
    return downy;
  }

  Func upsample(Func f) {
      using Halide::_;
      Func upx, upy;
      upx(x, y, c) = 0.25f * f((x/2) - 1, y, c) + 0.75f * f(x/2, y, c);
      upy(x, y, c) = 0.25f * upx(x, (y/2) - 1, c) + 0.75f * upx(x, y/2, c);
      return upy;
  }


};
HALIDE_REGISTER_GENERATOR(HalideLaplace, laplace)
