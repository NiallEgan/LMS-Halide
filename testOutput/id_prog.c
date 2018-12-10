#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 * x3;
int32_t x5 = x4 * 3;
UCHAR x6[x5];
for(int x8=0; x8 < x3; x8++) {
int32_t x11 = x2 * x8;
for(int x10=0; x10 < x2; x10++) {
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
memcpy(x1, x6, x5);
}
