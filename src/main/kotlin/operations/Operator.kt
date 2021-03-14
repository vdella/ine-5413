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

//    fun showEulerianPathOf(graph: GraphBuilder.Graph) {
//        val result = searchForEulerianPathAt(graph)
//        if (result.first) {
//            println(1)
//            println(result.second)
//        }
//        else println(0)
//    }

//    private fun searchForEulerianPathAt(graph: GraphBuilder.Graph): Pair<Boolean, List<Int>?> {
//        if (! graph.isEulerianCyclePossible()) return Pair(false, null)
//
//        val visited = MutableList(graph.edgesQuantity()) { false }
//        val pairedResult = searchForSubPath(0, graph, visited)
//
//        return if (! pairedResult.first) Pair(false, null)
//        else {
//            if (visited.contains(false)) Pair(false, null)
//            else Pair(true, pairedResult.second)
//        }
//    }
//
//    private fun searchForSubPath(nodeID: Int,
//                                 graph: GraphBuilder.Graph,
//                                 visited: MutableList<Boolean>): Pair<Boolean, List<Int>?> {
//        val eulerianPath = mutableListOf(nodeID)
//        var changeableNode = nodeID
//        do {
//            if (! visited.contains(false)) return Pair(false, null)
//
//            var firstFalseFound = false
//
//            if (graph.neighborsFrom(changeableNode).isEmpty()) return Pair(false, null)
//
//            graph.neighborsFrom(changeableNode).forEachIndexed { _, node ->
//                if (! visited[node] && !firstFalseFound) {
//                    changeableNode = node
//                    firstFalseFound = true
//                }
//            }
//
//            eulerianPath.add(changeableNode)
//            visited[changeableNode] = true
//        } while (changeableNode != nodeID)
//
//        eulerianPath.forEachIndexed { _, node ->
//            if (! visited[node]) {
//                val cycleTuple = searchForSubPath(node, graph, visited)
//                if (! cycleTuple.first) return Pair(false, null)
//            }
//        }
//        return Pair(true, eulerianPath)
//    }

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

    fun doFloydWarshall() {

    }
}