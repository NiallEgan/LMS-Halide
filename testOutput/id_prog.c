#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 * x3;
int32_t x5 = x4 * 3;
UCHAR *x32 = malloc(sizeof(UCHAR) * x5);
for(int x8=0; x8 < x3; x8++) {
int32_t x11 = x2 * x8;
for(int x10=0; x10 < x2; x10++) {
int32_t x12 = x10 + x11;
int32_t x13 = 3 * x12;
int32_t x14 = x13 + 2;
UCHAR x15 = x0[x14];
UCHAR x19 = (UCHAR) x15;
x32[x14] = x19;
int32_t x16 = x13 + 1;
UCHAR x17 = x0[x16];
UCHAR x20 = (UCHAR) x17;
x32[x16] = x20;
UCHAR x18 = x0[x13];
UCHAR x21 = (UCHAR) x18;
x32[x13] = x21;

}

}
memcpy(x1, x32, x5);
free(x32);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
