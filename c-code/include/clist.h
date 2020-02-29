// 循环链表

#ifndef clist_mark
#define clist_mark

typedef struct _clist
{
    int size;
    struct _clist_node * head;
    void ( * destory ) (void *);
} clist;

typedef struct _clist_node
{
    void * data;
    struct _clist_node * next;
} clist_node;

void init_clist(clist *, void(*destory) (void *));
void destory_clist(clist *);
int clist_ins_next(clist *, clist_node *, void *data);
int clist_rem_next(clist *, clist_node *, void **data);



#endif