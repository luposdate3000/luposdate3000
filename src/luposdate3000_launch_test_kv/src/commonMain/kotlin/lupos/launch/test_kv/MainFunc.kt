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
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.ParallelThread
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.math.abs

internal const val verbose = false

internal const val maxSize = 16384

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = ParallelThread.runBlocking {
    AflCore("kv.${BufferManagerExt.isInMemoryOnly}", 1.0, ::executeTest)(arg)
}

internal fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, @Suppress("UNUSED_PARAMETER") resetRandom: () -> Unit) {
    if (!SanityCheck.enabled) {
        return
    }
    val instance = Luposdate3000Instance()
    instance.allowInitFromDisk = false
    BufferManagerExt.allowInitFromDisk = false
    instance.bufferManager = BufferManager(instance)
    val rootPage = instance.bufferManager!!.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_test_kv/src/commonMain/kotlin/lupos/launch/test_kv/MainFunc.kt:45"/*SOURCE_FILE_END*/)
    var kv = KeyValueStore(instance.bufferManager!!, rootPage, false, instance)

    val values = mutableListOf<ByteArray>()
    val mapping = mutableMapOf<Int, Int>() // kv.id -> values.idx

    val usedGenerators = mutableMapOf<Int, MutableSet<Int>>() // len -> seed

    fun getExistingData(rng: Int, action: (ByteArray, Int) -> Unit) {
        val ids = mutableListOf<Int>()
        ids.addAll(mapping.keys)
        if (ids.size > 0) {
            val key = ids[abs(rng % ids.size)]
            action(values[mapping[key]!!], key)
        }
    }

    fun getNotExistingKey(rng: Int, action: (Int) -> Unit) {
        val ids = MutableList(1000) { it }
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
        action(ByteArray(len) { (it + seed).toByte() })
    }

    fun testCreateValueNotExistingOk(data: ByteArray) {
        val key = kv.createValue(ByteArrayWrapper(data))
        if (verbose) {
            println("testCreateValueNotExistingOk $key ${data.map { it }}")
        }
        if (mapping[key] != null) {
            TODO()
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
        if (ByteArrayWrapperExt.getSize(value) != data.size) {
            TODO()
        }
        for (i in 0 until ByteArrayWrapperExt.getSize(value)) {
            if (ByteArrayWrapperExt.getBuf(value)[i] != data[i]) {
                TODO()
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
            // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_test_kv/src/commonMain/kotlin/lupos/launch/test_kv/MainFunc.kt:137"/*SOURCE_FILE_END*/ ) this is handled correctly
            flag = false
        }
        if (flag) {
            TODO()
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
        if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
            TODO()
        }
        kv = KeyValueStore(instance.bufferManager!!, rootPage, true, instance)
    }
    for ((k, v) in mapping) {
        testGetValueOk(values[v], k)
    }
    kv.delete()
    if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
        TODO()
    }
    if (instance.bufferManager!!.getNumberOfAllocatedPages() != 0) {
        TODO()
    }
    instance.bufferManager!!.close()
}
