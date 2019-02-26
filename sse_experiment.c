#include <x86intrin.h>
#include <immintrin.h>
#include <stdio.h>

int main() {
  /*__m256 evens = _mm256_set_ps(2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0);
  __m256 odds = _mm256_set_ps(1.0, 3.0, 5.0, 7.0, 9.0, 11.0, 13.0, 15.0);

  __m256 result = _mm256_sub_ps(evens, odds);

  float arr[16] = {1.0f, 2.0f, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
  _mm256_storeu_ps(arr + 8, result);

  for (int i = 0; i < 16; i++) {
    printf("%f\n", arr[i]);
  }*/

  /*int32_t *vals = malloc(sizeof(int32_t) * 8);
  for (int i = 0; i < 8; i++) vals[i] = i;
  __m256i vec = _mm256_loadu_si256((__m256i const *) (vals));
  int32_t *initialLoad = malloc(sizeof(int32_t) * 8);
  _mm256_storeu_si256((__m256i *) initialLoad, vec);
  printf("Loading values: %d, %d, %d, %d, %d, %d, %d, %d\n", initialLoad[0], initialLoad[1], initialLoad[2], initialLoad[3], initialLoad[4],
         initialLoad[5], initialLoad[6], initialLoad[7]);*/
  /*char bytes[16] = {1, 2, 3, 4, 5, 6, 7, 8,
                   1, 2, 3, 4, 5, 6, 7, 8};
  __m128i byte_vector = _mm_loadu_si128((__m128i const *) bytes);
  __m256i vec = _mm256_cvtepu8_epi16(byte_vector);
  int16_t *initialLoad = malloc(sizeof(int16_t) * 16);
  _mm256_storeu_si256((__m256i *) initialLoad, vec);

  printf("Loading values: %d, %d, %d, %d, %d, %d, %d, %d\n", initialLoad[0], initialLoad[1], initialLoad[2], initialLoad[3], initialLoad[4],
         initialLoad[5], initialLoad[6], initialLoad[7]);*/
 /*__m256 vecf = _mm256_castsi256_ps(vec);
 float *floatingStore = malloc(sizeof(float) * 8);
 _mm256_storeu_ps(floatingStore, vecf);
 printf("Floating values: %f, %f, %f, %f, %f, %f, %f, %f\n",
        vecf[0], vecf[1], vecf[2], vecf[3],
        vecf[4], vecf[5], vecf[6], vecf[7]);*/

 __m256i powers =  _mm256_setr_epi16(1, 2, 4, 8, 16, 32, 64, 128,
                                     1, 2, 4, 8, 16, 32, 64, 128);
char *bytes = malloc(sizeof(char) * 16);
for (int i = 0; i < 16; i++) bytes[i] = 0;
__m256i vector = _mm256_setr_epi16(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
__m256i mask = _mm256_setr_epi64x(0, 0, 0, 0);
__m256i truncated = _mm256_packus_epi16(vector, mask);
truncated = _mm256_permute4x64_epi64(truncated, 0b11011000);
__m128i new_bytes = _mm256_extracti128_si256(truncated, 0);
/*__m256i truncated = _mm256_castps_si256(_mm256_and_ps(_mm256_castsi256_ps(vector), _mm256_castsi256_ps(mask)));
__m256i shuffle_mask = _mm256_setr_epi8(0, 31, 1, 31, 2, 31, 3, 31, 4, 31, 5, 31, 6, 31, 7, 31,
                                        8, 31, 9, 31, 10, 31, 11, 31, 12, 31, 13, 31, 14, 31, 15, 31);
*/
//truncated = _mm256_shuffle_epi8(truncated, shuffle_mask);

_mm_storeu_si128((__m128i *)bytes, new_bytes);
printf("Stored values: %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d\n", bytes[0], bytes[1],
        bytes[2], bytes[3], bytes[4], bytes[5], bytes[6], bytes[7], bytes[8], bytes[9],
        bytes[10], bytes[11], bytes[12], bytes[13], bytes[14], bytes[15]);
/*__m256i zeroes = _mm256_set_epi64x(0, 0, 0, 0);
__m256i powers_less_one = _mm256_alignr_epi8(zeroes, powers, 1);*/
/*__m128i first_half = _mm256_extractf128_si256(powers, 0);
__m128i second_half = _mm256_extractf128_si256(powers, 0);
__m128i first_half_shifted = _mm256_srli_epi16(first_half, 1);
__m128i second_half_shifted = _mm256_srli_epi16(second_half, 1);
__m256i powers_less_one = _mm256_castsi128_si256(first_half_shifted);*/
//__m256i powers_less_one = _mm256_srli_epi16(powers, 1);
//powers_less_one = _mm256_insertf128_si256(powers_less_one, second_half_shifted, 1);

/*unsigned short *plo = (unsigned short *) &powers_less_one;
printf("Powers less one: %d %d %d %d %d %d %d %d\n %d %d %d %d %d %d %d %d\n",
       plo[0], plo[1], plo[2], plo[3], plo[4], plo[5], plo[6], plo[7],
       plo[8], plo[9], plo[10], plo[11], plo[12], plo[13], plo[14], plo[15]);
 */
 //__m256 x188 = _mm256_div_ps(x186, x187)
  /*int32_t *initialLoad = malloc(sizeof(int32_t) * 8);
  _mm256_storeu_si256((__m256i *) initialLoad, x181);

  /*float* f = (float*)&result;
  printf("%f %f %f %f %f %f %f %f\n",
   f[0], f[1], f[2], f[3], f[4], f[5], f[6], f[7]);*/

  /*int int_array[8] = {100, 200, 300, 400, 500, 600, 700, 800};

   __m256i mask = _mm256_setr_epi32(-20, -72, -48, -9, -1, 3, 5, 8);
   __m256i result = _mm256_maskload_epi32(int_array, mask);

   int *res = (int *)&result;
   printf("%d %d %d %d %d %d %d %d\n",
    res[0], res[1], res[2], res[3], res[4], res[5], res[6], res[7]);

    /*for (int y = 0; y < 4; y++) {
      for (int x_outer = 0; x_outer < 2; x_outer++) {
        __m256i x_vec = _mm256_set_epi32(
                       x_outer,
                       x_outer,
                       x_outer,
                       x_outer, 0, 0, 0, 0);
        __m256i x_vec2 = _mm256_add_epi32(x_vec, _mm256_set_epi32(0, 1, 2, 3, 0, 0, 0, 0));
        x_vec2 = _mm256_add_epi32(x_vec2, _mm256_set1_epi32(y));
        //
        /*int val[] = {x_vec[0] + y,
                     x_vec[1] + y,
                     x_vec[2] + y,
                     x_vec[3] + y};
        int *val = (int *)&x_vec2;
        printf("vals: <%d, %d, %d, %d>\n", val[7], val[6], val[5], val[4]);

      }
    }*/

  return 0;
}
