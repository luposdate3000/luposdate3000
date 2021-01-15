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
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPTriple
import kotlin.jvm.JvmField
public class LOPModify public constructor(
    query: IQuery,
    @JvmField public val insert: MutableList<LOPTriple> = mutableListOf(),
    @JvmField public val delete: MutableList<LOPTriple> = mutableListOf(),
    child: IOPBase
) : LOPBase(query, EOperatorIDExt.LOPModifyID, "LOPModify", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    override fun getProvidedVariableNames(): MutableList<String> = mutableListOf("?boolean")
    override /*suspend*/ fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val xmlI = XMLElement("insert")
        res.addContent(xmlI)
        for (e in insert) {
            xmlI.addContent(e.toXMLElement())
        }
        val xmlD = XMLElement("delete")
        res.addContent(xmlD)
        for (e in delete) {
            xmlD.addContent(e.toXMLElement())
        }
        return res
    }
    override fun equals(other: Any?): Boolean = other is LOPModify && insert == other.insert && delete == other.delete && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPModify(query, insert, delete, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        res.values["?boolean"] = 1
        res.count = 1
        return res
    }
}
