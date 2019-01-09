#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x18 = x2 * x3;
int32_t x19 = x18 * 3;
UCHAR x20[x19];
int32_t x11 = x3 - 2;
int32_t x12 = x11 % 2;
bool x13 = x12 == 0;
int32_t x17;
if (x13) {
int32_t x14 = x11 / 2;
int32_t x15 = x14 + 1;
x17 = x15;
} else {
int32_t x14 = x11 / 2;
int32_t x16 = x14 + 2;
x17 = x16;
}
int32_t x4 = x2 - 2;
int32_t x5 = x4 % 2;
bool x6 = x5 == 0;
int32_t x10;
if (x6) {
int32_t x7 = x4 / 2;
int32_t x8 = x7 + 1;
x10 = x8;
} else {
int32_t x7 = x4 / 2;
int32_t x9 = x7 + 2;
x10 = x9;
}
for(int x22=0; x22 < x17; x22++) {
int32_t x32 = x22 * 2;
bool x33 = x32 > x11;
int32_t x34;
if (x33) {
x34 = x11;
} else {
x34 = x32;
}
for(int x24=0; x24 < 2; x24++) {
int32_t x35 = x34 + x24;
int32_t x37 = x2 * x35;
for(int x26=0; x26 < x10; x26++) {
int32_t x28 = x26 * 2;
bool x29 = x28 > x4;
int32_t x30;
if (x29) {
x30 = x4;
} else {
x30 = x28;
}
for(int x27=0; x27 < 2; x27++) {
int32_t x31 = x30 + x27;
int32_t x38 = x31 + x37;
int32_t x39 = 3 * x38;
int32_t x40 = x39 + 2;
int32_t x36 = x31 + x35;
UCHAR x41 = x36;
x20[x40] = x41;
int32_t x43 = x39 + 1;
x20[x43] = x41;
x20[x39] = x41;

}

}

}

}
memcpy(x1, x20, x19);
}
#define WIDTH_OUT_DIFF (0)
#define HEIGHT_OUT_DIFF (0)
