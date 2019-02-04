#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x11 = x2 * x3;
int32_t x12 = x11 * 3;
UCHAR *x43 = malloc(sizeof(UCHAR) * x12);
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
for(int x15=0; x15 < x3; x15++) {
int32_t x25 = x2 * x15;
for(int x17=0; x17 < x10; x17++) {
int32_t x20 = x17 * 2;
bool x21 = x20 > x4;
int32_t x22;
if (x21) {
x22 = x4;
} else {
x22 = x20;
}
for(int x19=0; x19 < 2; x19++) {
int32_t x23 = x22 + x19;
int32_t x26 = x23 + x25;
int32_t x27 = 3 * x26;
int32_t x28 = x27 + 2;
int32_t x24 = x23 + x15;
UCHAR x29 = x24;
x43[x28] = x29;
int32_t x31 = x27 + 1;
x43[x31] = x29;
x43[x27] = x29;

}

}

}
memcpy(x1, x43, x12);
free(x43);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
