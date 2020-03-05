import lupos.*
import lupos.s00misc.*
import lupos.s12p2p.P2P
import lupos.s14endpoint.*

fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    P2P.knownClients.add(endpointServer!!.fullname)
    SparqlTestSuite().testMain()
    printAllMicroTest()
}
