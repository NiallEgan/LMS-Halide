#include "qdbmp.c"
#include "pipeline.h"

int main(int argc, char **argv) {
  BMP* bmp = BMP_ReadFile(argv[1]);
  BMP* new_bmp = BMP_Create(bmp->Header->width, bmp->Header->height, bmp->Header->depth)

  pipeline(bmp->Data, new_bmp->Data)

  BMP_WriteFile(new_bmp, argv[2])
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
