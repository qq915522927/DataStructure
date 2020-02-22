#ifndef FRAMES_H
#define FRAMES_H

#include "linkedList.h"

/**
 *  get freeframe from frames list
 * @param frames Linkedlist
 * @return n of frame
 */
int alloc_frame(List * frames);

int free_frame(List * frames, int frame_n);

#endif