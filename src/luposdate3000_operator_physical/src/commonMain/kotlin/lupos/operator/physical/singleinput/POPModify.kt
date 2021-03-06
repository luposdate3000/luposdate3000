/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.operator.physical.singleinput

import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.operator.logical.noinput.LOPTriple
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EModifyType
import lupos.shared.EModifyTypeExt
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPModify public constructor(query: IQuery, projectedVariables: List<String>, insert: List<LOPTriple>, delete: List<LOPTriple>, child: IOPBase) :
    POPBase(query, projectedVariables, EOperatorIDExt.POPModifyID, "POPModify", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = super.toXMLElement(false)
        val xmlInsert = XMLElement("insert")
        val xmlDelete = XMLElement("delete")
        for ((first, second) in modify) {
            if (second == EModifyTypeExt.INSERT) {
                xmlInsert.addContent(first.toXMLElement(false))
            } else {
                xmlDelete.addContent(first.toXMLElement(false))
            }
        }
        res.addContent(xmlInsert)
        res.addContent(xmlDelete)
        return res
    }

    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPModify.kt:61"/*SOURCE_FILE_END*/ }, { children[0].getPartitionCount(variable) == 1 })
        return 1
    }

    @JvmField
    public val modify: Array<Pair<LOPTriple, EModifyType>> = Array(insert.size + delete.size) {
        if (it < insert.size) {
            Pair(insert[it], EModifyTypeExt.INSERT)
        } else {
            Pair(delete[it - insert.size], EModifyTypeExt.DELETE)
        }
    }

    override fun equals(other: Any?): Boolean = other is POPModify && modify.contentEquals(other.modify) && children[0] == other.children[0]
    override fun toSparql(): String {
        val res = StringBuilder()
        val insertions = StringBuilder()
        val deletions = StringBuilder()
        for ((first, second) in modify) {
            if (second == EModifyTypeExt.INSERT) {
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
            if (second == EModifyTypeExt.INSERT) {
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
        val row = DictionaryValueTypeArray(variables.size) { DictionaryValueHelper.undefValue }
        val data = mutableMapOf<String, Array<Array<MutableList<DictionaryValueType>>>>()
        val buffer = ByteArrayWrapper()
        loop@ while (true) {
            if (variables.isNotEmpty()) {
                for (columnIndex in variables.indices) {
                    val value = columns[columnIndex].next()
                    if (value == DictionaryValueHelper.nullValue) {
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPModify.kt:148"/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
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
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPModify.kt:162"/*SOURCE_FILE_END*/ }, { variables.contains(first.graph) })
                    while (variables[graphVarIdx] != first.graph) {
                        graphVarIdx++
                    }
                }
                val graphName: String = if (first.graphVar) {
                    query.getDictionary().getValue(buffer, row[graphVarIdx])
                    DictionaryHelper.byteArrayToSparql(buffer)
                } else {
                    first.graph
                }
                if (data[graphName] == null) {
                    data[graphName] = Array(EModifyTypeExt.values_size) { Array(3) { mutableListOf() } }
                }
                val target = data[graphName]!![second]
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
            for (closeIndex in child.columns.values) {
                closeIndex.close()
            }
        } else {
            child.hasNext2Close()
        }
        for ((graphName, iterator) in data) {
            val dict = query.getDictionary()
            val store = query.getInstance().tripleStoreManager!!.getGraph(graphName)
            for (type in 0 until EModifyTypeExt.values_size) {
                if (iterator[type][0].size > 0) {
                    val cache = store.modify_create_cache(query, EModifyTypeExt.INSERT)
                    val iterator = Array(3) { ColumnIteratorMultiValue(iterator[type][it]) }
                    while (true) {
                        val s = dict.valueToGlobal(iterator[0].next())
                        val p = dict.valueToGlobal(iterator[1].next())
                        val o = dict.valueToGlobal(iterator[2].next())
                        if (s == DictionaryValueHelper.nullValue) {
                            break
                        }
                        cache.writeRow(s, p, o, query)
                    }
                    cache.close()
                }
            }
        }
        return IteratorBundle(mapOf("?success" to ColumnIteratorRepeatValue(1, DictionaryValueHelper.booleanTrueValue)))
    }
    public open override fun usesDictionary(): Boolean {
        return true
    }
}
