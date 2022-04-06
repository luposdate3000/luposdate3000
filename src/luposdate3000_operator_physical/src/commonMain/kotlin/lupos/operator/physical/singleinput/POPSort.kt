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
import lupos.operator.base.IPOPLimit
import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.ESortTypeExt
import lupos.shared.OperationCanNotBeLocalException
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPSort public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val sortBy: Array<AOPVariable>, @JvmField public val sortOrder: Boolean, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPSortID, "POPSort", arrayOf(child), ESortPriorityExt.SORT) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPSort.kt:37"/*SOURCE_FILE_END*/ }, { children[0].getPartitionCount(variable) == 1 })
        return 1
    }

    override fun equals(other: Any?): Boolean = other is POPSort && sortBy.contentEquals(other.sortBy) && sortOrder == other.sortOrder && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = POPSort(query, projectedVariables, sortBy, sortOrder, children[0].cloneOP())
    override fun getRequiredVariableNames(): List<String> = sortBy.map { it.name }
    override fun toSparql(): String {
        val variables = Array(sortBy.size) { sortBy[it].name }
        val child = children[0]
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPSort.kt:47"/*SOURCE_FILE_END*/ }, { child !is POPSort })
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

    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement {
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
        res.addContent(childrenToXML(partial, partition))
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
        if (getProvidedVariableNames().size == 0) {
            return children[0].evaluate(parent)
        } else {
            return EvalSort(
                children[0].evaluate(parent),
                mySortPriority.map { it.variableName },
                query,
                sortBy.map { it.name }.toTypedArray(),
                getProvidedVariableNames(),
                sortOrder
            )
        }
    }

    override fun usesDictionary(): Boolean {
        return true
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? = throw OperationCanNotBeLocalException()
}
