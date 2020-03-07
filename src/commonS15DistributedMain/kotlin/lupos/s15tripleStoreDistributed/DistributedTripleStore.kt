package lupos.s15tripleStoreDistributed

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.POPTripleStoreIteratorBase
import lupos.s12p2p.P2P


val uuid = ThreadSafeUuid()

class TripleStoreIteratorGlobal : POPTripleStoreIteratorBase {
    @JvmField
    val nodeNameIterator: Iterator<String>
    @JvmField
    var remoteIterator: Iterator<ResultRow>? = null
    @JvmField
    val graphNameL: String
    @JvmField
    val index: EIndexPattern

    override fun cloneOP() = TripleStoreIteratorGlobal(query, graphNameL, sparam, pparam, oparam, index)

    constructor(query: Query, graphName: String, index: EIndexPattern = EIndexPattern.SPO) : super(query, EOperatorID.TripleStoreIteratorGlobalID, "TripleStoreIteratorGlobal", ResultSet(query.dictionary), arrayOf()) {
        this.index = index
        this.graphNameL = graphName
        nodeNameIterator = P2P.getKnownClientsCopy().iterator()
    }

    constructor(query: Query, graphName: String, s: AOPBase, p: AOPBase, o: AOPBase, index: EIndexPattern) : super(query, EOperatorID.TripleStoreIteratorGlobalID, "TripleStoreIteratorGlobal", ResultSet(query.dictionary), arrayOf()) {
        this.index = index
        this.graphNameL = graphName
        nodeNameIterator = P2P.getKnownClientsCopy().iterator()
        sparam = s
        pparam = p
        oparam = o
    }

    override fun toXMLElement() = XMLElement("TripleStoreIteratorGlobalFilter").//
            addAttribute("uuid", "" + uuid).//
            addAttribute("name", graphNameL).//
            addContent(XMLElement("sparam").addContent(sparam.toXMLElement())).//
            addContent(XMLElement("pparam").addContent(pparam.toXMLElement())).//
            addContent(XMLElement("oparam").addContent(oparam.toXMLElement()))

    override fun toSparql(): String {
        if (graphNameL == PersistentStoreLocal.defaultGraphName)
            return sparam.toSparql() + " " + pparam.toSparql() + " " + oparam.toSparql() + "."
        return "GRAPH <$graphNameL> {" + sparam.toSparql() + " " + pparam.toSparql() + " " + oparam.toSparql() + "}."
    }

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

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "TripleStoreIteratorGlobal.evaluate" }, {
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (nodeName in nodeNameIterator) {
                    var remoteNode: OPBase? = null
                    var remoteNodeChannel: Channel<ResultRow>? = null
                    try {
                        remoteNode = P2P.execTripleGet(query, nodeName, graphNameL, sparam, pparam, oparam, index)
                        remoteNodeChannel = remoteNode.evaluate()
                        for (c in remoteNodeChannel)
                            channel.send(resultFlowProduce({ this@TripleStoreIteratorGlobal }, { c }))
                    } catch (e: Throwable) {
                        remoteNodeChannel?.close(e)
                        channel.close(e)
                    }
                }
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return channel
    })

}

class DistributedGraph(val query: Query, @JvmField val name: String) {
    @JvmField
    val K = 8 // defined in project.pdf

    fun myHashCode(s: String, d: Int): Int {
        val c = s.hashCode()
        if (c < 0)
            return (-c) % d
        return c % d
    }

    fun myHashCode(s: Int, p: Int, o: Int, d: Int, index: EIndexPattern): Int = Trace.trace({ "DistributedGraph.myHashCode" }, {
        when (index) {
            EIndexPattern.S -> return myHashCode("" + s, d)
            EIndexPattern.P -> return myHashCode("" + p, d)
            EIndexPattern.O -> return myHashCode("" + o, d)
            EIndexPattern.SP -> return myHashCode("" + s + "-" + p, d)
            EIndexPattern.SO -> return myHashCode("" + s + "-" + o, d)
            EIndexPattern.PO -> return myHashCode("" + p + "-" + o, d)
            EIndexPattern.SPO -> return myHashCode("" + s + "-" + p + "-" + o, d)
        }
    })

    fun calculateNodeForDataFull(s: AOPConstant, p: AOPConstant, o: AOPConstant, index: EIndexPattern): String = Trace.trace({ "DistributedGraph.calculateNodeForDataFull" }, {
        val sh = +myHashCode("" + s.valueToString(), K)
        val ph = +myHashCode("" + p.valueToString(), K)
        val oh = +myHashCode("" + o.valueToString(), K)
        return P2P.getKnownClientsCopy()[myHashCode(sh, ph, oh, P2P.knownClients.size(), index)]
    })

