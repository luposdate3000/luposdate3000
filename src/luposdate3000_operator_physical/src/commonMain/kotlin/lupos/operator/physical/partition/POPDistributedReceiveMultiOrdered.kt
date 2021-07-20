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

import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class POPDistributedReceiveMultiOrdered public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount: Int,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    @JvmField public val hosts: Map<String, String>, // key -> hostname
) : POPBase(query, projectedVariables, EOperatorIDExt.POPDistributedReceiveMultiOrderedID, "POPDistributedReceiveMultiOrdered", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMultiOrdered.kt:45"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    override fun getPartitionCount(variable: String): Int {
        return if (variable == partitionVariable) {
            1
        } else {
            1
        }
    }

    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement {
        return toXMLElementHelper2(partial, true)
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        return toXMLElementHelper2(partial, false)
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
            XMLElement(classname).addAttribute("uuid", "$uuid").addContent(childrenToXML(partial))
        } else {
            super.toXMLElementHelper(partial, partial && !isRoot)
        }
        val theKey = mutableMapOf(partitionVariable to 0)
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

    override fun cloneOP(): IOPBase = POPDistributedReceiveMultiOrdered(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP(), hosts)
    override fun toSparql(): String = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPDistributedReceiveMultiOrdered && children[0] == other.children[0] && partitionVariable == other.partitionVariable
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = mutableListOf<String>()
        variables.addAll(projectedVariables)
        if (partitionVariable != "_" && variables.contains(partitionVariable)) {
            variables.remove(partitionVariable)
            variables.add(0, partitionVariable)
        }
        val buffer = DictionaryValueTypeArray(partitionCount * variables.size)
        val connections = Array<MyConnection?>(partitionCount) { null }
        var openConnections = 0
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMultiOrdered.kt:127"/*SOURCE_FILE_END*/ }, { hosts.size == partitionCount })
        val handler = query.getInstance().communicationHandler!!
        val allConnections = mutableMapOf<String, Pair<IMyInputStream, IMyOutputStream>>()
        for ((k, v) in hosts) {
            allConnections[k] = handler.openConnection(v, "/distributed/query/execute", mapOf("key" to k, "dictionaryURL" to query.getDictionaryUrl()!!), query.getTransactionID().toInt())
        }
        for (k in hosts.keys) {
            val conn = allConnections[k]!!
            val mapping = IntArray(variables.size)
            val cnt = conn.first.readInt()
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMultiOrdered.kt:137"/*SOURCE_FILE_END*/ }, { cnt == variables.size }, { "$cnt vs ${variables.size}" })
            for (i in 0 until variables.size) {
                val len = conn.first.readInt()
                val buf = ByteArray(len)
                conn.first.read(buf, len)
                val name = buf.decodeToString()
                val j = variables.indexOf(name)
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMultiOrdered.kt:144"/*SOURCE_FILE_END*/ }, { j >= 0 && j < variables.size })
                mapping[i] = j
            }
            val off = openConnections * variables.size
            for (i in 0 until variables.size) {
                buffer[off + mapping[i]] = conn.first.readDictionaryValueType()
            }
            if (buffer[off] == DictionaryValueHelper.nullValue) {
                conn.first.close()
                conn.second.close()
            } else {
                connections[openConnections] = MyConnection(conn.first, conn.second, mapping)
                openConnections++
            }
        }
        val iterator = RowIterator()
        iterator.columns = variables.toTypedArray()
        iterator.buf = DictionaryValueTypeArray(variables.size)
        iterator.next = {
            var res = -1
            if (openConnections > 0) {
                res = 0
                var min = 0
                for (i in 1 until openConnections) {
                    if (buffer[i * variables.size] < buffer[min * variables.size]) {
                        min = i
                    }
                }
                val off = min * variables.size
                val connMin = connections[min]!!
                buffer.copyInto(iterator.buf, 0, off, off + variables.size)
                for (i in 0 until variables.size) {
                    buffer[off + connMin.mapping[i]] = connMin.input.readDictionaryValueType()
                }
                if (buffer[off] == DictionaryValueHelper.nullValue) {
                    connMin.input.close()
                    connMin.output.close()
                    val off2 = (openConnections - 1) * variables.size
                    if (off != off2) {
                        val connOther = connections[openConnections - 1]!!
                        for (i in 0 until variables.size) {
                            buffer[off + connMin.mapping[i]] = buffer[off2 + connOther.mapping[i]]
                        }
                        connections[min] = connections[openConnections - 1]
                    }
                    connections[openConnections - 1] = null
                    openConnections--
                }
            }
            res
        }
        iterator.close = {
            for (i in 0 until openConnections) {
                connections[i]?.input?.close()
                connections[i]?.output?.close()
            }
        }
        return IteratorBundle(iterator)
    }
}
