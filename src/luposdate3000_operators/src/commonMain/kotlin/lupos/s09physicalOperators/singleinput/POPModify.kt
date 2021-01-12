package lupos.s09physicalOperators.singleinput
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.distributedTripleStore
import kotlin.jvm.JvmField
public class POPModify public constructor(query: IQuery, projectedVariables: List<String>, insert: List<LOPTriple>, delete: List<LOPTriple>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPModifyID, "POPModify", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check { children[0].getPartitionCount(variable) == 1 }
        return 1
    }
    @JvmField
    public val modify: Array<Pair<LOPTriple, EModifyType>> = Array(insert.size + delete.size) {
        if (it < insert.size) {
            Pair(insert[it], EModifyType.INSERT)
        } else {
            Pair(delete[it - insert.size], EModifyType.DELETE)
        }
    }
    override fun equals(other: Any?): Boolean = other is POPModify && modify.contentEquals(other.modify) && children[0] == other.children[0]
    override fun toSparql(): String {
        val res = StringBuilder()
        val insertions = StringBuilder()
        val deletions = StringBuilder()
        for ((first, second) in modify) {
            if (second == EModifyType.INSERT) {
                insertions.append(first.toSparql() + ".")
            } else {
                deletions.append(first.toSparql() + ".")
            }
        }
        val istring = insertions.toString()
        val dstring = deletions.toString()
        if (istring.isNotEmpty()) {
            res.append("INSERT{")
            res.append(istring)
            res.append("}")
        }
        if (dstring.isNotEmpty()) {
            res.append("DELETE{")
            res.append(dstring)
            res.append("}")
        }
        res.append("WHERE{")
        res.append(children[0].toSparql())
        res.append("}")
        return res.toString()
    }
    override fun toSparqlQuery(): String = toSparql()
    override fun getProvidedVariableNames(): List<String> = listOf("?success")
    override fun getProvidedVariableNamesInternal(): List<String> = children[0].getProvidedVariableNames()
    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for ((first) in modify) {
            if (first.graphVar) {
                res.add(first.graph)
            }
            for (i in 0 until 3) {
                val tmp = first.children[i]
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
        for ((first, second) in modify) {
            if (second == EModifyType.INSERT) {
                insert.add(first)
            } else {
                delete.add(first)
            }
        }
        return POPModify(query, projectedVariables, insert, delete, children[0].cloneOP())
    }
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = children[0].getProvidedVariableNames()
        val child = children[0].evaluate(parent)
        val columns = Array(variables.size) { child.columns[variables[it]]!! }
        val row = IntArray(variables.size) { ResultSetDictionaryExt.undefValue }
        val data = mutableMapOf<String, Array<Array<MutableList<Int>>>>()
        loop@ while (true) {
            if (variables.isNotEmpty()) {
                for (columnIndex in variables.indices) {
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
            for ((first, second) in modify) {
                var graphVarIdx = 0
                if (first.graphVar) {
                    SanityCheck.check { variables.contains(first.graph) }
                    while (variables[graphVarIdx] != first.graph) {
                        graphVarIdx++
                    }
                }
                val graphName: String = if (first.graphVar) {
                    query.getDictionary().getValue(row[graphVarIdx]).valueToString()!!
                } else {
                    first.graph
                }
                if (data[graphName] == null) {
                    data[graphName] = Array(EModifyType.values().size) { Array(3) { mutableListOf() } }
                }
                val target = data[graphName]!![second.ordinal]
                loop2@ for (columnIndex in 0 until 3) {
                    val tmp = first.children[columnIndex]
                    if (tmp is AOPConstant) {
                        target[columnIndex].add(tmp.value)
                    } else {
                        for (columnIndex2 in variables.indices) {
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
        if (row.isNotEmpty()) {
            for ((n, closeIndex) in child.columns) {
                closeIndex.close()
            }
        } else {
            child.hasNext2Close()
        }
        for ((graphName, iterator) in data) {
            val store = distributedTripleStore.getNamedGraph(query, graphName)
            for (type in EModifyType.values()) {
                if (iterator[type.ordinal][0].size > 0) {
                    store.modify(Array(3) { ColumnIteratorMultiValue(iterator[type.ordinal][it]) }, type)
                }
            }
        }
        return IteratorBundle(mapOf("?success" to ColumnIteratorRepeatValue(1, query.getDictionary().createValue(ValueBoolean(true)))))
    }
}
