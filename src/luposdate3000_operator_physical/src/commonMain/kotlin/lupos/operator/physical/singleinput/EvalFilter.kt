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
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.SanityCheck
import lupos.shared.inline.ColumnIteratorQueueExt
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorQueue
import lupos.shared.operator.iterator.IteratorBundle

public object EvalFilter {
    public operator fun invoke(
        child: IteratorBundle,
        variablesOut: List<String>,
        filterExpression: AOPBase,
    ): IteratorBundle {
        val variables = child.names
        // TODO not-equal shortcut during evaluation based on integer-ids
        val outMap = mutableMapOf<String, ColumnIterator>()
        val localMap = mutableMapOf<String, ColumnIterator>()
        var expression: () -> Boolean = { true }
        val res: IteratorBundle
        val columnsIn = Array(variables.size) { child.columns[variables[it]] }
        val columnsOut = mutableListOf<ColumnIteratorQueue>()
        val columnsLocal = mutableListOf<ColumnIteratorQueue>()
        for (i in variables.indices) {
            columnsLocal.add(object : ColumnIteratorQueue() {
                override /*suspend*/ fun close() {
                    __close()
                }

                /*suspend*/ @Suppress("NOTHING_TO_INLINE")
                fun __close() {
                    if (label != 0) {
                        ColumnIteratorQueueExt._close(this)
                        for (v in child.columns.values) {
                            v.close()
                        }
                    }
                }

                override /*suspend*/ fun next(): DictionaryValueType {
                    return ColumnIteratorQueueExt.nextHelper(
                        this,
                        {
                            var done = false
                            while (!done) {
                                for (variableIndex2 in variables.indices) {
                                    columnsLocal[variableIndex2].tmp = columnsIn[variableIndex2]!!.next()
                                    // point each iterator to the current value
                                    if (columnsLocal[variableIndex2].tmp == DictionaryValueHelper.nullValue) {
                                        if (SanityCheck.enabled) { if (!(variableIndex2 == 0)) { throw Exception("SanityCheck failed") } }
                                        for (v in child.columns.values) {
                                            v.close()
                                        }
                                        for (variableIndex3 in 0 until variables.size) {
                                            ColumnIteratorQueueExt.closeOnEmptyQueue(columnsLocal[variableIndex3])
                                        }
                                        done = true
                                        break
                                    }
                                }
                                if (!done) {
                                    // evaluate
                                    if (expression()) {
                                        // accept/deny row in each iterator
                                        for (variableIndex2 in variablesOut.indices) {
                                            columnsOut[variableIndex2].queue.add(columnsOut[variableIndex2].tmp)
                                        }
                                        done = true
                                    }
                                }
                            }
                        },
                        { __close() }
                    )
                }
            })
        }
        for (variableIndex in variables.indices) {
            if (variablesOut.contains(variables[variableIndex])) {
                outMap[variables[variableIndex]] = columnsLocal[variableIndex]
            }
            localMap[variables[variableIndex]] = columnsLocal[variableIndex]
        }
        val resLocal: IteratorBundle = if (variables.isNotEmpty()) {
            IteratorBundle(localMap)
        } else {
            IteratorBundle(0)
        }
        for (element in variablesOut) {
            columnsOut.add(resLocal.columns[element]!! as ColumnIteratorQueue)
        }
        expression = filterExpression.evaluateAsBoolean(resLocal)
        if (variablesOut.isEmpty()) {
            if (variables.isEmpty()) {
                res = if (expression()) {
                    child
                } else {
                    object : IteratorBundle(0) {
                        override /*suspend*/ fun hasNext2(): Boolean {
                            return false
                        }
                    }
                }
            } else {
                res = object : IteratorBundle(0) {
                    override /*suspend*/ fun hasNext2Close() {
                        for (v in child.columns.values) {
                            v.close()
                        }
                    }

                    override /*suspend*/ fun hasNext2(): Boolean {
                        var res2 = false
                        var done = false
                        while (!done) {
                            for (variableIndex2 in variables.indices) {
                                columnsLocal[variableIndex2].tmp = columnsIn[variableIndex2]!!.next()
                                // point each iterator to the current value
                                if (columnsLocal[variableIndex2].tmp == DictionaryValueHelper.nullValue) {
                                    for (v in child.columns.values) {
                                        v.close()
                                    }
                                    if (SanityCheck.enabled) { if (!(variableIndex2 == 0)) { throw Exception("SanityCheck failed") } }
                                    for (variableIndex3 in 0 until variables.size) {
                                        ColumnIteratorQueueExt.closeOnEmptyQueue(columnsLocal[variableIndex3])
                                    }
                                    done = true
                                    break
                                }
                            }
                            if (!done) {
                                // evaluate
                                if (expression()) {
                                    // accept/deny row in each iterator
                                    res2 = true
                                }
                            }
                        }
                        return res2
                    }
                }
            }
        } else {
            res = IteratorBundle(outMap)
        }
        return res
    }
}
