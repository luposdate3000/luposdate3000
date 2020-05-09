import lupos.s00misc.CoroutinesHelper
import lupos.s12p2p.P2P
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.endpointServer
import lupos.s14endpoint.EndpointServerImpl
import lupos.SparqlTestSuite


fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    endpointServer = EndpointServerImpl("localhost")
    P2P.start(null)
    SparqlTestSuite().testMain()
}
