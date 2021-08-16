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
package lupos.operator.physical.partition

import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPSplitPartitionPassThrough public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount: Int,
    @JvmField public var partitionID: Int,
    child: IOPBase
) : APOPParallel(
    query,
    projectedVariables,
    EOperatorIDExt.POPSplitPartitionPassThroughID,
    "POPSplitPartitionPassThrough",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY
) {
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPSplitPartitionPassThrough.kt:44"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    public override fun changePartitionID(idFrom: Int, idTo: Int) {
        changePartitionID(idFrom, idTo)
    }

    override fun getPartitionCount(variable: String): Int {
        return if (variable == partitionVariable) {
            partitionCount
        } else {
            children[0].getPartitionCount(variable)
        }
    }

    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement {
        return toXMLElementHelper2(partial, true)
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        return toXMLElementHelper2(partial, false)
    }

    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean): XMLElement {
        val res = if (partial) {
            if (isRoot) {
                XMLElement("POPDistributedSendSingle").addContent(childrenToXML(partial))
            } else {
                XMLElement("POPDistributedReceiveSingle")
            }
        } else {
            super.toXMLElementHelper(partial, partial && !isRoot)
        }
        res.addAttribute("keyPrefix", "$uuid")
        res.addAttribute("uuid", "$uuid")
        val theKey = mutableMapOf(partitionVariable to 0)
        theKey.putAll(query.getDistributionKey())
        if (isRoot) {
            res.addContent(XMLElement("partitionDistributionKey").addAttribute("key", theKeyToString(theKey)))
        } else {
            res.addContent(XMLElement("partitionDistributionKey").addAttribute("key", theKeyToString(theKey)))
        }
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        res.addAttribute("partitionVariable", partitionVariable)
        res.addAttribute("partitionCount", "" + partitionCount)
        res.addAttribute("partitionID", "" + partitionID)
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        return res
    }

    override fun cloneOP(): IOPBase = POPSplitPartitionPassThrough(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPSplitPartitionPassThrough && children[0] == other.children[0] && partitionVariable == other.partitionVariable && partitionCount == other.partitionCount
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val partitionCount = parent.limit[partitionVariable]!!
        return if (partitionCount == 1) {
            children[0].evaluate(parent)
        } else {
            val childPartition = Partition(parent, partitionVariable)
            children[0].evaluate(childPartition)
        }
    }
}
