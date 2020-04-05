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
import lupos.s14endpoint.*

class TripleStoreIteratorGlobal(query: Query, projectedVariables: List<String>, val graphName: String, val params: Array<AOPBase>, val idx: EIndexPattern) : POPBase(query, projectedVariables, EOperatorID.TripleStoreIteratorGlobalID, "TripleStoreIteratorGlobal", arrayOf()) {
    override fun cloneOP() = TripleStoreIteratorGlobal(query, projectedVariables, graphName, params, idx)
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
        return tmp.distinct()
    }

    init {
        if (idx == EIndexPattern.SPO) {
            idx.keyIndices.map { require(params[it] is AOPVariable, { "$graphName ${idx} ${params.map { it }}" }) }
        } else {
            idx.keyIndices.map { require(params[it] is AOPConstant) }
            idx.valueIndices.map { require(params[it] is AOPVariable) }
        }
    }

    override suspend fun evaluate(): ColumnIteratorRow {
        return Endpoint.process_local_triple_get(query, graphName, params, idx)
    }
}

class DistributedGraph(val query: Query, @JvmField val name: String) {
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
            idx.keyIndices.map { require(params[it] is AOPVariable, { "$name ${idx} ${params.map { it }}" }) }
            idx.keyIndices.map {
                val tmp = (params[it] as AOPVariable).name
                if (tmp != "_") {
                    projectedVariables.add(tmp)
                }
            }
        } else {
            idx.keyIndices.map { require(params[it] is AOPConstant) }
            idx.valueIndices.map { require(params[it] is AOPVariable) }
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
