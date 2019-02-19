#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>
#define N 32

uint32_t muluh(uint32_t a, uint32_t b) {
  printf("a: %" PRIu32 "\n", a);
  printf("b: %" PRIu32 "\n", b);
  uint64_t prod = ((uint64_t) a) * ((uint64_t) b);
  printf("Prod: %" PRIu64 "\n", prod);
  uint64_t high_half = prod >> 32;
  printf("High half: %d\n", high_half);
  return (uint32_t) high_half;

}

uint8_t ilog(uint32_t i)
{
  uint8_t result = 0;
  while (i >>= 1)
  {
    //printf("i: %d\n", i);
    result++;
  }
  return result + 1;
}

uint32_t divide(uint32_t d, uint32_t n) {
  uint8_t l = ilog(d);
  printf("l: %d\n", l);
  uint64_t x = (4294967296UL / d);
  uint64_t y =  ((1 << l) - d);
  uint32_t m_ = ((uint32_t) x) * ((uint32_t) y) + 1;
  printf("x: %lu\n", (unsigned long) x);
  printf("y: %lu\n", (unsigned long) y);
  printf("m_: %lu\n", (unsigned long) m_);
  int sh1 = (l < 1) ? l : 1;//min(l , 1);
  int sh2 = (l-1 > 0) ? l-1 : 0;
  //
  uint32_t t1 = muluh((uint32_t)m_, n);
  printf("t1: %d\n", t1);
  uint32_t q = (t1 + ((n - t1) >> sh1)) >> sh2;
  return q;

}

int main() {
  // 374, 7
  uint32_t q = divide(3124U, 5000);
  printf("q: %u\n", (unsigned int) q);
}
