import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s16network.ServerCommunicationSend
import lupos.SparqlTestSuite
fun main(args: Array<String>) = CoroutinesHelper.runBlock {
Coverage.funStart(13921)
    ServerCommunicationSend.start()
Coverage.statementStart(13922)
    SparqlTestSuite().testMain()
Coverage.statementStart(13923)
}
