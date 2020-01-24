package lupos.p2p

import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) = runBlocking {
    var i = 0
    var serverPort = 0
    var serverName = ""
    var bootStrapServer: String? = null
    for (a in args) {
        println("args[$i]=$a")
        when (i) {
            0 -> P2P.port = a.toInt()
            1 -> P2P.hostname = a
            2 -> bootStrapServer = a
        }
        i++
    }
        launch(Dispatchers.Default) {
            P2P.start(bootStrapServer)
        }
println("todo deleteme")
    while (true) {
        delay(1000)
    }
}
