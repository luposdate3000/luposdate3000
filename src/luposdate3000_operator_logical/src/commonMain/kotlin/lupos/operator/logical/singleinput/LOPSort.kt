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
package lupos.operator.logical.singleinput

import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.logical.LOPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.ESortTypeExt
import lupos.shared.IQuery
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.XMLElement
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class LOPSort public constructor(query: IQuery, @JvmField public val asc: Boolean, @JvmField public var by: AOPVariable, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPSortID, "LOPSort", arrayOf(child), ESortPriorityExt.SORT) {
    public constructor(query: IQuery, asc: Boolean, by: AOPVariable) : this(query, asc, by, OPEmptyRow(query))

    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement {
        val res = XMLElement("LOPSort")
        res.addAttribute("by", by.name)
        if (asc) {
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

    override fun getRequiredVariableNames(): List<String> = listOf(by.name)
    override fun equals(other: Any?): Boolean = other is LOPSort && asc == other.asc && by == other.by && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPSort(query, asc, by, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }

    override fun getPossibleSortPriorities(): List<List<SortHelper>> {
        val res = mutableListOf<List<SortHelper>>()
        val requiredVariables = mutableListOf<String>()
        var sortType = ESortTypeExt.ASC
        if (!this.asc) {
            sortType = ESortTypeExt.DESC
        }
        requiredVariables.add(this.by.name)
        val tmp = mutableListOf<SortHelper>()
        for (v in requiredVariables) {
            tmp.add(SortHelper(v, sortType))
        }
        res.add(tmp)
        return res
    }

    override fun replaceVariableWithAnother(name: String, name2: String, parent: IOPBase, parentIdx: Int): IOPBase {
        if (SanityCheck.enabled) { if (!(parent.getChildren()[parentIdx] == this)) { throw Exception("SanityCheck failed") } }
        if (this.by.name == name) {
            this.by = AOPVariable(query, name2)
        }
        for (i in this.getChildren().indices) {
            this.getChildren()[i] = this.getChildren()[i].replaceVariableWithAnother(name, name2, this, i)
        }
        return this
    }
}
