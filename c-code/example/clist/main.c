#include <stdio.h>
#include <stdlib.h>
#include "clist.h"

void print_clist(clist * list);
int * set_data(int i);
void print_clist_times(clist * list, int times);

int main(int argc, char const *argv[])
{

    clist *list;
    if((list = (clist *)malloc(sizeof(clist))) == NULL){
        perror("error in malloc for clist");
        return -1;
    }
    init_clist(list, free);
    int * data;
    data = set_data(1);
    printf("Insert 1 for a empty list\n");
    clist_ins_next(list, NULL, data);
    printf("Insert 2 after head\n");
    data = set_data(2);
    clist_ins_next(list, list->head, data);
    printf("Insert 3 after head\n");
    data = set_data(3);
    clist_ins_next(list, list->head, data);
    printf("Remove 3 after head\n");
    clist_rem_next(list, list->head, (void **)&data);
    printf("Removed ele is %d\n", *data);
    print_clist(list);

    printf("Insert 3 after head\n");
    data = set_data(3);
    clist_ins_next(list, list->head, data);

    printf("print list 3 times");
    print_clist_times(list, 3);
    destory_clist(list);
    return 0;
}

int * set_data(int i){
    int * res;
    if((res = (int *) malloc(sizeof(int))) == NULL){
        perror("error to set data");
        return NULL;
    }
    *res = i;
    return res;
}

void print_clist(clist * list){
    if(list->size==0){
        printf("Empty List");
    }
    printf("size(%d): ", list->size);

    if(list->size == 1){

        printf("%d\n", *(int *)list->head->data);
        return;
    }
    printf("%d -> ", *(int *)list->head->data);
    clist_node * cur_node = list->head->next;
    while (cur_node != list->head)
    {
        if(cur_node->next == list->head){
            printf("%d\n", *(int *)cur_node->data);
        }
        else {
            printf("%d -> ", *(int *)cur_node->data);
        }
        cur_node = cur_node->next;
    }
}

/*
print list loop times
*/
void print_clist_times(clist * list, int times){
    if(list->size==0){
        printf("Empty List");
    }
    printf("size(%d): ", list->size);

    if(list->size == 1){

        printf("%d\n", *(int *)list->head->data);
        return;
    }
    printf("%d -> ", *(int *)list->head->data);
    clist_node * cur_node = list->head->next;
    int i = 0;
    while (1)
    {
            if(i >= times){
                break;
            }
            printf("%d -> ", *(int *)cur_node->data);
            cur_node = cur_node->next;
            if (cur_node == list->head){
                i ++;
            }
    }


}
