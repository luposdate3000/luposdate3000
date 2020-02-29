apackage lupos.s15tripleStoreDistributed

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s05tripleStore.TripleStoreIteratorLocalFilter
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.EndpointImpl


val uuid = ThreadSafeUuid()

typealias TripleStoreIteratorGlobal = TripleStoreIteratorLocalFilter

class DistributedGraph(val name: String) {
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
        return EndpointImpl.fullname
    }

    fun calculateNodeForDataMaybe(s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern): Set<String> {
        return setOf(EndpointImpl.fullname)
    }

    fun addData(transactionID: Long, t: List<AOPConstant>) {
        EIndexPattern.values().forEach {
            Endpoint.process_local_triple_add(EndpointImpl.fullname, transactionID, t[0]!!, t[1]!!, t[2]!!, it)
        }
    }

    fun deleteData(transactionID: Long, t: List<AOPConstant>) {
        EIndexPattern.values().forEach {
            Endpoint.process_local_triple_delete(EndpointImpl.fullname, transactionID, t[0], t[1], t[2], it)
        }
    }

    fun deleteDataVar(transactionID: Long, t: List<AOPBase>) {
        EIndexPattern.values().forEach {
            Endpoint.process_local_triple_delete(EndpointImpl.fullname, transactionID, t[0], t[1], t[2], it)
        }
    }

    fun addData(transactionID: Long, iterator: OPBase) {
        val rs = iterator.resultSet
        val ks = rs.createVariable("s")
        val kp = rs.createVariable("p")
        val ko = rs.createVariable("o")
        iterator.evaluate()
        CoroutinesHelper.runBlock {
            for (v in iterator.channel) {
                val s = AOPVariable.calculate(rs.getValue(v[ks]))
                val p = AOPVariable.calculate(rs.getValue(v[kp]))
                val o = AOPVariable.calculate(rs.getValue(v[ko]))
                addData(transactionID, listOf(s, p, o))
            }
        }
    }

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, index: EIndexPattern): POPTripleStoreIteratorBase {
        return DistributedTripleStore.localStore.getNamedGraph(name).getIterator(transactionID, ResultSet(dictionary), index)
    }

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: AOPBase, p: AOPBase, o: AOPBase, index: EIndexPattern): POPTripleStoreIteratorBase {
        val res = DistributedTripleStore.localStore.getNamedGraph(name).getIterator(transactionID, ResultSet(dictionary), s, p, o, index)
        return res
    }

}

object DistributedTripleStore {
    val localStore = PersistentStoreLocal()
    fun nextTransactionID(): Long {
        return localStore.nextTransactionID()
    }

    fun getGraphNames(includeDefault: Boolean = false): List<String> {
        return localStore.getGraphNames(includeDefault)
    }

    fun createGraph(name: String): DistributedGraph {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.createGraph $name 1" })
        Endpoint.process_local_graph_operation(name, EGraphOperationType.CREATE)
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.createGraph $name 2" })
        return DistributedGraph(name)
    }

    fun dropGraph(name: String) {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.dropGraph $name 1" })
        Endpoint.process_local_graph_operation(name, EGraphOperationType.DROP)
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.dropGraph $name 2" })
    }

    fun clearGraph(name: String) {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.clearGraph $name 1" })
        Endpoint.process_local_graph_operation(name, EGraphOperationType.CLEAR)
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.clearGraph $name 2" })
    }

    fun getNamedGraph(name: String, create: Boolean = false): DistributedGraph {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.getNamedGraph $name 1" })
        if (create && !(localStore.getGraphNames(true).contains(name)))
            createGraph(name)
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.getNamedGraph $name 2" })
        return DistributedGraph(name)
    }

    fun getDefaultGraph(): DistributedGraph {
        return DistributedGraph(localStore.defaultGraphName)
    }

    fun commit(transactionID: Long) {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.commit 1" })
        Endpoint.process_local_commit(transactionID)
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.commit 2" })
    }
}
