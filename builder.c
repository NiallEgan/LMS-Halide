#include "pipeline.h"
//#include "pam.h"
#include <assert.h>
#include "netpbm/pam.h"



void pipeline(UCHAR* x0, UCHAR * x3, int32_t  x1, int32_t  x2) {
int32_t x4 = x1 - 1; // 4
int32_t x6 = x4 - 1; // 3
int32_t x5 = x2 - 1; // 4
int32_t x7 = x5 - 1; // 3 // TODO: Optimise this bit
int32_t x8 = x6 * x7; // 9
int32_t x9 = x8 * 3; // 27
UCHAR x10[x9];
for(int x12=1; x12 < x5; x12++) { // x12 from 1 upto 4
int32_t x27 = x12 - 1; // y-1
int32_t x28 = x6 * x27; // 3 * (y - 1)
int32_t x15 = x1 * x12; // 5 * y
for(int x14=1; x14 < x4; x14++) { // x14 from 1 upto 4
int32_t x26 = x14 - 1; // x-1
int32_t x29 = x26 + x28; // x-1 + 3 * (y-1)
int32_t x30 = 3 * x29; // 3 * (x-1 + 3 * (y-1))
int32_t x31 = x30 + 2; // ... + 2
int32_t x16 = x14 + x15; // x + 5 * y
int32_t x17 = 3 * x16; // 3 * (x + 5 * y)
int32_t x18 = x17 + 2; // ... + 2
UCHAR x19 = x0[x18];  //
int32_t x20 = (int) x19;
UCHAR x32 = x20;
x10[x31] = x32; // x10[3 * (x-1 + 3 * (y-1)) + 2] = x0[3 * (x + 5 * y) + 2]
int32_t x34 = x30 + 1;
int32_t x21 = x17 + 1;
UCHAR x22 = x0[x21];
int32_t x23 = (int) x22;
UCHAR x35 = x23;
x10[x34] = x35;
UCHAR x24 = x0[x17];
int32_t x25 = (int) x24;
UCHAR x37 = x25;
x10[x30] = x37;

}

}
memcpy(x3, x10, x9);
}

/*void pipeline(UCHAR *in, UCHAR * out, int w, int h) {
  UCHAR buf[(w - 1) * (h - 1) * 3];

  for (int y = 1; y < h - 1; y++) {
    int full_y_base = w * y;
    int adj_y_base = (w - 2) * (y - 1);

    for (int x = 1; x < w - 1; x++) {
      int full_base = (full_y_base + x) * 3;
      int adj_base = (adj_y_base + x-1) * 3;
      buf[adj_base] = in[full_base];
      buf[adj_base + 1] = in[full_base + 1];
      buf[adj_base + 2] = in[full_base + 2];
    }
  }

  memcpy(out, buf, (w-1) * (h-1) * 3);
}*/


UCHAR *pam_array_to_flat_array(tuple **arr, struct pam *pamP) {
  assert(pamP->depth == 3);
  UCHAR *flat_array = malloc(sizeof(UCHAR) * pamP->height * pamP->width * pamP->depth);

  for (int y = 0; y < pamP->height; y++) {
    for (int x = 0; x < pamP->width; x++) {
      for (int c = 0; c < 3; x++) {
        flat_array[3 * (x + y * pamP->width) + c] = arr[y][x][c];
      }
    }
  }
}

void flat_array_to_pam_struct(UCHAR *arr, struct pam *pamP) {
  tuple *row[pamP->width];
  for (int x = 0; x < pamP->width; x++) {
    row[x] = malloc(sizeof(int) * pamP->depth);
  }
  for (int y = 0; y < pamP->height; y++) {
    // Make the row

    // Write the row
  }
}


int main(int argc, char **argv) {
  // N.B. BMPv3 only for now... :(
  BMP* bmp = BMP_ReadFile(argv[1]);
  BMP* new_bmp = BMP_Create(bmp->Header.Width-2, bmp->Header.Height-2,
                            bmp->Header.BitsPerPixel);

  pipeline(bmp->Data, new_bmp->Data, bmp->Header.Width, bmp->Header.Height);

  printf("Width: %lu\n, Height: %lu\n", bmp->Header.Width, bmp->Header.Height);

  BMP_WriteFile(new_bmp, argv[2]);
  printf("%s\n", BMP_ERROR_STRING[BMP_LAST_ERROR_CODE]);

  pm_init(argv[0], 0);

  /*printf("Different: %d\n", compare_offset(bmp->Data, new_bmp->Data,
          bmp->Header.Width-2, bmp->Header.Height-2));*/

  // Use PAM to read rows into an array... inefficent, but will do for now.
  /*UCHAR inb[5 * 6 * 3];
  for (int i = 0; i < 30; i++) {
    inb[3 * i] = 3 * i;
    inb[3 * i + 1] = 3 * i + 1;
    inb[3 * i + 2] = 3 * i + 2;
  }
  print_b(inb, 30);
  UCHAR outb[3 * 4 * 3];
  pipeline(inb, outb, 5, 6);

  print_b(outb, 12);*/


}
