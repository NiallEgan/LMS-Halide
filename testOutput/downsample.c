#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 / 2;
int32_t x5 = x4 - 2;
int32_t x8 = x5 - 1;
int32_t x6 = x3 / 2;
int32_t x7 = x6 - 2;
int32_t x9 = x7 - 1;
int32_t x10 = x8 * x9;
int32_t x11 = x10 * 3;
UCHAR *x300 = malloc(sizeof(UCHAR) * x11);
for(int x14=1; x14 < x7; x14++) {
int32_t x17 = 2 * x14;
int32_t x18 = x17 - 1;
int32_t x21 = x2 * x18;
int32_t x82 = x2 * x17;
int32_t x141 = x17 + 1;
int32_t x142 = x2 * x141;
int32_t x210 = x17 + 2;
int32_t x211 = x2 * x210;
for(int x16=1; x16 < x5; x16++) {
int32_t x19 = 2 * x16;
int32_t x29 = x19 + x21;
int32_t x30 = 3 * x29;
int32_t x31 = x30 + 2;
UCHAR x32 = x0[x31];
uint16_t x44 = (uint16_t) x32;
int32_t x36 = x19 + 1;
int32_t x37 = x36 + x21;
int32_t x38 = 3 * x37;
int32_t x39 = x38 + 2;
UCHAR x40 = x0[x39];
uint16_t x47 = (uint16_t) x40;
uint16_t x50 = x44 + x47;
float x53 = (float) x50;
float x56 = 3.0f * x53;
int32_t x20 = x19 - 1;
int32_t x22 = x20 + x21;
int32_t x23 = 3 * x22;
int32_t x24 = x23 + 2;
UCHAR x25 = x0[x24];
float x59 = (float) x25;
float x62 = x59 + x56;
int32_t x65 = x19 + 2;
int32_t x66 = x65 + x21;
int32_t x67 = 3 * x66;
int32_t x68 = x67 + 2;
UCHAR x69 = x0[x68];
float x73 = (float) x69;
float x76 = x62 + x73;
float x79 = x76 / 8.0f;
int32_t x90 = x19 + x82;
int32_t x91 = 3 * x90;
int32_t x92 = x91 + 2;
UCHAR x93 = x0[x92];
uint16_t x104 = (uint16_t) x93;
int32_t x97 = x36 + x82;
int32_t x98 = 3 * x97;
int32_t x99 = x98 + 2;
UCHAR x100 = x0[x99];
uint16_t x107 = (uint16_t) x100;
uint16_t x110 = x104 + x107;
float x113 = (float) x110;
float x116 = 3.0f * x113;
int32_t x83 = x20 + x82;
int32_t x84 = 3 * x83;
int32_t x85 = x84 + 2;
UCHAR x86 = x0[x85];
float x119 = (float) x86;
float x122 = x119 + x116;
int32_t x125 = x65 + x82;
int32_t x126 = 3 * x125;
int32_t x127 = x126 + 2;
UCHAR x128 = x0[x127];
float x132 = (float) x128;
float x135 = x122 + x132;
float x138 = x135 / 8.0f;
int32_t x150 = x19 + x142;
int32_t x151 = 3 * x150;
int32_t x152 = x151 + 2;
UCHAR x153 = x0[x152];
uint16_t x164 = (uint16_t) x153;
int32_t x157 = x36 + x142;
int32_t x158 = 3 * x157;
int32_t x159 = x158 + 2;
UCHAR x160 = x0[x159];
uint16_t x167 = (uint16_t) x160;
uint16_t x170 = x164 + x167;
float x173 = (float) x170;
float x176 = 3.0f * x173;
int32_t x143 = x20 + x142;
int32_t x144 = 3 * x143;
int32_t x145 = x144 + 2;
UCHAR x146 = x0[x145];
float x179 = (float) x146;
float x182 = x179 + x176;
int32_t x185 = x65 + x142;
int32_t x186 = 3 * x185;
int32_t x187 = x186 + 2;
UCHAR x188 = x0[x187];
float x192 = (float) x188;
float x195 = x182 + x192;
float x198 = x195 / 8.0f;
float x201 = x138 + x198;
float x204 = 3.0f * x201;
float x207 = x79 + x204;
int32_t x219 = x19 + x211;
int32_t x220 = 3 * x219;
int32_t x221 = x220 + 2;
UCHAR x222 = x0[x221];
uint16_t x233 = (uint16_t) x222;
int32_t x226 = x36 + x211;
int32_t x227 = 3 * x226;
int32_t x228 = x227 + 2;
UCHAR x229 = x0[x228];
uint16_t x236 = (uint16_t) x229;
uint16_t x239 = x233 + x236;
float x242 = (float) x239;
float x245 = 3.0f * x242;
int32_t x212 = x20 + x211;
int32_t x213 = 3 * x212;
int32_t x214 = x213 + 2;
UCHAR x215 = x0[x214];
float x248 = (float) x215;
float x251 = x248 + x245;
int32_t x254 = x65 + x211;
int32_t x255 = 3 * x254;
int32_t x256 = x255 + 2;
UCHAR x257 = x0[x256];
float x261 = (float) x257;
float x264 = x251 + x261;
float x267 = x264 / 8.0f;
float x270 = x207 + x267;
float x273 = x270 / 8.0f;
UCHAR x276 = (UCHAR) x273;
int32_t x279 = x16 - 1;
int32_t x281 = MIN(x279, x8);
int32_t x282 = MAX(0, x281);
int32_t x283 = MIN(x279, x9);
int32_t x284 = MAX(0, x283);
int32_t x285 = x8 * x284;
int32_t x286 = x282 + x285;
int32_t x287 = 3 * x286;
int32_t x288 = x287 + 2;
x300[x288] = x276;
int32_t x33 = x30 + 1;
UCHAR x34 = x0[x33];
uint16_t x45 = (uint16_t) x34;
int32_t x41 = x38 + 1;
UCHAR x42 = x0[x41];
uint16_t x48 = (uint16_t) x42;
uint16_t x51 = x45 + x48;
float x54 = (float) x51;
float x57 = 3.0f * x54;
int32_t x26 = x23 + 1;
UCHAR x27 = x0[x26];
float x60 = (float) x27;
float x63 = x60 + x57;
int32_t x70 = x67 + 1;
UCHAR x71 = x0[x70];
float x74 = (float) x71;
float x77 = x63 + x74;
float x80 = x77 / 8.0f;
int32_t x94 = x91 + 1;
UCHAR x95 = x0[x94];
uint16_t x105 = (uint16_t) x95;
int32_t x101 = x98 + 1;
UCHAR x102 = x0[x101];
uint16_t x108 = (uint16_t) x102;
uint16_t x111 = x105 + x108;
float x114 = (float) x111;
float x117 = 3.0f * x114;
int32_t x87 = x84 + 1;
UCHAR x88 = x0[x87];
float x120 = (float) x88;
float x123 = x120 + x117;
int32_t x129 = x126 + 1;
UCHAR x130 = x0[x129];
float x133 = (float) x130;
float x136 = x123 + x133;
float x139 = x136 / 8.0f;
int32_t x154 = x151 + 1;
UCHAR x155 = x0[x154];
uint16_t x165 = (uint16_t) x155;
int32_t x161 = x158 + 1;
UCHAR x162 = x0[x161];
uint16_t x168 = (uint16_t) x162;
uint16_t x171 = x165 + x168;
float x174 = (float) x171;
float x177 = 3.0f * x174;
int32_t x147 = x144 + 1;
UCHAR x148 = x0[x147];
float x180 = (float) x148;
float x183 = x180 + x177;
int32_t x189 = x186 + 1;
UCHAR x190 = x0[x189];
float x193 = (float) x190;
float x196 = x183 + x193;
float x199 = x196 / 8.0f;
float x202 = x139 + x199;
float x205 = 3.0f * x202;
float x208 = x80 + x205;
int32_t x223 = x220 + 1;
UCHAR x224 = x0[x223];
uint16_t x234 = (uint16_t) x224;
int32_t x230 = x227 + 1;
UCHAR x231 = x0[x230];
uint16_t x237 = (uint16_t) x231;
uint16_t x240 = x234 + x237;
float x243 = (float) x240;
float x246 = 3.0f * x243;
int32_t x216 = x213 + 1;
UCHAR x217 = x0[x216];
float x249 = (float) x217;
float x252 = x249 + x246;
int32_t x258 = x255 + 1;
UCHAR x259 = x0[x258];
float x262 = (float) x259;
float x265 = x252 + x262;
float x268 = x265 / 8.0f;
float x271 = x208 + x268;
float x274 = x271 / 8.0f;
UCHAR x277 = (UCHAR) x274;
int32_t x290 = x287 + 1;
x300[x290] = x277;
UCHAR x35 = x0[x30];
uint16_t x46 = (uint16_t) x35;
UCHAR x43 = x0[x38];
uint16_t x49 = (uint16_t) x43;
uint16_t x52 = x46 + x49;
float x55 = (float) x52;
float x58 = 3.0f * x55;
UCHAR x28 = x0[x23];
float x61 = (float) x28;
float x64 = x61 + x58;
UCHAR x72 = x0[x67];
float x75 = (float) x72;
float x78 = x64 + x75;
float x81 = x78 / 8.0f;
UCHAR x96 = x0[x91];
uint16_t x106 = (uint16_t) x96;
UCHAR x103 = x0[x98];
uint16_t x109 = (uint16_t) x103;
uint16_t x112 = x106 + x109;
float x115 = (float) x112;
float x118 = 3.0f * x115;
UCHAR x89 = x0[x84];
float x121 = (float) x89;
float x124 = x121 + x118;
UCHAR x131 = x0[x126];
float x134 = (float) x131;
float x137 = x124 + x134;
float x140 = x137 / 8.0f;
UCHAR x156 = x0[x151];
uint16_t x166 = (uint16_t) x156;
UCHAR x163 = x0[x158];
uint16_t x169 = (uint16_t) x163;
uint16_t x172 = x166 + x169;
float x175 = (float) x172;
float x178 = 3.0f * x175;
UCHAR x149 = x0[x144];
float x181 = (float) x149;
float x184 = x181 + x178;
UCHAR x191 = x0[x186];
float x194 = (float) x191;
float x197 = x184 + x194;
float x200 = x197 / 8.0f;
float x203 = x140 + x200;
float x206 = 3.0f * x203;
float x209 = x81 + x206;
UCHAR x225 = x0[x220];
uint16_t x235 = (uint16_t) x225;
UCHAR x232 = x0[x227];
uint16_t x238 = (uint16_t) x232;
uint16_t x241 = x235 + x238;
float x244 = (float) x241;
float x247 = 3.0f * x244;
UCHAR x218 = x0[x213];
float x250 = (float) x218;
float x253 = x250 + x247;
UCHAR x260 = x0[x255];
float x263 = (float) x260;
float x266 = x253 + x263;
float x269 = x266 / 8.0f;
float x272 = x209 + x269;
float x275 = x272 / 8.0f;
UCHAR x278 = (UCHAR) x275;
x300[x287] = x278;

}

}
memcpy(x1, x300, x11);
free(x300);
}
int32_t WIDTH_OUT_DIFF = 3;
int32_t HEIGHT_OUT_DIFF = 3;
int32_t WIDTH_OUT_MUL = 1;
int32_t WIDTH_OUT_DIV = 2;
int32_t HEIGHT_OUT_MUL = 1;
int32_t HEIGHT_OUT_DIV = 2;
