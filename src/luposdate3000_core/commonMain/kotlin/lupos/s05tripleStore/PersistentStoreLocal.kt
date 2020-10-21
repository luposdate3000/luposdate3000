package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.File
import lupos.s00misc.GraphNameAlreadyExistsDuringCreateException
import lupos.s00misc.GraphNameNotExistsDuringDeleteException
import lupos.s00misc.GraphNameNotFoundException
import lupos.s00misc.SanityCheck
import lupos.s01io.BufferManager
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.IQuery
import lupos.s15tripleStoreDistributed.IPersistentStoreLocal

class PersistentStoreLocal :IPersistentStoreLocal{
    @JvmField
    val stores = mutableMapOf<String, TripleStoreLocal>()

    constructor() {
        stores[PersistentStoreLocalExt.defaultGraphName] = TripleStoreLocal(PersistentStoreLocalExt.defaultGraphName)
    }

    override fun getGraphNames(includeDefault: Boolean ): List<String> {
        val res = mutableListOf<String>()
        stores.keys.forEach { t ->
            if (t != PersistentStoreLocalExt.defaultGraphName || includeDefault) {
                res.add(t)
            }
        }
        return res
    }

    override fun createGraph(query: IQuery, name: String): TripleStoreLocal {
        val tmp = stores[name]
        if (tmp != null) {
            throw GraphNameAlreadyExistsDuringCreateException(name)
        }
        val tmp2 = TripleStoreLocal(name)
        stores[name] = tmp2
        return tmp2
    }

    suspend override fun dropGraph(query: IQuery, name: String) {
        SanityCheck.check({ name != PersistentStoreLocalExt.defaultGraphName })
        var store = stores[name]
        if (store == null) {
            throw GraphNameNotExistsDuringDeleteException(name)
        }
        store.clear()
        stores.remove(name)
    }

    suspend override fun clearGraph(query: IQuery, name: String) {
        getNamedGraph(query, name).clear()
    }

    suspend override fun getNamedGraph(query: IQuery, name: String, create: Boolean ): TripleStoreLocal {
        val tmp = stores[name]
        if (tmp != null) {
            return tmp
        } else if (!create) {
            throw GraphNameNotFoundException(name)
        }
        return createGraph(query, name)
    }

    suspend override fun getDefaultGraph(query: IQuery): TripleStoreLocal {
        return getNamedGraph(query, PersistentStoreLocalExt.defaultGraphName, true)
    }

    suspend override fun commit(query: IQuery) {
        stores.values.forEach { v ->
            v.commit(query)
        }
    }

    suspend override fun safeToFolder() {
        stores.values.forEach { v ->
            v.flush()
        }
        var i = 0
        stores[PersistentStoreLocalExt.defaultGraphName]!!.safeToFolder(BufferManager.bufferPrefix + "store/" + i)
        i++
        File(BufferManager.bufferPrefix + "store/stores.txt").printWriterSuspended { out ->
            for ((name, store) in stores) {
                if (name != "") {
                    out.println(name)
                    store.safeToFolder(BufferManager.bufferPrefix + "store/" + i)
                    i++
                }
            }
        }
        nodeGlobalDictionary.safeToFolder()
        BufferManager.safeToFolder()
    }

    suspend override fun loadFromFolder() {
        BufferManager.loadFromFolder()
        nodeGlobalDictionary.loadFromFolder()
        var i = 0
        val store = TripleStoreLocal(PersistentStoreLocalExt.defaultGraphName)
        store.loadFromFolder(BufferManager.bufferPrefix + "store/" + i)
        stores[PersistentStoreLocalExt.defaultGraphName] = store
        i++
        File(BufferManager.bufferPrefix + "store/stores.txt").forEachLineSuspended { name ->
            if (name != "") {
                val store2 = TripleStoreLocal(name)
                store2.loadFromFolder(BufferManager.bufferPrefix + "store/" + i)
                stores[name] = store2
                i++
            }
        }
    }
}
