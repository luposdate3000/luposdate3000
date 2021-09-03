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

import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.logical.LOPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.XMLElement
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class LOPOffset public constructor(query: IQuery, @JvmField public val offset: Int, child: IOPBase) : LOPBase(query, EOperatorIDExt.LOPOffsetID, "LOPOffset", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    public constructor(query: IQuery, offset: Int) : this(query, offset, OPEmptyRow(query))

    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: Map<String, Int>): XMLElement = super.toXMLElement(partial, partition).addAttribute("offset", "" + offset)
    override fun equals(other: Any?): Boolean = other is LOPOffset && offset == other.offset && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPOffset(query, offset, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        val childHistogram = children[0].getHistogram()
        res.count = childHistogram.count - offset
        if (res.count < 0) {
            res.count = 0
            for (k in childHistogram.values.keys) {
                res.values[k] = 0
            }
        } else {
            for ((k, v) in childHistogram.values) {
                if (v > childHistogram.count - offset) {
                    res.values[k] = childHistogram.count - offset
                } else {
                    res.values[k] = v
                }
            }
        }
        return res
    }
}
