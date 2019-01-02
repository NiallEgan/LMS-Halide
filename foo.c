#include "testOutput/pipeline.h"
#include <stdio.h>

int main() {
  UCHAR in[5 * 5 * 3];
  for (int i = 0; i < 5; i++) {
    for (int j = 0; j < 5; j++) {
      in[3 * (j + 5 * i)] = j + 5 * i + 1;
      in[3 * (j + 5 * i) + 1] = j + 5 * i + 1;
      in[3 * (j + 5 * i) + 2] = j + 5 * i + 1;
    }
  }
  UCHAR out[3 * 3 * 3];
  int w = 5;
  int h = 5;

  pipeline(in, out, w, h);

  for (int i = 0; i < 3; i++) {
    printf("\n");
    for (int j = 0; j < 3; j++) {
      printf("(%d, %d, %d) ", out[3* (j + 3 * i)],
                             out[3 * (j + 3 * i) + 1],
                             out[3 * (j + 3 * i) + 2]);
    }
  }
}
