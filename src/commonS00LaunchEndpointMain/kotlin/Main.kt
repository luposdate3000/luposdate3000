import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s16network.HttpEndpointLauncher
import lupos.s16network.ServerCommunicationSend

fun main(args: Array<String>) = runBlocking {
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
        HttpEndpointLauncher.start(hostname, 2324)
    }
    launch(Dispatchers.Default) {
        ServerCommunicationSend.start(hostname, lupos.s16network.NETWORK_DEFAULT_PORT, bootStrapServer)
    }
    while (true) {
        delay(1000)
    }
}
