/*****************************************
  Emitting C Generated Code
*******************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
void pipeline(UCHAR[] * x0, UCHAR[] * x3, int32_t  x1, int32_t  x2) {
int32_t x4 = x1 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x2 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR x10[x9];
for(int x12=1; x12 < x5; x12++) {
int32_t x27 = x12 - 1;
int32_t x28 = x6 * x27;
int32_t x15 = x1 * x12;
for(int x14=1; x14 < x4; x14++) {
int32_t x26 = x14 - 1;
int32_t x29 = x26 + x28;
int32_t x30 = 3 * x29;
int32_t x31 = x30 + 2;
int32_t x16 = x14 + x15;
int32_t x17 = 3 * x16;
int32_t x18 = x17 + 2;
UCHAR x19 = x0[x18];
int32_t x20 = (int) x19;
UCHAR x32 = x20;
x10[x31] = x32;
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
for(int x44=0; x44 < x7; x44++) {
int32_t x47 = x6 * x44;
for(int x46=0; x46 < x6; x46++) {
int32_t x48 = x46 + x47;
int32_t x49 = 3 * x48;
int32_t x50 = x49 + 2;
UCHAR x51 = x10[x50];
int32_t x53 = x49 + 1;
UCHAR x54 = x10[x53];
UCHAR x56 = x10[x49];
int32_t x52 = (int) x51;
UCHAR x58 = x52;
x3[x50] = x58;
int32_t x55 = (int) x54;
UCHAR x60 = x55;
x3[x53] = x60;
int32_t x57 = (int) x56;
UCHAR x62 = x57;
x3[x49] = x62;

}

}
}
/*****************************************
  End of C Generated Code
*******************************************/

void pipeline(UCHAR *in, UCHAR * out, int w, int h) {
  int buffer[(w - 1) * (h - 1) * 3];

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
}
