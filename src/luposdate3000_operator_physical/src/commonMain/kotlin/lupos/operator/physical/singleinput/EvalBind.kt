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
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.SanityCheck
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
        val variablesLocal = (child.columns.keys + name).toList()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val localMap = mutableMapOf<String, ColumnIterator>()
        val columnsLocal = Array<ColumnIteratorQueue>(variablesLocal.size) { ColumnIteratorQueueEmpty() }
        var expression: () -> DictionaryValueType = { DictionaryValueHelper.errorValue }
        val columnsOut = Array<ColumnIteratorQueue>(variablesOut.size) { ColumnIteratorQueueEmpty() }
        fun expressionWrapper(): DictionaryValueType {
            var res = expression()
            if (res == DictionaryValueHelper.errorValue) {
                return DictionaryValueHelper.undefValue
            }
            return res
        }
        if (variablesLocal.size == 1 && variablesInCount == 0) {
            outMap[name] = ColumnIteratorRepeatValue(child.count(), expressionWrapper())
        } else {
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
                                        columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp)
                                    }
                                }
                            },
                            { ColumnIteratorQueueExt._close(this) }
                        )
                    }
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
        expression = value.evaluateID(IteratorBundle(localMap))
        return IteratorBundle(outMap)
    }
}
