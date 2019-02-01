#include <stdio.h>
#include <immintrin.h>

int main() {
  int32_t *x8 = malloc(sizeof(int32_t) * 8);
  __m256i x11 = _mm256_set_epi32(7, 6, 5, 4, 3, 2, 1, 0);
  __m256i x12 = _mm256_set_epi32(2, 2, 2, 2, 2, 2, 2, 2);
  __m256i x13 = _mm256_sub_epi32(x11, x12);
  _mm256_storeu_si256((__m256i *) (x8), x13);
  int32_t x17 = x8[1];
  printf("%d\n", x17);

  return 0;
}
