#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x7 = x6 * x3;
int32_t x8 = x7 * 3;
UCHAR x9[x8];
for(int x11=0; x11 < x3; x11++) {
int32_t x56 = x6 * x11;
int32_t x14 = x2 * x11;
for(int x13=1; x13 < x4; x13++) {
int32_t x39 = x13 - 1;
int32_t x57 = x39 + x56;
int32_t x58 = 3 * x57;
int32_t x59 = x58 + 2;
int32_t x15 = x13 + x14;
int32_t x16 = 3 * x15;
int32_t x17 = x16 + 2;
UCHAR x18 = x0[x17];
int32_t x19 = (int) x18;
int32_t x25 = x13 + 1;
int32_t x26 = x25 + x14;
int32_t x27 = 3 * x26;
int32_t x28 = x27 + 2;
UCHAR x29 = x0[x28];
int32_t x30 = (int) x29;
int32_t x36 = x19 + x30;
int32_t x40 = x39 + x14;
int32_t x41 = 3 * x40;
int32_t x42 = x41 + 2;
UCHAR x43 = x0[x42];
int32_t x44 = (int) x43;
int32_t x50 = x36 + x44;
int32_t x53 = x50 / 3;
UCHAR x60 = x53;
x9[x59] = x60;
int32_t x62 = x58 + 1;
int32_t x20 = x16 + 1;
UCHAR x21 = x0[x20];
int32_t x22 = (int) x21;
int32_t x31 = x27 + 1;
UCHAR x32 = x0[x31];
int32_t x33 = (int) x32;
int32_t x37 = x22 + x33;
int32_t x45 = x41 + 1;
UCHAR x46 = x0[x45];
int32_t x47 = (int) x46;
int32_t x51 = x37 + x47;
int32_t x54 = x51 / 3;
UCHAR x63 = x54;
x9[x62] = x63;
UCHAR x23 = x0[x16];
int32_t x24 = (int) x23;
UCHAR x34 = x0[x27];
int32_t x35 = (int) x34;
int32_t x38 = x24 + x35;
UCHAR x48 = x0[x41];
int32_t x49 = (int) x48;
int32_t x52 = x38 + x49;
int32_t x55 = x52 / 3;
UCHAR x65 = x55;
x9[x58] = x65;

}

}
int32_t x5 = x3 - 1;
int32_t x71 = x5 - 1;
int32_t x72 = x6 * x71;
int32_t x73 = x72 * 3;
UCHAR x74[x73];
for(int x76=1; x76 < x5; x76++) {
int32_t x79 = x6 * x76;
int32_t x90 = x76 + 1;
int32_t x91 = x6 * x90;
int32_t x105 = x76 - 1;
int32_t x106 = x6 * x105;
for(int x77=1; x77 < x4; x77++) {
int32_t x78 = x77 - 1;
int32_t x80 = x78 + x79;
int32_t x81 = 3 * x80;
int32_t x82 = x81 + 2;
UCHAR x83 = x9[x82];
int32_t x85 = x81 + 1;
UCHAR x86 = x9[x85];
UCHAR x88 = x9[x81];
int32_t x92 = x78 + x91;
int32_t x93 = 3 * x92;
int32_t x94 = x93 + 2;
UCHAR x95 = x9[x94];
int32_t x97 = x93 + 1;
UCHAR x98 = x9[x97];
UCHAR x100 = x9[x93];
int32_t x107 = x78 + x106;
int32_t x108 = 3 * x107;
int32_t x109 = x108 + 2;
UCHAR x110 = x9[x109];
int32_t x112 = x108 + 1;
UCHAR x113 = x9[x112];
UCHAR x115 = x9[x108];
int32_t x84 = (int) x83;
int32_t x96 = (int) x95;
int32_t x102 = x84 + x96;
int32_t x111 = (int) x110;
int32_t x117 = x102 + x111;
int32_t x120 = x117 / 3;
UCHAR x123 = x120;
x74[x109] = x123;
int32_t x87 = (int) x86;
int32_t x99 = (int) x98;
int32_t x103 = x87 + x99;
int32_t x114 = (int) x113;
int32_t x118 = x103 + x114;
int32_t x121 = x118 / 3;
UCHAR x125 = x121;
x74[x112] = x125;
int32_t x89 = (int) x88;
int32_t x101 = (int) x100;
int32_t x104 = x89 + x101;
int32_t x116 = (int) x115;
int32_t x119 = x104 + x116;
int32_t x122 = x119 / 3;
UCHAR x127 = x122;
x74[x108] = x127;

}

}
memcpy(x1, x74, x73);
}
#define WIDTH_OUT_DIFF (2)
#define HEIGHT_OUT_DIFF (2)
