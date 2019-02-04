#include <string.h>
#include "pipeline.h"
void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 - 1;
int32_t x13 = x4 - 1;
int32_t x5 = x3 - 1;
int32_t x14 = x5 - 1;
int32_t x15 = x13 * x14;
int32_t x16 = x15 * 3;
UCHAR *x174 = malloc(sizeof(UCHAR) * x16);
for(int x19=1; x19 < x5; x19++) {

}
memcpy(x1, x174, x16);
free(x174);
}
int32_t WIDTH_OUT_DIFF = 2;
int32_t HEIGHT_OUT_DIFF = 2;
