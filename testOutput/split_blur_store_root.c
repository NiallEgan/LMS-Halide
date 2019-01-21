#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x13 = x4 - 1;
int32_t x14 = x13 * x3;
int32_t x15 = x14 * 3;
UCHAR x16[x15];
int32_t x5 = x3 - 1;
int32_t x17 = x5 - 1;
int32_t x18 = x13 * x17;
int32_t x19 = x18 * 3;
UCHAR x20[x19];
for(int x22=1; x22 < x5; x22++) {
int32_t x23 = x22 + -1;
int32_t x25 = x23 / 2;
int32_t x24 = x22 + 1;
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
bool x44 = x43 < 2;
bool x45 = x43 == x24;
bool x46 = x44 || x45;
int32_t x89 = x13 * x43;
int32_t x47 = x2 * x43;
for(int x38=1; x38 < x4; x38++) {
if (x46) {
int32_t x72 = x38 - 1;
int32_t x90 = x72 + x89;
int32_t x91 = 3 * x90;
int32_t x92 = x91 + 2;
int32_t x48 = x38 + x47;
int32_t x49 = 3 * x48;
int32_t x50 = x49 + 2;
UCHAR x51 = x0[x50];
int32_t x52 = (int) x51;
int32_t x58 = x38 + 1;
int32_t x59 = x58 + x47;
int32_t x60 = 3 * x59;
int32_t x61 = x60 + 2;
UCHAR x62 = x0[x61];
int32_t x63 = (int) x62;
int32_t x69 = x52 + x63;
int32_t x73 = x72 + x47;
int32_t x74 = 3 * x73;
int32_t x75 = x74 + 2;
UCHAR x76 = x0[x75];
int32_t x77 = (int) x76;
int32_t x83 = x69 + x77;
int32_t x86 = x83 / 3;
UCHAR x93 = x86;
x16[x92] = x93;
int32_t x95 = x91 + 1;
int32_t x53 = x49 + 1;
UCHAR x54 = x0[x53];
int32_t x55 = (int) x54;
int32_t x64 = x60 + 1;
UCHAR x65 = x0[x64];
int32_t x66 = (int) x65;
int32_t x70 = x55 + x66;
int32_t x78 = x74 + 1;
UCHAR x79 = x0[x78];
int32_t x80 = (int) x79;
int32_t x84 = x70 + x80;
int32_t x87 = x84 / 3;
UCHAR x96 = x87;
x16[x95] = x96;
UCHAR x56 = x0[x49];
int32_t x57 = (int) x56;
UCHAR x67 = x0[x60];
int32_t x68 = (int) x67;
int32_t x71 = x57 + x68;
UCHAR x81 = x0[x74];
int32_t x82 = (int) x81;
int32_t x85 = x71 + x82;
int32_t x88 = x85 / 3;
UCHAR x98 = x88;
x16[x91] = x98;
} else {
}

}

}

}
int32_t x110 = x13 * x22;
int32_t x121 = x13 * x24;
int32_t x135 = x22 - 1;
int32_t x136 = x13 * x135;
for(int x108=1; x108 < x4; x108++) {
int32_t x109 = x108 - 1;
int32_t x111 = x109 + x110;
int32_t x112 = 3 * x111;
int32_t x113 = x112 + 2;
UCHAR x114 = x16[x113];
int32_t x116 = x112 + 1;
UCHAR x117 = x16[x116];
UCHAR x119 = x16[x112];
int32_t x122 = x109 + x121;
int32_t x123 = 3 * x122;
int32_t x124 = x123 + 2;
UCHAR x125 = x16[x124];
int32_t x127 = x123 + 1;
UCHAR x128 = x16[x127];
UCHAR x130 = x16[x123];
int32_t x137 = x109 + x136;
int32_t x138 = 3 * x137;
int32_t x139 = x138 + 2;
UCHAR x140 = x16[x139];
int32_t x142 = x138 + 1;
UCHAR x143 = x16[x142];
UCHAR x145 = x16[x138];
int32_t x115 = (int) x114;
int32_t x126 = (int) x125;
int32_t x132 = x115 + x126;
int32_t x141 = (int) x140;
int32_t x147 = x132 + x141;
int32_t x150 = x147 / 3;
UCHAR x153 = x150;
x20[x139] = x153;
int32_t x118 = (int) x117;
int32_t x129 = (int) x128;
int32_t x133 = x118 + x129;
int32_t x144 = (int) x143;
int32_t x148 = x133 + x144;
int32_t x151 = x148 / 3;
UCHAR x155 = x151;
x20[x142] = x155;
int32_t x120 = (int) x119;
int32_t x131 = (int) x130;
int32_t x134 = x120 + x131;
int32_t x146 = (int) x145;
int32_t x149 = x134 + x146;
int32_t x152 = x149 / 3;
UCHAR x157 = x152;
x20[x138] = x157;

}

}
memcpy(x1, x20, x19);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
