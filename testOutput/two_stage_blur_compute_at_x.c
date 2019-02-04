#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR *x142 = malloc(sizeof(UCHAR) * x9);
for(int x12=1; x12 < x5; x12++) {
int32_t x16 = x12 + -1;
int32_t x17 = x12 + 1;
int32_t x18 = x17 + 1;
int32_t x82 = x12 - x16;
int32_t x83 = 3 * x82;
int32_t x84 = x83 + 2;
int32_t x87 = x83 + 1;
int32_t x92 = x17 - x16;
int32_t x93 = 3 * x92;
int32_t x94 = x93 + 2;
int32_t x97 = x93 + 1;
int32_t x105 = x12 - 1;
int32_t x106 = x105 - x16;
int32_t x107 = 3 * x106;
int32_t x108 = x107 + 2;
int32_t x111 = x107 + 1;
int32_t x123 = x6 * x105;
for(int x14=1; x14 < x4; x14++) {
UCHAR *x143 = malloc(sizeof(UCHAR) * 9);
int32_t x21 = x14 + 1;
for(int x20=x16; x20 < x18; x20++) {
int32_t x67 = x20 - x16;
int32_t x24 = x2 * x20;
for(int x23=x14; x23 < x21; x23++) {
int32_t x66 = x23 - x14;
int32_t x68 = x66 + x67;
int32_t x69 = 3 * x68;
int32_t x70 = x69 + 2;
int32_t x25 = x23 + x24;
int32_t x26 = 3 * x25;
int32_t x27 = x26 + 2;
UCHAR x28 = x0[x27];
int32_t x29 = (int) x28;
int32_t x35 = x23 + 1;
int32_t x36 = x35 + x24;
int32_t x37 = 3 * x36;
int32_t x38 = x37 + 2;
UCHAR x39 = x0[x38];
int32_t x40 = (int) x39;
int32_t x46 = x29 + x40;
int32_t x49 = x23 - 1;
int32_t x50 = x49 + x24;
int32_t x51 = 3 * x50;
int32_t x52 = x51 + 2;
UCHAR x53 = x0[x52];
int32_t x54 = (int) x53;
int32_t x60 = x46 + x54;
int32_t x63 = x60 / 3;
UCHAR x71 = x63;
x143[x70] = x71;
int32_t x73 = x69 + 1;
int32_t x30 = x26 + 1;
UCHAR x31 = x0[x30];
int32_t x32 = (int) x31;
int32_t x41 = x37 + 1;
UCHAR x42 = x0[x41];
int32_t x43 = (int) x42;
int32_t x47 = x32 + x43;
int32_t x55 = x51 + 1;
UCHAR x56 = x0[x55];
int32_t x57 = (int) x56;
int32_t x61 = x47 + x57;
int32_t x64 = x61 / 3;
UCHAR x74 = x64;
x143[x73] = x74;
UCHAR x33 = x0[x26];
int32_t x34 = (int) x33;
UCHAR x44 = x0[x37];
int32_t x45 = (int) x44;
int32_t x48 = x34 + x45;
UCHAR x58 = x0[x51];
int32_t x59 = (int) x58;
int32_t x62 = x48 + x59;
int32_t x65 = x62 / 3;
UCHAR x76 = x65;
x143[x69] = x76;

}

}
UCHAR x151 = x143[x84];
UCHAR x152 = x143[x87];
UCHAR x153 = x143[x83];
UCHAR x154 = x143[x94];
UCHAR x155 = x143[x97];
UCHAR x156 = x143[x93];
UCHAR x157 = x143[x108];
UCHAR x158 = x143[x111];
UCHAR x159 = x143[x107];
int32_t x122 = x14 - 1;
int32_t x124 = x122 + x123;
int32_t x125 = 3 * x124;
int32_t x126 = x125 + 2;
int32_t x160 = (int) x151;
int32_t x161 = (int) x154;
int32_t x162 = x160 + x161;
int32_t x163 = (int) x157;
int32_t x164 = x162 + x163;
int32_t x165 = x164 / 3;
UCHAR x166 = x165;
x142[x126] = x166;
int32_t x129 = x125 + 1;
int32_t x168 = (int) x152;
int32_t x169 = (int) x155;
int32_t x170 = x168 + x169;
int32_t x171 = (int) x158;
int32_t x172 = x170 + x171;
int32_t x173 = x172 / 3;
UCHAR x174 = x173;
x142[x129] = x174;
int32_t x176 = (int) x153;
int32_t x177 = (int) x156;
int32_t x178 = x176 + x177;
int32_t x179 = (int) x159;
int32_t x180 = x178 + x179;
int32_t x181 = x180 / 3;
UCHAR x182 = x181;
x142[x125] = x182;
free(x143);

}

}
memcpy(x1, x142, x9);
free(x142);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
