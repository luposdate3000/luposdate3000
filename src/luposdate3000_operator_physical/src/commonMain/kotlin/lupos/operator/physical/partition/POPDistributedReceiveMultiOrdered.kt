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
import lupos.shared.PartitionHelper
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
    @JvmField public val orderedBy: List<String>,
) : APOPDistributed(
    query,
    projectedVariables,
    EOperatorIDExt.POPDistributedReceiveMultiOrderedID,
    "POPDistributedReceiveMultiOrdered",
    arrayOf(child),
    ESortPriorityExt.PREVENT_ANY,
) {
    public companion object {
        internal fun toXMLElementInternal(partitionID: Int, partial: Boolean, isRoot: Boolean, hosts: Map<Int, String>, orderedBy: List<String>) = toXMLElementHelper10("POPDistributedReceiveMultiOrdered", partitionID, partial, isRoot, hosts, orderedBy)
        public operator fun invoke(
            query: IQuery,
            projectedVariables: List<String>,
            partitionID: Int,
            child: IOPBase,
            hosts: Map<Int, String>,
            orderedBy: List<String>,
        ): POPDistributedReceiveMultiOrdered {
            val handler = query.getInstance().communicationHandler!!
            val inputs = mutableListOf<IMyInputStream>()
            val outputs = mutableListOf<IMyOutputStream?>()
            for ((k, v) in hosts) {
                val conn = handler.openConnection(v, "/distributed/query/execute", mapOf("key" to "$k", "dictionaryURL" to query.getDictionaryUrl()!!), query.getTransactionID().toInt())
                inputs.add(conn.first)
                outputs.add(conn.second)
            }
            return POPDistributedReceiveMultiOrdered(query, projectedVariables, partitionID, child, inputs.toTypedArray(), outputs.toTypedArray(), hosts, orderedBy)
        }
    }

    init {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMultiOrdered.kt:75"/*SOURCE_FILE_END*/ }, { projectedVariables.isNotEmpty() })
    }

    override fun getPartitionCount(variable: String): Int = 1
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelperAddBase(partition, partial, true, toXMLElementInternal(partitionID, partial, true, hosts, orderedBy))
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement = toXMLElementHelperAddBase(partition, partial, false, toXMLElementInternal(partitionID, partial, false, hosts, orderedBy))
    override fun cloneOP(): IOPBase = POPDistributedReceiveMultiOrdered(query, projectedVariables, partitionID, children[0].cloneOP(), inputs, outputs, hosts, orderedBy)
    override fun equals(other: Any?): Boolean = other is POPDistributedReceiveMultiOrdered && children[0] == other.children[0]

    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = mutableListOf<String>()
        variables.addAll(projectedVariables)
        for (i in 0 until orderedBy.size) {
            val v = orderedBy[orderedBy.size - i - 1]
            if (variables.contains(v)) {
                variables.remove(v)
                variables.add(0, v)
            }
        }
// val sortColumns = IntArray(mySortPriority.size) { variables.indexOf(mySortPriority[it].variableName) }
        val openInputs = Array<IMyInputStream?>(inputs.size) { inputs[it] }
        val openOutputs = Array<IMyOutputStream?>(inputs.size) { outputs[it] }
        var openConnections = BooleanArray(inputs.size) { true }
        val openInputMappings = IntArray(inputs.size * variables.size)
        val buffer = DictionaryValueTypeArray(inputs.size * variables.size)
        val debugbuffer = DictionaryValueTypeArray(inputs.size * variables.size)
        println("POPDistributedReceiveMultiOrdered $uuid columns $variables")
        for (kk in 0 until inputs.size) {
            val off = kk * variables.size
            val cnt = openInputs[kk]!!.readInt()
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMultiOrdered.kt:106"/*SOURCE_FILE_END*/ },
                { cnt == variables.size },
                { "$cnt vs ${variables.size}" }
            )
            for (i in 0 until variables.size) {
                val len = openInputs[kk]!!.readInt()
                val buf = ByteArray(len)
                openInputs[kk]!!.read(buf, len)
                val name = buf.decodeToString()
                val j = variables.indexOf(name)
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/POPDistributedReceiveMultiOrdered.kt:116"/*SOURCE_FILE_END*/ }, { j >= 0 && j < variables.size })
                openInputMappings[off + i] = off + j
            }
            for (i in 0 until variables.size) {
                buffer[openInputMappings[off + i]] = inputs[kk].readDictionaryValueType()
            }
            var debugtmp = ""
            for (i in 0 until variables.size) {
                debugtmp = debugtmp + ",${buffer[off + i]}"
            }
            println("POPDistributedReceiveMultiOrdered $uuid row $kk $debugtmp")
            if (buffer[off] == DictionaryValueHelper.nullValue) {
                openInputs[kk]!!.close()
                openOutputs[kk]?.close()
                openInputs[kk] = null
                openOutputs[kk] = null
            } else {
            }
        }
        val iterator = RowIterator()
        iterator.columns = variables.toTypedArray()
        iterator.buf = DictionaryValueTypeArray(variables.size)
        iterator.next = {
            var res = -1
            for (ii in 0 until openInputs.size) {
                if (openInputs[ii] != null) {
                    res = 0
                    var min = ii
                    loop@ for (i in min + 1 until openInputs.size) {
                        if (openInputs[i] != null) {
                            for (idx in 0 until orderedBy.size) {
                                val c = buffer[idx + i * variables.size]
                                val d = buffer[idx + min * variables.size]
                                if (d > c) {
                                    min = i
                                    continue@loop
                                } else if (d < c) {
                                    continue@loop
                                }
                            }
                        }
                    }
                    val off = min * variables.size
                    for (i in 0 until variables.size) {
                        iterator.buf[i] = buffer[off + i]
                    }
                    for (i in 0 until variables.size) {
                        buffer[openInputMappings[off + i]] = openInputs[min]!!.readDictionaryValueType()
                    }
                    var debugtmp = ""
                    for (i in 0 until variables.size) {
                        debugtmp = debugtmp + ",${buffer[off + i]}"
                    }
                    println("POPDistributedReceiveMultiOrdered $uuid row $min $debugtmp")
                    if (buffer[off] != DictionaryValueHelper.nullValue) {
                        for (idx in 0 until orderedBy.size) {
                            val a = buffer[idx + min * variables.size]
                            val b = debugbuffer[idx + min * variables.size]
                            if (a > b) {
                                break
                            } else {
                                if (a < b) {
                                    TODO("not sorted input can not be fixed here $min")
                                }
                            }
                        }
                        for (i in 0 until variables.size) {
                            debugbuffer[off + i] = buffer[off + i]
                        }
                    }
                    if (buffer[off] == DictionaryValueHelper.nullValue) {
                        openInputs[min]!!.close()
                        openOutputs[min]?.close()
                        openInputs[min] = null
                        openOutputs[min] = null
                    }
                    break
                }
            }
            res
        }
        iterator.close = {
            for (i in 0 until inputs.size) {
                openInputs[i]?.close()
                openOutputs[i]?.close()
                openInputs[i] = null
                openOutputs[i] = null
            }
        }
        return IteratorBundle(iterator)
    }
}
