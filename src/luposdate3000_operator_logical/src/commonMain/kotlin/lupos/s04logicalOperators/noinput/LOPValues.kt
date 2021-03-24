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
package lupos.s04logicalOperators.noinput

import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import kotlin.jvm.JvmField

public class LOPValues public constructor(query: IQuery, @JvmField public val variables: List<AOPVariable>, values: List<AOPValue>) : LOPBase(query, EOperatorIDExt.LOPValuesID, "LOPValues", ArrayAllocatorESortPriority(values.size) { values[it] }, ESortPriorityExt.PREVENT_ANY) {
    override fun getProvidedVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = XMLElement("LOPValues").addAttribute("uuid", "" + uuid)
        val xmlvariables = XMLElement("LocalVariables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("LocalBindings")
        res.addContent(bindings)
        for (v in variables) {
            xmlvariables.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        }
        bindings.addContent(childrenToXML(partial))
        return res
    }

    override fun equals(other: Any?): Boolean = other is LOPValues && variables == other.variables && children.contentEquals(other.children)
    override fun cloneOP(): IOPBase = LOPValues(query, variables, List(children.size) { children[it].cloneOP() as AOPValue })
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        val p = getProvidedVariableNames()
        for (i in p.indices) {
            val localSet = mutableSetOf<Int>()
            for (row in children) {
                localSet.add((row.getChildren()[i] as AOPConstant).value)
            }
            res.values[p[i]] = localSet.size
        }
        res.count = children.size
        return res
    }
}
