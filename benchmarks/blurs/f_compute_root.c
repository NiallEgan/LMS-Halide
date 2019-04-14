#include <string.h>
#include "../../testOutput/pipeline.h"
// f.compute_root()
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x7 = x6 * x3;
int32_t x8 = x7 * 3;
uint16_t *x125 = malloc(sizeof(uint16_t) * x8);
for(int x11=0; x11 < x3; x11++) {
int32_t x14 = x2 * x11;
int32_t x56 = x6 * x11;
for(int x13=1; x13 < x4; x13++) {
int32_t x15 = x13 + x14;
int32_t x16 = 3 * x15;
int32_t x17 = x16 + 2;
UCHAR x18 = x0[x17];
uint16_t x30 = (uint16_t) x18;
int32_t x22 = x13 + 1;
int32_t x23 = x22 + x14;
int32_t x24 = 3 * x23;
int32_t x25 = x24 + 2;
UCHAR x26 = x0[x25];
uint16_t x33 = (uint16_t) x26;
uint16_t x36 = x30 + x33;
int32_t x39 = x13 - 1;
int32_t x40 = x39 + x14;
int32_t x41 = 3 * x40;
int32_t x42 = x41 + 2;
UCHAR x43 = x0[x42];
uint16_t x47 = (uint16_t) x43;
uint16_t x50 = x36 + x47;
uint16_t x53 = x50 / 3;
int32_t x57 = x39 + x56;
int32_t x58 = 3 * x57;
int32_t x59 = x58 + 2;
x125[x59] = x53;
int32_t x19 = x16 + 1;
UCHAR x20 = x0[x19];
uint16_t x31 = (uint16_t) x20;
int32_t x27 = x24 + 1;
UCHAR x28 = x0[x27];
uint16_t x34 = (uint16_t) x28;
uint16_t x37 = x31 + x34;
int32_t x44 = x41 + 1;
UCHAR x45 = x0[x44];
uint16_t x48 = (uint16_t) x45;
uint16_t x51 = x37 + x48;
uint16_t x54 = x51 / 3;
int32_t x61 = x58 + 1;
x125[x61] = x54;
UCHAR x21 = x0[x16];
uint16_t x32 = (uint16_t) x21;
UCHAR x29 = x0[x24];
uint16_t x35 = (uint16_t) x29;
uint16_t x38 = x32 + x35;
UCHAR x46 = x0[x41];
uint16_t x49 = (uint16_t) x46;
uint16_t x52 = x38 + x49;
uint16_t x55 = x52 / 3;
x125[x58] = x55;

}

}
int32_t x5 = x3 - 1;
int32_t x68 = x5 - 1;
int32_t x69 = x6 * x68;
int32_t x70 = x69 * 3;
UCHAR *x133 = malloc(sizeof(UCHAR) * x70);
for(int x73=1; x73 < x5; x73++) {
int32_t x76 = x6 * x73;
int32_t x84 = x73 + 1;
int32_t x85 = x6 * x84;
int32_t x96 = x73 - 1;
int32_t x97 = x6 * x96;
for(int x74=1; x74 < x4; x74++) {
int32_t x75 = x74 - 1;
int32_t x77 = x75 + x76;
int32_t x78 = 3 * x77;
int32_t x79 = x78 + 2;
uint16_t x134 = x125[x79];
int32_t x81 = x78 + 1;
uint16_t x135 = x125[x81];
uint16_t x136 = x125[x78];
int32_t x86 = x75 + x85;
int32_t x87 = 3 * x86;
int32_t x88 = x87 + 2;
uint16_t x137 = x125[x88];
int32_t x90 = x87 + 1;
uint16_t x138 = x125[x90];
uint16_t x139 = x125[x87];
int32_t x98 = x75 + x97;
int32_t x99 = 3 * x98;
int32_t x100 = x99 + 2;
uint16_t x140 = x125[x100];
int32_t x102 = x99 + 1;
uint16_t x141 = x125[x102];
uint16_t x142 = x125[x99];
uint16_t x143 = x134 + x137;
uint16_t x144 = x143 + x140;
uint16_t x145 = x144 / 3;
UCHAR x146 = (UCHAR) x145;
x133[x100] = x146;
uint16_t x148 = x135 + x138;
uint16_t x149 = x148 + x141;
uint16_t x150 = x149 / 3;
UCHAR x151 = (UCHAR) x150;
x133[x102] = x151;
uint16_t x153 = x136 + x139;
uint16_t x154 = x153 + x142;
uint16_t x155 = x154 / 3;
UCHAR x156 = (UCHAR) x155;
x133[x99] = x156;

}

}
free(x125);
memcpy(x1, x133, x70);
free(x133);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
