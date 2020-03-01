#include <stdio.h>
#include <stdlib.h>
#include "linkedList.h"
#include "stack.h"
#include "my_utils.h"

int main(int argc, char const *argv[])
{
    Stack * stack;
    if(( stack = (Stack *)malloc(sizeof(Stack))) == NULL){
        perror("Error");
        return -1;
    }
    stack_init(stack, free);

    int * data;
    for (int i = 0; i < 10; i++)
    {
        data = set_data(i);
        stack_push(stack, data);
    }
    print_list(stack);

    for (int i = 0; i < 10; i++)
    {
        stack_pop(stack, (void **)&data);
        printf("Pop data: %d\n", *data);
    }
    print_list(stack);

    data = set_data(5);
    stack_push(stack, data);
    stack_destory(stack);
    return 0;
}
