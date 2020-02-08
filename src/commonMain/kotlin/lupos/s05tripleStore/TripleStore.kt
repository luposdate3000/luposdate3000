package lupos.s05tripleStore

import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s09physicalOperators.POPBase


enum class IndexPattern {
    S, P, O, SP, SO, PO, SPO, SOP, PSO, POS, OSP, OPS
}

abstract class POPTripleStoreIteratorBase() : POPBase() {
    var nameS = "#s" + uuid
    var nameP = "#p" + uuid
    var nameO = "#o" + uuid
    abstract fun setMNameS(n: String)
    abstract fun setMNameP(n: String)
    abstract fun setMNameO(n: String)
    abstract fun getGraphName(): String
}

class PersistentStoreLocal {
    val defaultGraphName = ""

    companion object {
        private val global_transactionID = ThreadSafeUuid()
    }

    fun nextTransactionID(): Long {
        return global_transactionID.next()
    }

    val stores = mutableMapOf<String, TripleStoreLocal>()

    inline fun forEach(action: (String, TripleStoreLocal) -> Unit) {
        for ((k, v) in stores)
            action(k, v)
    }

    inline fun getGraphNames(): List<String> {
        val res = mutableListOf<String>()
        for (t in stores.keys)
            if (t != defaultGraphName)
                res.add(t)
        return res
    }

    constructor() {
        if (stores[defaultGraphName] == null) {
            stores[defaultGraphName] = TripleStoreLocal(defaultGraphName)
        }
    }

    inline fun createGraph(name: String): TripleStoreLocal {
        val tmp = stores[name]
        if (tmp != null)
            throw Exception("PersistentStore.createGraph :: graph[$name] already exist")
        val tmp2 = TripleStoreLocal(name)
        stores[name] = tmp2
        return tmp2
    }

    inline fun dropGraph(name: String) {
        require(name != defaultGraphName)
        if (stores[name] == null)
            throw Exception("PersistentStore.dropGraph :: graph[$name] did not exist")
        stores.remove(name)
    }

    inline fun dropGraphAll() {
        stores.clear()
        createGraph(defaultGraphName)
    }

    inline fun clearGraph(name: String) {
        getNamedGraph(name).truncate()
    }

    inline fun clearGraphAll() {
        for (v in stores.values) {
            v.truncate()
        }
    }

    inline fun getNamedGraph(name: String, create: Boolean = false): TripleStoreLocal {
        val tmp = stores[name]
        if (tmp != null || !create)
            return tmp!!
        return createGraph(name)
    }

    inline fun getDefaultGraph(): TripleStoreLocal {
        return getNamedGraph(defaultGraphName)
    }

    inline fun commit(transactionID: Long) {
        for (v in stores.values) {
            v.commit2(transactionID)
        }
    }
}

