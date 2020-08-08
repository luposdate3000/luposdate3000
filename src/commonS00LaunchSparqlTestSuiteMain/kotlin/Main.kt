import kotlinx.coroutines.runBlocking
import lupos.s16network.ServerCommunicationSend
import lupos.SparqlTestSuite

fun main(args: Array<String>) = runBlocking {
    println("args ${args.toMutableList()}")
    SparqlTestSuite.filterList.addAll(args)
    ServerCommunicationSend.start()
    SparqlTestSuite().testMain()
}
