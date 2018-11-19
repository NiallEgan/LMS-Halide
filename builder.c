#include "qdbmp.c"
#include "pipeline.h"

int main(int argc, char **argv) {
  BMP* bmp = BMP_ReadFile(argv[1]);
  BMP* new_bmp = BMP_Create(bmp->Header->width, bmp->Header->height, bmp->Header->depth)

  pipeline(bmp->Data, new_bmp->Data)

  BMP_WriteFile(new_bmp, argv[2])
}

void pipeline(USHORT[] * x0, USHORT[] * x1) {
  USHORT[90] x2;
  for(int x4=0; x4 < 6; x4++) {
    USHORT x8 = 5 * x4;
    for(int x6=0; x6 < 5; x6++) {
      USHORT x7 = x6 + x4;
      USHORT x9 = x6 + x8;
      USHORT x10 = x9 + 2;
      x2[x10] = x7;
      USHORT x12 = x9 + 1;
      x2[x12] = x7;
      x2[x9] = x7;
    }
  }

  for(int x19=0; x19 < 6; x19++) {
    USHORT x24 = 5 * x19;
    USHORT x21 = x19 * 5;
    for(int x20=0; x20 < 5; x20++) {
      USHORT x25 = x20 + x24;
      USHORT x26 = x25 + 2;
      USHORT x27 = x2[x26];
      USHORT x28 = x25 + 1;
      USHORT x29 = x2[x28];
      USHORT x30 = x2[x25];
      USHORT x22 = x20 + x21;
      USHORT x23 = x22 + 2;
      x1[x23] = x27;
      USHORT x32 = x22 + 1;
      x1[x32] = x29;
      x1[x22] = x30;
    }
  }
}

/*
void pipeline(int* * x0) {
  int32_t[5][3] x1;

  for(int x3=0; x3 < 3; x3++) {
    for(int x5=0; x5 < 5; x5++) {
    int32_t x6 = x5 + x3;
    x1[x5][x3] = x6;
    }
  }
}

void pipeline(UCHAR *x0, UCHAR *out) {
  //UCHAR[5 * 3 * 3] x1;
  UCHAR *x1 = out

  for(int x3=0; x3 < 3; x3++) {
    for(int x5=0; x5 < 5; x5++) {
      UCHAR x6 = x5 + x3;
      x1[(x5 + x3 * 5) * 3] = x6;
      x1[(x5 + x3 * 5) * 3 + 1] = x6;
      x1[(x5 + x3 * 5) * 3 + 2] = x6;
    }
  }


}
*/
