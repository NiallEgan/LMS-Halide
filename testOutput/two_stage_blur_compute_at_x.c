#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR x10[x9];
int32_t x15 = x3 * 3;
for(int x12=1; x12 < x5; x12++) {
int32_t x17 = x12 + -1;
int32_t x18 = x12 + 1;
int32_t x19 = x18 + 1;
int32_t x83 = x12 - x17;
int32_t x84 = 3 * x83;
int32_t x85 = x84 + 2;
int32_t x88 = x84 + 1;
int32_t x93 = x18 - x17;
int32_t x94 = 3 * x93;
int32_t x95 = x94 + 2;
int32_t x98 = x94 + 1;
int32_t x106 = x12 - 1;
int32_t x107 = x106 - x17;
int32_t x108 = 3 * x107;
int32_t x109 = x108 + 2;
int32_t x112 = x108 + 1;
int32_t x124 = x6 * x106;
for(int x14=1; x14 < x4; x14++) {
UCHAR x16[x15];
int32_t x22 = x14 + 1;
for(int x21=x17; x21 < x19; x21++) {
int32_t x68 = x21 - x17;
int32_t x25 = x2 * x21;
for(int x24=x14; x24 < x22; x24++) {
int32_t x67 = x24 - x14;
int32_t x69 = x67 + x68;
int32_t x70 = 3 * x69;
int32_t x71 = x70 + 2;
int32_t x26 = x24 + x25;
int32_t x27 = 3 * x26;
int32_t x28 = x27 + 2;
UCHAR x29 = x0[x28];
int32_t x30 = (int) x29;
int32_t x36 = x24 + 1;
int32_t x37 = x36 + x25;
int32_t x38 = 3 * x37;
int32_t x39 = x38 + 2;
UCHAR x40 = x0[x39];
int32_t x41 = (int) x40;
int32_t x47 = x30 + x41;
int32_t x50 = x24 - 1;
int32_t x51 = x50 + x25;
int32_t x52 = 3 * x51;
int32_t x53 = x52 + 2;
UCHAR x54 = x0[x53];
int32_t x55 = (int) x54;
int32_t x61 = x47 + x55;
int32_t x64 = x61 / 3;
UCHAR x72 = x64;
x16[x71] = x72;
int32_t x74 = x70 + 1;
int32_t x31 = x27 + 1;
UCHAR x32 = x0[x31];
int32_t x33 = (int) x32;
int32_t x42 = x38 + 1;
UCHAR x43 = x0[x42];
int32_t x44 = (int) x43;
int32_t x48 = x33 + x44;
int32_t x56 = x52 + 1;
UCHAR x57 = x0[x56];
int32_t x58 = (int) x57;
int32_t x62 = x48 + x58;
int32_t x65 = x62 / 3;
UCHAR x75 = x65;
x16[x74] = x75;
UCHAR x34 = x0[x27];
int32_t x35 = (int) x34;
UCHAR x45 = x0[x38];
int32_t x46 = (int) x45;
int32_t x49 = x35 + x46;
UCHAR x59 = x0[x52];
int32_t x60 = (int) x59;
int32_t x63 = x49 + x60;
int32_t x66 = x63 / 3;
UCHAR x77 = x66;
x16[x70] = x77;

}

}
UCHAR x86 = x16[x85];
UCHAR x89 = x16[x88];
UCHAR x91 = x16[x84];
UCHAR x96 = x16[x95];
UCHAR x99 = x16[x98];
UCHAR x101 = x16[x94];
UCHAR x110 = x16[x109];
UCHAR x113 = x16[x112];
UCHAR x115 = x16[x108];
int32_t x123 = x14 - 1;
int32_t x125 = x123 + x124;
int32_t x126 = 3 * x125;
int32_t x127 = x126 + 2;
int32_t x87 = (int) x86;
int32_t x97 = (int) x96;
int32_t x103 = x87 + x97;
int32_t x111 = (int) x110;
int32_t x117 = x103 + x111;
int32_t x120 = x117 / 3;
UCHAR x128 = x120;
x10[x127] = x128;
int32_t x130 = x126 + 1;
int32_t x90 = (int) x89;
int32_t x100 = (int) x99;
int32_t x104 = x90 + x100;
int32_t x114 = (int) x113;
int32_t x118 = x104 + x114;
int32_t x121 = x118 / 3;
UCHAR x131 = x121;
x10[x130] = x131;
int32_t x92 = (int) x91;
int32_t x102 = (int) x101;
int32_t x105 = x92 + x102;
int32_t x116 = (int) x115;
int32_t x119 = x105 + x116;
int32_t x122 = x119 / 3;
UCHAR x133 = x122;
x10[x126] = x133;

}

}
memcpy(x1, x10, x9);
}
#define WIDTH_OUT_DIFF (2)
#define HEIGHT_OUT_DIFF (2)
