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
package lupos.operator.physical.singleinput.modifiers

import lupos.operator.base.IPOPLimit
import lupos.operator.base.POPLimitHandler
import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPLimit public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val limit: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPLimitID, "POPLimit", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    private val finishHandler = POPLimitHandler()
    override fun getPartitionCount(variable: String): Int {
        if (SanityCheck.enabled) { if (!(children[0].getPartitionCount(variable) == 1)) { throw Exception("SanityCheck failed") } }
        return 1
    }

    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return sparql.substring(0, sparql.length - 1) + " LIMIT " + limit + "}"
        }
        return "{SELECT * {$sparql} LIMIT $limit}"
    }

    override fun equals(other: Any?): Boolean = other is POPLimit && limit == other.limit && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = POPLimit(query, projectedVariables, limit, children[0].cloneOP())
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = EvalLimit(children[0].evaluate(parent), limit, finishHandler)
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = super.toXMLElement(partial, partition).addAttribute("limit", "" + limit)
    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? {
        val tmp = (children[0] as POPBase).toLocalOperatorGraph(parent, onFoundLimit, onFoundSort)
        if (tmp == null) {
            return null
        }
        val res = POPLimit(query, projectedVariables, limit, tmp)
        onFoundLimit(res.finishHandler)
        return res
    }
}
