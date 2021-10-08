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
import com.ionspin.kotlin.bignum.integer.BigInteger
import lupos.operator.arithmetik.AOPAggregationBase
import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.arithmetik.singleinput.AOPAggregationCOUNT
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.operator.base.iterator.ColumnIteratorQueueEmpty
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.operator.physical.MapKey
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.GroupByDuplicateColumnException
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.ColumnIteratorQueueExt
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorQueue
import lupos.shared.operator.iterator.IteratorBundle
public object EvalGroup {
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
        by: List<AOPVariable>,
        sortPriority: MutableList<SortHelper>,
        dict: IDictionary,
    ): IteratorBundle {
        val buffer = ByteArrayWrapper()
        val localVariables = child.columns.keys.toList()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val aggregations = mutableListOf<AOPAggregationBase>()
        for (b in bindings) {
            aggregations.addAll(getAggregations(b.second))
        }
        val keyColumnNames = Array(by.size) { by[it].name }
        if (keyColumnNames.size != keyColumnNames.distinct().size) {
            throw GroupByDuplicateColumnException()
        }
        val keyColumns: Array<ColumnIterator> = Array(keyColumnNames.size) { child.columns[keyColumnNames[it]]!! }
        val valueColumnNames = mutableListOf<String>()
        for (name in localVariables) {
            if (!keyColumnNames.contains(name)) {
                valueColumnNames.add(name)
            }
        }
        val valueColumns = Array(valueColumnNames.size) { child.columns[valueColumnNames[it]]!! }
        if (keyColumnNames.isEmpty()) {
            val localMap = mutableMapOf<String, ColumnIterator>()
            val localColumns = Array<ColumnIteratorQueue>(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
            for (columnIndex in 0 until valueColumnNames.size) {
                localMap[valueColumnNames[columnIndex]] = localColumns[columnIndex]
            }
            val row = IteratorBundle(localMap)
            val localAggregations = Array(aggregations.size) {
                val tmp = aggregations[it].createIterator(row)
                localMap["#" + aggregations[it].uuid] = tmp
                tmp
            }
            val localRow = POPGroup_Row(row, localAggregations, localColumns)
            if (valueColumnNames.size == 0) {
                for (i in 0 until child.count()) {
                    for (aggregate in localRow.aggregates) {
                        aggregate.evaluate()
                    }
                }
            } else {
                loop2@ while (true) {
                    for (columnIndex in 0 until valueColumnNames.size) {
                        val value = valueColumns[columnIndex].next()
                        if (value == DictionaryValueHelper.nullValue) {
                            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/EvalGroup.kt:102"/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
                            for (closeIndex in 0 until valueColumnNames.size) {
                                valueColumns[closeIndex].close()
                            }
                            break@loop2
                        }
                        localRow.columns[columnIndex].tmp = value
                    }
                    for (aggregate in localRow.aggregates) {
                        aggregate.evaluate()
                    }
                }
            }
            for (columnIndex in 0 until bindings.size) {
                val columnName = bindings[columnIndex].first
                if (projectedVariables.contains(columnName)) {
                    val value = bindings[columnIndex].second.evaluateID(localRow.iterators)()
                    outMap[columnName] = ColumnIteratorRepeatValue(1, value)
                }
            }
        } else {
            val tmpSortPriority = sortPriority.map { it.variableName }
            var canUseSortedInput = true
            if ((!localVariables.containsAll(keyColumnNames.toMutableList())) || (tmpSortPriority.size < keyColumnNames.size)) {
                canUseSortedInput = false
            } else {
                for (element in keyColumnNames) {
                    if (!tmpSortPriority.contains(element)) {
                        canUseSortedInput = false
                        break
                    }
                }
            }
            if (canUseSortedInput) {
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
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/EvalGroup.kt:149"/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
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

                            @Suppress("NOTHING_TO_INLINE")
                            /*suspend*/ inline fun __close() {
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
                                                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/EvalGroup.kt:230"/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
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
            } else {
                if (bindings.size == 1 && bindings.toList().first().second is AOPAggregationCOUNT &&
// simplicity ->
                    keyColumnNames.size == 1 && valueColumnNames.size == 0
// <- simplicity
                ) {
                    val iterator = keyColumns[0]
                    val map = mutableMapOf<DictionaryValueType, Int>()
                    while (true) {
                        val value = iterator.next()
                        if (value == DictionaryValueHelper.nullValue) {
                            iterator.close()
                            break
                        }
                        val v = map[value]
                        if (v == null) {
                            map[value] = 1
                        } else {
                            map[value] = v + 1
                        }
                    }
                    val arrK = DictionaryValueTypeArray(map.size)
                    val arrV = DictionaryValueTypeArray(map.size)
                    var i = 0
                    for ((k, v) in map) {
                        arrK[i] = k
                        DictionaryHelper.integerToByteArray(buffer, BigInteger(v))
                        arrV[i] = dict.createValue(buffer)
                        i++
                    }
                    outMap[keyColumnNames[0]] = ColumnIteratorMultiValue(arrK, arrK.size)
                    outMap[bindings.toList().first().first] = ColumnIteratorMultiValue(arrV, arrV.size)
                } else {
                    val map = mutableMapOf<MapKey, POPGroup_Row>()
                    loop@ while (true) {
                        val currentKey = DictionaryValueTypeArray(keyColumnNames.size) { DictionaryValueHelper.undefValue }
                        for (columnIndex in keyColumnNames.indices) {
                            val value = keyColumns[columnIndex].next()
                            if (value == DictionaryValueHelper.nullValue) {
                                for (element in keyColumns) {
                                    element.close()
                                }
                                for (element in valueColumns) {
                                    element.close()
                                }
                                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/EvalGroup.kt:355"/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
                                break@loop
                            }
                            currentKey[columnIndex] = value
                        }
                        val key = MapKey(currentKey)
                        var localRow = map[key]
                        if (localRow == null) {
                            val localMap = mutableMapOf<String, ColumnIterator>()
                            val localColumns = Array<ColumnIteratorQueue>(valueColumnNames.size) { ColumnIteratorQueueEmpty() }
                            for (columnIndex in keyColumnNames.indices) {
                                val tmp = ColumnIteratorQueueEmpty()
                                tmp.tmp = currentKey[columnIndex]
                                localMap[keyColumnNames[columnIndex]] = tmp
                            }
                            for (columnIndex in 0 until valueColumnNames.size) {
                                localMap[valueColumnNames[columnIndex]] = localColumns[columnIndex]
                            }
                            val row = IteratorBundle(localMap)
                            val localAggregations = Array(aggregations.size) {
                                val tmp = aggregations[it].createIterator(row)
                                localMap["#" + aggregations[it].uuid] = tmp
                                tmp
                            }
                            localRow = POPGroup_Row(row, localAggregations, localColumns)
                            map[key] = localRow
                        }
                        for (columnIndex in 0 until valueColumnNames.size) {
                            localRow.columns[columnIndex].tmp = valueColumns[columnIndex].next()
                        }
                        for (aggregate in localRow.aggregates) {
                            aggregate.evaluate()
                        }
                    }
                    if (map.isEmpty()) {
                        for (v in keyColumnNames) {
                            outMap[v] = ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue)
                        }
                        for ((first) in bindings) {
                            outMap[first] = ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue)
                        }
                    } else {
                        val outKeys = Array(keyColumnNames.size) { mutableListOf<DictionaryValueType>() }
                        val outValues = Array(bindings.size) { mutableListOf<DictionaryValueType>() }
                        for ((k, v) in map) {
                            for (columnIndex in keyColumnNames.indices) {
                                outKeys[columnIndex].add(k.data[columnIndex])
                            }
                            for (columnIndex in 0 until bindings.size) {
                                outValues[columnIndex].add(bindings[columnIndex].second.evaluateID(v.iterators)())
                            }
                        }
                        for (columnIndex in keyColumnNames.indices) {
                            outMap[keyColumnNames[columnIndex]] = ColumnIteratorMultiValue(outKeys[columnIndex])
                        }
                        for (columnIndex in 0 until bindings.size) {
                            outMap[bindings[columnIndex].first] = ColumnIteratorMultiValue(outValues[columnIndex])
                        }
                    }
                }
            }
        }
        return IteratorBundle(outMap)
    }
}
