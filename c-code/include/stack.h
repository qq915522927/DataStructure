#ifndef stack_mark
#define stack_mark

#include "linkedList.h"

typedef List Stack;
// 宏的展开方式 在不同的编译器下可能不一样
// 一种是将函数 重命名
// 一种必须显示的在宏里 调用函数
#define stack_init(stack, destory) list_init(stack, destory);
#define stack_destory(stack) list_destory(stack);

 int stack_push(Stack *, const void *data);
 int stack_pop(Stack *, void **data);

 #define stack_peek(stack) ((stack)->head==NULL ? NULL: (stack)->head->data)

#endif