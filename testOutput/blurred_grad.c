#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x5 = x3 - 1;
int32_t x6 = x4 * x5;
int32_t x7 = x6 * 3;
UCHAR x8[x7];
for(int x10=0; x10 < x5; x10++) {
int32_t x23 = x4 * x10;
int32_t x15 = x10 + 1;
for(int x12=0; x12 < x4; x12++) {
int32_t x24 = x12 + x23;
int32_t x25 = 3 * x24;
int32_t x26 = x25 + 2;
int32_t x13 = x12 + 1;
int32_t x14 = x13 + x10;
int32_t x16 = x12 + x15;
int32_t x17 = x14 + x16;
int32_t x18 = x13 + x15;
int32_t x19 = x17 + x18;
int32_t x20 = x12 + x10;
int32_t x21 = x19 + x20;
int32_t x22 = x21 / 4;
UCHAR x27 = x22;
x8[x26] = x27;
int32_t x29 = x25 + 1;
x8[x29] = x27;
x8[x25] = x27;

}

}
for(int x36=0; x36 < x5; x36++) {
int32_t x38 = x4 * x36;
for(int x37=0; x37 < x4; x37++) {
int32_t x39 = x37 + x38;
int32_t x40 = 3 * x39;
int32_t x41 = x40 + 2;
UCHAR x42 = x8[x41];
int32_t x44 = x40 + 1;
UCHAR x45 = x8[x44];
UCHAR x47 = x8[x40];
int32_t x43 = (int) x42;
UCHAR x49 = x43;
x1[x41] = x49;
int32_t x46 = (int) x45;
UCHAR x51 = x46;
x1[x44] = x51;
int32_t x48 = (int) x47;
UCHAR x53 = x48;
x1[x40] = x53;

}

}
}
