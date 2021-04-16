package a1

import graphs.GraphBuilder

object FloydWarshall {

    fun show(graph: GraphBuilder.Graph) {
        val result = doAt(graph)
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

    private fun doAt(graph: GraphBuilder.Graph): Array<FloatArray> {
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