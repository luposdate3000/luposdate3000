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
package lupos.simulator_db.luposdate3000
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IMyInputStream
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

// http://blog.pronghorn.tech/optimizing-suspending-functions-in-kotlin/
public class MySimulatorPOPDistributedReceiveMulti public constructor(
    query: IQuery,
    projectedVariables: List<String>,
    @JvmField public val partitionVariable: String,
    @JvmField public var partitionCount: Int,
    @JvmField public var partitionID: Int,
    child: IOPBase,
    private val inputs: Array<IMyInputStream>,
) : POPBase(query, projectedVariables, EOperatorIDExt.POPDistributedReceiveMultiID, "POPDistributedReceiveMulti", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    init {
        SanityCheck.check { projectedVariables.size > 0 }
    }

    override fun getPartitionCount(variable: String): Int = TODO()
    override /*suspend*/ fun toXMLElementRoot(partial: Boolean): XMLElement = TODO()
    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = TODO()
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

    override fun cloneOP(): IOPBase = TODO()
    override fun toSparql(): String = TODO()
    override fun equals(other: Any?): Boolean = TODO()
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = mutableListOf<String>()
        variables.addAll(projectedVariables)
        if (partitionVariable != "_" && variables.contains(partitionVariable)) {
            variables.remove(partitionVariable)
            variables.add(0, partitionVariable)
        }
        var buffer = DictionaryValueTypeArray(partitionCount * variables.size)
        var connections = Array<Pair<IMyInputStream, IntArray>?>(partitionCount) { null }
        var openConnections = 0
        SanityCheck.check { inputs.size == partitionCount }
        val handler = query.getInstance().communicationHandler!!
        val allConnections = inputs
        for (k in 0 until allConnections.size) {
            val conn = allConnections[k]!!
            val cnt = conn.readInt()
            var mapping = IntArray(cnt)
            for (i in 0 until cnt) {
                val len = conn.readInt()
                val buf = ByteArray(len)
                conn.read(buf, len)
                val name = buf.decodeToString()
                val j = variables.indexOf(name)
                SanityCheck.check { j >= 0 && j < variables.size }
                mapping[i] = j
            }
            SanityCheck.check({ cnt == variables.size }, { "$cnt vs ${variables.size} ${variables.map { it }}" })
            val off = openConnections * variables.size
            for (i in 0 until variables.size) {
                buffer[off + mapping[i]] = conn.readDictionaryValueType()
            }
            if (buffer[off] == DictionaryValueHelper.nullValue) {
                conn.close()
            } else {
                connections[openConnections] = conn to mapping
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
                    buffer[off + connMin.second[i]] = connMin.first.readDictionaryValueType()
                }
                if (buffer[off] == DictionaryValueHelper.nullValue) {
                    connMin.first.close()
                    val off2 = (openConnections - 1) * variables.size
                    if (off != off2) {
                        val connOther = connections[openConnections - 1]!!
                        for (i in 0 until variables.size) {
                            buffer[off + connMin.second[i]] = buffer[off2 + connOther.second[i]]
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
                connections[i]?.first?.close()
            }
        }
        return IteratorBundle(iterator)
    }
}
