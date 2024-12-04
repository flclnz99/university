#include <stdio.h>
#include <stdlib.h>
#include "unity.h"
#include "mergeinsertsort.h"

/*
 * Test suite for ordered array data structure and algorithms
 */

/*precedence relation used in tests
static int precedes_int(void* i1_p, void* i2_p){
  int* int1_p = (int*)i1_p;
  int* int2_p = (int*)i2_p;
  if((*int1_p) < (*int2_p))
    return(1);
  return(0);
}*/

//Data elements that are initialized before each test
static int i1, i2, i3;
static void** array_int;
static size_t capacity = 2;
static size_t nelem = 0;

void setUp(void){
  i1 = -12;
  i2 = 0;
  i3 = 4;
  array_int = arrayCreate();
}

void tearDown(void){
  free(array_int);
}

static void test_mergeinsertsort_array_add_one_el(void) {
  array_add(array_int, &i1, &nelem, &capacity);
  TEST_ASSERT_EQUAL_INT(1, nelem);
}

static void test_mergeinsertsort_array_add_two_el(void) {
  array_add(array_int, &i1, &nelem, &capacity);
  array_add(array_int, &i2, &nelem, &capacity);
  TEST_ASSERT_EQUAL_INT(2, nelem);
}

static void test_merge_binary_insertion_sort_el_added_in_order(void) {
  int exp_arr[] = {-12, 0, 4};
  array_add(array_int, &i1, &nelem, &capacity);
  array_add(array_int, &i2, &nelem, &capacity);
  array_add(array_int, &i3, &nelem, &capacity);
  int act_arr[3];
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

static void test_merge_binary_insertion_sort_el_added_reverse_order(void) {
  int exp_arr[] = {4, 0, -12};
  array_add(array_int, &i3, &nelem, &capacity);
  array_add(array_int, &i2, &nelem, &capacity);
  array_add(array_int, &i1, &nelem, &capacity);
  int act_arr[3];
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

static void test_merge_binary_insertion_sort_el_added_no_order(void) {
  int exp_arr[] = {0, -12, 4};
  array_add(array_int, &i2, &nelem, &capacity);
  array_add(array_int, &i1, &nelem, &capacity);
  array_add(array_int, &i3, &nelem, &capacity);
  int act_arr[3];
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

static void test_merge_binary_insertion_sort_two_equal_el(void) {
  int exp_arr[] = {-12, -12, 4};
  array_add(array_int, &i1, &nelem, &capacity);
  array_add(array_int, &i1, &nelem, &capacity);
  array_add(array_int, &i3, &nelem, &capacity);
  int act_arr[3];
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}

static void test_merge_binary_insertion_sort_three_equal_el(void) {
  int exp_arr[] = {4, 4, 4};
  array_add(array_int, &i3, &nelem, &capacity);
  array_add(array_int, &i3, &nelem, &capacity);
  array_add(array_int, &i3, &nelem, &capacity);
  int act_arr[3];
  TEST_ASSERT_EQUAL_INT_ARRAY(exp_arr, act_arr, 3);
}


int main(void) {

  //test session
  UNITY_BEGIN();
  
  RUN_TEST(test_mergeinsertsort_array_add_one_el);  
  RUN_TEST(test_mergeinsertsort_array_add_two_el);
  //RUN_TEST(test_merge_binary_insertion_sort_el_added_in_order);
  //RUN_TEST(test_merge_binary_insertion_sort_el_added_reverse_order);
  //RUN_TEST(test_merge_binary_insertion_sort_el_added_no_order);
  //RUN_TEST(test_merge_binary_insertion_sort_two_equal_el);
  //RUN_TEST();
  //RUN_TEST();
  //RUN_TEST();
  //RUN_TEST();
  //RUN_TEST();
  
  return UNITY_END();
}

