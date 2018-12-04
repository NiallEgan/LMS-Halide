/*****************************************
  Emitting C Generated Code                  
*******************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
void pipeline(UCHAR[] * x0, UCHAR[] * x3, int32_t  x1, int32_t  x2) {
int32_t x4 = x1 - 1;
int32_t x5 = x2 - 1;
int32_t x6 = x4 * x5;
int32_t x7 = x6 * 3;
UCHAR x8[x7];
int32_t x11 = x1 * 2;
int32_t x12 = x11 * 3;
for(int x10=0; x10 < x5; x10++) {
UCHAR x13[x12];
int32_t x14 = x10 + 1;
int32_t x15 = x14 + 1;
for(int x17=x10; x17 < x15; x17++) {
int32_t x21 = x17 - x10;
int32_t x22 = x1 * x21;
for(int x19=0; x19 < x1; x19++) {
int32_t x23 = x19 + x22;
int32_t x24 = 3 * x23;
int32_t x25 = x24 + 2;
int32_t x20 = x19 + x17;
UCHAR x26 = x20;
x13[x25] = x26;
int32_t x28 = x24 + 1;
x13[x28] = x26;
x13[x24] = x26;

}

}
int32_t x47 = x14 - x10;
int32_t x48 = x1 * x47;
int32_t x90 = x4 * x10;
for(int x36=0; x36 < x4; x36++) {
int32_t x37 = x36 + 1;
int32_t x38 = 3 * x37;
int32_t x39 = x38 + 2;
UCHAR x40 = x13[x39];
int32_t x42 = x38 + 1;
UCHAR x43 = x13[x42];
UCHAR x45 = x13[x38];
int32_t x49 = x36 + x48;
int32_t x50 = 3 * x49;
int32_t x51 = x50 + 2;
UCHAR x52 = x13[x51];
int32_t x54 = x50 + 1;
UCHAR x55 = x13[x54];
UCHAR x57 = x13[x50];
int32_t x62 = x37 + x48;
int32_t x63 = 3 * x62;
int32_t x64 = x63 + 2;
UCHAR x65 = x13[x64];
int32_t x67 = x63 + 1;
UCHAR x68 = x13[x67];
UCHAR x70 = x13[x63];
int32_t x75 = 3 * x36;
int32_t x76 = x75 + 2;
UCHAR x77 = x13[x76];
int32_t x79 = x75 + 1;
UCHAR x80 = x13[x79];
UCHAR x82 = x13[x75];
int32_t x91 = x36 + x90;
int32_t x92 = 3 * x91;
int32_t x93 = x92 + 2;
int32_t x41 = (int) x40;
int32_t x53 = (int) x52;
int32_t x59 = x41 + x53;
int32_t x66 = (int) x65;
int32_t x72 = x59 + x66;
int32_t x78 = (int) x77;
int32_t x84 = x72 + x78;
int32_t x87 = x84 / 4;
UCHAR x94 = x87;
x8[x93] = x94;
int32_t x96 = x92 + 1;
int32_t x44 = (int) x43;
int32_t x56 = (int) x55;
int32_t x60 = x44 + x56;
int32_t x69 = (int) x68;
int32_t x73 = x60 + x69;
int32_t x81 = (int) x80;
int32_t x85 = x73 + x81;
int32_t x88 = x85 / 4;
UCHAR x97 = x88;
x8[x96] = x97;
int32_t x46 = (int) x45;
int32_t x58 = (int) x57;
int32_t x61 = x46 + x58;
int32_t x71 = (int) x70;
int32_t x74 = x61 + x71;
int32_t x83 = (int) x82;
int32_t x86 = x74 + x83;
int32_t x89 = x86 / 4;
UCHAR x99 = x89;
x8[x92] = x99;

}

}
for(int x105=0; x105 < x5; x105++) {
int32_t x107 = x4 * x105;
for(int x106=0; x106 < x4; x106++) {
int32_t x108 = x106 + x107;
int32_t x109 = 3 * x108;
int32_t x110 = x109 + 2;
UCHAR x111 = x8[x110];
int32_t x113 = x109 + 1;
UCHAR x114 = x8[x113];
UCHAR x116 = x8[x109];
int32_t x112 = (int) x111;
UCHAR x118 = x112;
x3[x110] = x118;
int32_t x115 = (int) x114;
UCHAR x120 = x115;
x3[x113] = x120;
int32_t x117 = (int) x116;
UCHAR x122 = x117;
x3[x109] = x122;

}

}
}
/*****************************************
  End of C Generated Code                  
*******************************************/
