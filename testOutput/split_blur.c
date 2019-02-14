#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x8 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x9 = x5 - 1;
int32_t x10 = x8 * x9;
int32_t x11 = x10 * 3;
UCHAR *x153 = malloc(sizeof(UCHAR) * x11);
int32_t x15 = x8 * 3;
int32_t x16 = x15 * 3;
for(int x14=1; x14 < x5; x14++) {
int32_t *x154 = malloc(sizeof(int32_t) * x16);
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
int32_t x81 = x8 * x37;
for(int x30=1; x30 < x4; x30++) {
int32_t x40 = x30 + x39;
int32_t x41 = 3 * x40;
int32_t x42 = x41 + 2;
UCHAR x43 = x0[x42];
int32_t x55 = (int) x43;
int32_t x47 = x30 + 1;
int32_t x48 = x47 + x39;
int32_t x49 = 3 * x48;
int32_t x50 = x49 + 2;
UCHAR x51 = x0[x50];
int32_t x58 = (int) x51;
int32_t x61 = x55 + x58;
int32_t x64 = x30 - 1;
int32_t x65 = x64 + x39;
int32_t x66 = 3 * x65;
int32_t x67 = x66 + 2;
UCHAR x68 = x0[x67];
int32_t x72 = (int) x68;
int32_t x75 = x61 + x72;
int32_t x78 = x75 / 3;
int32_t x82 = x64 + x81;
int32_t x83 = 3 * x82;
int32_t x84 = x83 + 2;
x154[x84] = x78;
int32_t x44 = x41 + 1;
UCHAR x45 = x0[x44];
int32_t x56 = (int) x45;
int32_t x52 = x49 + 1;
UCHAR x53 = x0[x52];
int32_t x59 = (int) x53;
int32_t x62 = x56 + x59;
int32_t x69 = x66 + 1;
UCHAR x70 = x0[x69];
int32_t x73 = (int) x70;
int32_t x76 = x62 + x73;
int32_t x79 = x76 / 3;
int32_t x86 = x83 + 1;
x154[x86] = x79;
UCHAR x46 = x0[x41];
int32_t x57 = (int) x46;
UCHAR x54 = x0[x49];
int32_t x60 = (int) x54;
int32_t x63 = x57 + x60;
UCHAR x71 = x0[x66];
int32_t x74 = (int) x71;
int32_t x77 = x63 + x74;
int32_t x80 = x77 / 3;
x154[x83] = x80;

}

}

}
int32_t x97 = x14 - x18;
int32_t x98 = x8 * x97;
int32_t x106 = x19 - x18;
int32_t x107 = x8 * x106;
int32_t x118 = x14 - 1;
int32_t x119 = x118 - x18;
int32_t x120 = x8 * x119;
int32_t x137 = x8 * x118;
for(int x95=1; x95 < x4; x95++) {
int32_t x96 = x95 - 1;
int32_t x99 = x96 + x98;
int32_t x100 = 3 * x99;
int32_t x101 = x100 + 2;
int32_t x164 = x154[x101];
int32_t x103 = x100 + 1;
int32_t x165 = x154[x103];
int32_t x166 = x154[x100];
int32_t x108 = x96 + x107;
int32_t x109 = 3 * x108;
int32_t x110 = x109 + 2;
int32_t x167 = x154[x110];
int32_t x112 = x109 + 1;
int32_t x168 = x154[x112];
int32_t x169 = x154[x109];
int32_t x121 = x96 + x120;
int32_t x122 = 3 * x121;
int32_t x123 = x122 + 2;
int32_t x170 = x154[x123];
int32_t x125 = x122 + 1;
int32_t x171 = x154[x125];
int32_t x172 = x154[x122];
int32_t x138 = x96 + x137;
int32_t x139 = 3 * x138;
int32_t x140 = x139 + 2;
int32_t x173 = x164 + x167;
int32_t x174 = x173 + x170;
int32_t x175 = x174 / 3;
UCHAR x176 = x175;
x153[x140] = x176;
int32_t x142 = x139 + 1;
int32_t x178 = x165 + x168;
int32_t x179 = x178 + x171;
int32_t x180 = x179 / 3;
UCHAR x181 = x180;
x153[x142] = x181;
int32_t x183 = x166 + x169;
int32_t x184 = x183 + x172;
int32_t x185 = x184 / 3;
UCHAR x186 = x185;
x153[x139] = x186;

}
free(x154);

}
memcpy(x1, x153, x11);
free(x153);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
