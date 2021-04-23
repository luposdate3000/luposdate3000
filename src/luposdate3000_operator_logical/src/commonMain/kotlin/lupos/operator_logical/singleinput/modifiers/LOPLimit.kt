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
package lupos.operator.logical.singleinput.modifiers

import lupos.operator.logical.HistogramResult
import lupos.operator.logical.IQuery
import lupos.operator.logical.LOPBase
import lupos.operator.logical.noinput.OPEmptyRow
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class LOPLimit public constructor(query: IQuery, @JvmField public val limit: Int, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPLimitID, "LOPLimit", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    public constructor(query: IQuery, limit: Int) : this(query, limit, OPEmptyRow(query))

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("limit", "" + limit)
    override fun equals(other: Any?): Boolean = other is LOPLimit && limit == other.limit && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPLimit(query, limit, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        val childHistogram = children[0].getHistogram()
        res.count = childHistogram.count
        if (res.count > limit) {
            res.count = limit
            val scale = limit.toDouble() / res.count.toDouble()
            for ((k, v) in childHistogram.values) {
                res.values[k] = (v.toDouble() * scale).toInt()
            }
        } else {
            for ((k, v) in childHistogram.values) {
                res.values[k] = v
            }
        }
        return res
    }
}
