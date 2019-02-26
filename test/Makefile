CC=g++
FLAGS = -g -fpermissive -I ~/Halide/include -msse -msse2 -mavx -mavx2
HALIDE_FLAGS = -g -I ~/Halide/include -I ~/Halide/tools -L ~/Halide/bin -lHalide `libpng-config --cflags --ldflags` -ljpeg -lpthread -ldl -o halide_prog  -std=c++11
SRC_FILES := $(wildcard $(TARGET)/*)
SRC_FILES := $(filter-out $(wildcard $(TARGET)/*_generator.cpp), $(SRC_FILES))

compile: benchmark.c
	$(CC) $(FLAGS) -o pipe $(SRC_FILES) benchmark.c -L~/src/halide

benchmark: benchmark.c
	$(CC) $(FLAGS) -o bm $(SRC_FILES) benchmark.c $(HALIDE_FLAGS)

halide:
	$(CC) $(TARGET) ~/Halide/tools/GenGen.cpp $(HALIDE_FLAGS)
halide_gen:
	LD_LIBRARY_PATH=~/Halide/bin/:$LD_LIBRARY_PATH  ./halide_prog -g halide_blur -o $(TARGET) target=host
