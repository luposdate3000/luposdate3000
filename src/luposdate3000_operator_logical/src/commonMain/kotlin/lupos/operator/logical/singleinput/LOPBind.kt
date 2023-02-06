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

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.logical.LOPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class LOPBind public constructor(query: IQuery, @JvmField public val name: AOPVariable, expression: AOPBase, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPBindID, "LOPBind", arrayOf(child, expression), ESortPriorityExt.BIND) {
    public constructor(query: IQuery, name: AOPVariable, expression: AOPBase) : this(query, name, expression, OPEmptyRow(query))

    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNames(): MutableList<String> = (children[0].getProvidedVariableNames() + name.name).distinct().toMutableList()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive().distinct()
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = super.toXMLElement(partial, partition).addAttribute("name", name.name)
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

    override fun replaceVariableWithAnother(name: String, name2: String, parent: IOPBase, parentIdx: Int): IOPBase {
        if (SanityCheck.enabled) { if (!(parent.getChildren()[parentIdx] == this)) { throw Exception("SanityCheck failed") } }
        if (this.name.name == name) {
            val exp = this.getChildren()[1]
            if (exp is AOPVariable) {
                this.getChildren()[0].replaceVariableWithAnother(exp.name, this.name.name, this, 0)
                parent.getChildren()[parentIdx] = this.getChildren()[0]
            } else {
                parent.getChildren()[parentIdx] = LOPBind(query, AOPVariable(query, name2), this.getChildren()[1] as AOPBase, this.getChildren()[0])
            }
            return parent.getChildren()[parentIdx].replaceVariableWithAnother(name, name2, parent, parentIdx)
        }
        for (i in this.getChildren().indices) {
            this.getChildren()[i] = this.getChildren()[i].replaceVariableWithAnother(name, name2, this, i)
        }
        return this
    }

    override fun syntaxVerifyAllVariableExistsAutocorrect() {
        for (name in getRequiredVariableNames()) {
            var found = false
            for (prov in getProvidedVariableNames()) {
                if (prov == name) {
                    found = true
                    break
                }
            }
            if (!found) {
                children[1] = children[1].replaceVariableWithUndef(name, false)
            }
        }
    }
}
