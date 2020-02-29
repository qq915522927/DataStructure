
#include <stdlib.h>
#include <stdio.h>
#include "clist.h"

void init_clist(clist * list, void(*destory) (void *)){
    list->head = NULL;
    list->size = 0;
    list->destory = destory;
}
void destory_clist(clist * list){
    clist_node * cur_node;
    clist_node * old_node;
    if(list->head != NULL){
        if(list->size==1){
            list->destory(list->head->data);
            list->destory(list->head);
        } else{
            // 从第二个节点绕一圈删到头节点
            cur_node = list->head->next;
            while(cur_node!=list->head){
                old_node = cur_node;
                cur_node = cur_node->next;
                list->destory(old_node->data);
                list->destory(old_node);
            }
            // 删除头结点
            list->destory(list->head->data);
            list->destory(list->head);
        }
    }
    list->destory(list);
}

int clist_ins_next(clist * list, clist_node * node, void *data){

    clist_node  * new_node;
    if((new_node = (clist_node *)malloc(sizeof(clist_node))) == NULL){
        perror("error in clist insert");
        return -1;
    }
    new_node->data = data;

    // the list must be empty
    if(node == NULL){
        if(list->head == NULL){
            list->head = new_node;
            new_node->next = list->head;
        } else{
            printf("Error: list is not empty");
            return -1;
        }
    } else {
        new_node->next = node->next;
        node->next = new_node;
    }
    list->size ++;
    return 0;
}
int clist_rem_next(clist * list, clist_node * node, void **data){
    if(list->size == 0){
        printf("list is empty");
        return -1;
    }
    clist_node * old_node;
    old_node = node->next;
    * data = old_node->data;
    if(list->size == 1){
        list->head = NULL;
    } else{
        node->next = old_node->next;
    }
    list->size--;
    list->destory(old_node);
    return 0;
}