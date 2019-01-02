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
int32_t x16 = x12 + -1;
int32_t x17 = x12 + 1;
int32_t x18 = x17 + 1;
int32_t x82 = x12 - x16;
int32_t x83 = 3 * x82;
int32_t x84 = x83 + 2;
int32_t x87 = x83 + 1;
int32_t x92 = x17 - x16;
int32_t x93 = 3 * x92;
int32_t x94 = x93 + 2;
int32_t x97 = x93 + 1;
int32_t x105 = x12 - 1;
int32_t x106 = x105 - x16;
int32_t x107 = 3 * x106;
int32_t x108 = x107 + 2;
int32_t x111 = x107 + 1;
int32_t x123 = x6 * x105;
for(int x14=1; x14 < x4; x14++) {
UCHAR x15[9];
int32_t x21 = x14 + 1;
for(int x20=x16; x20 < x18; x20++) {
int32_t x67 = x20 - x16;
int32_t x24 = x2 * x20;
for(int x23=x14; x23 < x21; x23++) {
int32_t x66 = x23 - x14;
int32_t x68 = x66 + x67;
int32_t x69 = 3 * x68;
int32_t x70 = x69 + 2;
int32_t x25 = x23 + x24;
int32_t x26 = 3 * x25;
int32_t x27 = x26 + 2;
UCHAR x28 = x0[x27];
int32_t x29 = (int) x28;
int32_t x35 = x23 + 1;
int32_t x36 = x35 + x24;
int32_t x37 = 3 * x36;
int32_t x38 = x37 + 2;
UCHAR x39 = x0[x38];
int32_t x40 = (int) x39;
int32_t x46 = x29 + x40;
int32_t x49 = x23 - 1;
int32_t x50 = x49 + x24;
int32_t x51 = 3 * x50;
int32_t x52 = x51 + 2;
UCHAR x53 = x0[x52];
int32_t x54 = (int) x53;
int32_t x60 = x46 + x54;
int32_t x63 = x60 / 3;
UCHAR x71 = x63;
x15[x70] = x71;
int32_t x73 = x69 + 1;
int32_t x30 = x26 + 1;
UCHAR x31 = x0[x30];
int32_t x32 = (int) x31;
int32_t x41 = x37 + 1;
UCHAR x42 = x0[x41];
int32_t x43 = (int) x42;
int32_t x47 = x32 + x43;
int32_t x55 = x51 + 1;
UCHAR x56 = x0[x55];
int32_t x57 = (int) x56;
int32_t x61 = x47 + x57;
int32_t x64 = x61 / 3;
UCHAR x74 = x64;
x15[x73] = x74;
UCHAR x33 = x0[x26];
int32_t x34 = (int) x33;
UCHAR x44 = x0[x37];
int32_t x45 = (int) x44;
int32_t x48 = x34 + x45;
UCHAR x58 = x0[x51];
int32_t x59 = (int) x58;
int32_t x62 = x48 + x59;
int32_t x65 = x62 / 3;
UCHAR x76 = x65;
x15[x69] = x76;

}

}
UCHAR x85 = x15[x84];
UCHAR x88 = x15[x87];
UCHAR x90 = x15[x83];
UCHAR x95 = x15[x94];
UCHAR x98 = x15[x97];
UCHAR x100 = x15[x93];
UCHAR x109 = x15[x108];
UCHAR x112 = x15[x111];
UCHAR x114 = x15[x107];
int32_t x122 = x14 - 1;
int32_t x124 = x122 + x123;
int32_t x125 = 3 * x124;
int32_t x126 = x125 + 2;
int32_t x86 = (int) x85;
int32_t x96 = (int) x95;
int32_t x102 = x86 + x96;
int32_t x110 = (int) x109;
int32_t x116 = x102 + x110;
int32_t x119 = x116 / 3;
UCHAR x127 = x119;
x10[x126] = x127;
int32_t x129 = x125 + 1;
int32_t x89 = (int) x88;
int32_t x99 = (int) x98;
int32_t x103 = x89 + x99;
int32_t x113 = (int) x112;
int32_t x117 = x103 + x113;
int32_t x120 = x117 / 3;
UCHAR x130 = x120;
x10[x129] = x130;
int32_t x91 = (int) x90;
int32_t x101 = (int) x100;
int32_t x104 = x91 + x101;
int32_t x115 = (int) x114;
int32_t x118 = x104 + x115;
int32_t x121 = x118 / 3;
UCHAR x132 = x121;
x10[x125] = x132;

}

}
memcpy(x1, x10, x9);
}
#define WIDTH_OUT_DIFF (2)
#define HEIGHT_OUT_DIFF (2)
