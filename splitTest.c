#include "testOutput/pipeline.h"
#include <stdio.h>

int main() {
  int x_max = 32;
  int y_max = 32;
  UCHAR input[x_max * y_max * 3];

  for (int y = 0; y < y_max; y++) {
    for (int x = 0; x < x_max; x++) {
      int b = (x + y * x_max);
      input[3 * b] = 13;
      input[3 * b + 1] = 13;
      input[3 * b + 2] = 13;
    }
  }
  UCHAR output[(x_max - 4) * (y_max - 4) * 3];

  pipeline(input, output, x_max, y_max);

  for (int y = 0; y < y_max - 4; y++) {
    printf("\n");
    for (int x = 0; x < x_max - 4; x++) {
      printf("%d ", output[3 * (x + (y) * (x_max-4))]);
    }
  }
  printf("\n");
}
