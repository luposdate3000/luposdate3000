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
package lupos.launch.test_vk

import lupos.buffer_manager.BufferManager
import lupos.buffer_manager.BufferManagerExt
import lupos.endpoint.LuposdateEndpoint
import lupos.shared.AflCore
import lupos.shared.Luposdate3000Instance
import lupos.shared.inline.ParallelThread
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.vk.ValueKeyStore
import kotlin.math.abs

internal const val verbose = false

internal const val maxSize = 16384

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = ParallelThread.runBlocking {
    AflCore("vk.${BufferManagerExt.isInMemoryOnly}", 1.0, ::executeTest)(arg)
}

internal fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, @Suppress("UNUSED_PARAMETER") resetRandom: () -> Unit) {
    var instance = Luposdate3000Instance()
    instance.allowInitFromDisk = false
    instance = LuposdateEndpoint.initializeB(instance)
    if (verbose) {
        println("start")
    }
    BufferManagerExt.allowInitFromDisk = false
    instance.bufferManager = BufferManager(instance)
    val rootPage = instance.bufferManager!!.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_test_vk/src/commonMain/kotlin/lupos/launch/test_vk/MainFunc.kt:47"/*SOURCE_FILE_END*/)
    var vk = ValueKeyStore(instance.bufferManager!!, rootPage, false)

    val values = mutableListOf<ByteArrayWrapper>()

    val usedGenerators = mutableMapOf<Int, MutableSet<Int>>() // len -> seed

    fun getExistingData(rng: Int, action: (ByteArrayWrapper, Int) -> Unit) {
        if (values.size > 0) {
            val key = abs(rng % values.size)
            action(values[key], key)
        }
    }

    fun getNotExistingKey(@Suppress("UNUSED_PARAMETER") rng: Int, action: (Int) -> Unit) {
        action(values.size)
    }

    fun getNotExistingData(rng: Int, action: (ByteArrayWrapper) -> Unit) {
        var len = abs(rng / 256) % maxSize
        var seed = abs(rng % 256)
        if (usedGenerators[len] == null) {
            usedGenerators[len] = mutableSetOf()
        } else {
            while (usedGenerators[len]!!.contains(seed)) {
                if (seed < 255) {
                    seed++
                } else {
                    len = (len + 1) % maxSize
                    seed = 0
                    if (usedGenerators[len] == null) {
                        usedGenerators[len] = mutableSetOf()
                        break
                    }
                }
            }
        }
        if (len == 0) {
            for (i in 0 until 256) {
                usedGenerators[len]!!.add(i)
            }
        } else {
            usedGenerators[len]!!.add(seed)
        }
        val res = ByteArrayWrapper()
        ByteArrayWrapperExt.setSize(res, len, false)
        for (i in 0 until len) {
            ByteArrayWrapperExt.getBuf(res)[i] = (i + seed).toByte()
        }
        action(res)
    }

    fun testCreateValueExistingOk(data: ByteArrayWrapper, targetKey: Int) {
        if (verbose) {
            println("testCreateValueExistingOk $targetKey $data")
        }
        val key = vk.createValue(data) {
            TODO()
        }
        if (key != targetKey) {
            TODO()
        }
    }

    fun testCreateValues(rng: Int) {
        val insertedData = mutableSetOf<ByteArrayWrapper>()
        val maxlen = hasNextRandom() / 2
        if (maxlen > 0) {
            for (i in 0 until (abs(rng % maxlen))) {
                getNotExistingData(nextRandom()) {
                    insertedData.add(it)
                }
            }
            val toInsert = insertedData.sorted()
            var i = 0
            vk.createValues(
                hasNext = {
                    i < toInsert.size
                },
                next = {
                    toInsert[i++]
                },
                onNotFound = {
                    if (values.contains(it)) {
                        TODO()
                    }
                    values.add(it)
                    values.size - 1
                },
                onFound = { _, _ ->
                }
            )
        }
    }

    fun testCreateValueNotExistingOk(data: ByteArrayWrapper) {
        val key = vk.createValue(data) { values.size }
        if (verbose) {
            println("testCreateValueNotExistingOk $key $data")
        }
        if (key != values.size) {
            TODO("$key != ${values.size}")
        }
        values.add(data)
    }

    fun testHasValueExistingOk(data: ByteArrayWrapper, targetKey: Int) {
        if (verbose) {
            println("testHasValueYesOk $targetKey $data")
        }
        val res = vk.hasValue(data)
        if (res != targetKey) {
            TODO("$res $targetKey")
        }
    }

    fun testHasValueNotExistingOk(data: ByteArrayWrapper) {
        if (verbose) {
            println("testHasValueNoOk $data")
        }
        val res = vk.hasValue(data)
        if (res != ValueKeyStore.ID_NULL) {
            TODO("$res")
        }
    }

    fun testAll() {
        if (verbose) {
            println("testAll")
        }
        val buffer = ByteArrayWrapper()
        val iterator = vk.getIterator(buffer)
        val counters = IntArray(values.size)
        while (iterator.hasNext()) {
            val id = iterator.next()
            if (id < 0) {
                TODO()
            }
            if (id >= values.size) {
                TODO()
            }
            if (values[id] != buffer) {
                TODO("$id $buffer")
            }
            counters[id]++
        }
        iterator.close()
        for (i in 0 until counters.size) {
            if (counters[i] != 1) {
                TODO("$i ${counters[i]}")
            }
        }
    }
    while (hasNextRandom() >= 2) {
        val mode = abs(nextRandom() % 5)
        val rng = nextRandom()
        when (mode) {
            0 -> getExistingData(rng) { v, k -> testCreateValueExistingOk(v, k) }
            1 -> getNotExistingData(rng) { v -> testCreateValueNotExistingOk(v) }

            2 -> getExistingData(rng) { v, k -> testHasValueExistingOk(v, k) }
            3 -> getNotExistingData(rng) { v -> testHasValueNotExistingOk(v) }
            4 -> testCreateValues(rng)
        }
    }
    testAll()
    if (!BufferManagerExt.isInMemoryOnly) {
        vk.close()
        if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
            TODO("${instance.bufferManager!!.getNumberOfReferencedPages()}")
        }
        vk = ValueKeyStore(instance.bufferManager!!, rootPage, true)
    }
    testAll()
    vk.delete()
    if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
        TODO()
    }
    if (instance.bufferManager!!.getNumberOfAllocatedPages() != 0) {
        TODO()
    }
    instance.bufferManager!!.close()
}
