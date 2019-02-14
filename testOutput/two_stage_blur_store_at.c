#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR *x143 = malloc(sizeof(UCHAR) * x9);
int32_t x13 = x6 * 3;
int32_t x14 = x13 * 3;
for(int x12=1; x12 < x5; x12++) {
int32_t *x144 = malloc(sizeof(int32_t) * x14);
int32_t x16 = x12 + -1;
int32_t x19 = x12 + 1;
int32_t x20 = x19 + 1;
int32_t x87 = x12 - x16;
int32_t x88 = x6 * x87;
int32_t x96 = x19 - x16;
int32_t x97 = x6 * x96;
int32_t x108 = x12 - 1;
int32_t x109 = x108 - x16;
int32_t x110 = x6 * x109;
int32_t x127 = x6 * x108;
for(int x18=1; x18 < x4; x18++) {
int32_t x23 = x18 + 1;
for(int x22=x16; x22 < x20; x22++) {
int32_t x29 = x2 * x22;
int32_t x71 = x22 - x16;
int32_t x72 = x6 * x71;
for(int x25=x18; x25 < x23; x25++) {
bool x26 = x25 < 1;
bool x27 = x25 == x18;
bool x28 = x26 || x27;
if (x28) {
int32_t x30 = x25 + x29;
int32_t x31 = 3 * x30;
int32_t x32 = x31 + 2;
UCHAR x33 = x0[x32];
int32_t x45 = (int) x33;
int32_t x37 = x25 + 1;
int32_t x38 = x37 + x29;
int32_t x39 = 3 * x38;
int32_t x40 = x39 + 2;
UCHAR x41 = x0[x40];
int32_t x48 = (int) x41;
int32_t x51 = x45 + x48;
int32_t x54 = x25 - 1;
int32_t x55 = x54 + x29;
int32_t x56 = 3 * x55;
int32_t x57 = x56 + 2;
UCHAR x58 = x0[x57];
int32_t x62 = (int) x58;
int32_t x65 = x51 + x62;
int32_t x68 = x65 / 3;
int32_t x73 = x54 + x72;
int32_t x74 = 3 * x73;
int32_t x75 = x74 + 2;
x144[x75] = x68;
int32_t x34 = x31 + 1;
UCHAR x35 = x0[x34];
int32_t x46 = (int) x35;
int32_t x42 = x39 + 1;
UCHAR x43 = x0[x42];
int32_t x49 = (int) x43;
int32_t x52 = x46 + x49;
int32_t x59 = x56 + 1;
UCHAR x60 = x0[x59];
int32_t x63 = (int) x60;
int32_t x66 = x52 + x63;
int32_t x69 = x66 / 3;
int32_t x77 = x74 + 1;
x144[x77] = x69;
UCHAR x36 = x0[x31];
int32_t x47 = (int) x36;
UCHAR x44 = x0[x39];
int32_t x50 = (int) x44;
int32_t x53 = x47 + x50;
UCHAR x61 = x0[x56];
int32_t x64 = (int) x61;
int32_t x67 = x53 + x64;
int32_t x70 = x67 / 3;
x144[x74] = x70;
} else {
}

}

}
int32_t x86 = x18 - 1;
int32_t x89 = x86 + x88;
int32_t x90 = 3 * x89;
int32_t x91 = x90 + 2;
int32_t x154 = x144[x91];
int32_t x93 = x90 + 1;
int32_t x155 = x144[x93];
int32_t x156 = x144[x90];
int32_t x98 = x86 + x97;
int32_t x99 = 3 * x98;
int32_t x100 = x99 + 2;
int32_t x157 = x144[x100];
int32_t x102 = x99 + 1;
int32_t x158 = x144[x102];
int32_t x159 = x144[x99];
int32_t x111 = x86 + x110;
int32_t x112 = 3 * x111;
int32_t x113 = x112 + 2;
int32_t x160 = x144[x113];
int32_t x115 = x112 + 1;
int32_t x161 = x144[x115];
int32_t x162 = x144[x112];
int32_t x128 = x86 + x127;
int32_t x129 = 3 * x128;
int32_t x130 = x129 + 2;
int32_t x163 = x154 + x157;
int32_t x164 = x163 + x160;
int32_t x165 = x164 / 3;
UCHAR x166 = x165;
x143[x130] = x166;
int32_t x132 = x129 + 1;
int32_t x168 = x155 + x158;
int32_t x169 = x168 + x161;
int32_t x170 = x169 / 3;
UCHAR x171 = x170;
x143[x132] = x171;
int32_t x173 = x156 + x159;
int32_t x174 = x173 + x162;
int32_t x175 = x174 / 3;
UCHAR x176 = x175;
x143[x129] = x176;

}
free(x144);

}
memcpy(x1, x143, x9);
free(x143);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
