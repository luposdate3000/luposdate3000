package lupos.s15tripleStoreDistributed

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s09physicalOperators.POPBase
import lupos.s16network.*

class TripleStoreIteratorGlobal(query: Query, projectedVariables: List<String>, val graphName: String, params: Array<AOPBase>, val idx: EIndexPattern) : POPBase(query, projectedVariables, EOperatorID.TripleStoreIteratorGlobalID, "TripleStoreIteratorGlobal", Array<OPBase>(3) { params[it] }, ESortPriority.ANY_PROVIDED_VARIABLE) {
    override fun cloneOP() = TripleStoreIteratorGlobal(query, projectedVariables, graphName, Array(3) { children[it] as AOPBase }, idx)
    override fun equals(other: Any?) = other is TripleStoreIteratorGlobal && graphName == other.graphName && idx == other.idx && projectedVariables.containsAll(other.projectedVariables) && other.projectedVariables.containsAll(projectedVariables) && children[0] == other.children[0] && children[1] == other.children[1] && children[2] == other.children[2]
    override fun toXMLElement() = XMLElement("TripleStoreIteratorGlobal").//
            addAttribute("uuid", "" + uuid).//
            addAttribute("name", graphName).//
            addAttribute("idx", "" + idx).//
            addAttribute("providedVariables", getProvidedVariableNames().toString()).//
            addAttribute("providedSort", getPossibleSortPriorities().toString()).//
            addAttribute("selectedSort", mySortPriority.toString()).//
            addContent(XMLElement("sparam").addContent(children[0].toXMLElement())).//
            addContent(XMLElement("pparam").addContent(children[1].toXMLElement())).//
            addContent(XMLElement("oparam").addContent(children[2].toXMLElement()))

    override fun toSparql(): String {
        if (graphName == PersistentStoreLocal.defaultGraphName) {
            return children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "."
        }
        return "GRAPH <$graphName> {" + children[0].toSparql() + " " + children[1].toSparql() + " " + children[2].toSparql() + "}."
    }

    override fun getProvidedVariableNamesInternal(): List<String> {
        val tmp = mutableListOf<String>()
        for (p in children) {
            tmp.addAll(p.getRequiredVariableNames())
        }
        tmp.remove("_")
        tmp.remove("_")
        tmp.remove("_")
        return tmp.distinct()
    }

    init {
        SanityCheck {
            if (idx.keyIndices.size == 3) {
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
        return ServerCommunicationSend.tripleGet(query, graphName, Array(3) { children[it] as AOPBase }, idx)
    }
}

class DistributedGraph(val query: Query, @JvmField val name: String) {
    suspend fun bulkImport(data: TripleStoreBulkImport, idx: EIndexPattern) {
        DistributedTripleStore.localStore.getNamedGraph(query, name).import(data, idx)
    }

    suspend fun modify(data: Array<ColumnIterator>, type: EModifyType) {
        SanityCheck.check { data.size == 3 }
        val map = Array(EIndexPattern.values().size) { Array(3) { MyListValue() } }
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
            for (idx in TripleStoreLocalBase.distinctIndices) {
                for (columnIndex in 0 until 3) {
                    map[idx.ordinal][columnIndex].add(row[columnIndex])
                }
            }
        }
        for (idx in TripleStoreLocalBase.distinctIndices) {
            if (map[idx.ordinal][0].size > 0) {
                DistributedTripleStore.localStore.getNamedGraph(query, name).modify(query, Array(3) { ColumnIteratorMultiValue(map[idx.ordinal][it]) }, idx, type)
            }
        }
    }

    fun getIterator(idx: EIndexPattern): POPBase {
        val projectedVariables = listOf<String>("s", "p", "o")
        return TripleStoreIteratorGlobal(query, projectedVariables, name, arrayOf<AOPBase>(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), idx)
    }

    fun getIterator(params: Array<AOPBase>, idx: EIndexPattern): POPBase {
        val projectedVariables = mutableListOf<String>()
        SanityCheck {
            if (idx.keyIndices.size == 3) {
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
        if (idx.keyIndices.size == 3) {
            if (params[0] is AOPVariable) {
                idx.keyIndices.forEach {
                    val tmp = (params[it] as AOPVariable).name
                    if (tmp != "_") {
                        projectedVariables.add(tmp)
                    }
                }
            }
        } else {
            idx.valueIndices.forEach {
                val tmp = (params[it] as AOPVariable).name
                if (tmp != "_") {
                    projectedVariables.add(tmp)
                }
            }
        }
        return TripleStoreIteratorGlobal(query, projectedVariables, name, params, idx)
    }

    fun getHistogram(params: Array<AOPBase>, idx: EIndexPattern): Pair<Int, Int> {
        SanityCheck {
            if (idx.keyIndices.size == 3) {
                if (params[0] is AOPVariable) {
                    idx.keyIndices.map { SanityCheck.check { params[it] is AOPVariable } }
                } else {
                    idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
                }
            } else {
                idx.keyIndices.map { SanityCheck.check { params[it] is AOPConstant } }
                idx.valueIndices.map { SanityCheck.check { params[it] is AOPVariable } }
            }
            var variableNames = 0
            if (idx.keyIndices.size == 3) {
                if (params[0] is AOPVariable) {
                    idx.keyIndices.forEach {
                        val tmp = (params[it] as AOPVariable).name
                        if (tmp != "_") {
                            variableNames++
                        }
                    }
                }
            } else {
                idx.valueIndices.forEach {
                    val tmp = (params[it] as AOPVariable).name
                    if (tmp != "_") {
                        variableNames++
                    }
                }
            }
            SanityCheck { variableNames == 1 }
        }
        return ServerCommunicationSend.histogramGet(query, name, params, idx)
    }
}

object DistributedTripleStore {
    @JvmField
    val localStore = PersistentStoreLocal()

    fun getGraphNames(includeDefault: Boolean = false): List<String> {
        return localStore.getGraphNames(includeDefault)
    }

    fun createGraph(query: Query, name: String): DistributedGraph {
        ServerCommunicationSend.graphOperation(query, name, EGraphOperationType.CREATE)
        return DistributedGraph(query, name)
    }

    fun dropGraph(query: Query, name: String) {
        ServerCommunicationSend.graphOperation(query, name, EGraphOperationType.DROP)
    }

    fun clearGraph(query: Query, name: String) {
        GlobalLogger.log(ELoggerType.DEBUG, { "DistributedTripleStore.clearGraph $name" })
        ServerCommunicationSend.graphOperation(query, name, EGraphOperationType.CLEAR)
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
        ServerCommunicationSend.commit(query)
    }
}
