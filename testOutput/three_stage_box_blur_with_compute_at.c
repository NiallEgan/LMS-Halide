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
int32_t x13 = x1 * 3;
int32_t x14 = x13 * 3;
for(int x12=1; x12 < x5; x12++) {
UCHAR x15[x14];
int32_t x16 = x12 + -1;
int32_t x17 = x12 + 1;
int32_t x18 = x17 + 1;
for(int x20=x16; x20 < x18; x20++) {
int32_t x37 = x20 - x16;
int32_t x38 = x1 * x37;
int32_t x23 = x1 * x20;
for(int x22=0; x22 < x1; x22++) {
int32_t x39 = x22 + x38;
int32_t x40 = 3 * x39;
int32_t x41 = x40 + 2;
int32_t x24 = x22 + x23;
int32_t x25 = 3 * x24;
int32_t x26 = x25 + 2;
UCHAR x27 = x0[x26];
int32_t x28 = (int) x27;
int32_t x34 = x28 / 2;
UCHAR x42 = x34;
x15[x41] = x42;
int32_t x44 = x40 + 1;
int32_t x29 = x25 + 1;
UCHAR x30 = x0[x29];
int32_t x31 = (int) x30;
int32_t x35 = x31 / 2;
UCHAR x45 = x35;
x15[x44] = x45;
UCHAR x32 = x0[x25];
int32_t x33 = (int) x32;
int32_t x36 = x33 / 2;
UCHAR x47 = x36;
x15[x40] = x47;

}

}
int32_t x55 = x12 - x16;
int32_t x56 = x1 * x55;
int32_t x98 = x17 - x16;
int32_t x99 = x1 * x98;
int32_t x142 = x12 - 1;
int32_t x143 = x142 - x16;
int32_t x144 = x1 * x143;
int32_t x190 = x6 * x142;
for(int x54=1; x54 < x4; x54++) {
int32_t x57 = x54 + x56;
int32_t x58 = 3 * x57;
int32_t x59 = x58 + 2;
UCHAR x60 = x15[x59];
int32_t x62 = x58 + 1;
UCHAR x63 = x15[x62];
UCHAR x65 = x15[x58];
int32_t x67 = x54 + 1;
int32_t x68 = x67 + x56;
int32_t x69 = 3 * x68;
int32_t x70 = x69 + 2;
UCHAR x71 = x15[x70];
int32_t x73 = x69 + 1;
UCHAR x74 = x15[x73];
UCHAR x76 = x15[x69];
int32_t x81 = x54 - 1;
int32_t x82 = x81 + x56;
int32_t x83 = 3 * x82;
int32_t x84 = x83 + 2;
UCHAR x85 = x15[x84];
int32_t x87 = x83 + 1;
UCHAR x88 = x15[x87];
UCHAR x90 = x15[x83];
int32_t x100 = x54 + x99;
int32_t x101 = 3 * x100;
int32_t x102 = x101 + 2;
UCHAR x103 = x15[x102];
int32_t x105 = x101 + 1;
UCHAR x106 = x15[x105];
UCHAR x108 = x15[x101];
int32_t x110 = x67 + x99;
int32_t x111 = 3 * x110;
int32_t x112 = x111 + 2;
UCHAR x113 = x15[x112];
int32_t x115 = x111 + 1;
UCHAR x116 = x15[x115];
UCHAR x118 = x15[x111];
int32_t x123 = x81 + x99;
int32_t x124 = 3 * x123;
int32_t x125 = x124 + 2;
UCHAR x126 = x15[x125];
int32_t x128 = x124 + 1;
UCHAR x129 = x15[x128];
UCHAR x131 = x15[x124];
int32_t x145 = x54 + x144;
int32_t x146 = 3 * x145;
int32_t x147 = x146 + 2;
UCHAR x148 = x15[x147];
int32_t x150 = x146 + 1;
UCHAR x151 = x15[x150];
UCHAR x153 = x15[x146];
int32_t x155 = x67 + x144;
int32_t x156 = 3 * x155;
int32_t x157 = x156 + 2;
UCHAR x158 = x15[x157];
int32_t x160 = x156 + 1;
UCHAR x161 = x15[x160];
UCHAR x163 = x15[x156];
int32_t x168 = x81 + x144;
int32_t x169 = 3 * x168;
int32_t x170 = x169 + 2;
UCHAR x171 = x15[x170];
int32_t x173 = x169 + 1;
UCHAR x174 = x15[x173];
UCHAR x176 = x15[x169];
int32_t x191 = x81 + x190;
int32_t x192 = 3 * x191;
int32_t x193 = x192 + 2;
int32_t x61 = (int) x60;
int32_t x72 = (int) x71;
int32_t x78 = x61 + x72;
int32_t x86 = (int) x85;
int32_t x92 = x78 + x86;
int32_t x95 = x92 / 3;
int32_t x104 = (int) x103;
int32_t x114 = (int) x113;
int32_t x120 = x104 + x114;
int32_t x127 = (int) x126;
int32_t x133 = x120 + x127;
int32_t x136 = x133 / 3;
int32_t x139 = x95 + x136;
int32_t x149 = (int) x148;
int32_t x159 = (int) x158;
int32_t x165 = x149 + x159;
int32_t x172 = (int) x171;
int32_t x178 = x165 + x172;
int32_t x181 = x178 / 3;
int32_t x184 = x139 + x181;
int32_t x187 = x184 / 3;
UCHAR x194 = x187;
x10[x193] = x194;
int32_t x196 = x192 + 1;
int32_t x64 = (int) x63;
int32_t x75 = (int) x74;
int32_t x79 = x64 + x75;
int32_t x89 = (int) x88;
int32_t x93 = x79 + x89;
int32_t x96 = x93 / 3;
int32_t x107 = (int) x106;
int32_t x117 = (int) x116;
int32_t x121 = x107 + x117;
int32_t x130 = (int) x129;
int32_t x134 = x121 + x130;
int32_t x137 = x134 / 3;
int32_t x140 = x96 + x137;
int32_t x152 = (int) x151;
int32_t x162 = (int) x161;
int32_t x166 = x152 + x162;
int32_t x175 = (int) x174;
int32_t x179 = x166 + x175;
int32_t x182 = x179 / 3;
int32_t x185 = x140 + x182;
int32_t x188 = x185 / 3;
UCHAR x197 = x188;
x10[x196] = x197;
int32_t x66 = (int) x65;
int32_t x77 = (int) x76;
int32_t x80 = x66 + x77;
int32_t x91 = (int) x90;
int32_t x94 = x80 + x91;
int32_t x97 = x94 / 3;
int32_t x109 = (int) x108;
int32_t x119 = (int) x118;
int32_t x122 = x109 + x119;
int32_t x132 = (int) x131;
int32_t x135 = x122 + x132;
int32_t x138 = x135 / 3;
int32_t x141 = x97 + x138;
int32_t x154 = (int) x153;
int32_t x164 = (int) x163;
int32_t x167 = x154 + x164;
int32_t x177 = (int) x176;
int32_t x180 = x167 + x177;
int32_t x183 = x180 / 3;
int32_t x186 = x141 + x183;
int32_t x189 = x186 / 3;
UCHAR x199 = x189;
x10[x192] = x199;

}

}
for(int x206=0; x206 < x7; x206++) {
int32_t x209 = x6 * x206;
for(int x208=0; x208 < x6; x208++) {
int32_t x210 = x208 + x209;
int32_t x211 = 3 * x210;
int32_t x212 = x211 + 2;
UCHAR x213 = x10[x212];
int32_t x215 = x211 + 1;
UCHAR x216 = x10[x215];
UCHAR x218 = x10[x211];
int32_t x214 = (int) x213;
UCHAR x220 = x214;
x3[x212] = x220;
int32_t x217 = (int) x216;
UCHAR x222 = x217;
x3[x215] = x222;
int32_t x219 = (int) x218;
UCHAR x224 = x219;
x3[x211] = x224;

}

}
}
/*****************************************
  End of C Generated Code                  
*******************************************/
