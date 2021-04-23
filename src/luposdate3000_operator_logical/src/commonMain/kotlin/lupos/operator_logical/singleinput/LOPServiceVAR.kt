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

import lupos.operator.logical.HistogramResult
import lupos.operator.logical.IQuery
import lupos.operator.logical.LOPBase
import lupos.operator.logical.noinput.OPEmptyRow
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class LOPServiceVAR public constructor(query: IQuery, @JvmField public val name: String, @JvmField public val silent: Boolean, constraint: IOPBase, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPServiceVARID, "LOPServiceVAR", arrayOf(child, constraint), ESortPriorityExt.PREVENT_ANY) {
    public constructor(query: IQuery, name: String, silent: Boolean, constraint: IOPBase) : this(query, name, silent, constraint, OPEmptyRow(query))

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("name", name).addAttribute("silent", "" + silent).addContent(XMLElement("constraint").addContent(children[1].toXMLElement(partial)))
    override fun equals(other: Any?): Boolean = other is LOPServiceVAR && name == other.name && silent == other.silent && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP(): IOPBase = LOPServiceVAR(query, name, silent, children[1].cloneOP(), children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
