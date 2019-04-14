#include <string.h>
#include "../../testOutput/pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x7 = x6 * x3;
int32_t x8 = x7 * 3;
uint16_t *x132 = malloc(sizeof(uint16_t) * x8);
int32_t x5 = x3 - 1;
int32_t x10 = x5 - 1;
int32_t x11 = x6 * x10;
int32_t x12 = x11 * 3;
UCHAR *x133 = malloc(sizeof(UCHAR) * x12);
for(int x15=1; x15 < x5; x15++) {
int32_t x16 = x15 + -1;
int32_t x17 = x15 + 1;
int32_t x18 = x17 + 1;
for(int x20=x16; x20 < x18; x20++) {
bool x23 = x20 < 2;
bool x24 = x20 == x17;
bool x25 = x23 || x24;
int32_t x26 = x2 * x20;
int32_t x68 = x6 * x20;
for(int x22=1; x22 < x4; x22++) {
if (x25) {
int32_t x27 = x22 + x26;
int32_t x28 = 3 * x27;
int32_t x29 = x28 + 2;
UCHAR x30 = x0[x29];
uint16_t x42 = (uint16_t) x30;
int32_t x34 = x22 + 1;
int32_t x35 = x34 + x26;
int32_t x36 = 3 * x35;
int32_t x37 = x36 + 2;
UCHAR x38 = x0[x37];
uint16_t x45 = (uint16_t) x38;
uint16_t x48 = x42 + x45;
int32_t x51 = x22 - 1;
int32_t x52 = x51 + x26;
int32_t x53 = 3 * x52;
int32_t x54 = x53 + 2;
UCHAR x55 = x0[x54];
uint16_t x59 = (uint16_t) x55;
uint16_t x62 = x48 + x59;
uint16_t x65 = x62 / 3;
int32_t x69 = x51 + x68;
int32_t x70 = 3 * x69;
int32_t x71 = x70 + 2;
x132[x71] = x65;
int32_t x31 = x28 + 1;
UCHAR x32 = x0[x31];
uint16_t x43 = (uint16_t) x32;
int32_t x39 = x36 + 1;
UCHAR x40 = x0[x39];
uint16_t x46 = (uint16_t) x40;
uint16_t x49 = x43 + x46;
int32_t x56 = x53 + 1;
UCHAR x57 = x0[x56];
uint16_t x60 = (uint16_t) x57;
uint16_t x63 = x49 + x60;
uint16_t x66 = x63 / 3;
int32_t x73 = x70 + 1;
x132[x73] = x66;
UCHAR x33 = x0[x28];
uint16_t x44 = (uint16_t) x33;
UCHAR x41 = x0[x36];
uint16_t x47 = (uint16_t) x41;
uint16_t x50 = x44 + x47;
UCHAR x58 = x0[x53];
uint16_t x61 = (uint16_t) x58;
uint16_t x64 = x50 + x61;
uint16_t x67 = x64 / 3;
x132[x70] = x67;
} else {
}

}

}
int32_t x84 = x6 * x15;
int32_t x92 = x6 * x17;
int32_t x103 = x15 - 1;
int32_t x104 = x6 * x103;
for(int x82=1; x82 < x4; x82++) {
int32_t x83 = x82 - 1;
int32_t x85 = x83 + x84;
int32_t x86 = 3 * x85;
int32_t x87 = x86 + 2;
uint16_t x143 = x132[x87];
int32_t x89 = x86 + 1;
uint16_t x144 = x132[x89];
uint16_t x145 = x132[x86];
int32_t x93 = x83 + x92;
int32_t x94 = 3 * x93;
int32_t x95 = x94 + 2;
uint16_t x146 = x132[x95];
int32_t x97 = x94 + 1;
uint16_t x147 = x132[x97];
uint16_t x148 = x132[x94];
int32_t x105 = x83 + x104;
int32_t x106 = 3 * x105;
int32_t x107 = x106 + 2;
uint16_t x149 = x132[x107];
int32_t x109 = x106 + 1;
uint16_t x150 = x132[x109];
uint16_t x151 = x132[x106];
uint16_t x152 = x143 + x146;
uint16_t x153 = x152 + x149;
uint16_t x154 = x153 / 3;
UCHAR x155 = (UCHAR) x154;
x133[x107] = x155;
uint16_t x157 = x144 + x147;
uint16_t x158 = x157 + x150;
uint16_t x159 = x158 / 3;
UCHAR x160 = (UCHAR) x159;
x133[x109] = x160;
uint16_t x162 = x145 + x148;
uint16_t x163 = x162 + x151;
uint16_t x164 = x163 / 3;
UCHAR x165 = (UCHAR) x164;
x133[x106] = x165;

}

}
free(x132);
memcpy(x1, x133, x12);
free(x133);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
