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

import lupos.operator.base.IPOPLimit
import lupos.operator.physical.POPBase
import lupos.operator.physical.multiinput.POPUnion
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.inline.ParallelThread
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPMergePartitionCount public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount: Int,
    @JvmField public var partitionID: Int,
    child: IOPBase
) : APOPParallel(
    query,
    projectedVariables,
    EOperatorIDExt.POPMergePartitionCountID,
    "POPMergePartitionCount",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY
) {
    override fun changePartitionID(idFrom: Int, idTo: Int) {
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
                val key = partition.getKeyFor(uuid, partitionID, query, partitionCount, true)
                return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedSendSingleCount.toXMLElementInternal(partitionID, partial, isRoot, key, query.getPartitionedBy()))
            } else {
                if (partitionCount > 1) {
                    val keys = partition.getKeysFor(uuid, partitionID, query, partitionCount, false)
                    return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedReceiveMultiCount.toXMLElementInternal(partitionID, partial, isRoot, keys.map { it to "" }.toMap()))
                } else {
                    val key = partition.getKeyFor(uuid, partitionID, query, partitionCount, false)
                    return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedReceiveSingleCount.toXMLElementInternal(partitionID, partial, isRoot, key to ""))
                }
            }
        } else {
            super.toXMLElementHelper(partial, false, partition)
        }
        res.addAttribute("uuid", "$uuid")
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

    override fun cloneOP(): IOPBase = POPMergePartitionCount(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPMergePartitionCount && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        if (partitionCount == 1) {
            // single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            val variables = getProvidedVariableNames()
            val variables0 = children[0].getProvidedVariableNames()
            if (SanityCheck.enabled) { if (!(variables0.containsAll(variables))) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(variables.containsAll(variables0))) { throw Exception("SanityCheck failed") } }
            // partitionVariable as any other variable is not included in the result of the child operator
            val ringbufferReadHead = IntArray(partitionCount) { 0 } // owned by read-thread - no locking required - available count is the difference between "ringbufferReadHead" and "ringbufferWriteHead"
            val ringbufferWriteHead = IntArray(partitionCount) { 0 } // owned by write thread - no locking required
            val writerFinished = IntArray(partitionCount) { 0 } // writer changes to 1 if finished
            var readerFinished = 0
            for (p in 0 until partitionCount) {
                ParallelThread.launch {
                    val child = children[0].evaluate(Partition(parent, partitionVariable, p, partitionCount))
                    loop@ while (readerFinished == 0) {
                        val tmp = child.hasNext2()
                        if (tmp) {
                            ringbufferWriteHead[p]++
                        } else {
                            break@loop
                        }
                    }
                    writerFinished[p] = 1
                    child.hasNext2Close()
                }
            }
            return object : IteratorBundle(0) {
                override /*suspend*/ fun hasNext2(): Boolean {
                    var res = false
                    loop@ while (true) {
                        var finishedWriters = 0
                        for (p in 0 until partitionCount) {
                            if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                                // non empty queue -> read one row
                                res = true
                                ringbufferReadHead[p]++
                                break@loop
                            } else if (writerFinished[p] == 1) {
                                finishedWriters++
                            }
                        }
                        if (finishedWriters == partitionCount) {
                            // done
                            break@loop
                        }
                        ParallelThread.delay(1)
                    }
                    return res
                }

                override /*suspend*/ fun hasNext2Close() {
                    readerFinished = 1
                }
            }
        }
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? {
        var res: POPBase? = null
        for (p in 0 until partitionCount) {
            val tmp = (children[0] as POPBase).toLocalOperatorGraph(Partition(parent, partitionVariable, p, partitionCount), onFoundLimit, onFoundSort)
            if (tmp != null) {
                if (res == null) {
                    res = tmp as POPBase
                } else {
                    res = POPUnion(query, projectedVariables, res, tmp)
                }
            }
        }
        return res
    }
}
