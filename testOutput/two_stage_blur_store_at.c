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
int32_t x19 = x12 + 1;
int32_t x20 = x19 + 1;
int32_t x90 = x12 - x16;
int32_t x91 = x6 * x90;
int32_t x102 = x19 - x16;
int32_t x103 = x6 * x102;
int32_t x117 = x12 - 1;
int32_t x118 = x117 - x16;
int32_t x119 = x6 * x118;
int32_t x136 = x6 * x117;
for(int x18=1; x18 < x4; x18++) {
int32_t x23 = x18 + 1;
for(int x22=x16; x22 < x20; x22++) {
int32_t x71 = x22 - x16;
int32_t x72 = x6 * x71;
int32_t x29 = x2 * x22;
for(int x25=x18; x25 < x23; x25++) {
bool x26 = x25 < 1;
bool x27 = x25 == x18;
bool x28 = x26 || x27;
if (x28) {
int32_t x54 = x25 - 1;
int32_t x73 = x54 + x72;
int32_t x74 = 3 * x73;
int32_t x75 = x74 + 2;
int32_t x30 = x25 + x29;
int32_t x31 = 3 * x30;
int32_t x32 = x31 + 2;
UCHAR x33 = x0[x32];
int32_t x34 = (int) x33;
int32_t x40 = x25 + 1;
int32_t x41 = x40 + x29;
int32_t x42 = 3 * x41;
int32_t x43 = x42 + 2;
UCHAR x44 = x0[x43];
int32_t x45 = (int) x44;
int32_t x51 = x34 + x45;
int32_t x55 = x54 + x29;
int32_t x56 = 3 * x55;
int32_t x57 = x56 + 2;
UCHAR x58 = x0[x57];
int32_t x59 = (int) x58;
int32_t x65 = x51 + x59;
int32_t x68 = x65 / 3;
UCHAR x76 = x68;
x15[x75] = x76;
int32_t x78 = x74 + 1;
int32_t x35 = x31 + 1;
UCHAR x36 = x0[x35];
int32_t x37 = (int) x36;
int32_t x46 = x42 + 1;
UCHAR x47 = x0[x46];
int32_t x48 = (int) x47;
int32_t x52 = x37 + x48;
int32_t x60 = x56 + 1;
UCHAR x61 = x0[x60];
int32_t x62 = (int) x61;
int32_t x66 = x52 + x62;
int32_t x69 = x66 / 3;
UCHAR x79 = x69;
x15[x78] = x79;
UCHAR x38 = x0[x31];
int32_t x39 = (int) x38;
UCHAR x49 = x0[x42];
int32_t x50 = (int) x49;
int32_t x53 = x39 + x50;
UCHAR x63 = x0[x56];
int32_t x64 = (int) x63;
int32_t x67 = x53 + x64;
int32_t x70 = x67 / 3;
UCHAR x81 = x70;
x15[x74] = x81;
} else {
}

}

}
int32_t x89 = x18 - 1;
int32_t x92 = x89 + x91;
int32_t x93 = 3 * x92;
int32_t x94 = x93 + 2;
UCHAR x95 = x15[x94];
int32_t x97 = x93 + 1;
UCHAR x98 = x15[x97];
UCHAR x100 = x15[x93];
int32_t x104 = x89 + x103;
int32_t x105 = 3 * x104;
int32_t x106 = x105 + 2;
UCHAR x107 = x15[x106];
int32_t x109 = x105 + 1;
UCHAR x110 = x15[x109];
UCHAR x112 = x15[x105];
int32_t x120 = x89 + x119;
int32_t x121 = 3 * x120;
int32_t x122 = x121 + 2;
UCHAR x123 = x15[x122];
int32_t x125 = x121 + 1;
UCHAR x126 = x15[x125];
UCHAR x128 = x15[x121];
int32_t x137 = x89 + x136;
int32_t x138 = 3 * x137;
int32_t x139 = x138 + 2;
int32_t x96 = (int) x95;
int32_t x108 = (int) x107;
int32_t x114 = x96 + x108;
int32_t x124 = (int) x123;
int32_t x130 = x114 + x124;
int32_t x133 = x130 / 3;
UCHAR x140 = x133;
x10[x139] = x140;
int32_t x142 = x138 + 1;
int32_t x99 = (int) x98;
int32_t x111 = (int) x110;
int32_t x115 = x99 + x111;
int32_t x127 = (int) x126;
int32_t x131 = x115 + x127;
int32_t x134 = x131 / 3;
UCHAR x143 = x134;
x10[x142] = x143;
int32_t x101 = (int) x100;
int32_t x113 = (int) x112;
int32_t x116 = x101 + x113;
int32_t x129 = (int) x128;
int32_t x132 = x116 + x129;
int32_t x135 = x132 / 3;
UCHAR x145 = x135;
x10[x138] = x145;

}

}
memcpy(x1, x10, x9);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
