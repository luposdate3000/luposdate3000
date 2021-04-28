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
package lupos.launch.test_kv

import lupos.buffer_manager.BufferManager
import lupos.buffer_manager.BufferManagerExt
import lupos.kv.KeyValueStore
import lupos.shared.AflCore
import lupos.shared.ByteArrayWrapper
import lupos.shared.Parallel
import kotlin.jvm.JvmField
import kotlin.math.abs

@JvmField
internal val verbose = false

// @JvmField internal val maxSize = 16
@JvmField
internal val maxSize = 16384

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("kv.${BufferManagerExt.isInMemoryOnly}", 1.0, ::executeTest)(arg)
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    BufferManagerExt.allowInitFromDisk = false
    var bufferManager = BufferManager()
    val rootPage = bufferManager.allocPage(lupos.SOURCE_FILE)
    var kv = KeyValueStore(bufferManager, rootPage, false)

    val values = mutableListOf<ByteArray>()
    val mapping = mutableMapOf<Int, Int>() // kv.id -> values.idx

    var usedGenerators = mutableMapOf<Int, MutableSet<Int>>() // len -> seed

    fun getExistingData(rng: Int, action: (ByteArray, Int) -> Unit) {
        val ids = mutableListOf<Int>()
        ids.addAll(mapping.keys)
        if (ids.size > 0) {
            val key = ids[abs(rng % ids.size)]
            action(values[mapping[key]!!], key)
        }
    }

    fun getNotExistingKey(rng: Int, action: (Int) -> Unit) {
        val ids = MutableList<Int>(1000) { it }
        ids.removeAll(mapping.keys)
        if (ids.size > 0) {
            val key = ids[abs(rng % ids.size)]
            action(key)
        }
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

    fun testCreateValueNotExistingOk(data: ByteArray) {
        val key = kv.createValue(ByteArrayWrapper(data))
        if (verbose) {
            println("testCreateValueNotExistingOk $key ${data.map { it }}")
        }
        if (mapping[key] != null) {
            throw Exception("")
        }
        mapping[key] = values.size
        values.add(data)
    }

    fun testGetValueOk(data: ByteArray, key: Int) {
        if (verbose) {
            println("testGetValueOk $key ${data.map { it }}")
        }
        val value = ByteArrayWrapper()
        kv.getValue(value, key)
        if (value.size != data.size) {
            throw Exception("")
        }
        for (i in 0 until value.size) {
            if (value.buf[i] != data[i]) {
                throw Exception("")
            }
        }
    }

    fun testGetValueFail(key: Int) {
        if (verbose) {
            println("testGetValueFail $key")
        }
        var flag = true
        try {
            val buffer = ByteArrayWrapper()
            kv.getValue(buffer, key)
        } catch (e: Throwable) {
            // e.printStackTrace() this is handled correctly
            flag = false
        }
        if (flag) {
            throw Exception("")
        }
    }

    while (hasNextRandom() >= 2) {
        val mode = abs(nextRandom() % 3)
        val rng = nextRandom()
        when (mode) {
            0 -> getNotExistingData(rng) { v -> testCreateValueNotExistingOk(v) }

            1 -> getExistingData(rng) { v, k -> testGetValueOk(v, k) }
            2 -> getNotExistingKey(rng) { k -> testGetValueFail(k) }
        }
    }
    for ((k, v) in mapping) {
        testGetValueOk(values[v], k)
    }
    if (!BufferManagerExt.isInMemoryOnly) {
        kv.close()
        if (bufferManager.getNumberOfReferencedPages() != 0) {
            throw Exception("")
        }
        kv = KeyValueStore(bufferManager, rootPage, true)
    }
    for ((k, v) in mapping) {
        testGetValueOk(values[v], k)
    }
    kv.delete()
    if (bufferManager.getNumberOfReferencedPages() != 0) {
        throw Exception("")
    }
    if (bufferManager.getNumberOfAllocatedPages() != 0) {
        throw Exception("")
    }
    bufferManager.close()
}
