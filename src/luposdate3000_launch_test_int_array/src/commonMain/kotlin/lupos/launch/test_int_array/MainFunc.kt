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
import lupos.shared.myPrintStackTrace

import lupos.buffer_manager.BufferManager
import lupos.buffer_manager.BufferManagerExt
import lupos.buffer_manager.MyIntArray
import lupos.shared.AflCore
import lupos.shared.Luposdate3000Instance
import lupos.shared.Parallel
import lupos.shared.SanityCheck
import kotlin.math.abs

internal const val verbose = false

internal const val maxSize = 1000000

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("int_array.${BufferManagerExt.isInMemoryOnly}", 1.0, ::executeTest)(arg)
}

internal fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, @Suppress("UNUSED_PARAMETER") resetRandom: () -> Unit) {
    if (!SanityCheck.enabled) {
        return
    }
    val instance = Luposdate3000Instance()
    instance.allowInitFromDisk = false
    BufferManagerExt.allowInitFromDisk = false
    instance.bufferManager = BufferManager(instance)
    var dataSize = 0
    val data = IntArray(maxSize)
    val rootPage = instance.bufferManager!!.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_test_int_array/src/commonMain/kotlin/lupos/launch/test_int_array/MainFunc.kt:47"/*SOURCE_FILE_END*/)
    var arr = MyIntArray(instance.bufferManager!!, rootPage, false, instance)

    fun testSetSizeOk(size: Int) {
        val oldSize = dataSize
        dataSize = abs(size % data.size)
        for (i in oldSize until dataSize) {
            data[i] = 0
        }
        if (verbose) {
            println("testSetSizeOk $dataSize")
        }
        arr.setSize(dataSize, true)
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
            // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_test_int_array/src/commonMain/kotlin/lupos/launch/test_int_array/MainFunc.kt:78"/*SOURCE_FILE_END*/ ) this is handled correctly
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
            arr[idx]
        } catch (e: Throwable) {
            // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_test_int_array/src/commonMain/kotlin/lupos/launch/test_int_array/MainFunc.kt:105"/*SOURCE_FILE_END*/ ) this is handled correctly
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

    while (hasNextRandom() >= 3) {
        val mode = abs(nextRandom() % 6)
        val rng = nextRandom()
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
    if (!BufferManagerExt.isInMemoryOnly) {
        arr.close()
        if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
            throw Exception("")
        }
        arr = MyIntArray(instance.bufferManager!!, rootPage, true, instance)
    }
    for (i in 0 until dataSize) {
        testGetOk(i)
    }
    arr.delete()
    if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
        throw Exception("")
    }
    if (instance.bufferManager!!.getNumberOfAllocatedPages() != 0) {
        throw Exception("${instance.bufferManager!!.getNumberOfAllocatedPages()}")
    }
    instance.bufferManager!!.close()
}
