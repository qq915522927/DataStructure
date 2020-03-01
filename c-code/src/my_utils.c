
#include <stdio.h>
#include <stdlib.h>
#include "linkedList.h"

int * set_data(int i){
    int * res;
    if((res = (int *) malloc(sizeof(int))) == NULL){
        perror("error to set data");
        return NULL;
    }
    *res = i;
    return res;
}

void print_list(const List *list){
    ListEle *ele;
    int *data, i;
    fprintf(stdout, "-> List size is %d\n", list_size(list));
    if(list_size(list) == 0)
        return;

    i = 0;
    ele = list_head(list);

    while (1)
    {
        data = (int *)ele->data;
        printf("--> list[%d]=%d ", i, *data);
        i++;
        if(ele->next == NULL) {
            break;
        } else {
            // ele = list_next(ele);
            ele = ele->next;
        }
    }
    printf("\n");
    return;
}