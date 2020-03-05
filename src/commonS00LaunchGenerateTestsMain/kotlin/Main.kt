import lupos.*
import lupos.s00misc.*
import lupos.s12p2p.P2P
import lupos.s14endpoint.EndpointImpl

fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    P2P.knownClients.add(EndpointImpl.fullname)
    SparqlTestSuite().testMain()
    printAllMicroTest()
}
