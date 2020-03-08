package lupos.s04logicalOperators

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class Query(val dictionary: ResultSetDictionary = ResultSetDictionary(), val transactionID: Long = global_transactionID.next()) {
    var commited = false

val variableNameMapping=mutableMapOf<String,String>()

    companion object {
        private val global_transactionID = ThreadSafeUuid()

    }

    fun commit() {
//TODO require(!commited)
        DistributedTripleStore.commit(this)
        commited = true
    }

    protected fun finalize() {
//TODO require(commited)
    }
}
