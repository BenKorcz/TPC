#include <stdio.h>

int count (int n) {
    if (n <= 0) {
        return 0;
    } else {
        return count(n - 1) + 1;
    }
}

main () {
    int i;
    printf("Count to what? "); scanf("%d", &i);
    printf("Counted to %d\n", count(i));
}
