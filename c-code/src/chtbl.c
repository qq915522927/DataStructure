#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "linkedList.h"
#include "chtbl.h"

int chtbl_init(CHTbl *htbl, int buckets,
               int (*h)(const void *key),
               int (*match)(const void *key1, const void *key2),
               void (*destroy)(void *data))
{
    if((htbl->table = (List* )malloc(buckets * sizeof(List))) == NULL){
        perror("init table failed");
        return -1;
    }
    // 为每个桶 分配内存
    for(int i=0;i <buckets;i++){
        list_init(&(htbl->table[i]), destroy);
    }

    htbl->buckets = buckets;
    htbl->h = h;
    htbl->match = match;
    htbl->destory = destroy;
    htbl->size = 0;
    return 0;

}

int chtbl_destory(CHTbl* htbl){
    for (int i = 0; i < htbl->buckets; i++)
    {
        list_destory(&(htbl->table[i]));
    }
    htbl->destory((void*)htbl->table);
    return 0;
}

// data shuld contains key,value in one struct
// if key already exist return 1
// success return 0
// failed return -1
int chtbl_insert(CHTbl* htbl, const void* data){
    void* tmp_data = (void*)data;
    int retval;
    if(chtbl_lookup(htbl, &tmp_data) == 0){
        return 1;
    }
    int bucket = htbl->h(data);
    if((retval = list_ins_next(&htbl->table[bucket], NULL, data))==0){
        htbl->size++;
    }
    return retval;
}

int chtbl_lookup(CHTbl* htbl, void** data){
    int bucket = htbl->h(*data);
    List* list = htbl->table + bucket;
    for(ListEle* ele = list->head;ele!=NULL;ele=ele->next){
        if(htbl->match(ele->data, *data)){
            // pass back the find element to data
            *data = ele->data;
            return 0;
        }
    }
    return -1;
}

int chtbl_remove(CHTbl* htbl, void** data){

    int bucket = htbl->h(*data);
    List* list = htbl->table + bucket;
    ListEle* pre=NULL;
    for(ListEle* ele = list->head;ele!=NULL;pre=ele,ele=ele->next){
        // printf("depth: %d\n", depth++);
        if(htbl->match(ele->data, *data)){
            if(pre!=NULL){
                printf("pre->next %p\n", pre->next);
                printf("ele %p\n", ele);
            }
            list_rem_next(list, pre, data);
            htbl->size--;
            return 0;
        }
    }
    return -1;

}