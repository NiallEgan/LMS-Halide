#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x8 = x2 * x3;
int32_t x9 = x8 * 3;
int32_t *x104 = malloc(sizeof(int32_t) * x9);
int32_t x4 = x2 - 1;
int32_t x5 = MIN(x4, x2);
int32_t x6 = x3 - 1;
int32_t x7 = MIN(x6, x3);
int32_t x11 = x5 * x7;
int32_t x12 = x11 * 3;
UCHAR *x105 = malloc(sizeof(UCHAR) * x12);
for(int x15=0; x15 < x7; x15++) {
int32_t x16 = x15 + 1;
int32_t x17 = x16 + 1;
for(int x19=x15; x19 < x17; x19++) {
bool x22 = x19 < 1;
bool x23 = x19 == x16;
bool x24 = x22 || x23;
int32_t x26 = x2 * x19;
for(int x21=0; x21 < x2; x21++) {
if (x24) {
int32_t x25 = x21 + x19;
int32_t x27 = x21 + x26;
int32_t x28 = 3 * x27;
int32_t x29 = x28 + 2;
x104[x29] = x25;
int32_t x31 = x28 + 1;
x104[x31] = x25;
x104[x28] = x25;
} else {
}

}

}
int32_t x42 = x2 * x15;
int32_t x50 = x2 * x16;
int32_t x88 = x5 * x15;
for(int x41=0; x41 < x5; x41++) {
int32_t x43 = x41 + x42;
int32_t x44 = 3 * x43;
int32_t x45 = x44 + 2;
int32_t x115 = x104[x45];
int32_t x47 = x44 + 1;
int32_t x116 = x104[x47];
int32_t x117 = x104[x44];
int32_t x51 = x41 + x50;
int32_t x52 = 3 * x51;
int32_t x53 = x52 + 2;
int32_t x118 = x104[x53];
int32_t x55 = x52 + 1;
int32_t x119 = x104[x55];
int32_t x120 = x104[x52];
int32_t x61 = x41 + 1;
int32_t x62 = x61 + x42;
int32_t x63 = 3 * x62;
int32_t x64 = x63 + 2;
int32_t x121 = x104[x64];
int32_t x66 = x63 + 1;
int32_t x122 = x104[x66];
int32_t x123 = x104[x63];
int32_t x72 = x61 + x50;
int32_t x73 = 3 * x72;
int32_t x74 = x73 + 2;
int32_t x124 = x104[x74];
int32_t x76 = x73 + 1;
int32_t x125 = x104[x76];
int32_t x126 = x104[x73];
int32_t x89 = x41 + x88;
int32_t x90 = 3 * x89;
int32_t x91 = x90 + 2;
int32_t x127 = x115 + x118;
int32_t x128 = x127 + x121;
int32_t x129 = x128 + x124;
int32_t x130 = x129 / 4;
UCHAR x131 = (UCHAR) x130;
x105[x91] = x131;
int32_t x93 = x90 + 1;
int32_t x133 = x116 + x119;
int32_t x134 = x133 + x122;
int32_t x135 = x134 + x125;
int32_t x136 = x135 / 4;
UCHAR x137 = (UCHAR) x136;
x105[x93] = x137;
int32_t x139 = x117 + x120;
int32_t x140 = x139 + x123;
int32_t x141 = x140 + x126;
int32_t x142 = x141 / 4;
UCHAR x143 = (UCHAR) x142;
x105[x90] = x143;

}

}
free(x104);
memcpy(x1, x105, x12);
free(x105);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
