#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x8 = x2 * x3;
int32_t x9 = x8 * 3;
UCHAR *x53 = malloc(sizeof(UCHAR) * x9);
int32_t x11 = x3 + 2;
int32_t x12 = x11 - 1;
int32_t x13 = x12 / 2;
int32_t x16 = x2 + 2;
int32_t x17 = x16 - 1;
int32_t x18 = x17 / 2;
int32_t x4 = x2 - 2;
int32_t x6 = x3 - 2;
for(int x15=0; x15 < x13; x15++) {
int32_t x28 = x15 * 2;
bool x29 = x28 > x6;
int32_t x30;
if (x29) {
x30 = x6;
} else {
x30 = x28;
}
for(int x20=0; x20 < x18; x20++) {
int32_t x24 = x20 * 2;
bool x25 = x24 > x4;
int32_t x26;
if (x25) {
x26 = x4;
} else {
x26 = x24;
}
for(int x22=0; x22 < 2; x22++) {
int32_t x31 = x30 + x22;
int32_t x34 = x2 * x31;
for(int x23=0; x23 < 2; x23++) {
int32_t x27 = x26 + x23;
int32_t x32 = x27 + x31;
UCHAR x33 = (UCHAR) x32;
int32_t x35 = x27 + x34;
int32_t x36 = 3 * x35;
int32_t x37 = x36 + 2;
x53[x37] = x33;
int32_t x39 = x36 + 1;
x53[x39] = x33;
x53[x36] = x33;

}

}

}

}
memcpy(x1, x53, x9);
free(x53);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
