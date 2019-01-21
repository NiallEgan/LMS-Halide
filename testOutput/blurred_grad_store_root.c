#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x6 = x2 * x3;
int32_t x7 = x6 * 3;
UCHAR x8[x7];
int32_t x4 = x2 - 1;
int32_t x5 = x3 - 1;
int32_t x9 = x4 * x5;
int32_t x10 = x9 * 3;
UCHAR x11[x10];
for(int x13=0; x13 < x5; x13++) {
int32_t x14 = x13 + 1;
int32_t x15 = x14 + 1;
for(int x17=x13; x17 < x15; x17++) {
bool x20 = x17 < 1;
bool x21 = x17 == x14;
bool x22 = x20 || x21;
int32_t x24 = x2 * x17;
for(int x19=0; x19 < x2; x19++) {
if (x22) {
int32_t x25 = x19 + x24;
int32_t x26 = 3 * x25;
int32_t x27 = x26 + 2;
int32_t x23 = x19 + x17;
UCHAR x28 = x23;
x8[x27] = x28;
int32_t x30 = x26 + 1;
x8[x30] = x28;
x8[x26] = x28;
} else {
}

}

}
int32_t x41 = x2 * x13;
int32_t x52 = x2 * x14;
int32_t x96 = x4 * x13;
for(int x40=0; x40 < x4; x40++) {
int32_t x42 = x40 + x41;
int32_t x43 = 3 * x42;
int32_t x44 = x43 + 2;
UCHAR x45 = x8[x44];
int32_t x47 = x43 + 1;
UCHAR x48 = x8[x47];
UCHAR x50 = x8[x43];
int32_t x53 = x40 + x52;
int32_t x54 = 3 * x53;
int32_t x55 = x54 + 2;
UCHAR x56 = x8[x55];
int32_t x58 = x54 + 1;
UCHAR x59 = x8[x58];
UCHAR x61 = x8[x54];
int32_t x66 = x40 + 1;
int32_t x67 = x66 + x41;
int32_t x68 = 3 * x67;
int32_t x69 = x68 + 2;
UCHAR x70 = x8[x69];
int32_t x72 = x68 + 1;
UCHAR x73 = x8[x72];
UCHAR x75 = x8[x68];
int32_t x80 = x66 + x52;
int32_t x81 = 3 * x80;
int32_t x82 = x81 + 2;
UCHAR x83 = x8[x82];
int32_t x85 = x81 + 1;
UCHAR x86 = x8[x85];
UCHAR x88 = x8[x81];
int32_t x97 = x40 + x96;
int32_t x98 = 3 * x97;
int32_t x99 = x98 + 2;
int32_t x46 = (int) x45;
int32_t x57 = (int) x56;
int32_t x63 = x46 + x57;
int32_t x71 = (int) x70;
int32_t x77 = x63 + x71;
int32_t x84 = (int) x83;
int32_t x90 = x77 + x84;
int32_t x93 = x90 / 4;
UCHAR x100 = x93;
x11[x99] = x100;
int32_t x102 = x98 + 1;
int32_t x49 = (int) x48;
int32_t x60 = (int) x59;
int32_t x64 = x49 + x60;
int32_t x74 = (int) x73;
int32_t x78 = x64 + x74;
int32_t x87 = (int) x86;
int32_t x91 = x78 + x87;
int32_t x94 = x91 / 4;
UCHAR x103 = x94;
x11[x102] = x103;
int32_t x51 = (int) x50;
int32_t x62 = (int) x61;
int32_t x65 = x51 + x62;
int32_t x76 = (int) x75;
int32_t x79 = x65 + x76;
int32_t x89 = (int) x88;
int32_t x92 = x79 + x89;
int32_t x95 = x92 / 4;
UCHAR x105 = x95;
x11[x98] = x105;

}

}
memcpy(x1, x11, x10);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
