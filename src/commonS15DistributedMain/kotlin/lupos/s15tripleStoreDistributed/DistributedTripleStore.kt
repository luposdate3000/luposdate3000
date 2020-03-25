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
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
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

    override fun cloneOP() = TripleStoreIteratorGlobal(query, graphNameL, params, index)

    constructor(query: Query, graphName: String, index: EIndexPattern = EIndexPattern.SPO) : super(query, EOperatorID.TripleStoreIteratorGlobalID, "TripleStoreIteratorGlobal", ResultSet(query.dictionary), arrayOf()) {
        this.index = index
        this.graphNameL = graphName
        nodeNameIterator = P2P.getKnownClientsCopy().iterator()
    }

    constructor(query: Query, graphName: String, params: Array<AOPBase>, index: EIndexPattern) : super(query, EOperatorID.TripleStoreIteratorGlobalID, "TripleStoreIteratorGlobal", ResultSet(query.dictionary), arrayOf()) {
        this.index = index
        this.graphNameL = graphName
        nodeNameIterator = P2P.getKnownClientsCopy().iterator()
        this.params = params
    }

    override fun toXMLElement() = XMLElement("TripleStoreIteratorGlobalFilter").//
            addAttribute("uuid", "" + uuid).//
            addAttribute("name", graphNameL).//
            addContent(XMLElement("sparam").addContent(params[0].toXMLElement())).//
            addContent(XMLElement("pparam").addContent(params[1].toXMLElement())).//
            addContent(XMLElement("oparam").addContent(params[2].toXMLElement()))

    override fun toSparql(): String {
        if (graphNameL == PersistentStoreLocal.defaultGraphName)
            return params[0].toSparql() + " " + params[1].toSparql() + " " + params[2].toSparql() + "."
        return "GRAPH <$graphNameL> {" + params[0].toSparql() + " " + params[1].toSparql() + " " + params[2].toSparql() + "}."
    }

    override fun getGraphName(): String = Trace.trace({ "TripleStoreIteratorGlobal.getGraphName" }, {
        return graphNameL
    })

    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        for (p in params)
            tmp.addAll(p.getRequiredVariableNames())
        return tmp.distinct()
    }

    class ResultIteratorImpl(var iterator: ResultIterator) : ResultIterator() {
    }

    override fun evaluate() = Trace.trace<ResultIterator>({ "TripleStoreIteratorGlobal.evaluate" }, {
        //column based
        val iterator = P2P.getKnownClientsCopy().iterator()
        val res = ResultIteratorImpl(P2P.execTripleGet(query, resultSet, iterator.next(), graphNameL, params, index).evaluate())
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "TripleStoreIteratorGlobal.next" }, {
                var outbuf: ResultChunk
                try {
                    outbuf = res.iterator.next()
                } catch (e: Throwable) {
                    if (iterator.hasNext()) {
                        res.iterator = P2P.execTripleGet(query, resultSet, iterator.next(), graphNameL, params, index).evaluate()
                    } else {
                        res.iterator.close()
                        res.close()
                    }
                    outbuf = res.next()
                }
                resultFlowProduce({ this@TripleStoreIteratorGlobal }, { outbuf })
            })
        }
        res.close = {
            res.iterator.close()
            res._close()
        }
        return res
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

    fun calculateNodeForDataFull(params: Array<ValueDefinition>, index: EIndexPattern): String = Trace.trace({ "DistributedGraph.calculateNodeForDataFull" }, {
        val params = Array(3) { myHashCode("" + params[it].valueToString(), K) }
        return P2P.getKnownClientsCopy()[myHashCode(params[0], params[1], params[2], P2P.knownClients.size(), index)]
    })

    fun calculateNodeForDataMaybe(params: Array<AOPBase>, index: EIndexPattern): Set<String> = Trace.trace({ "DistributedGraph.calculateNodeForDataMaybe" }, {
        val res = mutableSetOf<String>()
        val arr = Array<IntRange>(3) {
            if (params[it] is AOPConstant) {
                val h = myHashCode("" + (params[it] as AOPConstant).value.valueToString(), K)
                IntRange(h, h)
            } else
                IntRange(0, K)
        }
        for (si in arr[0]) {
            for (pi in arr[1]) {
                for (oi in arr[2]) {
                    res.add(P2P.getKnownClientsCopy()[myHashCode(si, pi, oi, P2P.knownClients.size(), index)])
                }
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "maybe :: " + res })
        return res
    })

    fun addData(t: Array<ValueDefinition>) = Trace.trace({ "DistributedGraph.addData a" }, {
        EIndexPattern.values().forEach {
            val node = calculateNodeForDataFull(t, it)
            P2P.execTripleAdd(query, node, name, t, it)
        }
    })

    fun deleteData(t: Array<ValueDefinition>) = Trace.trace({ "DistributedGraph.deleteData" }, {
        EIndexPattern.values().forEach {
            for (node in calculateNodeForDataMaybe(Array<AOPBase>(3) { AOPConstant(query, t[it]) }, it)) {
                P2P.execTripleDelete(query, node, name, t, it)
            }
        }
    })

    suspend fun addData(iterator: OPBase) = Trace.trace({ "DistributedGraph.addData b" }, {
        val vars = arrayOf(iterator.resultSet.createVariable("s"), iterator.resultSet.createVariable("p"), iterator.resultSet.createVariable("o"))
        val channel = iterator.evaluate()
        channel.forEach { oldRows ->
            for (oldRow in oldRows) {
                val params = Array(3) { iterator.resultSet.getValueObject(oldRow, vars[it]) }
                addData(params)
            }
        }
    })

    fun getIterator(index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "DistributedGraph.getIterator c" }, {
        return TripleStoreIteratorGlobal(query, name, index)
    })

    fun getIterator(params: Array<AOPBase>, index: EIndexPattern): POPTripleStoreIteratorBase = Trace.trace({ "DistributedGraph.getIterator a" }, {
        return TripleStoreIteratorGlobal(query, name, params, index)
    })
}

object DistributedTripleStore {
    @JvmField
    val localStore = PersistentStoreLocal()

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
