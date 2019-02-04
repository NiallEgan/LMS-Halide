#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x8 = x2 * x3;
int32_t x9 = x8 * 3;
UCHAR *x117 = malloc(sizeof(UCHAR) * x9);
int32_t x4 = x2 - 1;
int32_t x5 = MIN(x4, x2);
int32_t x6 = x3 - 1;
int32_t x7 = MIN(x6, x3);
int32_t x11 = x5 * x7;
int32_t x12 = x11 * 3;
UCHAR *x118 = malloc(sizeof(UCHAR) * x12);
for(int x15=0; x15 < x7; x15++) {
int32_t x16 = x15 + 1;
int32_t x17 = x16 + 1;
for(int x19=x15; x19 < x17; x19++) {
bool x22 = x19 < 1;
bool x23 = x19 == x16;
bool x24 = x22 || x23;
int32_t x26 = x2 * x19;
for(int x21=0; x21 < x2; x21++) {
if (x24) {
int32_t x27 = x21 + x26;
int32_t x28 = 3 * x27;
int32_t x29 = x28 + 2;
int32_t x25 = x21 + x19;
UCHAR x30 = x25;
x117[x29] = x30;
int32_t x32 = x28 + 1;
x117[x32] = x30;
x117[x28] = x30;
} else {
}

}

}
int32_t x43 = x2 * x15;
int32_t x54 = x2 * x16;
int32_t x98 = x5 * x15;
for(int x42=0; x42 < x5; x42++) {
int32_t x44 = x42 + x43;
int32_t x45 = 3 * x44;
int32_t x46 = x45 + 2;
UCHAR x128 = x117[x46];
int32_t x49 = x45 + 1;
UCHAR x129 = x117[x49];
UCHAR x130 = x117[x45];
int32_t x55 = x42 + x54;
int32_t x56 = 3 * x55;
int32_t x57 = x56 + 2;
UCHAR x131 = x117[x57];
int32_t x60 = x56 + 1;
UCHAR x132 = x117[x60];
UCHAR x133 = x117[x56];
int32_t x68 = x42 + 1;
int32_t x69 = x68 + x43;
int32_t x70 = 3 * x69;
int32_t x71 = x70 + 2;
UCHAR x134 = x117[x71];
int32_t x74 = x70 + 1;
UCHAR x135 = x117[x74];
UCHAR x136 = x117[x70];
int32_t x82 = x68 + x54;
int32_t x83 = 3 * x82;
int32_t x84 = x83 + 2;
UCHAR x137 = x117[x84];
int32_t x87 = x83 + 1;
UCHAR x138 = x117[x87];
UCHAR x139 = x117[x83];
int32_t x99 = x42 + x98;
int32_t x100 = 3 * x99;
int32_t x101 = x100 + 2;
int32_t x140 = (int) x128;
int32_t x141 = (int) x131;
int32_t x142 = x140 + x141;
int32_t x143 = (int) x134;
int32_t x144 = x142 + x143;
int32_t x145 = (int) x137;
int32_t x146 = x144 + x145;
int32_t x147 = x146 / 4;
UCHAR x148 = x147;
x118[x101] = x148;
int32_t x104 = x100 + 1;
int32_t x150 = (int) x129;
int32_t x151 = (int) x132;
int32_t x152 = x150 + x151;
int32_t x153 = (int) x135;
int32_t x154 = x152 + x153;
int32_t x155 = (int) x138;
int32_t x156 = x154 + x155;
int32_t x157 = x156 / 4;
UCHAR x158 = x157;
x118[x104] = x158;
int32_t x160 = (int) x130;
int32_t x161 = (int) x133;
int32_t x162 = x160 + x161;
int32_t x163 = (int) x136;
int32_t x164 = x162 + x163;
int32_t x165 = (int) x139;
int32_t x166 = x164 + x165;
int32_t x167 = x166 / 4;
UCHAR x168 = x167;
x118[x100] = x168;

}

}
free(x117);
memcpy(x1, x118, x12);
free(x118);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
