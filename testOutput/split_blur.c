#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x13 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x14 = x5 - 1;
int32_t x15 = x13 * x14;
int32_t x16 = x15 * 3;
UCHAR *x171 = malloc(sizeof(UCHAR) * x16);
int32_t x20 = x13 * 3;
int32_t x21 = x20 * 3;
for(int x19=1; x19 < x5; x19++) {
UCHAR *x172 = malloc(sizeof(UCHAR) * x21);
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
x172[x90] = x91;
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
x172[x93] = x94;
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
x172[x89] = x96;

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
UCHAR x182 = x172[x110];
int32_t x113 = x109 + 1;
UCHAR x183 = x172[x113];
UCHAR x184 = x172[x109];
int32_t x120 = x105 + x119;
int32_t x121 = 3 * x120;
int32_t x122 = x121 + 2;
UCHAR x185 = x172[x122];
int32_t x125 = x121 + 1;
UCHAR x186 = x172[x125];
UCHAR x187 = x172[x121];
int32_t x136 = x105 + x135;
int32_t x137 = 3 * x136;
int32_t x138 = x137 + 2;
UCHAR x188 = x172[x138];
int32_t x141 = x137 + 1;
UCHAR x189 = x172[x141];
UCHAR x190 = x172[x137];
int32_t x153 = x105 + x152;
int32_t x154 = 3 * x153;
int32_t x155 = x154 + 2;
int32_t x191 = (int) x182;
int32_t x192 = (int) x185;
int32_t x193 = x191 + x192;
int32_t x194 = (int) x188;
int32_t x195 = x193 + x194;
int32_t x196 = x195 / 3;
UCHAR x197 = x196;
x171[x155] = x197;
int32_t x158 = x154 + 1;
int32_t x199 = (int) x183;
int32_t x200 = (int) x186;
int32_t x201 = x199 + x200;
int32_t x202 = (int) x189;
int32_t x203 = x201 + x202;
int32_t x204 = x203 / 3;
UCHAR x205 = x204;
x171[x158] = x205;
int32_t x207 = (int) x184;
int32_t x208 = (int) x187;
int32_t x209 = x207 + x208;
int32_t x210 = (int) x190;
int32_t x211 = x209 + x210;
int32_t x212 = x211 / 3;
UCHAR x213 = x212;
x171[x154] = x213;

}
free(x172);

}
memcpy(x1, x171, x16);
free(x171);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
