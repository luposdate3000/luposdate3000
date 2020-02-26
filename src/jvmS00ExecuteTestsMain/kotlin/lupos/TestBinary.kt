package lupos

import lupos.s00misc.*
import lupos.s14endpoint.EndpointImpl
import lupos.s12p2p.P2P
fun main(args: Array<String>) {
 P2P.knownClients.add(EndpointImpl.fullname)
    executeBinaryTests("src/commonTest/kotlin/lupos")
}
