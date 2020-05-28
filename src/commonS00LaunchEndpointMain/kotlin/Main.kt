import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s16network.HttpEndpointLauncher
import lupos.s16network.ServerCommunicationSend
fun main(args: Array<String>) = CoroutinesHelper.runBlock {
Coverage.funStart(13898)
    var i = 0
Coverage.statementStart(13899)
    var bootStrapServer: String? = null
Coverage.statementStart(13900)
    var hostname = "localhost"
Coverage.statementStart(13901)
    for (a in args) {
Coverage.forLoopStart(13902)
        GlobalLogger.log(ELoggerType.DEBUG, { "args[$i]=$a" })
Coverage.statementStart(13903)
        when (i) {
            0 -> {
Coverage.whenCaseStart(13905)
                hostname = a
Coverage.statementStart(13906)
            }
            1 -> {
Coverage.whenCaseStart(13907)
                bootStrapServer = a
Coverage.statementStart(13908)
            }
        }
Coverage.statementStart(13909)
        i++
Coverage.statementStart(13910)
    }
Coverage.statementStart(13911)
    launch(Dispatchers.Default) {
Coverage.statementStart(13912)
        HttpEndpointLauncher.start(hostname)
Coverage.statementStart(13913)
    }
Coverage.statementStart(13914)
    launch(Dispatchers.Default) {
Coverage.statementStart(13915)
        ServerCommunicationSend.start(hostname, lupos.s16network.NETWORK_DEFAULT_PORT, bootStrapServer)
Coverage.statementStart(13916)
    }
Coverage.statementStart(13917)
    while (true) {
Coverage.whileLoopStart(13918)
        delay(1000)
Coverage.statementStart(13919)
    }
Coverage.statementStart(13920)
}
