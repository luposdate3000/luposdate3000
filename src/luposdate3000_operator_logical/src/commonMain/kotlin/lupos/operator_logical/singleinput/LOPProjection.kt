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
import lupos.operator.logical.HistogramResult
import lupos.operator.logical.IQuery
import lupos.operator.logical.LOPBase
import lupos.operator.logical.noinput.OPEmptyRow
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class LOPProjection public constructor(query: IQuery, @JvmField public val variables: MutableList<AOPVariable>, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPProjectionID, "LOPProjection", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    public constructor(query: IQuery) : this(query, mutableListOf(), OPEmptyRow(query))

    override fun getProvidedVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = super.toXMLElement(partial)
        val vars = XMLElement("LocalVariables")
        res.addContent(vars)
        for (v in variables) {
            vars.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        }
        return res
    }

    override fun equals(other: Any?): Boolean = other is LOPProjection && variables == other.variables && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPProjection(query, variables, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        val childHistogram = children[0].getHistogram()
        for (v in variables) {
            val w = childHistogram.values[v.name]
            if (w == null) {
                res.values[v.name] = 1
            } else {
                res.values[v.name] = w
            }
        }
        res.count = childHistogram.count
        return res
    }

    public override fun replaceVariableWithAnother(name: String, name2: String, parent: IOPBase, parentIdx: Int): IOPBase {
        SanityCheck.check { parent.getChildren()[parentIdx] == this }
        for (i in 0 until this.variables.size) {
            if (this.variables[i].name == name) {
                this.variables[i] = AOPVariable(query, name2)
            }
        }
        for (i in this.getChildren().indices) {
            this.getChildren()[i] = this.getChildren()[i].replaceVariableWithAnother(name, name2, this, i)
        }
        return this
    }
}
