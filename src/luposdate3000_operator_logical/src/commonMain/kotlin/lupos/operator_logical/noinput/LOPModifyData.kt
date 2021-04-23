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
package lupos.operator_logical.noinput

import lupos.operator_logical.HistogramResult
import lupos.operator_logical.IOPBase
import lupos.operator_logical.IQuery
import lupos.operator_logical.LOPBase
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.XMLElement
import kotlin.jvm.JvmField

public class LOPModifyData public constructor(query: IQuery, @JvmField public val type: EModifyType, @JvmField public val data: MutableList<LOPTriple>) : LOPBase(query, EOperatorIDExt.LOPModifyDataID, "LOPModifyData", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
    public constructor(query: IQuery, type: EModifyType) : this(query, type, mutableListOf())

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = XMLElement("LOPModifyData")
        res.addAttribute("type", "" + type)
        for (t in data) {
            res.addContent(t.toXMLElement(partial))
        }
        return res
    }

    override fun equals(other: Any?): Boolean = other is LOPModifyData && type == other.type && data == other.data
    override fun cloneOP(): IOPBase = LOPModifyData(query, type, data)
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        res.count = 1
        return res
    }
}
