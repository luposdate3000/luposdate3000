import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s12p2p.P2P
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.endpointServer
import lupos.s14endpoint.EndpointServerImpl

fun main(args: Array<String>) = CoroutinesHelper.runBlock {
    var i = 0
    var bootStrapServer: String? = null
    var hostname = "localhost"
    for (a in args) {
        GlobalLogger.log(ELoggerType.DEBUG, { "args[$i]=$a" })
        when (i) {
            0 -> {
                hostname = a
            }
            1 -> {
                bootStrapServer = a
            }
        }
        i++
    }
    launch(Dispatchers.Default) {
        endpointServer = EndpointServerImpl(hostname)
        P2P.start(bootStrapServer)
        endpointServer!!.start()
    }
    while (true) {
        delay(1000)
    }
}
