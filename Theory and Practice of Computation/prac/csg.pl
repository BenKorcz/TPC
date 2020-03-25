s --> np(X), copular(X), np(X).
s --> np(X), copular(X), [not], np(X).
s --> np(X), tv(X), np(_).
np(X) --> det(X), snp(X).
np(npg(sg,1,_)) --> ['I'].
np(npg(pl,1,_)) --> [we].
np(npg(_,2,_)) --> [you].
np(npg(sg,3,m)) --> [he].
np(npg(sg,3,f)) --> [she].
np(npg(pl,3,_)) --> [they].
snp(X) --> noun(X).
snp(X) --> adj(X), snp(X).
noun(npg(sg,3,m)) --> [boy].
noun(npg(sg,3,_)) --> [dog].
noun(npg(sg,3,f)) --> [girl].
noun(npg(pl,3,m)) --> [boys].
noun(npg(pl,3,_)) --> [dogs].
noun(npg(pl,3,f)) --> [girls].
adj(_) --> [big].
adj(_) --> [brown].
det(npg(_,3,_)) --> [the].
det(npg(sg,3,_)) --> [a].
tv(npg(sg,3,_)) --> [hits].
tv(npg(N,P,_)) --> [hit], {N/P \== sg/3}.
copular(npg(sg,1,_)) --> [am].
copular(npg(sg,3,_)) --> [is].
copular(npg(pl,_,_)) --> [are].
copular(npg(_,2,_)) --> [are].
copular(_), [not] --> [aint].
