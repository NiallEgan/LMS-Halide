#include <stdio.h>


#include "../../testOutput/pipeline.h"
#include "../benchmark.h"
#include "HalideBuffer.h"
#include "halide_edge.h"

using namespace Halide::Runtime;
int input_width = 3000;
int input_height = 4000;

UCHAR *input_buffer = (UCHAR *) malloc(sizeof(UCHAR) * input_width * input_height * 3);
UCHAR *output_buffer = (UCHAR *) malloc(sizeof(UCHAR) * (input_width - 2) * (input_height - 2) * 3);
Buffer<uint8_t> in(input_width, input_height, 3);
Buffer<uint8_t> out(input_width, input_height, 3);


void test_mine() {
  pipeline(input_buffer, output_buffer, input_width, input_height);
}

void test_halide() {
  halide_edge(in, out);
  // Sync device execution if any.
  out.device_sync();
}

int main() {
  for (int y = 0; y < input_height; y++) {
    for (int x = 0; x < input_width; x++) {
      uint16_t v = rand() & 0xFFF;
      in(x, y) = v;
      int base = x + y * input_width;
      input_buffer[3 * base] = v;
      input_buffer[3 * base + 1] = v;
      input_buffer[3 * base + 2] = v;
    }
  }
  //double time = benchmark(&test_clean, 5, 15, 0.01);
  //printf("Clean time is: %f\n", time);
  double my_time = benchmark(&test_mine, 5, 15, 0.01);
  printf("My time is: %f\n", my_time);
  //double fast_time = benchmark(&test_fast, 5, 15, 0.01);
  //printf("Hand optimized is: %f\n", fast_time);
  double halide_time = benchmark(&test_halide, 5, 15, 0.01);
  printf("Halide's time is: %f\n", halide_time);

  /*for (int y = 0; y < input_height - 2; y++) {
    for (int x = 0; x < input_width - 2; x++) {
      int base = x + (input_width - 2) * y;
      if (input_buffer[3 * base] != out(x, y)) {
        printf("Difference at (%d, %d)\n", x, y);
        printf("%d, %d\n", input_buffer[3 * base], out(x, y));
      }
    }
  }*/

}
