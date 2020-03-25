#include <stdio.h>

void main(){

    int i=0;
    i = ++i + i++;
    printf("%d\n", i);
}