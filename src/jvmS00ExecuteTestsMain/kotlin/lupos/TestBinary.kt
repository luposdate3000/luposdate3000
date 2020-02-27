package lupos
import lupos.s00misc.executeBinaryTests
import lupos.s12p2p.P2P
import lupos.s14endpoint.EndpointImpl



fun main(args: Array<String>) {
//https://github.com/Barro/java-afl#caveats
    P2P.knownClients.add(EndpointImpl.fullname)
    executeBinaryTests("src/commonTest/kotlin/lupos")
}
