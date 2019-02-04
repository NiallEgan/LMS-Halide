#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x5 = x2 - 1;
int32_t x6 = x5 - 1;
int32_t x4 = x3 - 1;
int32_t x7 = x4 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR *x147 = malloc(sizeof(UCHAR) * x9);
int32_t x13 = x2 * 3;
for(int x12=1; x12 < x4; x12++) {
UCHAR *x148 = malloc(sizeof(UCHAR) * x13);
int32_t x17 = x12 + 1;
int32_t x127 = x12 - 1;
int32_t x128 = x6 * x127;
for(int x16=1; x16 < x5; x16++) {
int32_t x20 = x16 + -1;
int32_t x21 = x16 + 1;
int32_t x22 = x21 + 1;
for(int x19=x12; x19 < x17; x19++) {
int32_t x72 = x19 - x12;
int32_t x73 = x2 * x72;
int32_t x28 = x19 - 1;
int32_t x29 = x2 * x28;
int32_t x40 = x2 * x19;
int32_t x54 = x19 + 1;
int32_t x55 = x2 * x54;
for(int x24=x20; x24 < x22; x24++) {
bool x25 = x24 < 2;
bool x26 = x24 == x21;
bool x27 = x25 || x26;
if (x27) {
int32_t x74 = x24 + x73;
int32_t x75 = 3 * x74;
int32_t x76 = x75 + 2;
int32_t x30 = x24 + x29;
int32_t x31 = 3 * x30;
int32_t x32 = x31 + 2;
UCHAR x33 = x0[x32];
int32_t x34 = (int) x33;
int32_t x41 = x24 + x40;
int32_t x42 = 3 * x41;
int32_t x43 = x42 + 2;
UCHAR x44 = x0[x43];
int32_t x45 = (int) x44;
int32_t x51 = x34 + x45;
int32_t x56 = x24 + x55;
int32_t x57 = 3 * x56;
int32_t x58 = x57 + 2;
UCHAR x59 = x0[x58];
int32_t x60 = (int) x59;
int32_t x66 = x51 + x60;
int32_t x69 = x66 / 3;
UCHAR x77 = x69;
x148[x76] = x77;
int32_t x79 = x75 + 1;
int32_t x35 = x31 + 1;
UCHAR x36 = x0[x35];
int32_t x37 = (int) x36;
int32_t x46 = x42 + 1;
UCHAR x47 = x0[x46];
int32_t x48 = (int) x47;
int32_t x52 = x37 + x48;
int32_t x61 = x57 + 1;
UCHAR x62 = x0[x61];
int32_t x63 = (int) x62;
int32_t x67 = x52 + x63;
int32_t x70 = x67 / 3;
UCHAR x80 = x70;
x148[x79] = x80;
UCHAR x38 = x0[x31];
int32_t x39 = (int) x38;
UCHAR x49 = x0[x42];
int32_t x50 = (int) x49;
int32_t x53 = x39 + x50;
UCHAR x64 = x0[x57];
int32_t x65 = (int) x64;
int32_t x68 = x53 + x65;
int32_t x71 = x68 / 3;
UCHAR x82 = x71;
x148[x75] = x82;
} else {
}

}

}
int32_t x90 = x16 - 1;
int32_t x91 = 3 * x90;
int32_t x92 = x91 + 2;
UCHAR x158 = x148[x92];
int32_t x95 = x91 + 1;
UCHAR x159 = x148[x95];
UCHAR x160 = x148[x91];
int32_t x100 = 3 * x16;
int32_t x101 = x100 + 2;
UCHAR x161 = x148[x101];
int32_t x104 = x100 + 1;
UCHAR x162 = x148[x104];
UCHAR x163 = x148[x100];
int32_t x112 = 3 * x21;
int32_t x113 = x112 + 2;
UCHAR x164 = x148[x113];
int32_t x116 = x112 + 1;
UCHAR x165 = x148[x116];
UCHAR x166 = x148[x112];
int32_t x129 = x90 + x128;
int32_t x130 = 3 * x129;
int32_t x131 = x130 + 2;
int32_t x167 = (int) x158;
int32_t x168 = (int) x161;
int32_t x169 = x167 + x168;
int32_t x170 = (int) x164;
int32_t x171 = x169 + x170;
int32_t x172 = x171 / 3;
UCHAR x173 = x172;
x147[x131] = x173;
int32_t x134 = x130 + 1;
int32_t x175 = (int) x159;
int32_t x176 = (int) x162;
int32_t x177 = x175 + x176;
int32_t x178 = (int) x165;
int32_t x179 = x177 + x178;
int32_t x180 = x179 / 3;
UCHAR x181 = x180;
x147[x134] = x181;
int32_t x183 = (int) x160;
int32_t x184 = (int) x163;
int32_t x185 = x183 + x184;
int32_t x186 = (int) x166;
int32_t x187 = x185 + x186;
int32_t x188 = x187 / 3;
UCHAR x189 = x188;
x147[x130] = x189;

}
free(x148);

}
memcpy(x1, x147, x9);
free(x147);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
