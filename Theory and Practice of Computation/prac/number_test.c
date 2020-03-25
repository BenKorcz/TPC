#include <stdio.h>

enum tag_type { invalid, integral, floating };

struct number {
    enum tag_type tag;
    union {
        int i;
        double d;
    } value;
};

void print(struct number n) {
    switch (n.tag) {
    case invalid:
        printf("  <<< invalid number in \"print\" >>> "); break;
    case integral:
        printf("%d", n.value.i); break;
    case floating:
        printf("%g", n.value.d); break;
    }
}

struct number plus (struct number l, struct number r) {
    struct number result; result.tag = invalid;

    if(l.tag!=r.tag){
        printf("Type error....!\n");
    }else{
        result.tag = l.tag;
        if(l.tag==integral){
            int result_num = l.value.i + r.value.i;
            result.value.i = result_num;
        }else{
            double result_num = l.value.d + r.value.d;
            result.value.d = result_num;
        }
    }
    /* more code here */

    return result;
}

int main () {
    struct number a, b;

    a.tag = integral; a.value.i = 5;                // a is integral
    b.tag = integral; b.value.i = 6;                // so is b

    printf("a = "); print(a); printf("\n");
    printf("b = "); print(b); printf("\n");
                                                    // OK: both integral
    printf("a + b = "); print(plus(a, b)); printf("\n");

    a.tag = floating; a.value.d = 5.7;              // a is now floating
    printf("a = "); print(a); printf("\n");
                                                    // type mismatch expected
    printf("a + b = "); print(plus(a, b)); printf("\n");

    b.tag = floating; b.value.d = 3.2;              // b is now floating
    printf("b = "); print(b); printf("\n");
                                                    // OK: both floating
    printf("a + b = "); print(plus(a, b)); printf("\n");

    return 0;
}
