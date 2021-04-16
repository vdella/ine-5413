from graph import Graph
from collections import deque


def dfs(graph: Graph, nodes: list):
    size = len(nodes)

    visited = [False] * size

    initial_time = [float('inf')] * size

    final_time = [float('inf')] * size

    ancestors = [None] * size

    time = list()
    time.append(0)

    for i in nodes:
        if not visited[i - 1] and visited.count(False) > 0:
            _visit(graph, i, visited, initial_time, ancestors, final_time, time, deque())

    return visited, initial_time, ancestors, final_time


def _visit(graph: Graph, i, visited, initial_time, ancestors, final_time, time, stack):
    visited[i - 1] = True
    time[0] += 1
    initial_time[i - 1] = time[0]

    for j in graph.neighbors_from(i):
        if not visited[j - 1]:
            ancestors[j - 1] = i
            _visit(graph, j, visited, initial_time, ancestors, final_time, time, stack)

    time[0] += 1
    final_time[i - 1] = time[0]

    stack.appendleft(i)
