#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x8 = x4 - 1;
int32_t x9 = x8 * x3;
int32_t x10 = x9 * 3;
int32_t *x150 = malloc(sizeof(int32_t) * x10);
int32_t x5 = x3 - 1;
int32_t x12 = x5 - 1;
int32_t x13 = x8 * x12;
int32_t x14 = x13 * 3;
UCHAR *x151 = malloc(sizeof(UCHAR) * x14);
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
int32_t x84 = x8 * x38;
for(int x30=1; x30 < x4; x30++) {
if (x41) {
int32_t x43 = x30 + x42;
int32_t x44 = 3 * x43;
int32_t x45 = x44 + 2;
UCHAR x46 = x0[x45];
int32_t x58 = (int) x46;
int32_t x50 = x30 + 1;
int32_t x51 = x50 + x42;
int32_t x52 = 3 * x51;
int32_t x53 = x52 + 2;
UCHAR x54 = x0[x53];
int32_t x61 = (int) x54;
int32_t x64 = x58 + x61;
int32_t x67 = x30 - 1;
int32_t x68 = x67 + x42;
int32_t x69 = 3 * x68;
int32_t x70 = x69 + 2;
UCHAR x71 = x0[x70];
int32_t x75 = (int) x71;
int32_t x78 = x64 + x75;
int32_t x81 = x78 / 3;
int32_t x85 = x67 + x84;
int32_t x86 = 3 * x85;
int32_t x87 = x86 + 2;
x150[x87] = x81;
int32_t x47 = x44 + 1;
UCHAR x48 = x0[x47];
int32_t x59 = (int) x48;
int32_t x55 = x52 + 1;
UCHAR x56 = x0[x55];
int32_t x62 = (int) x56;
int32_t x65 = x59 + x62;
int32_t x72 = x69 + 1;
UCHAR x73 = x0[x72];
int32_t x76 = (int) x73;
int32_t x79 = x65 + x76;
int32_t x82 = x79 / 3;
int32_t x89 = x86 + 1;
x150[x89] = x82;
UCHAR x49 = x0[x44];
int32_t x60 = (int) x49;
UCHAR x57 = x0[x52];
int32_t x63 = (int) x57;
int32_t x66 = x60 + x63;
UCHAR x74 = x0[x69];
int32_t x77 = (int) x74;
int32_t x80 = x66 + x77;
int32_t x83 = x80 / 3;
x150[x86] = x83;
} else {
}

}

}

}
int32_t x102 = x8 * x17;
int32_t x110 = x8 * x19;
int32_t x121 = x17 - 1;
int32_t x122 = x8 * x121;
for(int x100=1; x100 < x4; x100++) {
int32_t x101 = x100 - 1;
int32_t x103 = x101 + x102;
int32_t x104 = 3 * x103;
int32_t x105 = x104 + 2;
int32_t x163 = x150[x105];
int32_t x107 = x104 + 1;
int32_t x164 = x150[x107];
int32_t x165 = x150[x104];
int32_t x111 = x101 + x110;
int32_t x112 = 3 * x111;
int32_t x113 = x112 + 2;
int32_t x166 = x150[x113];
int32_t x115 = x112 + 1;
int32_t x167 = x150[x115];
int32_t x168 = x150[x112];
int32_t x123 = x101 + x122;
int32_t x124 = 3 * x123;
int32_t x125 = x124 + 2;
int32_t x169 = x150[x125];
int32_t x127 = x124 + 1;
int32_t x170 = x150[x127];
int32_t x171 = x150[x124];
int32_t x172 = x163 + x166;
int32_t x173 = x172 + x169;
int32_t x174 = x173 / 3;
UCHAR x175 = x174;
x151[x125] = x175;
int32_t x177 = x164 + x167;
int32_t x178 = x177 + x170;
int32_t x179 = x178 / 3;
UCHAR x180 = x179;
x151[x127] = x180;
int32_t x182 = x165 + x168;
int32_t x183 = x182 + x171;
int32_t x184 = x183 / 3;
UCHAR x185 = x184;
x151[x124] = x185;

}

}
free(x150);
memcpy(x1, x151, x14);
free(x151);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
