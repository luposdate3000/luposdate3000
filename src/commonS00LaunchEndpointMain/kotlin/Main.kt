import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s16network.ServerCommunicationSend
import lupos.s16network.HttpEndpointLauncher

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
        HttpEndpointLauncher.start(hostname)
    }
    launch(Dispatchers.Default) {
        ServerCommunicationSend.start(bootStrapServer)
    }
    while (true) {
        delay(1000)
    }
}
