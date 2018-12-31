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
int32_t x13 = x6 * 3;
int32_t x14 = x13 * 3;
for(int x12=1; x12 < x5; x12++) {
UCHAR x15[x14];
int32_t x18 = x12 + -1;
int32_t x19 = x12 + 1;
int32_t x20 = x19 + 1;
int32_t x91 = x12 - x18;
int32_t x92 = x6 * x91;
int32_t x93 = 3 * x92;
int32_t x94 = x93 + 2;
int32_t x97 = x93 + 1;
int32_t x102 = x19 - x18;
int32_t x103 = x6 * x102;
int32_t x104 = 3 * x103;
int32_t x105 = x104 + 2;
int32_t x108 = x104 + 1;
int32_t x116 = x12 - 1;
int32_t x117 = x116 - x18;
int32_t x118 = x6 * x117;
int32_t x119 = 3 * x118;
int32_t x120 = x119 + 2;
int32_t x123 = x119 + 1;
int32_t x134 = x6 * x116;
for(int x17=1; x17 < x4; x17++) {
int32_t x23 = x17 + 1;
int32_t x27 = x17 - 1;
for(int x22=x18; x22 < x20; x22++) {
int32_t x73 = x22 - x18;
int32_t x74 = x6 * x73;
int32_t x30 = x2 * x22;
for(int x25=x17; x25 < x23; x25++) {
bool x26 = x25 < 1;
bool x28 = x25 == x27;
bool x29 = x26 || x28;
if (x29) {
int32_t x72 = x25 - x17;
int32_t x75 = x72 + x74;
int32_t x76 = 3 * x75;
int32_t x77 = x76 + 2;
int32_t x31 = x25 + x30;
int32_t x32 = 3 * x31;
int32_t x33 = x32 + 2;
UCHAR x34 = x0[x33];
int32_t x35 = (int) x34;
int32_t x41 = x25 + 1;
int32_t x42 = x41 + x30;
int32_t x43 = 3 * x42;
int32_t x44 = x43 + 2;
UCHAR x45 = x0[x44];
int32_t x46 = (int) x45;
int32_t x52 = x35 + x46;
int32_t x55 = x25 - 1;
int32_t x56 = x55 + x30;
int32_t x57 = 3 * x56;
int32_t x58 = x57 + 2;
UCHAR x59 = x0[x58];
int32_t x60 = (int) x59;
int32_t x66 = x52 + x60;
int32_t x69 = x66 / 3;
UCHAR x78 = x69;
x15[x77] = x78;
int32_t x80 = x76 + 1;
int32_t x36 = x32 + 1;
UCHAR x37 = x0[x36];
int32_t x38 = (int) x37;
int32_t x47 = x43 + 1;
UCHAR x48 = x0[x47];
int32_t x49 = (int) x48;
int32_t x53 = x38 + x49;
int32_t x61 = x57 + 1;
UCHAR x62 = x0[x61];
int32_t x63 = (int) x62;
int32_t x67 = x53 + x63;
int32_t x70 = x67 / 3;
UCHAR x81 = x70;
x15[x80] = x81;
UCHAR x39 = x0[x32];
int32_t x40 = (int) x39;
UCHAR x50 = x0[x43];
int32_t x51 = (int) x50;
int32_t x54 = x40 + x51;
UCHAR x64 = x0[x57];
int32_t x65 = (int) x64;
int32_t x68 = x54 + x65;
int32_t x71 = x68 / 3;
UCHAR x83 = x71;
x15[x76] = x83;
} else {
}

}

}
UCHAR x95 = x15[x94];
UCHAR x98 = x15[x97];
UCHAR x100 = x15[x93];
UCHAR x106 = x15[x105];
UCHAR x109 = x15[x108];
UCHAR x111 = x15[x104];
UCHAR x121 = x15[x120];
UCHAR x124 = x15[x123];
UCHAR x126 = x15[x119];
int32_t x135 = x27 + x134;
int32_t x136 = 3 * x135;
int32_t x137 = x136 + 2;
int32_t x96 = (int) x95;
int32_t x107 = (int) x106;
int32_t x113 = x96 + x107;
int32_t x122 = (int) x121;
int32_t x128 = x113 + x122;
int32_t x131 = x128 / 3;
UCHAR x138 = x131;
x10[x137] = x138;
int32_t x140 = x136 + 1;
int32_t x99 = (int) x98;
int32_t x110 = (int) x109;
int32_t x114 = x99 + x110;
int32_t x125 = (int) x124;
int32_t x129 = x114 + x125;
int32_t x132 = x129 / 3;
UCHAR x141 = x132;
x10[x140] = x141;
int32_t x101 = (int) x100;
int32_t x112 = (int) x111;
int32_t x115 = x101 + x112;
int32_t x127 = (int) x126;
int32_t x130 = x115 + x127;
int32_t x133 = x130 / 3;
UCHAR x143 = x133;
x10[x136] = x143;

}

}
memcpy(x1, x10, x9);
}
#define WIDTH_OUT_DIFF (2)
#define HEIGHT_OUT_DIFF (2)
