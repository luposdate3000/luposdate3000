package lupos.s15tripleStoreDistributed

import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s03resultRepresentation.Value
import lupos.s05tripleStore.IndexPattern
import lupos.s05tripleStore.ModifyType
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s05tripleStore.TripleStoreLocal


class DistributedGraph(val name: String, val create: Boolean = false) {

    fun modifyData(transactionID: Long, vals: Value, valp: Value, valo: Value, action: ModifyType) {
        DistributedTripleStore.localStore.getNamedGraph(name, create).modifyData(transactionID, vals, valp, valo, action)
    }

    fun clear() {
        DistributedTripleStore.localStore.getNamedGraph(name, create).clear()
    }

    fun addData(key: ResultRow, value: ResultRow, store: MutableMap<ResultRow, MutableSet<ResultRow>>) {
        require(false)
    }

    fun deleteData(key: ResultRow, value: ResultRow, store: MutableMap<ResultRow, MutableSet<ResultRow>>) {
        require(false)
    }

    fun abort(transactionID: Long) {
        DistributedTripleStore.localStore.getNamedGraph(name, create).abort(transactionID)
    }

    fun commit2(transactionID: Long) {
        DistributedTripleStore.localStore.getNamedGraph(name, create).commit2(transactionID)
    }

    fun commitModifyData(vals: Value, valp: Value, valo: Value, action: (ResultRow, ResultRow, MutableMap<ResultRow, MutableSet<ResultRow>>) -> Unit) {
        DistributedTripleStore.localStore.getNamedGraph(name, create).commitModifyData(vals, valp, valo, action)
    }

    fun addData(transactionID: Long, t: List<String?>) {
        DistributedTripleStore.localStore.getNamedGraph(name, create).addData(transactionID, t)
    }

    fun deleteData(transactionID: Long, t: List<String?>) {
        DistributedTripleStore.localStore.getNamedGraph(name, create).deleteData(transactionID, t)
    }

    fun addDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        DistributedTripleStore.localStore.getNamedGraph(name, create).addDataVar(transactionID, t)
    }

    fun deleteDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        DistributedTripleStore.localStore.getNamedGraph(name, create).deleteDataVar(transactionID, t)
    }

    fun addData(transactionID: Long, iterator: ResultSetIterator) {
        DistributedTripleStore.localStore.getNamedGraph(name, create).addData(transactionID, iterator)
    }

    fun getIterator(dictionary: ResultSetDictionary): POPTripleStoreIteratorBase {
        return DistributedTripleStore.localStore.getNamedGraph(name, create).getIterator(dictionary)
    }

    fun getIterator(dictionary: ResultSetDictionary, s: String, p: String, o: String): POPTripleStoreIteratorBase {
        return DistributedTripleStore.localStore.getNamedGraph(name, create).getIterator(dictionary, s, p, o)
    }

    fun getIterator(dictionary: ResultSetDictionary, index: IndexPattern): POPTripleStoreIteratorBase {
        return DistributedTripleStore.localStore.getNamedGraph(name, create).getIterator(dictionary, index)
    }
}

object DistributedTripleStore {
    val localStore = PersistentStoreLocal()
    fun nextTransactionID(): Long {
        return localStore.nextTransactionID()
    }

    fun getGraphNames(includeDefault: Boolean = false): List<String> {
        return localStore.getGraphNames(includeDefault)
    }

    fun createGraph(name: String): DistributedGraph {
        return DistributedGraph(name)
    }

    fun dropGraph(name: String) {
        localStore.dropGraph(name)
    }

    fun clearGraph(name: String) {
        localStore.clearGraph(name)
    }

    fun getNamedGraph(name: String, create: Boolean = false): DistributedGraph {
        return DistributedGraph(name, create)
    }

    fun getDefaultGraph(): DistributedGraph {
        return DistributedGraph(localStore.defaultGraphName)
    }

    fun commit(transactionID: Long) {
        localStore.commit(transactionID)
    }
}
