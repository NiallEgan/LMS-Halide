#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 * x3;
int32_t x5 = x4 * 3;
UCHAR *x28 = malloc(sizeof(UCHAR) * x5);
for(int x8=0; x8 < x3; x8++) {
int32_t x13 = x2 * x8;
for(int x10=0; x10 < x2; x10++) {
int32_t x11 = x10 + x8;
UCHAR x12 = (UCHAR) x11;
int32_t x14 = x10 + x13;
int32_t x15 = 3 * x14;
int32_t x16 = x15 + 2;
x28[x16] = x12;
int32_t x18 = x15 + 1;
x28[x18] = x12;
x28[x15] = x12;

}

}
memcpy(x1, x28, x5);
free(x28);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
