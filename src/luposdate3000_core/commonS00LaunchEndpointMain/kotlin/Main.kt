import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s16network.HttpEndpointLauncher
import lupos.s16network.ServerCommunicationSend

fun main(args: Array<String>) = Parallel.runBlocking {
    var i = 0
    var bootStrapServer: String? = null
    var hostname = "localhost"
    for (a in args) {
        println("args[$i]=$a")
        when (i) {
            0 -> {
                hostname = a
            }
            1 -> {
                if (a != "null") {
                    bootStrapServer = a
                }
            }
            2 -> {
                Partition.k = a.toInt()
            }
        }
        i++
    }
    Parallel.launch {
        HttpEndpointLauncher.start(hostname, 2324)
    }
    Parallel.launch {
        ServerCommunicationSend.start(hostname, lupos.s16network.NETWORK_DEFAULT_PORT, bootStrapServer)
    }
    while (true) {
        Parallel.delay(1000)
    }
}
