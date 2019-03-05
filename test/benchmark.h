#include "HalideBuffer.h"
#ifndef BENCHMARK_H
#define BENCHMARK_H

double benchmark(void (*f)(), int min_iterations,
                 int max_iterations, double accuracy);
extern int input_height;
extern int input_width;

extern Halide::Runtime::Buffer<uint8_t> in;
extern Halide::Runtime::Buffer<uint8_t> out;

//void test_clean(void);
//void test_fast(void);

#endif
