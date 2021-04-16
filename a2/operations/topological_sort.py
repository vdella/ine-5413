from collections import deque
from operations.searches import _visit


def show(graph, milestone: int, stack: deque):
    result: deque = do(graph, milestone, stack)
    for i in result:
        print(graph.labels[i] + ' -> ', end="")


def do(graph, milestone: int, stack: deque) -> deque:
    size = len(graph.vertices())

    iterable = list()
    iterable.append(milestone)

    for i in range(size):
        if i != milestone:
            iterable.append(i)

    visited = [False] * size

    initial_time = [float('inf')] * size

    final_time = [float('inf')] * size

    time = list()
    time.append(0)

    ancestors = [None] * size

    for i in iterable:
        if not visited[i - 1] and visited.count(False) > 0:
            _visit(graph, i, visited, initial_time, ancestors, final_time, time, stack)

    return stack
