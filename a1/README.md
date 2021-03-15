# ine-5413

> A1

Through _Activity 1_'s implementation, the main difficult was to deal with file parsing,
such as we would be reading .net files with a common pattern: they all start with a line
indicating how many vertices the to-be-built graph has and, from that, we can find
lines starting with a given node's id and, next to it, it's value to be stored
(all the latter are stored as `String`'s; this will be explained through further reading).

>> Parsing

An example of file would be `dolphins.net`. Here's a sample:

*vertices 62\
1 0   <- *Second line*\
2 1\
3 2\
4 3\
5 4\
6 5\
7 6\
8 7\
9 8

62 will be our vertex quantity for that given graph. Through parsing, we will be reading
the files in a way that, at the second line, we'll have node 1 with value equal as 0.

There will be a moment for every file inside `/resources`' folder in which we'll find the
graph's edges. This will be visible as the line will be marked as `*edges`.

For dealing with the graph's edges, let's take look at another sample from the same file:

*edges\
1 41 1.0\
1 11 1.0\
1 48 1.0\
1 43 1.0\
1 15 1.0\
1 16 1.0\
2 18 1.0

After the `*edges` line, until EOF, we'll be taking every line as a `String` array, in which
first two position indicate the matrix indices, and the third position indicates the weight
between two vertices.

>> Building

During building phase, we'll be delivering a private class' data structure which has
a dictionary and a matrix, working as the adjacency list and adjacency matrix, respectively.

For easing the legibility and code's organization, that said dictionary works as abstraction
to the graph's vertices while the matrix works as abstraction to the graph's edges.
This way we hide the graphs interns, in an attempt to preserve _Demeter's Law_.

For enhancing motivation to why the graph has an adjacency list followed by an adjacency matrix,
we could use the _Bread First Search_ algorithm as proof of concept for our line of thought:
for that said algorithm, we'll want to use the graph as a queue/list, which the dictionary
will take care of, but, for other algorithm implementations, such as checking if a given graph is
a Complete Graph, we must use the matrix to ease operation handling and writing process.

With that being said, we now can analyse to why all graph node's values are casted to `String`'s.
Let's take `ContemCicloEuleriano.net` as an example:

*vertices 6\
1 "Itajai"\
2 "Camboriu"\
3 "Brusque"\
4 "Nova Trento"\
5 "Guabiruba"\
6 "Navegantes"\
*edges\
1 2 1.0\
1 6 1.0\
2 3 1.0\
3 6 1.0\
3 4 1.0\
3 5 1.0\
4 5 1.0

This graph has its first node's value as "Itajaí", which, evidently, cannot be correctly casted
to an `Int` value without leading to confusion: how would we know which graph has `String` values
and which has `Int` values? Using generics would be an alternative, but doing the proper
parsing for a value of type T to `Int` in case we find `Int` values, or to `String` otherwise,
would only help to make the code unorganized, which would lead, therefore, to possible difficulties
to understand this API. That's why all graph node's values are automatically treated as `String`s. 