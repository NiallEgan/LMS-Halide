#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x13 = x4 - 1;
int32_t x14 = x13 * x3;
int32_t x15 = x14 * 3;
int32_t *x155 = malloc(sizeof(int32_t) * x15);
int32_t x5 = x3 - 1;
int32_t x17 = x5 - 1;
int32_t x18 = x13 * x17;
int32_t x19 = x18 * 3;
UCHAR *x156 = malloc(sizeof(UCHAR) * x19);
for(int x22=1; x22 < x5; x22++) {
int32_t x23 = x22 + -1;
int32_t x25 = x23 / 2;
int32_t x24 = x22 + 1;
int32_t x27 = x24 % 2;
bool x28 = x27 == 0;
int32_t x31;
if (x28) {
int32_t x29 = x24 / 2;
x31 = x29;
} else {
int32_t x29 = x24 / 2;
int32_t x30 = x29 + 1;
x31 = x30;
}
int32_t x32 = x31 + 1;
int32_t x26 = x24 + 1;
int32_t x40 = x26 - 2;
for(int x34=x25; x34 < x32; x34++) {
int32_t x39 = x34 * 2;
bool x41 = x39 > x40;
int32_t x42;
if (x41) {
x42 = x40;
} else {
x42 = x39;
}
for(int x36=0; x36 < 2; x36++) {
int32_t x43 = x42 + x36;
bool x44 = x43 < 2;
bool x45 = x43 == x24;
bool x46 = x44 || x45;
int32_t x47 = x2 * x43;
int32_t x89 = x13 * x43;
for(int x38=1; x38 < x4; x38++) {
if (x46) {
int32_t x48 = x38 + x47;
int32_t x49 = 3 * x48;
int32_t x50 = x49 + 2;
UCHAR x51 = x0[x50];
int32_t x63 = (int) x51;
int32_t x55 = x38 + 1;
int32_t x56 = x55 + x47;
int32_t x57 = 3 * x56;
int32_t x58 = x57 + 2;
UCHAR x59 = x0[x58];
int32_t x66 = (int) x59;
int32_t x69 = x63 + x66;
int32_t x72 = x38 - 1;
int32_t x73 = x72 + x47;
int32_t x74 = 3 * x73;
int32_t x75 = x74 + 2;
UCHAR x76 = x0[x75];
int32_t x80 = (int) x76;
int32_t x83 = x69 + x80;
int32_t x86 = x83 / 3;
int32_t x90 = x72 + x89;
int32_t x91 = 3 * x90;
int32_t x92 = x91 + 2;
x155[x92] = x86;
int32_t x52 = x49 + 1;
UCHAR x53 = x0[x52];
int32_t x64 = (int) x53;
int32_t x60 = x57 + 1;
UCHAR x61 = x0[x60];
int32_t x67 = (int) x61;
int32_t x70 = x64 + x67;
int32_t x77 = x74 + 1;
UCHAR x78 = x0[x77];
int32_t x81 = (int) x78;
int32_t x84 = x70 + x81;
int32_t x87 = x84 / 3;
int32_t x94 = x91 + 1;
x155[x94] = x87;
UCHAR x54 = x0[x49];
int32_t x65 = (int) x54;
UCHAR x62 = x0[x57];
int32_t x68 = (int) x62;
int32_t x71 = x65 + x68;
UCHAR x79 = x0[x74];
int32_t x82 = (int) x79;
int32_t x85 = x71 + x82;
int32_t x88 = x85 / 3;
x155[x91] = x88;
} else {
}

}

}

}
int32_t x107 = x13 * x22;
int32_t x115 = x13 * x24;
int32_t x126 = x22 - 1;
int32_t x127 = x13 * x126;
for(int x105=1; x105 < x4; x105++) {
int32_t x106 = x105 - 1;
int32_t x108 = x106 + x107;
int32_t x109 = 3 * x108;
int32_t x110 = x109 + 2;
int32_t x168 = x155[x110];
int32_t x112 = x109 + 1;
int32_t x169 = x155[x112];
int32_t x170 = x155[x109];
int32_t x116 = x106 + x115;
int32_t x117 = 3 * x116;
int32_t x118 = x117 + 2;
int32_t x171 = x155[x118];
int32_t x120 = x117 + 1;
int32_t x172 = x155[x120];
int32_t x173 = x155[x117];
int32_t x128 = x106 + x127;
int32_t x129 = 3 * x128;
int32_t x130 = x129 + 2;
int32_t x174 = x155[x130];
int32_t x132 = x129 + 1;
int32_t x175 = x155[x132];
int32_t x176 = x155[x129];
int32_t x177 = x168 + x171;
int32_t x178 = x177 + x174;
int32_t x179 = x178 / 3;
UCHAR x180 = x179;
x156[x130] = x180;
int32_t x182 = x169 + x172;
int32_t x183 = x182 + x175;
int32_t x184 = x183 / 3;
UCHAR x185 = x184;
x156[x132] = x185;
int32_t x187 = x170 + x173;
int32_t x188 = x187 + x176;
int32_t x189 = x188 / 3;
UCHAR x190 = x189;
x156[x129] = x190;

}

}
free(x155);
memcpy(x1, x156, x19);
free(x156);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
