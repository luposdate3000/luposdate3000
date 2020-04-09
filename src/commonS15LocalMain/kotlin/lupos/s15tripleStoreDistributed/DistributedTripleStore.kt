package lupos.s15tripleStoreDistributed

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s00misc.EGraphOperationType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.GlobalLogger
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.*
import lupos.s05tripleStore.PersistentStoreLocal
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
        if (idx == EIndexPattern.SPO) {
            if (children[0] is AOPVariable) {
                idx.keyIndices.map { require(children[it] is AOPVariable, { "$graphName ${idx} ${children.map { it }}" }) }
            } else {
                idx.keyIndices.map { require(children[it] is AOPConstant, { "$graphName ${idx} ${children.map { it }}" }) }
            }
        } else {
            idx.keyIndices.map { require(children[it] is AOPConstant) }
            idx.valueIndices.map { require(children[it] is AOPVariable) }
        }
    }

    override suspend fun evaluate(): ColumnIteratorRow {
        return Endpoint.process_local_triple_get(query, graphName, Array(3) { children[it] as AOPBase }, idx)
    }
}

class DistributedGraph(val query: Query, @JvmField val name: String) {
    suspend fun bulkImport(data: TripleStoreBulkImport) {
        for (idx in EIndexPattern.values()) {
            Endpoint.process_local_triple_import(query, name, data, idx)
        }
    }

    suspend fun modify(data: Array<ColumnIterator>, type: EModifyType) {
        require(data.size == 3)
        val map = Array(EIndexPattern.values().size) { Array(3) { mutableListOf<Value>() } }
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
        if (idx == EIndexPattern.SPO) {
            if (params[0] is AOPVariable) {
                idx.keyIndices.map { require(params[it] is AOPVariable, { "$idx ${params.map { it }}" }) }
                idx.keyIndices.map {
                    val tmp = (params[it] as AOPVariable).name
                    if (tmp != "_") {
                        projectedVariables.add(tmp)
                    }
                }
            } else {
                idx.keyIndices.map { require(params[it] is AOPConstant, { "$idx ${params.map { it }}" }) }
            }
        } else {
            idx.keyIndices.map { require(params[it] is AOPConstant, { "$idx ${params.map { it }}" }) }
            idx.valueIndices.map { require(params[it] is AOPVariable, { "$idx ${params.map { it }}" }) }
            idx.valueIndices.map {
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
