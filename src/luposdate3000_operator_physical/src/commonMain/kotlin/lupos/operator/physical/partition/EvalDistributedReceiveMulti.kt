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
import lupos.shared.DateHelperRelative
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator

public object EvalDistributedReceiveMulti {
    internal var debugCounter = 0
    public operator fun invoke(
        inputs: Array<IMyInputStream>,
        outputs: Array<IMyOutputStream?>,
        timeoutInMs: Long,
    ): IteratorBundle {
        val startTime = DateHelperRelative.markNow()
        val variables = mutableListOf<String>()
        val connectionsIn = Array<IMyInputStream?>(inputs.size) { null }
        val connectionsMapping = Array<IntArray?>(inputs.size) { null }
        val connectionsOut = Array<IMyOutputStream?>(inputs.size) { null }

        var openConnections = 0
        var buffer = DictionaryValueTypeArray(inputs.size * 1)
        for (k in 0 until inputs.size) {
            val conn = inputs[k]
            val cnt = conn.readInt()
            val mapping = IntArray(cnt)
            for (i in 0 until cnt) {
                val len = conn.readInt()
                val buf = ByteArray(len)
                conn.read(buf, len)
                val name = buf.decodeToString()
                var j = variables.indexOf(name)
                if (j < 0) {
                    j = variables.size
                    variables.add(name)
                }
                mapping[i] = j
            }
            if (k == 0) {
                buffer = DictionaryValueTypeArray(inputs.size * variables.size)
            }
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
        val debugID = debugCounter++
        iterator.next = {
            if (!(timeoutInMs <= 0 || DateHelperRelative.elapsedMilliSeconds(startTime) < timeoutInMs)) {
                TODO("timeout")
            }
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
// println("EvalDistributedReceiveMulti $debugID ${variables.toList()} $res ${iterator.buf.toList()}")
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
