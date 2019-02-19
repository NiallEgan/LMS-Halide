#include <string.h>
#include "../../testOutput/pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x7 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x12 = x5 - 1;
int32_t x13 = x7 * x12;
int32_t x14 = x13 * 3;
UCHAR *x169 = malloc(sizeof(UCHAR) * x14);
int32_t x18 = x7 * 3;
int32_t x19 = x18 * 3;
int32_t x30 = x7 + 2;
int32_t x31 = x30 - 1;
int32_t x32 = x31 / 2;
int32_t x6 = x4 - 2;
int32_t x41 = x6 - 1;
for(int x17=1; x17 < x5; x17++) {
int32_t *x170 = malloc(sizeof(int32_t) * x19);
int32_t x21 = x17 + -1;
int32_t x22 = x17 + 1;
int32_t x23 = x22 + 1;
int32_t x24 = x23 - x21;
int32_t x25 = x24 + 2;
int32_t x26 = x25 - 1;
int32_t x27 = x26 / 2;
int32_t x47 = x23 - 2;
int32_t x49 = x47 - x21;
for(int x29=0; x29 < x27; x29++) {
int32_t x45 = x29 * 2;
int32_t x46 = x45 + x21;
bool x48 = x46 > x47;
int32_t x50;
if (x48) {
x50 = x49;
} else {
x50 = x45;
}
for(int x34=0; x34 < x32; x34++) {
int32_t x38 = x34 * 2;
int32_t x39 = x38 + 1;
bool x40 = x39 > x6;
int32_t x42;
if (x40) {
x42 = x41;
} else {
x42 = x38;
}
for(int x36=0; x36 < 2; x36++) {
int32_t x51 = x50 + x36;
int32_t x52 = x51 + x21;
int32_t x53 = x2 * x52;
int32_t x94 = x7 * x51;
for(int x37=0; x37 < 2; x37++) {
int32_t x43 = x42 + x37;
int32_t x44 = x43 + 1;
int32_t x54 = x44 + x53;
int32_t x55 = 3 * x54;
int32_t x56 = x55 + 2;
UCHAR x57 = x0[x56];
int32_t x69 = (int) x57;
int32_t x61 = x44 + 1;
int32_t x62 = x61 + x53;
int32_t x63 = 3 * x62;
int32_t x64 = x63 + 2;
UCHAR x65 = x0[x64];
int32_t x72 = (int) x65;
int32_t x75 = x69 + x72;
int32_t x78 = x43 + x53;
int32_t x79 = 3 * x78;
int32_t x80 = x79 + 2;
UCHAR x81 = x0[x80];
int32_t x85 = (int) x81;
int32_t x88 = x75 + x85;
int32_t x91 = x88 / 3;
int32_t x95 = x43 + x94;
int32_t x96 = 3 * x95;
int32_t x97 = x96 + 2;
x170[x97] = x91;
int32_t x58 = x55 + 1;
UCHAR x59 = x0[x58];
int32_t x70 = (int) x59;
int32_t x66 = x63 + 1;
UCHAR x67 = x0[x66];
int32_t x73 = (int) x67;
int32_t x76 = x70 + x73;
int32_t x82 = x79 + 1;
UCHAR x83 = x0[x82];
int32_t x86 = (int) x83;
int32_t x89 = x76 + x86;
int32_t x92 = x89 / 3;
int32_t x99 = x96 + 1;
x170[x99] = x92;
UCHAR x60 = x0[x55];
int32_t x71 = (int) x60;
UCHAR x68 = x0[x63];
int32_t x74 = (int) x68;
int32_t x77 = x71 + x74;
UCHAR x84 = x0[x79];
int32_t x87 = (int) x84;
int32_t x90 = x77 + x87;
int32_t x93 = x90 / 3;
x170[x96] = x93;

}

}

}

}
int32_t x113 = x17 - x21;
int32_t x114 = x7 * x113;
int32_t x122 = x22 - x21;
int32_t x123 = x7 * x122;
int32_t x134 = x17 - 1;
int32_t x135 = x134 - x21;
int32_t x136 = x7 * x135;
int32_t x153 = x7 * x134;
for(int x111=1; x111 < x4; x111++) {
int32_t x112 = x111 - 1;
int32_t x115 = x112 + x114;
int32_t x116 = 3 * x115;
int32_t x117 = x116 + 2;
int32_t x182 = x170[x117];
int32_t x119 = x116 + 1;
int32_t x183 = x170[x119];
int32_t x184 = x170[x116];
int32_t x124 = x112 + x123;
int32_t x125 = 3 * x124;
int32_t x126 = x125 + 2;
int32_t x185 = x170[x126];
int32_t x128 = x125 + 1;
int32_t x186 = x170[x128];
int32_t x187 = x170[x125];
int32_t x137 = x112 + x136;
int32_t x138 = 3 * x137;
int32_t x139 = x138 + 2;
int32_t x188 = x170[x139];
int32_t x141 = x138 + 1;
int32_t x189 = x170[x141];
int32_t x190 = x170[x138];
int32_t x154 = x112 + x153;
int32_t x155 = 3 * x154;
int32_t x156 = x155 + 2;
int32_t x191 = x182 + x185;
int32_t x192 = x191 + x188;
int32_t x193 = x192 / 3;
UCHAR x194 = x193;
x169[x156] = x194;
int32_t x158 = x155 + 1;
int32_t x196 = x183 + x186;
int32_t x197 = x196 + x189;
int32_t x198 = x197 / 3;
UCHAR x199 = x198;
x169[x158] = x199;
int32_t x201 = x184 + x187;
int32_t x202 = x201 + x190;
int32_t x203 = x202 / 3;
UCHAR x204 = x203;
x169[x155] = x204;

}
free(x170);

}
memcpy(x1, x169, x14);
free(x169);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
