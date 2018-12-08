#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 * x3;
int32_t x5 = x4 * 3;
UCHAR x6[x5];
for(int x8=0; x8 < x3; x8++) {
int32_t x12 = x2 * x8;
for(int x10=0; x10 < x2; x10++) {
int32_t x13 = x10 + x12;
int32_t x14 = 3 * x13;
int32_t x15 = x14 + 2;
int32_t x11 = x10 + x8;
UCHAR x16 = x11;
x6[x15] = x16;
int32_t x18 = x14 + 1;
x6[x18] = x16;
x6[x14] = x16;

}

}
for(int x25=0; x25 < x3; x25++) {
int32_t x27 = x2 * x25;
for(int x26=0; x26 < x2; x26++) {
int32_t x28 = x26 + x27;
int32_t x29 = 3 * x28;
int32_t x30 = x29 + 2;
UCHAR x31 = x6[x30];
int32_t x33 = x29 + 1;
UCHAR x34 = x6[x33];
UCHAR x36 = x6[x29];
int32_t x32 = (int) x31;
UCHAR x38 = x32;
x1[x30] = x38;
int32_t x35 = (int) x34;
UCHAR x40 = x35;
x1[x33] = x40;
int32_t x37 = (int) x36;
UCHAR x42 = x37;
x1[x29] = x42;

}

}
}
