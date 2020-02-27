package lupos.s15tripleStoreDistributed
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable

import lupos.s04logicalOperators.OPBase
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s12p2p.P2P



val uuid = ThreadSafeUuid()

class TripleStoreIteratorGlobal : POPTripleStoreIteratorBase {
    override val operatorID = EOperatorID.TripleStoreIteratorGlobalID
    override val classname = "TripleStoreIteratorGlobal"
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    override val resultSet: ResultSet
    val nodeNameIterator: Iterator<String>
    var remoteIterator: Iterator<ResultRow>? = null
    val transactionID: Long
    val graphNameL: String
    val idx: EIndexPattern

    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String) {
        this.transactionID = transactionID
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        graphNameL = graphName
        idx = EIndexPattern.SPO
        nodeNameIterator = P2P.getKnownClientsCopy().iterator()
    }

    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String, index: EIndexPattern) {
        idx = index
        graphNameL = graphName
        this.dictionary = dictionary
        this.transactionID = transactionID
        resultSet = ResultSet(dictionary)
        nodeNameIterator = P2P.getKnownClientsCopy().iterator()
    }

    constructor(transactionID: Long, dictionary: ResultSetDictionary, graphName: String, s: AOPBase, p: AOPBase, o: AOPBase, index: EIndexPattern) {
        idx = index
        graphNameL = graphName
        this.dictionary = dictionary
        this.transactionID = transactionID
        resultSet = ResultSet(dictionary)
        nodeNameIterator = P2P.getKnownClientsCopy().iterator()
        sparam = s
        pparam = p
        oparam = o
    }

    override fun toXMLElement() = XMLElement("TripleStoreIteratorGlobalFilter").addAttribute("uuid", "" + uuid).addAttribute("name", getGraphName()).addContent(XMLElement("sparam").addContent(sparam.toXMLElement())).addContent(XMLElement("pparam").addContent(pparam.toXMLElement())).addContent(XMLElement("oparam").addContent(oparam.toXMLElement()))

    override fun getGraphName(): String = Trace.trace({ "TripleStoreIteratorGlobal.getGraphName" }, {
        return graphNameL
    })

    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        tmp.addAll(sparam.getRequiredVariableNames())
        tmp.addAll(pparam.getRequiredVariableNames())
        tmp.addAll(oparam.getRequiredVariableNames())
        return tmp.distinct()
    }

    override fun evaluate() = Trace.trace<Unit>({ "TripleStoreIteratorGlobal.evaluate" }, {
        CoroutinesHelper.run {
            try {
                for (nodeName in nodeNameIterator) {
                    var remoteNode: OPBase? = null
                    try {
                        remoteNode = P2P.execTripleGet(nodeName, graphNameL, resultSet, transactionID, sparam, pparam, oparam, idx)
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

    fun calculateNodeForDataFull(s: AOPConstant, p: AOPConstant, o: AOPConstant, idx: EIndexPattern): String = Trace.trace({ "DistributedGraph.calculateNodeForDataFull" }, {
        val sh = +myHashCode("" + s.valueToString(), K)
        val ph = +myHashCode("" + p.valueToString(), K)
        val oh = +myHashCode("" + o.valueToString(), K)
        return P2P.getKnownClientsCopy()[myHashCode(sh, ph, oh, P2P.knownClients.size, idx)]
    })

    fun calculateNodeForDataMaybe(s: AOPBase, p: AOPBase, o: AOPBase, idx: EIndexPattern): Set<String> = Trace.trace({ "DistributedGraph.calculateNodeForDataMaybe" }, {
        val res = mutableSetOf<String>()
        val sr = if (s is AOPConstant) {
            val h = myHashCode("" + s.valueToString(), K)
            IntRange(h, h)
        } else
            IntRange(0, K)
        val pr = if (p is AOPConstant) {
            val h = myHashCode("" + p.valueToString(), K)
            IntRange(h, h)
        } else
            IntRange(0, K)
        val or = if (o is AOPConstant) {
            val h = myHashCode("" + o.valueToString(), K)
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

    fun addData(transactionID: Long, t: List<AOPConstant>) = Trace.trace({ "DistributedGraph.addData a" }, {
        EIndexPattern.values().forEach {
            val node = calculateNodeForDataFull(t[0], t[1], t[2], it)
            P2P.execTripleAdd(node, name, transactionID, t[0], t[1], t[2], it)
        }
    })

    fun deleteData(transactionID: Long, t: List<AOPConstant>) = Trace.trace({ "DistributedGraph.deleteData" }, {
        EIndexPattern.values().forEach {
            for (node in calculateNodeForDataMaybe(t[0], t[1], t[2], it)) {
                P2P.execTripleDelete(node, name, transactionID, t, it)
            }
        }
    })

    fun deleteDataVar(transactionID: Long, t: List<AOPBase>) = Trace.trace({ "DistributedGraph.deleteDataVar" }, {
        EIndexPattern.values().forEach {
            for (node in calculateNodeForDataMaybe(t[0], t[1], t[2], it))
                P2P.execTripleDelete(node, name, transactionID, t, it)
        }
    })

    suspend fun addData(transactionID: Long, iterator: OPBase) = Trace.trace({ "DistributedGraph.addData b" }, {
        val rs = iterator.resultSet
        val ks = rs.createVariable("s")
        val kp = rs.createVariable("p")
        val ko = rs.createVariable("o")
        iterator.evaluate()
        for (v in iterator.channel) {
            val s = AOPVariable.calculate(rs.getValue(v[ks]))
            val p = AOPVariable.calculate(rs.getValue(v[kp]))
            val o = AOPVariable.calculate(rs.getValue(v[ko]))
            addData(transactionID, listOf(s, p, o))
        }
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "DistributedGraph.getIterator c" }, {
        return TripleStoreIteratorGlobal(transactionID, dictionary, name, index)
    })

    fun getIterator(transactionID: Long, dictionary: ResultSetDictionary, s: AOPBase, p: AOPBase, o: AOPBase, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "DistributedGraph.getIterator a" }, {
        return TripleStoreIteratorGlobal(transactionID, dictionary, name, s, p, o, index)
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
