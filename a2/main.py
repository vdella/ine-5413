import graph
from operations import strongly_connected_components, topological_sort
from collections import deque


def main():
    sample = graph.build_from('resources/manha.net')
    print(strongly_connected_components.do(sample))

    sample = graph.build_from('resources/manha.net')
    stack = deque()
    print(topological_sort.show(sample, 4, stack))


if __name__ == "__main__":
    main()
