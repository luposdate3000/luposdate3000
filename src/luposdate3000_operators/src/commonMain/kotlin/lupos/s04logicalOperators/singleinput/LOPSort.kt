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

package lupos.s04logicalOperators.singleinput
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import kotlin.jvm.JvmField
public class LOPSort public constructor(query: IQuery, @JvmField public val asc: Boolean, @JvmField public var by: AOPVariable, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPSortID, "LOPSort", arrayOf(child), ESortPriorityExt.SORT) {
    public constructor(query: IQuery, asc: Boolean, by: AOPVariable) : this(query, asc, by, OPEmptyRow(query))
    override /*suspend*/ fun toXMLElement(): XMLElement {
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
        res.addContent(childrenToXML())
        return res
    }
    override fun getRequiredVariableNames(): List<String> = listOf(by.name)
    override fun equals(other: Any?): Boolean = other is LOPSort && asc == other.asc && by == other.by && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPSort(query, asc, by, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
