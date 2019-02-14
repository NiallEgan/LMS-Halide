#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR *x140 = malloc(sizeof(UCHAR) * x9);
int32_t x13 = x6 * 3;
int32_t x14 = x13 * 3;
for(int x12=1; x12 < x5; x12++) {
int32_t *x141 = malloc(sizeof(int32_t) * x14);
int32_t x16 = x12 + -1;
int32_t x19 = x6 * x16;
int32_t x20 = 1 + x19;
int32_t x17 = x12 + 1;
int32_t x21 = x17 * x6;
int32_t x22 = x4 + x21;
for(int x24=x20; x24 < x22; x24++) {
int32_t x25 = x24 - 1;
int32_t x26 = x25 % x6;
int32_t x27 = x26 + 1;
int32_t x28 = x25 / x6;
int32_t x29 = x2 * x28;
int32_t x30 = x27 + x29;
int32_t x31 = 3 * x30;
int32_t x32 = x31 + 2;
UCHAR x33 = x0[x32];
int32_t x45 = (int) x33;
int32_t x37 = x27 + 1;
int32_t x38 = x37 + x29;
int32_t x39 = 3 * x38;
int32_t x40 = x39 + 2;
UCHAR x41 = x0[x40];
int32_t x48 = (int) x41;
int32_t x51 = x45 + x48;
int32_t x54 = x26 + x29;
int32_t x55 = 3 * x54;
int32_t x56 = x55 + 2;
UCHAR x57 = x0[x56];
int32_t x61 = (int) x57;
int32_t x64 = x51 + x61;
int32_t x67 = x64 / 3;
int32_t x70 = x28 - x16;
int32_t x71 = x6 * x70;
int32_t x72 = x26 + x71;
int32_t x73 = 3 * x72;
int32_t x74 = x73 + 2;
x141[x74] = x67;
int32_t x34 = x31 + 1;
UCHAR x35 = x0[x34];
int32_t x46 = (int) x35;
int32_t x42 = x39 + 1;
UCHAR x43 = x0[x42];
int32_t x49 = (int) x43;
int32_t x52 = x46 + x49;
int32_t x58 = x55 + 1;
UCHAR x59 = x0[x58];
int32_t x62 = (int) x59;
int32_t x65 = x52 + x62;
int32_t x68 = x65 / 3;
int32_t x76 = x73 + 1;
x141[x76] = x68;
UCHAR x36 = x0[x31];
int32_t x47 = (int) x36;
UCHAR x44 = x0[x39];
int32_t x50 = (int) x44;
int32_t x53 = x47 + x50;
UCHAR x60 = x0[x55];
int32_t x63 = (int) x60;
int32_t x66 = x53 + x63;
int32_t x69 = x66 / 3;
x141[x73] = x69;

}
int32_t x84 = x12 - x16;
int32_t x85 = x6 * x84;
int32_t x93 = x17 - x16;
int32_t x94 = x6 * x93;
int32_t x105 = x12 - 1;
int32_t x106 = x105 - x16;
int32_t x107 = x6 * x106;
int32_t x124 = x6 * x105;
for(int x82=1; x82 < x4; x82++) {
int32_t x83 = x82 - 1;
int32_t x86 = x83 + x85;
int32_t x87 = 3 * x86;
int32_t x88 = x87 + 2;
int32_t x147 = x141[x88];
int32_t x90 = x87 + 1;
int32_t x148 = x141[x90];
int32_t x149 = x141[x87];
int32_t x95 = x83 + x94;
int32_t x96 = 3 * x95;
int32_t x97 = x96 + 2;
int32_t x150 = x141[x97];
int32_t x99 = x96 + 1;
int32_t x151 = x141[x99];
int32_t x152 = x141[x96];
int32_t x108 = x83 + x107;
int32_t x109 = 3 * x108;
int32_t x110 = x109 + 2;
int32_t x153 = x141[x110];
int32_t x112 = x109 + 1;
int32_t x154 = x141[x112];
int32_t x155 = x141[x109];
int32_t x125 = x83 + x124;
int32_t x126 = 3 * x125;
int32_t x127 = x126 + 2;
int32_t x156 = x147 + x150;
int32_t x157 = x156 + x153;
int32_t x158 = x157 / 3;
UCHAR x159 = x158;
x140[x127] = x159;
int32_t x129 = x126 + 1;
int32_t x161 = x148 + x151;
int32_t x162 = x161 + x154;
int32_t x163 = x162 / 3;
UCHAR x164 = x163;
x140[x129] = x164;
int32_t x166 = x149 + x152;
int32_t x167 = x166 + x155;
int32_t x168 = x167 / 3;
UCHAR x169 = x168;
x140[x126] = x169;

}
free(x141);

}
memcpy(x1, x140, x9);
free(x140);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
