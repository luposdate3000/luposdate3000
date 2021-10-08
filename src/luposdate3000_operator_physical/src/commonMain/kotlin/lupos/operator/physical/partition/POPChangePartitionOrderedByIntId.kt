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
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPChangePartitionOrderedByIntId public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCountFrom: Int,
    @JvmField public var partitionCountTo: Int,
    @JvmField public var partitionIDFrom: Int,
    @JvmField public var partitionIDTo: Int,
    child: IOPBase
) : APOPParallel(
    query,
    projectedVariables,
    EOperatorIDExt.POPChangePartitionOrderedByIntIdID,
    "POPChangePartitionOrderedByIntId",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY
) {
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPChangePartitionOrderedByIntId.kt:48"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    public override fun changePartitionID(idFrom: Int, idTo: Int) {
        if (partitionIDFrom == idFrom) {
            partitionIDFrom = idTo
        } else {
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPChangePartitionOrderedByIntId.kt:55"/*SOURCE_FILE_END*/ }, { partitionIDTo == idFrom })
            partitionIDTo = idTo
        }
    }

    override fun getPartitionCount(variable: String): Int {
        return if (variable == partitionVariable) {
            partitionCountTo
        } else {
            children[0].getPartitionCount(variable)
        }
    }

    override /*suspend*/ fun toXMLElementRoot(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelper2(partial, true, partition)
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelper2(partial, false, partition)
    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean, partition: PartitionHelper): XMLElement {
        val res = if (partial) {
            val partitionCount = if (partitionCountFrom > partitionCountTo) {
                partitionCountFrom
            } else {
                partitionCountTo
            }
            if (isRoot) {
                if (partitionCountTo > partitionCountFrom) {
// use from id, because it is the smaller one
// add the current partition for the from id as offset to the filter
                    val keys = partition.getKeysFor(uuid, partitionIDFrom, query, partitionCountTo, true, { IntArray(partitionCountTo / partitionCountFrom) { it2 -> it + it2 * partitionCountFrom } }).toList()
                    return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedSendMulti.toXMLElementInternal(partitionIDFrom, partial, isRoot, keys, query.getPartitionedBy(), partitionVariable, partitionCountTo))
                } else {
// because the sender is the smaller one - there must be a partition from id on the stack
                    val key = partition.getKeyFor(uuid, partitionIDFrom, query, partitionCountFrom, true)
                    return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedSendSingle.toXMLElementInternal(partitionIDFrom, partial, isRoot, key, query.getPartitionedBy()))
                }
            } else {
                if (partitionCountTo > partitionCountFrom) {
                    val key = partition.getKeyFor(uuid, partitionIDTo, query, partitionCountTo, false)
                    return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedReceiveSingle.toXMLElementInternal(partitionIDTo, partial, isRoot, key to ""))
                } else {
// inverse case to sendmulti
                    val keys = partition.getKeysFor(uuid, partitionIDTo, query, partitionCountFrom, false, { IntArray(partitionCountFrom / partitionCountTo) { it2 -> it + it2 * partitionCountTo } }).toList()
                    return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedReceiveMultiOrdered.toXMLElementInternal(partitionIDTo, partial, isRoot, keys.map { it to "" }.toMap(), mySortPriority.map { it.variableName }))
                }
            }
        } else {
            super.toXMLElementHelper(partial, false, partition)
        }
        res.addAttribute("uuid", "$uuid")
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        res.addAttribute("partitionVariable", partitionVariable)
        res.addAttribute("partitionCountFrom", "" + partitionCountFrom)
        res.addAttribute("partitionCountTo", "" + partitionCountTo)
        res.addAttribute("partitionIDFrom", "" + partitionIDFrom)
        res.addAttribute("partitionIDTo", "" + partitionIDTo)
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        return res
    }

    override fun cloneOP(): IOPBase = POPChangePartitionOrderedByIntId(query, projectedVariables, partitionVariable, partitionCountFrom, partitionCountTo, partitionIDFrom, partitionIDTo, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPChangePartitionOrderedByIntId && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = EvalChangePartitionOrderedByIntId()
}
