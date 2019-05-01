#include <stdio.h>


#include "../../testOutput/pipeline.h"
#include "../benchmark.h"
#include "HalideBuffer.h"
#include "halide_blur.h"

using namespace Halide::Runtime;
int input_width, input_height;
UCHAR *input_buffer, *output_buffer;
Buffer<uint16_t> in, out;

void test_mine() {
  pipeline(input_buffer, output_buffer, input_width, input_height);
}

void test_halide() {
  halide_blur(in, out);
  // Sync device execution if any.
  out.device_sync();
}

int main(int argc, char *argv[]) {
  input_width = atoi(argv[1]);
  input_height = atoi(argv[2]);

  input_buffer = (UCHAR *) malloc(sizeof(UCHAR) * input_width * input_height * 3);
  output_buffer = (UCHAR *) malloc(sizeof(UCHAR) * (input_width - 2) * (input_height - 2) * 3);
  printf("size: %d %d\n", input_width, input_height);
  in = Buffer<uint16_t>(input_width, input_height);
  out = Buffer<uint16_t>(input_width - 2, input_height - 2);

  for (int y = 0; y < input_height; y++) {
    for (int x = 0; x < input_width; x++) {
      unsigned char v = (unsigned char) (rand() & 0xFFF);
      in(x, y) = v;
      int base = x + y * input_width;
      input_buffer[3 * base] = v;
      input_buffer[3 * base + 1] = v;
      input_buffer[3 * base + 2] = v;
    }
  }



  //double time = benchmark(&test_clean, 5, 15, 0.01);
  //printf("Clean time is: %f\n", time);
  double my_time = benchmark(&test_mine, 1, 1, 0.01);
  printf("My time is: %f\n", my_time);
/*  double fast_time = benchmark(&test_fast, 1, 1, 0.01);
  printf("Hand optimized is: %f\n", fast_time);*/
  double halide_time = benchmark(&test_halide, 1, 1, 0.01);
  printf("Halide's time is: %f\n", halide_time);


  for (int y = 1; y < input_height - 1; y++) {
    for (int x = 1; x < input_width - 1; x++) {
      int base = (x - 1) + (input_width - 2) * (y - 1);
      assert(output_buffer[3 * base] == (unsigned char) out(x-1, y-1));
      /*if (output_buffer[3 * base] != (unsigned char) out(x-1, y-1)) {
        printf("Difference at (%d, %d)\n", x, y);
        printf("%d, %d\n", input_buffer[3 * base], (unsigned char) out(x-1, y-1));
      }*/
    }
  }

}
