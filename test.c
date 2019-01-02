#include <stdio.h>

int main() {
  unsigned int in[25];
  for (int i = 0; i < 25; i++) in[i] = i+1;
  unsigned int out[9];

  for (int y = 1; y < 4; y++) {
    unsigned int temp[5];
    for(int i = 0; i < 5; i++) temp[i] = 29;
    for (int x = 1; x < 4; x++) {
      for(int py = y; py < y + 1; py++) {
        for (int px = x - 1; px < x + 2; px++) {
          if (px < 2 || px == x + 1) {
            printf("Producer calculating %d\n", px + py * 5 + 1);
            temp[px] = in[px + (py - 1) * 5] +
                               in[px + py * 5] +
                               in[px + (py + 1) * 5]; // No need for y here
          }
        }
      }
      printf("Consumer calculating %d\n", x + y * 5 + 1);

      printf("Buffer values: %ud, %ud, %ud, %ud, %ud\n", temp[0], temp[1],
              temp[2], temp[3], temp[4]);
      printf("Operands: %d %d %d\n", temp[x-1], temp[x], temp[x+1]);
      out[x - 1 + (y-1) * 3] = temp[x-1] + temp[x] + temp[x+1];
      printf("Calculated value: %u\n", out[x-1 + (y-1) * 3]);
    }
  }

  for (int i = 0; i < 3; i++) {
    printf("\n");
    for (int j = 0; j < 3; j++) {
      printf("%u ", out[j + i * 3]);
    }
  }
}
