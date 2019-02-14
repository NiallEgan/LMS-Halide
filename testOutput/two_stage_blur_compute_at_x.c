#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR *x130 = malloc(sizeof(UCHAR) * x9);
for(int x12=1; x12 < x5; x12++) {
int32_t x16 = x12 + -1;
int32_t x17 = x12 + 1;
int32_t x18 = x17 + 1;
int32_t x79 = x12 - x16;
int32_t x80 = 3 * x79;
int32_t x81 = x80 + 2;
int32_t x83 = x80 + 1;
int32_t x86 = x17 - x16;
int32_t x87 = 3 * x86;
int32_t x88 = x87 + 2;
int32_t x90 = x87 + 1;
int32_t x96 = x12 - 1;
int32_t x97 = x96 - x16;
int32_t x98 = 3 * x97;
int32_t x99 = x98 + 2;
int32_t x101 = x98 + 1;
int32_t x114 = x6 * x96;
for(int x14=1; x14 < x4; x14++) {
int32_t *x131 = malloc(sizeof(int32_t) * 9);
int32_t x21 = x14 + 1;
for(int x20=x16; x20 < x18; x20++) {
int32_t x24 = x2 * x20;
int32_t x67 = x20 - x16;
for(int x23=x14; x23 < x21; x23++) {
int32_t x25 = x23 + x24;
int32_t x26 = 3 * x25;
int32_t x27 = x26 + 2;
UCHAR x28 = x0[x27];
int32_t x40 = (int) x28;
int32_t x32 = x23 + 1;
int32_t x33 = x32 + x24;
int32_t x34 = 3 * x33;
int32_t x35 = x34 + 2;
UCHAR x36 = x0[x35];
int32_t x43 = (int) x36;
int32_t x46 = x40 + x43;
int32_t x49 = x23 - 1;
int32_t x50 = x49 + x24;
int32_t x51 = 3 * x50;
int32_t x52 = x51 + 2;
UCHAR x53 = x0[x52];
int32_t x57 = (int) x53;
int32_t x60 = x46 + x57;
int32_t x63 = x60 / 3;
int32_t x66 = x23 - x14;
int32_t x68 = x66 + x67;
int32_t x69 = 3 * x68;
int32_t x70 = x69 + 2;
x131[x70] = x63;
int32_t x29 = x26 + 1;
UCHAR x30 = x0[x29];
int32_t x41 = (int) x30;
int32_t x37 = x34 + 1;
UCHAR x38 = x0[x37];
int32_t x44 = (int) x38;
int32_t x47 = x41 + x44;
int32_t x54 = x51 + 1;
UCHAR x55 = x0[x54];
int32_t x58 = (int) x55;
int32_t x61 = x47 + x58;
int32_t x64 = x61 / 3;
int32_t x72 = x69 + 1;
x131[x72] = x64;
UCHAR x31 = x0[x26];
int32_t x42 = (int) x31;
UCHAR x39 = x0[x34];
int32_t x45 = (int) x39;
int32_t x48 = x42 + x45;
UCHAR x56 = x0[x51];
int32_t x59 = (int) x56;
int32_t x62 = x48 + x59;
int32_t x65 = x62 / 3;
x131[x69] = x65;

}

}
int32_t x139 = x131[x81];
int32_t x140 = x131[x83];
int32_t x141 = x131[x80];
int32_t x142 = x131[x88];
int32_t x143 = x131[x90];
int32_t x144 = x131[x87];
int32_t x145 = x131[x99];
int32_t x146 = x131[x101];
int32_t x147 = x131[x98];
int32_t x113 = x14 - 1;
int32_t x115 = x113 + x114;
int32_t x116 = 3 * x115;
int32_t x117 = x116 + 2;
int32_t x148 = x139 + x142;
int32_t x149 = x148 + x145;
int32_t x150 = x149 / 3;
UCHAR x151 = x150;
x130[x117] = x151;
int32_t x119 = x116 + 1;
int32_t x153 = x140 + x143;
int32_t x154 = x153 + x146;
int32_t x155 = x154 / 3;
UCHAR x156 = x155;
x130[x119] = x156;
int32_t x158 = x141 + x144;
int32_t x159 = x158 + x147;
int32_t x160 = x159 / 3;
UCHAR x161 = x160;
x130[x116] = x161;
free(x131);

}

}
memcpy(x1, x130, x9);
free(x130);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
