package lupos.s15tripleStoreDistributed

import lupos.s00misc.*
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s12p2p.P2P


val uuid = ThreadSafeUuid()

class TripleStoreIteratorGlobal : POPTripleStoreIteratorBase {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    override val resultSet: ResultSet
    val nodeNameIterator: Iterator<String>
    var remoteIterator: Iterator<ResultRow>? = null
    val transactionID: Long
    val graphNameL: String
    val idx: EIndexPattern

    val sFilter: String?
    val pFilter: String?
    val oFilter: String?

    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String) : this(transactionID, dictionary, graphName, EIndexPattern.SPO)
    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String, index: EIndexPattern) : this(transactionID, dictionary, graphName, null, null, null, false, false, false, index)
    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String, s: String?, p: String?, o: String?, sv: Boolean, pv: Boolean, ov: Boolean, index: EIndexPattern) {
        idx = index
        graphNameL = graphName
        this.dictionary = dictionary
        this.transactionID = transactionID
        resultSet = ResultSet(dictionary)
        nodeNameIterator = P2P.getKnownClientsCopy().iterator()
        if (sv)
            sFilter = s
        else {
            sFilter = null
            if (s != null)
                setMNameS(s)
        }
        if (pv)
            pFilter = p
        else {
            pFilter = null
            if (p != null)
                setMNameP(p)
        }
        if (ov)
            oFilter = o
        else {
            oFilter = null
            if (o != null)
                setMNameO(o)
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("TripleStoreIteratorGlobalFilter").addAttribute("uuid", "" + uuid).addAttribute("name", getGraphName())
        if (sFilter == null)
            res.addAttribute("nameS", nameS)
        else
            res.addAttribute("filterS", sFilter)
        if (pFilter == null)
            res.addAttribute("nameP", nameP)
        else
            res.addAttribute("filterP", pFilter)
        if (oFilter == null)
            res.addAttribute("nameO", nameO)
        else
            res.addAttribute("filterO", oFilter)
        return res
    }

    override fun getGraphName(): String = Trace.trace({ "TripleStoreIteratorGlobal.getGraphName" }, {
        return graphNameL
    })

override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        if (sFilter == null)
            tmp += nameS
        if (pFilter == null)
            tmp += nameP
        if (oFilter == null)
            tmp += nameO
        return tmp.distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun evaluate() = Trace.trace<Unit>({ "TripleStoreIteratorGlobal.evaluate" }, {
        CoroutinesHelper.run {
            try {
                for (nodeName in nodeNameIterator) {
                    val s = if (sFilter == null)
                        nameS
                    else
                        sFilter
                    val p = if (pFilter == null)
                        nameP
                    else
                        pFilter
                    val o = if (oFilter == null)
                        nameO
                    else
                        oFilter
                    val sv = sFilter != null
                    val pv = pFilter != null
                    val ov = oFilter != null
                    var remoteNode: OPBase? = null
                    try {
                        remoteNode = P2P.execTripleGet(nodeName, graphNameL, resultSet, transactionID, s, p, o, sv, pv, ov, idx)
                        remoteNode.evaluate()
                        for (c in remoteNode.channel)
                            channel.send(resultFlowProduce({ this@TripleStoreIteratorGlobal }, { c }))
                    } catch (e: Throwable) {
                        remoteNode?.channel?.close(e)
                        channel.close(e)
                    }
                }
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
    })

    override fun setMNameS(n: String) {
        nameS = n
    }

    override fun setMNameP(n: String) {
        nameP = n
    }

    override fun setMNameO(n: String) {
        nameO = n
    }


}

class DistributedGraph(val name: String) {
    val K = 8 // defined in project.pdf

    fun myHashCode(s: String, d: Int): Int {
        val c = s.hashCode()
        if (c < 0)
            return (-c) % d
        return c % d
    }

    fun myHashCode(s: Int, p: Int, o: Int, d: Int, idx: EIndexPattern): Int = Trace.trace({ "DistributedGraph.myHashCode" }, {
        when (idx) {
            EIndexPattern.S -> return myHashCode("" + s, d)
            EIndexPattern.P -> return myHashCode("" + p, d)
            EIndexPattern.O -> return myHashCode("" + o, d)
            EIndexPattern.SP -> return myHashCode("" + s + "-" + p, d)
            EIndexPattern.SO -> return myHashCode("" + s + "-" + o, d)
            EIndexPattern.PO -> return myHashCode("" + p + "-" + o, d)
            EIndexPattern.SPO -> return myHashCode("" + s + "-" + p + "-" + o, d)
        }
    })

    fun calculateNodeForDataFull(s: String, p: String, o: String, idx: EIndexPattern): String = Trace.trace({ "DistributedGraph.calculateNodeForDataFull" }, {
        val sh = +myHashCode(s, K)
        val ph = +myHashCode(p, K)
        val oh = +myHashCode(o, K)
        return P2P.getKnownClientsCopy()[myHashCode(sh, ph, oh, P2P.knownClients.size, idx)]
    })

    fun calculateNodeForDataMaybe(s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, idx: EIndexPattern): Set<String> = Trace.trace({ "DistributedGraph.calculateNodeForDataMaybe" }, {
        val res = mutableSetOf<String>()
        val sr = if (sv) {
            val h = myHashCode(s, K)
            IntRange(h, h)
        } else
            IntRange(0, K)
        val pr = if (pv) {
            val h = myHashCode(p, K)
            IntRange(h, h)
        } else
            IntRange(0, K)
        val or = if (ov) {
            val h = myHashCode(o, K)
            IntRange(h, h)
        } else
            IntRange(0, K)
        for (si in sr) {
            for (pi in pr) {
                for (oi in or) {
                    res.add(P2P.getKnownClientsCopy()[myHashCode(si, pi, oi, P2P.knownClients.size, idx)])
                }
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "maybe :: " + res })
        return res
    })

    fun addData(transactionID: Long, t: List<String?>) = Trace.trace({ "DistributedGraph.addData a" }, {
        EIndexPattern.values().forEach {
            val node = calculateNodeForDataFull(t[0]!!, t[1]!!, t[2]!!, it)
            P2P.execTripleAdd(node, name, transactionID, t[0]!!, t[1]!!, t[2]!!, it)
        }
    })

    fun addDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) = Trace.trace({ "DistributedGraph.addDataVar" }, {
        require(t[0].second && t[1].second && t[2].second)
        EIndexPattern.values().forEach {
            val node = calculateNodeForDataFull(t[0].first, t[1].first, t[2].first, it)
            P2P.execTripleAdd(node, name, transactionID, t[0].first, t[1].first, t[2].first, it)
        }
    })

    fun deleteData(transactionID: Long, t: List<String?>) = Trace.trace({ "DistributedGraph.deleteData" }, {
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
                P2P.execTripleDelete(node, name, transactionID, l, it)
            }
        }
    })

    fun deleteDataVar(transactionID: Long, t: List<Pair<String, Boolean>>) = Trace.trace({ "DistributedGraph.deleteDataVar" }, {
        EIndexPattern.values().forEach {
            for (node in calculateNodeForDataMaybe(t[0].first, t[1].first, t[2].first, t[0].second, t[1].second, t[2].second, it))
                P2P.execTripleDelete(node, name, transactionID, listOf(t[0], t[1], t[2]), it)
        }
    })

    suspend fun addData(transactionID: Long, iterator: OPBase) = Trace.trace({ "DistributedGraph.addData b" }, {
        val rs = iterator.resultSet
        val ks = rs.createVariable("s")
        val kp = rs.createVariable("p")
        val ko = rs.createVariable("o")
        println("xx before evaluate")
        iterator.evaluate()
        println("xx after evaluate")
        println("xx before loop")
        for (v in iterator.channel) {
            println("xx in loop")
            val s = rs.getValue(v[ks])
            val p = rs.getValue(v[kp])
            val o = rs.getValue(v[ko])
            addData(transactionID, listOf(s, p, o))
        }
        println("xx after loop")
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "DistributedGraph.getIterator c" }, {
        return TripleStoreIteratorGlobal(transactionID, dictionary, name, index)
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "DistributedGraph.getIterator b" }, {
        val res = TripleStoreIteratorGlobal(transactionID, dictionary, name, index)
        res.setMNameS(s)
        res.setMNameP(p)
        res.setMNameO(o)
        return res
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: String, p: String, o: String, sv: Boolean, pv: Boolean, ov: Boolean, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "DistributedGraph.getIterator a" }, {
        val res = TripleStoreIteratorGlobal(transactionID, dictionary, name, s, p, o, sv, pv, ov, index)
        return res
    })
}

