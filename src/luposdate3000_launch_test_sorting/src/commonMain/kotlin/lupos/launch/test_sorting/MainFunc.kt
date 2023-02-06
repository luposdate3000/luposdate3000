/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.launch.test_sorting

import lupos.shared.AflCore
import lupos.shared.DateHelperRelative
import lupos.shared.Parallel
import kotlin.jvm.JvmField
import kotlin.math.abs
import kotlin.math.log2
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("sorting", 10000000.0, ::executeTest)(arg)
}

@JvmField
internal var data1: IntArray = IntArray(0)

@JvmField
internal var data2: IntArray = IntArray(0)

@JvmField
internal val stack = IntArray(128)

private fun mergesort2(n: Int, copyBToA: (Int, Int) -> Unit, copyAToB: (Int, Int) -> Unit, cmpAtoA: (Int, Int) -> Int, cmpBtoB: (Int, Int) -> Int, step: Int) {
    var size = 1
    while (size < n) {
        var lstart = 0
        while (lstart < n) {
            val lend = min(lstart + size * step, n)
            var rstart = lend
            val rend = min(lend + size * step, n)
            var dstart = lstart
            if (lstart < lend && rstart < rend) {
                if (cmpAtoA(lend - step, rstart) > 0) {
                    loop@ while (true) {
                        if (cmpAtoA(lstart, rstart) <= 0) {
                            copyAToB(dstart, lstart)
                            dstart += step
                            lstart += step
                            if (lstart >= lend) {
                                break@loop
                            }
                        } else {
                            copyAToB(dstart, rstart)
                            dstart += step
                            rstart += step
                            if (rstart >= rend) {
                                break@loop
                            }
                        }
                    }
                }
            }
            while (lstart < lend) {
                copyAToB(dstart, lstart)
                dstart += step
                lstart += step
            }
            while (rstart < rend) {
                copyAToB(dstart, rstart)
                dstart += step
                rstart += step
            }
            lstart = rend
        }
        size += size
//
        lstart = 0
        while (lstart < n) {
            val lend = min(lstart + size * step, n)
            var rstart = lend
            val rend = min(lend + size * step, n)
            var dstart = lstart
            if (lstart < lend && rstart < rend) {
                if (cmpBtoB(lend - step, rstart) > 0) {
                    loop@ while (true) {
                        if (cmpBtoB(lstart, rstart) <= 0) {
                            copyBToA(dstart, lstart)
                            dstart += step
                            lstart += step
                            if (lstart >= lend) {
                                break@loop
                            }
                        } else {
                            copyBToA(dstart, rstart)
                            dstart += step
                            rstart += step
                            if (rstart >= rend) {
                                break@loop
                            }
                        }
                    }
                }
            }
            while (lstart < lend) {
                copyBToA(dstart, lstart)
                dstart += step
                lstart += step
            }
            while (rstart < rend) {
                copyBToA(dstart, rstart)
                dstart += step
                rstart += step
            }
            lstart = rend
        }
        size += size
    }
}

private fun mergesort1(n: Int, copyBToA: (Int, Int) -> Unit, copyAToB: (Int, Int) -> Unit, copyAToA: (Int, Int) -> Unit, cmpBtoA: (Int, Int) -> Int) {
    var s = 1
    while (s < n) {
        var m = n - 1 - s
        while (m >= 0) {
            val lo = max(m - s + 1, 0)
            val hi = m + s
            var i = 0
            var j = lo
            while (j <= m) {
                copyAToB(i++, j++)
            }
            i = 0
            var k = lo
            while (k < j && j <= hi) {
                if (cmpBtoA(i, j) <= 0) {
                    copyBToA(k++, i++)
                } else {
                    copyAToA(k++, j++)
                }
            }
            while (k < j) {
                copyBToA(k++, i++)
            }
            m -= s + s
        }
        s += s
    }
}

private fun quicksort(l: Int, r: Int, cmp: (Int, Int) -> Int, swap: (Int, Int) -> Unit, step: Int, stack: IntArray) {
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

internal fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    val step = 2
    var thesize = hasNextRandom()
    thesize -= (thesize % step)
    val dataA = IntArray(thesize)
    val dataB = IntArray(thesize)
    data1 = dataA
    data2 = dataB
    var comparisons = 0L
    var swapped = 0L
    val algorithms = arrayOf(
/*        Pair(
            "quicksort ",
            {
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
            }
        ),
        Pair(
            "mergesort1",
            {
                mergesort1(
                    dataA.size,
                    copyBToA = { i, j ->
                        swapped++
                        dataA[i] = dataB[j]
                    },
                    copyAToB = { i, j ->
                        swapped++
                        dataB[i] = dataA[j]
                    },
                    copyAToA = { i, j ->
                        swapped++
                        dataA[i] = dataA[j]
                    },
                    cmpBtoA = { i, j ->
                        comparisons++
                        dataB[i] - dataA[j]
                    }
                )
            }
        ),
  */
        Pair(
            "mergesort2"
        ) {
            mergesort2(
                dataA.size,
                copyBToA = { i, j ->
                    swapped++
                    dataA[i] = dataB[j]
                },
                copyAToB = { i, j ->
                    swapped++
                    dataB[i] = dataA[j]
                },
                cmpAtoA = { i, j ->
                    comparisons++
                    dataA[i] - dataA[j]
                },
                cmpBtoB = { i, j ->
                    comparisons++
                    dataB[i] - dataB[j]
                },
                step = step,
            )
        },
    )

    for (ai in 0 until algorithms.size) {
        for (sorted in 0 until 2) {
            val algorithm = algorithms[ai]
            resetRandom()
            var i = 0
            while (i < dataA.size) {
                dataA[i] = abs(nextRandom() % (Int.MAX_VALUE / 2 - 1))
                for (j in 1 until step) {
                    dataA[i + j] = dataA[i]
                }
                i += step
            }
            if (sorted == 1) {
                dataA.sort()
            }
            comparisons = 0
            swapped = 0
            val startTime = DateHelperRelative.markNow()
            algorithm.second()
            val time = DateHelperRelative.elapsedSeconds(startTime)
            resetRandom()
            i = 0
            while (i < dataA.size) {
                dataB[i] = abs(nextRandom() % (Int.MAX_VALUE / 2 - 1))
                for (j in 1 until step) {
                    dataB[i + j] = dataB[i]
                }
                i += step
            }
            dataB.sort()
            i = 0
            while (i < dataA.size) {
                if (dataA[i] != dataB[i]) {
                    var res = "" + dataA[0]
                    for (j in 1 until dataA.size) {
                        res += ", ${dataA[j]}(${dataA[j] > dataA[j - 1]})"
                    }
                    TODO(res)
                }
                i += step
            }
            val n_log_n = dataA.size * log2(dataA.size.toDouble()).toLong()
            val n_times_n = dataA.size.toLong() * dataA.size.toLong()
            println(
                "${algorithm.first} $sorted" +
                    "n=${dataA.size.toString().padStart(10, ' ')} " +
                    "n_log_n=${n_log_n.toString().padStart(10, ' ')} " +
                    "n_times_n=${n_times_n.toString().padStart(15, ' ')} " +
                    "comparisons=${comparisons.toString().padStart(10, ' ')} " +
                    "swapped=${swapped.toString().padStart(10, ' ')} " +
                    "time=${time.toString().padStart(10, ' ')} "
            )
        }
    }
}
