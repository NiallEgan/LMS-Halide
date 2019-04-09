/*
Copyright (c) 2012-2018 MIT CSAIL, Google Inc., and other contributors

Developed by:

  The Halide team
  http://halide-lang.org

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
of the Software, and to permit persons to whom the Software is furnished to do
so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

#include <cmath>
#include <cstdint>
#include <cstdio>
#ifdef __SSE2__
#include <emmintrin.h>
#elif __ARM_NEON
#include <arm_neon.h>
#endif

#include "../benchmark.h"
#include "HalideBuffer.h"

using namespace Halide::Runtime;
//using namespace Halide::Tools;

double t;

Buffer<uint16_t> blur(Buffer<uint16_t> in, Buffer<uint16_t> out) {
    Buffer<uint16_t> tmp(in.width()-8, in.height());
    for (int y = 0; y < tmp.height(); y++)
        for (int x = 0; x < tmp.width(); x++)
            tmp(x, y) = (in(x, y) + in(x+1, y) + in(x+2, y))/3;

    for (int y = 0; y < out.height(); y++)
        for (int x = 0; x < out.width(); x++)
            out(x, y) = (tmp(x, y) + tmp(x, y+1) + tmp(x, y+2))/3;
    return out;
}


void test_clean() {
  blur(in, out);
}
