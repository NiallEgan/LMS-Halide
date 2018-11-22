#include "pipeline.h"

void pipeline(UCHAR* x0, UCHAR* x1) {
  UCHAR x2[90];
  for(int x4=0; x4 < 6; x4++) {
    UCHAR x7 = 5 * x4;
    for(int x6=0; x6 < 5; x6++) {
      UCHAR x8 = x6 + x7;
      UCHAR x9 = x8 + 2;
      UCHAR x10 = x0[x9];
      x2[x9] = x10;
      UCHAR x11 = x8 + 1;
      UCHAR x12 = x0[x11];
      x2[x11] = x12;
      UCHAR x13 = x0[x8];
      x2[x8] = x13;
    }
  }
  for(int x21=0; x21 < 6; x21++) {
    UCHAR x23 = 5 * x21;
    for(int x22=0; x22 < 5; x22++) {
      UCHAR x24 = x22 + x23;
      UCHAR x25 = x24 + 2;
      UCHAR x26 = x2[x25];
      UCHAR x27 = x24 + 1;
      UCHAR x28 = x2[x27];
      UCHAR x29 = x2[x24];
      x1[x25] = x26;
      x1[x27] = x28;
      x1[x24] = x29;
    }
  }
}

int main(int argc, char **argv) {
  BMP* bmp = BMP_ReadFile(argv[1]);
  BMP* new_bmp = BMP_Create(bmp->Header.Width, bmp->Header.Height, bmp->Header.BitsPerPixel);

  pipeline(bmp->Data, new_bmp->Data);

  BMP_WriteFile(new_bmp, argv[2]);
}
