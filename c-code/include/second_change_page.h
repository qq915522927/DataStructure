// second change page
// 第二次机会页面替换算法
# include "clist.h"

#ifndef second_page
#define second_page

typedef struct _page
{
    int number;
    int reference;
} Page;

/*
return the avaliable page number
update the vaule of current pointer
*/
int page_replace(clist_node ** current);


#endif