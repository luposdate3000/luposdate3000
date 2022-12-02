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
package lupos.operator.physical.multiinput

import lupos.operator.base.iterator.ColumnIteratorMultiIterator
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.shared.ColumnIteratorChildIterator
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueHelper
import lupos.shared.IQuery
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
public object EvalJoinMergeFromUnsortedData {

    private val bufferArrayLen = 1000000
    private fun myComparator(array: Array<MutableList<DictionaryValueTypeArray>>, i: Long, j: Long, sortOrder: IntArray): Int {
        val a = i / bufferArrayLen
        val b = i - a * bufferArrayLen
        val c = i / bufferArrayLen
        val d = i - c * bufferArrayLen
        for (col in sortOrder) {
            if (array[col][a.toInt()][b.toInt()] <array[col][c.toInt()][d.toInt()]) {
                return -1
            }
            if (array[col][a.toInt()][b.toInt()]> array[col][c.toInt()][d.toInt()]) {
                return 1
            }
        }
        return 0
    }
    private fun quickSort(array: Array<MutableList<DictionaryValueTypeArray>>, sortOrder: IntArray) {
        var len = 0L
        for (x in array[0]) {
            len += x.size
        }
        quickSort(array, 0L, len, sortOrder)
    }

    private fun quickSort(array: Array<MutableList<DictionaryValueTypeArray>>, left: Long, right: Long, sortOrder: IntArray) {
        val index = partition(array, left, right, sortOrder)
        if (left < index - 1) { // 2) Sorting left half
            quickSort(array, left, index - 1, sortOrder)
        }
        if (index < right) { // 3) Sorting right half
            quickSort(array, index, right, sortOrder)
        }
    }

    private fun partition(array: Array<MutableList<DictionaryValueTypeArray>>, l: Long, r: Long, sortOrder: IntArray): Long {
        var left = l
        var right = r
        val pivot = (left + right) / 2 // 4) Pivot Point
        while (left <= right) {
            while (myComparator(array,left, pivot,sortOrder)<0) left++ // 5) Find the elements on left that should be on right

            while (myComparator(array,right, pivot,sortOrder)>0) right-- // 6) Find the elements on right that should be on left

            // 7) Swap elements, and move left and right indices
            if (left <= right) {
                swapArray(array, left, right)
                left++
                right--
            }
        }
        return left
    }

    private fun swapArray(a: Array<MutableList<DictionaryValueTypeArray>>, b: Long, c: Long) {
        val x = b / bufferArrayLen
        val y = b - x * bufferArrayLen
        val z = c / bufferArrayLen
        val w = c - z * bufferArrayLen
for(col in 0 until a.size){ 
        val temp = a[col][x.toInt()][y.toInt()]
        a[col][x.toInt()][y.toInt()] = a[col][z.toInt()][w.toInt()]
        a[col][z.toInt()][w.toInt()] = temp
}
    }

