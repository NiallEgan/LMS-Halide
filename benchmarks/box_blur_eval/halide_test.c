/*#include <cmath>
#include <cstdint>
#include <cstdio>
#ifdef __SSE2__
#include <emmintrin.h>
#elif __ARM_NEON
#include <arm_neon.h>
#endif

#include "halide_benchmark.h"#include "HalideBuffer.h"

using namespace Halide::Runtime;
using namespace Halide::Tools;


Buffer<uint16_t> blur_halide(Buffer<uint16_t> in) {
    Buffer<uint16_t> out(in.width()-8, in.height()-2);

    // Call it once to initialize the halide runtime stuff
    halide_blur(in, out);
    // Copy-out result if it's device buffer and dirty.
    out.copy_to_host();

    t = benchmark(10, 1, [&]() {
        // Compute the same region of the output as blur_fast (i.e., we're
        // still being sloppy with boundary conditions)

    });

    out.copy_to_host();

    return out;
}*/
