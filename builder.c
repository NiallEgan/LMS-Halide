#include "pipeline.h"

void pipeline(UCHAR* x0, UCHAR* x3, int32_t  x1, int32_t  x2) {
int32_t x4 = x1 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x2 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR x10[x9];
for(int x12=1; x12 < x5; x12++) {
int32_t x41 = x12 - 1;
int32_t x139 = x6 * x41;
int32_t x15 = x1 * x12;
int32_t x26 = x12 + 1;
int32_t x27 = x1 * x26;
int32_t x42 = x1 * x41;
for(int x14=1; x14 < x4; x14++) {
int32_t x56 = x14 - 1;
int32_t x140 = x56 + x139;
int32_t x141 = 3 * x140;
int32_t x142 = x141 + 2;
int32_t x16 = x14 + x15;
int32_t x17 = 3 * x16;
int32_t x18 = x17 + 2;
UCHAR x19 = x0[x18];
int32_t x20 = (int) x19;
int32_t x28 = x14 + x27;
int32_t x29 = 3 * x28;
int32_t x30 = x29 + 2;
UCHAR x31 = x0[x30];
int32_t x32 = (int) x31;
int32_t x38 = x20 + x32;
int32_t x43 = x14 + x42;
int32_t x44 = 3 * x43;
int32_t x45 = x44 + 2;
UCHAR x46 = x0[x45];
int32_t x47 = (int) x46;
int32_t x53 = x38 + x47;
int32_t x57 = x56 + x42;
int32_t x58 = 3 * x57;
int32_t x59 = x58 + 2;
UCHAR x60 = x0[x59];
int32_t x61 = (int) x60;
int32_t x67 = x53 + x61;
int32_t x70 = x56 + x15;
int32_t x71 = 3 * x70;
int32_t x72 = x71 + 2;
UCHAR x73 = x0[x72];
int32_t x74 = (int) x73;
int32_t x80 = x67 + x74;
int32_t x83 = x56 + x27;
int32_t x84 = 3 * x83;
int32_t x85 = x84 + 2;
UCHAR x86 = x0[x85];
int32_t x87 = (int) x86;
int32_t x93 = x80 + x87;
int32_t x96 = x14 + 1;
int32_t x97 = x96 + x42;
int32_t x98 = 3 * x97;
int32_t x99 = x98 + 2;
UCHAR x100 = x0[x99];
int32_t x101 = (int) x100;
int32_t x107 = x93 + x101;
int32_t x110 = x96 + x15;
int32_t x111 = 3 * x110;
int32_t x112 = x111 + 2;
UCHAR x113 = x0[x112];
int32_t x114 = (int) x113;
int32_t x120 = x107 + x114;
int32_t x123 = x96 + x27;
int32_t x124 = 3 * x123;
int32_t x125 = x124 + 2;
UCHAR x126 = x0[x125];
int32_t x127 = (int) x126;
int32_t x133 = x120 + x127;
int32_t x136 = x133 / 9;
UCHAR x143 = x136;
x10[x142] = x143;
int32_t x145 = x141 + 1;
int32_t x21 = x17 + 1;
UCHAR x22 = x0[x21];
int32_t x23 = (int) x22;
int32_t x33 = x29 + 1;
UCHAR x34 = x0[x33];
int32_t x35 = (int) x34;
int32_t x39 = x23 + x35;
int32_t x48 = x44 + 1;
UCHAR x49 = x0[x48];
int32_t x50 = (int) x49;
int32_t x54 = x39 + x50;
int32_t x62 = x58 + 1;
UCHAR x63 = x0[x62];
int32_t x64 = (int) x63;
int32_t x68 = x54 + x64;
int32_t x75 = x71 + 1;
UCHAR x76 = x0[x75];
int32_t x77 = (int) x76;
int32_t x81 = x68 + x77;
int32_t x88 = x84 + 1;
UCHAR x89 = x0[x88];
int32_t x90 = (int) x89;
int32_t x94 = x81 + x90;
int32_t x102 = x98 + 1;
UCHAR x103 = x0[x102];
int32_t x104 = (int) x103;
int32_t x108 = x94 + x104;
int32_t x115 = x111 + 1;
UCHAR x116 = x0[x115];
int32_t x117 = (int) x116;
int32_t x121 = x108 + x117;
int32_t x128 = x124 + 1;
UCHAR x129 = x0[x128];
int32_t x130 = (int) x129;
int32_t x134 = x121 + x130;
int32_t x137 = x134 / 9;
UCHAR x146 = x137;
x10[x145] = x146;
UCHAR x24 = x0[x17];
int32_t x25 = (int) x24;
UCHAR x36 = x0[x29];
int32_t x37 = (int) x36;
int32_t x40 = x25 + x37;
UCHAR x51 = x0[x44];
int32_t x52 = (int) x51;
int32_t x55 = x40 + x52;
UCHAR x65 = x0[x58];
int32_t x66 = (int) x65;
int32_t x69 = x55 + x66;
UCHAR x78 = x0[x71];
int32_t x79 = (int) x78;
int32_t x82 = x69 + x79;
UCHAR x91 = x0[x84];
int32_t x92 = (int) x91;
int32_t x95 = x82 + x92;
UCHAR x105 = x0[x98];
int32_t x106 = (int) x105;
int32_t x109 = x95 + x106;
UCHAR x118 = x0[x111];
int32_t x119 = (int) x118;
int32_t x122 = x109 + x119;
UCHAR x131 = x0[x124];
int32_t x132 = (int) x131;
int32_t x135 = x122 + x132;
int32_t x138 = x135 / 9;
UCHAR x148 = x138;
x10[x141] = x148;

}

}
for(int x155=0; x155 < x7; x155++) {
int32_t x158 = x6 * x155;
for(int x157=0; x157 < x6; x157++) {
int32_t x159 = x157 + x158;
int32_t x160 = 3 * x159;
int32_t x161 = x160 + 2;
UCHAR x162 = x10[x161];
int32_t x164 = x160 + 1;
UCHAR x165 = x10[x164];
UCHAR x167 = x10[x160];
int32_t x163 = (int) x162;
UCHAR x169 = x163;
x3[x161] = x169;
int32_t x166 = (int) x165;
UCHAR x171 = x166;
x3[x164] = x171;
int32_t x168 = (int) x167;
UCHAR x173 = x168;
x3[x160] = x173;

}

}
}

int compare(UCHAR *a, UCHAR *b, int w, int h) {
  for (int x = 0; x < w; x++) {
    for (int y = 0; y < h; y++) {
      int base = (x + y * x) * 3;
      if (a[base] != b[base] || a[base+1] != b[base+1] || a[base+2] != b[base+2]) return 1;
    }
  }
  return 0;
}

int main(int argc, char **argv) {
  // N.B. BMPv3 only for now... :(
  BMP* bmp = BMP_ReadFile(argv[1]);
  printf("%s\n", BMP_ERROR_STRING[BMP_LAST_ERROR_CODE]);
  BMP* new_bmp = BMP_Create(bmp->Header.Width, bmp->Header.Height, bmp->Header.BitsPerPixel);

  pipeline(bmp->Data, new_bmp->Data, bmp->Header.Width, bmp->Header.Height);
  printf("Different = %d\n", compare(bmp->Data, new_bmp->Data, 1024, 768));
  printf("%s\n", BMP_ERROR_STRING[BMP_LAST_ERROR_CODE]);

  printf("Width: %lu\n, Height: %lu\n", bmp->Header.Width, bmp->Header.Height);

  BMP_WriteFile(new_bmp, argv[2]);
}
