package lupos.s15tripleStoreDistributed

import lupos.s00misc.*
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.*
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s14endpoint.Endpoint
import lupos.s14endpoint.EndpointImpl


val uuid = ThreadSafeUuid()

typealias TripleStoreIteratorGlobal = TripleStoreIteratorLocalFilter

class DistributedGraph(val name: String) {
    override val operatorID = EOperatorID.DistributedGraphID
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

    fun addData(transactionID: Long, t: List<String?>) {
        EIndexPattern.values().forEach {
            val node = calculateNodeForDataFull(t[0]!!, t[1]!!, t[2]!!, it)
            Endpoint.process_local_triple_add(name, transactionID, t[0]!!, t[1]!!, t[2]!!, it)
        }
    }

    fun addDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        require(t[0].second && t[1].second && t[2].second)
        EIndexPattern.values().forEach {
            val node = calculateNodeForDataFull(t[0].first, t[1].first, t[2].first, it)
            Endpoint.process_local_triple_add(name, transactionID, t[0].first, t[1].first, t[2].first, it)
        }
    }

    fun deleteData(transactionID: Long, t: List<String?>) {
        val l = mutableListOf<Pair<String, Boolean>>()
        if (t[0] != null)
            l.add(Pair(t[0]!!, true))
        else
            l.add(Pair("s", false))
        if (t[1] != null)
            l.add(Pair(t[1]!!, true))
        else
            l.add(Pair("p", false))
        if (t[2] != null)
            l.add(Pair(t[2]!!, true))
        else
            l.add(Pair("o", false))
        EIndexPattern.values().forEach {
            for (node in calculateNodeForDataMaybe(l[0].first, l[1].first, l[2].first, l[0].second, l[1].second, l[2].second, it)) {
                Endpoint.process_local_triple_delete(name, transactionID, l[0].first, l[1].first, l[2].first, l[0].second, l[1].second, l[2].second, it)
            }
        }
    }

    fun deleteDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) {
        EIndexPattern.values().forEach {
            for (node in calculateNodeForDataMaybe(t[0].first, t[1].first, t[2].first, t[0].second, t[1].second, t[2].second, it)) {
                Endpoint.process_local_triple_delete(name, transactionID, t[0].first, t[1].first, t[2].first, t[0].second, t[1].second, t[2].second, it)
            }
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
                val s = rs.getValue(v[ks])
                val p = rs.getValue(v[kp])
                val o = rs.getValue(v[ko])
                addData(transactionID, listOf(s, p, o))
            }
        }
    }

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, index: EIndexPattern): POPTripleStoreIteratorBase {
        return DistributedTripleStore.localStore.getNamedGraph(name).getIterator(transactionID, ResultSet(dictionary), index)
    }

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, index: EIndexPattern): POPTripleStoreIteratorBase {
        val res = DistributedTripleStore.localStore.getNamedGraph(name).getIterator(transactionID, ResultSet(dictionary), s, p, o, index)
        return res
    }

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, index: EIndexPattern): POPTripleStoreIteratorBase {
        val res = DistributedTripleStore.localStore.getNamedGraph(name).getIterator(transactionID, ResultSet(dictionary), s, p, o, sv, pv, ov, index)
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
