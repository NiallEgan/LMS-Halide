#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x5 = x2 - 1;
int32_t x6 = x5 - 1;
int32_t x4 = x3 - 1;
int32_t x7 = x4 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR *x138 = malloc(sizeof(UCHAR) * x9);
int32_t x13 = x2 * 3;
for(int x12=1; x12 < x4; x12++) {
int32_t *x139 = malloc(sizeof(int32_t) * x13);
int32_t x17 = x12 + 1;
int32_t x121 = x12 - 1;
int32_t x122 = x6 * x121;
for(int x16=1; x16 < x5; x16++) {
int32_t x20 = x16 + -1;
int32_t x21 = x16 + 1;
int32_t x22 = x21 + 1;
for(int x19=x12; x19 < x17; x19++) {
int32_t x28 = x19 - 1;
int32_t x29 = x2 * x28;
int32_t x37 = x2 * x19;
int32_t x54 = x19 + 1;
int32_t x55 = x2 * x54;
int32_t x75 = x19 - x12;
int32_t x76 = x2 * x75;
for(int x24=x20; x24 < x22; x24++) {
bool x25 = x24 < 2;
bool x26 = x24 == x21;
bool x27 = x25 || x26;
if (x27) {
int32_t x30 = x24 + x29;
int32_t x31 = 3 * x30;
int32_t x32 = x31 + 2;
UCHAR x33 = x0[x32];
uint16_t x45 = (uint16_t) x33;
int32_t x38 = x24 + x37;
int32_t x39 = 3 * x38;
int32_t x40 = x39 + 2;
UCHAR x41 = x0[x40];
uint16_t x48 = (uint16_t) x41;
uint16_t x51 = x45 + x48;
int32_t x56 = x24 + x55;
int32_t x57 = 3 * x56;
int32_t x58 = x57 + 2;
UCHAR x59 = x0[x58];
uint16_t x63 = (uint16_t) x59;
uint16_t x66 = x51 + x63;
int32_t x69 = (int) x66;
int32_t x72 = x69 / 3;
int32_t x77 = x24 + x76;
int32_t x78 = 3 * x77;
int32_t x79 = x78 + 2;
x139[x79] = x72;
int32_t x34 = x31 + 1;
UCHAR x35 = x0[x34];
uint16_t x46 = (uint16_t) x35;
int32_t x42 = x39 + 1;
UCHAR x43 = x0[x42];
uint16_t x49 = (uint16_t) x43;
uint16_t x52 = x46 + x49;
int32_t x60 = x57 + 1;
UCHAR x61 = x0[x60];
uint16_t x64 = (uint16_t) x61;
uint16_t x67 = x52 + x64;
int32_t x70 = (int) x67;
int32_t x73 = x70 / 3;
int32_t x81 = x78 + 1;
x139[x81] = x73;
UCHAR x36 = x0[x31];
uint16_t x47 = (uint16_t) x36;
UCHAR x44 = x0[x39];
uint16_t x50 = (uint16_t) x44;
uint16_t x53 = x47 + x50;
UCHAR x62 = x0[x57];
uint16_t x65 = (uint16_t) x62;
uint16_t x68 = x53 + x65;
int32_t x71 = (int) x68;
int32_t x74 = x71 / 3;
x139[x78] = x74;
} else {
}

}

}
int32_t x90 = x16 - 1;
int32_t x91 = 3 * x90;
int32_t x92 = x91 + 2;
int32_t x149 = x139[x92];
int32_t x94 = x91 + 1;
int32_t x150 = x139[x94];
int32_t x151 = x139[x91];
int32_t x97 = 3 * x16;
int32_t x98 = x97 + 2;
int32_t x152 = x139[x98];
int32_t x100 = x97 + 1;
int32_t x153 = x139[x100];
int32_t x154 = x139[x97];
int32_t x106 = 3 * x21;
int32_t x107 = x106 + 2;
int32_t x155 = x139[x107];
int32_t x109 = x106 + 1;
int32_t x156 = x139[x109];
int32_t x157 = x139[x106];
int32_t x123 = x90 + x122;
int32_t x124 = 3 * x123;
int32_t x125 = x124 + 2;
int32_t x158 = x149 + x152;
int32_t x159 = x158 + x155;
int32_t x160 = x159 / 3;
UCHAR x161 = (UCHAR) x160;
x138[x125] = x161;
int32_t x127 = x124 + 1;
int32_t x163 = x150 + x153;
int32_t x164 = x163 + x156;
int32_t x165 = x164 / 3;
UCHAR x166 = (UCHAR) x165;
x138[x127] = x166;
int32_t x168 = x151 + x154;
int32_t x169 = x168 + x157;
int32_t x170 = x169 / 3;
UCHAR x171 = (UCHAR) x170;
x138[x124] = x171;

}
free(x139);

}
memcpy(x1, x138, x9);
free(x138);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
