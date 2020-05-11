package lupos.s15tripleStoreDistributed

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s09physicalOperators.POPBase
import lupos.s12p2p.P2P

class TripleStoreIteratorGlobal(query: Query, projectedVariables: List<String>, val graphName: String, val params: Array<AOPBase>, val idx: EIndexPattern, val nodeNames: List<String>) : POPBase(query, projectedVariables, EOperatorID.TripleStoreIteratorGlobalID, "TripleStoreIteratorGlobal", arrayOf()) {
    override fun cloneOP() = TripleStoreIteratorGlobal(query, projectedVariables, graphName, params, idx, nodeNames)
    override fun toXMLElement() = XMLElement("TripleStoreIteratorGlobal").//
            addAttribute("uuid", "" + uuid).//
            addAttribute("name", graphName).//
            addAttribute("idx", "" + idx).//
            addContent(XMLElement("sparam").addContent(params[0].toXMLElement())).//
            addContent(XMLElement("pparam").addContent(params[1].toXMLElement())).//
            addContent(XMLElement("oparam").addContent(params[2].toXMLElement()))

    override fun toSparql(): String {
        if (graphName == PersistentStoreLocal.defaultGraphName) {
            return params[0].toSparql() + " " + params[1].toSparql() + " " + params[2].toSparql() + "."
        }
        return "GRAPH <$graphName> {" + params[0].toSparql() + " " + params[1].toSparql() + " " + params[2].toSparql() + "}."
    }

    override fun getProvidedVariableNamesInternal(): List<String> {
        val tmp = mutableListOf<String>()
        for (p in params) {
            tmp.addAll(p.getRequiredVariableNames())
        }
        tmp.remove("_")
        tmp.remove("_")
        tmp.remove("_")
        return tmp.distinct()
    }

    init {
        SanityCheck {
            if (idx == EIndexPattern.SPO) {
                if (params[0] is AOPVariable) {
                    idx.keyIndices.map { SanityCheck.check { params[it] is AOPVariable } }
                } else {
                    idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
                }
            } else {
                idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
                idx.valueIndices.map { SanityCheck.check { params[it] is AOPVariable } }
            }
        }
    }

    override suspend fun evaluate(): IteratorBundle {
        val outMap = mutableMapOf<String, ColumnIterator>()
        val variables = projectedVariables
        SanityCheck {
            if (idx == EIndexPattern.SPO) {
                idx.keyIndices.map { SanityCheck.check { params[it] is AOPVariable } }
            } else {
                idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
                idx.valueIndices.map { SanityCheck.check { params[it] is AOPVariable } }
            }
        }
        val nodeNameIterator = nodeNames.iterator()
        val columns = Array(variables.size) { ColumnIteratorChildIterator() }
        for (variableIndex in 0 until variables.size) {
            val tmp = columns[variableIndex]
            tmp.onNoMoreElements = {
                if (nodeNameIterator.hasNext()) {
                    val nodeName = nodeNameIterator.next()
                    val row = P2P.execTripleGet(query, nodeName, graphName, params, idx)
                    for (variableIndex2 in 0 until variables.size) {
                        columns[variableIndex2].childs.add(row.columns[variables[variableIndex2]]!!)
                    }
                } else {
                    tmp.close()
                }
            }
            outMap[variables[variableIndex]] = ColumnIteratorDebug(uuid, variables[variableIndex], tmp)
        }
        return IteratorBundle(outMap)
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
            EIndexPattern.SP_O -> {
                return myHashCode("" + s + "-" + p, d)
            }
            EIndexPattern.SO_P -> {
                return myHashCode("" + s + "-" + o, d)
            }
            EIndexPattern.PO_S -> {
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
/*return*/res
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
        SanityCheck.check { data.size == 3 }
        val map = mutableMapOf<String, Array<Array<MyListValue>>>()
        loop@ while (true) {
            val row = Array(3) { ResultSetDictionary.undefValue }
            for (columnIndex in 0 until 3) {
                val v = data[columnIndex].next()
                if (v == null) {
                    SanityCheck.check { columnIndex == 0 }
                    break@loop
                }
                row[columnIndex] = v
            }
            for (idx in EIndexPattern.values()) {
                val node = calculateNodeForDataFull(row.map { query.dictionary.getValue(it) }.toTypedArray(), idx)
                var tmp = map[node]
                if (tmp == null) {
                    tmp = Array(EIndexPattern.values().size) { Array(3) { MyListValue() } }
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
        val projectedVariables = listOf<String>("s", "p", "o")
        return TripleStoreIteratorGlobal(query, projectedVariables, name, arrayOf<AOPBase>(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), idx, P2P.knownClients)
    }

    fun getIterator(params: Array<AOPBase>, idx: EIndexPattern): POPBase {
        val projectedVariables = mutableListOf<String>()
        if (idx == EIndexPattern.SPO) {
            idx.keyIndices.map { SanityCheck.check { params[it] is AOPVariable } }
            idx.keyIndices.map {
                val tmp = (params[it] as AOPVariable).name
                if (tmp != "_") {
                    projectedVariables.add(tmp)
                }
            }
        } else {
            idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
            idx.valueIndices.map { SanityCheck.check { params[it] is AOPVariable } }
            idx.valueIndices.map {
                val tmp = (params[it] as AOPVariable).name
                if (tmp != "_") {
                    projectedVariables.add(tmp)
                }
            }
        }
        val res = TripleStoreIteratorGlobal(query, projectedVariables, name, params, idx, calculateNodeForDataMaybe(params, idx).toList())
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
        P2P.execGraphOperation(query, name, EGraphOperationType.CREATE)
        return DistributedGraph(query, name)
    }

    fun dropGraph(query: Query, name: String) {
        P2P.execGraphOperation(query, name, EGraphOperationType.DROP)
    }

    fun clearGraph(query: Query, name: String) {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.clearGraph $name" })
        P2P.execGraphOperation(query, name, EGraphOperationType.CLEAR)
    }

    fun getNamedGraph(query: Query, name: String): DistributedGraph {
        if (!(localStore.getGraphNames(true).contains(name))) {
            createGraph(query, name)
        }
        return DistributedGraph(query, name)
    }

    fun getDefaultGraph(query: Query): DistributedGraph {
        return DistributedGraph(query, PersistentStoreLocal.defaultGraphName)
    }

    fun commit(query: Query) {
        P2P.execCommit(query)
    }
}
