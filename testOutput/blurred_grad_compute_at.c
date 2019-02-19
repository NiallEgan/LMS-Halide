#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x5 = MIN(x4, x2);
int32_t x8 = x5 - 1;
int32_t x6 = x3 - 1;
int32_t x7 = MIN(x6, x3);
int32_t x9 = x7 - 1;
int32_t x10 = x8 * x9;
int32_t x11 = x10 * 3;
UCHAR *x159 = malloc(sizeof(UCHAR) * x11);
int32_t x15 = x2 * 3;
int32_t x16 = x15 * 3;
for(int x14=1; x14 < x7; x14++) {
int32_t *x160 = malloc(sizeof(int32_t) * x16);
int32_t x18 = x14 + -1;
int32_t x19 = x14 + 1;
int32_t x20 = x19 + 1;
for(int x22=x18; x22 < x20; x22++) {
int32_t x26 = x22 - x18;
int32_t x27 = x2 * x26;
for(int x24=0; x24 < x2; x24++) {
int32_t x25 = x24 + x22;
int32_t x28 = x24 + x27;
int32_t x29 = 3 * x28;
int32_t x30 = x29 + 2;
x160[x30] = x25;
int32_t x32 = x29 + 1;
x160[x32] = x25;
x160[x29] = x25;

}

}
int32_t x42 = x14 - 1;
int32_t x43 = x42 - x18;
int32_t x44 = x2 * x43;
int32_t x52 = x14 - x18;
int32_t x53 = x2 * x52;
int32_t x64 = x19 - x18;
int32_t x65 = x2 * x64;
int32_t x143 = x8 * x42;
for(int x40=1; x40 < x5; x40++) {
int32_t x41 = x40 + 1;
int32_t x45 = x41 + x44;
int32_t x46 = 3 * x45;
int32_t x47 = x46 + 2;
int32_t x168 = x160[x47];
int32_t x49 = x46 + 1;
int32_t x169 = x160[x49];
int32_t x170 = x160[x46];
int32_t x54 = x41 + x53;
int32_t x55 = 3 * x54;
int32_t x56 = x55 + 2;
int32_t x171 = x160[x56];
int32_t x58 = x55 + 1;
int32_t x172 = x160[x58];
int32_t x173 = x160[x55];
int32_t x66 = x41 + x65;
int32_t x67 = 3 * x66;
int32_t x68 = x67 + 2;
int32_t x174 = x160[x68];
int32_t x70 = x67 + 1;
int32_t x175 = x160[x70];
int32_t x176 = x160[x67];
int32_t x76 = x40 + x44;
int32_t x77 = 3 * x76;
int32_t x78 = x77 + 2;
int32_t x177 = x160[x78];
int32_t x80 = x77 + 1;
int32_t x178 = x160[x80];
int32_t x179 = x160[x77];
int32_t x86 = x40 + x53;
int32_t x87 = 3 * x86;
int32_t x88 = x87 + 2;
int32_t x180 = x160[x88];
int32_t x90 = x87 + 1;
int32_t x181 = x160[x90];
int32_t x182 = x160[x87];
int32_t x96 = x40 + x65;
int32_t x97 = 3 * x96;
int32_t x98 = x97 + 2;
int32_t x183 = x160[x98];
int32_t x100 = x97 + 1;
int32_t x184 = x160[x100];
int32_t x185 = x160[x97];
int32_t x106 = x40 - 1;
int32_t x107 = x106 + x44;
int32_t x108 = 3 * x107;
int32_t x109 = x108 + 2;
int32_t x186 = x160[x109];
int32_t x111 = x108 + 1;
int32_t x187 = x160[x111];
int32_t x188 = x160[x108];
int32_t x117 = x106 + x53;
int32_t x118 = 3 * x117;
int32_t x119 = x118 + 2;
int32_t x189 = x160[x119];
int32_t x121 = x118 + 1;
int32_t x190 = x160[x121];
int32_t x191 = x160[x118];
int32_t x127 = x106 + x65;
int32_t x128 = 3 * x127;
int32_t x129 = x128 + 2;
int32_t x192 = x160[x129];
int32_t x131 = x128 + 1;
int32_t x193 = x160[x131];
int32_t x194 = x160[x128];
int32_t x144 = x106 + x143;
int32_t x145 = 3 * x144;
int32_t x146 = x145 + 2;
int32_t x195 = x168 + x171;
int32_t x196 = x195 + x174;
int32_t x197 = x196 + x177;
int32_t x198 = x197 + x180;
int32_t x199 = x198 + x183;
int32_t x200 = x199 + x186;
int32_t x201 = x200 + x189;
int32_t x202 = x201 + x192;
int32_t x203 = x202 / 9;
UCHAR x204 = (UCHAR) x203;
x159[x146] = x204;
int32_t x148 = x145 + 1;
int32_t x206 = x169 + x172;
int32_t x207 = x206 + x175;
int32_t x208 = x207 + x178;
int32_t x209 = x208 + x181;
int32_t x210 = x209 + x184;
int32_t x211 = x210 + x187;
int32_t x212 = x211 + x190;
int32_t x213 = x212 + x193;
int32_t x214 = x213 / 9;
UCHAR x215 = (UCHAR) x214;
x159[x148] = x215;
int32_t x217 = x170 + x173;
int32_t x218 = x217 + x176;
int32_t x219 = x218 + x179;
int32_t x220 = x219 + x182;
int32_t x221 = x220 + x185;
int32_t x222 = x221 + x188;
int32_t x223 = x222 + x191;
int32_t x224 = x223 + x194;
int32_t x225 = x224 / 9;
UCHAR x226 = (UCHAR) x225;
x159[x145] = x226;

}
free(x160);

}
memcpy(x1, x159, x11);
free(x159);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
