p(X,Y) :- 
    Z = (X+Y),
    write(Z).

r(X) :-
    Y is 4,
    p(X,Y).

q_(X,Y) :- 
    X is 3,
    r(X).