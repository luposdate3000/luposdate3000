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

package lupos.s09physicalOperators.partition
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.MyLock
import lupos.s00misc.Parallel
import lupos.s00misc.ParallelCondition
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField
// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPMergePartition public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val partitionVariable: String, @JvmField public var partitionCount: Int, @JvmField public var partitionID: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPMergePartitionID, "POPMergePartition", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int {
        return if (variable == partitionVariable) {
            1
        } else {
            children[0].getPartitionCount(variable)
        }
    }
    override /*suspend*/ fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        res.addAttribute("partitionVariable", partitionVariable)
        res.addAttribute("partitionCount", "" + partitionCount)
        res.addAttribute("partitionID", "" + partitionID)
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
    override fun cloneOP(): IOPBase = POPMergePartition(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP())
    override fun toSparql(): String = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPMergePartition && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        if (partitionCount == 1) {
            // single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            var error: Throwable? = null
            val variables = getProvidedVariableNames()
            val variables0 = children[0].getProvidedVariableNames()
            SanityCheck.check { variables0.containsAll(variables) }
            SanityCheck.check { variables.containsAll(variables0) }
            // the variable may be eliminated directly after using it in the join            SanityCheck.check { variables.contains(partitionVariable) }
            val elementsPerRing = Partition.queue_size * variables.size
            val ringbuffer = IntArray(elementsPerRing * partitionCount) // only modified by writer, reader just modifies its pointer
            val ringbufferStart = IntArray(partitionCount) { it * elementsPerRing } // constant
            val ringbufferReadHead = IntArray(partitionCount) { 0 } // owned by read-thread - no locking required
            val ringbufferWriteHead = IntArray(partitionCount) { 0 } // owned by write thread - no locking required
            val continuationLock = MyLock()
            val ringbufferWriterContinuation = Array(partitionCount) { Parallel.createCondition() }
            val ringbufferReaderContinuation: ParallelCondition = Parallel.createCondition()
            val writerFinished = IntArray(partitionCount) { 0 } // writer changes to 1 if finished
            var readerFinished = 0
            for (p in 0 until partitionCount) {
                SanityCheck.println { "merge $uuid $p writer launched F" }
                SanityCheck.println { "merge $uuid $p writer launched G" }
                Parallel.launch {
                    try {
                        val childEval2: IteratorBundle?
                        childEval2 = children[0].evaluate(Partition(parent, partitionVariable, p, partitionCount))
                        SanityCheck.println { "merge $uuid $p writer launched A" }
                        if (childEval2.hasColumnMode()) {
                            SanityCheck.println { "merge $uuid $p writer launched B" }
                            val child = childEval2.columns
                            if (variables.size == 1) {
                                SanityCheck.println { "merge $uuid $p writer launched C" }
                                val childIterator = child[variables[0]]!!
                                loop@ while (readerFinished == 0) {
                                    SanityCheck.println { "merge $uuid $p writer loop start" }
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
                                    if (tmp == ResultSetDictionaryExt.nullValue) {
                                        break@loop
                                    } else {
                                        SanityCheck.println { "merge $uuid $p writer append data" }
                                        ringbuffer[ringbufferWriteHead[p] + ringbufferStart[p]] = tmp
                                        // println("$p produced")
                                        ringbufferWriteHead[p] = (ringbufferWriteHead[p] + 1) % elementsPerRing
                                        ringbufferReaderContinuation.signal()
                                    }
                                }
                            } else {
                                SanityCheck.println { "merge $uuid $p writer launched D" }
                                val variableMapping = Array(variables.size) { child[variables[it]]!! }
                                loop@ while (readerFinished == 0) {
                                    SanityCheck.println { "merge $uuid $p writer loop start" }
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
                                    if (tmp == ResultSetDictionaryExt.nullValue) {
                                        SanityCheck.println { "merge $uuid $p writer closed B" }
                                        for (variable in 0 until variables.size) {
                                            variableMapping[variable].close()
                                        }
                                        break@loop
                                    } else {
                                        SanityCheck.println { "merge $uuid $p writer append data" }
                                        ringbuffer[ringbufferWriteHead[p] + ringbufferStart[p]] = tmp
                                        for (variableIdx in 1 until variables.size) {
                                            try {
                                                ringbuffer[ringbufferWriteHead[p] + variableIdx + ringbufferStart[p]] = variableMapping[variableIdx].next()
                                            } catch (e: Throwable) {
                                                SanityCheck.println { "merge $uuid $p writer closed A" }
                                                for (variableIdx2 in 0 until variables.size) {
                                                    variableMapping[variableIdx2].close()
                                                }
                                                break@loop
                                            }
                                        }
                                        // println("$p produced")
                                        ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                        ringbufferReaderContinuation.signal()
                                    }
                                }
                            }
                        } else {
                            SanityCheck.println { "merge $uuid $p writer launched E" }
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
                                SanityCheck.println { "merge $uuid $p writer loop start" }
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
                                    SanityCheck.println { "merge $uuid $p writer closed B" }
                                    break@loop
                                } else {
                                    SanityCheck.println { "merge $uuid $p writer append data" }
                                    for (variable in variables.indices) {
                                        ringbuffer[ringbufferWriteHead[p] + variableMapping[variable] + ringbufferStart[p]] = child.buf[tmp + variable]
                                    }
                                    // println("$p produced")
                                    ringbufferWriteHead[p] = (ringbufferWriteHead[p] + variables.size) % elementsPerRing
                                    ringbufferReaderContinuation.signal()
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        error = e
                        e.printStackTrace()
                    }
                    writerFinished[p] = 1
                    ringbufferReaderContinuation.signal()
                    SanityCheck.println { "merge $uuid $p writer exited loop" }
                }
                SanityCheck.println { "merge $uuid $p writer lupos.s00misc.ParallelJob init :: " }
            }
            val iterator = RowIterator()
            iterator.columns = variables.toTypedArray()
            iterator.buf = IntArray(variables.size)
            iterator.next = {
                var res = -1
                loop@ while (true) {
                    SanityCheck.println { "merge $uuid reader loop start" }
                    for (p in 0 until partitionCount) {
                        if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                            // non empty queue -> read one row
                            SanityCheck.println { "merge $uuid $p reader consumed data" }
                            for (variable in variables.indices) {
                                iterator.buf[variable] = (ringbuffer[ringbufferReadHead[p] + variable + ringbufferStart[p]])
                            }
                            res = 0
                            ringbufferReadHead[p] = (ringbufferReadHead[p] + variables.size) % elementsPerRing
                            ringbufferWriterContinuation[p].signal()
                            break@loop
                        } else if (writerFinished[p] == 0) {
                            ringbufferWriterContinuation[p].signal()
                        }
                    }
                    var finishedWriters = 0
                    ringbufferReaderContinuation.waitCondition {
                        var flag = true
                        for (p in 0 until partitionCount) {
                            if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                                flag = false
                                break
                            } else if (writerFinished[p] != 0) {
                                finishedWriters++
                            }
                        }
                        (flag && finishedWriters < partitionCount)
                    }
                    if (finishedWriters == partitionCount) {
                        break@loop
                    }
                }
                if (error != null) {
                    iterator.close()
                    throw error!!
                }
                res
            }
            iterator.close = {
                SanityCheck.println { "merge $uuid reader closed" }
                readerFinished = 1
                for (p in 0 until partitionCount) {
                    ringbufferWriterContinuation[p].signal()
                }
            }
            return IteratorBundle(iterator)
        }
    }
}