    public operator fun invoke(
        query: IQuery,
        child0: IteratorBundle,
        child1: IteratorBundle,
        projectedVariables: List<String>,
    ): IteratorBundle {
// buffer the data
        val joinColumns = mutableListOf<String>()
        for (name in child0.columns.keys) {
            if (child1.columns.keys.contains(name)) {
                joinColumns.add(name)
            }
        }
        val child0Buf = Array(child0.columns.size) { mutableListOf<DictionaryValueTypeArray>() }
        val child0BufLen = Array(child0.columns.size) { mutableListOf<Int>() }
        val child0Names = child0.columns.keys.toTypedArray()
        for (i in 0 until child0Names.size) {
            val iter = child0.columns[child0Names[i]]!!
            var d = DictionaryValueTypeArray(bufferArrayLen)
            child0Buf[i].add(d)
            var idx = 0
            var next = iter.next()
            while (true) {
                if (idx == bufferArrayLen) {
                    child0BufLen[i].add(bufferArrayLen)
                    d = DictionaryValueTypeArray(bufferArrayLen)
                    child0Buf[i].add(d)
                    idx = 0
                }
                d[idx++] = next
                if (next == DictionaryValueHelper.nullValue) {
                    child0BufLen[i].add(idx)
                    break
                }
                next = iter.next()
            }
        }
        quickSort(child0Buf,joinColumns.map{child0Names.indexOf(it)}.toIntArray())
        val child0Iterators=mutableMapOf<String,ColumnIterator>()
        for(i in 0 until child0Names.size){
child0Iterators[child0Names[i]]=ColumnIteratorMultiIterator(child0Buf[i].mapIndexed{idx,it->ColumnIteratorMultiValue(it,child0BufLen[i][idx])})
}

 val child1Buf = Array(child1.columns.size) { mutableListOf<DictionaryValueTypeArray>() }
        val child1BufLen = Array(child1.columns.size) { mutableListOf<Int>() }
        val child1Names = child1.columns.keys.toTypedArray()
        for (i in 0 until child1Names.size) {
            val iter = child1.columns[child1Names[i]]!!
            var d = DictionaryValueTypeArray(bufferArrayLen)
            child1Buf[i].add(d)
            var idx = 0
            var next = iter.next()
            while (true) {
                if (idx == bufferArrayLen) {
                    child1BufLen[i].add(bufferArrayLen)
                    d = DictionaryValueTypeArray(bufferArrayLen)
                    child1Buf[i].add(d)
                    idx = 0
                }
                d[idx++] = next
                if (next == DictionaryValueHelper.nullValue) {
                    child1BufLen[i].add(idx)
                    break
                }
                next = iter.next()
            }
        }
        quickSort(child1Buf,joinColumns.map{child1Names.indexOf(it)}.toIntArray())
        val child1Iterators=mutableMapOf<String,ColumnIterator>()
        for(i in 0 until child1Names.size){
child1Iterators[child1Names[i]]=ColumnIteratorMultiIterator(child1Buf[i].mapIndexed{idx,it->ColumnIteratorMultiValue(it,child1BufLen[i][idx])})
}        

        // setup columns
        val columnsINO0 = mutableListOf<ColumnIterator>()
        val columnsINO1 = mutableListOf<ColumnIterator>()
        val columnsINJ0 = mutableListOf<ColumnIterator>()
        val columnsINJ1 = mutableListOf<ColumnIterator>()
        val columnsOUT0 = mutableListOf<ColumnIteratorChildIterator>()
        val columnsOUT1 = mutableListOf<ColumnIteratorChildIterator>()
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>() // Key_in_outMap, which_outIteratorsCounter (J,O0,O1,none)
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(child1Iterators.keys)
        for (name in child0Iterators.keys) {
            if (tmp.contains(name)) {
                if (projectedVariables.contains(name)) {
                    outIterators.add(Pair(name, 0))
                    columnsINJ0.add(0, child0Iterators[name]!!)
                    columnsINJ1.add(0, child1Iterators[name]!!)
                } else {
                    columnsINJ0.add(child0Iterators[name]!!)
                    columnsINJ1.add(child1Iterators[name]!!)
                }
                tmp.remove(name)
            } else {
                outIterators.add(Pair(name, 1))
                columnsINO0.add(child0Iterators[name]!!)
            }
        }
        for (name in tmp) {
            outIterators.add(Pair(name, 2))
            columnsINO1.add(child1Iterators[name]!!)
        }
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeFromUnsortedData.kt:200"/*SOURCE_FILE_END*/ }, { columnsINJ0.size > 0 })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinMergeFromUnsortedData.kt:201"/*SOURCE_FILE_END*/ }, { columnsINJ0.size == columnsINJ1.size })
        val emptyColumnsWithJoin = outIterators.size == 0
        if (emptyColumnsWithJoin) {
            outIterators.add(Pair("", 3))
        }
        val key0 = DictionaryValueTypeArray(columnsINJ0.size)
        val key1 = DictionaryValueTypeArray(columnsINJ1.size)
        for ((first, second) in outIterators) {
            val iterator = POPJoinMerge_Iterator(query, columnsINJ0, columnsINJ1, columnsINO0, columnsINO1, columnsOUT0, columnsOUT1, columnsOUTJ, key0, key1)
            when (second) {
                0 -> {
                    outMap[first] = iterator
                    columnsOUTJ.add(iterator)
                }
                1 -> {
                    outMap[first] = iterator
                    columnsOUT0.add(iterator)
                }
                2 -> {
                    outMap[first] = iterator
                    columnsOUT1.add(iterator)
                }
                3 -> {
                    columnsOUTJ.add(iterator)
                }
            }
        }
        val res: IteratorBundle
        if (emptyColumnsWithJoin) {
            res = POPJoinMerge_Bundle(columnsINJ0, columnsINJ1, columnsOUTJ[0])
            for (it in columnsINO0) {
                it.close()
            }
            for (it in columnsINO1) {
                it.close()
            }
        } else {
            res = IteratorBundle(outMap)
        }
        for (i in 0 until columnsINJ0.size) {
            key0[i] = columnsINJ0[i].next()
        }
        for (i in 0 until columnsINJ1.size) {
            key1[i] = columnsINJ1[i].next()
        }
        return res
    }
}
