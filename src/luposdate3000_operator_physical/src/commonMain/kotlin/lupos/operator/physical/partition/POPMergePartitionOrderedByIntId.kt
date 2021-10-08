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
public class POPMergePartitionOrderedByIntId public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount2: Int,
    @JvmField public var partitionID: Int,
    child: IOPBase
) : APOPParallel(
    query,
    projectedVariables,
    EOperatorIDExt.POPMergePartitionOrderedByIntIdID,
    "POPMergePartitionOrderedByIntId",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY
) {
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPMergePartitionOrderedByIntId.kt:45"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    public override fun changePartitionID(idFrom: Int, idTo: Int) {
        partitionID = idTo
    }

    override fun getPartitionCount(variable: String): Int {
        return if (variable == partitionVariable) {
            1
        } else {
            children[0].getPartitionCount(variable)
        }
    }

    override /*suspend*/ fun toXMLElementRoot(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelper2(partial, true, partition)
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelper2(partial, false, partition)
    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean, partition: PartitionHelper): XMLElement {
        val res = if (partial) {
            if (isRoot) {
                val key = partition.getKeyFor(uuid, partitionID, query, partitionCount2, true)
                return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedSendSingle.toXMLElementInternal(partitionID, partial, isRoot, key, query.getPartitionedBy()))
            } else {
                if (partitionCount2 > 1) {
                    val keys = partition.getKeysFor(uuid, partitionID, query, partitionCount2, false)
                    return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedReceiveMultiOrdered.toXMLElementInternal(partitionID, partial, isRoot, keys.map { it to "" }.toMap(), mySortPriority.map { it.variableName }))
                } else {
                    val key = partition.getKeyFor(uuid, partitionID, query, partitionCount2, false)
                    return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedReceiveSingle.toXMLElementInternal(partitionID, partial, isRoot, key to ""))
                }
            }
        } else {
            super.toXMLElementHelper(partial, false, partition)
        }
        res.addAttribute("uuid", "$uuid")
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        res.addAttribute("partitionVariable", partitionVariable)
        res.addAttribute("partitionCount", "" + partitionCount2)
        res.addAttribute("partitionID", "" + partitionID)
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        return res
    }

    override fun cloneOP(): IOPBase = POPMergePartitionOrderedByIntId(query, projectedVariables, partitionVariable, partitionCount2, partitionID, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPMergePartitionOrderedByIntId && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = EvalMergePartitionOrderedByIntId()
}
