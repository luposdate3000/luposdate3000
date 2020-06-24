import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s16network.ServerCommunicationSend
import lupos.SparqlTestSuite

fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    println("args ${args.toMutableList()}")
    SparqlTestSuite.filterList.addAll(args)
    ServerCommunicationSend.start()
    SparqlTestSuite().testMain()
}
