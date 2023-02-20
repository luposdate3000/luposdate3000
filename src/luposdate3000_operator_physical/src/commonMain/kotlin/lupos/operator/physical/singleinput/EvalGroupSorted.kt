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

import lupos.operator.arithmetik.AOPAggregationBase
import lupos.operator.arithmetik.AOPBase
import lupos.operator.base.iterator.ColumnIteratorQueueEmpty
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.inline.ColumnIteratorQueueExt
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorQueue
import lupos.shared.operator.iterator.IteratorBundle

public object EvalGroupSorted {
    private fun getAggregations(node: IOPBase): MutableList<AOPAggregationBase> {
        val res = mutableListOf<AOPAggregationBase>()
        for (n in node.getChildren()) {
            res.addAll(getAggregations(n))
        }
        if (node is AOPAggregationBase) {
            res.add(node)
        }
        return res
    }

    public operator fun invoke(
        child: IteratorBundle,
        bindings: MutableList<Pair<String, AOPBase>>,
        projectedVariables: List<String>,
        keyColumnNames: Array<String>,
    ): IteratorBundle {
        val localVariables = child.names
        val outMap = mutableMapOf<String, ColumnIterator>()
        val aggregations = mutableListOf<AOPAggregationBase>()
        for (b in bindings) {
            aggregations.addAll(getAggregations(b.second))
        }
        val keyColumns: Array<ColumnIterator> = Array(keyColumnNames.size) { child.columns[keyColumnNames[it]]!! }
        val valueColumnNames = mutableListOf<String>()
        for (name in localVariables) {
            if (!keyColumnNames.contains(name)) {
                valueColumnNames.add(name)
            }
        }
        val valueColumns = Array(valueColumnNames.size) { child.columns[valueColumnNames[it]]!! }
        var currentKey = DictionaryValueTypeArray(keyColumnNames.size) { DictionaryValueHelper.undefValue }
        var nextKey: DictionaryValueTypeArray? = null
        // first row ->
        var emptyResult = false
        for (columnIndex in keyColumnNames.indices) {
            val value = keyColumns[columnIndex].next()
            if (value == DictionaryValueHelper.nullValue) {
                for (element in keyColumns) {
                    element.close()
                }
                for (element in valueColumns) {
                    element.close()
                }
                emptyResult = true
                break
            }
            currentKey[columnIndex] = value
        }
        if (emptyResult) {
            // there is no first row
            for (v in keyColumnNames) {
                if (projectedVariables.contains(v)) {
                    outMap[v] = ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue)
                }
            }
            for ((first) in bindings) {
                if (projectedVariables.contains(first)) {
                    outMap[first] = ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue)
                }
            }
        } else {
            val localMap = mutableMapOf<String, ColumnIterator>()
            var localRowColumns = Array(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
            for (columnIndex in keyColumnNames.indices) {
                val tmp = ColumnIteratorQueueEmpty()
                tmp.tmp = currentKey[columnIndex]
                localMap[keyColumnNames[columnIndex]] = tmp
            }
            for (columnIndex in 0 until valueColumnNames.size) {
                localMap[valueColumnNames[columnIndex]] = localRowColumns[columnIndex]
            }
            var localRowIterators = IteratorBundle(localMap)
            var localRowAggregates = Array(aggregations.size) {
                val tmp = aggregations[it].createIterator(localRowIterators)
                localMap["#" + aggregations[it].uuid] = tmp
                tmp
            }
            for (columnIndex in 0 until valueColumnNames.size) {
                localRowColumns[columnIndex].tmp = valueColumns[columnIndex].next()
            }
            for (aggregate in localRowAggregates) {
                aggregate.evaluate()
            }
            // first row <-
            val output = mutableListOf<ColumnIteratorQueue>()
            for (outIndex in 0 until keyColumnNames.size + bindings.size) {
                val iterator = object : ColumnIteratorQueue() {
                    override /*suspend*/ fun close() {
                        __close()
                    }

                    /*suspend*/ @Suppress("NOTHING_TO_INLINE")
                    fun __close() {
                        if (label != 0) {
                            ColumnIteratorQueueExt._close(this)
                            for (element in keyColumns) {
                                element.close()
                            }
                            for (element in valueColumns) {
                                element.close()
                            }
                        }
                    }

                    override /*suspend*/ fun next(): DictionaryValueType {
                        return ColumnIteratorQueueExt.nextHelper(
                            this,
                            {
                                loop@ while (true) {
                                    var changedKey = false
                                    if (nextKey != null) {
                                        currentKey = nextKey!!
                                        nextKey = null
                                    }
                                    for (columnIndex in keyColumnNames.indices) {
                                        val value = keyColumns[columnIndex].next()
                                        if (value == DictionaryValueHelper.nullValue) {
                                            for (element in keyColumns) {
                                                element.close()
                                            }
                                            for (element in valueColumns) {
                                                element.close()
                                            }
                                            for (columnIndex2 in keyColumnNames.indices) {
                                                if (projectedVariables.contains(keyColumnNames[columnIndex2])) {
                                                    output[columnIndex2].queue.add(currentKey[columnIndex2])
                                                }
                                            }
                                            for (columnIndex2 in 0 until bindings.size) {
                                                if (projectedVariables.contains(bindings[columnIndex2].first)) {
                                                    output[columnIndex2 + keyColumnNames.size].queue.add(bindings[columnIndex2].second.evaluateID(localRowIterators)())
                                                }
                                            }
                                            for (outIndex2 in 0 until output.size) {
                                                ColumnIteratorQueueExt.closeOnEmptyQueue(output[outIndex2])
                                            }
                                            break@loop
                                        }
                                        if (nextKey != null) {
                                            nextKey!![columnIndex] = value
                                        } else if (currentKey[columnIndex] != value) {
                                            nextKey = DictionaryValueTypeArray(keyColumnNames.size) { currentKey[it] }
                                            nextKey!![columnIndex] = value
                                            changedKey = true
                                        }
                                    }
                                    if (changedKey) {
                                        for (columnIndex in keyColumnNames.indices) {
                                            if (projectedVariables.contains(keyColumnNames[columnIndex])) {
                                                output[columnIndex].queue.add(currentKey[columnIndex])
                                            }
                                        }
                                        for (columnIndex in 0 until bindings.size) {
                                            if (projectedVariables.contains(bindings[columnIndex].first)) {
                                                output[columnIndex + keyColumnNames.size].queue.add(bindings[columnIndex].second.evaluateID(localRowIterators)())
                                            }
                                        }
                                        localMap.clear()
                                        localRowColumns = Array(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
                                        for (columnIndex in keyColumnNames.indices) {
                                            val tmp = ColumnIteratorQueueEmpty()
                                            tmp.tmp = currentKey[columnIndex]
                                            localMap[keyColumnNames[columnIndex]] = tmp
                                        }
                                        for (columnIndex in 0 until valueColumnNames.size) {
                                            localMap[valueColumnNames[columnIndex]] = localRowColumns[columnIndex]
                                        }
                                        localRowIterators = IteratorBundle(localMap)
                                        localRowAggregates = Array(aggregations.size) {
                                            val tmp = aggregations[it].createIterator(localRowIterators)
                                            localMap["#" + aggregations[it].uuid] = tmp
                                            tmp
                                        }
                                    }
                                    for (columnIndex in 0 until valueColumnNames.size) {
                                        localRowColumns[columnIndex].tmp = valueColumns[columnIndex].next()
                                    }
                                    for (aggregate in localRowAggregates) {
                                        aggregate.evaluate()
                                    }
                                    if (changedKey) {
                                        break@loop
                                    }
                                }
                            },
                            { __close() }
                        )
                    }
                }
                output.add(iterator)
            }
            for (columnIndex in keyColumnNames.indices) {
                if (projectedVariables.contains(keyColumnNames[columnIndex])) {
                    outMap[keyColumnNames[columnIndex]] = output[columnIndex]
                }
            }
            for (columnIndex in 0 until bindings.size) {
                if (projectedVariables.contains(bindings[columnIndex].first)) {
                    outMap[bindings[columnIndex].first] = output[columnIndex + keyColumnNames.size]
                }
            }
        }
        return IteratorBundle(outMap)
    }
}
