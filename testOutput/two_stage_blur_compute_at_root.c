#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x7 = x6 * x3;
int32_t x8 = x7 * 3;
int32_t *x128 = malloc(sizeof(int32_t) * x8);
for(int x11=0; x11 < x3; x11++) {
int32_t x14 = x2 * x11;
int32_t x59 = x6 * x11;
for(int x13=1; x13 < x4; x13++) {
int32_t x15 = x13 + x14;
int32_t x16 = 3 * x15;
int32_t x17 = x16 + 2;
UCHAR x18 = x0[x17];
uint16_t x30 = (uint16_t) x18;
int32_t x22 = x13 + 1;
int32_t x23 = x22 + x14;
int32_t x24 = 3 * x23;
int32_t x25 = x24 + 2;
UCHAR x26 = x0[x25];
uint16_t x33 = (uint16_t) x26;
uint16_t x36 = x30 + x33;
int32_t x39 = x13 - 1;
int32_t x40 = x39 + x14;
int32_t x41 = 3 * x40;
int32_t x42 = x41 + 2;
UCHAR x43 = x0[x42];
uint16_t x47 = (uint16_t) x43;
uint16_t x50 = x36 + x47;
int32_t x53 = (int) x50;
int32_t x56 = x53 / 3;
int32_t x60 = x39 + x59;
int32_t x61 = 3 * x60;
int32_t x62 = x61 + 2;
x128[x62] = x56;
int32_t x19 = x16 + 1;
UCHAR x20 = x0[x19];
uint16_t x31 = (uint16_t) x20;
int32_t x27 = x24 + 1;
UCHAR x28 = x0[x27];
uint16_t x34 = (uint16_t) x28;
uint16_t x37 = x31 + x34;
int32_t x44 = x41 + 1;
UCHAR x45 = x0[x44];
uint16_t x48 = (uint16_t) x45;
uint16_t x51 = x37 + x48;
int32_t x54 = (int) x51;
int32_t x57 = x54 / 3;
int32_t x64 = x61 + 1;
x128[x64] = x57;
UCHAR x21 = x0[x16];
uint16_t x32 = (uint16_t) x21;
UCHAR x29 = x0[x24];
uint16_t x35 = (uint16_t) x29;
uint16_t x38 = x32 + x35;
UCHAR x46 = x0[x41];
uint16_t x49 = (uint16_t) x46;
uint16_t x52 = x38 + x49;
int32_t x55 = (int) x52;
int32_t x58 = x55 / 3;
x128[x61] = x58;

}

}
int32_t x5 = x3 - 1;
int32_t x71 = x5 - 1;
int32_t x72 = x6 * x71;
int32_t x73 = x72 * 3;
UCHAR *x136 = malloc(sizeof(UCHAR) * x73);
for(int x76=1; x76 < x5; x76++) {
int32_t x79 = x6 * x76;
int32_t x87 = x76 + 1;
int32_t x88 = x6 * x87;
int32_t x99 = x76 - 1;
int32_t x100 = x6 * x99;
for(int x77=1; x77 < x4; x77++) {
int32_t x78 = x77 - 1;
int32_t x80 = x78 + x79;
int32_t x81 = 3 * x80;
int32_t x82 = x81 + 2;
int32_t x137 = x128[x82];
int32_t x84 = x81 + 1;
int32_t x138 = x128[x84];
int32_t x139 = x128[x81];
int32_t x89 = x78 + x88;
int32_t x90 = 3 * x89;
int32_t x91 = x90 + 2;
int32_t x140 = x128[x91];
int32_t x93 = x90 + 1;
int32_t x141 = x128[x93];
int32_t x142 = x128[x90];
int32_t x101 = x78 + x100;
int32_t x102 = 3 * x101;
int32_t x103 = x102 + 2;
int32_t x143 = x128[x103];
int32_t x105 = x102 + 1;
int32_t x144 = x128[x105];
int32_t x145 = x128[x102];
int32_t x146 = x137 + x140;
int32_t x147 = x146 + x143;
int32_t x148 = x147 / 3;
UCHAR x149 = (UCHAR) x148;
x136[x103] = x149;
int32_t x151 = x138 + x141;
int32_t x152 = x151 + x144;
int32_t x153 = x152 / 3;
UCHAR x154 = (UCHAR) x153;
x136[x105] = x154;
int32_t x156 = x139 + x142;
int32_t x157 = x156 + x145;
int32_t x158 = x157 / 3;
UCHAR x159 = (UCHAR) x158;
x136[x102] = x159;

}

}
free(x128);
memcpy(x1, x136, x73);
free(x136);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
