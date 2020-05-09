package lupos.s15tripleStoreDistributed
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
import lupos.s00misc.GlobalLogger
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorDistinct
import lupos.s04logicalOperators.iterator.ColumnIteratorMergeSort
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.iterator.ColumnIteratorStore1
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3c
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s09physicalOperators.POPBase
import lupos.s12p2p.P2P
import lupos.s14endpoint.*




class TripleStoreIteratorGlobal(query: Query, projectedVariables: List<String>, val graphName: String, params: Array<AOPBase>, val idx: EIndexPattern) : POPBase(query, projectedVariables, EOperatorID.TripleStoreIteratorGlobalID, "TripleStoreIteratorGlobal", Array<OPBase>(3) { params[it] }, ESortPriority.ANY_PROVIDED_VARIABLE) {
    override fun cloneOP() = TripleStoreIteratorGlobal(query, projectedVariables, graphName, Array(3) { children[it] as AOPBase }, idx)
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

    override suspend fun evaluate(): ColumnIteratorRow {
        return Endpoint.process_local_triple_get(query, graphName, Array(3) { children[it] as AOPBase }, idx)
    }
}

class DistributedGraph(val query: Query, @JvmField val name: String) {
    suspend fun bulkImport(data: TripleStoreBulkImport) {
        for (idx in TripleStoreLocalBase.distinctIndices) {
            Endpoint.process_local_triple_import(query, name, data, idx)
        }
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
            for (idx in EIndexPattern.values()) {
                for (columnIndex in 0 until 3) {
                    map[idx.ordinal][columnIndex].add(row[columnIndex])
                }
            }
        }
        for (idx in EIndexPattern.values()) {
            if (map[idx.ordinal][0].size > 0) {
                Endpoint.process_local_triple_modify(query, name, Array(3) { ColumnIteratorMultiValue(map[idx.ordinal][it]) }, idx, type)
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
