#include <stdio.h>


#include "../../testOutput/pipeline.h"
#include "../benchmark.h"
#include "HalideBuffer.h"
#include "bilateral_grid.h"

using namespace Halide::Runtime;
int input_width, input_height;
UCHAR *input_buffer, *output_buffer;
Buffer<float> inf, outf;
float *input_bufferf, *output_bufferf;

void test_mine() {
  pipeline(input_buffer, output_buffer, input_width, input_height);
}

void test_halide() {
  bilateral_grid(inf, 0.1, outf);
  // Sync device execution if any.
  outf.device_sync();
}

int main(int argc, char *argv[]) {
  input_width = atoi(argv[1]);
  input_height = atoi(argv[2]);

  input_buffer = (UCHAR *) malloc(sizeof(UCHAR) * input_width * input_height * 3);
  output_buffer = (UCHAR *) malloc(sizeof(UCHAR) * input_width  * input_height * 3);
  input_bufferf = (float *) malloc(sizeof(float) * input_width * input_height * 4);
  output_bufferf = (float *) malloc(sizeof(float) * input_width * input_height * 4);
  printf("size: %d %d\n", input_width, input_height);
  inf = Buffer<float>(input_width, input_height);
  outf = Buffer<float>(input_width, input_height);

  for (int y = 0; y < input_height; y++) {
    for (int x = 0; x < input_width; x++) {
      uint16_t v = rand() & 0xFF;
      float vf = (float) v;
      //printf("v: %f\n", vf);
      assert(vf / 255.0f <= 1 && vf / 255.0f >= 0);
      inf(x, y) =  vf; /// 255.0f;
      int base = x + y * input_width;

      input_bufferf[4 * base] = vf / 255.0f;
      input_bufferf[4 * base + 1] = vf / 255.0f;
      input_bufferf[4 * base + 2] = vf / 255.0f;
      input_bufferf[4 * base + 3] = vf / 255.0f;
      input_buffer[3 * base] = v;
      input_buffer[3 * base + 1] = v;
      input_buffer[3 * base + 2] = v;
    }
  }



  //double time = benchmark(&test_clean, 5, 15, 0.01);
  //printf("Clean time is: %f\n", time);
  double my_time = benchmark(&test_mine, 5, 5, 0.01);
  printf("My time is: %f\n", my_time);
  //double fast_time = benchmark(&test_fast, 5, 5, 0.01);
  //printf("Hand optimized is: %f\n", fast_time);
  /*double halide_time = benchmark(&test_halide, 5, 5, 0.01);
  printf("Halide's time is: %f\n", halide_time);*/

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
