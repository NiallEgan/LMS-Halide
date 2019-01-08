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
int32_t x13 = x6 * 3;
int32_t x14 = x13 * 3;
for(int x12=1; x12 < x5; x12++) {
UCHAR x15[x14];
int32_t x16 = x12 + -1;
int32_t x17 = x12 + 1;
int32_t x18 = x17 + 1;
for(int x20=x16; x20 < x18; x20++) {
int32_t x65 = x20 - x16;
int32_t x66 = x6 * x65;
int32_t x23 = x2 * x20;
for(int x22=1; x22 < x4; x22++) {
int32_t x48 = x22 - 1;
int32_t x67 = x48 + x66;
int32_t x68 = 3 * x67;
int32_t x69 = x68 + 2;
int32_t x24 = x22 + x23;
int32_t x25 = 3 * x24;
int32_t x26 = x25 + 2;
UCHAR x27 = x0[x26];
int32_t x28 = (int) x27;
int32_t x34 = x22 + 1;
int32_t x35 = x34 + x23;
int32_t x36 = 3 * x35;
int32_t x37 = x36 + 2;
UCHAR x38 = x0[x37];
int32_t x39 = (int) x38;
int32_t x45 = x28 + x39;
int32_t x49 = x48 + x23;
int32_t x50 = 3 * x49;
int32_t x51 = x50 + 2;
UCHAR x52 = x0[x51];
int32_t x53 = (int) x52;
int32_t x59 = x45 + x53;
int32_t x62 = x59 / 3;
UCHAR x70 = x62;
x15[x69] = x70;
int32_t x72 = x68 + 1;
int32_t x29 = x25 + 1;
UCHAR x30 = x0[x29];
int32_t x31 = (int) x30;
int32_t x40 = x36 + 1;
UCHAR x41 = x0[x40];
int32_t x42 = (int) x41;
int32_t x46 = x31 + x42;
int32_t x54 = x50 + 1;
UCHAR x55 = x0[x54];
int32_t x56 = (int) x55;
int32_t x60 = x46 + x56;
int32_t x63 = x60 / 3;
UCHAR x73 = x63;
x15[x72] = x73;
UCHAR x32 = x0[x25];
int32_t x33 = (int) x32;
UCHAR x43 = x0[x36];
int32_t x44 = (int) x43;
int32_t x47 = x33 + x44;
UCHAR x57 = x0[x50];
int32_t x58 = (int) x57;
int32_t x61 = x47 + x58;
int32_t x64 = x61 / 3;
UCHAR x75 = x64;
x15[x68] = x75;

}

}
int32_t x83 = x12 - x16;
int32_t x84 = x6 * x83;
int32_t x95 = x17 - x16;
int32_t x96 = x6 * x95;
int32_t x110 = x12 - 1;
int32_t x111 = x110 - x16;
int32_t x112 = x6 * x111;
int32_t x129 = x6 * x110;
for(int x81=1; x81 < x4; x81++) {
int32_t x82 = x81 - 1;
int32_t x85 = x82 + x84;
int32_t x86 = 3 * x85;
int32_t x87 = x86 + 2;
UCHAR x88 = x15[x87];
int32_t x90 = x86 + 1;
UCHAR x91 = x15[x90];
UCHAR x93 = x15[x86];
int32_t x97 = x82 + x96;
int32_t x98 = 3 * x97;
int32_t x99 = x98 + 2;
UCHAR x100 = x15[x99];
int32_t x102 = x98 + 1;
UCHAR x103 = x15[x102];
UCHAR x105 = x15[x98];
int32_t x113 = x82 + x112;
int32_t x114 = 3 * x113;
int32_t x115 = x114 + 2;
UCHAR x116 = x15[x115];
int32_t x118 = x114 + 1;
UCHAR x119 = x15[x118];
UCHAR x121 = x15[x114];
int32_t x130 = x82 + x129;
int32_t x131 = 3 * x130;
int32_t x132 = x131 + 2;
int32_t x89 = (int) x88;
int32_t x101 = (int) x100;
int32_t x107 = x89 + x101;
int32_t x117 = (int) x116;
int32_t x123 = x107 + x117;
int32_t x126 = x123 / 3;
UCHAR x133 = x126;
x10[x132] = x133;
int32_t x135 = x131 + 1;
int32_t x92 = (int) x91;
int32_t x104 = (int) x103;
int32_t x108 = x92 + x104;
int32_t x120 = (int) x119;
int32_t x124 = x108 + x120;
int32_t x127 = x124 / 3;
UCHAR x136 = x127;
x10[x135] = x136;
int32_t x94 = (int) x93;
int32_t x106 = (int) x105;
int32_t x109 = x94 + x106;
int32_t x122 = (int) x121;
int32_t x125 = x109 + x122;
int32_t x128 = x125 / 3;
UCHAR x138 = x128;
x10[x131] = x138;

}

}
memcpy(x1, x10, x9);
}
#define WIDTH_OUT_DIFF (2)
#define HEIGHT_OUT_DIFF (2)
