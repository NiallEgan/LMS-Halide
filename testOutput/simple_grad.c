#include <string.h>
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
memcpy(x1, x6, x5);
}
#define WIDTH_OUT_DIFF (0)
#define HEIGHT_OUT_DIFF (0)
