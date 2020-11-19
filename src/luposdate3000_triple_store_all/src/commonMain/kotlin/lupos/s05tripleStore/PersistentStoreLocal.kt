package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s01io.BufferManager
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s04logicalOperators.IQuery
import lupos.s15tripleStoreDistributed.IPersistentStoreLocal

class PersistentStoreLocal : IPersistentStoreLocal {
    @JvmField
    val stores: MutableMap<String, TripleStoreLocal> = mutableMapOf()

    init {
 stores[PersistentStoreLocalExt.defaultGraphName] = TripleStoreLocal(PersistentStoreLocalExt.defaultGraphName)
    }
fun reloadPartitioningScheme(){
stores.clear()
        stores[PersistentStoreLocalExt.defaultGraphName] = TripleStoreLocal(PersistentStoreLocalExt.defaultGraphName)
}
    override fun getGraphNames(includeDefault: Boolean): List<String> {
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

    override /*suspend*/ fun dropGraph(query: IQuery, name: String) {
        SanityCheck.check { name != PersistentStoreLocalExt.defaultGraphName }
        val store = stores[name] ?: throw GraphNameNotExistsDuringDeleteException(name)
        store.clear()
        stores.remove(name)
    }

    override /*suspend*/ fun clearGraph(query: IQuery, name: String) {
        getNamedGraph(query, name).clear()
    }

    override /*suspend*/ fun getNamedGraph(query: IQuery, name: String, create: Boolean): TripleStoreLocal {
        val tmp = stores[name]
        if (tmp != null) {
            return tmp
        } else if (!create) {
            throw GraphNameNotFoundException(name)
        }
        return createGraph(query, name)
    }

    override /*suspend*/ fun getDefaultGraph(query: IQuery): TripleStoreLocal {
        return getNamedGraph(query, PersistentStoreLocalExt.defaultGraphName, true)
    }

    override /*suspend*/ fun commit(query: IQuery) {
        stores.values.forEach { v ->
            v.commit(query)
        }
    }

    override /*suspend*/ fun safeToFolder() {
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

    override /*suspend*/ fun loadFromFolder() {
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
