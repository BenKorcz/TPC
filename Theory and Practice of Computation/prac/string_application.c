#include <stdio.h>

char upshift (char c) {
    return (c >= 'a' && c <= 'z') ? c - 'a' + 'A' : c;
}

char downshift (char c) {
    return (c >= 'A' && c <= 'Z') ? c - 'A' + 'a' : c;
}

char rot13 (char c) {
    if (c >= 'A' && c <= 'M' || c >= 'a' && c <= 'm') {
        return c + 13;
    } else if (c >= 'N' && c <= 'Z' || c >= 'n' && c <= 'z') {
        return c - 13;
    } else {
        return c;
    }
}

void apply ...

int main () {
    char s[] = "The quick fox jumps!";
    puts(s);                      // -> The quick fox jumps!
    apply...
    puts(s);                      // -> THE QUICK FOX JUMPS!
    apply...
    puts(s);                      // -> the quick fox jumps!
    apply...
    puts(s);                      // -> gur dhvpx sbk whzcf!
    apply...
    puts(s);                      // -> the quick fox jumps!
}
