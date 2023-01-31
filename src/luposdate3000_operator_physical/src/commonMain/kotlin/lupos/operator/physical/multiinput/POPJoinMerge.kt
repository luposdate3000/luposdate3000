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
package lupos.operator.physical.multiinput

import lupos.operator.base.IPOPLimit
import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.VariableNotDefinedSyntaxException
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPJoinMerge public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    childA: IOPBase,
    childB: IOPBase,
    @JvmField public val optional: Boolean,
    @JvmField public val joinVariableOrder: List<String>,
) : POPBase(query, projectedVariables, EOperatorIDExt.POPJoinMergeID, "POPJoinMerge", arrayOf(childA, childB), ESortPriorityExt.JOIN) {
    override fun getPartitionCount(variable: String): Int {
        return if (children[0].getProvidedVariableNames().contains(variable)) {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge.kt:43"/*SOURCE_FILE_END*/ }, { children[0].getPartitionCount(variable) == children[1].getPartitionCount(variable) })
                children[0].getPartitionCount(variable)
            } else {
                children[0].getPartitionCount(variable)
            }
        } else {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                children[1].getPartitionCount(variable)
            } else {
                throw VariableNotDefinedSyntaxException(classname, variable)
            }
        }
    }

    override fun toSparql(): String = children[0].toSparql() + children[1].toSparql()
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = super.toXMLElement(partial, partition).addAttribute("optional", "" + optional).addAttribute("joinVariableOrder", joinVariableOrder.joinToString("?"))
    override fun cloneOP(): IOPBase = POPJoinMerge(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional, joinVariableOrder)
    override fun equals(other: Any?): Boolean = other is POPJoinMerge && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = EvalJoinMerge(query, children[0].evaluate(parent), children[1].evaluate(parent), projectedVariables, joinVariableOrder,)
    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? {
        val tmp1 = (children[0] as POPBase).toLocalOperatorGraph(parent, onFoundLimit, onFoundSort)
        if (tmp1 == null) {
            return null
        }
        val tmp2 = (children[1] as POPBase).toLocalOperatorGraph(parent, onFoundLimit, onFoundSort)
        if (tmp2 == null) {
            return null
        }
        return POPJoinMerge(query, projectedVariables, tmp1, tmp2, optional, joinVariableOrder)
    }
}