object DistributedTripleStore {
    val localStore = PersistentStoreLocal()
    fun nextTransactionID(): Long = Trace.trace({ "DistributedTripleStore.nextTransactionID" }, {
        return localStore.nextTransactionID()
    })

    fun getGraphNames(includeDefault: Boolean = false): List<String> = Trace.trace({ "DistributedTripleStore.getGraphNames" }, {
        return localStore.getGraphNames(includeDefault)
    })

    fun createGraph(name: String): DistributedGraph = Trace.trace({ "DistributedTripleStore.createGraph" }, {
        P2P.execGraphOperation(name, EGraphOperationType.CREATE)
        return DistributedGraph(name)
    })

    fun dropGraph(name: String) = Trace.trace({ "DistributedTripleStore.dropGraph" }, {
        P2P.execGraphOperation(name, EGraphOperationType.DROP)
    })

    fun clearGraph(name: String) = Trace.trace({ "DistributedTripleStore.clearGraph" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.clearGraph $name" })
        P2P.execGraphOperation(name, EGraphOperationType.CLEAR)
    })

    fun getNamedGraph(name: String, create: Boolean = false): DistributedGraph = Trace.trace({ "DistributedTripleStore.getNamedGraph" }, {
        if (create && !(localStore.getGraphNames(true).contains(name)))
            createGraph(name)
        return DistributedGraph(name)
    })

    fun getDefaultGraph(): DistributedGraph = Trace.trace({ "DistributedTripleStore.getDefaultGraph" }, {
        return DistributedGraph(localStore.defaultGraphName)
    })

    fun commit(transactionID: Long) = Trace.trace({ "DistributedTripleStore.commit" }, {
        P2P.execCommit(transactionID)
    })
}
