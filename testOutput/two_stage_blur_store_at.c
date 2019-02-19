#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR *x146 = malloc(sizeof(UCHAR) * x9);
int32_t x13 = x6 * 3;
int32_t x14 = x13 * 3;
for(int x12=1; x12 < x5; x12++) {
int32_t *x147 = malloc(sizeof(int32_t) * x14);
int32_t x16 = x12 + -1;
int32_t x19 = x12 + 1;
int32_t x20 = x19 + 1;
int32_t x90 = x12 - x16;
int32_t x91 = x6 * x90;
int32_t x99 = x19 - x16;
int32_t x100 = x6 * x99;
int32_t x111 = x12 - 1;
int32_t x112 = x111 - x16;
int32_t x113 = x6 * x112;
int32_t x130 = x6 * x111;
for(int x18=1; x18 < x4; x18++) {
int32_t x23 = x18 + 1;
for(int x22=x16; x22 < x20; x22++) {
int32_t x29 = x2 * x22;
int32_t x74 = x22 - x16;
int32_t x75 = x6 * x74;
for(int x25=x18; x25 < x23; x25++) {
bool x26 = x25 < 1;
bool x27 = x25 == x18;
bool x28 = x26 || x27;
if (x28) {
int32_t x30 = x25 + x29;
int32_t x31 = 3 * x30;
int32_t x32 = x31 + 2;
UCHAR x33 = x0[x32];
uint16_t x45 = (uint16_t) x33;
int32_t x37 = x25 + 1;
int32_t x38 = x37 + x29;
int32_t x39 = 3 * x38;
int32_t x40 = x39 + 2;
UCHAR x41 = x0[x40];
uint16_t x48 = (uint16_t) x41;
uint16_t x51 = x45 + x48;
int32_t x54 = x25 - 1;
int32_t x55 = x54 + x29;
int32_t x56 = 3 * x55;
int32_t x57 = x56 + 2;
UCHAR x58 = x0[x57];
uint16_t x62 = (uint16_t) x58;
uint16_t x65 = x51 + x62;
int32_t x68 = (int) x65;
int32_t x71 = x68 / 3;
int32_t x76 = x54 + x75;
int32_t x77 = 3 * x76;
int32_t x78 = x77 + 2;
x147[x78] = x71;
int32_t x34 = x31 + 1;
UCHAR x35 = x0[x34];
uint16_t x46 = (uint16_t) x35;
int32_t x42 = x39 + 1;
UCHAR x43 = x0[x42];
uint16_t x49 = (uint16_t) x43;
uint16_t x52 = x46 + x49;
int32_t x59 = x56 + 1;
UCHAR x60 = x0[x59];
uint16_t x63 = (uint16_t) x60;
uint16_t x66 = x52 + x63;
int32_t x69 = (int) x66;
int32_t x72 = x69 / 3;
int32_t x80 = x77 + 1;
x147[x80] = x72;
UCHAR x36 = x0[x31];
uint16_t x47 = (uint16_t) x36;
UCHAR x44 = x0[x39];
uint16_t x50 = (uint16_t) x44;
uint16_t x53 = x47 + x50;
UCHAR x61 = x0[x56];
uint16_t x64 = (uint16_t) x61;
uint16_t x67 = x53 + x64;
int32_t x70 = (int) x67;
int32_t x73 = x70 / 3;
x147[x77] = x73;
} else {
}

}

}
int32_t x89 = x18 - 1;
int32_t x92 = x89 + x91;
int32_t x93 = 3 * x92;
int32_t x94 = x93 + 2;
int32_t x157 = x147[x94];
int32_t x96 = x93 + 1;
int32_t x158 = x147[x96];
int32_t x159 = x147[x93];
int32_t x101 = x89 + x100;
int32_t x102 = 3 * x101;
int32_t x103 = x102 + 2;
int32_t x160 = x147[x103];
int32_t x105 = x102 + 1;
int32_t x161 = x147[x105];
int32_t x162 = x147[x102];
int32_t x114 = x89 + x113;
int32_t x115 = 3 * x114;
int32_t x116 = x115 + 2;
int32_t x163 = x147[x116];
int32_t x118 = x115 + 1;
int32_t x164 = x147[x118];
int32_t x165 = x147[x115];
int32_t x131 = x89 + x130;
int32_t x132 = 3 * x131;
int32_t x133 = x132 + 2;
int32_t x166 = x157 + x160;
int32_t x167 = x166 + x163;
int32_t x168 = x167 / 3;
UCHAR x169 = (UCHAR) x168;
x146[x133] = x169;
int32_t x135 = x132 + 1;
int32_t x171 = x158 + x161;
int32_t x172 = x171 + x164;
int32_t x173 = x172 / 3;
UCHAR x174 = (UCHAR) x173;
x146[x135] = x174;
int32_t x176 = x159 + x162;
int32_t x177 = x176 + x165;
int32_t x178 = x177 / 3;
UCHAR x179 = (UCHAR) x178;
x146[x132] = x179;

}
free(x147);

}
memcpy(x1, x146, x9);
free(x146);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
