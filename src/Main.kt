import graphs.GraphBuilder

class Runner {

    fun main(args: Array<String>) {
        val builder = GraphBuilder()
        val graphSample = builder.buildGraph("src/main/resources/karate.net")
        println(graphSample.isDirected())
    }
}