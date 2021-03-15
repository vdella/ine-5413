package operations

import graphs.GraphBuilder

object Operator {

    fun showBFS(nodeID: Int, graph: GraphBuilder.Graph) {
        doBreadthFirstSearch(nodeID, graph).forEach { (key, value) -> println("$key: $value")}
    }

    private fun doBreadthFirstSearch(nodeID: Int, graph: GraphBuilder.Graph): MutableMap<Int, MutableList<Int>> {
        val visited = MutableList(graph.verticesQuantity()) { false }
        val queue = mutableListOf<Int>()
        val nodeTree = mutableMapOf<Int, MutableList<Int>>()

        queue.add(0, nodeID)
        visited[nodeID] = true

        nodeTree[0] = mutableListOf(nodeID)

        var level = 1
        while (queue.isNotEmpty()) {
            val popped: Int = queue.removeAt(0)

            graph.adjacencyMatrix[popped].forEachIndexed { j: Int, weight ->
                if (! visited[j] && weight != Float.MAX_VALUE) {
                    queue.add(j)

                    if (nodeTree[level] == null)
                        nodeTree[level] = mutableListOf()

                    visited[j] = true
                    nodeTree[level]!!.add(j)
                }
            }

            level++
        }

        return nodeTree
    }

    fun showDijkstra(src: Int, graph: GraphBuilder.Graph) {
        val result = doDijkstra(src, graph)
        for (i in 0 until result.size) {
            val distance = result[i]
            println("$i: d=$distance")
        }
    }

    private fun doDijkstra(src: Int, graph: GraphBuilder.Graph): MutableList<Float> {
        val distance = MutableList(graph.verticesQuantity()) { Float.MAX_VALUE }
        distance[src] = 0.0F

        val visited = MutableList(graph.verticesQuantity()) { false }

        val queue = MutableList(0) { 0 }
        queue.add(0, src)

        while (queue.isNotEmpty()) {
            val head = queue.removeAt(0)
            val neighbors = graph.neighborsFrom(head)
            visited[head] = true

            for (i in 0 until graph.degreeFrom(head)) {
                val neighbor = neighbors[i]
                val weight = graph.weightBetween(head, neighbor).toInt()

                if (distance[neighbor] > distance[head] + weight && ! visited[neighbor]) {
                    distance[neighbor] = distance[head] + weight
                    queue.add(neighbor)
                }
            }
        }

        return distance
    }

    fun showFW(graph: GraphBuilder.Graph) {
        val result = doFloydWarshall(graph)
        val size = graph.verticesQuantity()
        for (i in 0 until size) {
            print("$i: ")
            for (j in 0 until size) {
                val point = result[i][j]
                if (j != size -1) print("${point.toInt()}, ")
                else print("${point.toInt()}")
            }
            println()
        }
    }

    private fun doFloydWarshall(graph: GraphBuilder.Graph): Array<FloatArray> {
        val size = graph.verticesQuantity()
        val distance: Array<FloatArray> = graph.adjacencyMatrix.clone()

        for (k in 0 until size) {
            for (i in 0 until size) {
                for (j in 0 until size) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j]
                    }
                }
            }
        }

        return distance
    }
}