#include <stdio.h>
#include <stdlib.h>
#include "clist.h"
#include "second_change_page.h"

Page * get_page(int number, int refer);

int main(int argc, char const *argv[])
{
    clist * pages;
    if((pages = (clist *)malloc(sizeof(clist))) == NULL){
        perror("Error pages");
        return -1;
    }
    init_clist(pages, free);
    Page * p;

    p = get_page(1, 0);
    clist_ins_next(pages, NULL, p);

    for (int i = 0; i < 10; i++)
    {
        p = get_page(i, 0);
        clist_ins_next(pages, pages->head, p);
    }

    clist_node * cur_page_node;
    cur_page_node = pages->head;
    int page_number;
    for (int i = 0; i < 30; i++)
    {
        page_number = page_replace(&cur_page_node);
        printf("Get the avaliable page %d\n", ((Page *)cur_page_node->data)->number);
        // 表示 page被占用了
        ((Page *)cur_page_node->data)->reference++;

    }
    
    return 0;
}

Page * get_page(int number, int refer){
    Page * p;
    if((p = (Page *)malloc(sizeof(Page))) == NULL){
        perror("Error set page");
        return NULL;
    }
    p->number = number;
    p->reference = refer;
    return p;
}
