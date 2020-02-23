# include<stdio.h>
# include<stdlib.h>
# include "DList.h"

void print_dlist(DList * list);

void print_dlist_reverse(DList * list);


int main(int argc, char const *argv[])
{
    DList * list;
    list = malloc(sizeof(DList));
    init_dlist(list, free);

    int * data;
    for (int i = 0; i < 10; i++)
    {
        if((data = (int *)malloc(sizeof(int))) == NULL){
            perror("Error");
            return -1;
        }
        *data = i;
        dlist_ins_next(list, NULL, (void *)data);
    }
    DlistEle * ele;
    // element is 8
    ele = list->head->next;
    rm_ele(list, ele, (void **)&data);
    printf("remove ele %d\n", *data);
    if((data = (int *)malloc(sizeof(int))) == NULL){
        perror("Error");
        return -1;
    }
    *data = 100;
    printf("insert ele %d before 7\n", *data);
    dlist_ins_prev(list, ele, (void *)data);

    // element is 7
    ele = list->head->next;
    print_dlist(list);
    print_dlist_reverse(list);
    destory_dlist(list);
    return 0;
}

void print_dlist(DList * list){
    DlistEle * cur;
    cur = list->head;
    printf("DList");
    while(cur){
        printf(">>> %d ", *(int *)cur->data);
        cur = cur->next;
    }
    printf("\n");
}

void print_dlist_reverse(DList * list){
    DlistEle * cur;
    cur = list->tail;
    printf("DList");
    while(cur){
        printf(">>> %d ", *(int *)cur->data);
        cur = cur->pre;
    }
    printf("\n");
}
