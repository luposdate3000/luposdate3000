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
package lupos.operator.logical.multiinput

import lupos.operator.base.multiinput.LOPJoin_Helper
import lupos.operator.logical.LOPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.PartitionHelper
import lupos.shared.XMLElement
import lupos.shared.operator.HistogramResult
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public class LOPJoinTopology public constructor(query: IQuery, childs: Array<IOPBase>) : LOPBase(query, EOperatorIDExt.LOPJoinID, "LOPJoin", childs, ESortPriorityExt.JOIN) {
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = TODO("not implemented")
    override fun equals(other: Any?): Boolean = TODO("not implemented")
    override fun cloneOP(): IOPBase = LOPJoinTopology(query, children.map{it.cloneOP()}.toTypedArray())
    override /*suspend*/ fun calculateHistogram(): HistogramResult = TODO("not implemented")
}
