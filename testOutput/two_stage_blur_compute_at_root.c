#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x7 = x6 * x3;
int32_t x8 = x7 * 3;
UCHAR *x137 = malloc(sizeof(UCHAR) * x8);
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
x137[x59] = x60;
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
x137[x62] = x63;
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
x137[x58] = x65;

}

}
int32_t x5 = x3 - 1;
int32_t x71 = x5 - 1;
int32_t x72 = x6 * x71;
int32_t x73 = x72 * 3;
UCHAR *x145 = malloc(sizeof(UCHAR) * x73);
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
UCHAR x146 = x137[x82];
int32_t x85 = x81 + 1;
UCHAR x147 = x137[x85];
UCHAR x148 = x137[x81];
int32_t x92 = x78 + x91;
int32_t x93 = 3 * x92;
int32_t x94 = x93 + 2;
UCHAR x149 = x137[x94];
int32_t x97 = x93 + 1;
UCHAR x150 = x137[x97];
UCHAR x151 = x137[x93];
int32_t x107 = x78 + x106;
int32_t x108 = 3 * x107;
int32_t x109 = x108 + 2;
UCHAR x152 = x137[x109];
int32_t x112 = x108 + 1;
UCHAR x153 = x137[x112];
UCHAR x154 = x137[x108];
int32_t x155 = (int) x146;
int32_t x156 = (int) x149;
int32_t x157 = x155 + x156;
int32_t x158 = (int) x152;
int32_t x159 = x157 + x158;
int32_t x160 = x159 / 3;
UCHAR x161 = x160;
x145[x109] = x161;
int32_t x163 = (int) x147;
int32_t x164 = (int) x150;
int32_t x165 = x163 + x164;
int32_t x166 = (int) x153;
int32_t x167 = x165 + x166;
int32_t x168 = x167 / 3;
UCHAR x169 = x168;
x145[x112] = x169;
int32_t x171 = (int) x148;
int32_t x172 = (int) x151;
int32_t x173 = x171 + x172;
int32_t x174 = (int) x154;
int32_t x175 = x173 + x174;
int32_t x176 = x175 / 3;
UCHAR x177 = x176;
x145[x108] = x177;

}

}
free(x137);
memcpy(x1, x145, x73);
free(x145);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
