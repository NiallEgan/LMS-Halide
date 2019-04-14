#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x8 = x4 - 1;
int32_t x9 = x8 * x3;
int32_t x10 = x9 * 3;
int32_t *x153 = malloc(sizeof(int32_t) * x10);
int32_t x5 = x3 - 1;
int32_t x12 = x5 - 1;
int32_t x13 = x8 * x12;
int32_t x14 = x13 * 3;
UCHAR *x154 = malloc(sizeof(UCHAR) * x14);
for(int x17=1; x17 < x5; x17++) {
int32_t x18 = x17 + -1;
int32_t x19 = x17 + 1;
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
bool x39 = x38 < 2;
bool x40 = x38 == x19;
bool x41 = x39 || x40;
int32_t x42 = x2 * x38;
int32_t x87 = x8 * x38;
for(int x30=1; x30 < x4; x30++) {
if (x41) {
int32_t x43 = x30 + x42;
int32_t x44 = 3 * x43;
int32_t x45 = x44 + 2;
UCHAR x46 = x0[x45];
uint16_t x58 = (uint16_t) x46;
int32_t x50 = x30 + 1;
int32_t x51 = x50 + x42;
int32_t x52 = 3 * x51;
int32_t x53 = x52 + 2;
UCHAR x54 = x0[x53];
uint16_t x61 = (uint16_t) x54;
uint16_t x64 = x58 + x61;
int32_t x67 = x30 - 1;
int32_t x68 = x67 + x42;
int32_t x69 = 3 * x68;
int32_t x70 = x69 + 2;
UCHAR x71 = x0[x70];
uint16_t x75 = (uint16_t) x71;
uint16_t x78 = x64 + x75;
int32_t x81 = (int) x78;
int32_t x84 = x81 / 3;
int32_t x88 = x67 + x87;
int32_t x89 = 3 * x88;
int32_t x90 = x89 + 2;
x153[x90] = x84;
int32_t x47 = x44 + 1;
UCHAR x48 = x0[x47];
uint16_t x59 = (uint16_t) x48;
int32_t x55 = x52 + 1;
UCHAR x56 = x0[x55];
uint16_t x62 = (uint16_t) x56;
uint16_t x65 = x59 + x62;
int32_t x72 = x69 + 1;
UCHAR x73 = x0[x72];
uint16_t x76 = (uint16_t) x73;
uint16_t x79 = x65 + x76;
int32_t x82 = (int) x79;
int32_t x85 = x82 / 3;
int32_t x92 = x89 + 1;
x153[x92] = x85;
UCHAR x49 = x0[x44];
uint16_t x60 = (uint16_t) x49;
UCHAR x57 = x0[x52];
uint16_t x63 = (uint16_t) x57;
uint16_t x66 = x60 + x63;
UCHAR x74 = x0[x69];
uint16_t x77 = (uint16_t) x74;
uint16_t x80 = x66 + x77;
int32_t x83 = (int) x80;
int32_t x86 = x83 / 3;
x153[x89] = x86;
} else {
}

}

}

}
int32_t x105 = x8 * x17;
int32_t x113 = x8 * x19;
int32_t x124 = x17 - 1;
int32_t x125 = x8 * x124;
for(int x103=1; x103 < x4; x103++) {
int32_t x104 = x103 - 1;
int32_t x106 = x104 + x105;
int32_t x107 = 3 * x106;
int32_t x108 = x107 + 2;
int32_t x166 = x153[x108];
int32_t x110 = x107 + 1;
int32_t x167 = x153[x110];
int32_t x168 = x153[x107];
int32_t x114 = x104 + x113;
int32_t x115 = 3 * x114;
int32_t x116 = x115 + 2;
int32_t x169 = x153[x116];
int32_t x118 = x115 + 1;
int32_t x170 = x153[x118];
int32_t x171 = x153[x115];
int32_t x126 = x104 + x125;
int32_t x127 = 3 * x126;
int32_t x128 = x127 + 2;
int32_t x172 = x153[x128];
int32_t x130 = x127 + 1;
int32_t x173 = x153[x130];
int32_t x174 = x153[x127];
int32_t x175 = x166 + x169;
int32_t x176 = x175 + x172;
int32_t x177 = x176 / 3;
UCHAR x178 = (UCHAR) x177;
x154[x128] = x178;
int32_t x180 = x167 + x170;
int32_t x181 = x180 + x173;
int32_t x182 = x181 / 3;
UCHAR x183 = (UCHAR) x182;
x154[x130] = x183;
int32_t x185 = x168 + x171;
int32_t x186 = x185 + x174;
int32_t x187 = x186 / 3;
UCHAR x188 = (UCHAR) x187;
x154[x127] = x188;

}

}
free(x153);
memcpy(x1, x154, x14);
free(x154);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
