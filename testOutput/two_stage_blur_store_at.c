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
int32_t x85 = x12 - x18;
int32_t x86 = x6 * x85;
int32_t x87 = 3 * x86;
int32_t x88 = x87 + 2;
int32_t x91 = x87 + 1;
int32_t x96 = x19 - x18;
int32_t x97 = x6 * x96;
int32_t x98 = 3 * x97;
int32_t x99 = x98 + 2;
int32_t x102 = x98 + 1;
int32_t x110 = x12 - 1;
int32_t x111 = x110 - x18;
int32_t x112 = x6 * x111;
int32_t x113 = 3 * x112;
int32_t x114 = x113 + 2;
int32_t x117 = x113 + 1;
int32_t x129 = x6 * x110;
for(int x17=1; x17 < x4; x17++) {
int32_t x23 = x17 + 1;
for(int x22=x18; x22 < x20; x22++) {
int32_t x69 = x22 - x18;
int32_t x70 = x6 * x69;
int32_t x26 = x2 * x22;
for(int x25=x17; x25 < x23; x25++) {
int32_t x68 = x25 - x17;
int32_t x71 = x68 + x70;
int32_t x72 = 3 * x71;
int32_t x73 = x72 + 2;
int32_t x27 = x25 + x26;
int32_t x28 = 3 * x27;
int32_t x29 = x28 + 2;
UCHAR x30 = x0[x29];
int32_t x31 = (int) x30;
int32_t x37 = x25 + 1;
int32_t x38 = x37 + x26;
int32_t x39 = 3 * x38;
int32_t x40 = x39 + 2;
UCHAR x41 = x0[x40];
int32_t x42 = (int) x41;
int32_t x48 = x31 + x42;
int32_t x51 = x25 - 1;
int32_t x52 = x51 + x26;
int32_t x53 = 3 * x52;
int32_t x54 = x53 + 2;
UCHAR x55 = x0[x54];
int32_t x56 = (int) x55;
int32_t x62 = x48 + x56;
int32_t x65 = x62 / 3;
UCHAR x74 = x65;
x15[x73] = x74;
int32_t x76 = x72 + 1;
int32_t x32 = x28 + 1;
UCHAR x33 = x0[x32];
int32_t x34 = (int) x33;
int32_t x43 = x39 + 1;
UCHAR x44 = x0[x43];
int32_t x45 = (int) x44;
int32_t x49 = x34 + x45;
int32_t x57 = x53 + 1;
UCHAR x58 = x0[x57];
int32_t x59 = (int) x58;
int32_t x63 = x49 + x59;
int32_t x66 = x63 / 3;
UCHAR x77 = x66;
x15[x76] = x77;
UCHAR x35 = x0[x28];
int32_t x36 = (int) x35;
UCHAR x46 = x0[x39];
int32_t x47 = (int) x46;
int32_t x50 = x36 + x47;
UCHAR x60 = x0[x53];
int32_t x61 = (int) x60;
int32_t x64 = x50 + x61;
int32_t x67 = x64 / 3;
UCHAR x79 = x67;
x15[x72] = x79;

}

}
UCHAR x89 = x15[x88];
UCHAR x92 = x15[x91];
UCHAR x94 = x15[x87];
UCHAR x100 = x15[x99];
UCHAR x103 = x15[x102];
UCHAR x105 = x15[x98];
UCHAR x115 = x15[x114];
UCHAR x118 = x15[x117];
UCHAR x120 = x15[x113];
int32_t x128 = x17 - 1;
int32_t x130 = x128 + x129;
int32_t x131 = 3 * x130;
int32_t x132 = x131 + 2;
int32_t x90 = (int) x89;
int32_t x101 = (int) x100;
int32_t x107 = x90 + x101;
int32_t x116 = (int) x115;
int32_t x122 = x107 + x116;
int32_t x125 = x122 / 3;
UCHAR x133 = x125;
x10[x132] = x133;
int32_t x135 = x131 + 1;
int32_t x93 = (int) x92;
int32_t x104 = (int) x103;
int32_t x108 = x93 + x104;
int32_t x119 = (int) x118;
int32_t x123 = x108 + x119;
int32_t x126 = x123 / 3;
UCHAR x136 = x126;
x10[x135] = x136;
int32_t x95 = (int) x94;
int32_t x106 = (int) x105;
int32_t x109 = x95 + x106;
int32_t x121 = (int) x120;
int32_t x124 = x109 + x121;
int32_t x127 = x124 / 3;
UCHAR x138 = x127;
x10[x131] = x138;

}

}
memcpy(x1, x10, x9);
}
#define WIDTH_OUT_DIFF (2)
#define HEIGHT_OUT_DIFF (2)
