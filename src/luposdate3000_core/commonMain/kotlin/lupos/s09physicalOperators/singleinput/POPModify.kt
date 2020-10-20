package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt

import lupos.s03resultRepresentation.ValueBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class POPModify(query: Query, projectedVariables: List<String>, insert: List<LOPTriple>, delete: List<LOPTriple>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPModifyID, "POPModify", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check { children[0].getPartitionCount(variable) == 1 }
        return 1
    }

    @JvmField
    val modify = Array<Pair<LOPTriple, EModifyType>>(insert.size + delete.size) {
        if (it < insert.size) {
            Pair(insert[it], EModifyType.INSERT)
        } else {
            Pair(delete[it - insert.size], EModifyType.DELETE)
        }
    }

    override fun equals(other: Any?) = other is POPModify && modify.contentEquals(other.modify) && children[0] == other.children[0]
    override fun toSparql(): String {
        var res = StringBuilder()
        var insertions = StringBuilder()
        var deletions = StringBuilder()
        for (m in modify) {
            if (m.second == EModifyType.INSERT) {
                insertions.append(m.first.toSparql() + ".")
            } else {
                deletions.append(m.first.toSparql() + ".")
            }
        }
        var istring = insertions.toString()
        var dstring = deletions.toString()
        if (istring.length > 0) {
            res.append("INSERT{")
            res.append(istring)
            res.append("}")
        }
        if (dstring.length > 0) {
            res.append("DELETE{")
            res.append(dstring)
            res.append("}")
        }
        res.append("WHERE{")
        res.append(children[0].toSparql())
        res.append("}")
        return res.toString()
    }

    override fun toSparqlQuery() = toSparql()
    override fun getProvidedVariableNames() = listOf<String>("?success")
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
        return res.intersect(children[0].getProvidedVariableNames()).distinct()
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

    override suspend fun evaluate(parent: Partition): IteratorBundle {
        val variables = children[0].getProvidedVariableNames()
        val child = children[0].evaluate(parent)
        val columns = Array(variables.size) { child.columns[variables[it]]!! }
        val row = IntArray(variables.size) { ResultSetDictionaryExt.undefValue }
        val data = mutableMapOf<String, Array<Array<MutableList<Int>>>>()
        loop@ while (true) {
            if (variables.size > 0) {
                for (columnIndex in 0 until variables.size) {
                    val value = columns[columnIndex].next()
                    if (value == ResultSetDictionaryExt.nullValue) {
                        SanityCheck.check { columnIndex == 0 }
                        break@loop
                    }
                    row[columnIndex] = value
                }
            } else {
                if (!child.hasNext2()) {
                    child.hasNext2Close()
                    break@loop
                }
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
                    data[graphName] = Array(EModifyType.values().size) { Array(3) { mutableListOf<Int>() } }
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
                        SanityCheck.checkUnreachable()
                    }
                }
            }
        }
        if (row.size > 0) {
            for ((n, closeIndex) in child.columns) {
                closeIndex.close()
            }
        } else {
            child.hasNext2Close()
        }
        for ((graphName, iterator) in data) {
            val store = DistributedTripleStore.getNamedGraph(query, graphName)
            for (type in EModifyType.values()) {
                if (iterator[type.ordinal][0].size > 0) {
                    store.modify(Array(3) { ColumnIteratorMultiValue(iterator[type.ordinal][it]) }, type)
                }
            }
        }
        return IteratorBundle(mapOf("?success" to ColumnIteratorRepeatValue(1, query.dictionary.createValue(ValueBoolean(true)))))
    }
}
