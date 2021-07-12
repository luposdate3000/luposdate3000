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
public class POPDistributedReceiveMulti public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount: Int,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    private val inputs: Array<IMyInputStream>,
    private val outputs: Array<IMyOutputStream?> = Array(inputs.size) { null },
) : POPBase(query, projectedVariables, EOperatorIDExt.POPDistributedReceiveMultiID, "POPDistributedReceiveMulti", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    public companion object {
        public operator fun invoke(
            query: IQuery,
            projectedVariables: List<String>,
            partitionVariable: String,
            partitionCount: Int,
            partitionID: Int,
            child: IOPBase,
            hosts: Map<String, String>,
        ): POPDistributedReceiveMulti {
            val handler = query.getInstance().communicationHandler!!
            val inputs = mutableListOf<IMyInputStream>()
            val outputs = mutableListOf<IMyOutputStream?>()
            for ((k, v) in hosts) {
                val conn = handler.openConnection(v, "/distributed/query/execute", mapOf("key" to k, "dictionaryURL" to query.getDictionaryUrl()!!))
                inputs.add(conn.first)
                outputs.add(conn.second)
            }
            return POPDistributedReceiveMulti(query, projectedVariables, partitionVariable, partitionCount, partitionID, child, inputs.toTypedArray(), outputs.toTypedArray())
        }
    }
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMulti.kt:66"/*SOURCE_FILE_END*/ }, { projectedVariables.size > 0 })
    }

    override fun getPartitionCount(variable: String): Int {
        return if (variable == partitionVariable) {
            1
        } else {
            1
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
            XMLElement(classname).addAttribute("uuid", "$uuid").addContent(childrenToXML(partial))
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

    override fun cloneOP(): IOPBase = POPDistributedReceiveMulti(query, projectedVariables, partitionVariable, partitionCount, partitionID, children[0].cloneOP(), inputs, outputs)
    override fun toSparql(): String = children[0].toSparql()
    override fun equals(other: Any?): Boolean = other is POPDistributedReceiveMulti && children[0] == other.children[0] && partitionVariable == other.partitionVariable

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        println("POPDistributedReceiveMulti.evaluate .. $projectedVariables")
        val variables = mutableListOf<String>()
        variables.addAll(projectedVariables)
        if (partitionVariable != "_" && variables.contains(partitionVariable)) {
            variables.remove(partitionVariable)
            variables.add(0, partitionVariable)
        }
        var buffer = DictionaryValueTypeArray(partitionCount * variables.size)

        var connectionsIn = Array<IMyInputStream?>(partitionCount) { null }
        val connectionsMapping = Array<IntArray?>(partitionCount) { null }
        val connectionsOut = Array<IMyOutputStream?>(partitionCount) { null }

        var openConnections = 0
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMulti.kt:154"/*SOURCE_FILE_END*/ }, { inputs.size == partitionCount })
        val handler = query.getInstance().communicationHandler!!
        for (k in 0 until inputs.size) {
            val conn = inputs[k]!!
            val cnt = conn.readInt()
            var mapping = IntArray(cnt)
            for (i in 0 until cnt) {
                val len = conn.readInt()
                val buf = ByteArray(len)
                conn.read(buf, len)
                val name = buf.decodeToString()
                val j = variables.indexOf(name)
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMulti.kt:166"/*SOURCE_FILE_END*/ }, { j >= 0 && j < variables.size })
                mapping[i] = j
            }
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMulti.kt:169"/*SOURCE_FILE_END*/ }, { cnt == variables.size }, { "$cnt vs ${variables.size} ${variables.map { it }}" })
            val off = openConnections * variables.size
            for (i in 0 until variables.size) {
                buffer[off + mapping[i]] = conn.readDictionaryValueType()
            }
            if (buffer[off] == DictionaryValueHelper.nullValue) {
                conn.close()
                outputs[k]?.close()
            } else {
                connectionsIn[openConnections] = conn
                connectionsOut[openConnections] = outputs[k]
                connectionsMapping[openConnections] = mapping
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
                val connMinIn = connectionsIn[min]!!
                val connMinOut = connectionsOut[min]
                val connMinMapping = connectionsMapping[min]!!
                buffer.copyInto(iterator.buf, 0, off, off + variables.size)
                for (i in 0 until variables.size) {
                    buffer[off + connMinMapping[i]] = connMinIn.readDictionaryValueType()
                }
                if (buffer[off] == DictionaryValueHelper.nullValue) {
                    connMinIn.close()
                    connMinOut?.close()
                    val off2 = (openConnections - 1) * variables.size
                    if (off != off2) {
                        val connOtherIn = connectionsIn[openConnections - 1]!!
                        val connOtherOut = connectionsOut[openConnections - 1]
                        val connOtherMapping = connectionsMapping[openConnections - 1]!!
                        for (i in 0 until variables.size) {
                            buffer[off + connMinMapping[i]] = buffer[off2 + connOtherMapping[i]]
                        }
                        connectionsIn[min] = connectionsIn[openConnections - 1]
                        connectionsOut[min] = connectionsOut[openConnections - 1]
                        connectionsMapping[min] = connectionsMapping[openConnections - 1]
                    }
                    connectionsIn[openConnections - 1] = null
                    connectionsOut[openConnections - 1] = null
                    connectionsMapping[openConnections - 1] = null
                    openConnections--
                }
            }
            res
        }
        iterator.close = {
            for (i in 0 until openConnections) {
                connectionsIn[i]?.close()
                connectionsOut[i]?.close()
            }
        }
        return IteratorBundle(iterator)
    }
}
