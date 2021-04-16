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
import lupos.test.AflCore


@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("sorting", 1.0, ::executeTest)(arg)
}

private fun quicksort(l: Int, r: Int, cmp: (Int, Int) -> Int, swap: (Int, Int) -> Unit) {
    var ll = l
    var rr = r
    while (ll < rr) {
        var i = ll
        var j = rr - 3
        while (true) {
            while (i < rr && cmp(i, rr) <= 0) {
                i += 3
            }
            while (j > ll && cmp(j, rr) >= 0) {
                j -= 3
            }
            if (i >= j) {
                break
            } else {
                swap(i, j)
                i += 3
                j -= 3
            }
        }
        if (i < rr && cmp(i, rr) > 0) {
            swap(i, rr)
        }
        if (i - ll < rr - i) {
            quicksort(i + 1, rr, cmp, swap)
            rr = i - 1
        } else {
            quicksort(ll, i - 1, cmp, swap)
            ll = i + 1
        }
    }
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    val dataA = IntArray(hasNextRandom())
    val dataB = IntArray(hasNextRandom())
    for (i in 0 until dataA.size) {
        dataA[i] = nextRandom()
        dataB[i] = dataA[i]
    }
    quicksort(
        l = 0,
        r = dataA.size - 1,
        cmp = { a, b ->
            dataA[a] - dataA[b]
        },
        swap = { a, b ->
            val t = dataA[a]
            dataA[a] = dataA[b]
            dataA[b] = t
        },
    )
    dataB.sort()
    for (i in 0 until dataA.size) {
        if (dataA[i] != dataB[i]) {
            throw Exception("")
        }
    }
}
