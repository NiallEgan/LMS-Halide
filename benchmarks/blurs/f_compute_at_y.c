#include <string.h>
#include "../../testOutput/pipeline.h"
// f.computeAt(g, "y")
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR *x136 = malloc(sizeof(UCHAR) * x9);
int32_t x13 = x6 * 3;
int32_t x14 = x13 * 3;
for(int x12=1; x12 < x5; x12++) {
uint16_t *x137 = malloc(sizeof(uint16_t) * x14);
int32_t x16 = x12 + -1;
int32_t x17 = x12 + 1;
int32_t x18 = x17 + 1;
for(int x20=x16; x20 < x18; x20++) {
int32_t x23 = x2 * x20;
int32_t x65 = x20 - x16;
int32_t x66 = x6 * x65;
for(int x22=1; x22 < x4; x22++) {
int32_t x24 = x22 + x23;
int32_t x25 = 3 * x24;
int32_t x26 = x25 + 2;
UCHAR x27 = x0[x26];
uint16_t x39 = (uint16_t) x27;
int32_t x31 = x22 + 1;
int32_t x32 = x31 + x23;
int32_t x33 = 3 * x32;
int32_t x34 = x33 + 2;
UCHAR x35 = x0[x34];
uint16_t x42 = (uint16_t) x35;
uint16_t x45 = x39 + x42;
int32_t x48 = x22 - 1;
int32_t x49 = x48 + x23;
int32_t x50 = 3 * x49;
int32_t x51 = x50 + 2;
UCHAR x52 = x0[x51];
uint16_t x56 = (uint16_t) x52;
uint16_t x59 = x45 + x56;
uint16_t x62 = x59 / 3;
int32_t x67 = x48 + x66;
int32_t x68 = 3 * x67;
int32_t x69 = x68 + 2;
x137[x69] = x62;
int32_t x28 = x25 + 1;
UCHAR x29 = x0[x28];
uint16_t x40 = (uint16_t) x29;
int32_t x36 = x33 + 1;
UCHAR x37 = x0[x36];
uint16_t x43 = (uint16_t) x37;
uint16_t x46 = x40 + x43;
int32_t x53 = x50 + 1;
UCHAR x54 = x0[x53];
uint16_t x57 = (uint16_t) x54;
uint16_t x60 = x46 + x57;
uint16_t x63 = x60 / 3;
int32_t x71 = x68 + 1;
x137[x71] = x63;
UCHAR x30 = x0[x25];
uint16_t x41 = (uint16_t) x30;
UCHAR x38 = x0[x33];
uint16_t x44 = (uint16_t) x38;
uint16_t x47 = x41 + x44;
UCHAR x55 = x0[x50];
uint16_t x58 = (uint16_t) x55;
uint16_t x61 = x47 + x58;
uint16_t x64 = x61 / 3;
x137[x68] = x64;

}

}
int32_t x80 = x12 - x16;
int32_t x81 = x6 * x80;
int32_t x89 = x17 - x16;
int32_t x90 = x6 * x89;
int32_t x101 = x12 - 1;
int32_t x102 = x101 - x16;
int32_t x103 = x6 * x102;
int32_t x120 = x6 * x101;
for(int x78=1; x78 < x4; x78++) {
int32_t x79 = x78 - 1;
int32_t x82 = x79 + x81;
int32_t x83 = 3 * x82;
int32_t x84 = x83 + 2;
uint16_t x145 = x137[x84];
int32_t x86 = x83 + 1;
uint16_t x146 = x137[x86];
uint16_t x147 = x137[x83];
int32_t x91 = x79 + x90;
int32_t x92 = 3 * x91;
int32_t x93 = x92 + 2;
uint16_t x148 = x137[x93];
int32_t x95 = x92 + 1;
uint16_t x149 = x137[x95];
uint16_t x150 = x137[x92];
int32_t x104 = x79 + x103;
int32_t x105 = 3 * x104;
int32_t x106 = x105 + 2;
uint16_t x151 = x137[x106];
int32_t x108 = x105 + 1;
uint16_t x152 = x137[x108];
uint16_t x153 = x137[x105];
int32_t x121 = x79 + x120;
int32_t x122 = 3 * x121;
int32_t x123 = x122 + 2;
uint16_t x154 = x145 + x148;
uint16_t x155 = x154 + x151;
uint16_t x156 = x155 / 3;
UCHAR x157 = (UCHAR) x156;
x136[x123] = x157;
int32_t x125 = x122 + 1;
uint16_t x159 = x146 + x149;
uint16_t x160 = x159 + x152;
uint16_t x161 = x160 / 3;
UCHAR x162 = (UCHAR) x161;
x136[x125] = x162;
uint16_t x164 = x147 + x150;
uint16_t x165 = x164 + x153;
uint16_t x166 = x165 / 3;
UCHAR x167 = (UCHAR) x166;
x136[x122] = x167;

}
free(x137);

}
memcpy(x1, x136, x9);
free(x136);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
