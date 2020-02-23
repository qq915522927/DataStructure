# include <stdio.h>
# include <stdlib.h>

# include "DList.h"

int dlist_ins_next(DList * list, DlistEle * ele, void * data){
    DlistEle * newEle;
    if((newEle = (DlistEle *)malloc(sizeof(DlistEle))) == NULL){
        perror("Error:");
        return -1;
    }
    newEle->data = data;
    // if ele is NULL, insert at the head
    if(ele == NULL){
        newEle->next = list->head;
        list->head = newEle;

        if(size(list) != 0){
            list->head->next->pre = list->head;
        } else{
            list->tail = newEle;
        }

    } else{
        newEle->next = ele->next;
        if(newEle->next != NULL)
        // newEle is not tail
            {newEle->next->pre = newEle;}
        else
        {
            // newEle is tail
            list->tail = newEle;
        }
        ele->next = newEle;
        newEle->pre = ele;
    }
    list->size++;
    return 0;
}
int dlist_ins_prev(DList * list, DlistEle * ele, void * data){
    if(ele==NULL){
        printf("Error element is NULL");
        return-1;
    }
    if(ele->pre != NULL){
        return dlist_ins_next(list, ele->pre, data);
    } else{
        return dlist_ins_next(list, NULL, data);
    }

}
void init_dlist(DList * list, void (*destory) (void *)){
    list->destroy = destory;
    list->head = NULL;
    list->tail= NULL;
    list->size = 0;
}
void rm_ele(DList *list, DlistEle * ele, void **data){
    ele->pre->next = ele->next;
    ele->next->pre = ele->pre;
    *data = ele->data;
    list->destroy((void *)ele);
}

void destory_dlist(DList * list){
    DlistEle * cur;
    DlistEle * next;
    cur = list->head;
    while (cur!=NULL)
    {
         next = cur->next;
        list->destroy((void *) cur->data);
        list->destroy((void *) cur);
        cur = next;
    }
    list->destroy((void *) list);

}