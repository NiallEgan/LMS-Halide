#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>
#define N 16

uint16_t muluh(uint16_t a, uint16_t b) {
  printf("a: %" PRIu16 "\n", a);
  printf("b: %" PRIu16 "\n", b);
  uint32_t prod = ((uint32_t) a) * ((uint32_t) b);
  printf("Prod: %" PRIu32 "\n", prod);
  uint64_t high_half = prod >> N;
  printf("High half: %d\n", high_half);
  return (uint16_t) high_half;

}

uint8_t ilog(uint16_t i)
{
  uint8_t result = 0;
  while (i >>= 1)
  {
    //printf("i: %d\n", i);
    result++;
  }
  return result + 1;
}

uint16_t divide(uint16_t d, uint16_t n) {
  uint8_t l = ilog(d);
  printf("l: %d\n", l);
  uint32_t x = (65536UL / d);
  uint32_t y =  ((1 << l) - d);
  uint16_t m_ = ((uint16_t) x) * ((uint16_t) y) + 1;
  printf("x: %lu\n", (unsigned long) x);
  printf("y: %lu\n", (unsigned long) y);
  printf("m_: %lu\n", (unsigned long) m_);
  int sh1 = (l < 1) ? l : 1;//min(l , 1);
  int sh2 = (l-1 > 0) ? l-1 : 0;
  //
  uint16_t t1 = muluh((uint16_t)m_, n);
  printf("t1: %d\n", t1);
  uint16_t q = (t1 + ((n - t1) >> sh1)) >> sh2;
  return q;

}

int main() {
  // 374, 7
  uint16_t q = divide(3, 128);
  printf("q: %u\n", (unsigned int) q);
}
