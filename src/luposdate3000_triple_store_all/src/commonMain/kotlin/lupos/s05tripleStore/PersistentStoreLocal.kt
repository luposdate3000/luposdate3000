package lupos.s05tripleStore
import lupos.s00misc.File
import lupos.s00misc.GraphNameAlreadyExistsDuringCreateException
import lupos.s00misc.GraphNameNotExistsDuringDeleteException
import lupos.s00misc.GraphNameNotFoundException
import lupos.s00misc.SanityCheck
import lupos.s00misc.TripleStoreLocal
import lupos.s01io.BufferManager
import lupos.s01io.BufferManagerExt
import lupos.s04logicalOperators.IQuery
import lupos.s15tripleStoreDistributed.IPersistentStoreLocal
import kotlin.jvm.JvmField
public class PersistentStoreLocal : IPersistentStoreLocal {
    @JvmField
    public val bufferManager: BufferManager = BufferManagerExt.getBuffermanager("stores")
    @JvmField
    public val stores: MutableMap<String, TripleStoreLocal> = mutableMapOf()
    private fun storesAdd(name: String) {
        var pageid2 = -1
        bufferManager.createPage { p, pageid3 ->
            pageid2 = pageid3
        }
        bufferManager.releasePage(pageid2)
        stores[name] = TripleStoreLocal(name, pageid2, false)
        storesChanged()
    }
    private fun storesRemove(name: String) {
        stores[name]!!.dropStore()
        stores.remove(name)
        storesChanged()
    }
    private fun storesRemoveAll() {
        for ((k, v) in stores) {
            v.dropStore()
        }
        stores.clear()
        storesChanged()
    }
    private fun storesChanged() {
        if (!BufferManagerExt.isInMemoryOnly) {
            File(BufferManagerExt.bufferPrefix + "PersistentStoreLocal.cnf").printWriter { out ->
                for ((k, v) in stores) {
                    out.println("$k;${v.store_root_page_id}")
                }
            }
        }
    }
    init {
        if (BufferManagerExt.initializedFromDisk) {
            File(BufferManagerExt.bufferPrefix + "PersistentStoreLocal.cnf").forEachLine { line ->
                val arr = line.split(";")
                if (arr.size == 2) {
                    stores[arr[0]] = TripleStoreLocal(arr[0], arr[1].toInt(), true)
                }
            }
        } else {
            storesAdd(PersistentStoreLocalExt.defaultGraphName)
        }
    }
    public fun reloadPartitioningScheme() {
        storesRemoveAll()
        storesAdd(PersistentStoreLocalExt.defaultGraphName)
    }
    public override fun getGraphNames(): List<String> {
        return getGraphNames(false)
    }
    public override fun getGraphNames(includeDefault: Boolean): List<String> {
        val res = mutableListOf<String>()
        stores.keys.forEach { t ->
            if (t != PersistentStoreLocalExt.defaultGraphName || includeDefault) {
                res.add(t)
            }
        }
        return res
    }
    public override fun createGraph(query: IQuery, name: String): TripleStoreLocal {
        val tmp = stores[name]
        if (tmp != null) {
            throw GraphNameAlreadyExistsDuringCreateException(name)
        }
        storesAdd(name)
        return stores[name]!!
    }
    public override fun getEnabledPartitions(name: String): Array<EnabledPartitionContainer> {
        return stores[name]!!.enabledPartitions
    }
    public override /*suspend*/ fun dropGraph(query: IQuery, name: String) {
        SanityCheck.check { name != PersistentStoreLocalExt.defaultGraphName }
        val store = stores[name] ?: throw GraphNameNotExistsDuringDeleteException(name)
        store.clear()
        storesRemove(name)
    }
    public override /*suspend*/ fun clearGraph(query: IQuery, name: String) {
        getNamedGraph(query, name).clear()
    }
    public override /*suspend*/ fun getNamedGraph(query: IQuery, name: String): TripleStoreLocal {
        return getNamedGraph(query, name, false)
    }
    public override /*suspend*/ fun getNamedGraph(query: IQuery, name: String, create: Boolean): TripleStoreLocal {
        val tmp = stores[name]
        if (tmp != null) {
            return tmp
        } else if (!create) {
            throw GraphNameNotFoundException(name)
        }
        return createGraph(query, name)
    }
    public override /*suspend*/ fun getDefaultGraph(query: IQuery): TripleStoreLocal {
        return getNamedGraph(query, PersistentStoreLocalExt.defaultGraphName, true)
    }
    public override /*suspend*/ fun commit(query: IQuery) {
        stores.values.forEach { v ->
            v.commit(query)
        }
    }
}
