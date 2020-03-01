#include "linkedList.h"
#include "stack.h"



/*
 * push ele into the stack
 * insert at the head of the list
 */
 int stack_push(Stack * stack, const void *data){
     //实际使用 list来实现
     return list_ins_next(stack, NULL, data);
 }

 int stack_pop(Stack * stack, void **data){
     // 弹出 list的头元素
     return list_rem_next(stack, NULL, data);
 }