#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <immintrin.h>

#define UCHAR unsigned char
#define MIN(x, y) ((x) < (y) ? (x) : (y))
#define MAX(x, y) ((x) > (y) ? (x) : (y))


void pipeline(UCHAR *x0, UCHAR *x1, int32_t w, int32_t h);
extern int32_t WIDTH_OUT_DIFF;
extern int32_t HEIGHT_OUT_DIFF;
