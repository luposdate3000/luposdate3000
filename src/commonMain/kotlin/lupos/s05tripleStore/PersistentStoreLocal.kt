package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.BufferManager
import lupos.s00misc.Coverage
import lupos.s00misc.File
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.index_IDTriple.NodeManager

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
        var store = stores[name]
        if (store == null) {
            throw Exception("PersistentStore.dropGraph :: graph[$name] did not exist")
        }
        store.clear()
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

    fun safeToFolder() {
        stores.values.forEach { v ->
            v.flush()
        }
        var i = 0
        stores[defaultGraphName]!!.safeToFolder(BufferManager.bufferPrefix + "store/" + i)
        i++
        File(BufferManager.bufferPrefix + "store/stores.txt").printWriter { out ->
            stores.forEach { name, store ->
                if (name != "") {
                    out.println(name)
                    store.safeToFolder(BufferManager.bufferPrefix + "store/" + i)
                    i++
                }
            }
        }
        nodeGlobalDictionary.safeToFolder()
        NodeManager.safeToFolder()
        BufferManager.safeToFolder()
    }

    fun loadFromFolder() {
        BufferManager.loadFromFolder()
        NodeManager.loadFromFolder()
        nodeGlobalDictionary.loadFromFolder()
        var i = 0
        val store = TripleStoreLocal(defaultGraphName)
        store.loadFromFolder(BufferManager.bufferPrefix + "store/" + i)
        stores[defaultGraphName] = store
        i++
        File(BufferManager.bufferPrefix + "store/stores.txt").forEachLine { name ->
            if (name != "") {
                val store = TripleStoreLocal(name)
                store.loadFromFolder(BufferManager.bufferPrefix + "store/" + i)
                stores[name] = store
                i++
            }
        }
    }
}
