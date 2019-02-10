#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x5 = MIN(x4, x2);
int32_t x8 = x5 - 1;
int32_t x6 = x3 - 1;
int32_t x7 = MIN(x6, x3);
int32_t x9 = x7 - 1;
int32_t x10 = x8 * x9;
int32_t x11 = x10 * 3;
UCHAR *x55 = malloc(sizeof(UCHAR) * x11);
for(int x14=1; x14 < x7; x14++) {
int32_t x18 = x14 - 1;
int32_t x22 = x14 + 1;
int32_t x40 = x8 * x18;
for(int x16=1; x16 < x5; x16++) {
int32_t x17 = x16 + 1;
int32_t x19 = x17 + x18;
int32_t x20 = x17 + x14;
int32_t x21 = x19 + x20;
int32_t x23 = x17 + x22;
int32_t x24 = x21 + x23;
int32_t x25 = x16 + x18;
int32_t x26 = x24 + x25;
int32_t x27 = x16 + x14;
int32_t x28 = x26 + x27;
int32_t x29 = x16 + x22;
int32_t x30 = x28 + x29;
int32_t x31 = x16 - 1;
int32_t x32 = x31 + x18;
int32_t x33 = x30 + x32;
int32_t x34 = x31 + x14;
int32_t x35 = x33 + x34;
int32_t x36 = x31 + x22;
int32_t x37 = x35 + x36;
int32_t x38 = x37 / 9;
UCHAR x39 = x38;
int32_t x41 = x31 + x40;
int32_t x42 = 3 * x41;
int32_t x43 = x42 + 2;
x55[x43] = x39;
int32_t x45 = x42 + 1;
x55[x45] = x39;
x55[x42] = x39;

}

}
memcpy(x1, x55, x11);
free(x55);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
