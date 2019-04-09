#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x7 = x4 - 1;
int32_t x10 = x7 * x3;
int32_t x11 = x10 * 3;
uint16_t *x142 = malloc(sizeof(uint16_t) * x11);
int32_t x5 = x3 - 1;
int32_t x13 = x5 * x7;
int32_t x14 = x4 + x13;
for(int x16=1; x16 < x14; x16++) {
int32_t x17 = x16 - 1;
int32_t x18 = x17 % x7;
int32_t x19 = x18 + 1;
int32_t x20 = x17 / x7;
int32_t x21 = x2 * x20;
int32_t x22 = x19 + x21;
int32_t x23 = 3 * x22;
int32_t x24 = x23 + 2;
UCHAR x25 = x0[x24];
uint16_t x37 = (uint16_t) x25;
int32_t x29 = x19 + 1;
int32_t x30 = x29 + x21;
int32_t x31 = 3 * x30;
int32_t x32 = x31 + 2;
UCHAR x33 = x0[x32];
uint16_t x40 = (uint16_t) x33;
uint16_t x43 = x37 + x40;
int32_t x46 = x18 + x21;
int32_t x47 = 3 * x46;
int32_t x48 = x47 + 2;
UCHAR x49 = x0[x48];
uint16_t x53 = (uint16_t) x49;
uint16_t x56 = x43 + x53;
uint16_t x59 = x56 / 3;
int32_t x62 = x7 * x20;
int32_t x63 = x18 + x62;
int32_t x64 = 3 * x63;
int32_t x65 = x64 + 2;
x142[x65] = x59;
int32_t x26 = x23 + 1;
UCHAR x27 = x0[x26];
uint16_t x38 = (uint16_t) x27;
int32_t x34 = x31 + 1;
UCHAR x35 = x0[x34];
uint16_t x41 = (uint16_t) x35;
uint16_t x44 = x38 + x41;
int32_t x50 = x47 + 1;
UCHAR x51 = x0[x50];
uint16_t x54 = (uint16_t) x51;
uint16_t x57 = x44 + x54;
uint16_t x60 = x57 / 3;
int32_t x67 = x64 + 1;
x142[x67] = x60;
UCHAR x28 = x0[x23];
uint16_t x39 = (uint16_t) x28;
UCHAR x36 = x0[x31];
uint16_t x42 = (uint16_t) x36;
uint16_t x45 = x39 + x42;
UCHAR x52 = x0[x47];
uint16_t x55 = (uint16_t) x52;
uint16_t x58 = x45 + x55;
uint16_t x61 = x58 / 3;
x142[x64] = x61;

}
int32_t x72 = x5 - 1;
int32_t x73 = x7 * x72;
int32_t x74 = x73 * 3;
UCHAR *x148 = malloc(sizeof(UCHAR) * x74);
int32_t x78 = x7 + 16;
int32_t x79 = x78 - 1;
int32_t x80 = x79 / 16;
int32_t x6 = x4 - 16;
int32_t x87 = x6 - 1;
__m256i x180 = _mm256_set_epi16(21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846);
__m256i x194 = _mm256_set_epi64x(0L, 0L, 0L, 0L);
for(int x77=1; x77 < x5; x77++) {
int32_t x91 = x7 * x77;
int32_t x99 = x77 + 1;
int32_t x100 = x7 * x99;
int32_t x111 = x77 - 1;
int32_t x112 = x7 * x111;
for(int x82=0; x82 < x80; x82++) {
int32_t x84 = x82 * 16;
int32_t x85 = x84 + 1;
bool x86 = x85 > x6;
int32_t x88;
if (x86) {
x88 = x87;
} else {
x88 = x84;
}
int32_t x149 = x88 + x91;
int32_t x150 = 3 * x149;
int32_t x152 = x150 + 32;
int32_t x155 = x150 + 16;
int32_t x158 = x88 + x100;
int32_t x159 = 3 * x158;
int32_t x161 = x159 + 32;
int32_t x164 = x159 + 16;
int32_t x167 = x88 + x112;
int32_t x168 = 3 * x167;
int32_t x170 = x168 + 32;
int32_t x173 = x168 + 16;
uint16_t x153 = x142[x152];
uint16_t x156 = x142[x155];
uint16_t x157 = x142[x150];
uint16_t x162 = x142[x161];
uint16_t x165 = x142[x164];
uint16_t x166 = x142[x159];
uint16_t x171 = x142[x170];
uint16_t x174 = x142[x173];
uint16_t x175 = x142[x168];
// loadu
__m256i x182 = _mm256_loadu_si256((__m256i const *) (x142 + x152));
// loadu
__m256i x184 = _mm256_loadu_si256((__m256i const *) (x142 + x161));
// loadu
__m256i x187 = _mm256_loadu_si256((__m256i const *) (x142 + x170));
__m256i x185 = _mm256_add_epi16(x182, x184);
__m256i x188 = _mm256_add_epi16(x185, x187);
__m256i x189 = _mm256_mulhi_epi16(x188, x180);
__m256i x190 = _mm256_sub_epi16(x188, x189);
__m256i x191 = _mm256_srli_epi16(x190, 1);
__m256i x192 = _mm256_add_epi16(x189, x191);
__m256i x193 = _mm256_srli_epi16(x192, 1);
__m256i x195 = _mm256_packus_epi16(x193, x194);
__m256i x196 = _mm256_permute4x64_epi64(x195, 216);
__m128i x197 = _mm256_extracti128_si256(x196, 0);
_mm_storeu_si128((__m128i*) (x148 + x170), x197);
// loadu
__m256i x204 = _mm256_loadu_si256((__m256i const *) (x142 + x155));
// loadu
__m256i x206 = _mm256_loadu_si256((__m256i const *) (x142 + x164));
// loadu
__m256i x209 = _mm256_loadu_si256((__m256i const *) (x142 + x173));
__m256i x207 = _mm256_add_epi16(x204, x206);
__m256i x210 = _mm256_add_epi16(x207, x209);
__m256i x211 = _mm256_mulhi_epi16(x210, x180);
__m256i x212 = _mm256_sub_epi16(x210, x211);
__m256i x213 = _mm256_srli_epi16(x212, 1);
__m256i x214 = _mm256_add_epi16(x211, x213);
__m256i x215 = _mm256_srli_epi16(x214, 1);
__m256i x216 = _mm256_packus_epi16(x215, x194);
__m256i x217 = _mm256_permute4x64_epi64(x216, 216);
__m128i x218 = _mm256_extracti128_si256(x217, 0);
_mm_storeu_si128((__m128i*) (x148 + x173), x218);
// loadu
__m256i x225 = _mm256_loadu_si256((__m256i const *) (x142 + x150));
// loadu
__m256i x227 = _mm256_loadu_si256((__m256i const *) (x142 + x159));
// loadu
__m256i x230 = _mm256_loadu_si256((__m256i const *) (x142 + x168));
__m256i x228 = _mm256_add_epi16(x225, x227);
__m256i x231 = _mm256_add_epi16(x228, x230);
__m256i x232 = _mm256_mulhi_epi16(x231, x180);
__m256i x233 = _mm256_sub_epi16(x231, x232);
__m256i x234 = _mm256_srli_epi16(x233, 1);
__m256i x235 = _mm256_add_epi16(x232, x234);
__m256i x236 = _mm256_srli_epi16(x235, 1);
__m256i x237 = _mm256_packus_epi16(x236, x194);
__m256i x238 = _mm256_permute4x64_epi64(x237, 216);
__m128i x239 = _mm256_extracti128_si256(x238, 0);
_mm_storeu_si128((__m128i*) (x148 + x168), x239);

}

}
free(x142);
memcpy(x1, x148, x74);
free(x148);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
