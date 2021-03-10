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

    private fun searchForPath(nodeID: Int, graph: GraphBuilder.Graph) {}

    fun searchForEulerianPathIn(graph: GraphBuilder.Graph) {}

    fun doBellmanFord() {}

    fun doDepthFirstSearch() {}

    fun doDijkstra() {}
}