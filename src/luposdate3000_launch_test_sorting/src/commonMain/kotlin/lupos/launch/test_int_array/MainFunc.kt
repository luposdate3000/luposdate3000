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
package lupos.launch.test_sorting

import lupos.s00misc.Parallel
import lupos.shared.IntArrayWrapper
import lupos.test.AflCore
import kotlin.math.abs
import kotlin.math.log2
import kotlin.math.max

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("sorting", 10000.0, ::executeTest)(arg)
}

private var data: IntArray = IntArray(0)
private val stack = IntArray(128)

private inline fun quicksort(l: Int, r: Int, crossinline cmp: (Int, Int) -> Int, crossinline swap: (Int, Int) -> Unit, step: Int, stack: IntArray) {
    var stackSize = 0
    stack[stackSize++] = l
    stack[stackSize++] = r
    while (stackSize > 0) {
        var rr = stack[--stackSize]
        var ll = stack[--stackSize]
        while (ll < rr) {
            var i = ll - step
            var j = rr
            while (i < j) {
                i += step
                while (i < j && cmp(i, rr) <= 0) {
                    i += step
                }
                j -= step
                while (j > i && cmp(j, rr) > 0) {
                    j -= step
                }
                if (i < j) {
                    swap(i, j)
                }
            }
            swap(i, rr)
            if (i - ll < rr - i) {
                stack[stackSize++] = i + step
                stack[stackSize++] = rr
                rr = i - step
            } else {
                stack[stackSize++] = ll
                stack[stackSize++] = i - step
                ll = i + step
            }
        }
    }
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    val dataA = IntArray(hasNextRandom())
    data = dataA
    val dataB = IntArray(hasNextRandom())
    for (i in 0 until dataA.size) {
        dataA[i] = abs(nextRandom() % (Int.MAX_VALUE / 2 - 1))
        dataB[i] = dataA[i]
    }
    var comparisons = 0
    var swapped = 0
    quicksort(
        l = 0,
        r = dataA.size - 1,
        cmp = { a, b ->
            comparisons++
            dataA[a] - dataA[b]
        },
        swap = { a, b ->
            swapped++
            val t = dataA[a]
            dataA[a] = dataA[b]
            dataA[b] = t
        },
        step = 1,
        stack = stack,
    )
    dataB.sort()
    for (i in 0 until dataA.size) {
        if (dataA[i] != dataB[i]) {
            var res = "" + dataA[0]
            for (i in 1 until dataA.size) {
                res += ", ${dataA[i]}(${dataA[i] > dataA[i - 1]})"
            }
            throw Exception(res)
        }
    }
    println("size=${dataA.size} comparisons=$comparisons swapped=$swapped nlogn=${(dataA.size.toDouble() * log2(dataA.size.toDouble())).toInt()}")
}
