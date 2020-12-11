package lupos.s05tripleStore
import lupos.s00misc.File
import lupos.s00misc.GraphNameAlreadyExistsDuringCreateException
import lupos.s00misc.GraphNameNotExistsDuringDeleteException
import lupos.s00misc.GraphNameNotFoundException
import lupos.s00misc.SanityCheck
import lupos.s00misc.TripleStoreLocal
import lupos.s01io.BufferManager
import lupos.s04logicalOperators.IQuery
import lupos.s15tripleStoreDistributed.IPersistentStoreLocal
import kotlin.jvm.JvmField
class PersistentStoreLocal : IPersistentStoreLocal {
    @JvmField
    val bufferManager = BufferManager.getBuffermanager("stores")
    @JvmField
    val stores: MutableMap<String, TripleStoreLocal> = mutableMapOf()
    fun storesAdd(name: String) {
        var pageid2 = -1
        bufferManager.createPage { p, pageid3 ->
            pageid2 = pageid3
        }
        bufferManager.releasePage(pageid2)
        stores[name] = TripleStoreLocal(name, pageid2, false)
        storesChanged()
    }
    fun storesRemove(name: String) {
        stores.remove(name)
        storesChanged()
    }
    fun storesRemoveAll() {
        stores.clear()
        storesChanged()
    }
    fun storesChanged() {
        if (!BufferManager.isInMemoryOnly) {
            File(BufferManager.bufferPrefix + "/PersistentStoreLocal.cnf").printWriter { out ->
                for ((k, v) in stores) {
                    out.println("$k;${v.store_root_page_id}")
                }
            }
        }
    }
    init {
        if (BufferManager.initializedFromDisk) {
            File(BufferManager.bufferPrefix + "/PersistentStoreLocal.cnf").forEachLine { line ->
                val arr = line.split(";")
                stores[arr[0]] = TripleStoreLocal(arr[0], arr[1].toInt(), true)
            }
        } else {
            storesAdd(PersistentStoreLocalExt.defaultGraphName)
        }
    }
    fun reloadPartitioningScheme() {
        storesRemoveAll()
        storesAdd(PersistentStoreLocalExt.defaultGraphName)
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
        storesAdd(name)
        return stores[name]!!
    }
    override /*suspend*/ fun dropGraph(query: IQuery, name: String) {
        SanityCheck.check { name != PersistentStoreLocalExt.defaultGraphName }
        val store = stores[name] ?: throw GraphNameNotExistsDuringDeleteException(name)
        store.clear()
        storesRemove(name)
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
}
