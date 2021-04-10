import a1.BFS
import a1.Dijkstra
import a1.FloydWarshall
import graphs.GraphBuilder

fun main() {
    val builder = GraphBuilder()
    val graphSample = builder.buildGraph("src/main/resources/karate.net")
    println(graphSample.isDirected())
    println(graphSample.edgesQuantity())
    println("BREADTH-FIRST SEARCH: ")
    println(BFS.show(3, graphSample))
    println()
    println("DIJKSTRA: ")
    println(Dijkstra.show(3, graphSample))
    println()
    println("FLOYD-WARSHALL: ")
    println(FloydWarshall.show(graphSample))
}