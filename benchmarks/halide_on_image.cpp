#include "Halide.h"
#include "HalideBuffer.h"
#include "edge_filter_eval/halide_edge.h"
#include "halide_image_io.h"
using namespace Halide::Runtime;
using namespace Halide::Tools;


//Buffer<float> output(640, 480);

int main() {
  Buffer<uint8_t> input = load_image("../parrot.png");
  Buffer<uint8_t> output(input.width(), input.height(), 3);
  halide_edge(input, output);
  save_image(output, "halide_edge.png");
}
