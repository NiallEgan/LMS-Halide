/*#include "Halide.h"
#include "HalideBuffer.h"
#include "bilateral_grid.h"
#include "halide_image_io.h"
using namespace Halide::Runtime;
using namespace Halide::Tools;


int main(int argc, char **argv) {
  int input_width = 500;
  int input_height = 700;
  Buffer<float> input = load_and_convert_image(argv[1]);
  Buffer<float> output(input.width(), input.height());
  bilateral_grid(input, 0.1, output);
  convert_and_save_image(output, argv[2]);
}
*/
