#ifndef _chtbl
#define _chtbl

#include "linkedList.h"

typedef struct CHTbl_
{
    int buckets;

    int (*h)(const void* key);
    int (*match)(const void* key1, const void* key2);
    void (*destory)(void* data);

    int size;
    List* table;
} CHTbl;

int chtbl_init(CHTbl *, int buckets,
               int (*h)(const void* key),
               int (*match)(const void* key1, const void* key2),
               void (*destory)(void *)
               );

int chtbl_destory(CHTbl*);

int chtbl_insert(CHTbl *, const void* data);

int chtbl_remove(CHTbl *, void** data);

int chtbl_lookup(CHTbl *, void** data);

#endif