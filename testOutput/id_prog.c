/*****************************************
  Emitting C Generated Code                  
*******************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
void pipeline(UCHAR[] * x0, UCHAR[] * x3, int32_t  x1, int32_t  x2) {
int32_t x4 = x1 * x2;
int32_t x5 = x4 * 3;
UCHAR x6[x5];
for(int x8=0; x8 < x2; x8++) {
int32_t x11 = x1 * x8;
for(int x10=0; x10 < x1; x10++) {
int32_t x12 = x10 + x11;
int32_t x13 = 3 * x12;
int32_t x14 = x13 + 2;
UCHAR x15 = x0[x14];
int32_t x16 = (int) x15;
UCHAR x22 = x16;
x6[x14] = x22;
int32_t x17 = x13 + 1;
UCHAR x18 = x0[x17];
int32_t x19 = (int) x18;
UCHAR x24 = x19;
x6[x17] = x24;
UCHAR x20 = x0[x13];
int32_t x21 = (int) x20;
UCHAR x26 = x21;
x6[x13] = x26;

}

}
for(int x32=0; x32 < x2; x32++) {
int32_t x34 = x1 * x32;
for(int x33=0; x33 < x1; x33++) {
int32_t x35 = x33 + x34;
int32_t x36 = 3 * x35;
int32_t x37 = x36 + 2;
UCHAR x38 = x6[x37];
int32_t x40 = x36 + 1;
UCHAR x41 = x6[x40];
UCHAR x43 = x6[x36];
int32_t x39 = (int) x38;
UCHAR x45 = x39;
x3[x37] = x45;
int32_t x42 = (int) x41;
UCHAR x47 = x42;
x3[x40] = x47;
int32_t x44 = (int) x43;
UCHAR x49 = x44;
x3[x36] = x49;

}

}
}
/*****************************************
  End of C Generated Code                  
*******************************************/
