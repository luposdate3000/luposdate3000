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
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import kotlin.jvm.JvmField
public class LOPBind public constructor(query: IQuery, @JvmField public val name: AOPVariable, expression: AOPBase, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPBindID, "LOPBind", arrayOf(child, expression), ESortPriorityExt.BIND) {
    public constructor(query: IQuery, name: AOPVariable, expression: AOPBase) : this(query, name, expression, OPEmptyRow(query))
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNames(): MutableList<String> = (children[0].getProvidedVariableNames() + name.name).distinct().toMutableList()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive().distinct()
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("name", name.name)
    override fun equals(other: Any?): Boolean = other is LOPBind && name == other.name && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = LOPBind(query, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        val childHistogram = children[0].getHistogram()
        var distinct = 1
        val requiredVariables = getRequiredVariableNames()
        for ((k, v) in childHistogram.values) {
            res.values[k] = v
            if (requiredVariables.contains(k)) {
                distinct *= v
            }
        }
        res.count = childHistogram.count
        if (distinct > res.count) {
            distinct = res.count
        }
        res.values[name.name] = distinct
        return res
    }
}
