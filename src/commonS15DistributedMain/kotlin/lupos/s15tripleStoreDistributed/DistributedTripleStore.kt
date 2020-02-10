package lupos.s15tripleStoreDistributed

import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.noinput.*
import lupos.s05tripleStore.IndexPattern
import lupos.s05tripleStore.ModifyType
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s05tripleStore.TripleStoreLocal
import lupos.s12p2p.*


class DistributedGraph(val name: String) {

    fun clear() {
        DistributedTripleStore.localStore.getNamedGraph(name).clear()
    }

    fun addData(transactionID: Long, t: List<String?>) {
        DistributedTripleStore.localStore.getNamedGraph(name).addData(transactionID, t)
    }

    fun deleteData(transactionID: Long, t: List<String?>) {
        DistributedTripleStore.localStore.getNamedGraph(name).deleteData(transactionID, t)
    }

    fun addDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        DistributedTripleStore.localStore.getNamedGraph(name).addDataVar(transactionID, t)
    }

    fun deleteDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        DistributedTripleStore.localStore.getNamedGraph(name).deleteDataVar(transactionID, t)
    }

    fun addData(transactionID: Long, iterator: ResultSetIterator) {
        DistributedTripleStore.localStore.getNamedGraph(name).addData(transactionID, iterator)
    }

    fun getIterator(dictionary: ResultSetDictionary): POPTripleStoreIteratorBase {
        return DistributedTripleStore.localStore.getNamedGraph(name).getIterator(dictionary)
    }

    fun getIterator(dictionary: ResultSetDictionary, s: String, p: String, o: String): POPTripleStoreIteratorBase {
        return DistributedTripleStore.localStore.getNamedGraph(name).getIterator(dictionary, s, p, o)
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
        P2P.execGraphOperation(name, GraphOperationType.CREATE)
        return DistributedGraph(name)
    }

    fun dropGraph(name: String) {
        P2P.execGraphOperation(name, GraphOperationType.DROP)
    }

    fun clearGraph(name: String) {
        println("DistributedTripleStore.clearGraph $name")
        P2P.execGraphOperation(name, GraphOperationType.CLEAR)
    }

    fun getNamedGraph(name: String, create: Boolean = false): DistributedGraph {
	if(create && ! (localStore.getGraphNames(true).contains(name)))
		createGraph(name)
        return DistributedGraph(name)
    }

    fun getDefaultGraph(): DistributedGraph {
        return DistributedGraph(localStore.defaultGraphName)
    }

    fun commit(transactionID: Long) {
        localStore.commit(transactionID)
    }
}
