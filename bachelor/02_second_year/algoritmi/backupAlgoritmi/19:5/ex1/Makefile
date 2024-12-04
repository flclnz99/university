CC = gcc
all: bin/mergeinsertsort bin/mergeinsertsort_tests

CFLAGS = -g -Wall -Wextra -Wconversion

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

bin/mergeinsertsort: build/mergeinsertsort.o build/main_ex1.o $(COMMON_DEPS)
	$(CC) -o bin/mergeinsertsort build/mergeinsertsort.o build/main_ex1.o

bin/mergeinsertsort_tests: build/mergeinsertsort_tests.o build/mergeinsertsort.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/mergeinsertsort_tests  build/mergeinsertsort_tests.o build/mergeinsertsort.o build/unity.o

clean:
	rm -f build/* bin/*
