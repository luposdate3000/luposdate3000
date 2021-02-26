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
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField

public class POPMergePartitionCount public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val partitionVariable: String, @JvmField public var partitionCount: Int, @JvmField public var partitionID: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPMergePartitionCountID, "POPMergePartitionCount", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
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
                XMLElement("POPDistributedSendSingleCount").addAttribute("uuid", "$uuid").addContent(childrenToXML(partial))
            } else {
                if (partitionCount > 1) {
                    XMLElement("POPDistributedReceiveMultiCount").addAttribute("uuid", "$uuid")
                } else {
                    XMLElement("POPDistributedReceiveSingleCount").addAttribute("uuid", "$uuid")
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

    override fun cloneOP(): IOPBase = POPMergePartitionCount(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP())
    override fun toSparql(): String = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPMergePartitionCount && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        if (partitionCount == 1) {
            // single partition - just pass through
            return children[0].evaluate(parent)
        } else {
            val variables = getProvidedVariableNames()
            val variables0 = children[0].getProvidedVariableNames()
            SanityCheck.check { variables0.containsAll(variables) }
            SanityCheck.check { variables.containsAll(variables0) }
            // partitionVariable as any other variable is not included in the result of the child operator
            val ringbufferReadHead = IntArray(partitionCount) { 0 } // owned by read-thread - no locking required - available count is the difference between "ringbufferReadHead" and "ringbufferWriteHead"
            val ringbufferWriteHead = IntArray(partitionCount) { 0 } // owned by write thread - no locking required
            val writerFinished = IntArray(partitionCount) { 0 } // writer changes to 1 if finished
            var readerFinished = 0
            for (p in 0 until partitionCount) {
                Parallel.launch {
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
                        Parallel.delay(1)
                    }
                    return res
                }

                override /*suspend*/ fun hasNext2Close() {
                    readerFinished = 1
                }
            }
        }
    }
}
