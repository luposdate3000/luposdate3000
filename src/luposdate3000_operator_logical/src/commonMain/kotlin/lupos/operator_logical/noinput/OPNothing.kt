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
package lupos.operator.logical.noinput

import lupos.operator.logical.HistogramResult
import lupos.operator.logical.IQuery
import lupos.operator.logical.LOPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class OPNothing public constructor(query: IQuery, @JvmField public val myProvidedVariableNames: List<String>) : LOPBase(query, EOperatorIDExt.OPNothingID, "OPNothing", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
    override fun getProvidedVariableNames(): List<String> = myProvidedVariableNames
    override fun toSparql(): String = "{}"
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = super.toXMLElement(partial)
        for (v in myProvidedVariableNames) {
            res.addContent(XMLElement("v").addContent(v))
        }
        return res
    }

    override fun equals(other: Any?): Boolean = other is OPNothing
    override fun cloneOP(): IOPBase = this
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        res.count = 0
        for (v in myProvidedVariableNames) {
            res.values[v] = 0
        }
        return res
    }
}
