import lupos.*
import lupos.s00misc.*
import lupos.s12p2p.P2P
import lupos.s14endpoint.*

fun main(args: Array<String>) = CoroutinesHelper.runBlock {
endpointServer = EndpointServerImpl("localhost")
P2P.start(null)
    SparqlTestSuite().testMain()
    printAllMicroTest()
}
