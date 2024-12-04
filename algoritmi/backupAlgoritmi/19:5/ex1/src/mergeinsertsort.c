#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "mergeinsertsort.h"

//Initial capacity for the array
#define INITIAL_CAPACITY 2

void divide(void** base, void** first_half, void** second_half,size_t length_first_half, size_t length_second_half);

void** arrayCreate(){
  void** array=(void**)malloc(sizeof(void*));
  if(array==NULL){
    fprintf(stderr,"arrayCreate: unable to allocate memory for the array");
    exit(EXIT_FAILURE);
  }
  return array;
}

void** array_add(void** base, void* element, size_t* nitems, size_t* capacity){
  if(*nitems==*capacity){
    base=(void**)realloc(base,(*capacity)*2 * sizeof(void*));
    (*capacity)=(*capacity)*2;
  }
  if(base == NULL){
    fprintf(stderr,"add: unable to reallocate memory to host the new element");
    exit(EXIT_FAILURE);
  }
	//memcpy();
  base[*nitems]=element; 
  (*nitems)++;
  return base;
}

void* array_get(void** base, unsigned long i, size_t nitem) {
	if (base == NULL) {
		fprintf(stderr, "array_get: array parameter cannot be NULL");
		exit(EXIT_FAILURE);
	}
	if (i >= nitem) {
		fprintf(stderr, "array_get: Index %lu is out of the array bounds", i);
		exit(EXIT_FAILURE);
	}
	return base[i];
}


void printArray(void** array, size_t nitems){
  for(size_t i=0;i<nitems;i++){
    struct record* tmp = array[i];
    printf("<%d,%s,%d,%5.6f>\n",tmp->id_field,tmp->string_field,tmp->integer_field,tmp->float_field); 
  }
  printf("\n");
}


void merge_binary_insertion_sort(void **base, size_t nitems, size_t k, int (*compar)(const void*, const void*)){
	if (nitems >= 2) {
		size_t length_first_half = nitems/2;
		size_t length_second_half = nitems-length_first_half;

		void** first_half = (void**)malloc(sizeof(void*)*length_first_half);
		void** second_half = (void**)malloc(sizeof(void*)*length_second_half);

		memcpy(first_half, base, length_first_half * sizeof(void*));
		second_half=base+length_first_half;		
		
		if (length_first_half <= k) 
			binary_insertion_sort(first_half, length_first_half,compar);
		else 
			merge_binary_insertion_sort(first_half, length_first_half, k, compar);	
		if (length_second_half <= k) 
			binary_insertion_sort(second_half, length_second_half,compar);
		else 
			merge_binary_insertion_sort(second_half, length_second_half,k,compar);
		
		merge(first_half, second_half, base, length_first_half,compar);
	}
}

void binary_insertion_sort(void **base, size_t nitems, int (*compar)(const void*, const void*)){
  size_t i, j, chosen_position;
	size_t size_arr = nitems;
	void* selected_el;
	for (i = 1; i < size_arr; i++) {
		j = i - 1;
		selected_el = (struct record*)(base[i]);
		chosen_position = binarySearch(base, selected_el, 0, j, compar, nitems);
		while (j >= chosen_position && (int)j >= 0) {
			base[j+1] = base[j];
			j--;
		}
		base[j + 1] = selected_el;
	}

}

void merge (void** first_half, void** second_half, void** whole_arr, size_t nitems, int (*compare)(const void*,const void*)) {
	unsigned long index_1 = 0, index_2 = 0, indexA = 0;
	while ((index_1 < nitems) && (index_2 < nitems)) {
		if (compare(first_half[index_1], second_half[index_2])) {
			whole_arr[indexA] = first_half[index_1];
			index_1++;
		}
		else {
			whole_arr[indexA] = second_half[index_2];
			index_2++;
		}
		indexA++; 
	}
	while (index_1 < nitems) {
		whole_arr[indexA] = first_half[index_1];
		index_1 ++;
		indexA ++;
	}
	while (index_2 < nitems) {
		whole_arr[indexA] = second_half[index_2];
		index_2 ++;
		indexA++;
	}
}


size_t binarySearch(void** array, void* x, size_t start, size_t end, int (*compar)(const void*, const void*), size_t nitems) {
	if (start == end) {
		if (compar(x, array_get(array, start, nitems)))
			return start;
		else
			return start + 1;
	}
	else {
		size_t mid = (start + end) / 2; 
		if (compar(x, array_get(array, mid,nitems))) 
			return binarySearch(array, x, start, mid, compar, nitems);
		else
			return binarySearch(array, x, mid + 1, end, compar, nitems);
	}
}

