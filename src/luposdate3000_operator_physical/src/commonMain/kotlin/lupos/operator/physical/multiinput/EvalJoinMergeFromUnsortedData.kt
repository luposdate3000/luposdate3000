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
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.IQuery
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
public object EvalJoinMergeFromUnsortedData {

    private val bufferArrayLen = 1000000
    private fun myComparator(array: Array<MutableList<DictionaryValueTypeArray>>, i: Long, j: DictionaryValueTypeArray, sortOrder: IntArray): Int {
        val a = i / bufferArrayLen
        val b = i - a * bufferArrayLen
        for (col in sortOrder) {
            if (array[col][a.toInt()][b.toInt()] <j[col]) {
                return -1
            }
            if (array[col][a.toInt()][b.toInt()]> j[col]) {
                return 1
            }
        }
        return 0
    }
    private fun quickSort(array: Array<MutableList<DictionaryValueTypeArray>>, sortOrder: IntArray, len: Long) {
        quickSort(array, 0L, len - 1, sortOrder)
    }

    private fun quickSort(array: Array<MutableList<DictionaryValueTypeArray>>, left: Long, right: Long, sortOrder: IntArray) {
        val index = partition(array, left, right, sortOrder)
        if (left < index - 1) {
            quickSort(array, left, index - 1, sortOrder)
        }
        if (index < right) {
            quickSort(array, index, right, sortOrder)
        }
    }

    private fun partition(array: Array<MutableList<DictionaryValueTypeArray>>, l: Long, r: Long, sortOrder: IntArray): Long {
        var left = l
        var right = r
        val pivot = DictionaryValueTypeArray(array.size) {
            val i = (left + right) / 2
            val a = i / bufferArrayLen
            val b = i - a * bufferArrayLen
            array[it][a.toInt()][b.toInt()]
        }

        while (left <= right) {
            while (myComparator(array, left, pivot, sortOrder) <0) left++
            while (myComparator(array, right, pivot, sortOrder)> 0) right--
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
        for (col in 0 until a.size) {
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
        val joinColumns = mutableListOf<String>()
        for (name in child0.columns.keys) {
            if (child1.columns.keys.contains(name)) {
                joinColumns.add(name)
            }
        }
        if (joinColumns.size == 0) {
            TODO("this requires at least one join column")
        }

        val child0Buf = Array(child0.columns.size) { mutableListOf<DictionaryValueTypeArray>() }
        val child0BufLen = Array(child0.columns.size) { mutableListOf<Long>() }
        val child0Names = child0.columns.keys.toTypedArray()
        for (i in 0 until child0Names.size) {
            val iter = child0.columns[child0Names[i]]!!
            var d = DictionaryValueTypeArray(bufferArrayLen)
            child0Buf[i].add(d)
            var idx = 0
            var next = iter.next()
            while (true) {
                if (idx == bufferArrayLen) {
                    child0BufLen[i].add(bufferArrayLen.toLong())
                    d = DictionaryValueTypeArray(bufferArrayLen)
                    child0Buf[i].add(d)
                    idx = 0
                }
                d[idx++] = next
                if (next == DictionaryValueHelper.nullValue) {
                    child0BufLen[i].add(idx.toLong() - 1)
                    break
                }
                next = iter.next()
            }
        }
        quickSort(child0Buf, joinColumns.map { child0Names.indexOf(it) }.toIntArray(), child0BufLen[0].sum())
        val child0Iterators = mutableMapOf<String, ColumnIterator>()
        for (i in 0 until child0Names.size) {
            child0Iterators[child0Names[i]] = ColumnIteratorMultiIterator(child0Buf[i].mapIndexed { idx, it -> ColumnIteratorMultiValue(it, child0BufLen[i][idx].toInt()) })
        }
        for ((k, v) in child0.columns) {
            v.close()
        }

        val child1Buf = Array(child1.columns.size) { mutableListOf<DictionaryValueTypeArray>() }
        val child1BufLen = Array(child1.columns.size) { mutableListOf<Long>() }
        val child1Names = child1.columns.keys.toTypedArray()
        for (i in 0 until child1Names.size) {
            val iter = child1.columns[child1Names[i]]!!
            var d = DictionaryValueTypeArray(bufferArrayLen)
            child1Buf[i].add(d)
            var idx = 0
            var next = iter.next()
            while (true) {
                if (idx == bufferArrayLen) {
                    child1BufLen[i].add(bufferArrayLen.toLong())
                    d = DictionaryValueTypeArray(bufferArrayLen)
                    child1Buf[i].add(d)
                    idx = 0
                }
                d[idx++] = next
                if (next == DictionaryValueHelper.nullValue) {
                    child1BufLen[i].add(idx.toLong() - 1)
                    break
                }
                next = iter.next()
            }
        }
        quickSort(child1Buf, joinColumns.map { child1Names.indexOf(it) }.toIntArray(), child1BufLen[0].sum())
        val child1Iterators = mutableMapOf<String, ColumnIterator>()
        for (i in 0 until child1Names.size) {
            child1Iterators[child1Names[i]] = ColumnIteratorMultiIterator(child1Buf[i].mapIndexed { idx, it -> ColumnIteratorMultiValue(it, child1BufLen[i][idx].toInt()) })
        }
        for ((k, v) in child1.columns) {
            v.close()
        }

        val child0Sorted = IteratorBundle(child0Iterators)
        val child1Sorted = IteratorBundle(child1Iterators)

        return EvalJoinMerge(query, child0Sorted, child1Sorted, projectedVariables, joinColumns)
// return EvalJoinHashMap(query,child0Sorted,child1Sorted,false,projectedVariables,-1)
    }
}
