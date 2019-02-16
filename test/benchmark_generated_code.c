#include <time.h>
#include <stdio.h>
#include "benchmark.h"
#include "../testOutput/pipeline.h"

void test() {
  pipeline(in, out, 4000, 3000);
}
