#include <stdio.h>

class number {
public:
    enum tag_type { invalid, integral, floating };

    tag_type tag;
    union {
        int value_i;
        double value_d;
    };

    number() { tag = invalid; }
    number(int v) { tag = integral; value_i = v; }
    number(double v) { tag = floating; value_d = v; }

    void print() {
        switch (tag) {
        case invalid:
            printf("  <<< invalid number in \"print\" >>> "); break;
        case integral:
            printf("%d", value_i); break;
        case floating:
            printf("%g", value_d); break;
        }
    }

};

number operator + (number l, number r) {
    number result;

    // more code here

    return result;
}

int main () {
    number a, b;

    a = number(5);                                  // a is integral
    b = number(6);                                  // so is b

    printf("a = "); a.print(); printf("\n");
    printf("b = "); b.print(); printf("\n");
    printf("a + b = "); (a+b).print(); printf("\n"); // OK: both integral

    a = number(5.7);                                // a is now floating
    printf("a = "); a.print(); printf("\n");
    printf("a + b = "); (a+b).print(); printf("\n"); // type mismatch expected

    b = number(3.2);                                // b is now floating
    printf("b = "); b.print(); printf("\n");
    printf("a + b = "); (a+b).print(); printf("\n"); // OK: both floating

    return 0;
}
