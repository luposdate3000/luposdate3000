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
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.ESortTypeExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.inline.ParallelThread
import lupos.shared.inline.ParallelThreadCondition
import lupos.shared.myPrintStackTrace
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPMergePartitionOrderedByIntId public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount2: Int,
    @JvmField public var partitionID: Int,
    child: IOPBase,
) : APOPParallel(
    query,
    projectedVariables,
    EOperatorIDExt.POPMergePartitionOrderedByIntIdID,
    "POPMergePartitionOrderedByIntId",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY,
) {
    init {
        if (SanityCheck.enabled) { if (!(projectedVariables.isNotEmpty())) { throw Exception("SanityCheck failed") } }
    }

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
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        if (partitionCount2 == 1) {
            // single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            var error: Throwable? = null
            val variables = getProvidedVariableNames()
            val variables0 = children[0].getProvidedVariableNames()
            if (SanityCheck.enabled) { if (!(variables0.containsAll(variables))) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(variables.containsAll(variables0))) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(variables.contains(partitionVariable))) { throw Exception("SanityCheck failed") } }
            var queue_size = query.getInstance().queue_size
            var elementsPerRing = queue_size * variables.size
            var buffersize = elementsPerRing * partitionCount2
            while (buffersize <= 0 || elementsPerRing <= 0) {
                queue_size = queue_size / 2
                elementsPerRing = queue_size * variables.size
                buffersize = elementsPerRing * partitionCount2
            }
            val ringbuffer = DictionaryValueTypeArray(buffersize) // only modified by writer, reader just modifies its pointer
            val ringbufferStart = IntArray(partitionCount2) { it * elementsPerRing } // constant
            val ringbufferReadHead = IntArray(partitionCount2) { 0 } // owned by read-thread - no locking required
            val ringbufferWriteHead = IntArray(partitionCount2) { 0 } // owned by write thread - no locking required
            val ringbufferWriterContinuation = Array(partitionCount2) { ParallelThread.createCondition() }
            val ringbufferReaderContinuation: ParallelThreadCondition = ParallelThread.createCondition()
            val writerFinished = IntArray(partitionCount2) { 0 } // writer changes to 1 if finished
            var readerFinished = 0
            val threads = List(partitionCount2) { p ->
                ParallelThread.launch {
                    try {
                        val childEval2: IteratorBundle?
                        childEval2 = children[0].evaluate(Partition(parent, partitionVariable, p, partitionCount2))
                        if (childEval2.hasColumnMode()) {
                            val child = childEval2.columns
                            if (variables.size == 1) {
                                val childIterator = child[variables[0]]!!
                                loop@ while (readerFinished == 0) {
                                    val t = (ringbufferWriteHead[p] + 1) % elementsPerRing
                                    while (ringbufferReadHead[p] == t && readerFinished == 0) {
                                        ringbufferReaderContinuation.signal()
                                        ringbufferWriterContinuation[p].waitCondition { ringbufferReadHead[p] == t && readerFinished == 0 }
                                    }
                                    if (readerFinished != 0) {
                                        childIterator.close()
                                        break@loop
                                    }
                                    val tmp = childIterator.next()
                                    if (tmp == DictionaryValueHelper.nullValue) {
                                        break@loop
                                    } else {
                                        ringbuffer[ringbufferWriteHead[p] + ringbufferStart[p]] = tmp
                                        ringbufferWriteHead[p] = (ringbufferWriteHead[p] + 1) % elementsPerRing
                                        ringbufferReaderContinuation.signal()
                                    }
                                }
                            } else {
                                val variableMapping = Array(variables.size) { child[variables[it]]!! }
                                loop@ while (readerFinished == 0) {
                                    val t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    while (ringbufferReadHead[p] == t && readerFinished == 0) {
                                        ringbufferReaderContinuation.signal()
                                        ringbufferWriterContinuation[p].waitCondition { ringbufferReadHead[p] == t && readerFinished == 0 }
                                    }
                                    if (readerFinished != 0) {
                                        for (variable in 0 until variables.size) {
                                            variableMapping[variable].close()
                                        }
                                        break@loop
                                    }
                                    val tmp = variableMapping[0].next()
                                    if (tmp == DictionaryValueHelper.nullValue) {
                                        for (variable in 0 until variables.size) {
                                            variableMapping[variable].close()
                                        }
                                        break@loop
                                    } else {
                                        ringbuffer[ringbufferWriteHead[p] + ringbufferStart[p]] = tmp
                                        for (variableIdx in 1 until variables.size) {
                                            try {
                                                ringbuffer[ringbufferWriteHead[p] + variableIdx + ringbufferStart[p]] = variableMapping[variableIdx].next()
                                            } catch (e: Throwable) {
                                                e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPMergePartitionOrderedByIntId.kt:185"/*SOURCE_FILE_END*/)
                                                for (variableIdx2 in 0 until variables.size) {
                                                    variableMapping[variableIdx2].close()
                                                }
                                                break@loop
                                            }
                                        }
                                        ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                        ringbufferReaderContinuation.signal()
                                    }
                                }
                            }
                        } else {
                            val child = childEval2.rows
                            val variableMapping = IntArray(variables.size)
                            for (variable in variables.indices) {
                                for (variable2 in variables.indices) {
                                    if (variables[variable2] == child.columns[variable]) {
                                        variableMapping[variable] = variable2
                                        break
                                    }
                                }
                            }
                            loop@ while (readerFinished == 0) {
                                val t = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                while (ringbufferReadHead[p] == t && readerFinished == 0) {
                                    ringbufferReaderContinuation.signal()
                                    ringbufferWriterContinuation[p].waitCondition { ringbufferReadHead[p] == t && readerFinished == 0 }
                                }
                                if (readerFinished != 0) {
                                    child.close()
                                    break@loop
                                }
                                val tmp = child.next()
                                if (tmp == -1) {
                                    break@loop
                                } else {
                                    for (variable in variables.indices) {
                                        ringbuffer[ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]] = child.buf[tmp + variable]
                                    }
                                    ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    ringbufferReaderContinuation.signal()
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPMergePartitionOrderedByIntId.kt:231"/*SOURCE_FILE_END*/)
                        error = e
                    }
                    writerFinished[p] = 1
                    ringbufferReaderContinuation.signal()
                }
            }
            val sortColumns = IntArray(mySortPriority.size) { variables.indexOf(mySortPriority[it].variableName) }
            if (SanityCheck.enabled) {
                for (x in sortColumns.indices) {
                    if (SanityCheck.enabled) { if (!(sortColumns[x] >= 0)) { throw Exception("SanityCheck failed") } }
                    if (SanityCheck.enabled) { if (!(mySortPriority[x].sortType == ESortTypeExt.FAST)) { throw Exception("SanityCheck failed") } }
                }
            }

            val iterator = RowIterator()
            iterator.columns = variables.toTypedArray()
            iterator.buf = DictionaryValueTypeArray(variables.size)
            iterator.next = {
                var res = -1
                loop@ while (true) {
                    var partitionToUse = -1
                    loop2@ for (p in 0 until partitionCount2) {
                        if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                            partitionToUse = if (partitionToUse < 0) {
                                p
                            } else {
                                for (sp in sortColumns) {
                                    val valThis = ringbuffer[ringbufferReadHead[p] + sp + ringbufferStart[p]]
                                    val valSmallest = ringbuffer[ringbufferReadHead[partitionToUse] + sp + ringbufferStart[partitionToUse]]
                                    if (valThis > valSmallest) {
                                        continue@loop2
                                    }
                                }
                                p
                            }
                        } else if (writerFinished[p] == 0) {
                            ringbufferWriterContinuation[p].signal()
                            partitionToUse = -1
                            break@loop2
                        }
                    }
                    if (partitionToUse >= 0) {
                        for (variable in variables.indices) {
                            iterator.buf[variable] = (ringbuffer[ringbufferReadHead[partitionToUse] + variable + ringbufferStart[partitionToUse]])
                        }
                        res = 0
                        ringbufferReadHead[partitionToUse] = (ringbufferReadHead[partitionToUse] + variables.size) % elementsPerRing
                        ringbufferWriterContinuation[partitionToUse].signal()
                        break@loop
                    }
                    var finishedWriters = 0
                    ringbufferReaderContinuation.waitCondition {
                        var flag = true
                        for (p in 0 until partitionCount2) {
                            if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                                flag = false
                                break
                            } else if (writerFinished[p] != 0) {
                                finishedWriters++
                            }
                        }
                        (flag && finishedWriters < partitionCount2)
                    }
                    if (finishedWriters == partitionCount2) {
                        break@loop
                    }
                }
                if (res == -1) {
                    iterator.close()
                }
                if (error != null) {
                    iterator.close()
                    throw error!!
                }
                res
            }
            iterator.close = {
                readerFinished = 1
                for (p in 0 until partitionCount2) {
                    ringbufferWriterContinuation[p].signal()
                }
                for (t in threads) {
                    t.join()
                }
            }
            return IteratorBundle(iterator)
        }
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? {
        var res: POPBase? = null
        for (p in 0 until partitionCount2) {
            val tmp = (children[0] as POPBase).toLocalOperatorGraph(Partition(parent, partitionVariable, p, partitionCount2), onFoundLimit, onFoundSort)
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
