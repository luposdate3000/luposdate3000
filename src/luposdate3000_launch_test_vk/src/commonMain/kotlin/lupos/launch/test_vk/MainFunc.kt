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
import lupos.shared.ByteArrayWrapper
import lupos.shared.Parallel
import lupos.test.AflCore
import lupos.vk.ValueKeyStore
import kotlin.jvm.JvmField
import kotlin.math.abs

@JvmField
internal val verbose = false

// @JvmField internal val maxSize = 16
@JvmField
internal val maxSize = 16384

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("vk.${BufferManagerExt.isInMemoryOnly}", 1.0, ::executeTest)(arg)
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    if (verbose) {
        println("start")
    }
    BufferManagerExt.allowInitFromDisk = false
    var bufferManager = BufferManager()
    val rootPage = bufferManager.allocPage(lupos.SOURCE_FILE)
    var vk = ValueKeyStore(bufferManager, rootPage, false)

    val values = mutableListOf<ByteArrayWrapper>()

    var usedGenerators = mutableMapOf<Int, MutableSet<Int>>() // len -> seed

    fun getExistingData(rng: Int, action: (ByteArrayWrapper, Int) -> Unit) {
        if (values.size > 0) {
            val key = abs(rng % values.size)
            action(values[key], key)
        }
    }

    fun getNotExistingKey(rng: Int, action: (Int) -> Unit) {
        action(values.size)
    }

    fun getNotExistingData(rng: Int, action: (ByteArrayWrapper) -> Unit) {
        var len = abs(rng / 256) % maxSize
        var seed = abs(rng % 256)
        if (usedGenerators[len] == null) {
            usedGenerators[len] = mutableSetOf<Int>()
        } else {
            while (usedGenerators[len]!!.contains(seed)) {
                if (seed < 255) {
                    seed++
                } else {
                    len = (len + 1) % maxSize
                    seed = 0
                    if (usedGenerators[len] == null) {
                        usedGenerators[len] = mutableSetOf<Int>()
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
        var res = ByteArrayWrapper()
        res.setSize(len)
        for (i in 0 until len) {
            res.getBuf()[i] = (i + seed).toByte()
        }
        action(res)
    }

    fun testCreateValueExistingOk(data: ByteArrayWrapper, targetKey: Int) {
        if (verbose) {
            println("testCreateValueExistingOk $targetKey $data")
        }
        val key = vk.createValue(data) {
            throw Exception("")
        }
        if (key != targetKey) {
            throw Exception("")
        }
    }

    fun testCreateValues(rng: Int) {
        var insertedData = mutableSetOf<ByteArrayWrapper>()
        val maxlen = hasNextRandom() / 2
        if (maxlen > 0) {
            for (i in 0 until (abs(rng % maxlen))) {
                getNotExistingData(nextRandom()) { it ->
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
                onNotFound = { it ->
                    if (values.contains(it)) {
                        throw Exception("")
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
            throw Exception("$key != ${values.size}")
        }
        values.add(data)
    }

    fun testHasValueExistingOk(data: ByteArrayWrapper, targetKey: Int) {
        if (verbose) {
            println("testHasValueYesOk $targetKey $data")
        }
        val res = vk.hasValue(data)
        if (res != targetKey) {
            throw Exception("$res $targetKey")
        }
    }

    fun testHasValueNotExistingOk(data: ByteArrayWrapper) {
        if (verbose) {
            println("testHasValueNoOk $data")
        }
        val res = vk.hasValue(data)
        if (res != ValueKeyStore.ID_NULL) {
            throw Exception("$res")
        }
    }

    fun testAll() {
        if (verbose) {
            println("testAll")
        }
        val buffer = ByteArrayWrapper()
        val iterator = vk.getIterator(buffer)
        var counters = IntArray(values.size)
        while (iterator.hasNext()) {
            val id = iterator.next()
            if (id < 0) {
                throw Exception("")
            }
            if (id >= values.size) {
                throw Exception("")
            }
            if (values[id] != buffer) {
                throw Exception("$id $buffer")
            }
            counters[id]++
        }
        iterator.close()
        for (i in 0 until counters.size) {
            if (counters[i] != 1) {
                throw Exception("$i ${counters[i]}")
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
        if (bufferManager.getNumberOfReferencedPages() != 0) {
            throw Exception("${bufferManager.getNumberOfReferencedPages()}")
        }
        vk = ValueKeyStore(bufferManager, rootPage, true)
    }
    testAll()
    vk.delete()
    if (bufferManager.getNumberOfReferencedPages() != 0) {
        throw Exception("")
    }
    if (bufferManager.getNumberOfAllocatedPages() != 0) {
        throw Exception("")
    }
    bufferManager.close()
}
