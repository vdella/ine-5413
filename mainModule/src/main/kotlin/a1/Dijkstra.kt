package a1

import graphs.GraphBuilder

object Dijkstra {

    fun show(src: Int, graph: GraphBuilder.Graph) {
        val result = doAt(src, graph)
        for (i in 0 until result.size) {
            val distance = result[i]
            println("$i: d=$distance")
        }
    }

    private fun doAt(src: Int, graph: GraphBuilder.Graph): MutableList<Float> {
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
}