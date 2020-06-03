package lupos.s04logicalOperators

import lupos.s00misc.Coverage
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class Query(val dictionary: ResultSetDictionary = ResultSetDictionary(), val transactionID: Long = global_transactionID.next()) {
    var workingDirectory = ""
    var commited = false

    companion object {
        private val global_transactionID = ThreadSafeUuid()
    }

    fun commit() {
        DistributedTripleStore.commit(this)
        commited = true
    }

    protected fun finalize() {
    }
}
