package lupos.s08tripleStore
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s07physicalOperators.POPBase



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

val globalStore = PersistentStore()

class PersistentStore {
    val defaultGraphName = ""

    companion object {
        private val global_transactionID = ThreadSafeUuid()
    }

    fun nextTransactionID(): Long {
        return global_transactionID.next()
    }

    val stores = mutableMapOf<String, TripleStore>()

    fun getGraphNames(): List<String> {
        val res = mutableListOf<String>()
        for (t in stores.keys)
            if (t != defaultGraphName)
                res.add(t)
        return res
    }

    constructor() {
        if (stores[defaultGraphName] == null) {
            stores[defaultGraphName] = TripleStore(defaultGraphName)
        }
    }

    fun createGraph(name: String): TripleStore {
        val tmp = stores[name]
        if (tmp != null)
            throw Exception("PersistentStore.createGraph :: graph[$name] already exist")
        val tmp2 = TripleStore(name)
        stores[name] = tmp2
        return tmp2
    }

    fun dropGraph(name: String) {
        require(name != defaultGraphName)
        if (stores[name] == null)
            throw Exception("PersistentStore.dropGraph :: graph[$name] did not exist")
        stores.remove(name)
    }

    fun dropGraphAll() {
        stores.clear()
        createGraph(defaultGraphName)
    }

    fun clearGraph(name: String) {
        getNamedGraph(name).truncate()
    }

    fun clearGraphAll() {
        for ((k, v) in stores) {
            v.truncate()
        }
    }

    fun getNamedGraph(name: String, create: Boolean = false): TripleStore {
        val tmp = stores[name]
        if (tmp != null || !create)
            return tmp!!
        return createGraph(name)
    }

    fun getDefaultGraph(): TripleStore {
        return getNamedGraph(defaultGraphName)
    }

    fun commit(transactionID: Long) {
        for ((k, v) in stores) {
            v.commit2(transactionID)
        }
    }
}

