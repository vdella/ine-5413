import graphs.GraphBuilder
import operations.Operator

fun main() {
    val builder = GraphBuilder()
    val list = builder.buildGraph("src/main/resources/test.txt")
    println(list.verticesQuantity())
    println(list.edgesQuantity())
    println(list.weightBetween(0, 1))
    println(Operator.showBFS(2, list))
}