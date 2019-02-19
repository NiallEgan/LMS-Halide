#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x8 = x2 * x3;
int32_t x9 = x8 * 3;
int32_t *x205 = malloc(sizeof(int32_t) * x9);
for(int x12=0; x12 < x3; x12++) {
int32_t x15 = x2 * x12;
for(int x14=0; x14 < x2; x14++) {
int32_t x16 = x14 + x15;
int32_t x17 = 3 * x16;
int32_t x18 = x17 + 2;
UCHAR x19 = x0[x18];
int32_t x23 = (int32_t) x19;
x205[x18] = x23;
int32_t x20 = x17 + 1;
UCHAR x21 = x0[x20];
int32_t x24 = (int32_t) x21;
x205[x20] = x24;
UCHAR x22 = x0[x17];
int32_t x25 = (int32_t) x22;
x205[x17] = x25;

}

}
int32_t x4 = x2 - 1;
int32_t x33 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x34 = x5 - 1;
int32_t x35 = x33 * x34;
int32_t x36 = x35 * 3;
UCHAR *x213 = malloc(sizeof(UCHAR) * x36);
int32_t x40 = x2 * 3;
int32_t x41 = x40 * 3;
int32_t x48 = x2 + 8;
int32_t x49 = x48 - 1;
int32_t x50 = x49 / 8;
int32_t x6 = x2 - 8;
for(int x39=1; x39 < x5; x39++) {
int32_t *x214 = malloc(sizeof(int32_t) * x41);
int32_t x43 = x39 + -1;
int32_t x44 = x39 + 1;
int32_t x45 = x44 + 1;
for(int x47=x43; x47 < x45; x47++) {
int32_t x58 = x2 * x47;
int32_t x69 = x47 - x43;
int32_t x70 = x2 * x69;
for(int x52=0; x52 < x50; x52++) {
int32_t x54 = x52 * 8;
bool x55 = x54 > x6;
int32_t x56;
if (x55) {
x56 = x6;
} else {
x56 = x54;
}
int32_t x215 = x56 + x58;
int32_t x216 = 3 * x215;
int32_t x218 = x216 + 16;
int32_t x221 = x216 + 8;
int32_t x225 = x56 + x70;
int32_t x226 = 3 * x225;
int32_t x228 = x226 + 16;
int32_t x236 = x226 + 8;
int32_t x219 = x205[x218];
int32_t x222 = x205[x221];
int32_t x223 = x205[x216];
// loadu
__m256i x230 = _mm256_loadu_si256((__m256i const *) (x205 + x218));
// loadu
__m256i x232 = _mm256_add_epi32(x230, x230);
_mm256_storeu_si256((__m256i *) (x214 + x228), x232);
// loadu
__m256i x238 = _mm256_loadu_si256((__m256i const *) (x205 + x221));
// loadu
__m256i x240 = _mm256_add_epi32(x238, x238);
_mm256_storeu_si256((__m256i *) (x214 + x236), x240);
// loadu
__m256i x244 = _mm256_loadu_si256((__m256i const *) (x205 + x216));
// loadu
__m256i x246 = _mm256_add_epi32(x244, x244);
_mm256_storeu_si256((__m256i *) (x214 + x226), x246);

}

}
int32_t x86 = x39 - x43;
int32_t x87 = x2 * x86;
int32_t x95 = x44 - x43;
int32_t x96 = x2 * x95;
int32_t x107 = x39 - 1;
int32_t x108 = x107 - x43;
int32_t x109 = x2 * x108;
int32_t x188 = x33 * x107;
for(int x85=1; x85 < x4; x85++) {
int32_t x88 = x85 + x87;
int32_t x89 = 3 * x88;
int32_t x90 = x89 + 2;
int32_t x256 = x214[x90];
int32_t x92 = x89 + 1;
int32_t x257 = x214[x92];
int32_t x258 = x214[x89];
int32_t x97 = x85 + x96;
int32_t x98 = 3 * x97;
int32_t x99 = x98 + 2;
int32_t x259 = x214[x99];
int32_t x101 = x98 + 1;
int32_t x260 = x214[x101];
int32_t x261 = x214[x98];
int32_t x110 = x85 + x109;
int32_t x111 = 3 * x110;
int32_t x112 = x111 + 2;
int32_t x262 = x214[x112];
int32_t x114 = x111 + 1;
int32_t x263 = x214[x114];
int32_t x264 = x214[x111];
int32_t x120 = x85 - 1;
int32_t x121 = x120 + x109;
int32_t x122 = 3 * x121;
int32_t x123 = x122 + 2;
int32_t x265 = x214[x123];
int32_t x125 = x122 + 1;
int32_t x266 = x214[x125];
int32_t x267 = x214[x122];
int32_t x131 = x120 + x87;
int32_t x132 = 3 * x131;
int32_t x133 = x132 + 2;
int32_t x268 = x214[x133];
int32_t x135 = x132 + 1;
int32_t x269 = x214[x135];
int32_t x270 = x214[x132];
int32_t x141 = x120 + x96;
int32_t x142 = 3 * x141;
int32_t x143 = x142 + 2;
int32_t x271 = x214[x143];
int32_t x145 = x142 + 1;
int32_t x272 = x214[x145];
int32_t x273 = x214[x142];
int32_t x151 = x85 + 1;
int32_t x152 = x151 + x109;
int32_t x153 = 3 * x152;
int32_t x154 = x153 + 2;
int32_t x274 = x214[x154];
int32_t x156 = x153 + 1;
int32_t x275 = x214[x156];
int32_t x276 = x214[x153];
int32_t x162 = x151 + x87;
int32_t x163 = 3 * x162;
int32_t x164 = x163 + 2;
int32_t x277 = x214[x164];
int32_t x166 = x163 + 1;
int32_t x278 = x214[x166];
int32_t x279 = x214[x163];
int32_t x172 = x151 + x96;
int32_t x173 = 3 * x172;
int32_t x174 = x173 + 2;
int32_t x280 = x214[x174];
int32_t x176 = x173 + 1;
int32_t x281 = x214[x176];
int32_t x282 = x214[x173];
int32_t x189 = x120 + x188;
int32_t x190 = 3 * x189;
int32_t x191 = x190 + 2;
int32_t x283 = x256 + x259;
int32_t x284 = x283 + x262;
int32_t x285 = x284 + x265;
int32_t x286 = x285 + x268;
int32_t x287 = x286 + x271;
int32_t x288 = x287 + x274;
int32_t x289 = x288 + x277;
int32_t x290 = x289 + x280;
int32_t x291 = x290 / 18;
UCHAR x292 = (UCHAR) x291;
x213[x191] = x292;
int32_t x193 = x190 + 1;
int32_t x294 = x257 + x260;
int32_t x295 = x294 + x263;
int32_t x296 = x295 + x266;
int32_t x297 = x296 + x269;
int32_t x298 = x297 + x272;
int32_t x299 = x298 + x275;
int32_t x300 = x299 + x278;
int32_t x301 = x300 + x281;
int32_t x302 = x301 / 18;
UCHAR x303 = (UCHAR) x302;
x213[x193] = x303;
int32_t x305 = x258 + x261;
int32_t x306 = x305 + x264;
int32_t x307 = x306 + x267;
int32_t x308 = x307 + x270;
int32_t x309 = x308 + x273;
int32_t x310 = x309 + x276;
int32_t x311 = x310 + x279;
int32_t x312 = x311 + x282;
int32_t x313 = x312 / 18;
UCHAR x314 = (UCHAR) x313;
x213[x190] = x314;

}
free(x214);

}
free(x205);
memcpy(x1, x213, x36);
free(x213);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
