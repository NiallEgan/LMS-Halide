#include <string.h>
#include "../../testOutput/pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x7 = x4 - 1;
int32_t x10 = x7 * x3;
int32_t x11 = x10 * 3;
uint16_t *x155 = malloc(sizeof(uint16_t) * x11);
int32_t x5 = x3 - 1;
int32_t x13 = x5 - 1;
int32_t x14 = x7 * x13;
int32_t x15 = x14 * 3;
UCHAR *x156 = malloc(sizeof(UCHAR) * x15);
int32_t x24 = x7 + 16;
int32_t x25 = x24 - 1;
int32_t x26 = x25 / 16;
int32_t x6 = x4 - 16;
int32_t x36 = x6 - 1;
__m256i x190 = _mm256_set_epi16(21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846);
__m256i x317 = _mm256_set_epi64x(0L, 0L, 0L, 0L);
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
int32_t x186 = x37 + x81;
int32_t x187 = 3 * x186;
int32_t x189 = x187 + 32;
int32_t x157 = x37 + 1;
int32_t x158 = x157 + x40;
int32_t x159 = 3 * x158;
int32_t x161 = x159 + 32;
__m128i x191 = _mm_loadu_si128((__m128i const*) (x0 + x161));
__m256i x192 = _mm256_cvtepu8_epi16(x191);
int32_t x166 = x157 + 1;
int32_t x167 = x166 + x40;
int32_t x168 = 3 * x167;
int32_t x170 = x168 + 32;
__m128i x193 = _mm_loadu_si128((__m128i const*) (x0 + x170));
__m256i x194 = _mm256_cvtepu8_epi16(x193);
__m256i x195 = _mm256_add_epi16(x192, x194);
int32_t x176 = x37 + x40;
int32_t x177 = 3 * x176;
int32_t x179 = x177 + 32;
__m128i x196 = _mm_loadu_si128((__m128i const*) (x0 + x179));
__m256i x197 = _mm256_cvtepu8_epi16(x196);
__m256i x198 = _mm256_add_epi16(x195, x197);
__m256i x199 = _mm256_mulhi_epi16(x198, x190);
__m256i x200 = _mm256_sub_epi16(x198, x199);
__m256i x201 = _mm256_srli_epi16(x200, 1);
__m256i x202 = _mm256_add_epi16(x199, x201);
__m256i x203 = _mm256_srli_epi16(x202, 1);
int32_t x227 = x187 + 16;
int32_t x206 = x159 + 16;
__m128i x228 = _mm_loadu_si128((__m128i const*) (x0 + x206));
__m256i x229 = _mm256_cvtepu8_epi16(x228);
int32_t x212 = x168 + 16;
__m128i x230 = _mm_loadu_si128((__m128i const*) (x0 + x212));
__m256i x231 = _mm256_cvtepu8_epi16(x230);
__m256i x232 = _mm256_add_epi16(x229, x231);
int32_t x219 = x177 + 16;
__m128i x233 = _mm_loadu_si128((__m128i const*) (x0 + x219));
__m256i x234 = _mm256_cvtepu8_epi16(x233);
__m256i x235 = _mm256_add_epi16(x232, x234);
__m256i x236 = _mm256_mulhi_epi16(x235, x190);
__m256i x237 = _mm256_sub_epi16(x235, x236);
__m256i x238 = _mm256_srli_epi16(x237, 1);
__m256i x239 = _mm256_add_epi16(x236, x238);
__m256i x240 = _mm256_srli_epi16(x239, 1);
__m128i x251 = _mm_loadu_si128((__m128i const*) (x0 + x159));
__m256i x252 = _mm256_cvtepu8_epi16(x251);
__m128i x253 = _mm_loadu_si128((__m128i const*) (x0 + x168));
__m256i x254 = _mm256_cvtepu8_epi16(x253);
__m256i x255 = _mm256_add_epi16(x252, x254);
__m128i x256 = _mm_loadu_si128((__m128i const*) (x0 + x177));
__m256i x257 = _mm256_cvtepu8_epi16(x256);
__m256i x258 = _mm256_add_epi16(x255, x257);
__m256i x259 = _mm256_mulhi_epi16(x258, x190);
__m256i x260 = _mm256_sub_epi16(x258, x259);
__m256i x261 = _mm256_srli_epi16(x260, 1);
__m256i x262 = _mm256_add_epi16(x259, x261);
__m256i x263 = _mm256_srli_epi16(x262, 1);
if (x32) {
_mm256_storeu_si256((__m256i *) (x155 + x189), x203);
_mm256_storeu_si256((__m256i *) (x155 + x227), x240);
_mm256_storeu_si256((__m256i *) (x155 + x187), x263);
} else {
}

}

}
int32_t x105 = x7 * x18;
int32_t x113 = x7 * x20;
int32_t x124 = x18 - 1;
int32_t x125 = x7 * x124;
for(int x97=0; x97 < x26; x97++) {
int32_t x99 = x97 * 16;
int32_t x100 = x99 + 1;
bool x101 = x100 > x6;
int32_t x102;
if (x101) {
x102 = x36;
} else {
x102 = x99;
}
int32_t x273 = x102 + x105;
int32_t x274 = 3 * x273;
int32_t x276 = x274 + 32;
int32_t x279 = x274 + 16;
int32_t x282 = x102 + x113;
int32_t x283 = 3 * x282;
int32_t x285 = x283 + 32;
int32_t x288 = x283 + 16;
int32_t x291 = x102 + x125;
int32_t x292 = 3 * x291;
int32_t x294 = x292 + 32;
int32_t x297 = x292 + 16;
uint16_t x277 = x155[x276];
uint16_t x280 = x155[x279];
uint16_t x281 = x155[x274];
uint16_t x286 = x155[x285];
uint16_t x289 = x155[x288];
uint16_t x290 = x155[x283];
uint16_t x295 = x155[x294];
uint16_t x298 = x155[x297];
uint16_t x299 = x155[x292];
// loadu
__m256i x305 = _mm256_loadu_si256((__m256i const *) (x155 + x276));
// loadu
__m256i x307 = _mm256_loadu_si256((__m256i const *) (x155 + x285));
// loadu
__m256i x310 = _mm256_loadu_si256((__m256i const *) (x155 + x294));
__m256i x308 = _mm256_add_epi16(x305, x307);
__m256i x311 = _mm256_add_epi16(x308, x310);
__m256i x312 = _mm256_mulhi_epi16(x311, x190);
__m256i x313 = _mm256_sub_epi16(x311, x312);
__m256i x314 = _mm256_srli_epi16(x313, 1);
__m256i x315 = _mm256_add_epi16(x312, x314);
__m256i x316 = _mm256_srli_epi16(x315, 1);
__m256i x318 = _mm256_packus_epi16(x316, x317);
__m256i x319 = _mm256_permute4x64_epi64(x318, 216);
__m128i x320 = _mm256_extracti128_si256(x319, 0);
_mm_storeu_si128((__m128i*) (x156 + x294), x320);
// loadu
__m256i x327 = _mm256_loadu_si256((__m256i const *) (x155 + x279));
// loadu
__m256i x329 = _mm256_loadu_si256((__m256i const *) (x155 + x288));
// loadu
__m256i x332 = _mm256_loadu_si256((__m256i const *) (x155 + x297));
__m256i x330 = _mm256_add_epi16(x327, x329);
__m256i x333 = _mm256_add_epi16(x330, x332);
__m256i x334 = _mm256_mulhi_epi16(x333, x190);
__m256i x335 = _mm256_sub_epi16(x333, x334);
__m256i x336 = _mm256_srli_epi16(x335, 1);
__m256i x337 = _mm256_add_epi16(x334, x336);
__m256i x338 = _mm256_srli_epi16(x337, 1);
__m256i x339 = _mm256_packus_epi16(x338, x317);
__m256i x340 = _mm256_permute4x64_epi64(x339, 216);
__m128i x341 = _mm256_extracti128_si256(x340, 0);
_mm_storeu_si128((__m128i*) (x156 + x297), x341);
// loadu
__m256i x348 = _mm256_loadu_si256((__m256i const *) (x155 + x274));
// loadu
__m256i x350 = _mm256_loadu_si256((__m256i const *) (x155 + x283));
// loadu
__m256i x353 = _mm256_loadu_si256((__m256i const *) (x155 + x292));
__m256i x351 = _mm256_add_epi16(x348, x350);
__m256i x354 = _mm256_add_epi16(x351, x353);
__m256i x355 = _mm256_mulhi_epi16(x354, x190);
__m256i x356 = _mm256_sub_epi16(x354, x355);
__m256i x357 = _mm256_srli_epi16(x356, 1);
__m256i x358 = _mm256_add_epi16(x355, x357);
__m256i x359 = _mm256_srli_epi16(x358, 1);
__m256i x360 = _mm256_packus_epi16(x359, x317);
__m256i x361 = _mm256_permute4x64_epi64(x360, 216);
__m128i x362 = _mm256_extracti128_si256(x361, 0);
_mm_storeu_si128((__m128i*) (x156 + x292), x362);

}

}
free(x155);
memcpy(x1, x156, x15);
free(x156);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
