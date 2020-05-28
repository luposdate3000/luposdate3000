package lupos.s05tripleStore
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
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
Coverage.funStart(6676)
        val res = mutableListOf<String>()
Coverage.statementStart(6677)
        stores.keys.forEach { t ->
Coverage.statementStart(6678)
            if (t != defaultGraphName || includeDefault) {
Coverage.ifStart(6679)
                res.add(t)
Coverage.statementStart(6680)
            }
Coverage.statementStart(6681)
        }
Coverage.statementStart(6682)
        return res
    }
    fun createGraph(query: Query, name: String): TripleStoreLocal {
Coverage.funStart(6683)
        val tmp = stores[name]
Coverage.statementStart(6684)
        if (tmp != null) {
Coverage.ifStart(6685)
            throw Exception("PersistentStore.createGraph :: graph[$name] already exist")
        }
Coverage.statementStart(6686)
        val tmp2 = TripleStoreLocal(name)
Coverage.statementStart(6687)
        stores[name] = tmp2
Coverage.statementStart(6688)
        return tmp2
    }
    fun dropGraph(query: Query, name: String) {
Coverage.funStart(6689)
        SanityCheck.checkNEQ({ name }, { defaultGraphName })
Coverage.statementStart(6690)
        if (stores[name] == null) {
Coverage.ifStart(6691)
            throw Exception("PersistentStore.dropGraph :: graph[$name] did not exist")
        }
Coverage.statementStart(6692)
        stores.remove(name)
Coverage.statementStart(6693)
    }
    fun clearGraph(query: Query, name: String) {
Coverage.funStart(6694)
        getNamedGraph(query, name).clear()
Coverage.statementStart(6695)
    }
    fun getNamedGraph(query: Query, name: String, create: Boolean = false): TripleStoreLocal {
Coverage.funStart(6696)
        val tmp = stores[name]
Coverage.statementStart(6697)
        if (tmp != null || !create) {
Coverage.ifStart(6698)
            return tmp!!
        }
Coverage.statementStart(6699)
        return createGraph(query, name)
    }
    fun getDefaultGraph(query: Query): TripleStoreLocal {
Coverage.funStart(6700)
        return getNamedGraph(query, defaultGraphName, true)
    }
    fun commit(query: Query) {
Coverage.funStart(6701)
        stores.values.forEach { v ->
Coverage.statementStart(6702)
            v.commit(query)
Coverage.statementStart(6703)
        }
Coverage.statementStart(6704)
    }
}
