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

import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.iterator.ColumnIteratorMerge
import lupos.operator.base.iterator.RowIteratorMerge
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.ESortTypeExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.ValueComparatorASC
import lupos.shared.ValueComparatorDESC
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPSort public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val sortBy: Array<AOPVariable>, @JvmField public val sortOrder: Boolean, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPSortID, "POPSort", arrayOf(child), ESortPriorityExt.SORT) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPSort.kt:39"/*SOURCE_FILE_END*/ }, { children[0].getPartitionCount(variable) == 1 })
        return 1
    }

    override fun equals(other: Any?): Boolean = other is POPSort && sortBy.contentEquals(other.sortBy) && sortOrder == other.sortOrder && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = POPSort(query, projectedVariables, sortBy, sortOrder, children[0].cloneOP())
    override fun getRequiredVariableNames(): List<String> = sortBy.map { it.name }
    override fun toSparql(): String {
        val variables = Array(sortBy.size) { sortBy[it].name }
        val child = children[0]
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPSort.kt:49"/*SOURCE_FILE_END*/ }, { child !is POPSort })
        val sparql = child.toSparql()
        var res: String = if (sparql.startsWith("{SELECT ")) {
            sparql.substring(0, sparql.length - 1)
        } else {
            "{SELECT *{$sparql}"
        }
        res += " ORDER BY "
        res += if (sortOrder) {
            "ASC("
        } else {
            "DESC("
        }
        for (v in variables) {
            res += AOPVariable(query, v).toSparql() + " "
        }
        res += ")"
        res += "}"
        return res
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = XMLElement("POPSort")
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        res.addAttribute("uuid", "" + uuid)
        val sortByXML = XMLElement("by")
        res.addContent(sortByXML)
        for (v in sortBy) {
            sortByXML.addContent(XMLElement("variable").addAttribute("name", v.name))
        }
        if (sortOrder) {
            res.addAttribute("order", "ASC")
        } else {
            res.addAttribute("order", "DESC")
        }
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        res.addAttribute("providedSort", getPossibleSortPriorities().toString())
        res.addAttribute("filteredSort", sortPriorities.toString())
        res.addAttribute("selectedSort", mySortPriority.toString())
        res.addContent(childrenToXML(partial))
        return res
    }

    override fun getPossibleSortPriorities(): List<List<SortHelper>> {
        val res = mutableListOf<List<SortHelper>>()
        val requiredVariables = mutableListOf<String>()
        var sortType = ESortTypeExt.ASC
        if (!this.sortOrder) {
            sortType = ESortTypeExt.DESC
        }
        for (v in this.sortBy) {
            requiredVariables.add(v.name)
        }
        val tmp = mutableListOf<SortHelper>()
        for (v in requiredVariables) {
            tmp.add(SortHelper(v, sortType))
        }
        res.add(tmp)
        return res
    }

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val child = children[0].evaluate(parent)
        val variablesOut = getProvidedVariableNames()
        val comparator: Comparator<DictionaryValueType> = if (sortOrder) {
            ValueComparatorASC(query)
        } else {
            ValueComparatorDESC(query)
        }
        when {
            variablesOut.isEmpty() -> {
                return child
            }
            variablesOut.size == 1 -> {
                return if (sortBy.size == 1) {
                    IteratorBundle(mapOf(variablesOut[0] to ColumnIteratorMerge(child.columns[variablesOut[0]]!!, comparator)))
                } else {
                    IteratorBundle(mapOf(variablesOut[0] to ColumnIteratorMerge(child.columns[variablesOut[0]]!!)))
                }
            }
            else -> {
                val columnNamesTmp = mutableListOf<String>()
                for (v in sortBy) {
                    columnNamesTmp.add(v.name)
                }
                for (v in mySortPriority.map { it.variableName }) {
                    if (variablesOut.contains(v)) {
                        if (!columnNamesTmp.contains(v)) {
                            columnNamesTmp.add(v)
                        }
                    }
                }
                for (v in variablesOut) {
                    if (!columnNamesTmp.contains(v)) {
                        columnNamesTmp.add(v)
                    }
                }
                val columnNames = columnNamesTmp.toTypedArray()
                return IteratorBundle(RowIteratorMerge(child.rows, comparator, sortBy.size, columnNames))
            }
        }
    }
    public open override fun usesDictionary(): Boolean {
        return true
    }
}
