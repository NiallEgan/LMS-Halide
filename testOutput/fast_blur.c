#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x7 = x4 - 1;
int32_t x10 = x7 * x3;
int32_t x11 = x10 * 3;
uint16_t *x148 = malloc(sizeof(uint16_t) * x11);
int32_t x15 = x7 + 16;
int32_t x16 = x15 - 1;
int32_t x17 = x16 / 16;
int32_t x6 = x4 - 16;
int32_t x24 = x6 - 1;
__m256i x182 = _mm256_set_epi16(21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846);
for(int x14=0; x14 < x3; x14++) {
int32_t x69 = x7 * x14;
int32_t x28 = x2 * x14;
for(int x19=0; x19 < x17; x19++) {
int32_t x21 = x19 * 16;
int32_t x22 = x21 + 1;
bool x23 = x22 > x6;
int32_t x25;
if (x23) {
x25 = x24;
} else {
x25 = x21;
}
int32_t x178 = x25 + x69;
int32_t x179 = 3 * x178;
int32_t x181 = x179 + 32;
int32_t x149 = x25 + 1;
int32_t x150 = x149 + x28;
int32_t x151 = 3 * x150;
int32_t x153 = x151 + 32;
__m128i x183 = _mm_loadu_si128((__m128i const*) (x0 + x153));
__m256i x184 = _mm256_cvtepu8_epi16(x183);
int32_t x158 = x149 + 1;
int32_t x159 = x158 + x28;
int32_t x160 = 3 * x159;
int32_t x162 = x160 + 32;
__m128i x185 = _mm_loadu_si128((__m128i const*) (x0 + x162));
__m256i x186 = _mm256_cvtepu8_epi16(x185);
__m256i x187 = _mm256_add_epi16(x184, x186);
int32_t x168 = x25 + x28;
int32_t x169 = 3 * x168;
int32_t x171 = x169 + 32;
__m128i x188 = _mm_loadu_si128((__m128i const*) (x0 + x171));
__m256i x189 = _mm256_cvtepu8_epi16(x188);
__m256i x190 = _mm256_add_epi16(x187, x189);
__m256i x191 = _mm256_mulhi_epi16(x190, x182);
__m256i x192 = _mm256_sub_epi16(x190, x191);
__m256i x193 = _mm256_srli_epi16(x192, 1);
__m256i x194 = _mm256_add_epi16(x191, x193);
__m256i x195 = _mm256_srli_epi16(x194, 1);
int32_t x219 = x179 + 16;
int32_t x198 = x151 + 16;
__m128i x220 = _mm_loadu_si128((__m128i const*) (x0 + x198));
__m256i x221 = _mm256_cvtepu8_epi16(x220);
int32_t x204 = x160 + 16;
__m128i x222 = _mm_loadu_si128((__m128i const*) (x0 + x204));
__m256i x223 = _mm256_cvtepu8_epi16(x222);
__m256i x224 = _mm256_add_epi16(x221, x223);
int32_t x211 = x169 + 16;
__m128i x225 = _mm_loadu_si128((__m128i const*) (x0 + x211));
__m256i x226 = _mm256_cvtepu8_epi16(x225);
__m256i x227 = _mm256_add_epi16(x224, x226);
__m256i x228 = _mm256_mulhi_epi16(x227, x182);
__m256i x229 = _mm256_sub_epi16(x227, x228);
__m256i x230 = _mm256_srli_epi16(x229, 1);
__m256i x231 = _mm256_add_epi16(x228, x230);
__m256i x232 = _mm256_srli_epi16(x231, 1);
__m128i x243 = _mm_loadu_si128((__m128i const*) (x0 + x151));
__m256i x244 = _mm256_cvtepu8_epi16(x243);
__m128i x245 = _mm_loadu_si128((__m128i const*) (x0 + x160));
__m256i x246 = _mm256_cvtepu8_epi16(x245);
__m256i x247 = _mm256_add_epi16(x244, x246);
__m128i x248 = _mm_loadu_si128((__m128i const*) (x0 + x169));
__m256i x249 = _mm256_cvtepu8_epi16(x248);
__m256i x250 = _mm256_add_epi16(x247, x249);
__m256i x251 = _mm256_mulhi_epi16(x250, x182);
__m256i x252 = _mm256_sub_epi16(x250, x251);
__m256i x253 = _mm256_srli_epi16(x252, 1);
__m256i x254 = _mm256_add_epi16(x251, x253);
__m256i x255 = _mm256_srli_epi16(x254, 1);
_mm256_storeu_si256((__m256i *) (x148 + x181), x195);
_mm256_storeu_si256((__m256i *) (x148 + x219), x232);
_mm256_storeu_si256((__m256i *) (x148 + x179), x255);

}

}
int32_t x5 = x3 - 1;
int32_t x83 = x5 - 1;
int32_t x84 = x7 * x83;
int32_t x85 = x84 * 3;
UCHAR *x263 = malloc(sizeof(UCHAR) * x85);
__m256i x308 = _mm256_set_epi64x(0L, 0L, 0L, 0L);
for(int x88=1; x88 < x5; x88++) {
int32_t x97 = x7 * x88;
int32_t x105 = x88 + 1;
int32_t x106 = x7 * x105;
int32_t x117 = x88 - 1;
int32_t x118 = x7 * x117;
for(int x89=0; x89 < x17; x89++) {
int32_t x91 = x89 * 16;
int32_t x92 = x91 + 1;
bool x93 = x92 > x6;
int32_t x94;
if (x93) {
x94 = x24;
} else {
x94 = x91;
}
int32_t x264 = x94 + x97;
int32_t x265 = 3 * x264;
int32_t x267 = x265 + 32;
int32_t x270 = x265 + 16;
int32_t x273 = x94 + x106;
int32_t x274 = 3 * x273;
int32_t x276 = x274 + 32;
int32_t x279 = x274 + 16;
int32_t x282 = x94 + x118;
int32_t x283 = 3 * x282;
int32_t x285 = x283 + 32;
int32_t x288 = x283 + 16;
uint16_t x268 = x148[x267];
uint16_t x271 = x148[x270];
uint16_t x272 = x148[x265];
uint16_t x277 = x148[x276];
uint16_t x280 = x148[x279];
uint16_t x281 = x148[x274];
uint16_t x286 = x148[x285];
uint16_t x289 = x148[x288];
uint16_t x290 = x148[x283];
// loadu
__m256i x296 = _mm256_loadu_si256((__m256i const *) (x148 + x267));
// loadu
__m256i x298 = _mm256_loadu_si256((__m256i const *) (x148 + x276));
// loadu
__m256i x301 = _mm256_loadu_si256((__m256i const *) (x148 + x285));
__m256i x299 = _mm256_add_epi16(x296, x298);
__m256i x302 = _mm256_add_epi16(x299, x301);
__m256i x303 = _mm256_mulhi_epi16(x302, x182);
__m256i x304 = _mm256_sub_epi16(x302, x303);
__m256i x305 = _mm256_srli_epi16(x304, 1);
__m256i x306 = _mm256_add_epi16(x303, x305);
__m256i x307 = _mm256_srli_epi16(x306, 1);
__m256i x309 = _mm256_packus_epi16(x307, x308);
__m256i x310 = _mm256_permute4x64_epi64(x309, 216);
__m128i x311 = _mm256_extracti128_si256(x310, 0);
_mm_storeu_si128((__m128i*) (x263 + x285), x311);
// loadu
__m256i x318 = _mm256_loadu_si256((__m256i const *) (x148 + x270));
// loadu
__m256i x320 = _mm256_loadu_si256((__m256i const *) (x148 + x279));
// loadu
__m256i x323 = _mm256_loadu_si256((__m256i const *) (x148 + x288));
__m256i x321 = _mm256_add_epi16(x318, x320);
__m256i x324 = _mm256_add_epi16(x321, x323);
__m256i x325 = _mm256_mulhi_epi16(x324, x182);
__m256i x326 = _mm256_sub_epi16(x324, x325);
__m256i x327 = _mm256_srli_epi16(x326, 1);
__m256i x328 = _mm256_add_epi16(x325, x327);
__m256i x329 = _mm256_srli_epi16(x328, 1);
__m256i x330 = _mm256_packus_epi16(x329, x308);
__m256i x331 = _mm256_permute4x64_epi64(x330, 216);
__m128i x332 = _mm256_extracti128_si256(x331, 0);
_mm_storeu_si128((__m128i*) (x263 + x288), x332);
// loadu
__m256i x339 = _mm256_loadu_si256((__m256i const *) (x148 + x265));
// loadu
__m256i x341 = _mm256_loadu_si256((__m256i const *) (x148 + x274));
// loadu
__m256i x344 = _mm256_loadu_si256((__m256i const *) (x148 + x283));
__m256i x342 = _mm256_add_epi16(x339, x341);
__m256i x345 = _mm256_add_epi16(x342, x344);
__m256i x346 = _mm256_mulhi_epi16(x345, x182);
__m256i x347 = _mm256_sub_epi16(x345, x346);
__m256i x348 = _mm256_srli_epi16(x347, 1);
__m256i x349 = _mm256_add_epi16(x346, x348);
__m256i x350 = _mm256_srli_epi16(x349, 1);
__m256i x351 = _mm256_packus_epi16(x350, x308);
__m256i x352 = _mm256_permute4x64_epi64(x351, 216);
__m128i x353 = _mm256_extracti128_si256(x352, 0);
_mm_storeu_si128((__m128i*) (x263 + x283), x353);

}

}
free(x148);
memcpy(x1, x263, x85);
free(x263);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
