#include "Halide.h"
#include "HalideBuffer.h"
#include "laplace.h"
#include "halide_image_io.h"
using namespace Halide::Runtime;
using namespace Halide::Tools;


int main() {
  Buffer<uint8_t> input = load_image("parrot.png");
  Buffer<uint8_t> output(input.width(), input.height(), 3);
  laplace(input, output);
  save_image(output, "halide_lalace.png");
}
