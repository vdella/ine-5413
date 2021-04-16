class Graph:

    def __init__(self, size: int):
        self.matrix = [[float('inf') for _ in range(size)] for _ in range(size)]
        self.labels = dict()

    def vertices(self):
        return self.labels.keys()

    def vertices_quantity(self) -> int:
        return len(self.vertices())

    def edges_quantity(self) -> int:
        counter = 0
        for i in range(self.vertices_quantity()):
            for j in range(self.vertices_quantity()):
                if self.matrix[i][j] != float('inf'):
                    counter += 1
        return counter

    def degree_from(self, node: int) -> int:
        return len(self.neighbors_from(node))

    def label_from(self, node: int) -> int:
        return self.labels[node]

    def neighbors_from(self, node: int) -> list:
        neighbors = list()
        for j in range(self.vertices_quantity()):
            if self.has_edge(node - 1, j):
                neighbors.append(j + 1)

        return neighbors

    def weight_between(self, src: int, dst: int) -> float:
        return self.matrix[src][dst]

    def has_edge(self, src: int, dst: int) -> bool:
        return True if self.weight_between(src, dst) != float('inf') else False


def build_from(filepath: str) -> Graph:
    with open(filepath, 'r') as file:
        text = file.readlines()

        _, size = text[0].split()
        size = int(size)
        returnable = Graph(size)

        def build_vertices():
            for i in range(1, size + 1):
                actual = text[i].split()
                returnable.labels[int(actual[0])] = " ".join(actual[1::])

        build_vertices()

        def build_arcs():
            for i in range(size + 2, len(text)):
                line = text[i].split()
                returnable.matrix[int(line[0]) - 1][int(line[1]) - 1] = float(line[2])
        build_arcs()

        if text[size + 1] == "*arcs":

            def build_edges():
                for i in range(size + 2, len(text)):
                    line = text[i].split()
                    returnable.matrix[int(line[1]) - 1][int(line[0]) - 1] = float(line[2])
            build_edges()

    return returnable
