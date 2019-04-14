#include <sys/time.h>
#include <stdio.h>


double benchmark(void (*f)(), int min_iterations,
                 int max_iterations, double accuracy) {
  double current_best = 99999999;
  double second_best = current_best * 2;


  for (int i = 0; i < max_iterations; i++) {
    if (i > min_iterations && (second_best - current_best) / second_best < accuracy) break;
    printf("Iteration number: %d\n", i);
    struct timeval start, end, result;
    gettimeofday(&start, NULL);
    f();
    gettimeofday(&end, NULL);
    timersub(&end, &start, &result);
    double time_elapsed = (long int) result.tv_sec + ((double) result.tv_usec) / 1000000;
    //printf("Time elapsed: %ld.%06ld\n", (long int)result.tv_sec, (long int)result.tv_usec);
    //printf("time_elapsed: %f\n", time_elapsed);
    if (time_elapsed < current_best) {
      double tmp = current_best;
      current_best = time_elapsed;
      second_best = tmp;
    }
  }
  //printf("Second best: %f\n", second_best);
  return current_best;
}
