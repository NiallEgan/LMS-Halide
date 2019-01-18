#include <stdio.h>
#include <stdlib.h>

int main() {
  int w_max = 6;
  int h_max = 6;
  int w_min = 1;
  int h_min = 1;
  int width = w_max - w_min;
  for (int i = w_min + h_min * width; i < w_max + (h_max - 1) * width; i++) {
    printf("i: %d ", i);
    int x = (i - w_min) % width + w_min;
    int y = (i - w_min) / width ;
    printf("x: %d, y: %d\n", x, y);
  }

  for (int y = h_min; y < h_max; y++) {
    for (int x = w_min; x < w_max; x++) {
      printf("j: %d x: %d, y: %d\n", y * width + x, x, y);
    }
  }
}
