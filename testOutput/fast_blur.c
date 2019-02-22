#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x7 = x4 - 1;
int32_t x10 = x7 * x3;
int32_t x11 = x10 * 3;
uint16_t *x141 = malloc(sizeof(uint16_t) * x11);
int32_t x15 = x7 + 16;
int32_t x16 = x15 - 1;
int32_t x17 = x16 / 16;
int32_t x6 = x4 - 16;
int32_t x24 = x6 - 1;
__m256i x175 = _mm256_set_epi16(21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846);
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
int32_t x171 = x25 + x69;
int32_t x172 = 3 * x171;
int32_t x174 = x172 + 32;
int32_t x142 = x25 + 1;
int32_t x143 = x142 + x28;
int32_t x144 = 3 * x143;
int32_t x146 = x144 + 32;
__m128i x176 = _mm_loadu_si128((__m128i const*) (x0 + x146));
__m256i x177 = _mm256_cvtepu8_epi16(x176);
int32_t x151 = x142 + 1;
int32_t x152 = x151 + x28;
int32_t x153 = 3 * x152;
int32_t x155 = x153 + 32;
__m128i x178 = _mm_loadu_si128((__m128i const*) (x0 + x155));
__m256i x179 = _mm256_cvtepu8_epi16(x178);
__m256i x180 = _mm256_add_epi16(x177, x179);
int32_t x161 = x25 + x28;
int32_t x162 = 3 * x161;
int32_t x164 = x162 + 32;
__m128i x181 = _mm_loadu_si128((__m128i const*) (x0 + x164));
__m256i x182 = _mm256_cvtepu8_epi16(x181);
__m256i x183 = _mm256_add_epi16(x180, x182);
__m256i x184 = _mm256_mulhi_epi16(x183, x175);
__m256i x185 = _mm256_sub_epi16(x183, x184);
__m256i x186 = _mm256_srli_epi16(x185, 1);
__m256i x187 = _mm256_add_epi16(x184, x186);
__m256i x188 = _mm256_srli_epi16(x187, 1);
int32_t x212 = x172 + 16;
int32_t x191 = x144 + 16;
__m128i x213 = _mm_loadu_si128((__m128i const*) (x0 + x191));
__m256i x214 = _mm256_cvtepu8_epi16(x213);
int32_t x197 = x153 + 16;
__m128i x215 = _mm_loadu_si128((__m128i const*) (x0 + x197));
__m256i x216 = _mm256_cvtepu8_epi16(x215);
__m256i x217 = _mm256_add_epi16(x214, x216);
int32_t x204 = x162 + 16;
__m128i x218 = _mm_loadu_si128((__m128i const*) (x0 + x204));
__m256i x219 = _mm256_cvtepu8_epi16(x218);
__m256i x220 = _mm256_add_epi16(x217, x219);
__m256i x221 = _mm256_mulhi_epi16(x220, x175);
__m256i x222 = _mm256_sub_epi16(x220, x221);
__m256i x223 = _mm256_srli_epi16(x222, 1);
__m256i x224 = _mm256_add_epi16(x221, x223);
__m256i x225 = _mm256_srli_epi16(x224, 1);
__m128i x236 = _mm_loadu_si128((__m128i const*) (x0 + x144));
__m256i x237 = _mm256_cvtepu8_epi16(x236);
__m128i x238 = _mm_loadu_si128((__m128i const*) (x0 + x153));
__m256i x239 = _mm256_cvtepu8_epi16(x238);
__m256i x240 = _mm256_add_epi16(x237, x239);
__m128i x241 = _mm_loadu_si128((__m128i const*) (x0 + x162));
__m256i x242 = _mm256_cvtepu8_epi16(x241);
__m256i x243 = _mm256_add_epi16(x240, x242);
__m256i x244 = _mm256_mulhi_epi16(x243, x175);
__m256i x245 = _mm256_sub_epi16(x243, x244);
__m256i x246 = _mm256_srli_epi16(x245, 1);
__m256i x247 = _mm256_add_epi16(x244, x246);
__m256i x248 = _mm256_srli_epi16(x247, 1);
_mm256_storeu_si256((__m256i *) (x141 + x174), x188);
_mm256_storeu_si256((__m256i *) (x141 + x212), x225);
_mm256_storeu_si256((__m256i *) (x141 + x172), x248);

}

}
int32_t x5 = x3 - 1;
int32_t x83 = x5 - 1;
int32_t x84 = x7 * x83;
int32_t x85 = x84 * 3;
UCHAR *x256 = malloc(sizeof(UCHAR) * x85);
for(int x88=1; x88 < x5; x88++) {
int32_t x92 = x7 * x88;
int32_t x100 = x88 + 1;
int32_t x101 = x7 * x100;
int32_t x112 = x88 - 1;
int32_t x113 = x7 * x112;
for(int x90=1; x90 < x4; x90++) {
int32_t x91 = x90 - 1;
int32_t x93 = x91 + x92;
int32_t x94 = 3 * x93;
int32_t x95 = x94 + 2;
uint16_t x257 = x141[x95];
int32_t x97 = x94 + 1;
uint16_t x258 = x141[x97];
uint16_t x259 = x141[x94];
int32_t x102 = x91 + x101;
int32_t x103 = 3 * x102;
int32_t x104 = x103 + 2;
uint16_t x260 = x141[x104];
int32_t x106 = x103 + 1;
uint16_t x261 = x141[x106];
uint16_t x262 = x141[x103];
int32_t x114 = x91 + x113;
int32_t x115 = 3 * x114;
int32_t x116 = x115 + 2;
uint16_t x263 = x141[x116];
int32_t x118 = x115 + 1;
uint16_t x264 = x141[x118];
uint16_t x265 = x141[x115];
uint16_t x266 = x257 + x260;
uint16_t x267 = x266 + x263;
uint16_t x268 = x267 / 3;
UCHAR x269 = (UCHAR) x268;
x256[x116] = x269;
uint16_t x271 = x258 + x261;
uint16_t x272 = x271 + x264;
uint16_t x273 = x272 / 3;
UCHAR x274 = (UCHAR) x273;
x256[x118] = x274;
uint16_t x276 = x259 + x262;
uint16_t x277 = x276 + x265;
uint16_t x278 = x277 / 3;
UCHAR x279 = (UCHAR) x278;
x256[x115] = x279;

}

}
free(x141);
memcpy(x1, x256, x85);
free(x256);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
