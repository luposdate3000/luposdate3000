package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase


abstract class POPTripleStoreIteratorBase(query: Query,
                                          operatorID: EOperatorID,
                                          classname: String,
                                          resultSet: ResultSet,
                                          children: Array<OPBase>) : POPBase(query, operatorID, classname, resultSet, children) {
    @JvmField
    var sparam: AOPBase = AOPVariable(query, "#s$uuid")
    @JvmField
    var pparam: AOPBase = AOPVariable(query, "#p$uuid")
    @JvmField
    var oparam: AOPBase = AOPVariable(query, "#o$uuid")

    abstract fun getGraphName(): String

    override fun equals(other: Any?): Boolean {
        if (other !is POPTripleStoreIteratorBase)
            return false
        if (getGraphName() != other.getGraphName())
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}

class PersistentStoreLocal {
    @JvmField
    val stores = ThreadSafeMutableMap<String, TripleStoreLocal>()

    constructor() {
        stores[defaultGraphName] = TripleStoreLocal(defaultGraphName)
    }

    companion object {
        val defaultGraphName = ""
        private val global_transactionID = ThreadSafeUuid()

    }

    fun nextTransactionID(): Long {
        return global_transactionID.next()
    }

    fun getGraphNames(includeDefault: Boolean = false): List<String> {
        val res = mutableListOf<String>()
        stores.forEachKey { t ->
            if (t != defaultGraphName || includeDefault)
                res.add(t)
        }
        return res
    }

    fun createGraph(query: Query, name: String): TripleStoreLocal {
        val tmp = stores[name]
        if (tmp != null)
            throw Exception("PersistentStore.createGraph :: graph[$name] already exist")
        val tmp2 = TripleStoreLocal(name)
        stores[name] = tmp2
        return tmp2
    }

    fun dropGraph(query: Query, name: String) {
        require(name != defaultGraphName)
        if (stores[name] == null)
            throw Exception("PersistentStore.dropGraph :: graph[$name] did not exist")
        stores.remove(name)
    }

    fun clearGraph(query: Query, name: String) {
        getNamedGraph(query, name).clear()
    }

    fun getNamedGraph(query: Query, name: String, create: Boolean = false): TripleStoreLocal {
        val tmp = stores[name]
        if (tmp != null || !create)
            return tmp!!
        return createGraph(query, name)
    }

    fun getDefaultGraph(query: Query): TripleStoreLocal {
        return getNamedGraph(query, defaultGraphName, true)
    }

    fun commit(query: Query) {
        stores.forEachValue { v ->
            v.commit2(query)
        }
    }
}

