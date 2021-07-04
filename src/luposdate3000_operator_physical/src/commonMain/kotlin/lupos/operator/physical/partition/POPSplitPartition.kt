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
import lupos.operator.base.PartitionHelper
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
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

public class POPSplitPartition public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val partitionVariable: String, @JvmField public var partitionCount: Int, @JvmField public var partitionID: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPSplitPartitionID, "POPSplitPartition", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    init {
        SanityCheck.check { projectedVariables.size > 0 }
    }

    public override fun changePartitionID(idFrom: Int, idTo: Int) {
        partitionID = idTo
    }

    override fun getPartitionCount(variable: String): Int {
        return if (variable == partitionVariable) {
            partitionCount
        } else {
            children[0].getPartitionCount(variable)
        }
    }

    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement {
        var res = toXMLElementHelper2(partial, true)
        return res
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        var res = toXMLElementHelper2(partial, false)
        return res
    }

    private fun theKeyToString(key: Map<String, Int>): String {
        var s = "$uuid"
        for (k in key.keys.sorted()) {
            s += ":$k=${key[k]}"
        }
        return s
    }

    private fun toXMLElementHelper2(partial: Boolean, isRoot: Boolean): XMLElement {
        val res = if (partial) {
            if (isRoot) {
                if (partitionCount > 1) {
                    XMLElement("POPDistributedSendMulti").addAttribute("uuid", "$uuid").addContent(childrenToXML(partial))
                } else {
                    XMLElement("POPDistributedSendSingle").addAttribute("uuid", "$uuid").addContent(childrenToXML(partial))
                }
            } else {
                XMLElement("POPDistributedReceiveSingle").addAttribute("uuid", "$uuid")
            }
        } else {
            super.toXMLElementHelper(partial, partial && !isRoot)
        }
        var theKey = mutableMapOf<String, Int>(partitionVariable to 0)
        theKey.putAll(query.getDistributionKey())
        if (isRoot) {
            res.addContent(XMLElement("partitionDistributionProvideKey").addAttribute("key", theKeyToString(theKey)))
            for (i in 1 until partitionCount) {
                theKey[partitionVariable] = theKey[partitionVariable]!! + 1
                res.addContent(XMLElement("partitionDistributionProvideKey").addAttribute("key", theKeyToString(theKey)))
            }
        } else {
            res.addContent(XMLElement("partitionDistributionReceiveKey").addAttribute("key", theKeyToString(theKey)))
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

    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun getProvidedVariableNames(): List<String> = children[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> {
        val tmp = children[0]
        return if (tmp is POPBase) {
            tmp.getProvidedVariableNamesInternal()
        } else {
            tmp.getProvidedVariableNames()
        }
    }

    override fun cloneOP(): IOPBase = POPSplitPartition(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP())
    override fun toSparql(): String = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPSplitPartition && children[0] == other.children[0] && partitionVariable == other.partitionVariable && partitionCount == other.partitionCount
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
// throw BugException("POPSplitPartition","child is not launching, because coroutine is missing suspension point")
        val partitionCount = parent.limit[partitionVariable]!!
        if (partitionCount == 1) {
            // single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            var iterators: Array<IteratorBundle>? = null
            val childPartition = Partition(parent, partitionVariable)
            val partitionHelper: PartitionHelper?
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
                SanityCheck.check { variables0.containsAll(variables) }
                SanityCheck.check { variables.containsAll(variables0) }
                SanityCheck.check { variables.contains(partitionVariable) }
                val elementsPerRing = query.getInstance().queue_size * variables.size
                val ringbuffer = DictionaryValueTypeArray(elementsPerRing * partitionCount) // only modified by writer, reader just modifies its pointer
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
                        SanityCheck.check { hashVariableIndex != -1 }
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
                                var q = child.buf[tmp + hashVariableIndex]
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
                        e.printStackTrace()
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
}
