#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x5 = x2 - 1;
int32_t x6 = x5 - 1;
int32_t x4 = x3 - 1;
int32_t x7 = x4 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR *x135 = malloc(sizeof(UCHAR) * x9);
int32_t x13 = x2 * 3;
for(int x12=1; x12 < x4; x12++) {
int32_t *x136 = malloc(sizeof(int32_t) * x13);
int32_t x17 = x12 + 1;
int32_t x118 = x12 - 1;
int32_t x119 = x6 * x118;
for(int x16=1; x16 < x5; x16++) {
int32_t x20 = x16 + -1;
int32_t x21 = x16 + 1;
int32_t x22 = x21 + 1;
for(int x19=x12; x19 < x17; x19++) {
int32_t x28 = x19 - 1;
int32_t x29 = x2 * x28;
int32_t x37 = x2 * x19;
int32_t x54 = x19 + 1;
int32_t x55 = x2 * x54;
int32_t x72 = x19 - x12;
int32_t x73 = x2 * x72;
for(int x24=x20; x24 < x22; x24++) {
bool x25 = x24 < 2;
bool x26 = x24 == x21;
bool x27 = x25 || x26;
if (x27) {
int32_t x30 = x24 + x29;
int32_t x31 = 3 * x30;
int32_t x32 = x31 + 2;
UCHAR x33 = x0[x32];
int32_t x45 = (int) x33;
int32_t x38 = x24 + x37;
int32_t x39 = 3 * x38;
int32_t x40 = x39 + 2;
UCHAR x41 = x0[x40];
int32_t x48 = (int) x41;
int32_t x51 = x45 + x48;
int32_t x56 = x24 + x55;
int32_t x57 = 3 * x56;
int32_t x58 = x57 + 2;
UCHAR x59 = x0[x58];
int32_t x63 = (int) x59;
int32_t x66 = x51 + x63;
int32_t x69 = x66 / 3;
int32_t x74 = x24 + x73;
int32_t x75 = 3 * x74;
int32_t x76 = x75 + 2;
x136[x76] = x69;
int32_t x34 = x31 + 1;
UCHAR x35 = x0[x34];
int32_t x46 = (int) x35;
int32_t x42 = x39 + 1;
UCHAR x43 = x0[x42];
int32_t x49 = (int) x43;
int32_t x52 = x46 + x49;
int32_t x60 = x57 + 1;
UCHAR x61 = x0[x60];
int32_t x64 = (int) x61;
int32_t x67 = x52 + x64;
int32_t x70 = x67 / 3;
int32_t x78 = x75 + 1;
x136[x78] = x70;
UCHAR x36 = x0[x31];
int32_t x47 = (int) x36;
UCHAR x44 = x0[x39];
int32_t x50 = (int) x44;
int32_t x53 = x47 + x50;
UCHAR x62 = x0[x57];
int32_t x65 = (int) x62;
int32_t x68 = x53 + x65;
int32_t x71 = x68 / 3;
x136[x75] = x71;
} else {
}

}

}
int32_t x87 = x16 - 1;
int32_t x88 = 3 * x87;
int32_t x89 = x88 + 2;
int32_t x146 = x136[x89];
int32_t x91 = x88 + 1;
int32_t x147 = x136[x91];
int32_t x148 = x136[x88];
int32_t x94 = 3 * x16;
int32_t x95 = x94 + 2;
int32_t x149 = x136[x95];
int32_t x97 = x94 + 1;
int32_t x150 = x136[x97];
int32_t x151 = x136[x94];
int32_t x103 = 3 * x21;
int32_t x104 = x103 + 2;
int32_t x152 = x136[x104];
int32_t x106 = x103 + 1;
int32_t x153 = x136[x106];
int32_t x154 = x136[x103];
int32_t x120 = x87 + x119;
int32_t x121 = 3 * x120;
int32_t x122 = x121 + 2;
int32_t x155 = x146 + x149;
int32_t x156 = x155 + x152;
int32_t x157 = x156 / 3;
UCHAR x158 = x157;
x135[x122] = x158;
int32_t x124 = x121 + 1;
int32_t x160 = x147 + x150;
int32_t x161 = x160 + x153;
int32_t x162 = x161 / 3;
UCHAR x163 = x162;
x135[x124] = x163;
int32_t x165 = x148 + x151;
int32_t x166 = x165 + x154;
int32_t x167 = x166 / 3;
UCHAR x168 = x167;
x135[x121] = x168;

}
free(x136);

}
memcpy(x1, x135, x9);
free(x135);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
