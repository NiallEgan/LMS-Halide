#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x13 = x4 - 1;
int32_t x14 = x13 * x3;
int32_t x15 = x14 * 3;
UCHAR *x167 = malloc(sizeof(UCHAR) * x15);
int32_t x5 = x3 - 1;
int32_t x17 = x5 - 1;
int32_t x18 = x13 * x17;
int32_t x19 = x18 * 3;
UCHAR *x168 = malloc(sizeof(UCHAR) * x19);
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
int32_t x89 = x13 * x43;
int32_t x47 = x2 * x43;
for(int x38=1; x38 < x4; x38++) {
if (x46) {
int32_t x72 = x38 - 1;
int32_t x90 = x72 + x89;
int32_t x91 = 3 * x90;
int32_t x92 = x91 + 2;
int32_t x48 = x38 + x47;
int32_t x49 = 3 * x48;
int32_t x50 = x49 + 2;
UCHAR x51 = x0[x50];
int32_t x52 = (int) x51;
int32_t x58 = x38 + 1;
int32_t x59 = x58 + x47;
int32_t x60 = 3 * x59;
int32_t x61 = x60 + 2;
UCHAR x62 = x0[x61];
int32_t x63 = (int) x62;
int32_t x69 = x52 + x63;
int32_t x73 = x72 + x47;
int32_t x74 = 3 * x73;
int32_t x75 = x74 + 2;
UCHAR x76 = x0[x75];
int32_t x77 = (int) x76;
int32_t x83 = x69 + x77;
int32_t x86 = x83 / 3;
UCHAR x93 = x86;
x167[x92] = x93;
int32_t x95 = x91 + 1;
int32_t x53 = x49 + 1;
UCHAR x54 = x0[x53];
int32_t x55 = (int) x54;
int32_t x64 = x60 + 1;
UCHAR x65 = x0[x64];
int32_t x66 = (int) x65;
int32_t x70 = x55 + x66;
int32_t x78 = x74 + 1;
UCHAR x79 = x0[x78];
int32_t x80 = (int) x79;
int32_t x84 = x70 + x80;
int32_t x87 = x84 / 3;
UCHAR x96 = x87;
x167[x95] = x96;
UCHAR x56 = x0[x49];
int32_t x57 = (int) x56;
UCHAR x67 = x0[x60];
int32_t x68 = (int) x67;
int32_t x71 = x57 + x68;
UCHAR x81 = x0[x74];
int32_t x82 = (int) x81;
int32_t x85 = x71 + x82;
int32_t x88 = x85 / 3;
UCHAR x98 = x88;
x167[x91] = x98;
} else {
}

}

}

}
int32_t x110 = x13 * x22;
int32_t x121 = x13 * x24;
int32_t x135 = x22 - 1;
int32_t x136 = x13 * x135;
for(int x108=1; x108 < x4; x108++) {
int32_t x109 = x108 - 1;
int32_t x111 = x109 + x110;
int32_t x112 = 3 * x111;
int32_t x113 = x112 + 2;
UCHAR x180 = x167[x113];
int32_t x116 = x112 + 1;
UCHAR x181 = x167[x116];
UCHAR x182 = x167[x112];
int32_t x122 = x109 + x121;
int32_t x123 = 3 * x122;
int32_t x124 = x123 + 2;
UCHAR x183 = x167[x124];
int32_t x127 = x123 + 1;
UCHAR x184 = x167[x127];
UCHAR x185 = x167[x123];
int32_t x137 = x109 + x136;
int32_t x138 = 3 * x137;
int32_t x139 = x138 + 2;
UCHAR x186 = x167[x139];
int32_t x142 = x138 + 1;
UCHAR x187 = x167[x142];
UCHAR x188 = x167[x138];
int32_t x189 = (int) x180;
int32_t x190 = (int) x183;
int32_t x191 = x189 + x190;
int32_t x192 = (int) x186;
int32_t x193 = x191 + x192;
int32_t x194 = x193 / 3;
UCHAR x195 = x194;
x168[x139] = x195;
int32_t x197 = (int) x181;
int32_t x198 = (int) x184;
int32_t x199 = x197 + x198;
int32_t x200 = (int) x187;
int32_t x201 = x199 + x200;
int32_t x202 = x201 / 3;
UCHAR x203 = x202;
x168[x142] = x203;
int32_t x205 = (int) x182;
int32_t x206 = (int) x185;
int32_t x207 = x205 + x206;
int32_t x208 = (int) x188;
int32_t x209 = x207 + x208;
int32_t x210 = x209 / 3;
UCHAR x211 = x210;
x168[x138] = x211;

}

}
free(x167);
memcpy(x1, x168, x19);
free(x168);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
