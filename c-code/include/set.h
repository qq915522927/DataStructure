#ifndef set_mark_h
#define set_mark_h
#include "linkedList.h"

typedef List Set;

int set_init(Set *, 
             int ( *match)(const void * key1, const void * key2), 
             void (*destory)(void *)
);

int set_add_ele(Set *, const void *data);
int set_rem_ele(Set *, const void *data, const void **removed_data);
Set * intersetion(Set * set1, Set * set2);
// get union of 2 set, if error return NULL
// else return the new set
Set * set_union(Set * set1, Set * set2);
Set * set_intersetion(Set * set1, Set * set2);
Set * set_difference(Set * set1, Set * set2);

// check if the ele is a member of the set
int set_is_member(Set *, const void *data);

// check if the set2 is the subset of set1
int set_is_subset(Set * set1, Set * set2);

// check if two sets are equal
int set_is_equal(Set * set1, Set * set2);

#define set_destory(set) (list_destory(set))
#endif