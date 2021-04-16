from graph import Graph
from collections import OrderedDict
from operations.searches import dfs


# noinspection PyTypeChecker
def do(graph):
    first = dfs(graph, graph.vertices())

    size = graph.vertices_quantity()
    t_edges = [[0 for _ in range(size)] for _ in range(size)]

    for i in range(size):
        for j in range(size):
            t_edges[j][i] = graph.matrix[i][j]

    t_graph = Graph(size)
    t_graph.matrix = t_edges
    t_graph.labels = graph.labels

    final_time = first[3]

    returnable = dict()
    for i in range(len(final_time)):
        returnable[final_time[i]] = i

    returnable = OrderedDict(sorted(returnable.items(), reverse=True))
    to_search = returnable.values()

    second = dfs(t_graph, to_search)

    return second[2]  # Ancestors.
