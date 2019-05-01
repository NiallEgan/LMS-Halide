CC=g++
FLAGS =  -O3 -ffast-math -fpermissive -I ~/Halide/include -msse -msse2 -mavx -mavx2
HALIDE_FLAGS = -I ~/Halide/include -I ~/Halide/tools -L ~/Halide/bin -lHalide `libpng-config --cflags --ldflags` -ljpeg -lpthread -ldl  -std=c++11
SRC_FILES := $(wildcard $(TARGET)/*)
SRC_FILES := $(filter-out $(wildcard $(TARGET)/*.registration.cpp), $(SRC_FILES))
SRC_FILES := $(filter-out $(wildcard $(TARGET)/*_generator.cpp), $(SRC_FILES))

compile: benchmark.c
	$(CC) $(FLAGS) -o pipe $(SRC_FILES) benchmark.c -L~/src/halide

benchmark: benchmark.c
	$(CC) $(FLAGS) -g -o bm  benchmark.c $(SRC_FILES) box_blur_eval/halide_blur.a $(HALIDE_FLAGS)
halide:
	$(CC) $(TARGET) ~/Halide/tools/GenGen.cpp $(HALIDE_FLAGS) -o generated
halide_gen:
	LD_LIBRARY_PATH=~/Halide/bin/:$LD_LIBRARY_PATH  ./generated -g halide_edge -o $(TARGET) target=host

halide_run_on_image:
	$(CC) halide_on_image.cpp $(TARGET) $(HALIDE_FLAGS)

update_and_run:
	make halide TARGET=edge_filter_eval/halide_edge_generator.cpp
	make halide_gen TARGET=$(TARGET)
	make benchmark TARGET=$(TARGET)
	LD_LIBRARY_PATH=~/Halide/bin/:$LD_LIBRARY_PATH ./bm 20 15
