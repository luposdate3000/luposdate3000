package lupos.s15tripleStoreDistributed

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.ResultSetIterator
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.*
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s05tripleStore.TripleStoreLocal
import lupos.s12p2p.*
import lupos.s14endpoint.*


val uuid = ThreadSafeUuid()

class DistributedGraph(val name: String) {
    val K = 8 // defined in project.pdf

    inline fun myHashCode(s: String, d: Int): Int {
        val c = s.hashCode()
        if (c < 0)
            return (-c) % d
        return c % d
    }

    inline fun myHashCode(s: Int, p: Int, o: Int, d: Int, idx: EIndexPattern): Int {
        when (idx) {
            EIndexPattern.SPO -> return myHashCode("" + s + "-" + p + "-" + o, d)
            EIndexPattern.SOP -> return myHashCode("" + s + "-" + o + "-" + p, d)
            EIndexPattern.PSO -> return myHashCode("" + p + "-" + s + "-" + o, d)
            EIndexPattern.POS -> return myHashCode("" + p + "-" + o + "-" + s, d)
            EIndexPattern.OPS -> return myHashCode("" + o + "-" + p + "-" + s, d)
            EIndexPattern.OSP -> return myHashCode("" + o + "-" + s + "-" + p, d)
        }
    }

    inline fun calculateNodeForDataFull(s: String, p: String, o: String, idx: EIndexPattern): String {
        return EndpointImpl.fullname
    }

    inline fun calculateNodeForDataMaybe(s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern): Set<String> {
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

    fun addData(transactionID: Long, iterator: ResultSetIterator) {
        val rs = iterator.getResultSet()
        val ks = rs.createVariable("s")
        val kp = rs.createVariable("p")
        val ko = rs.createVariable("o")
        while (iterator.hasNext()) {
            val v = iterator.next()
            val s = rs.getValue(v[ks])
            val p = rs.getValue(v[kp])
            val o = rs.getValue(v[ko])
            addData(transactionID, listOf(s, p, o))
        }
    }

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, index: EIndexPattern): POPTripleStoreIteratorBase {
        return DistributedTripleStore.localStore.getNamedGraph(name).getIterator(transactionID, dictionary, index)
    }

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, index: EIndexPattern): POPTripleStoreIteratorBase {
        val res = DistributedTripleStore.localStore.getNamedGraph(name).getIterator(transactionID, dictionary, s, p, o, index)
        return res
    }

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, index: EIndexPattern): POPTripleStoreIteratorBase {
        val res = DistributedTripleStore.localStore.getNamedGraph(name).getIterator(transactionID, dictionary, s, p, o, sv, pv, ov, index)
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
