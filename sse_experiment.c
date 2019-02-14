#include <immintrin.h>
//#include <smmintrin.h>
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
  char bytes[8] = {1, 2, 3, 4, 0, 0, 0, 0};
  __m256i ctrl = _mm256_setr_epi8(0, 255, 255, 255,
                                  1, 255, 255, 255,
                                  2, 255, 255, 255,
                                  3, 255, 255, 255,

                                  255, 255, 255, 255,
                                  255, 255, 255, 255,
                                  255, 255, 255, 255,
                                  255, 255, 255, 255);
  __m256i vec = _mm256_loadu_si256((__m256i const *) (bytes));
  vec = _mm256_shuffle_epi8(vec, ctrl);
  int32_t *initialLoad = malloc(sizeof(int32_t) * 8);
  _mm256_storeu_si256((__m256i *) initialLoad, vec);

  printf("Loading values: %d, %d, %d, %d, %d, %d, %d, %d\n", initialLoad[0], initialLoad[1], initialLoad[2], initialLoad[3], initialLoad[4],
         initialLoad[5], initialLoad[6], initialLoad[7]);
 __m256 vecf = _mm256_castsi256_ps(vec);
 float *floatingStore = malloc(sizeof(float) * 8);
 _mm256_storeu_ps(floatingStore, vecf);
 printf("Floating values: %f, %f, %f, %f, %f, %f, %f, %f\n",
        vecf[0], vecf[1], vecf[2], vecf[3],
        vecf[4], vecf[5], vecf[6], vecf[7]);
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
