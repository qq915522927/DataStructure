#include <stdio.h>
#include "linkedList.h"

static void print_list(const List *list){
    ListEle *ele;
    int *data, i;
    fprintf(stdout, "-> List size is %d\n", list_size(list));

    i = 0;
    ele = list_head(list);

    while (1)
    {
        data = list_data(ele);
        fprintf(stdout, "--> list[%03d]=%03d\n", i, *data);
        i++;
        if(list_is_tail(ele)) {
            break;
        } else {
            ele = list_next(ele);
        }
    }
    printf("\n");
    return;
}
int main(int argc, char const *argv[])
{
    List list;
    ListEle *ele;
    int *data;
    int i;

    list_init(&list, free);

    ele  = list_head(&list);
    for (i = 10; i > 0; i--)
    {
        if((data = (int *) malloc(sizeof(int))) == NULL) return 1;
        *data = i;
        //从头插入
        if(list_ins_next(&list, NULL, data) != 0) return 1;
    }
    print_list(&list);
    
    ele = list_head(&list);
    for (i = 0; i < 7; i++)
    {
        ele = list_next(ele);
    }
    data = list_data(ele);
    fprintf(stdout, "Removing an element after the one containing %03d\n", *data);
    if(list_rem_next(&list, ele, (void **)&data) != 0) return 1;

    print_list(&list);

    fprintf(stdout, "Inserting 012 at the head of the list\n");
    *data = 12;
    if(list_ins_next(&list, NULL, data) != 0) return 1;

    print_list(&list);
    
    fprintf(stdout, "Iterating and removing the fourth element\n");

    ele = list_head(&list);
    ele = list_next(ele);
    ele = list_next(ele);
    if(list_rem_next(&list, ele, (void **)&data) != 0) return 1;
    print_list(&list);

    i = list_is_head(&list, list_head(&list));
    fprintf(stdout, "Testing list_is_head...Value=%d (1=OK)\n", i);
    i = list_is_head(&list, list_tail(&list));
    fprintf(stdout, "Testing list_is_head...Value=%d (0=OK)\n", i);
    i = list_is_tail(list_tail(&list));
    fprintf(stdout, "Testing list_is_tail...Value=%d (1=OK)\n", i);
    i = list_is_tail(list_head(&list));
    fprintf(stdout, "Testing list_is_tail...Value=%d (0=OK)\n", i);
    
    /// 销毁链表
    fprintf(stdout, "Destroying the list\n");
    list_destory(&list);

    return 0;
}
