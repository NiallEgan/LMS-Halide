#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR *x133 = malloc(sizeof(UCHAR) * x9);
for(int x12=1; x12 < x5; x12++) {
int32_t x16 = x12 + -1;
int32_t x17 = x12 + 1;
int32_t x18 = x17 + 1;
int32_t x82 = x12 - x16;
int32_t x83 = 3 * x82;
int32_t x84 = x83 + 2;
int32_t x86 = x83 + 1;
int32_t x89 = x17 - x16;
int32_t x90 = 3 * x89;
int32_t x91 = x90 + 2;
int32_t x93 = x90 + 1;
int32_t x99 = x12 - 1;
int32_t x100 = x99 - x16;
int32_t x101 = 3 * x100;
int32_t x102 = x101 + 2;
int32_t x104 = x101 + 1;
int32_t x117 = x6 * x99;
for(int x14=1; x14 < x4; x14++) {
int32_t *x134 = malloc(sizeof(int32_t) * 9);
int32_t x21 = x14 + 1;
for(int x20=x16; x20 < x18; x20++) {
int32_t x24 = x2 * x20;
int32_t x70 = x20 - x16;
for(int x23=x14; x23 < x21; x23++) {
int32_t x25 = x23 + x24;
int32_t x26 = 3 * x25;
int32_t x27 = x26 + 2;
UCHAR x28 = x0[x27];
uint16_t x40 = (uint16_t) x28;
int32_t x32 = x23 + 1;
int32_t x33 = x32 + x24;
int32_t x34 = 3 * x33;
int32_t x35 = x34 + 2;
UCHAR x36 = x0[x35];
uint16_t x43 = (uint16_t) x36;
uint16_t x46 = x40 + x43;
int32_t x49 = x23 - 1;
int32_t x50 = x49 + x24;
int32_t x51 = 3 * x50;
int32_t x52 = x51 + 2;
UCHAR x53 = x0[x52];
uint16_t x57 = (uint16_t) x53;
uint16_t x60 = x46 + x57;
int32_t x63 = (int) x60;
int32_t x66 = x63 / 3;
int32_t x69 = x23 - x14;
int32_t x71 = x69 + x70;
int32_t x72 = 3 * x71;
int32_t x73 = x72 + 2;
x134[x73] = x66;
int32_t x29 = x26 + 1;
UCHAR x30 = x0[x29];
uint16_t x41 = (uint16_t) x30;
int32_t x37 = x34 + 1;
UCHAR x38 = x0[x37];
uint16_t x44 = (uint16_t) x38;
uint16_t x47 = x41 + x44;
int32_t x54 = x51 + 1;
UCHAR x55 = x0[x54];
uint16_t x58 = (uint16_t) x55;
uint16_t x61 = x47 + x58;
int32_t x64 = (int) x61;
int32_t x67 = x64 / 3;
int32_t x75 = x72 + 1;
x134[x75] = x67;
UCHAR x31 = x0[x26];
uint16_t x42 = (uint16_t) x31;
UCHAR x39 = x0[x34];
uint16_t x45 = (uint16_t) x39;
uint16_t x48 = x42 + x45;
UCHAR x56 = x0[x51];
uint16_t x59 = (uint16_t) x56;
uint16_t x62 = x48 + x59;
int32_t x65 = (int) x62;
int32_t x68 = x65 / 3;
x134[x72] = x68;

}

}
int32_t x142 = x134[x84];
int32_t x143 = x134[x86];
int32_t x144 = x134[x83];
int32_t x145 = x134[x91];
int32_t x146 = x134[x93];
int32_t x147 = x134[x90];
int32_t x148 = x134[x102];
int32_t x149 = x134[x104];
int32_t x150 = x134[x101];
int32_t x116 = x14 - 1;
int32_t x118 = x116 + x117;
int32_t x119 = 3 * x118;
int32_t x120 = x119 + 2;
int32_t x151 = x142 + x145;
int32_t x152 = x151 + x148;
int32_t x153 = x152 / 3;
UCHAR x154 = (UCHAR) x153;
x133[x120] = x154;
int32_t x122 = x119 + 1;
int32_t x156 = x143 + x146;
int32_t x157 = x156 + x149;
int32_t x158 = x157 / 3;
UCHAR x159 = (UCHAR) x158;
x133[x122] = x159;
int32_t x161 = x144 + x147;
int32_t x162 = x161 + x150;
int32_t x163 = x162 / 3;
UCHAR x164 = (UCHAR) x163;
x133[x119] = x164;
free(x134);

}

}
memcpy(x1, x133, x9);
free(x133);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
