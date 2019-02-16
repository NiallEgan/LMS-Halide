#include <time.h>
#include <stdio.h>


double benchmark(void (*f)(), int min_iterations,
                 int max_iterations, double accuracy) {
  double current_best = 99999999;
  double second_best = current_best * 2;


  for (int i = 0; i < max_iterations; i++) {
    if (i > min_iterations && (second_best - current_best) / second_best < accuracy) break;
    printf("Iteration number: %d\n", i);
    clock_t start = clock();
    f();
    clock_t end = clock();
    double cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    if (cpu_time_used < current_best) {
      double tmp = current_best;
      current_best = cpu_time_used;
      second_best = tmp;
    }
  }
  printf("Second best: %f\n", second_best);
  return current_best;
}
