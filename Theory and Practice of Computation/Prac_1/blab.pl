get_vars :-
    write('message?'),
    nl,
    read(X),
    write('repeats?'),
    read(Y),
    blab_print(Y,X).

blab_print(N,X) :-
    N>0,
    M is N-1,
    write(X),
    nl,
    blab_print(M,X).

