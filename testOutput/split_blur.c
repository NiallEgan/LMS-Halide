#include <string.h>
#include <stdio.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x13 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x14 = x5 - 1;
int32_t x15 = x13 * x14;
int32_t x16 = x15 * 3;
UCHAR x17[x16];
int32_t x20 = x13 * 3;
int32_t x21 = x20 * 3;
for(int x19=1; x19 < x5; x19++) {
UCHAR x22[x21];
int32_t x23 = x19 + -1;
int32_t x25 = x23 / 2;
int32_t x24 = x19 + 1;
int32_t x27 = x24 % 2;
bool x28 = x27 == 0;
int32_t x31;
if (x28) {
int32_t x29 = x24 / 2;
x31 = x29;
} else {
int32_t x29 = x24 / 2;
int32_t x30 = x29 + 1;
x31 = x30;
}
int32_t x32 = x31 + 1;
int32_t x26 = x24 + 1;
int32_t x40 = x26 - 2;
for(int x34=x25; x34 < x32; x34++) {
int32_t x39 = x34 * 2;
bool x41 = x39 > x40;
int32_t x42;
if (x41) {
x42 = x40;
} else {
x42 = x39;
}
for(int x36=0; x36 < 2; x36++) {
int32_t x43 = x42 + x36;
int32_t x86 = x43 - x23;
int32_t x87 = x13 * x86;
int32_t x44 = x2 * x43;
for(int x38=1; x38 < x4; x38++) {
int32_t x69 = x38 - 1;
int32_t x88 = x69 + x87;
int32_t x89 = 3 * x88;
int32_t x90 = x89 + 2;
int32_t x45 = x38 + x44;
int32_t x46 = 3 * x45;
int32_t x47 = x46 + 2;
UCHAR x48 = x0[x47];
int32_t x49 = (int) x48;
int32_t x55 = x38 + 1;
int32_t x56 = x55 + x44;
int32_t x57 = 3 * x56;
int32_t x58 = x57 + 2;
UCHAR x59 = x0[x58];
int32_t x60 = (int) x59;
int32_t x66 = x49 + x60;
int32_t x70 = x69 + x44;
int32_t x71 = 3 * x70;
int32_t x72 = x71 + 2;
UCHAR x73 = x0[x72];
int32_t x74 = (int) x73;
int32_t x80 = x66 + x74;
int32_t x83 = x80 / 3;
UCHAR x91 = x83;
x22[x90] = x91;
int32_t x93 = x89 + 1;
int32_t x50 = x46 + 1;
UCHAR x51 = x0[x50];
int32_t x52 = (int) x51;
int32_t x61 = x57 + 1;
UCHAR x62 = x0[x61];
int32_t x63 = (int) x62;
int32_t x67 = x52 + x63;
int32_t x75 = x71 + 1;
UCHAR x76 = x0[x75];
int32_t x77 = (int) x76;
int32_t x81 = x67 + x77;
int32_t x84 = x81 / 3;
UCHAR x94 = x84;
x22[x93] = x94;
UCHAR x53 = x0[x46];
int32_t x54 = (int) x53;
UCHAR x64 = x0[x57];
int32_t x65 = (int) x64;
int32_t x68 = x54 + x65;
UCHAR x78 = x0[x71];
int32_t x79 = (int) x78;
int32_t x82 = x68 + x79;
int32_t x85 = x82 / 3;
UCHAR x96 = x85;
x22[x89] = x96;

}

}

}
int32_t x106 = x19 - x23;
int32_t x107 = x13 * x106;
int32_t x118 = x24 - x23;
int32_t x119 = x13 * x118;
int32_t x133 = x19 - 1;
int32_t x134 = x133 - x23;
int32_t x135 = x13 * x134;
int32_t x152 = x13 * x133;
for(int x104=1; x104 < x4; x104++) {
int32_t x105 = x104 - 1;
int32_t x108 = x105 + x107;
int32_t x109 = 3 * x108;
int32_t x110 = x109 + 2;
UCHAR x111 = x22[x110];
int32_t x113 = x109 + 1;
UCHAR x114 = x22[x113];
UCHAR x116 = x22[x109];
int32_t x120 = x105 + x119;
int32_t x121 = 3 * x120;
int32_t x122 = x121 + 2;
UCHAR x123 = x22[x122];
int32_t x125 = x121 + 1;
UCHAR x126 = x22[x125];
UCHAR x128 = x22[x121];
int32_t x136 = x105 + x135;
int32_t x137 = 3 * x136;
int32_t x138 = x137 + 2;
UCHAR x139 = x22[x138];
int32_t x141 = x137 + 1;
UCHAR x142 = x22[x141];
UCHAR x144 = x22[x137];
int32_t x153 = x105 + x152;
int32_t x154 = 3 * x153;
int32_t x155 = x154 + 2;
int32_t x112 = (int) x111;
int32_t x124 = (int) x123;
int32_t x130 = x112 + x124;
int32_t x140 = (int) x139;
int32_t x146 = x130 + x140;
int32_t x149 = x146 / 3;
UCHAR x156 = x149;
x17[x155] = x156;
int32_t x158 = x154 + 1;
int32_t x115 = (int) x114;
int32_t x127 = (int) x126;
int32_t x131 = x115 + x127;
int32_t x143 = (int) x142;
int32_t x147 = x131 + x143;
int32_t x150 = x147 / 3;
UCHAR x159 = x150;
x17[x158] = x159;
int32_t x117 = (int) x116;
int32_t x129 = (int) x128;
int32_t x132 = x117 + x129;
int32_t x145 = (int) x144;
int32_t x148 = x132 + x145;
int32_t x151 = x148 / 3;
UCHAR x161 = x151;
x17[x154] = x161;

}

}
memcpy(x1, x17, x16);
}
#define WIDTH_OUT_DIFF (2)
#define HEIGHT_OUT_DIFF (2)
