#ifndef queue_mark
#define queue_mark

#include "linkedList.h"

typedef List Queue;
// 宏的展开方式 在不同的编译器下可能不一样
// 一种是将函数 重命名
// 一种必须显示的在宏里 调用函数
#define queue_init(queue, destory) list_init(queue, destory);
#define queue_destory(queue) list_destory(queue);

 int dequeue(Queue *, void **data);
 int enqueue(Queue *, const void *data);

 #define queue_peek(queue) ((queue)->head==NULL ? NULL: (queue)->head->data)

#endif