#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR x10[x9];
for(int x12=1; x12 < x5; x12++) {
int32_t x16 = x12 - 1;
int32_t x37 = x6 * x16;
int32_t x20 = x12 + 1;
for(int x14=1; x14 < x4; x14++) {
int32_t x29 = x14 - 1;
int32_t x38 = x29 + x37;
int32_t x39 = 3 * x38;
int32_t x40 = x39 + 2;
int32_t x15 = x14 + 1;
int32_t x17 = x15 + x16;
int32_t x18 = x15 + x12;
int32_t x19 = x17 + x18;
int32_t x21 = x15 + x20;
int32_t x22 = x19 + x21;
int32_t x23 = x14 + x16;
int32_t x24 = x22 + x23;
int32_t x25 = x14 + x12;
int32_t x26 = x24 + x25;
int32_t x27 = x14 + x20;
int32_t x28 = x26 + x27;
int32_t x30 = x29 + x16;
int32_t x31 = x28 + x30;
int32_t x32 = x29 + x12;
int32_t x33 = x31 + x32;
int32_t x34 = x29 + x20;
int32_t x35 = x33 + x34;
int32_t x36 = x35 / 9;
UCHAR x41 = x36;
x10[x40] = x41;
int32_t x43 = x39 + 1;
x10[x43] = x41;
x10[x39] = x41;

}

}
memcpy(x1, x10, x9);
}
#define WIDTH_OUT_DIFF (0)
#define HEIGHT_OUT_DIFF (0)
