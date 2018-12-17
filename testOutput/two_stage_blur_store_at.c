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
int32_t x13 = x4 * 3;
int32_t x14 = x13 * 3;
for(int x12=1; x12 < x5; x12++) {
UCHAR x15[x14];
int32_t x92 = x12 + 1;
int32_t x107 = x12 - 1;
int32_t x126 = x6 * x107;
for(int x17=1; x17 < x4; x17++) {
int32_t x18 = x17 + 1;
for(int x20=x17; x20 < x18; x20++) {
int32_t x65 = x20 - x17;
int32_t x66 = x4 * x65;
int32_t x22 = x2 * x20;
for(int x21=x17; x21 < x18; x21++) {
int32_t x64 = x21 - x17;
int32_t x67 = x64 + x66;
int32_t x68 = 3 * x67;
int32_t x69 = x68 + 2;
int32_t x23 = x21 + x22;
int32_t x24 = 3 * x23;
int32_t x25 = x24 + 2;
UCHAR x26 = x0[x25];
int32_t x27 = (int) x26;
int32_t x33 = x21 + 1;
int32_t x34 = x33 + x22;
int32_t x35 = 3 * x34;
int32_t x36 = x35 + 2;
UCHAR x37 = x0[x36];
int32_t x38 = (int) x37;
int32_t x44 = x27 + x38;
int32_t x47 = x21 - 1;
int32_t x48 = x47 + x22;
int32_t x49 = 3 * x48;
int32_t x50 = x49 + 2;
UCHAR x51 = x0[x50];
int32_t x52 = (int) x51;
int32_t x58 = x44 + x52;
int32_t x61 = x58 / 3;
UCHAR x70 = x61;
x15[x69] = x70;
int32_t x72 = x68 + 1;
int32_t x28 = x24 + 1;
UCHAR x29 = x0[x28];
int32_t x30 = (int) x29;
int32_t x39 = x35 + 1;
UCHAR x40 = x0[x39];
int32_t x41 = (int) x40;
int32_t x45 = x30 + x41;
int32_t x53 = x49 + 1;
UCHAR x54 = x0[x53];
int32_t x55 = (int) x54;
int32_t x59 = x45 + x55;
int32_t x62 = x59 / 3;
UCHAR x73 = x62;
x15[x72] = x73;
UCHAR x31 = x0[x24];
int32_t x32 = (int) x31;
UCHAR x42 = x0[x35];
int32_t x43 = (int) x42;
int32_t x46 = x32 + x43;
UCHAR x56 = x0[x49];
int32_t x57 = (int) x56;
int32_t x60 = x46 + x57;
int32_t x63 = x60 / 3;
UCHAR x75 = x63;
x15[x68] = x75;

}

}
int32_t x81 = x12 - x17;
int32_t x82 = x4 * x81;
int32_t x83 = 3 * x82;
int32_t x84 = x83 + 2;
UCHAR x85 = x15[x84];
int32_t x87 = x83 + 1;
UCHAR x88 = x15[x87];
UCHAR x90 = x15[x83];
int32_t x93 = x92 - x17;
int32_t x94 = x4 * x93;
int32_t x95 = 3 * x94;
int32_t x96 = x95 + 2;
UCHAR x97 = x15[x96];
int32_t x99 = x95 + 1;
UCHAR x100 = x15[x99];
UCHAR x102 = x15[x95];
int32_t x108 = x107 - x17;
int32_t x109 = x4 * x108;
int32_t x110 = 3 * x109;
int32_t x111 = x110 + 2;
UCHAR x112 = x15[x111];
int32_t x114 = x110 + 1;
UCHAR x115 = x15[x114];
UCHAR x117 = x15[x110];
int32_t x125 = x17 - 1;
int32_t x127 = x125 + x126;
int32_t x128 = 3 * x127;
int32_t x129 = x128 + 2;
int32_t x86 = (int) x85;
int32_t x98 = (int) x97;
int32_t x104 = x86 + x98;
int32_t x113 = (int) x112;
int32_t x119 = x104 + x113;
int32_t x122 = x119 / 3;
UCHAR x130 = x122;
x10[x129] = x130;
int32_t x132 = x128 + 1;
int32_t x89 = (int) x88;
int32_t x101 = (int) x100;
int32_t x105 = x89 + x101;
int32_t x116 = (int) x115;
int32_t x120 = x105 + x116;
int32_t x123 = x120 / 3;
UCHAR x133 = x123;
x10[x132] = x133;
int32_t x91 = (int) x90;
int32_t x103 = (int) x102;
int32_t x106 = x91 + x103;
int32_t x118 = (int) x117;
int32_t x121 = x106 + x118;
int32_t x124 = x121 / 3;
UCHAR x135 = x124;
x10[x128] = x135;

}

}
memcpy(x1, x10, x9);
}
#define WIDTH_OUT_DIFF (2)
#define HEIGHT_OUT_DIFF (2)
