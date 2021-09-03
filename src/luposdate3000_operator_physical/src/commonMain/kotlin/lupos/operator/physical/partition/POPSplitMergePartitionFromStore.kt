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

public class POPSplitMergePartitionFromStore public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    public var partitionID: Int,
    child: IOPBase
) : APOPParallel(
    query,
    projectedVariables,
    EOperatorIDExt.POPSplitMergePartitionFromStoreID,
    "POPSplitMergePartitionFromStore",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY
) {
    private var keys = intArrayOf()
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPSplitMergePartitionFromStore.kt:42"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    public override fun changePartitionID(idFrom: Int, idTo: Int) {
        partitionID = idTo
    }

    override fun getPartitionCount(variable: String): Int = 1

    override /*suspend*/ fun toXMLElementRoot(partial: Boolean, partition: Map<String, Int>): XMLElement = toXMLElementHelper2(partial, true, partition)
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: Map<String, Int>): XMLElement = toXMLElementHelper2(partial, false, partition)
    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean, partition: Map<String, Int>): XMLElement {
        val res = if (partial) {
            if (keys.size != 1) {
                keys = IntArray(1) { query.createPartitionKey() }
            }
            if (isRoot) {
                return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedSendSingle.toXMLElementInternal(partitionID, partial, isRoot, keys[0], query.getPartitionedBy()))
            } else {
                return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedReceiveSingle.toXMLElementInternal(partitionID, partial, isRoot, keys[0] to ""))
            }
        } else {
            super.toXMLElementHelper(partial, false, mapOf())
        }
        res.addAttribute("uuid", "$uuid")
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        res.addAttribute("partitionVariable", "$uuid")
        res.addAttribute("partitionCount", "1")
        res.addAttribute("partitionID", "" + partitionID)
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        return res
    }

    override fun cloneOP(): IOPBase = POPSplitMergePartitionFromStore(query, projectedVariables, partitionID, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPSplitMergePartitionFromStore && children[0] == other.children[0]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        return children[0].evaluate(parent)
    }
}
