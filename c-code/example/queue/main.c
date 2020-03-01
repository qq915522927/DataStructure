#include <stdlib.h>
#include <stdio.h>
#include "queue.h"
#include "my_utils.h"

int main(int argc, char const *argv[])
{
    Queue * queue;
    if((queue = (Queue *) malloc(sizeof(Queue))) == NULL){
        perror("Error");
        return -1;
    }
    queue_init(queue, free);

    int * data;
    for (int i = 0; i < 10; i++)
    {
        data = set_data(i);
        enqueue(queue, data);
    }
    print_list(queue);

    for (int i = 0; i < 10; i++)
    {
        dequeue(queue, (void **)&data);
        printf("Dequeue: %d\n", *data);
    }
    return 0;
}
