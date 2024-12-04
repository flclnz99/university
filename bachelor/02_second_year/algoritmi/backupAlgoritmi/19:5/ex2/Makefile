CC = gcc
all: bin/skiplist_main

CFLAGS = -g -Wall -Wextra -Wpedantic -Wconversion

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

bin/skiplist_main: build/skiplist.o build/skiplist_main.o $(COMMON_DEPS)
	$(CC) -o bin/skiplist_main build/skiplist.o build/skiplist_main.o

clean:
	rm -f build/* bin/*
