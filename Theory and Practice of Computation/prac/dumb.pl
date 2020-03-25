dumb(A,B):- dumber(A,[A,B]).
dumber(A,[B,A]):- dumberest(A,B).
dumberest([A|B],[A,B]).

