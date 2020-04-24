#include <stdio.h>
#include <stdlib.h>
#include "set.h"
#include "linkedList.h"

ListEle * iter_list(List * list);
Set * new_set(
    int (*match) (const void *key1, const void* key2),
    void (*destory)(void *)
);

int set_init(Set * set,
             int ( *match)(const void * key1, const void * key2), 
             void (*destory)(void *)
){
    if(set == NULL){
        printf("Set is Null, can't init");
        return -1;
    }
    set->size = 0;
    set->head = NULL;
    set->destory = destory;
    set->match = match;
    set->tail = NULL;
    return 0;
}

int set_add_ele(Set * set, const void *data){
    if(!set_is_member(set, data)){
        return list_ins_next(set, NULL, data);
    }
    return 0;

}
int set_rem_ele(Set * set, const void *data, const void **removed_data){
    ListEle * pre_ele;
    //find the element and got the pre element
    if(private_find_pre_ele(set, data, pre_ele) == 0){
        return list_rem_next(set, pre_ele, removed_data);
    } else{
        printf("Can't find element");
        return -1;
    }

}
Set * set_intersetion(Set * set1, Set * set2){
    ListEle * ele;
    Set * interction_set;
    interction_set = new_set(set1->match, set1->destory);

    while( (ele = iter_list(set1)) != NULL){
        if(set_is_member(set2, ele->data)){
            if(set_add_ele(interction_set, ele->data) !=0){
                return NULL;
            }
        }
    }
    return interction_set;
}
// get union of 2 set, if error return NULL
// else return the new set
Set * set_union(Set * set1, Set * set2){
    ListEle * ele;
    Set * union_set;
    union_set = new_set(set1->match, set1->destory);
    if(union_set == NULL){
        return NULL;
    }

    while( (ele = iter_list(set1)) != NULL){
        set_add_ele(union_set, ele->data);
    }
    while( (ele = iter_list(set2)) != NULL){
        set_add_ele(union_set, ele->data);
    }
    return union_set;

}
Set * set_difference(Set * set1, Set * set2){

    ListEle * ele;
    Set * difference_set;
    difference_set = new_set(set1->match, set1->destory);
    if(difference_set == NULL){
        return NULL;
    }

    while( (ele = iter_list(set1)) != NULL){
        if(!set_is_member(set2, ele->data)){
            set_add_ele(difference_set, ele->data);
        }
    }
    return difference_set;
}

// check if the ele is a member of the set
int set_is_member(Set * set, const void *data){
    ListEle * ele;
    while( (ele= iter_list(set)) != NULL){
        if(set->match(ele->data, data)){
            return 1;
        }
    }
    return 0;
}

// check if the set2 is the subset of set1
int set_is_subset(Set * set1, Set * set2){
    // not subset
    if(set1->size > set2->size){
        return 0;
    }

    int is_sub_set = 1;
    ListEle * ele;
    while((ele = iter_list(set1)) != NULL){
        if(!set_is_member(set2, ele->data)){
            // if one of the ele in set1, but not in
            // set2, set1 is not the subset of set2
            is_sub_set = 0;
            break;
        }
    }
    return is_sub_set;
}

// check if two sets are equal
//
// if size of both set is equal
// and the set1 is the subset of set2
// they are totally equal
int set_is_equal(Set * set1, Set * set2){
    if(set1->size != set2->size){
        return 0;
    }
    if(set_is_subset(set1, set2)){
        return 1;
    }
}


// return 0 means find the element
// return -1 means failed to find the element
//
// if successfully find, and pre is NULl, means the matched element
// is head of the list
int private_find_pre_ele(Set * set, const void * data, ListEle * pre){
    if (set->size == 0){
        return -1;
    }
    ListEle * cur_ele;
    pre = NULL;
    // if head is what  you want
    if (set->match(set->head->data, data)){
        return 0;
    }
    // conside other ele
    pre = set->head;
    cur_ele = set->head->next;
    while(cur_ele !=NULL && !set->match(cur_ele->data, data)){
        pre = cur_ele;
        cur_ele = cur_ele->next;
    }
    // interate to the end of the set
    // means can't find the matched element
    if (cur_ele == NULL){
        return -1;
    }
    return 0;
}

ListEle * iter_list(List * list){
    static List * cur_list;
    static ListEle * cur_ele;
    ListEle * result_ele;
    if(cur_list==NULL){
        cur_list = list;
    }
    if(cur_list != list){
        cur_list = list;
        cur_ele = list->head;
    }
    if (cur_ele == NULL){
        cur_ele = cur_list->head;
    }
    result_ele = cur_ele;
    if(cur_ele!= NULL)
        cur_ele = cur_ele->next;
    return result_ele;
}

Set * new_set(
    int (*match) (const void *key1, const void* key2),
    void (*destory)(void *)
){
    Set * new_set;
    if((new_set = (Set *)malloc(sizeof(Set))) == NULL){
        perror("error");
        return NULL;
    }
    if(set_init(new_set, match, destory)!=0){
        return NULL;
    }
    return new_set;
}