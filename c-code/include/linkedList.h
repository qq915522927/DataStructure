#ifndef LIST_H
#define LIST_H

#include <stdlib.h>


//节点
typedef struct ListEle_
{
    void *data;
    //不可以直接 用新定义的类型 ListEle
    struct ListEle_ * next;
} ListEle;

// 链表
typedef struct List_
{
    int size;
    int (*match)(const void *key1, const void *key2);
    void (*destory)(void *data);
    ListEle * head;
    ListEle * tail;
} List;

// 初始化链表
void list_init(List *list, void (*destory)(void *data));

//销毁链表
void list_destory(List *list);

//在一个元素后面加一个元素
int list_ins_next(List *list, ListEle *ele, const void *data);

// 移除 指定element后的那个元素
int list_rem_next(List *list, ListEle * ele, void **data);

#define list_size(list) ((list)->size)
#define list_head(list) ((list)->head)
#define list_tail(list) ((list)->tail)

#define list_is_head(list, element) ((element) == (list)->head ? 1 : 0)
#define list_is_tail(element) ((element)->next == NULL ? 1 : 0)

#define list_data(element) ((element)->data)

#define list_next(element)((element)->next)


#endif