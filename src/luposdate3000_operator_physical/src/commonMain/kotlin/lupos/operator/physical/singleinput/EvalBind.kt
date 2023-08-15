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
package lupos.operator.physical.singleinput

import lupos.operator.arithmetik.AOPBase
import lupos.operator.base.iterator.ColumnIteratorQueueEmpty
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.inline.ColumnIteratorQueueExt
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorQueue
import lupos.shared.operator.iterator.IteratorBundle

public object EvalBind {
    public operator fun invoke(
        child: IteratorBundle,
        variablesOut: List<String>,
        name: String,
        value: AOPBase,
    ): IteratorBundle {
        val variablesInCount = child.names.size
        val localMap = mutableMapOf<String, ColumnIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        var expression: () -> DictionaryValueType = { DictionaryValueHelper.errorValue }
        fun expressionWrapper(): DictionaryValueType {
            var res = expression()
            if (res == DictionaryValueHelper.errorValue) {
                return DictionaryValueHelper.undefValue
            }
            return res
        }
        println("EvalBind .. " + variablesInCount)
        if (variablesInCount == 0) {
            outMap[name] = object : ColumnIteratorQueue() {
                var ctr = child.count()
                override /*suspend*/ fun close() {
                    ColumnIteratorQueueExt._close(this)
                }

                override /*suspend*/ fun next(): DictionaryValueType {
                    return ColumnIteratorQueueExt.nextHelper(
                        this,
                        {
                            if (ctr > 0) {
                                ctr = ctr - 1
                                queue.add(expressionWrapper())
                            }
                        },
                        { ColumnIteratorQueueExt._close(this) },
                    )
                }
            }
        } else {
            println("EvalBind .. with input data")
            val variablesLocal = (child.columns.keys + name).toList()
            val columnsLocal = Array<ColumnIteratorQueue>(variablesLocal.size) { ColumnIteratorQueueEmpty() }
            val columnsOut = Array<ColumnIteratorQueue>(variablesOut.size) { ColumnIteratorQueueEmpty() }
            var boundIndex = -1
            for (variableIndex in variablesLocal.indices) {
                if (variablesLocal[variableIndex] == name) {
                    boundIndex = variableIndex
                }
            }
            val columnsIn = Array(variablesLocal.size) { child.columns[variablesLocal[it]] }
            for (variableIndex in variablesLocal.indices) {
                columnsLocal[variableIndex] = object : ColumnIteratorQueue() {
                    override /*suspend*/ fun close() {
                        ColumnIteratorQueueExt._close(this)
                    }

                    override /*suspend*/ fun next(): DictionaryValueType {
                        return ColumnIteratorQueueExt.nextHelper(
                            this,
                            {
                                var done = false
                                for (variableIndex2 in variablesLocal.indices) {
                                    if (boundIndex != variableIndex2) {
                                        val value2 = columnsIn[variableIndex2]!!.next()
                                        println("EvalBind .. next[$variableIndex2] = $value2")
                                        if (value2 == DictionaryValueHelper.nullValue) {
                                            for (variableIndex3 in 0 until variablesLocal.size) {
                                                ColumnIteratorQueueExt.closeOnEmptyQueue(columnsLocal[variableIndex3])
                                            }
                                            for (closeIndex in variablesLocal.indices) {
                                                if (boundIndex != closeIndex) {
                                                    columnsIn[closeIndex]!!.close()
                                                }
                                            }
                                            done = true
                                            break
                                        }
// point each iterator to the current value
                                        columnsLocal[variableIndex2].tmp = value2
                                    }
                                }
                                if (!done) {
                                    columnsLocal[boundIndex].tmp = expressionWrapper()
                                    for (variableIndex2 in columnsOut.indices) {
                                        println("EvalBind .. assign[$variableIndex2] = ${columnsOut[variableIndex2].tmp}")
                                        columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp)
                                    }
                                }
                            },
                            { ColumnIteratorQueueExt._close(this) },
                        )
                    }
                }
            }
            for (variableIndex in variablesLocal.indices) {
                localMap[variablesLocal[variableIndex]] = columnsLocal[variableIndex]
                if (variablesOut.contains(variablesLocal[variableIndex])) {
                    outMap[variablesLocal[variableIndex]] = columnsLocal[variableIndex]
                }
            }
            for (it in variablesOut.indices) {
                columnsOut[it] = localMap[variablesOut[it]] as ColumnIteratorQueue
            }
        }
        expression = value.evaluateID(IteratorBundle(localMap))
        println("EvalBind .. " + outMap.keys)
        return IteratorBundle(outMap)
    }
}
