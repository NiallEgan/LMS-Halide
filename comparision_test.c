#include "testOutput/pipeline.h"
#include <assert.h>
#include <png.h>
#include <string.h>

void ref_pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR *x175 = malloc(sizeof(UCHAR) * x9);
for(int x12=1; x12 < x5; x12++) {
int32_t x15 = x2 * x12;
int32_t x60 = x12 + 1;
int32_t x61 = x2 * x60;
int32_t x107 = x12 - 1;
int32_t x108 = x2 * x107;
int32_t x160 = x6 * x107;
for(int x14=1; x14 < x4; x14++) {
int32_t x16 = x14 + x15;
int32_t x17 = 3 * x16;
int32_t x18 = x17 + 2;
UCHAR x19 = x0[x18];
uint16_t x31 = (uint16_t) x19;
int32_t x23 = x14 + 1;
int32_t x24 = x23 + x15;
int32_t x25 = 3 * x24;
int32_t x26 = x25 + 2;
UCHAR x27 = x0[x26];
uint16_t x34 = (uint16_t) x27;
uint16_t x37 = x31 + x34;
int32_t x40 = x14 - 1;
int32_t x41 = x40 + x15;
int32_t x42 = 3 * x41;
int32_t x43 = x42 + 2;
UCHAR x44 = x0[x43];
uint16_t x48 = (uint16_t) x44;
uint16_t x51 = x37 + x48;
int32_t x54 = (int) x51;
int32_t x57 = x54 / 3;
int32_t x62 = x14 + x61;
int32_t x63 = 3 * x62;
int32_t x64 = x63 + 2;
UCHAR x65 = x0[x64];
uint16_t x76 = (uint16_t) x65;
int32_t x69 = x23 + x61;
int32_t x70 = 3 * x69;
int32_t x71 = x70 + 2;
UCHAR x72 = x0[x71];
uint16_t x79 = (uint16_t) x72;
uint16_t x82 = x76 + x79;
int32_t x85 = x40 + x61;
int32_t x86 = 3 * x85;
int32_t x87 = x86 + 2;
UCHAR x88 = x0[x87];
uint16_t x92 = (uint16_t) x88;
uint16_t x95 = x82 + x92;
int32_t x98 = (int) x95;
int32_t x101 = x98 / 3;
int32_t x104 = x57 + x101;
int32_t x109 = x14 + x108;
int32_t x110 = 3 * x109;
int32_t x111 = x110 + 2;
UCHAR x112 = x0[x111];
uint16_t x123 = (uint16_t) x112;
int32_t x116 = x23 + x108;
int32_t x117 = 3 * x116;
int32_t x118 = x117 + 2;
UCHAR x119 = x0[x118];
uint16_t x126 = (uint16_t) x119;
uint16_t x129 = x123 + x126;
int32_t x132 = x40 + x108;
int32_t x133 = 3 * x132;
int32_t x134 = x133 + 2;
UCHAR x135 = x0[x134];
uint16_t x139 = (uint16_t) x135;
uint16_t x142 = x129 + x139;
int32_t x145 = (int) x142;
int32_t x148 = x145 / 3;
int32_t x151 = x104 + x148;
int32_t x154 = x151 / 3;
UCHAR x157 = (UCHAR) x154;
int32_t x161 = x40 + x160;
int32_t x162 = 3 * x161;
int32_t x163 = x162 + 2;
x175[x163] = x157;
int32_t x20 = x17 + 1;
UCHAR x21 = x0[x20];
uint16_t x32 = (uint16_t) x21;
int32_t x28 = x25 + 1;
UCHAR x29 = x0[x28];
uint16_t x35 = (uint16_t) x29;
uint16_t x38 = x32 + x35;
int32_t x45 = x42 + 1;
UCHAR x46 = x0[x45];
uint16_t x49 = (uint16_t) x46;
uint16_t x52 = x38 + x49;
int32_t x55 = (int) x52;
int32_t x58 = x55 / 3;
int32_t x66 = x63 + 1;
UCHAR x67 = x0[x66];
uint16_t x77 = (uint16_t) x67;
int32_t x73 = x70 + 1;
UCHAR x74 = x0[x73];
uint16_t x80 = (uint16_t) x74;
uint16_t x83 = x77 + x80;
int32_t x89 = x86 + 1;
UCHAR x90 = x0[x89];
uint16_t x93 = (uint16_t) x90;
uint16_t x96 = x83 + x93;
int32_t x99 = (int) x96;
int32_t x102 = x99 / 3;
int32_t x105 = x58 + x102;
int32_t x113 = x110 + 1;
UCHAR x114 = x0[x113];
uint16_t x124 = (uint16_t) x114;
int32_t x120 = x117 + 1;
UCHAR x121 = x0[x120];
uint16_t x127 = (uint16_t) x121;
uint16_t x130 = x124 + x127;
int32_t x136 = x133 + 1;
UCHAR x137 = x0[x136];
uint16_t x140 = (uint16_t) x137;
uint16_t x143 = x130 + x140;
int32_t x146 = (int) x143;
int32_t x149 = x146 / 3;
int32_t x152 = x105 + x149;
int32_t x155 = x152 / 3;
UCHAR x158 = (UCHAR) x155;
int32_t x165 = x162 + 1;
x175[x165] = x158;
UCHAR x22 = x0[x17];
uint16_t x33 = (uint16_t) x22;
UCHAR x30 = x0[x25];
uint16_t x36 = (uint16_t) x30;
uint16_t x39 = x33 + x36;
UCHAR x47 = x0[x42];
uint16_t x50 = (uint16_t) x47;
uint16_t x53 = x39 + x50;
int32_t x56 = (int) x53;
int32_t x59 = x56 / 3;
UCHAR x68 = x0[x63];
uint16_t x78 = (uint16_t) x68;
UCHAR x75 = x0[x70];
uint16_t x81 = (uint16_t) x75;
uint16_t x84 = x78 + x81;
UCHAR x91 = x0[x86];
uint16_t x94 = (uint16_t) x91;
uint16_t x97 = x84 + x94;
int32_t x100 = (int) x97;
int32_t x103 = x100 / 3;
int32_t x106 = x59 + x103;
UCHAR x115 = x0[x110];
uint16_t x125 = (uint16_t) x115;
UCHAR x122 = x0[x117];
uint16_t x128 = (uint16_t) x122;
uint16_t x131 = x125 + x128;
UCHAR x138 = x0[x133];
uint16_t x141 = (uint16_t) x138;
uint16_t x144 = x131 + x141;
int32_t x147 = (int) x144;
int32_t x150 = x147 / 3;
int32_t x153 = x106 + x150;
int32_t x156 = x153 / 3;
UCHAR x159 = (UCHAR) x156;
x175[x162] = x159;

}

}
memcpy(x1, x175, x9);
free(x175);
}


