package lupos.s09physicalOperators.singleinput

import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
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
            println("POPModify loop ${variables.map { it }}")
            for (columnIndex in 0 until variables.size) {
                val value = columns[columnIndex].next()
                if (value == null) {
                    SanityCheck.check { columnIndex == 0 }
                    break@loop
                }
                row[columnIndex] = value
            }
            println("POPModify accessing row :: ${row.map { it }}")
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
            println("POPModify is accessing graph $graphName")
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
