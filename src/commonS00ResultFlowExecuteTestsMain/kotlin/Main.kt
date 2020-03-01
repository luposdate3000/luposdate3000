import lupos.*
import lupos.s00misc.*
import lupos.s00misc.executeBinaryTest
import lupos.s12p2p.P2P
import lupos.s14endpoint.EndpointImpl


fun main(args: Array<String>) {
val input=File.readStdInAsDynamicByteArray()

    P2P.knownClients.add(EndpointImpl.fullname)
if(input!=null)
executeBinaryTest(input!!)
else    if (args.isEmpty())
        executeBinaryTests("/opt/tmpfs")
    else
        executeBinaryTest(args[0], true)
}
