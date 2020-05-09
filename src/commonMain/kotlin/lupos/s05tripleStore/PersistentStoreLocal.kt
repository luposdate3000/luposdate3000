package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.Query

class PersistentStoreLocal {
    @JvmField
    val stores = mutableMapOf<String, TripleStoreLocal>()

    constructor() {
        stores[defaultGraphName] = TripleStoreLocal(defaultGraphName)
    }

    companion object {
        val defaultGraphName = ""
    }

    fun getGraphNames(includeDefault: Boolean = false): List<String> {
        val res = mutableListOf<String>()
        stores.keys.forEach { t ->
            if (t != defaultGraphName || includeDefault) {
                res.add(t)
            }
        }
        return res
    }

    fun createGraph(query: Query, name: String): TripleStoreLocal {
        val tmp = stores[name]
        if (tmp != null) {
            throw Exception("PersistentStore.createGraph :: graph[$name] already exist")
        }
        val tmp2 = TripleStoreLocal(name)
        stores[name] = tmp2
        return tmp2
    }

    fun dropGraph(query: Query, name: String) {
        SanityCheck.checkNEQ({ name }, { defaultGraphName })
        if (stores[name] == null) {
            throw Exception("PersistentStore.dropGraph :: graph[$name] did not exist")
        }
        stores.remove(name)
    }

    fun clearGraph(query: Query, name: String) {
        getNamedGraph(query, name).clear()
    }

    fun getNamedGraph(query: Query, name: String, create: Boolean = false): TripleStoreLocal {
        val tmp = stores[name]
        if (tmp != null || !create) {
            return tmp!!
        }
        return createGraph(query, name)
    }

    fun getDefaultGraph(query: Query): TripleStoreLocal {
        return getNamedGraph(query, defaultGraphName, true)
    }

    fun commit(query: Query) {
        stores.values.forEach { v ->
            v.commit(query)
        }
    }
}
