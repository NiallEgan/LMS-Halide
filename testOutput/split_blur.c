#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x13 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x14 = x5 - 1;
int32_t x15 = x13 * x14;
int32_t x16 = x15 * 3;
UCHAR *x159 = malloc(sizeof(UCHAR) * x16);
int32_t x20 = x13 * 3;
int32_t x21 = x20 * 3;
for(int x19=1; x19 < x5; x19++) {
int32_t *x160 = malloc(sizeof(int32_t) * x21);
int32_t x23 = x19 + -1;
int32_t x25 = x23 / 2;
int32_t x24 = x19 + 1;
int32_t x27 = x24 % 2;
bool x28 = x27 == 0;
int32_t x31;
if (x28) {
int32_t x29 = x24 / 2;
x31 = x29;
} else {
int32_t x29 = x24 / 2;
int32_t x30 = x29 + 1;
x31 = x30;
}
int32_t x32 = x31 + 1;
int32_t x26 = x24 + 1;
int32_t x40 = x26 - 2;
for(int x34=x25; x34 < x32; x34++) {
int32_t x39 = x34 * 2;
bool x41 = x39 > x40;
int32_t x42;
if (x41) {
x42 = x40;
} else {
x42 = x39;
}
for(int x36=0; x36 < 2; x36++) {
int32_t x43 = x42 + x36;
int32_t x44 = x2 * x43;
int32_t x86 = x43 - x23;
int32_t x87 = x13 * x86;
for(int x38=1; x38 < x4; x38++) {
int32_t x45 = x38 + x44;
int32_t x46 = 3 * x45;
int32_t x47 = x46 + 2;
UCHAR x48 = x0[x47];
int32_t x60 = (int) x48;
int32_t x52 = x38 + 1;
int32_t x53 = x52 + x44;
int32_t x54 = 3 * x53;
int32_t x55 = x54 + 2;
UCHAR x56 = x0[x55];
int32_t x63 = (int) x56;
int32_t x66 = x60 + x63;
int32_t x69 = x38 - 1;
int32_t x70 = x69 + x44;
int32_t x71 = 3 * x70;
int32_t x72 = x71 + 2;
UCHAR x73 = x0[x72];
int32_t x77 = (int) x73;
int32_t x80 = x66 + x77;
int32_t x83 = x80 / 3;
int32_t x88 = x69 + x87;
int32_t x89 = 3 * x88;
int32_t x90 = x89 + 2;
x160[x90] = x83;
int32_t x49 = x46 + 1;
UCHAR x50 = x0[x49];
int32_t x61 = (int) x50;
int32_t x57 = x54 + 1;
UCHAR x58 = x0[x57];
int32_t x64 = (int) x58;
int32_t x67 = x61 + x64;
int32_t x74 = x71 + 1;
UCHAR x75 = x0[x74];
int32_t x78 = (int) x75;
int32_t x81 = x67 + x78;
int32_t x84 = x81 / 3;
int32_t x92 = x89 + 1;
x160[x92] = x84;
UCHAR x51 = x0[x46];
int32_t x62 = (int) x51;
UCHAR x59 = x0[x54];
int32_t x65 = (int) x59;
int32_t x68 = x62 + x65;
UCHAR x76 = x0[x71];
int32_t x79 = (int) x76;
int32_t x82 = x68 + x79;
int32_t x85 = x82 / 3;
x160[x89] = x85;

}

}

}
int32_t x103 = x19 - x23;
int32_t x104 = x13 * x103;
int32_t x112 = x24 - x23;
int32_t x113 = x13 * x112;
int32_t x124 = x19 - 1;
int32_t x125 = x124 - x23;
int32_t x126 = x13 * x125;
int32_t x143 = x13 * x124;
for(int x101=1; x101 < x4; x101++) {
int32_t x102 = x101 - 1;
int32_t x105 = x102 + x104;
int32_t x106 = 3 * x105;
int32_t x107 = x106 + 2;
int32_t x170 = x160[x107];
int32_t x109 = x106 + 1;
int32_t x171 = x160[x109];
int32_t x172 = x160[x106];
int32_t x114 = x102 + x113;
int32_t x115 = 3 * x114;
int32_t x116 = x115 + 2;
int32_t x173 = x160[x116];
int32_t x118 = x115 + 1;
int32_t x174 = x160[x118];
int32_t x175 = x160[x115];
int32_t x127 = x102 + x126;
int32_t x128 = 3 * x127;
int32_t x129 = x128 + 2;
int32_t x176 = x160[x129];
int32_t x131 = x128 + 1;
int32_t x177 = x160[x131];
int32_t x178 = x160[x128];
int32_t x144 = x102 + x143;
int32_t x145 = 3 * x144;
int32_t x146 = x145 + 2;
int32_t x179 = x170 + x173;
int32_t x180 = x179 + x176;
int32_t x181 = x180 / 3;
UCHAR x182 = x181;
x159[x146] = x182;
int32_t x148 = x145 + 1;
int32_t x184 = x171 + x174;
int32_t x185 = x184 + x177;
int32_t x186 = x185 / 3;
UCHAR x187 = x186;
x159[x148] = x187;
int32_t x189 = x172 + x175;
int32_t x190 = x189 + x178;
int32_t x191 = x190 / 3;
UCHAR x192 = x191;
x159[x145] = x192;

}
free(x160);

}
memcpy(x1, x159, x16);
free(x159);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
