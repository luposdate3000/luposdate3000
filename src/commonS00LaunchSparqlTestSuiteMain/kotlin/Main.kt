import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.SparqlTestSuite
import lupos.s16network.ServerCommunicationSend

fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    ServerCommunicationSend.start(null)
    SparqlTestSuite().testMain()
}
