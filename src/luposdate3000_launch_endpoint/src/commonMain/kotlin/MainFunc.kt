import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s16network.HttpEndpointLauncher
import lupos.s16network.LuposdateEndpoint
@Suppress("NOTHING_TO_INLINE") internal inline fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    var bootStrapServer: String? = null
    var hostname = "localhost"
    for ((i, a) in args.withIndex()) {
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
                Partition.default_k = a.toInt()
            }
        }
    }
    Parallel.launch {
        HttpEndpointLauncher.start(hostname, 2324)
    }
    while (true) {
        Parallel.delay(1000)
    }
}
