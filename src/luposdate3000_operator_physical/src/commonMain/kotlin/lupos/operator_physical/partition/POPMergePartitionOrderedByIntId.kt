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
package lupos.operator_physical.partition

import lupos.dictionary.DictionaryExt
import lupos.operator_logical.IOPBase
import lupos.operator_logical.IQuery
import lupos.operator_logical.iterator.IteratorBundle
import lupos.operator_logical.iterator.RowIterator
import lupos.operator_physical.POPBase
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.ESortTypeExt
import lupos.s00misc.Parallel
import lupos.s00misc.ParallelCondition
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPMergePartitionOrderedByIntId public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val partitionVariable: String, @JvmField public var partitionCount: Int, @JvmField public var partitionID: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPMergePartitionOrderedByIntIdID, "POPMergePartitionOrderedByIntId", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    init {
        SanityCheck.check { projectedVariables.size > 0 }
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
                XMLElement("POPDistributedSendSingle").addAttribute("uuid", "$uuid").addContent(childrenToXML(partial))
            } else {
                if (partitionCount > 1) {
                    XMLElement("POPDistributedReceiveMulti").addAttribute("uuid", "$uuid")
                } else {
                    XMLElement("POPDistributedReceiveSingle").addAttribute("uuid", "$uuid")
                }
            }
        } else {
            super.toXMLElementHelper(partial, partial && !isRoot)
        }
        var theKey = mutableMapOf<String, Int>(partitionVariable to 0)
        theKey.putAll(query.getDistributionKey())
        if (isRoot) {
            res.addContent(XMLElement("partitionDistributionProvideKey").addAttribute("key", theKeyToString(theKey)))
        } else {
            res.addContent(XMLElement("partitionDistributionReceiveKey").addAttribute("key", theKeyToString(theKey)))
            for (i in 1 until partitionCount) {
                theKey[partitionVariable] = theKey[partitionVariable]!! + 1
                res.addContent(XMLElement("partitionDistributionReceiveKey").addAttribute("key", theKeyToString(theKey)))
            }
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

    override fun cloneOP(): IOPBase = POPMergePartitionOrderedByIntId(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP())
    override fun toSparql(): String = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPMergePartitionOrderedByIntId && children[0] == other.children[0] && partitionVariable == other.partitionVariable
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
            val ringbufferWriterContinuation = Array(partitionCount) { Parallel.createCondition() }
            val ringbufferReaderContinuation: ParallelCondition = Parallel.createCondition()
            val writerFinished = IntArray(partitionCount) { 0 } // writer changes to 1 if finished
            var readerFinished = 0
            for (p in 0 until partitionCount) {
                Parallel.launch {
                    try {
                        val childEval2: IteratorBundle?
                        childEval2 = children[0].evaluate(Partition(parent, partitionVariable, p, partitionCount))
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
                                    if (tmp == DictionaryExt.nullValue) {
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
                                    if (tmp == DictionaryExt.nullValue) {
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
                                                e.printStackTrace()
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
                        e.printStackTrace()
                        error = e
                    }
                    writerFinished[p] = 1
                    ringbufferReaderContinuation.signal()
                }
            }
            val sortColumns = IntArray(mySortPriority.size) { variables.indexOf(mySortPriority[it].variableName) }
            SanityCheck {
                for (x in sortColumns.indices) {
                    SanityCheck.check { sortColumns[x] >= 0 }
                    SanityCheck.check { mySortPriority[x].sortType == ESortTypeExt.FAST }
                }
            }
            val iterator = RowIterator()
            iterator.columns = variables.toTypedArray()
            iterator.buf = IntArray(variables.size)
            iterator.next = {
                var res = -1
                loop@ while (true) {
                    var partitionToUse = -1
                    loop2@ for (p in 0 until partitionCount) {
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
                readerFinished = 1
                for (p in 0 until partitionCount) {
                    ringbufferWriterContinuation[p].signal()
                }
            }
            return IteratorBundle(iterator)
        }
    }
}
