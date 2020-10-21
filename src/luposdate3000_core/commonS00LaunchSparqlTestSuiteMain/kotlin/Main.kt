import lupos.s00misc.Parallel
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s16network.ServerCommunicationSend
import lupos.SparqlTestSuite

fun main(args: Array<String>) = Parallel.runBlocking {
    distributedTripleStore = DistributedTripleStore()
    println("args ${args.toMutableList()}")
    SparqlTestSuite.filterList.addAll(args)
    ServerCommunicationSend.start()
    SparqlTestSuite().testMain()
}
