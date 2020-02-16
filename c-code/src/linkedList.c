#include <stdio.h>
#include <string.h>
#include "linkedList.h"


void list_init(List *list, void (*destory)(void *data)){
    list->size = 0;
    list->destory = destory;
    list->head = NULL;
    list->tail = NULL;

    return;
}

void list_destory(List *list){
    int *data;
    while (list_size(list) > 0)
    {
        if(list_rem_next(list, NULL, (void **) &data) == 0 && 
            list->destory != NULL
        )
        printf("--->Remove element <%d>\n", *data);
        list->destory(data);
    }
}

//在一个元素后面加一个元素
int list_ins_next(List *list, ListEle *ele, const void *data){
    ListEle * newEle;

    //分配需要的内存
    if( (newEle = (ListEle *)malloc(sizeof(ListEle))) == NULL)
        return -1;

    newEle->data = (void *)data;
    if(ele == NULL){
        //插入到头的情况
        if(list_size(list) == 0){
            list->tail = newEle;
        }
        newEle->next = list->head;
        list->head = newEle;
    } else{
        if(ele->next == NULL){
            list->tail = newEle;
        }
        newEle->next = ele->next;
        ele->next = newEle;
    }
    list->size++;
    return 0;
}

// 移除 指定element后的那个元素
int list_rem_next(List *list, ListEle * ele, void **data){
    ListEle * oldEle;
    // 空list
    if(list_size(list) == 0) return -1;

    // 移除头元素
    if(ele == NULL){
        if(list_size(list) == 1){
            list->tail = NULL;
        }
        oldEle = list->head;
        *data = list->head->data;
        list->head = list->head->next;
    } else {
        // 当前为结尾
        if(ele->next == NULL) return -1;

        oldEle = ele->next;
        ele->next = ele->next->next;

        // 移除是结尾
        if(ele->next == NULL){
            list->tail = ele;
        }
    }
    *data = oldEle->data;
    free(oldEle);
    list->size--;
    return 0;
}