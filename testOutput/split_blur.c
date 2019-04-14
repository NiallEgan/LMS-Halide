#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x8 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x9 = x5 - 1;
int32_t x10 = x8 * x9;
int32_t x11 = x10 * 3;
UCHAR *x156 = malloc(sizeof(UCHAR) * x11);
int32_t x15 = x8 * 3;
int32_t x16 = x15 * 3;
for(int x14=1; x14 < x5; x14++) {
int32_t *x157 = malloc(sizeof(int32_t) * x16);
int32_t x18 = x14 + -1;
int32_t x19 = x14 + 1;
int32_t x20 = x19 + 1;
int32_t x21 = x20 - x18;
int32_t x22 = x21 + 2;
int32_t x23 = x22 - 1;
int32_t x24 = x23 / 2;
int32_t x33 = x20 - 2;
int32_t x35 = x33 - x18;
for(int x26=0; x26 < x24; x26++) {
int32_t x31 = x26 * 2;
int32_t x32 = x31 + x18;
bool x34 = x32 > x33;
int32_t x36;
if (x34) {
x36 = x35;
} else {
x36 = x31;
}
for(int x28=0; x28 < 2; x28++) {
int32_t x37 = x36 + x28;
int32_t x38 = x37 + x18;
int32_t x39 = x2 * x38;
int32_t x84 = x8 * x37;
for(int x30=1; x30 < x4; x30++) {
int32_t x40 = x30 + x39;
int32_t x41 = 3 * x40;
int32_t x42 = x41 + 2;
UCHAR x43 = x0[x42];
uint16_t x55 = (uint16_t) x43;
int32_t x47 = x30 + 1;
int32_t x48 = x47 + x39;
int32_t x49 = 3 * x48;
int32_t x50 = x49 + 2;
UCHAR x51 = x0[x50];
uint16_t x58 = (uint16_t) x51;
uint16_t x61 = x55 + x58;
int32_t x64 = x30 - 1;
int32_t x65 = x64 + x39;
int32_t x66 = 3 * x65;
int32_t x67 = x66 + 2;
UCHAR x68 = x0[x67];
uint16_t x72 = (uint16_t) x68;
uint16_t x75 = x61 + x72;
int32_t x78 = (int) x75;
int32_t x81 = x78 / 3;
int32_t x85 = x64 + x84;
int32_t x86 = 3 * x85;
int32_t x87 = x86 + 2;
x157[x87] = x81;
int32_t x44 = x41 + 1;
UCHAR x45 = x0[x44];
uint16_t x56 = (uint16_t) x45;
int32_t x52 = x49 + 1;
UCHAR x53 = x0[x52];
uint16_t x59 = (uint16_t) x53;
uint16_t x62 = x56 + x59;
int32_t x69 = x66 + 1;
UCHAR x70 = x0[x69];
uint16_t x73 = (uint16_t) x70;
uint16_t x76 = x62 + x73;
int32_t x79 = (int) x76;
int32_t x82 = x79 / 3;
int32_t x89 = x86 + 1;
x157[x89] = x82;
UCHAR x46 = x0[x41];
uint16_t x57 = (uint16_t) x46;
UCHAR x54 = x0[x49];
uint16_t x60 = (uint16_t) x54;
uint16_t x63 = x57 + x60;
UCHAR x71 = x0[x66];
uint16_t x74 = (uint16_t) x71;
uint16_t x77 = x63 + x74;
int32_t x80 = (int) x77;
int32_t x83 = x80 / 3;
x157[x86] = x83;

}

}

}
int32_t x100 = x14 - x18;
int32_t x101 = x8 * x100;
int32_t x109 = x19 - x18;
int32_t x110 = x8 * x109;
int32_t x121 = x14 - 1;
int32_t x122 = x121 - x18;
int32_t x123 = x8 * x122;
int32_t x140 = x8 * x121;
for(int x98=1; x98 < x4; x98++) {
int32_t x99 = x98 - 1;
int32_t x102 = x99 + x101;
int32_t x103 = 3 * x102;
int32_t x104 = x103 + 2;
int32_t x167 = x157[x104];
int32_t x106 = x103 + 1;
int32_t x168 = x157[x106];
int32_t x169 = x157[x103];
int32_t x111 = x99 + x110;
int32_t x112 = 3 * x111;
int32_t x113 = x112 + 2;
int32_t x170 = x157[x113];
int32_t x115 = x112 + 1;
int32_t x171 = x157[x115];
int32_t x172 = x157[x112];
int32_t x124 = x99 + x123;
int32_t x125 = 3 * x124;
int32_t x126 = x125 + 2;
int32_t x173 = x157[x126];
int32_t x128 = x125 + 1;
int32_t x174 = x157[x128];
int32_t x175 = x157[x125];
int32_t x141 = x99 + x140;
int32_t x142 = 3 * x141;
int32_t x143 = x142 + 2;
int32_t x176 = x167 + x170;
int32_t x177 = x176 + x173;
int32_t x178 = x177 / 3;
UCHAR x179 = (UCHAR) x178;
x156[x143] = x179;
int32_t x145 = x142 + 1;
int32_t x181 = x168 + x171;
int32_t x182 = x181 + x174;
int32_t x183 = x182 / 3;
UCHAR x184 = (UCHAR) x183;
x156[x145] = x184;
int32_t x186 = x169 + x172;
int32_t x187 = x186 + x175;
int32_t x188 = x187 / 3;
UCHAR x189 = (UCHAR) x188;
x156[x142] = x189;

}
free(x157);

}
memcpy(x1, x156, x11);
free(x156);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
