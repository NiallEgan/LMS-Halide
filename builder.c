#include "pipeline.h"

void pipeline(UCHAR* x0, UCHAR* x1) {
  int w = 1024;
  int h = 768;

  UCHAR x2[w * h * 3];
  for(int x4=0; x4 < h; x4++) {
    int32_t x7 = w * x4;
    for(int x6=0; x6 < w; x6++) {
      int32_t x8 = x6 + x7;
      int32_t x9 = 3 * x8;
      int32_t x10 = x9 + 2;
      UCHAR x11 = x0[x10];
      x2[x10] = x11;

      int32_t x12 = x9 + 1;
      UCHAR x13 = x0[x12];
      x2[x12] = x13;
      UCHAR x14 = x0[x9];
      x2[x9] = x14;
    }

  }

  for(int x22=0; x22 < h; x22++) {
    int32_t x24 = w * x22;
    for(int x23=0; x23 < w; x23++) {
      int32_t x25 = x23 + x24;
      int32_t x26 = 3 * x25;
      int32_t x27 = x26 + 2;
      UCHAR x28 = x2[x27];
      int32_t x29 = x26 + 1;
      UCHAR x30 = x2[x29];
      UCHAR x31 = x2[x26];
      x1[x27] = x28;
      x1[x29] = x30;
      x1[x26] = x31;
    }
  }
}

int compare(UCHAR *a, UCHAR *b, int w, int h) {
  for (int x = 0; x < w; x++) {
    for (int y = 0; y < h; y++) {
      int base = (x + y * x) * 3;
      if (a[base] != b[base] || a[base+1] != b[base+1] || a[base+2] != b[base+2]) return 1;
    }
  }
  return 0;
}

int main(int argc, char **argv) {
  // N.B. BMPv3 only for now... :(
  BMP* bmp = BMP_ReadFile(argv[1]);
  printf("%s\n", BMP_ERROR_STRING[BMP_LAST_ERROR_CODE]);
  BMP* new_bmp = BMP_Create(bmp->Header.Width, bmp->Header.Height, bmp->Header.BitsPerPixel);

  pipeline(bmp->Data, new_bmp->Data);
  printf("Different = %d\n", compare(bmp->Data, new_bmp->Data, 1024, 768));
  printf("%s\n", BMP_ERROR_STRING[BMP_LAST_ERROR_CODE]);

  printf("Width: %lu\n, Height: %lu\n", bmp->Header.Width, bmp->Header.Height);

  BMP_WriteFile(new_bmp, argv[2]);
}
