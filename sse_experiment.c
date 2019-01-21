#include <immintrin.h>
#include <stdio.h>

int main() {
  __m256 evens = _mm256_set_ps(2.0, 4.0, 6.0, 8.0, 10.0, 12.0, 14.0, 16.0);
  __m256 odds = _mm256_set_ps(1.0, 3.0, 5.0, 7.0, 9.0, 11.0, 13.0, 15.0);

  /*__m256 result = _mm256_sub_ps(evens, odds);

  float* f = (float*)&result;
  printf("%f %f %f %f %f %f %f %f\n",
   f[0], f[1], f[2], f[3], f[4], f[5], f[6], f[7]);*/

   int int_array[8] = {100, 200, 300, 400, 500, 600, 700, 800};

   __m256i mask = _mm256_setr_epi32(-20, -72, -48, -9, -1, 3, 5, 8);
   __m256i result = _mm256_maskload_epi32(int_array, mask);

   int *res = (int *)&result;
   printf("%d %d %d %d %d %d %d %d\n",
    res[0], res[1], res[2], res[3], res[4], res[5], res[6], res[7]);

  return 0;
}
