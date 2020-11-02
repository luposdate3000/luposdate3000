import lupos.s00misc.BinaryTestCase
import lupos.s00misc.Parallel
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s15tripleStoreDistributed.DistributedTripleStore

internal fun main(args: Array<String>): Unit {
    distributedTripleStore = DistributedTripleStore()
    Parallel.runBlocking {
        if (args.size == 1) {
            BinaryTestCase.executeAllTestCase(args[0])
        } else if (args.size > 0) {
            BinaryTestCase.executeTestCase(args[0] + "/" + args[1])
        } else {
            BinaryTestCase.executeAllTestCase()
        }
    }
}
