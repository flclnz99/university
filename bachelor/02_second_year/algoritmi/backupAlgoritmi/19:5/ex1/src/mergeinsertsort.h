#ifndef ORDERED_ARRAY_H_laokjsdnbudjllvfidkfmqm
#define ORDERED_ARRAY_H_laokjsdnbudjllvfidkfmqm

/**An array of any number of elements of any kind, ordered in non descending order
  * according to a specific precedence relation.
  */

struct record{
  int id_field;
  char* string_field;
  int integer_field;
  double float_field;
};

//void printArray(void**, size_t);

void** arrayCreate();

void printArray(void** array, size_t nitems);

void** array_add(void**,void*,size_t*,size_t*);

void merge_binary_insertion_sort(void **base, size_t nitems, size_t k, int (*compar)(const void *, const void*));

void binary_insertion_sort(void **base, size_t nitems, int (*compar)(const void*, const void*));

size_t binarySearch(void** array, void* x, size_t start, size_t end, int (*compar)(const void*, const void*), size_t nitems);

void merge (void** first_half, void** second_half, void** whole_arr, size_t nitems, int (*compare)(const void*,const void*));

#endif
