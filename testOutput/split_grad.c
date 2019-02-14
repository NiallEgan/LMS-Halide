#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x6 = x2 * x3;
int32_t x7 = x6 * 3;
UCHAR *x41 = malloc(sizeof(UCHAR) * x7);
int32_t x11 = x2 + 2;
int32_t x12 = x11 - 1;
int32_t x13 = x12 / 2;
int32_t x4 = x2 - 2;
for(int x10=0; x10 < x3; x10++) {
int32_t x24 = x2 * x10;
for(int x15=0; x15 < x13; x15++) {
int32_t x18 = x15 * 2;
bool x19 = x18 > x4;
int32_t x20;
if (x19) {
x20 = x4;
} else {
x20 = x18;
}
for(int x17=0; x17 < 2; x17++) {
int32_t x21 = x20 + x17;
int32_t x22 = x21 + x10;
UCHAR x23 = x22;
int32_t x25 = x21 + x24;
int32_t x26 = 3 * x25;
int32_t x27 = x26 + 2;
x41[x27] = x23;
int32_t x29 = x26 + 1;
x41[x29] = x23;
x41[x26] = x23;

}

}

}
memcpy(x1, x41, x7);
free(x41);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
