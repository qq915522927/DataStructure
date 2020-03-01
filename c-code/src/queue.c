#include "linkedList.h"
#include "queue.h"

// remove ele from head
 int dequeue(Queue * queue, void **data){
     return list_rem_next(queue, NULL, data);
 }
 // insert ele at tail
 int enqueue(Queue * queue, const void *data){
     return list_ins_next(queue, queue->tail, data);
 }