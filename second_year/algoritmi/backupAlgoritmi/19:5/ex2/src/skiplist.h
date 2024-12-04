#ifndef SKIP_LIST_H
#define SKIP_LIST_H

struct SkipList {
  struct Node* head;
  size_t max_level;
  size_t max_height;
  int (*compar)(const void*, const void*);
};

struct Node {
  struct Node** next;
  size_t size;
  void* item;
};

/**A chained list of any number of elements of any kind, ordered in non descending order.
 * It is a probabilistic data structure in which search, insert and delete operations 
 * have O(log n) complexity
*/
struct SkipList;

/**It allocates a new skiplist, given the maximum height and comparison function, saving 
 * the memory location allocated in *list.
 */
void new_skiplist(struct SkipList **list, size_t max_height, int (*compar)(const void *, const void*));

/**It frees all memory allocated for the SkipList, 
 * including all internal nodes and the data they contain.
*/
void clear_skiplist(struct SkipList **list);

/**It inserts a given item into the skiplist list. 
 * The item to be inserted is provided as a pointer to a generic data item, 
 * whose "responsibility" is passed to the skiplist (which then must deallocate it when 
 * the skiplist is deallocated).
 */
void insert_skiplist(struct SkipList *list, void *item);

/**It checks whether an element with value equal to item is present in the skiplist list;
 * returning NULL if no match is found, otherwise returning the pointer to the item element stored 
 * in the skiplist.
 */
const void* search_skiplist(struct SkipList *list, void *item);

struct Node* createNode(void* item, size_t size);

#endif /* SKIP_LIST_H*/
