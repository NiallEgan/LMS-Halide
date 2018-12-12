#include "pipeline.h"

int compare(UCHAR *a, UCHAR *b, int w, int h) {
  for (int x = 0; x < w; x++) {
    for (int y = 0; y < h; y++) {
      int base = (x + y * x) * 3;
      if (a[base] != b[base] || a[base+1] != b[base+1] || a[base+2] != b[base+2]) return 1;
    }
  }
  return 0;
}

int compare_offset(UCHAR *a, UCHAR *b, int w, int h) {
  for (int x = 0; x < w; x++) {
    for (int y = 0; y < h; y++) {
      int base = (x + y * w) * 3;
      int base_adjusted = (x + 1 + (y + 1) * (w + 2)) * 3;
      if (a[base_adjusted] != b[base] || a[base_adjusted+1] != b[base+1]
          || a[base_adjusted+2] != b[base+2]) {

            printf("Different at: %d, %d with adjusted base %d\n", x, y, base_adjusted / 3);
            return 1;
      }
    }
  }
  return 0;
}

void print_b(UCHAR *b, int size) {
  for (int i = 0; i < size; i++) {
    printf("%d: %d, %d, %d\n", i, b[3 * i],  b[3 * i + 1],  b[3 * i + 2]);
  }
}
