/*****************************************
  Emitting C Generated Code                  
*******************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
void pipeline(UCHAR[] * x0, UCHAR[] * x3, int32_t  x1, int32_t  x2) {
int32_t x4 = x1 - 1;
int32_t x6 = x4 - 1;
int32_t x5 = x2 - 1;
int32_t x7 = x5 - 1;
int32_t x8 = x6 * x7;
int32_t x9 = x8 * 3;
UCHAR x10[x9];
for(int x12=1; x12 < x5; x12++) {
int32_t x101 = x12 - 1;
int32_t x148 = x6 * x101;
int32_t x15 = x1 * x12;
int32_t x57 = x12 + 1;
int32_t x58 = x1 * x57;
int32_t x102 = x1 * x101;
for(int x14=1; x14 < x4; x14++) {
int32_t x40 = x14 - 1;
int32_t x149 = x40 + x148;
int32_t x150 = 3 * x149;
int32_t x151 = x150 + 2;
int32_t x16 = x14 + x15;
int32_t x17 = 3 * x16;
int32_t x18 = x17 + 2;
UCHAR x19 = x0[x18];
int32_t x20 = (int) x19;
int32_t x26 = x14 + 1;
int32_t x27 = x26 + x15;
int32_t x28 = 3 * x27;
int32_t x29 = x28 + 2;
UCHAR x30 = x0[x29];
int32_t x31 = (int) x30;
int32_t x37 = x20 + x31;
int32_t x41 = x40 + x15;
int32_t x42 = 3 * x41;
int32_t x43 = x42 + 2;
UCHAR x44 = x0[x43];
int32_t x45 = (int) x44;
int32_t x51 = x37 + x45;
int32_t x54 = x51 / 3;
int32_t x59 = x14 + x58;
int32_t x60 = 3 * x59;
int32_t x61 = x60 + 2;
UCHAR x62 = x0[x61];
int32_t x63 = (int) x62;
int32_t x69 = x26 + x58;
int32_t x70 = 3 * x69;
int32_t x71 = x70 + 2;
UCHAR x72 = x0[x71];
int32_t x73 = (int) x72;
int32_t x79 = x63 + x73;
int32_t x82 = x40 + x58;
int32_t x83 = 3 * x82;
int32_t x84 = x83 + 2;
UCHAR x85 = x0[x84];
int32_t x86 = (int) x85;
int32_t x92 = x79 + x86;
int32_t x95 = x92 / 3;
int32_t x98 = x54 + x95;
int32_t x103 = x14 + x102;
int32_t x104 = 3 * x103;
int32_t x105 = x104 + 2;
UCHAR x106 = x0[x105];
int32_t x107 = (int) x106;
int32_t x113 = x26 + x102;
int32_t x114 = 3 * x113;
int32_t x115 = x114 + 2;
UCHAR x116 = x0[x115];
int32_t x117 = (int) x116;
int32_t x123 = x107 + x117;
int32_t x126 = x40 + x102;
int32_t x127 = 3 * x126;
int32_t x128 = x127 + 2;
UCHAR x129 = x0[x128];
int32_t x130 = (int) x129;
int32_t x136 = x123 + x130;
int32_t x139 = x136 / 3;
int32_t x142 = x98 + x139;
int32_t x145 = x142 / 3;
UCHAR x152 = x145;
x10[x151] = x152;
int32_t x154 = x150 + 1;
int32_t x21 = x17 + 1;
UCHAR x22 = x0[x21];
int32_t x23 = (int) x22;
int32_t x32 = x28 + 1;
UCHAR x33 = x0[x32];
int32_t x34 = (int) x33;
int32_t x38 = x23 + x34;
int32_t x46 = x42 + 1;
UCHAR x47 = x0[x46];
int32_t x48 = (int) x47;
int32_t x52 = x38 + x48;
int32_t x55 = x52 / 3;
int32_t x64 = x60 + 1;
UCHAR x65 = x0[x64];
int32_t x66 = (int) x65;
int32_t x74 = x70 + 1;
UCHAR x75 = x0[x74];
int32_t x76 = (int) x75;
int32_t x80 = x66 + x76;
int32_t x87 = x83 + 1;
UCHAR x88 = x0[x87];
int32_t x89 = (int) x88;
int32_t x93 = x80 + x89;
int32_t x96 = x93 / 3;
int32_t x99 = x55 + x96;
int32_t x108 = x104 + 1;
UCHAR x109 = x0[x108];
int32_t x110 = (int) x109;
int32_t x118 = x114 + 1;
UCHAR x119 = x0[x118];
int32_t x120 = (int) x119;
int32_t x124 = x110 + x120;
int32_t x131 = x127 + 1;
UCHAR x132 = x0[x131];
int32_t x133 = (int) x132;
int32_t x137 = x124 + x133;
int32_t x140 = x137 / 3;
int32_t x143 = x99 + x140;
int32_t x146 = x143 / 3;
UCHAR x155 = x146;
x10[x154] = x155;
UCHAR x24 = x0[x17];
int32_t x25 = (int) x24;
UCHAR x35 = x0[x28];
int32_t x36 = (int) x35;
int32_t x39 = x25 + x36;
UCHAR x49 = x0[x42];
int32_t x50 = (int) x49;
int32_t x53 = x39 + x50;
int32_t x56 = x53 / 3;
UCHAR x67 = x0[x60];
int32_t x68 = (int) x67;
UCHAR x77 = x0[x70];
int32_t x78 = (int) x77;
int32_t x81 = x68 + x78;
UCHAR x90 = x0[x83];
int32_t x91 = (int) x90;
int32_t x94 = x81 + x91;
int32_t x97 = x94 / 3;
int32_t x100 = x56 + x97;
UCHAR x111 = x0[x104];
int32_t x112 = (int) x111;
UCHAR x121 = x0[x114];
int32_t x122 = (int) x121;
int32_t x125 = x112 + x122;
UCHAR x134 = x0[x127];
int32_t x135 = (int) x134;
int32_t x138 = x125 + x135;
int32_t x141 = x138 / 3;
int32_t x144 = x100 + x141;
int32_t x147 = x144 / 3;
UCHAR x157 = x147;
x10[x150] = x157;

}

}
for(int x164=0; x164 < x7; x164++) {
int32_t x167 = x6 * x164;
for(int x166=0; x166 < x6; x166++) {
int32_t x168 = x166 + x167;
int32_t x169 = 3 * x168;
int32_t x170 = x169 + 2;
UCHAR x171 = x10[x170];
int32_t x173 = x169 + 1;
UCHAR x174 = x10[x173];
UCHAR x176 = x10[x169];
int32_t x172 = (int) x171;
UCHAR x178 = x172;
x3[x170] = x178;
int32_t x175 = (int) x174;
UCHAR x180 = x175;
x3[x173] = x180;
int32_t x177 = (int) x176;
UCHAR x182 = x177;
x3[x169] = x182;

}

}
}
/*****************************************
  End of C Generated Code                  
*******************************************/
