package lupos.s09physicalOperators.singleinput
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
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
import lupos.s03resultRepresentation.Variable
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
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s05tripleStore.PersistentStoreLocal
import lupos.s05tripleStore.TripleStoreBulkImport
import lupos.s05tripleStore.TripleStoreLocalBase
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore




class POPModify(query: Query, projectedVariables: List<String>, insert: List<LOPTriple>, delete: List<LOPTriple>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPModifyID, "POPModify", arrayOf(child), ESortPriority.PREVENT_ANY) {
    val modify = Array<Pair<LOPTriple, EModifyType>>(insert.size + delete.size) {
        if (it < insert.size) {
            Pair(insert[it], EModifyType.INSERT)
        } else {
            Pair(delete[it - insert.size], EModifyType.DELETE)
        }
    }

    override fun equals(other: Any?): Boolean = other is POPModify && modify.equals(other.modify) && children[0] == other.children[0]
    override fun toSparqlQuery() = toSparql()
    override fun getProvidedVariableNames() = listOf<String>()
    override fun getProvidedVariableNamesInternal() = children[0].getProvidedVariableNames()
    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (action in modify) {
            if (action.first.graphVar) {
                res.add(action.first.graph)
            }
            for (i in 0 until 3) {
                val tmp = action.first.children[i]
                if (tmp is AOPVariable) {
                    res.add(tmp.name)
                }
            }
        }
        return res.distinct()
    }

    override fun cloneOP(): POPModify {
        val insert = mutableListOf<LOPTriple>()
        val delete = mutableListOf<LOPTriple>()
        for (action in modify) {
            if (action.second == EModifyType.INSERT) {
                insert.add(action.first)
            } else {
                delete.add(action.first)
            }
        }
        return POPModify(query, projectedVariables, insert, delete, children[0].cloneOP())
    }

    override suspend fun evaluate(): ColumnIteratorRow {
        val variables = children[0].getProvidedVariableNames()
        val child = children[0].evaluate()
        val columns = Array(variables.size) { child.columns[variables[it]]!! }
        val row = Array(variables.size) { ResultSetDictionary.undefValue }
        val data = mutableMapOf<String, Array<Array<MyListValue>>>()
        loop@ while (true) {
            for (columnIndex in 0 until variables.size) {
                val value = columns[columnIndex].next()
                if (value == null) {
                    SanityCheck.check { columnIndex == 0 }
                    break@loop
                }
                row[columnIndex] = value
            }
            for (action in modify) {
                var graphVarIdx = 0
                if (action.first.graphVar) {
                    SanityCheck.check { variables.contains(action.first.graph) }
                    while (variables[graphVarIdx] != action.first.graph) {
                        graphVarIdx++
                    }
                }
                var graphName: String
                if (action.first.graphVar) {
                    graphName = query.dictionary.getValue(row[graphVarIdx]).valueToString()!!
                } else {
                    graphName = action.first.graph
                }
                if (data[graphName] == null) {
                    data[graphName] = Array(EModifyType.values().size) { Array(3) { MyListValue() } }
                }
                val target = data[graphName]!![action.second.ordinal]
                loop2@ for (columnIndex in 0 until 3) {
                    val tmp = action.first.children[columnIndex]
                    if (tmp is AOPConstant) {
                        target[columnIndex].add(tmp.value)
                    } else {
                        for (columnIndex2 in 0 until variables.size) {
                            if (variables[columnIndex2] == (tmp as AOPVariable).name) {
                                target[columnIndex].add(row[columnIndex2])
                                continue@loop2
                            }
                        }
                        SanityCheck.check { false }
                    }
                }
            }
        }
        for ((graphName, iterator) in data) {
            val store = DistributedTripleStore.getNamedGraph(query, graphName)
            for (type in EModifyType.values()) {
                if (iterator[type.ordinal][0].size > 0) {
                    store.modify(Array(3) { ColumnIteratorMultiValue(iterator[type.ordinal][it]) }, type)
                }
            }
        }
        return ColumnIteratorRow(mapOf("?success" to ColumnIteratorRepeatValue(1, query.dictionary.createValue(ValueBoolean(true)))))
    }
}
