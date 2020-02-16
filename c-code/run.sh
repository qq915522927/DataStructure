#!/bin/bash

if [ ! -d "build" ];then
  mkdir build
fi

if [ -d "example/$1" ]
then
  cc src/*.c example/$1/main.c -I ./include  -o build/$1
  cd build
  ./$1
else
  echo "example does not exists."
  echo "example list :"
  echo `ls example`
fi