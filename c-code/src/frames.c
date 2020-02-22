#include <stdio.h>
#include "frames.h"
#include "linkedList.h"

// frames 是一个 链表， 储存的是空闲的 页帧号
// 在此链表中 就说明， 这个页帧是 空闲的
// 所以 alloc_frame 其实是 从链表中删除一个节点，代表
// 对应的页帧 被占用了
// 相反 free_frame, 其实是向 链表中插入节点，代表
// 对应的页帧被释放 成为空闲的
int alloc_frame(List * frames)
{
    int frame_number, * data;
    if(list_size(frames) == 0){
        return -1;
    } else {
        if(list_rem_next(frames, NULL, (void **)&data) != 0){
            // can't find free frame
            return -1;
        } else {
            frame_number = *data;
            free(data);
        }
    }
    return frame_number;
}

int free_frame(List *frames, int frame_number){
    int *data;
    if((data = (int *)malloc(sizeof(int))) == NULL){
        return -1;
    }

    *data = frame_number;
    if (list_ins_next(frames, NULL, data) != 0){
        return -1;
    }
    return 0;
}