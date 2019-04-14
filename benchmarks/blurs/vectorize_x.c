/*
f.computeAt(g, "y")
f.storeRoot()
f.vectorize("x", 16)*/

#include <string.h>
#include "../../testOutput/pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x7 = x4 - 1;
int32_t x10 = x7 * x3;
int32_t x11 = x10 * 3;
uint16_t *x148 = malloc(sizeof(uint16_t) * x11);
int32_t x5 = x3 - 1;
int32_t x13 = x5 - 1;
int32_t x14 = x7 * x13;
int32_t x15 = x14 * 3;
UCHAR *x149 = malloc(sizeof(UCHAR) * x15);
int32_t x24 = x7 + 16;
int32_t x25 = x24 - 1;
int32_t x26 = x25 / 16;
int32_t x6 = x4 - 16;
int32_t x36 = x6 - 1;
__m256i x183 = _mm256_set_epi16(21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846);
for(int x18=1; x18 < x5; x18++) {
int32_t x19 = x18 + -1;
int32_t x20 = x18 + 1;
int32_t x21 = x20 + 1;
for(int x23=x19; x23 < x21; x23++) {
bool x30 = x23 < 2;
bool x31 = x23 == x20;
bool x32 = x30 || x31;
int32_t x81 = x7 * x23;
int32_t x40 = x2 * x23;
for(int x28=0; x28 < x26; x28++) {
int32_t x33 = x28 * 16;
int32_t x34 = x33 + 1;
bool x35 = x34 > x6;
int32_t x37;
if (x35) {
x37 = x36;
} else {
x37 = x33;
}
int32_t x179 = x37 + x81;
int32_t x180 = 3 * x179;
int32_t x182 = x180 + 32;
int32_t x150 = x37 + 1;
int32_t x151 = x150 + x40;
int32_t x152 = 3 * x151;
int32_t x154 = x152 + 32;
__m128i x184 = _mm_loadu_si128((__m128i const*) (x0 + x154));
__m256i x185 = _mm256_cvtepu8_epi16(x184);
int32_t x159 = x150 + 1;
int32_t x160 = x159 + x40;
int32_t x161 = 3 * x160;
int32_t x163 = x161 + 32;
__m128i x186 = _mm_loadu_si128((__m128i const*) (x0 + x163));
__m256i x187 = _mm256_cvtepu8_epi16(x186);
__m256i x188 = _mm256_add_epi16(x185, x187);
int32_t x169 = x37 + x40;
int32_t x170 = 3 * x169;
int32_t x172 = x170 + 32;
__m128i x189 = _mm_loadu_si128((__m128i const*) (x0 + x172));
__m256i x190 = _mm256_cvtepu8_epi16(x189);
__m256i x191 = _mm256_add_epi16(x188, x190);
__m256i x192 = _mm256_mulhi_epi16(x191, x183);
__m256i x193 = _mm256_sub_epi16(x191, x192);
__m256i x194 = _mm256_srli_epi16(x193, 1);
__m256i x195 = _mm256_add_epi16(x192, x194);
__m256i x196 = _mm256_srli_epi16(x195, 1);
int32_t x220 = x180 + 16;
int32_t x199 = x152 + 16;
__m128i x221 = _mm_loadu_si128((__m128i const*) (x0 + x199));
__m256i x222 = _mm256_cvtepu8_epi16(x221);
int32_t x205 = x161 + 16;
__m128i x223 = _mm_loadu_si128((__m128i const*) (x0 + x205));
__m256i x224 = _mm256_cvtepu8_epi16(x223);
__m256i x225 = _mm256_add_epi16(x222, x224);
int32_t x212 = x170 + 16;
__m128i x226 = _mm_loadu_si128((__m128i const*) (x0 + x212));
__m256i x227 = _mm256_cvtepu8_epi16(x226);
__m256i x228 = _mm256_add_epi16(x225, x227);
__m256i x229 = _mm256_mulhi_epi16(x228, x183);
__m256i x230 = _mm256_sub_epi16(x228, x229);
__m256i x231 = _mm256_srli_epi16(x230, 1);
__m256i x232 = _mm256_add_epi16(x229, x231);
__m256i x233 = _mm256_srli_epi16(x232, 1);
__m128i x244 = _mm_loadu_si128((__m128i const*) (x0 + x152));
__m256i x245 = _mm256_cvtepu8_epi16(x244);
__m128i x246 = _mm_loadu_si128((__m128i const*) (x0 + x161));
__m256i x247 = _mm256_cvtepu8_epi16(x246);
__m256i x248 = _mm256_add_epi16(x245, x247);
__m128i x249 = _mm_loadu_si128((__m128i const*) (x0 + x170));
__m256i x250 = _mm256_cvtepu8_epi16(x249);
__m256i x251 = _mm256_add_epi16(x248, x250);
__m256i x252 = _mm256_mulhi_epi16(x251, x183);
__m256i x253 = _mm256_sub_epi16(x251, x252);
__m256i x254 = _mm256_srli_epi16(x253, 1);
__m256i x255 = _mm256_add_epi16(x252, x254);
__m256i x256 = _mm256_srli_epi16(x255, 1);
if (x32) {
_mm256_storeu_si256((__m256i *) (x148 + x182), x196);
_mm256_storeu_si256((__m256i *) (x148 + x220), x233);
_mm256_storeu_si256((__m256i *) (x148 + x180), x256);
} else {
}

}

}
int32_t x100 = x7 * x18;
int32_t x108 = x7 * x20;
int32_t x119 = x18 - 1;
int32_t x120 = x7 * x119;
for(int x98=1; x98 < x4; x98++) {
int32_t x99 = x98 - 1;
int32_t x101 = x99 + x100;
int32_t x102 = 3 * x101;
int32_t x103 = x102 + 2;
uint16_t x266 = x148[x103];
int32_t x105 = x102 + 1;
uint16_t x267 = x148[x105];
uint16_t x268 = x148[x102];
int32_t x109 = x99 + x108;
int32_t x110 = 3 * x109;
int32_t x111 = x110 + 2;
uint16_t x269 = x148[x111];
int32_t x113 = x110 + 1;
uint16_t x270 = x148[x113];
uint16_t x271 = x148[x110];
int32_t x121 = x99 + x120;
int32_t x122 = 3 * x121;
int32_t x123 = x122 + 2;
uint16_t x272 = x148[x123];
int32_t x125 = x122 + 1;
uint16_t x273 = x148[x125];
uint16_t x274 = x148[x122];
uint16_t x275 = x266 + x269;
uint16_t x276 = x275 + x272;
uint16_t x277 = x276 / 3;
UCHAR x278 = (UCHAR) x277;
x149[x123] = x278;
uint16_t x280 = x267 + x270;
uint16_t x281 = x280 + x273;
uint16_t x282 = x281 / 3;
UCHAR x283 = (UCHAR) x282;
x149[x125] = x283;
uint16_t x285 = x268 + x271;
uint16_t x286 = x285 + x274;
uint16_t x287 = x286 / 3;
UCHAR x288 = (UCHAR) x287;
x149[x122] = x288;

}

}
free(x148);
memcpy(x1, x149, x15);
free(x149);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
