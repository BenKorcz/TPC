#include <stdio.h>
#include <stdlib.h>

int main()
{
    int repeats;
    char message[40];

    printf("Message?");
    scanf("%[^\n]%*c", &message);
    printf("repeats?");
    scanf("%d", &repeats);


    for(int i=0;i<repeats;i++){
        printf("%s\n", message);
    }

    return 0;
}