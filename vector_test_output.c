#include <immintrin.h>
//#include <smmintrin.h>
#include <stdint.h>
#include <stdio.h>

int main() {
  uint16_t *x25 = malloc(sizeof(uint16_t) * 16);
  x25[0] = 1;
  x25[1] = 2;
  x25[2] = 4;
  x25[3] = 8;
  x25[4] = 16;
  x25[5] = 32;
  x25[6] = 64;
  x25[7] = 128;
  x25[8] = 256;
  x25[9] = 512;
  x25[10] = 1024;
  x25[11] = 2048;
  x25[12] = 4096;
  x25[13] = 8192;
  x25[14] = 16384;
  x25[15] = 32768;
  __m256i x44 = _mm256_set_epi16(21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846, 21846);
  // loadu
  __m256i x46 = _mm256_loadu_si256((__m256i const *) (x25));
  __m256i x47 = _mm256_mulhi_epi16(x46, x44);
  __m256i x48 = _mm256_sub_epi16(x46, x47);
  __m256i x49 = _mm256_srli_epi16(x48, 1);
  __m256i x50 = _mm256_add_epi16(x47, x49);
  __m256i x51 = _mm256_srli_epi16(x50, 1);
  _mm256_storeu_si256((__m256i *) (x25), x51);
  printf("%d %d %d %d %d %d %d %d\n", x25[0], x25[1], x25[2], x25[3], x25[4], x25[5], x25[6], x25[7]);
}
