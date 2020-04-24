#include <stdio.h>
#include "chtbl.h"

typedef struct CHTblEle_
{
    int key;
    int value;
} CHTblEle;

#define TABLE_SIZE 200

int hash_int(const void* key){
    CHTblEle* ele = (CHTblEle*)key;
    float f = ele->key * 0.618;
    return (f - (int)f) * TABLE_SIZE;
}
int int_match(const void* data1, const void* data2){
    CHTblEle* ele1 = (CHTblEle*)data1;
    CHTblEle* ele2 = (CHTblEle*)data2;
    return ele1->key == ele2->key;

}
int add_value(CHTbl* tbl, int key, int value){
    CHTblEle* ele;
    if((ele = malloc(sizeof(CHTblEle))) == NULL){
        return -1;
    }
    ele->key = key;
    ele->value = value;
    chtbl_insert(tbl, ele);
    return 0;
}
int get_value(CHTbl* tbl, int key){
    CHTblEle ele = {key, 0};
    CHTblEle* p = &ele;
    chtbl_lookup(tbl, &p);
    return p->value;
}
int remove_key(CHTbl* tbl, int key){
    CHTblEle ele = {key, -999};
    CHTblEle* p = &ele;
    if(chtbl_remove(tbl, &p) != 0){
        printf("can not get %d\n", key);
    }
    return p->value;
}



int main(int argc, char const *argv[])
{
    CHTbl* tbl;
    if((tbl = (CHTbl*)malloc(sizeof(CHTbl))) == NULL){
        perror("Error malloc tbl");
        return -1;
    }
    chtbl_init(tbl, TABLE_SIZE, hash_int, int_match, free);
    for(int i=0;i<300;i++){
        add_value(tbl, i, i);
    }
    printf("Table size: %d\n", tbl->size);
    for(int i=0;i<300;i++){
        printf("Get value: %d\n", get_value(tbl, i));
    }
    for(int i=299;i>=0;i--){
        printf("Remove value: %d\n", remove_key(tbl, i));
    }
    printf("Table size: %d\n", tbl->size);

    return 0;
}
