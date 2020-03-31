package lupos.s15tripleStoreDistributed

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s09physicalOperators.POPBase
import lupos.s12p2p.P2P

class TripleStoreIteratorGlobal(query: Query, val graphName: String, val params: Array<AOPBase>, val idx: EIndexPattern, val nodeNames: List<String>) : POPBase(query, EOperatorID.TripleStoreIteratorGlobalID, "TripleStoreIteratorGlobal", arrayOf()) {
    override fun cloneOP() = TripleStoreIteratorGlobal(query, graphName, params, idx, nodeNames)
    override fun toXMLElement() = XMLElement("TripleStoreIteratorGlobalFilter").//
            addAttribute("uuid", "" + uuid).//
            addAttribute("name", graphName).//
            addContent(XMLElement("sparam").addContent(params[0].toXMLElement())).//
            addContent(XMLElement("pparam").addContent(params[1].toXMLElement())).//
            addContent(XMLElement("oparam").addContent(params[2].toXMLElement()))

    override fun toSparql(): String {
        if (graphName == PersistentStoreLocal.defaultGraphName) {
            return params[0].toSparql() + " " + params[1].toSparql() + " " + params[2].toSparql() + "."
        }
        return "GRAPH <$graphName> {" + params[0].toSparql() + " " + params[1].toSparql() + " " + params[2].toSparql() + "}."
    }

    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        for (p in params) {
            tmp.addAll(p.getRequiredVariableNames())
        }
        return tmp.distinct()
    }

    override suspend fun evaluate(): ColumnIteratorRow {
        val outMap = mutableMapOf<String, ColumnIteratorChildIterator>()
        val variables: List<String>
        if (idx == EIndexPattern.SPO) {
            idx.keyIndices.map { require(params[it] is AOPVariable) }
            variables = idx.keyIndices.map { (params[it] as AOPVariable).name }
        } else {
            variables = idx.valueIndices.map { (params[it] as AOPVariable).name }
            idx.keyIndices.map { require(params[it] is AOPConstant) }
            idx.valueIndices.map { require(params[it] is AOPVariable) }
        }
        val nodeNameIterator = nodeNames.iterator()
        for (variable in variables) {
            val tmp = ColumnIteratorChildIterator()
            tmp.onNoMoreElements = {
                if (nodeNameIterator.hasNext()) {
                    val nodeName = nodeNameIterator.next()
                    val row = P2P.execTripleGet(query, nodeName, graphName, params, idx)
                    for (variable2 in variables) {
                        outMap[variable2]!!.childs.add(row.columns[variable2]!!)
                    }
                } else {
                    for (variable2 in variables) {
                        outMap[variable2]!!.close()
                    }
                }
            }
            outMap[variable] = tmp
        }
        return ColumnIteratorRow(outMap)
    }
}

class DistributedGraph(val query: Query, @JvmField val name: String) {
    @JvmField
    val K = 8 // defined in project.pdf

    fun myHashCode(s: String, d: Int): Int {
        val c = s.hashCode()
        if (c < 0) {
            return (-c) % d
        }
        return c % d
    }

    fun myHashCode(s: Int, p: Int, o: Int, d: Int, idx: EIndexPattern): Int {
        when (idx) {
            EIndexPattern.S -> {
                return myHashCode("" + s, d)
            }
            EIndexPattern.P -> {
                return myHashCode("" + p, d)
            }
            EIndexPattern.O -> {
                return myHashCode("" + o, d)
            }
            EIndexPattern.SP -> {
                return myHashCode("" + s + "-" + p, d)
            }
            EIndexPattern.SO -> {
                return myHashCode("" + s + "-" + o, d)
            }
            EIndexPattern.PO -> {
                return myHashCode("" + p + "-" + o, d)
            }
            EIndexPattern.SPO -> {
                return myHashCode("" + s + "-" + p + "-" + o, d)
            }
        }
    }

    fun calculateNodeForDataFull(params: Array<ValueDefinition>, idx: EIndexPattern): String {
        val paramsLocal = Array(3) { myHashCode("" + params[it].valueToString(), K) }
        return P2P.knownClients[myHashCode(paramsLocal[0], paramsLocal[1], paramsLocal[2], P2P.knownClients.size, idx)]
    }

    fun calculateNodeForDataMaybe(params: Array<AOPBase>, idx: EIndexPattern): Set<String> {
        val res = mutableSetOf<String>()
        val arr = Array<IntRange>(3) {
            var res: IntRange
            if (params[it] is AOPConstant) {
                val h = myHashCode("" + (params[it] as AOPConstant).value.valueToString(), K)
                res = IntRange(h, h)
            } else {
                res = IntRange(0, K)
            }
/*return*/ res
        }
        for (si in arr[0]) {
            for (pi in arr[1]) {
                for (oi in arr[2]) {
                    res.add(P2P.knownClients[myHashCode(si, pi, oi, P2P.knownClients.size, idx)])
                }
            }
        }
        GlobalLogger.log(ELoggerType.DEBUG, { "maybe :: " + res })
        return res
    }

    suspend fun modify(data: Array<ColumnIterator>, type: EModifyType) {
        require(data.size == 3)
        val map = mutableMapOf<String, Array<Array<MutableList<Value>>>>()
        loop@ while (true) {
            val row = Array(3) { ResultSetDictionary.undefValue }
            for (columnIndex in 0 until 3) {
                val v = data[columnIndex].next()
                if (v == null) {
                    require(columnIndex == 0)
                    break@loop
                }
                row[columnIndex] = v
            }
            for (idx in EIndexPattern.values()) {
                val node = calculateNodeForDataFull(row.map { query.dictionary.getValue(it) }.toTypedArray(), idx)
                var tmp = map[node]
                if (tmp == null) {
                    tmp = Array(EIndexPattern.values().size) { Array(3) { mutableListOf<Value>() } }
                    map[node] = tmp
                }
                for (columnIndex in 0 until 3) {
                    tmp[idx.ordinal][columnIndex].add(row[columnIndex])
                }
            }
        }
        for ((node, iterator) in map) {
            for (idx in EIndexPattern.values()) {
                if (iterator[idx.ordinal][0].size > 0) {
                    P2P.execTripleModify(query, node, name, Array(3) { ColumnIteratorMultiValue(iterator[idx.ordinal][it]) }, idx, type)
                }
            }
        }
    }

    fun getIterator(idx: EIndexPattern): POPBase {
        return TripleStoreIteratorGlobal(query, name, arrayOf<AOPBase>(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), idx, P2P.knownClients)
    }

    fun getIterator(params: Array<AOPBase>, idx: EIndexPattern): POPBase {
        return TripleStoreIteratorGlobal(query, name, params, idx, calculateNodeForDataMaybe(params, idx).toList())
    }
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

    fun getNamedGraph(query: Query, name: String): DistributedGraph = Trace.trace({ "DistributedTripleStore.getNamedGraph" }, {
        if (!(localStore.getGraphNames(true).contains(name))) {
            createGraph(query, name)
        }
        return DistributedGraph(query, name)
    })

    fun getDefaultGraph(query: Query): DistributedGraph = Trace.trace({ "DistributedTripleStore.getDefaultGraph" }, {
        return DistributedGraph(query, PersistentStoreLocal.defaultGraphName)
    })

    fun commit(query: Query) = Trace.trace({ "DistributedTripleStore.commit" }, {
        P2P.execCommit(query)
    })
}
