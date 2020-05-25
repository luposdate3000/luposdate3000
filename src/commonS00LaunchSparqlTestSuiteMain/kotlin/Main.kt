import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s12p2p.P2P
import lupos.s14endpoint.Endpoint
import lupos.SparqlTestSuite

fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    P2P.start(null)
    SparqlTestSuite().testMain()
}
