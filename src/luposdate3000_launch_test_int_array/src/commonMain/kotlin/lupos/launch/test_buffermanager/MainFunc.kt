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
package lupos.launch.test_int_array

import lupos.buffermanager.BufferManager
import lupos.kv.IntArrayOnBufferManager
import lupos.s00misc.DateHelperRelative
import lupos.s00misc.File
import lupos.s00misc.Parallel
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow

private val verbose = false
private val maxSize = 1000000

private class MyRandom(var seed: Long) {
    val bits = 32
    fun nextInt(): Int {
        seed = (seed * 0x5DEECE66DL + 0xBL) and ((1L shl 48) - 1)
        return (seed shr (48 - bits)).toInt()
    }
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
/*
 if arg is an long, than random tests are executed
 otherwise it is assumed, that arg is a filename, which is then used as input data
 */
    var seed = 0L
    var dataoff: Int = 0
    try {
        val data = IntArray(65536)
        seed = arg.toLong()
        // randomized testing
        var tests = 0
        var errors = 0
        val timer = DateHelperRelative.markNow()
        val random = MyRandom(seed)
        Parallel.launch {
            while (true) {
                val time = DateHelperRelative.elapsedSeconds(timer)
                println("tests $tests, errors $errors testsPerSecond ${tests / time}")
                Parallel.delay(1000)
            }
        }
        while (true) {
            val maxlen = min((tests + 2).toDouble().pow(1.0 / 2.0).toInt(), data.size)
            val cnt = abs(random.nextInt() % maxlen)
            for (i in 0 until cnt) {
                val tmp = random.nextInt()
                data[i] = tmp
            }
            var tmp = data.contentHashCode()
            if (tmp < 0) {
                tmp = -tmp
            }
            var testCase = "test_int_array_${tmp.toString(16)}.data"
            if (verbose) {
                println("case $tests :: $cnt $testCase")
            }
            try {
                dataoff = 0
                executeTest(nextRandom = { data[dataoff++] }, hasNextRandom = { dataoff < cnt })
            } catch (e: Throwable) {
                e.printStackTrace()
                errors++
                File("erroredTests").mkdirs()
                println("errored $tests :: $dataoff $testCase")
                File("erroredTests/$testCase").withOutputStream {
                    for (i in 0 until dataoff) {
                        it.writeInt(data[i])
                    }
                }
            }
            tests++
        }
    } catch (e: Throwable) {
// verification of single testcase
        val f = File(arg)
        val data = IntArray((f.length() / 4).toInt())
        f.withInputStream {
            for (i in 0 until data.size) {
                data[i] = it.readInt()
            }
        }
        var dataoff = 0
        executeTest(nextRandom = { data[dataoff++] }, hasNextRandom = { dataoff < data.size })
    }
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Boolean) {
    var bufferManager = BufferManager()
    var dataSize = 0
    val data = IntArray(maxSize)
    var rootPage = -1
    bufferManager.createPage { page, pageid ->
        rootPage = pageid
    }
    bufferManager.releasePage(rootPage)
    var arr = IntArrayOnBufferManager(bufferManager, rootPage, false)

    fun testSetSizeOk(size: Int) {
        var oldSize = dataSize
        dataSize = abs(size % data.size)
        for (i in oldSize until dataSize) {
            data[i] = 0
        }
        if (verbose) {
            println("testSetSizeOk $dataSize")
        }
        arr.setSize(dataSize)
    }

    fun testSetOk(idx: Int, value: Int) {
        if (verbose) {
            println("testSetOk $idx $value")
        }
        data[idx] = value
        arr[idx] = value
    }

    fun testSetFail(idx: Int) {
        if (verbose) {
            println("testSetFail $idx")
        }
        var flag = true
        try {
            arr[idx] = -1
        } catch (e: Throwable) {
            flag = false
        }
        if (flag) {
            throw Exception("")
        }
    }

    fun testGetOk(idx: Int) {
        if (verbose) {
            println("testGetOk $idx")
        }
        val a = data[idx]
        val b = arr[idx]
        if (a != b) {
            throw Exception("$a $b")
        }
    }

    fun testGetFail(idx: Int) {
        if (verbose) {
            println("testGetFail $idx")
        }
        var flag = true
        try {
            val x = arr[idx]
        } catch (e: Throwable) {
            flag = false
        }
        if (flag) {
            throw Exception("")
        }
    }

    fun getValidIndex(rng: Int, action: (Int) -> Unit) {
        if (dataSize > 0) {
            action(abs(rng % dataSize))
        }
    }

    fun getNotValidIndex(rng: Int, action: (Int) -> Unit) {
        if (rng < 0 || rng >= dataSize) {
            action(rng)
        } else {
            action(rng + dataSize)
        }
    }

    while (hasNextRandom()) {
        val mode = abs(nextRandom() % 6)
        if (!hasNextRandom()) {
            break
        }
        val rng = nextRandom()
        if (!hasNextRandom()) {
            break
        }
        val rng2 = nextRandom()
        when (mode) {
            0 -> testSetSizeOk(rng)

            1 -> getValidIndex(rng) { testSetOk(it, rng2) }
            2 -> getNotValidIndex(rng) { testSetFail(it) }

            3 -> getValidIndex(rng) { testGetOk(it) }
            4 -> getNotValidIndex(rng) { testGetFail(it) }
        }
    }
    for (i in 0 until dataSize) {
        testGetOk(i)
    }
    arr.close()
    arr = IntArrayOnBufferManager(bufferManager, rootPage, true)
    for (i in 0 until dataSize) {
        testGetOk(i)
    }
    arr.close()
    bufferManager.close()
}
