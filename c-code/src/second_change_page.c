#include "clist.h"
#include "second_change_page.h"

int page_replace(clist_node ** current){
    while( ((Page *)(*current)->data)->reference != 0){
        ((Page *)(*current)->data)->reference = 0;
        *current = (*current)->next;
    }
    return ((Page *)(*current)->data)->number;
}