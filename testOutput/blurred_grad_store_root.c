#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x8 = x2 * x3;
int32_t x9 = x8 * 3;
UCHAR *x10 = malloc(sizeof(UCHAR) * x9);
int32_t x4 = x2 - 1;
int32_t x5 = MIN(x4, x2);
int32_t x6 = x3 - 1;
int32_t x7 = MIN(x6, x3);
int32_t x11 = x5 * x7;
int32_t x12 = x11 * 3;
UCHAR *x13 = malloc(sizeof(UCHAR) * x12);
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
x10[x29] = x30;
int32_t x32 = x28 + 1;
x10[x32] = x30;
x10[x28] = x30;
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
UCHAR x47 = x10[x46];
int32_t x49 = x45 + 1;
UCHAR x50 = x10[x49];
UCHAR x52 = x10[x45];
int32_t x55 = x42 + x54;
int32_t x56 = 3 * x55;
int32_t x57 = x56 + 2;
UCHAR x58 = x10[x57];
int32_t x60 = x56 + 1;
UCHAR x61 = x10[x60];
UCHAR x63 = x10[x56];
int32_t x68 = x42 + 1;
int32_t x69 = x68 + x43;
int32_t x70 = 3 * x69;
int32_t x71 = x70 + 2;
UCHAR x72 = x10[x71];
int32_t x74 = x70 + 1;
UCHAR x75 = x10[x74];
UCHAR x77 = x10[x70];
int32_t x82 = x68 + x54;
int32_t x83 = 3 * x82;
int32_t x84 = x83 + 2;
UCHAR x85 = x10[x84];
int32_t x87 = x83 + 1;
UCHAR x88 = x10[x87];
UCHAR x90 = x10[x83];
int32_t x99 = x42 + x98;
int32_t x100 = 3 * x99;
int32_t x101 = x100 + 2;
int32_t x48 = (int) x47;
int32_t x59 = (int) x58;
int32_t x65 = x48 + x59;
int32_t x73 = (int) x72;
int32_t x79 = x65 + x73;
int32_t x86 = (int) x85;
int32_t x92 = x79 + x86;
int32_t x95 = x92 / 4;
UCHAR x102 = x95;
x13[x101] = x102;
int32_t x104 = x100 + 1;
int32_t x51 = (int) x50;
int32_t x62 = (int) x61;
int32_t x66 = x51 + x62;
int32_t x76 = (int) x75;
int32_t x80 = x66 + x76;
int32_t x89 = (int) x88;
int32_t x93 = x80 + x89;
int32_t x96 = x93 / 4;
UCHAR x105 = x96;
x13[x104] = x105;
int32_t x53 = (int) x52;
int32_t x64 = (int) x63;
int32_t x67 = x53 + x64;
int32_t x78 = (int) x77;
int32_t x81 = x67 + x78;
int32_t x91 = (int) x90;
int32_t x94 = x81 + x91;
int32_t x97 = x94 / 4;
UCHAR x107 = x97;
x13[x100] = x107;

}

}
free(x10);
memcpy(x1, x13, x12);
free(x13);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
