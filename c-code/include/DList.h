#ifndef DLIST_MARK
#define DLIST_MARK

typedef struct _DlistEle{
    void * data;
    struct _DlistEle * pre;
    struct _DlistEle * next;
} DlistEle;

typedef struct _DList
{
    DlistEle * head;
    DlistEle * tail;
    int size;
    void (*destroy)(void *);

} DList;

// interface
int dlist_ins_next(DList *, DlistEle *, void * data);
int dlist_ins_prev(DList *, DlistEle *, void * data);
void rm_ele(DList *list, DlistEle * ele, void **data);
void init_dlist(DList *, void (*destory) (void *));
void destory_dlist(DList *);

#define ele_data(ele) ((ele)->data)
#define next_ele(ele) ((ele)->next)
#define pre_ele(ele) ((ele)->prev)
#define size(list) ((list)->size)
#define head(list) ((list)->head)
#define tail(list) ((list)->tail)
#endif