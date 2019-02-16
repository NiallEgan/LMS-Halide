#include <string.h>
#include "../../testOutput/pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x7 = x4 - 1;
int32_t x12 = x7 * x3;
int32_t x13 = x12 * 3;
int32_t *x166 = malloc(sizeof(int32_t) * x13);
int32_t x5 = x3 - 1;
int32_t x15 = x5 - 1;
int32_t x16 = x7 * x15;
int32_t x17 = x16 * 3;
UCHAR *x167 = malloc(sizeof(UCHAR) * x17);
int32_t x30 = x7 + 2;
int32_t x31 = x30 - 1;
int32_t x32 = x31 / 2;
int32_t x6 = x4 - 2;
int32_t x52 = x6 - 1;
for(int x20=1; x20 < x5; x20++) {
int32_t x21 = x20 + -1;
int32_t x22 = x20 + 1;
int32_t x23 = x22 + 1;
int32_t x24 = x23 - x21;
int32_t x25 = x24 + 2;
int32_t x26 = x25 - 1;
int32_t x27 = x26 / 2;
int32_t x40 = x23 - 2;
int32_t x42 = x40 - x21;
for(int x29=0; x29 < x27; x29++) {
int32_t x38 = x29 * 2;
int32_t x39 = x38 + x21;
bool x41 = x39 > x40;
int32_t x43;
if (x41) {
x43 = x42;
} else {
x43 = x38;
}
for(int x34=0; x34 < x32; x34++) {
int32_t x49 = x34 * 2;
int32_t x50 = x49 + 1;
bool x51 = x50 > x6;
int32_t x53;
if (x51) {
x53 = x52;
} else {
x53 = x49;
}
for(int x36=0; x36 < 2; x36++) {
int32_t x44 = x43 + x36;
int32_t x45 = x44 + x21;
bool x46 = x45 < 2;
bool x47 = x45 == x22;
bool x48 = x46 || x47;
int32_t x56 = x2 * x45;
int32_t x97 = x7 * x45;
for(int x37=0; x37 < 2; x37++) {
if (x48) {
int32_t x54 = x53 + x37;
int32_t x55 = x54 + 1;
int32_t x57 = x55 + x56;
int32_t x58 = 3 * x57;
int32_t x59 = x58 + 2;
UCHAR x60 = x0[x59];
int32_t x72 = (int) x60;
int32_t x64 = x55 + 1;
int32_t x65 = x64 + x56;
int32_t x66 = 3 * x65;
int32_t x67 = x66 + 2;
UCHAR x68 = x0[x67];
int32_t x75 = (int) x68;
int32_t x78 = x72 + x75;
int32_t x81 = x54 + x56;
int32_t x82 = 3 * x81;
int32_t x83 = x82 + 2;
UCHAR x84 = x0[x83];
int32_t x88 = (int) x84;
int32_t x91 = x78 + x88;
int32_t x94 = x91 / 3;
int32_t x98 = x54 + x97;
int32_t x99 = 3 * x98;
int32_t x100 = x99 + 2;
x166[x100] = x94;
int32_t x61 = x58 + 1;
UCHAR x62 = x0[x61];
int32_t x73 = (int) x62;
int32_t x69 = x66 + 1;
UCHAR x70 = x0[x69];
int32_t x76 = (int) x70;
int32_t x79 = x73 + x76;
int32_t x85 = x82 + 1;
UCHAR x86 = x0[x85];
int32_t x89 = (int) x86;
int32_t x92 = x79 + x89;
int32_t x95 = x92 / 3;
int32_t x102 = x99 + 1;
x166[x102] = x95;
UCHAR x63 = x0[x58];
int32_t x74 = (int) x63;
UCHAR x71 = x0[x66];
int32_t x77 = (int) x71;
int32_t x80 = x74 + x77;
UCHAR x87 = x0[x82];
int32_t x90 = (int) x87;
int32_t x93 = x80 + x90;
int32_t x96 = x93 / 3;
x166[x99] = x96;
} else {
}

}

}

}

}
int32_t x118 = x7 * x20;
int32_t x126 = x7 * x22;
int32_t x137 = x20 - 1;
int32_t x138 = x7 * x137;
for(int x116=1; x116 < x4; x116++) {
int32_t x117 = x116 - 1;
int32_t x119 = x117 + x118;
int32_t x120 = 3 * x119;
int32_t x121 = x120 + 2;
int32_t x181 = x166[x121];
int32_t x123 = x120 + 1;
int32_t x182 = x166[x123];
int32_t x183 = x166[x120];
int32_t x127 = x117 + x126;
int32_t x128 = 3 * x127;
int32_t x129 = x128 + 2;
int32_t x184 = x166[x129];
int32_t x131 = x128 + 1;
int32_t x185 = x166[x131];
int32_t x186 = x166[x128];
int32_t x139 = x117 + x138;
int32_t x140 = 3 * x139;
int32_t x141 = x140 + 2;
int32_t x187 = x166[x141];
int32_t x143 = x140 + 1;
int32_t x188 = x166[x143];
int32_t x189 = x166[x140];
int32_t x190 = x181 + x184;
int32_t x191 = x190 + x187;
int32_t x192 = x191 / 3;
UCHAR x193 = x192;
x167[x141] = x193;
int32_t x195 = x182 + x185;
int32_t x196 = x195 + x188;
int32_t x197 = x196 / 3;
UCHAR x198 = x197;
x167[x143] = x198;
int32_t x200 = x183 + x186;
int32_t x201 = x200 + x189;
int32_t x202 = x201 / 3;
UCHAR x203 = x202;
x167[x140] = x203;

}

}
free(x166);
memcpy(x1, x167, x17);
free(x167);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
