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

public object EvalDistributedReceiveMultiOrdered {
    internal var debugCounter = 0
    public operator fun invoke(
        inputs: Array<IMyInputStream>,
        outputs: Array<IMyOutputStream?>,
        orderedBy: List<String>,
        variablesOut: List<String>,
        timeoutInMs: Long,
    ): IteratorBundle {
        val startTime = DateHelperRelative.markNow()
        val variables = mutableListOf<String>()
        variables.addAll(variablesOut)
        for (i in 0 until orderedBy.size) {
            val v = orderedBy[orderedBy.size - i - 1]
            if (variables.contains(v)) {
                variables.remove(v)
                variables.add(0, v)
            }
        }
        val openInputs = Array<IMyInputStream?>(inputs.size) { inputs[it] }
        val openOutputs = Array<IMyOutputStream?>(inputs.size) { outputs[it] }
        val openInputMappings = IntArray(inputs.size * variables.size)
        val buffer = DictionaryValueTypeArray(inputs.size * variables.size)
        val debugbuffer = DictionaryValueTypeArray(inputs.size * variables.size)
//        println("POPDistributedReceiveMultiOrdered $uuid columns $variables")
        for (kk in 0 until inputs.size) {
            val off = kk * variables.size
            val cnt = openInputs[kk]!!.readInt()
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalDistributedReceiveMultiOrdered.kt:56"/*SOURCE_FILE_END*/ },
                { cnt == variables.size },
                { "$cnt vs ${variables.size}" }
            )
            for (i in 0 until variables.size) {
                val len = openInputs[kk]!!.readInt()
                val buf = ByteArray(len)
                openInputs[kk]!!.read(buf, len)
                val name = buf.decodeToString()
                val j = variables.indexOf(name)
if(SanityCheck.enabled){if(!( j >= 0 && j < variables.size )){throw Exception("SanityCheck failed")}}
                openInputMappings[off + i] = off + j
            }
            for (i in 0 until variables.size) {
                buffer[openInputMappings[off + i]] = inputs[kk].readDictionaryValueType()
            }
            // var debugtmp = ""
            // for (i in 0 until variables.size) {
            //     debugtmp = debugtmp + ",${buffer[off + i]}"
            // }
            // println("POPDistributedReceiveMultiOrdered $uuid row $kk $debugtmp")
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
        val debugID = debugCounter++
        iterator.buf = DictionaryValueTypeArray(variables.size)
        iterator.next = {
            if (!(timeoutInMs <= 0 || DateHelperRelative.elapsedMilliSeconds(startTime) < timeoutInMs)) {
                TODO("timeout")
            }
            var res = -1
            for (ii in 0 until openInputs.size) {
                if (openInputs[ii] != null) { // looking for first non null input
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
                    SanityCheck(
                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/partition/EvalDistributedReceiveMultiOrdered.kt:120"/*SOURCE_FILE_END*/ },
                        {
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
                        }
                    )
                    if (buffer[off] == DictionaryValueHelper.nullValue) {
                        openInputs[min]!!.close()
                        openOutputs[min]?.close()
                        openInputs[min] = null
                        openOutputs[min] = null
                    }
                    break
                }
            }
// println("EvalDistributedReceiveMultiOrdered $debugID ${variables.toList()} $res ${iterator.buf.toList()}")
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