    fun calculateNodeForDataMaybe(s: AOPBase, p: AOPBase, o: AOPBase, index: EIndexPattern): Set<String> = Trace.trace({ "DistributedGraph.calculateNodeForDataMaybe" }, {
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
                    res.add(P2P.getKnownClientsCopy()[myHashCode(si, pi, oi, P2P.knownClients.size(), index)])
                }
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "maybe :: " + res })
        return res
    })

    fun addData(t: List<AOPConstant>) = Trace.trace({ "DistributedGraph.addData a" }, {
        EIndexPattern.values().forEach {
            val node = calculateNodeForDataFull(t[0], t[1], t[2], it)
            P2P.execTripleAdd(query, node, name, t[0], t[1], t[2], it)
        }
    })

    fun deleteData(t: List<AOPConstant>) = Trace.trace({ "DistributedGraph.deleteData" }, {
        EIndexPattern.values().forEach {
            for (node in calculateNodeForDataMaybe(t[0], t[1], t[2], it)) {
                P2P.execTripleDelete(query, node, name, t, it)
            }
        }
    })

    fun deleteDataVar(t: List<AOPBase>) = Trace.trace({ "DistributedGraph.deleteDataVar" }, {
        EIndexPattern.values().forEach {
            for (node in calculateNodeForDataMaybe(t[0], t[1], t[2], it))
                P2P.execTripleDelete(query, node, name, t, it)
        }
    })

    suspend fun addData(iterator: OPBase) = Trace.trace({ "DistributedGraph.addData b" }, {
        val rs = iterator.resultSet
        val ks = rs.createVariable("s")
        val kp = rs.createVariable("p")
        val ko = rs.createVariable("o")
        val channel = iterator.evaluate()
        for (v in channel) {
            val s = AOPVariable.calculate(query, rs.getValue(v[ks]))
            val p = AOPVariable.calculate(query, rs.getValue(v[kp]))
            val o = AOPVariable.calculate(query, rs.getValue(v[ko]))
            addData(listOf(s, p, o))
        }
    })

    fun getIterator(index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "DistributedGraph.getIterator c" }, {
        return TripleStoreIteratorGlobal(query, name, index)
    })

    fun getIterator(s: AOPBase, p: AOPBase, o: AOPBase, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "DistributedGraph.getIterator a" }, {
        return TripleStoreIteratorGlobal(query, name, s, p, o, index)
    })
}

object DistributedTripleStore {
    @JvmField
    val localStore = PersistentStoreLocal()

    fun nextTransactionID(): Long = Trace.trace({ "DistributedTripleStore.nextTransactionID" }, {
        return localStore.nextTransactionID()
    })

    fun getGraphNames(includeDefault: Boolean = false): List<String> = Trace.trace({ "DistributedTripleStore.getGraphNames" }, {
        return localStore.getGraphNames(includeDefault)
    })

    fun createGraph(query: Query, name: String): DistributedGraph = Trace.trace({ "DistributedTripleStore.createGraph" }, {
        P2P.execGraphOperation(query, name, EGraphOperationType.CREATE)
        return DistributedGraph(query, name)
    })

    fun dropGraph(query: Query, name: String) = Trace.trace({ "DistributedTripleStore.dropGraph" }, {
        P2P.execGraphOperation(query, name, EGraphOperationType.DROP)
    })

    fun clearGraph(query: Query, name: String) = Trace.trace({ "DistributedTripleStore.clearGraph" }, {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.clearGraph $name" })
        P2P.execGraphOperation(query, name, EGraphOperationType.CLEAR)
    })

    fun getNamedGraph(query: Query, name: String, create: Boolean = false): DistributedGraph = Trace.trace({ "DistributedTripleStore.getNamedGraph" }, {
        if (!(localStore.getGraphNames(true).contains(name)))
            createGraph(query, name)
        return DistributedGraph(query, name)
    })

    fun getDefaultGraph(query: Query): DistributedGraph = Trace.trace({ "DistributedTripleStore.getDefaultGraph" }, {
        return DistributedGraph(query, PersistentStoreLocal.defaultGraphName)
    })

    fun commit(query: Query) = Trace.trace({ "DistributedTripleStore.commit" }, {
        P2P.execCommit(query)
    })
}
