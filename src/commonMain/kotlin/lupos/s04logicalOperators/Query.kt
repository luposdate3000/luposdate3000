package lupos.s04logicalOperators
import lupos.s00misc.Coverage
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s15tripleStoreDistributed.DistributedTripleStore
class Query(val dictionary: ResultSetDictionary = ResultSetDictionary(), val transactionID: Long = global_transactionID.next()) {
    var commited = false
    companion object {
        private val global_transactionID = ThreadSafeUuid()
    }
    fun commit() {
Coverage.funStart(5056)
        DistributedTripleStore.commit(this)
Coverage.statementStart(5057)
        commited = true
Coverage.statementStart(5058)
    }
    protected fun finalize() {
Coverage.funStart(5059)
    }
}
