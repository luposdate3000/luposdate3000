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

import lupos.dictionary.DictionaryExt
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.ESortTypeExt
import lupos.s00misc.Parallel
import lupos.s00misc.ParallelCondition
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPChangePartitionOrderedByIntId public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val partitionVariable: String, @JvmField public var partitionCountFrom: Int, @JvmField public var partitionCountTo: Int, @JvmField public var partitionIDFrom: Int, @JvmField public var partitionIDTo: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPChangePartitionOrderedByIntIdID, "POPChangePartitionOrderedByIntId", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    init {
        SanityCheck.check { projectedVariables.size > 0 }
    }

    public override fun changePartitionID(idFrom: Int, idTo: Int) {
        if (partitionIDFrom == idFrom) {
            partitionIDFrom = idTo
        } else {
            SanityCheck.check { partitionIDTo == idFrom }
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
                if (partitionCountTo > partitionCountFrom) {
                    XMLElement("POPDistributedSendMulti").addAttribute("uuid", "$uuid").addContent(childrenToXML(partial))
                } else {
                    XMLElement("POPDistributedSendSingle").addAttribute("uuid", "$uuid").addContent(childrenToXML(partial))
                }
            } else {
                if (partitionCountTo < partitionCountFrom) {
                    XMLElement("POPDistributedReceiveMultiOrdered").addAttribute("uuid", "$uuid")
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
            for (i in 1 until partitionCountTo / partitionCountFrom) {
                theKey[partitionVariable] = theKey[partitionVariable]!! + i * partitionCountFrom
                res.addContent(XMLElement("partitionDistributionProvideKey").addAttribute("key", theKeyToString(theKey)))
            }
        } else {
            res.addContent(XMLElement("partitionDistributionReceiveKey").addAttribute("key", theKeyToString(theKey)))
            for (i in 1 until partitionCountFrom / partitionCountTo) {
                theKey[partitionVariable] = theKey[partitionVariable]!! + i * partitionCountTo
                res.addContent(XMLElement("partitionDistributionReceiveKey").addAttribute("key", theKeyToString(theKey)))
            }
        }
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

    override fun cloneOP(): IOPBase = POPChangePartitionOrderedByIntId(query, projectedVariables, partitionVariable, partitionCountFrom, partitionCountTo, partitionIDFrom, partitionIDTo, children[0].cloneOP())
    override fun toSparql(): String = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPChangePartitionOrderedByIntId && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { partitionCountTo < partitionCountFrom }
        val partitionCountSrc = partitionCountFrom / partitionCountTo
        var error: Throwable? = null
        val variables = getProvidedVariableNames()
        val variables0 = children[0].getProvidedVariableNames()
        SanityCheck.check { variables0.containsAll(variables) }
        SanityCheck.check { variables.containsAll(variables0) }
        // the variable may be eliminated directly after using it in the join            SanityCheck.check { variables.contains(partitionVariable) }
        val elementsPerRing = Partition.queue_size * variables.size
        val ringbuffer = IntArray(elementsPerRing * partitionCountSrc) // only modified by writer, reader just modifies its pointer
        val ringbufferStart = IntArray(partitionCountSrc) { it * elementsPerRing } // constant
        val ringbufferReadHead = IntArray(partitionCountSrc) { 0 } // owned by read-thread - no locking required
        val ringbufferWriteHead = IntArray(partitionCountSrc) { 0 } // owned by write thread - no locking required
        val ringbufferWriterContinuation = ArrayAllocator(partitionCountSrc) { Parallel.createCondition() }
        val ringbufferReaderContinuation: ParallelCondition = Parallel.createCondition()
        val writerFinished = IntArray(partitionCountSrc) { 0 } // writer changes to 1 if finished
        var readerFinished = 0
        for (p1 in 0 until partitionCountSrc) {
            val pChild = p1 * partitionCountTo + parent.data[partitionVariable]!!
            Parallel.launch {
                try {
                    val childEval2: IteratorBundle?
                    childEval2 = children[0].evaluate(Partition(parent, partitionVariable, pChild, partitionCountFrom))
                    if (childEval2.hasColumnMode()) {
                        val child = childEval2.columns
                        if (variables.size == 1) {
                            val childIterator = child[variables[0]]!!
                            loop@ while (readerFinished == 0) {
                                val t = (ringbufferWriteHead[p1] + 1) % elementsPerRing
                                while (ringbufferReadHead[p1] == t && readerFinished == 0) {
                                    ringbufferReaderContinuation.signal()
                                    ringbufferWriterContinuation[p1].waitCondition { ringbufferReadHead[p1] == t && readerFinished == 0 }
                                }
                                if (readerFinished != 0) {
                                    childIterator.close()
                                    break@loop
                                }
                                val tmp = childIterator.next()
                                if (tmp == DictionaryExt.nullValue) {
                                    break@loop
                                } else {
                                    ringbuffer[ringbufferWriteHead[p1] + ringbufferStart[p1]] = tmp
                                    ringbufferWriteHead[p1] = (ringbufferWriteHead[p1] + 1) % elementsPerRing
                                    ringbufferReaderContinuation.signal()
                                }
                            }
                        } else {
                            val variableMapping = ArrayAllocator(variables.size) { child[variables[it]]!! }
                            loop@ while (readerFinished == 0) {
                                val t = (ringbufferWriteHead[p1] + variables.size) % elementsPerRing
                                while (ringbufferReadHead[p1] == t && readerFinished == 0) {
                                    ringbufferReaderContinuation.signal()
                                    ringbufferWriterContinuation[p1].waitCondition { ringbufferReadHead[p1] == t && readerFinished == 0 }
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
                                    ringbuffer[ringbufferWriteHead[p1] + ringbufferStart[p1]] = tmp
                                    for (variableIdx in 1 until variables.size) {
                                        try {
                                            ringbuffer[ringbufferWriteHead[p1] + variableIdx + ringbufferStart[p1]] = variableMapping[variableIdx].next()
                                        } catch (e: Throwable) {
                                            for (variableIdx2 in 0 until variables.size) {
                                                variableMapping[variableIdx2].close()
                                            }
                                            break@loop
                                        }
                                    }
                                    ringbufferWriteHead[p1] = (ringbufferWriteHead[p1] + variables.size) % elementsPerRing
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
                            val t = (ringbufferWriteHead[p1] + variables.size) % elementsPerRing
                            while (ringbufferReadHead[p1] == t && readerFinished == 0) {
                                ringbufferReaderContinuation.signal()
                                ringbufferWriterContinuation[p1].waitCondition { ringbufferReadHead[p1] == t && readerFinished == 0 }
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
                                    ringbuffer[ringbufferWriteHead[p1] + variableMapping[variable] + ringbufferStart[p1]] = child.buf[tmp + variable]
                                }
                                ringbufferWriteHead[p1] = (ringbufferWriteHead[p1] + variables.size) % elementsPerRing
                                ringbufferReaderContinuation.signal()
                            }
                        }
                    }
                } catch (e: Throwable) {
                    error = e
                    e.printStackTrace()
                }
                writerFinished[p1] = 1
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
                loop2@ for (p in 0 until partitionCountSrc) {
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
                    for (p in 0 until partitionCountSrc) {
                        if (ringbufferReadHead[p] != ringbufferWriteHead[p]) {
                            flag = false
                            break
                        } else if (writerFinished[p] != 0) {
                            finishedWriters++
                        }
                    }
                    (flag && finishedWriters < partitionCountSrc)
                }
                if (finishedWriters == partitionCountSrc) {
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
            for (p in 0 until partitionCountSrc) {
                ringbufferWriterContinuation[p].signal()
            }
        }
        return IteratorBundle(iterator)
    }
}
