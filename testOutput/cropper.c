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
int32_t x27 = x12 - 1;
int32_t x28 = x6 * x27;
int32_t x15 = x2 * x12;
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
memcpy(x1, x10, x9);
}
