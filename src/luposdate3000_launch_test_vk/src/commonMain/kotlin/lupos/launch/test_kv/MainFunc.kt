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

import lupos.buffermanager.BufferManager
import lupos.buffermanager.BufferManagerExt
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.Parallel
import lupos.test.AflCore
import lupos.vk.ValueKeyStore
import kotlin.math.abs

private val verbose = true

// private val maxSize = 16
private val maxSize = 16

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("vk.${BufferManagerExt.isInMemoryOnly}", 1.0, ::executeTest)(arg)
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    BufferManagerExt.allowInitFromDisk = false
    var bufferManager = BufferManager()
    var rootPage = -1
    bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
        rootPage = pageid
    }
    bufferManager.releasePage(lupos.SOURCE_FILE, rootPage)
    var vk = ValueKeyStore(bufferManager, rootPage, false)

    val values = mutableListOf<ByteArray>()

    var usedGenerators = mutableMapOf<Int, MutableSet<Int>>() // len -> seed

    fun getExistingData(rng: Int, action: (ByteArray, Int) -> Unit) {
        if (values.size > 0) {
            val key = abs(rng % values.size)
            action(values[key], key)
        }
    }

    fun getNotExistingKey(rng: Int, action: (Int) -> Unit) {
        action(values.size)
    }

    fun getNotExistingData(rng: Int, action: (ByteArray) -> Unit) {
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
        action(ByteArray(len) { (it + seed).toByte() })
    }

    fun testCreateValueExistingOk(data: ByteArray, targetKey: Int) {
        if (verbose) {
            println("testCreateValueExistingOk $targetKey ${data.map { it }}")
        }
        val key = vk.createValue(ByteArrayWrapper(data)) {
            throw Exception("")
        }
        if (key != targetKey) {
            throw Exception("")
        }
    }

    fun testCreateValueNotExistingOk(data: ByteArray) {
        val key = vk.createValue(ByteArrayWrapper(data)) { values.size }
        if (verbose) {
            println("testCreateValueNotExistingOk $key ${data.map { it }}")
        }
        if (key != values.size) {
            throw Exception("")
        }
        values.add(data)
    }

    fun testHasValueExistingOk(data: ByteArray, targetKey: Int) {
        if (verbose) {
            println("testHasValueYesOk $targetKey ${data.map { it }}")
        }
        val res = vk.hasValue(ByteArrayWrapper(data))
        if (res != targetKey) {
            throw Exception("$res $targetKey")
        }
    }

    fun testHasValueNotExistingOk(data: ByteArray) {
        if (verbose) {
            println("testHasValueNoOk ${data.map { it }}")
        }
        val res = vk.hasValue(ByteArrayWrapper(data))
        if (res != null) {
            throw Exception("")
        }
    }

    fun testAll() {
        val buffer = ByteArrayWrapper()
        val iterator = vk.getIterator(buffer)
        var counters = IntArray(values.size)
        while (iterator.hasNext()) {
            val id = iterator.next()
            if (id <0) {
                throw Exception("")
            }
            if (id >= values.size) {
                throw Exception("")
            }
            if (ByteArrayWrapper(values[id]) != buffer) {
                throw Exception("")
            }
            counters[id]++
        }
iterator.close()
        for (i in 0 until counters.size) {
            if (counters[i] != 1) {
                throw Exception("")
            }
        }
    }
    while (hasNextRandom() >= 2) {
        val mode = abs(nextRandom() % 4)
        val rng = nextRandom()
        when (mode) {
            0 -> getExistingData(rng) { v, k -> testCreateValueExistingOk(v, k) }
            1 -> getNotExistingData(rng) { v -> testCreateValueNotExistingOk(v) }

            2 -> getExistingData(rng) { v, k -> testHasValueExistingOk(v, k) }
            3 -> getNotExistingData(rng) { v -> testHasValueNotExistingOk(v) }
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
