#include "testOutput/pipeline.h"
#include <stdio.h>

int main() {
  int x_max = 16;
  int y_max = 32;
  UCHAR input[x_max * y_max * 3];

  for (int y = 0; y < y_max; y++) {
    for (int x = 0; x < x_max; x++) {
      int b = (x + y * x_max);
      input[3 * b] = b;
      input[3 * b + 1] = b;
      input[3 * b + 2] = b;
    }
  }
  UCHAR output[(x_max -2) * (y_max-2) * 3];

  pipeline(input, output, x_max, y_max);

  for (int y = 1; y < y_max-1; y++) {
    printf("\n");
    for (int x = 1; x < x_max-1; x++) {
      printf("%d ", output[3 * ((x-1) + (y-1) * (x_max-2))]);
    }
  }
}
