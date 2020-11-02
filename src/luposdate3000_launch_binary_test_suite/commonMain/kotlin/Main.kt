import lupos.s00misc.BinaryTestCase
import lupos.s00misc.Parallel
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.SparqlTestSuite

internal fun main(args: Array<String>): Unit {
    println("main a")
    distributedTripleStore = DistributedTripleStore()
    println("main b")
    Parallel.runBlocking {
        try {
            if (args.size == 1) {
                BinaryTestCase.executeAllTestCase(args[0])
            } else if (args.size > 0) {
                BinaryTestCase.executeTestCase(args[0] + "/" + args[1])
            } else {
                BinaryTestCase.executeAllTestCase()
            }
        } catch (e: Exception) {
            println("going to terminate now")
            throw e
        }
    }
}
