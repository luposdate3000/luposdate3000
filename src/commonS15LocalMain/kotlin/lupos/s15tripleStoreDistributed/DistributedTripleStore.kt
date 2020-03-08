package lupos.s15tripleStoreDistributed

import kotlin.jvm.JvmField
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s05tripleStore.TripleStoreIteratorLocalFilter
import lupos.s14endpoint.*
import lupos.s14endpoint.Endpoint


val uuid = ThreadSafeUuid()

typealias TripleStoreIteratorGlobal = TripleStoreIteratorLocalFilter

class DistributedGraph(val query: Query, @JvmField val name: String) {
    @JvmField
    val K = 8 // defined in project.pdf

    fun myHashCode(s: String, d: Int): Int {
        val c = s.hashCode()
        if (c < 0)
            return (-c) % d
        return c % d
    }

    fun myHashCode(s: Int, p: Int, o: Int, d: Int, idx: EIndexPattern): Int {
        when (idx) {
            EIndexPattern.S -> return myHashCode("" + s, d)
            EIndexPattern.P -> return myHashCode("" + p, d)
            EIndexPattern.O -> return myHashCode("" + o, d)
            EIndexPattern.SP -> return myHashCode("" + s + "-" + p, d)
            EIndexPattern.SO -> return myHashCode("" + s + "-" + o, d)
            EIndexPattern.PO -> return myHashCode("" + p + "-" + o, d)
            EIndexPattern.SPO -> return myHashCode("" + s + "-" + p + "-" + o, d)
        }
    }

    fun calculateNodeForDataFull(s: String, p: String, o: String, idx: EIndexPattern): String {
        return endpointServer!!.fullname
    }

    fun calculateNodeForDataMaybe(s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern): Set<String> {
        return setOf(endpointServer!!.fullname)
    }

    fun addData(t: Array<AOPConstant>) {
        EIndexPattern.values().forEach {
            Endpoint.process_local_triple_add(query, endpointServer!!.fullname, t, it)
        }
    }

    fun deleteData(t: Array<AOPConstant>) {
        EIndexPattern.values().forEach {
            Endpoint.process_local_triple_delete(query, endpointServer!!.fullname, Array<AOPBase>(3) { t[it] }, it)
        }
    }

    fun deleteDataVar(t: Array<AOPBase>) {
        EIndexPattern.values().forEach {
            Endpoint.process_local_triple_delete(query, endpointServer!!.fullname, t, it)
        }
    }

    fun addData(iterator: OPBase) {
        val rs = iterator.resultSet
        val variables = arrayOf(rs.createVariable("s"), rs.createVariable("p"), rs.createVariable("o"))
        val channel = iterator.evaluate()
        CoroutinesHelper.runBlock {
            for (v in channel)
                addData(Array(3) { AOPVariable.calculate(query, rs.getValueString(v,variables[it])) })
        }
    }

    fun getIterator(index: EIndexPattern): POPTripleStoreIteratorBase {
        return DistributedTripleStore.localStore.getNamedGraph(query, name).getIterator(query, ResultSet(query.dictionary), index)
    }

    fun getIterator(params: Array<AOPBase>, index: EIndexPattern): POPTripleStoreIteratorBase {
        val res = DistributedTripleStore.localStore.getNamedGraph(query, name).getIterator(query, ResultSet(query.dictionary), params, index)
        return res
    }

}

object DistributedTripleStore {
    @JvmField
    val localStore = PersistentStoreLocal()

    fun getGraphNames(includeDefault: Boolean = false): List<String> {
        return localStore.getGraphNames(includeDefault)
    }

    fun createGraph(query: Query, name: String): DistributedGraph {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.createGraph $name 1" })
        Endpoint.process_local_graph_operation(query, name, EGraphOperationType.CREATE)
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.createGraph $name 2" })
        return DistributedGraph(query, name)
    }

    fun dropGraph(query: Query, name: String) {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.dropGraph $name 1" })
        Endpoint.process_local_graph_operation(query, name, EGraphOperationType.DROP)
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.dropGraph $name 2" })
    }

    fun clearGraph(query: Query, name: String) {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.clearGraph $name 1" })
        Endpoint.process_local_graph_operation(query, name, EGraphOperationType.CLEAR)
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.clearGraph $name 2" })
    }

    fun getNamedGraph(query: Query, name: String, create: Boolean = false): DistributedGraph {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.getNamedGraph $name 1" })
        if (create && !(localStore.getGraphNames(true).contains(name)))
            createGraph(query, name)
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.getNamedGraph $name 2" })
        return DistributedGraph(query, name)
    }

    fun getDefaultGraph(query: Query): DistributedGraph {
        return DistributedGraph(query, PersistentStoreLocal.defaultGraphName)
    }

    fun commit(query: Query) {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.commit 1" })
        Endpoint.process_local_commit(query)
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.commit 2" })
    }
}
