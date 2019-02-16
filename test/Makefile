CC=g++
FLAGS = -g -fpermissive -I ~/src/halide/include -msse -msse2 -mavx -mavx2
SRC_FILES := $(wildcard $(TARGET)/*)

compile: benchmark.c
	$(CC) $(FLAGS) -o pipe $(SRC_FILES) benchmark.c -L~/src/halide

benchmark: benchmark.c
	$(CC) $(FLAGS) -o bm $(SRC_FILES) benchmark.c -L~/src/halide
