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
    @JvmField public var partitionID: Int,
    child: IOPBase,
    private val inputs: Array<IMyInputStream>,
    private val outputs: Array<IMyOutputStream?> = Array(inputs.size) { null },
    private val hosts: Map<Int, String>,
    @JvmField public val partitionVariable: String,
) : APOPDistributed(
    query,
    projectedVariables,
    EOperatorIDExt.POPDistributedReceiveMultiOrderedID,
    "POPDistributedReceiveMultiOrdered",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY,
) {
    public companion object {
        internal fun toXMLElementInternal(partitionID: Int, partial: Boolean, isRoot: Boolean, hosts: Map<Int, String>, partitionVariable: String) = toXMLElementHelper10("POPDistributedReceiveMultiOrdered", partitionID, partial, isRoot, hosts, partitionVariable)
        public operator fun invoke(
            query: IQuery,
            projectedVariables: List<String>,
            partitionID: Int,
            child: IOPBase,
            hosts: Map<Int, String>,
            partitionVariable: String,
        ): POPDistributedReceiveMultiOrdered {
            val handler = query.getInstance().communicationHandler!!
            val inputs = mutableListOf<IMyInputStream>()
            val outputs = mutableListOf<IMyOutputStream?>()
            for ((k, v) in hosts) {
                val conn = handler.openConnection(v, "/distributed/query/execute", mapOf("key" to "$k", "dictionaryURL" to query.getDictionaryUrl()!!), query.getTransactionID().toInt())
                inputs.add(conn.first)
                outputs.add(conn.second)
            }
            return POPDistributedReceiveMultiOrdered(query, projectedVariables, partitionID, child, inputs.toTypedArray(), outputs.toTypedArray(), hosts, partitionVariable)
        }
    }
    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMultiOrdered.kt:73"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    override fun getPartitionCount(variable: String): Int = 1
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean, partition: Map<String, Int>): XMLElement = toXMLElementHelperAddBase(partition, partial, true, toXMLElementInternal(partitionID, partial, true, hosts, partitionVariable))
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: Map<String, Int>): XMLElement = toXMLElementHelperAddBase(partition, partial, false, toXMLElementInternal(partitionID, partial, false, hosts, partitionVariable))
    override fun cloneOP(): IOPBase = POPDistributedReceiveMultiOrdered(query, projectedVariables, partitionID, children[0].cloneOP(), inputs, outputs, hosts, partitionVariable)
    override fun equals(other: Any?): Boolean = other is POPDistributedReceiveMultiOrdered && children[0] == other.children[0]

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = mutableListOf<String>()
        variables.addAll(projectedVariables)
        if (partitionVariable != "_" && variables.contains(partitionVariable)) {
            variables.remove(partitionVariable)
            variables.add(0, partitionVariable)
        }
        val buffer = DictionaryValueTypeArray(inputs.size * variables.size)
        val connections = Array<MyConnection?>(inputs.size) { null }
        var openConnections = 0
        for (kk in 0 until inputs.size) {
            val mapping = IntArray(variables.size)
            val cnt = inputs[kk].readInt()
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMultiOrdered.kt:95"/*SOURCE_FILE_END*/ }, { cnt == variables.size }, { "$cnt vs ${variables.size}" })
            for (i in 0 until variables.size) {
                val len = inputs[kk].readInt()
                val buf = ByteArray(len)
                inputs[kk].read(buf, len)
                val name = buf.decodeToString()
                val j = variables.indexOf(name)
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMultiOrdered.kt:102"/*SOURCE_FILE_END*/ }, { j >= 0 && j < variables.size })
                mapping[i] = j
            }
            val off = openConnections * variables.size
            for (i in 0 until variables.size) {
                buffer[off + mapping[i]] = inputs[kk].readDictionaryValueType()
            }
            if (buffer[off] == DictionaryValueHelper.nullValue) {
                inputs[kk].close()
                outputs[kk]?.close()
            } else {
                connections[openConnections] = MyConnection(inputs[kk], outputs[kk], mapping)
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
                    connMin.output?.close()
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
