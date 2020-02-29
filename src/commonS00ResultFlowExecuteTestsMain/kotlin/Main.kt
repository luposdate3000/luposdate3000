import lupos.s00misc.executeBinaryTest
import lupos.s00misc.*
import lupos.*
import lupos.s12p2p.P2P
import lupos.s14endpoint.EndpointImpl


fun main(args: Array<String>) {
    println(args.toList())
    return
    P2P.knownClients.add(EndpointImpl.fullname)
    if (args.isEmpty())
        executeBinaryTests("src/commonTest/kotlin/lupos")
    else
        executeBinaryTest(args[0], true)
}
