package graphs

import java.io.File

class GraphBuilder {

    class Graph(size: Int) {
        var adjacencyMatrix: Array<FloatArray> = Array(size) { FloatArray(size) }
        val dict = mutableMapOf<Int, String>()

        fun verticesQuantity(): Int {
            return this.dict.size
        }

        fun edgesQuantity(): Int {
            var counter = 0
            this.adjacencyMatrix.forEach { row ->
                row.forEach { value ->
                    if (value != Float.MAX_VALUE) counter++
                }
            }
            return counter
        }

        fun degreeFrom(nodeID: Int): Int {
            var counter = 0
            for (i in adjacencyMatrix.indices) {
                if (adjacencyMatrix[nodeID][i] != Float.MAX_VALUE || adjacencyMatrix[i][nodeID] != Float.MAX_VALUE)
                    counter++
            }
            return counter
        }

        fun labelFrom(nodeID: Int): String? {
            return this.dict[nodeID]
        }

        fun neighborsFrom(nodeID: Int): List<Int> {
            val possibleNeighbors = this.adjacencyMatrix[nodeID]
            val neighbors: MutableList<Int> = mutableListOf()

            possibleNeighbors.forEachIndexed { index, _ ->
                if (possibleNeighbors[index] != Float.MAX_VALUE) {
                    neighbors.add(index)
                }
            }

            return neighbors
        }

        fun weightBetween(srcNodeID: Int, dstNodeID: Int): Float {
            return this.adjacencyMatrix[srcNodeID][dstNodeID]
        }

        fun hasEdge(srcNodeID: Int, dstNodeID: Int): Boolean {
            return weightBetween(srcNodeID, dstNodeID) != Float.MAX_VALUE
        }
    }

    companion object FileParser {

        fun doParsingOf(filePath: String): MutableList<String> {
            val bufferedReader = File(filePath).bufferedReader()
            val fileAsList: MutableList<String> = mutableListOf()

            bufferedReader.forEachLine {
                fileAsList.add(it)
            }

            return fileAsList
        }

    }

    fun buildGraph(filePath: String): Graph {
        val graphAsList: MutableList<String> = doParsingOf(filePath)

        fun verticesNumber(): Int {
            return graphAsList[0].replace("*vertices ", "").toInt()
        }

        val returnable = Graph(verticesNumber())

        fun buildVertices() {
            for (i in 1..verticesNumber()) {
                val line = graphAsList[i].split(" ")
                returnable.dict[line[0].toInt()] = line[1]
            }
        }
        buildVertices()

        fun buildEdges() {
            for (i in (verticesNumber() + 2)..graphAsList.lastIndex) {
                val line = graphAsList[i].split(" ")
                returnable.adjacencyMatrix[line[0].toInt() - 1][line[1].toInt() - 1] = line[2].toFloat()
            }
        }
        buildEdges()

        fun fillEdgeGaps() {
            for (i in returnable.adjacencyMatrix.indices) {
                for (j in returnable.adjacencyMatrix.indices) {
                    if (returnable.adjacencyMatrix[i][j].toInt() == 0) {
                        returnable.adjacencyMatrix[i][j] = Float.MAX_VALUE
                    }
                }
            }
        }
        fillEdgeGaps()

        return returnable
    }
}