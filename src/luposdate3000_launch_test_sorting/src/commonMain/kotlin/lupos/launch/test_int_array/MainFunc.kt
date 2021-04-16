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
private var maxdepth = 0

private inline fun quicksort(l: Int, r: Int, crossinline cmp: (Int, Int) -> Int, crossinline swap: (Int, Int) -> Unit, step: Int) {
    var stack = IntArrayWrapper()
    stack.append(l)
    stack.append(r)
    while (stack.getSize() > 0) {
        maxdepth = max(maxdepth, stack.getSize())
        var rr = stack.removeLast()
        var ll = stack.removeLast()
        while (ll < rr) {
            println(".. $ll $rr")
            var i = ll - step
            var j = rr
            while (i < j) {
                println("a")
                i += step
                while (i < j && cmp(i, rr) <= 0) {
                    i += step
                }
                println("b")
                j -= step
                while (j >= i && cmp(j, rr) > 0) {
                    j -= step
                }
                if (i < j) {
                    swap(i, j)
                }
            }
            println("c $i $j")
            swap(i, rr)
            if (i - ll < rr - i) {
                stack.append(i + step)
                stack.append(rr)
                rr = i - step
            } else {
                stack.append(ll)
                stack.append(i - step)
                ll = i + step
            }
        }
    }
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    val dataA = IntArray(hasNextRandom())
    data = dataA
    maxdepth = 0
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
            println("cmp $a $b")
            comparisons++
            dataA[a] - dataA[b]
        },
        swap = { a, b ->
            println("swap $a $b")
            swapped++
            val t = dataA[a]
            dataA[a] = dataA[b]
            dataA[b] = t
        },
        step = 1,
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
    println("size=${dataA.size} maxdepth=$maxdepth comparisons=$comparisons swapped=$swapped nlogn=${(dataA.size.toDouble() * log2(dataA.size.toDouble())).toInt()}")
}
