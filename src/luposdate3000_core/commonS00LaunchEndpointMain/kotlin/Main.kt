import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s16network.HttpEndpointLauncher

fun main(args: Array<String>) = Parallel.runBlocking {
    distributedTripleStore = DistributedTripleStore()
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
    while (true) {
        Parallel.delay(1000)
    }
}
