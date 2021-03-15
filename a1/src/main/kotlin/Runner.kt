import graphs.GraphBuilder
import operations.Operator

fun main() {
    val builder = GraphBuilder()
    val graphSample = builder.buildGraph("src/main/resources/karate.net")
    println(graphSample.isDirected())
    println(graphSample.edgesQuantity())
    println("BREADTH-FIRST SEARCH: ")
    println(Operator.showBFS(3, graphSample))
    println()
    println("DIJKSTRA: ")
    println(Operator.showDijkstra(3, graphSample))
    println(Operator.showFW(graphSample))
}