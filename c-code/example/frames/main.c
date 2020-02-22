#include <stdio.h>
#include "linkedList.h"
#include "frames.h"

int main(int argc, char const *argv[])
{
    List * frames;
    if((frames = (List *)malloc(sizeof(List))) == NULL){
        return -1;
    }
    list_init(frames, free);
    int * frame_n;
    for(int i=0;i<10;i++){
        frame_n = (int *)malloc(sizeof(int));
        *frame_n = i;
        list_ins_next(frames, NULL, frame_n);
    }
    for (int i = 10; i < 20; i++)
    {
        free_frame(frames, i);
    }

    for (int i = 0; i < 20; i++)
    {
        printf("alloc frame: %d\n", alloc_frame(frames));
    }
    return 0;
}