int main() {
  unsigned char *input = malloc(sizeof(unsigned char) * 350 * 607 * 3);
  unsigned char *ref_output = malloc(sizeof(unsigned char) * (350 - WIDTH_OUT_DIFF) * (607 - HEIGHT_OUT_DIFF) * 3);
  unsigned char *output = malloc(sizeof(unsigned char) * (350 - WIDTH_OUT_DIFF) * (607 - HEIGHT_OUT_DIFF) * 3);

  for (int y = 0; y < 607; y++) {
    for (int x = 0; x < 350; x++) {
      input[3 * (x + 350 * y) + 2] = rand();
      input[3 * (x + 350 * y) + 1] = rand();
      input[3 * (x + 350 * y) + 0] = rand();
    }
  }

  ref_pipeline(input, ref_output, 350, 607);
  pipeline(input, output, 350, 607);

  for (int y = 0; y < 607 - HEIGHT_OUT_DIFF; y++) {
    for (int x = 0; x < 350 - WIDTH_OUT_DIFF; x++) {
      assert(ref_output[3 * (x + (350 - WIDTH_OUT_DIFF) * y) + 2] == output[3 * (x + (350 - WIDTH_OUT_DIFF) * y) + 2]);
      assert(ref_output[3 * (x + (350 - WIDTH_OUT_DIFF) * y) + 1] == output[3 * (x + (350 - WIDTH_OUT_DIFF) * y) + 1]);
      assert(ref_output[3 * (x + (350 - WIDTH_OUT_DIFF) * y) + 0] == output[3 * (x + (350 - WIDTH_OUT_DIFF) * y) + 0]);
    }
  }

  printf("Test passed\n");
}
