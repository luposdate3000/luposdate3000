package lupos.s15tripleStoreDistributed
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.TriplePatternsContainingTheSameVariableTwiceNotImplementedException
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s05tripleStore.ITripleStoreBulkImport
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.PersistentStoreLocalExt
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreFeatureParams
import lupos.s05tripleStore.TripleStoreFeatureParamsDefault
import lupos.s05tripleStore.TripleStoreFeatureParamsPartition
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField
public class TripleStoreIteratorGlobal(query: IQuery, projectedVariables: List<String>, @JvmField public val graphName: String, params: Array<IAOPBase>, @JvmField public val idx: EIndexPattern, @JvmField public val partition: Partition) : POPBase(query, projectedVariables, EOperatorIDExt.TripleStoreIteratorGlobalID, "TripleStoreIteratorGlobal", Array(3) { params[it] }, ESortPriorityExt.ANY_PROVIDED_VARIABLE) {
    override fun getPartitionCount(variable: String): Int {
        val res = partition.limit[variable]
        if (res != null) {
            return res
        }
        return 1
    }
    override fun cloneOP(): TripleStoreIteratorGlobal = TripleStoreIteratorGlobal(query, projectedVariables, graphName, Array(3) { children[it] as IAOPBase }, idx, partition)
    override fun equals(other: Any?): Boolean = other is TripleStoreIteratorGlobal && graphName == other.graphName && idx == other.idx && projectedVariables.containsAll(other.projectedVariables) && other.projectedVariables.containsAll(projectedVariables) && children[0] == other.children[0] && children[1] == other.children[1] && children[2] == other.children[2]
    override /*suspend*/ fun toXMLElement(): XMLElement {
        return XMLElement("TripleStoreIteratorGlobal") //
            .addAttribute("uuid", "" + uuid) //
            .addAttribute("name", graphName) //
            .addAttribute("idx", "" + idx) //
            .addAttribute("providedVariables", getProvidedVariableNames().toString()) //
            .addAttribute("providedSort", getPossibleSortPriorities().toString()) //
            .addAttribute("selectedSort", mySortPriority.toString()) //
            .addContent(XMLElement("sparam").addContent(children[0].toXMLElement())) //
            .addContent(XMLElement("pparam").addContent(children[1].toXMLElement())) //
            .addContent(XMLElement("oparam").addContent(children[2].toXMLElement())) //
            .addContent(XMLElement("partition").addContent(partition.toXMLElement()))
    }
    override fun toSparql(): String {
        if (graphName == PersistentStoreLocalExt.defaultGraphName) {
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
            if (EIndexPatternHelper.keyIndices[idx].size == 3) {
                if (params[0] is AOPVariable) {
                    EIndexPatternHelper.keyIndices[idx].map { SanityCheck.check { params[it] is AOPVariable } }
                } else {
                    EIndexPatternHelper.keyIndices[idx].map { SanityCheck.check { params[it] is AOPConstant } }
                }
            } else {
                EIndexPatternHelper.keyIndices[idx].map { SanityCheck.check { params[it] is AOPConstant } }
                EIndexPatternHelper.valueIndices[idx].map { SanityCheck.check { params[it] is AOPVariable } }
            }
        }
    }
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.println { "opening store for $uuid" }
        SanityCheck {
            for ((k, v) in parent.limit) {
                SanityCheck.check({ partition.limit[k] == v || v == 1 }, { "${parent.limit} ${partition.limit}" })
            }
//            for ((k, v) in partition.limit) {
//                SanityCheck.check({ parent.limit[k] == v || v == 1 }, { "${parent.limit} ${partition.limit}" })
//            }
        }
        var params: TripleStoreFeatureParams? = null
        if (parent.data.isNotEmpty()) {
            params = TripleStoreFeatureParamsPartition(idx, Array(3) { children[it] as IAOPBase }, parent)
        }
        if (params == null) {
            params = TripleStoreFeatureParamsDefault(idx, Array(3) { children[it] as IAOPBase })
        }
        return distributedTripleStore.getLocalStore().getNamedGraph(query, graphName).getIterator(query, params)
    }
}
public class DistributedGraph(@JvmField public val query: IQuery, @JvmField public val name: String) : IDistributedGraph {
    override /*suspend*/ fun bulkImport(action: /*suspend*/ (ITripleStoreBulkImport) -> Unit) {
        val bulk = TripleStoreBulkImport(query, name)
        action(bulk as ITripleStoreBulkImport)
        bulk.finishImport()
    }
    override /*suspend*/ fun modify(data: Array<ColumnIterator>, type: EModifyType) {
        SanityCheck.check { data.size == 3 }
        val map = Array(3) { mutableListOf<Int>() }
        loop@ while (true) {
            val row = Array(3) { ResultSetDictionaryExt.undefValue }
            for (columnIndex in 0 until 3) {
                val v = data[columnIndex].next()
                if (v == ResultSetDictionaryExt.nullValue) {
                    for (element in data) {
                        element.close()
                    }
                    SanityCheck.check { columnIndex == 0 }
                    break@loop
                }
                row[columnIndex] = v
            }
            for (columnIndex in 0 until 3) {
                map[columnIndex].add(row[columnIndex])
            }
        }
        if (map[0].size > 0) {
            distributedTripleStore.getLocalStore().getNamedGraph(query, name).modify(query, Array(3) { ColumnIteratorMultiValue(map[it]) }, type)
        }
    }
    override fun getIterator(idx: EIndexPattern, partition: Partition): POPBase {
        val projectedVariables = listOf("s", "p", "o")
        return TripleStoreIteratorGlobal(query, projectedVariables, name, arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), idx, partition)
    }
    override fun getIterator(params: Array<IAOPBase>, idx: EIndexPattern, partition: Partition): POPBase {
        val projectedVariables = mutableListOf<String>()
        SanityCheck {
            if (EIndexPatternHelper.keyIndices[idx].size == 3) {
                if (params[0] is AOPVariable) {
                    EIndexPatternHelper.keyIndices[idx].map { SanityCheck.check { params[it] is AOPVariable } }
                } else {
                    EIndexPatternHelper.keyIndices[idx].map { SanityCheck.check { params[it] is AOPConstant } }
                }
            } else {
                EIndexPatternHelper.keyIndices[idx].map { SanityCheck.check { params[it] is AOPConstant } }
                EIndexPatternHelper.valueIndices[idx].map { SanityCheck.check { params[it] is AOPVariable } }
            }
        }
        if (EIndexPatternHelper.keyIndices[idx].size == 3) {
            if (params[0] is AOPVariable) {
                EIndexPatternHelper.keyIndices[idx].forEach {
                    val tmp = (params[it] as AOPVariable).name
                    if (tmp != "_") {
                        projectedVariables.add(tmp)
                    }
                }
            }
        } else {
            EIndexPatternHelper.valueIndices[idx].forEach {
                val tmp = (params[it] as AOPVariable).name
                if (tmp != "_") {
                    projectedVariables.add(tmp)
                }
            }
        }
        return TripleStoreIteratorGlobal(query, projectedVariables, name, params, idx, partition)
    }
    override /*suspend*/ fun getHistogram(params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int> {
        SanityCheck {
            if (EIndexPatternHelper.keyIndices[idx].size == 3) {
                if (params[0] is AOPVariable) {
                    EIndexPatternHelper.keyIndices[idx].map { SanityCheck.check { params[it] is AOPVariable } }
                } else {
                    EIndexPatternHelper.keyIndices[idx].map { SanityCheck.check { params[it] is AOPConstant } }
                }
            } else {
                EIndexPatternHelper.keyIndices[idx].map { SanityCheck.check { params[it] is AOPConstant } }
                EIndexPatternHelper.valueIndices[idx].map { SanityCheck.check { params[it] is AOPVariable } }
            }
            var variableNames = 0
            if (EIndexPatternHelper.keyIndices[idx].size == 3) {
                if (params[0] is AOPVariable) {
                    EIndexPatternHelper.keyIndices[idx].forEach {
                        val tmp = (params[it] as AOPVariable).name
                        if (tmp != "_") {
                            variableNames++
                        }
                    }
                }
            } else {
                EIndexPatternHelper.valueIndices[idx].forEach {
                    val tmp = (params[it] as AOPVariable).name
                    if (tmp != "_") {
                        variableNames++
                    }
                }
            }
            if (variableNames > 1) {
                throw TriplePatternsContainingTheSameVariableTwiceNotImplementedException()
            }
            SanityCheck.check { variableNames > 0 }
        }
        return distributedTripleStore.getLocalStore().getNamedGraph(query, name).getHistogram(query, TripleStoreFeatureParamsDefault(idx, params))
    }
}
public class DistributedTripleStore : IDistributedTripleStore {
    @JvmField
    public val localStore: PersistentStoreLocal = PersistentStoreLocal()
    override fun reloadPartitioningScheme() {
        localStore.reloadPartitioningScheme()
    }
    override fun getLocalStore(): PersistentStoreLocal = localStore
    override fun getGraphNames(): List<String> {
        return getGraphNames(false)
    }
    override fun getGraphNames(includeDefault: Boolean): List<String> {
        return localStore.getGraphNames(includeDefault)
    }
    override /*suspend*/ fun createGraph(query: IQuery, name: String): DistributedGraph {
        localStore.createGraph(query, name)
        return DistributedGraph(query, name)
    }
    override /*suspend*/ fun dropGraph(query: IQuery, name: String) {
        localStore.dropGraph(query, name)
    }
    override /*suspend*/ fun clearGraph(query: IQuery, name: String) {
        SanityCheck.println { "DistributedTripleStore.clearGraph $name" }
        localStore.clearGraph(query, name)
    }
    override /*suspend*/ fun getNamedGraph(query: IQuery, name: String): DistributedGraph {
        if (!(localStore.getGraphNames(true).contains(name))) {
            createGraph(query, name)
        }
        return DistributedGraph(query, name)
    }
    override fun getDefaultGraph(query: IQuery): DistributedGraph {
        return DistributedGraph(query, PersistentStoreLocalExt.defaultGraphName)
    }
    override /*suspend*/ fun commit(query: IQuery) {
        localStore.commit(query)
    }
}
