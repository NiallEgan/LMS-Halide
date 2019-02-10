#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x8 = x4 - 1;
int32_t x9 = x8 * x3;
int32_t x10 = x9 * 3;
int32_t *x148 = malloc(sizeof(int32_t) * x10);
int32_t x5 = x3 - 1;
int32_t x12 = x5 - 1;
int32_t x13 = x8 * x12;
int32_t x14 = x13 * 3;
UCHAR *x149 = malloc(sizeof(UCHAR) * x14);
for(int x17=1; x17 < x5; x17++) {
int32_t x18 = x17 + -1;
int32_t x20 = x18 / 2;
int32_t x19 = x17 + 1;
int32_t x22 = x19 % 2;
bool x23 = x22 == 0;
int32_t x26;
if (x23) {
int32_t x24 = x19 / 2;
x26 = x24;
} else {
int32_t x24 = x19 / 2;
int32_t x25 = x24 + 1;
x26 = x25;
}
int32_t x27 = x26 + 1;
for(int x29=x20; x29 < x27; x29++) {
int32_t x34 = x29 * 2;
for(int x31=0; x31 < 2; x31++) {
int32_t x35 = x34 + x31;
int32_t x36 = x35 + x18;
bool x37 = x36 < 2;
bool x38 = x36 == x19;
bool x39 = x37 || x38;
int32_t x40 = x2 * x36;
int32_t x82 = x8 * x36;
for(int x33=1; x33 < x4; x33++) {
if (x39) {
int32_t x41 = x33 + x40;
int32_t x42 = 3 * x41;
int32_t x43 = x42 + 2;
UCHAR x44 = x0[x43];
int32_t x56 = (int) x44;
int32_t x48 = x33 + 1;
int32_t x49 = x48 + x40;
int32_t x50 = 3 * x49;
int32_t x51 = x50 + 2;
UCHAR x52 = x0[x51];
int32_t x59 = (int) x52;
int32_t x62 = x56 + x59;
int32_t x65 = x33 - 1;
int32_t x66 = x65 + x40;
int32_t x67 = 3 * x66;
int32_t x68 = x67 + 2;
UCHAR x69 = x0[x68];
int32_t x73 = (int) x69;
int32_t x76 = x62 + x73;
int32_t x79 = x76 / 3;
int32_t x83 = x65 + x82;
int32_t x84 = 3 * x83;
int32_t x85 = x84 + 2;
x148[x85] = x79;
int32_t x45 = x42 + 1;
UCHAR x46 = x0[x45];
int32_t x57 = (int) x46;
int32_t x53 = x50 + 1;
UCHAR x54 = x0[x53];
int32_t x60 = (int) x54;
int32_t x63 = x57 + x60;
int32_t x70 = x67 + 1;
UCHAR x71 = x0[x70];
int32_t x74 = (int) x71;
int32_t x77 = x63 + x74;
int32_t x80 = x77 / 3;
int32_t x87 = x84 + 1;
x148[x87] = x80;
UCHAR x47 = x0[x42];
int32_t x58 = (int) x47;
UCHAR x55 = x0[x50];
int32_t x61 = (int) x55;
int32_t x64 = x58 + x61;
UCHAR x72 = x0[x67];
int32_t x75 = (int) x72;
int32_t x78 = x64 + x75;
int32_t x81 = x78 / 3;
x148[x84] = x81;
} else {
}

}

}

}
int32_t x100 = x8 * x17;
int32_t x108 = x8 * x19;
int32_t x119 = x17 - 1;
int32_t x120 = x8 * x119;
for(int x98=1; x98 < x4; x98++) {
int32_t x99 = x98 - 1;
int32_t x101 = x99 + x100;
int32_t x102 = 3 * x101;
int32_t x103 = x102 + 2;
int32_t x161 = x148[x103];
int32_t x105 = x102 + 1;
int32_t x162 = x148[x105];
int32_t x163 = x148[x102];
int32_t x109 = x99 + x108;
int32_t x110 = 3 * x109;
int32_t x111 = x110 + 2;
int32_t x164 = x148[x111];
int32_t x113 = x110 + 1;
int32_t x165 = x148[x113];
int32_t x166 = x148[x110];
int32_t x121 = x99 + x120;
int32_t x122 = 3 * x121;
int32_t x123 = x122 + 2;
int32_t x167 = x148[x123];
int32_t x125 = x122 + 1;
int32_t x168 = x148[x125];
int32_t x169 = x148[x122];
int32_t x170 = x161 + x164;
int32_t x171 = x170 + x167;
int32_t x172 = x171 / 3;
UCHAR x173 = x172;
x149[x123] = x173;
int32_t x175 = x162 + x165;
int32_t x176 = x175 + x168;
int32_t x177 = x176 / 3;
UCHAR x178 = x177;
x149[x125] = x178;
int32_t x180 = x163 + x166;
int32_t x181 = x180 + x169;
int32_t x182 = x181 / 3;
UCHAR x183 = x182;
x149[x122] = x183;

}

}
free(x148);
memcpy(x1, x149, x14);
free(x149);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
