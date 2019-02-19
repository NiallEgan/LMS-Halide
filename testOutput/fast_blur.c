#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x7 = x4 - 1;
int32_t x12 = x7 * x3;
int32_t x13 = x12 * 3;
int32_t *x169 = malloc(sizeof(int32_t) * x13);
int32_t x5 = x3 - 1;
int32_t x15 = x5 - 1;
int32_t x16 = x7 * x15;
int32_t x17 = x16 * 3;
UCHAR *x170 = malloc(sizeof(UCHAR) * x17);
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
int32_t x100 = x7 * x45;
for(int x37=0; x37 < 2; x37++) {
if (x48) {
int32_t x54 = x53 + x37;
int32_t x55 = x54 + 1;
int32_t x57 = x55 + x56;
int32_t x58 = 3 * x57;
int32_t x59 = x58 + 2;
UCHAR x60 = x0[x59];
uint16_t x72 = (uint16_t) x60;
int32_t x64 = x55 + 1;
int32_t x65 = x64 + x56;
int32_t x66 = 3 * x65;
int32_t x67 = x66 + 2;
UCHAR x68 = x0[x67];
uint16_t x75 = (uint16_t) x68;
uint16_t x78 = x72 + x75;
int32_t x81 = x54 + x56;
int32_t x82 = 3 * x81;
int32_t x83 = x82 + 2;
UCHAR x84 = x0[x83];
uint16_t x88 = (uint16_t) x84;
uint16_t x91 = x78 + x88;
int32_t x94 = (int) x91;
int32_t x97 = x94 / 3;
int32_t x101 = x54 + x100;
int32_t x102 = 3 * x101;
int32_t x103 = x102 + 2;
x169[x103] = x97;
int32_t x61 = x58 + 1;
UCHAR x62 = x0[x61];
uint16_t x73 = (uint16_t) x62;
int32_t x69 = x66 + 1;
UCHAR x70 = x0[x69];
uint16_t x76 = (uint16_t) x70;
uint16_t x79 = x73 + x76;
int32_t x85 = x82 + 1;
UCHAR x86 = x0[x85];
uint16_t x89 = (uint16_t) x86;
uint16_t x92 = x79 + x89;
int32_t x95 = (int) x92;
int32_t x98 = x95 / 3;
int32_t x105 = x102 + 1;
x169[x105] = x98;
UCHAR x63 = x0[x58];
uint16_t x74 = (uint16_t) x63;
UCHAR x71 = x0[x66];
uint16_t x77 = (uint16_t) x71;
uint16_t x80 = x74 + x77;
UCHAR x87 = x0[x82];
uint16_t x90 = (uint16_t) x87;
uint16_t x93 = x80 + x90;
int32_t x96 = (int) x93;
int32_t x99 = x96 / 3;
x169[x102] = x99;
} else {
}

}

}

}

}
int32_t x121 = x7 * x20;
int32_t x129 = x7 * x22;
int32_t x140 = x20 - 1;
int32_t x141 = x7 * x140;
for(int x119=1; x119 < x4; x119++) {
int32_t x120 = x119 - 1;
int32_t x122 = x120 + x121;
int32_t x123 = 3 * x122;
int32_t x124 = x123 + 2;
int32_t x184 = x169[x124];
int32_t x126 = x123 + 1;
int32_t x185 = x169[x126];
int32_t x186 = x169[x123];
int32_t x130 = x120 + x129;
int32_t x131 = 3 * x130;
int32_t x132 = x131 + 2;
int32_t x187 = x169[x132];
int32_t x134 = x131 + 1;
int32_t x188 = x169[x134];
int32_t x189 = x169[x131];
int32_t x142 = x120 + x141;
int32_t x143 = 3 * x142;
int32_t x144 = x143 + 2;
int32_t x190 = x169[x144];
int32_t x146 = x143 + 1;
int32_t x191 = x169[x146];
int32_t x192 = x169[x143];
int32_t x193 = x184 + x187;
int32_t x194 = x193 + x190;
int32_t x195 = x194 / 3;
UCHAR x196 = (UCHAR) x195;
x170[x144] = x196;
int32_t x198 = x185 + x188;
int32_t x199 = x198 + x191;
int32_t x200 = x199 / 3;
UCHAR x201 = (UCHAR) x200;
x170[x146] = x201;
int32_t x203 = x186 + x189;
int32_t x204 = x203 + x192;
int32_t x205 = x204 / 3;
UCHAR x206 = (UCHAR) x205;
x170[x143] = x206;

}

}
free(x169);
memcpy(x1, x170, x17);
free(x170);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
