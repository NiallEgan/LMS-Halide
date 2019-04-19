#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 / 2;
int32_t x5 = x4 - 1;
int32_t x6 = x5 - 1;
int32_t x7 = x6 * x3;
int32_t x8 = x7 * 3;
UCHAR *x64 = malloc(sizeof(UCHAR) * x8);
for(int x11=0; x11 < x3; x11++) {
int32_t x16 = x2 * x11;
for(int x13=1; x13 < x5; x13++) {
int32_t x14 = 2 * x13;
int32_t x15 = x14 + 1;
int32_t x17 = x15 + x16;
int32_t x18 = 3 * x17;
int32_t x19 = x18 + 2;
UCHAR x20 = x0[x19];
uint16_t x32 = (uint16_t) x20;
int32_t x24 = x14 - 1;
int32_t x25 = x24 + x16;
int32_t x26 = 3 * x25;
int32_t x27 = x26 + 2;
UCHAR x28 = x0[x27];
uint16_t x35 = (uint16_t) x28;
uint16_t x38 = x32 - x35;
UCHAR x41 = (UCHAR) x38;
int32_t x44 = x13 - 1;
int32_t x45 = MIN(x44, x6);
int32_t x46 = MAX(0, x45);
int32_t x47 = MIN(x44, x3);
int32_t x48 = MAX(0, x47);
int32_t x49 = x6 * x48;
int32_t x50 = x46 + x49;
int32_t x51 = 3 * x50;
int32_t x52 = x51 + 2;
x64[x52] = x41;
int32_t x21 = x18 + 1;
UCHAR x22 = x0[x21];
uint16_t x33 = (uint16_t) x22;
int32_t x29 = x26 + 1;
UCHAR x30 = x0[x29];
uint16_t x36 = (uint16_t) x30;
uint16_t x39 = x33 - x36;
UCHAR x42 = (UCHAR) x39;
int32_t x54 = x51 + 1;
x64[x54] = x42;
UCHAR x23 = x0[x18];
uint16_t x34 = (uint16_t) x23;
UCHAR x31 = x0[x26];
uint16_t x37 = (uint16_t) x31;
uint16_t x40 = x34 - x37;
UCHAR x43 = (UCHAR) x40;
x64[x51] = x43;

}

}
memcpy(x1, x64, x8);
free(x64);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 0;
int32_t WIDTH_OUT_MUL = 1;
int32_t WIDTH_OUT_DIV = 2;
int32_t HEIGHT_OUT_MUL = 1;
int32_t HEIGHT_OUT_DIV = 1;
