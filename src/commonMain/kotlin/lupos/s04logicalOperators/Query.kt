package lupos.s04logicalOperators
import lupos.s15tripleStoreDistributed.DistributedTripleStore
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.*

class Query(val dictionary: ResultSetDictionary=ResultSetDictionary(), val transactionID: Long= global_transactionID.next()) {
var commited=false
companion object {
        private val global_transactionID = ThreadSafeUuid()

    }
fun commit(){
//TODO require(!commited)
DistributedTripleStore.commit(this)
commited=true
}
protected fun finalize() {
//TODO require(commited)
}
}
