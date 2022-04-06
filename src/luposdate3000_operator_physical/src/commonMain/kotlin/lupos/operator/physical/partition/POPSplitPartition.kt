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
import lupos.shared.myPrintStackTrace

import lupos.operator.base.IPOPLimit
import lupos.operator.base.OPBase
import lupos.operator.base.PartitionHelper2
import lupos.operator.base.Query
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Parallel
import lupos.shared.ParallelCondition
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

public class POPSplitPartition public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String?,
    @JvmField public var partitionCount: Int,
    @JvmField public var partitionID: Int,
    child: IOPBase
) : APOPParallel(
    query,
    projectedVariables,
    EOperatorIDExt.POPSplitPartitionID,
    "POPSplitPartition",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY
) {
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPSplitPartition.kt:56"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    override fun changePartitionID(idFrom: Int, idTo: Int) {
        partitionID = idTo
    }

    override fun getPartitionCount(variable: String): Int {
        return if (variable == partitionVariable) {
            partitionCount
        } else {
            children[0].getPartitionCount(variable)
        }
    }

    override /*suspend*/ fun toXMLElementRoot(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelper2(partial, true, partition)
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelper2(partial, false, partition)
    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean, partition: PartitionHelper): XMLElement {
        val res = if (partial) {
            if (isRoot) {
                if (partitionCount > 1) {
                    val keys = partition.getKeysFor(uuid, partitionID, query, partitionCount, true)
                    return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedSendMulti.toXMLElementInternal(partitionID, partial, isRoot, keys.toList(), query.getPartitionedBy(), partitionVariable!!, partitionCount))
                } else {
                    val key = partition.getKeyFor(uuid, partitionID, query, partitionCount, true)
                    return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedSendSingle.toXMLElementInternal(partitionID, partial, isRoot, key, query.getPartitionedBy()))
                }
            } else {
                val key = partition.getKeyFor(uuid, partitionID, query, partitionCount, false)
                return toXMLElementHelperAddBase(partition, partial, isRoot, POPDistributedReceiveSingle.toXMLElementInternal(partitionID, partial, isRoot, key to ""))
            }
        } else {
            super.toXMLElementHelper(partial, false, partition)
        }
        res.addAttribute("uuid", "$uuid")
        res.addAttribute("providedVariables", getProvidedVariableNames().toString())
        if (partitionVariable != null) {
            res.addAttribute("partitionVariable", partitionVariable)
        }
        res.addAttribute("partitionCount", "" + partitionCount)
        res.addAttribute("partitionID", "" + partitionID)
        val projectedXML = XMLElement("projectedVariables")
        res.addContent(projectedXML)
        for (variable in projectedVariables) {
            projectedXML.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        return res
    }

    override fun cloneOP(): IOPBase = POPSplitPartition(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPSplitPartition && children[0] == other.children[0] && partitionVariable == other.partitionVariable && partitionCount == other.partitionCount
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
// throw BugException("POPSplitPartition","child is not launching, because coroutine is missing suspension point")
        if (partitionCount == 1) {
            // single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            var iterators: Array<IteratorBundle>? = null
            val childPartition = Partition(parent, partitionVariable!!)
            val partitionHelper: PartitionHelper2?
            partitionHelper = (query as Query).getPartitionHelper(uuid)
            partitionHelper.lock.lock()
            if (partitionHelper.iterators != null) {
                iterators = partitionHelper.iterators!![childPartition]
            }
            var error: Throwable? = null
            if (iterators == null) {
                iterators = Array(partitionCount) { IteratorBundle(0) }
                val variables = getProvidedVariableNames()
                val variables0 = children[0].getProvidedVariableNames()
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPSplitPartition.kt:126"/*SOURCE_FILE_END*/ }, { variables0.containsAll(variables) })
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPSplitPartition.kt:127"/*SOURCE_FILE_END*/ }, { variables.containsAll(variables0) })
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPSplitPartition.kt:128"/*SOURCE_FILE_END*/ }, { variables.contains(partitionVariable) })
                var queue_size = query.getInstance().queue_size
                var elementsPerRing = queue_size * variables.size
                var buffersize = elementsPerRing * partitionCount
                while (buffersize <= 0 || elementsPerRing <= 0) {
                    queue_size = queue_size / 2
                    elementsPerRing = queue_size * variables.size
                    buffersize = elementsPerRing * partitionCount
                }
                val ringbuffer = DictionaryValueTypeArray(buffersize) // only modified by writer, reader just modifies its pointer
                val ringbufferStart = IntArray(partitionCount) { it * elementsPerRing } // constant
                val ringbufferReadHead = IntArray(partitionCount) { 0 } // owned by read-thread - no locking required
                val ringbufferWriteHead = IntArray(partitionCount) { 0 } // owned by write thread - no locking required
                val ringbufferReaderContinuation = Array(partitionCount) { Parallel.createCondition() }
                val ringbufferWriterContinuation: ParallelCondition = Parallel.createCondition()
                val readerFinished = IntArray(partitionCount) { 0 } // writer changes to 1 if finished
                var writerFinished = 0
                val job = Parallel.launch {
                    var child2: RowIterator? = null
                    try {
                        val child = children[0].evaluate(childPartition).rows
                        child2 = child
                        var hashVariableIndex = -1
                        val variableMapping = IntArray(variables.size)
                        for (variable in variables.indices) {
                            for (variable2 in variables.indices) {
                                if (child.columns[variable] == partitionVariable) {
                                    hashVariableIndex = variable
                                }
                                if (variables[variable2] == child.columns[variable]) {
                                    variableMapping[variable] = variable2
                                    break
                                }
                            }
                        }
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPSplitPartition.kt:163"/*SOURCE_FILE_END*/ }, { hashVariableIndex != -1 })
                        val cacheArr = IntArray(partitionCount) { it }
                        loop@ while (true) {
                            var tmp = child.next()
                            var readerFinishedCounter = 0
                            for (p in 0 until partitionCount) {
                                if (readerFinished[p] != 0) {
                                    readerFinishedCounter++
                                }
                            }
                            if (readerFinishedCounter == partitionCount) {
                                tmp = -1
                            }
                            if (tmp == -1) {
                                break@loop
                            } else {
                                val q = child.buf[tmp + hashVariableIndex]
                                var cacheSize: Int
                                if (q == DictionaryValueHelper.undefValue) {
                                    // broadcast undef to every partition
                                    SanityCheck.println { " attention may increase result count here - this is always ok, _if there is a join afterwards immediately - otherwise probably not" }
                                    cacheSize = partitionCount
                                    cacheArr[0] = 0
                                } else {
                                    cacheSize = 1
                                    cacheArr[0] = DictionaryValueHelper.toInt(q % partitionCount)
                                }
                                loopcache@ for (i in 0 until cacheSize) {
                                    val p = cacheArr[i]
                                    if (readerFinished[p] != 0) {
                                        continue@loopcache
                                    }
                                    val t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    while (ringbufferReadHead[p] == t && readerFinished[p] == 0) {
                                        ringbufferReaderContinuation[p].signal()
                                        ringbufferWriterContinuation.waitCondition { ringbufferReadHead[p] == t && readerFinished[p] == 0 }
                                    }
                                    if (readerFinished[p] != 0) {
                                        continue@loopcache
                                    }
                                    for (variable in variables.indices) {
                                        ringbuffer[ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]] = child.buf[tmp + variable]
                                    }
                                    ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    ringbufferReaderContinuation[p].signal()
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPSplitPartition.kt:212"/*SOURCE_FILE_END*/ )()
                        error = e
                    }
                    child2?.close?.invoke()
                    writerFinished = 1
                    for (p in 0 until partitionCount) {
                        ringbufferReaderContinuation[p].signal()
                    }
                }
                for (p in 0 until partitionCount) {
                    val iterator = RowIterator()
                    iterator.columns = variables.toTypedArray()
                    iterator.buf = DictionaryValueTypeArray(variables.size)
                    iterator.next = {
                        var res = -1
                        loop@ while (true) {
                            if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                                // non empty queue -> read one row
                                for (variable in variables.indices) {
                                    iterator.buf[variable] = (ringbuffer[ringbufferReadHead[p] + variable + ringbufferStart[p]])
                                }
                                res = 0
                                ringbufferReadHead[p] = (ringbufferReadHead[p] + variables.size) % elementsPerRing
                                break@loop
                            } else if (writerFinished == 1) {
                                iterator.close()
                                if (error != null) {
                                    throw error!!
                                }
                                break@loop
                            }
                            ringbufferWriterContinuation.signal()
                            ringbufferReaderContinuation[p].waitCondition { ringbufferReadHead[p] == ringbufferWriteHead[p] && writerFinished == 0 }
                        }
                        res
                    }
                    iterator.close = {
                        readerFinished[p] = 1
                        ringbufferWriterContinuation.signal()
                    }
                    iterators[p] = IteratorBundle(iterator)
                }
                if (partitionHelper.iterators == null || partitionHelper.jobs == null) {
                    partitionHelper.iterators = mutableMapOf(childPartition to iterators)
                    partitionHelper.jobs = mutableMapOf(childPartition to job)
                } else {
                    partitionHelper.iterators!![childPartition] = iterators
                    partitionHelper.jobs!![childPartition] = job
                }
            }
            partitionHelper.lock.unlock()
            return iterators[parent.data[partitionVariable]!!]
        }
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): OPBase? = (children[0] as POPBase).toLocalOperatorGraph(Partition(parent, partitionVariable!!), onFoundLimit, onFoundSort)
}
