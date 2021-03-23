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

import lupos.SOURCE_FILE
import lupos.buffermanager.BufferManager
import lupos.kv.KeyValueStore
import lupos.s00misc.Parallel
import lupos.s00misc.SanityCheck
import lupos.test.AflCore
import kotlin.math.abs

private val verbose = false

private val maxSize = 16384

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("kv", 1, ::executeTest)(arg)
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int) {
    var bufferManager = BufferManager()
    var rootPage = -1
    bufferManager.createPage { page, pageid ->
        SanityCheck.println_buffermanager { "BufferManager.createPage($pageid) : $SOURCE_FILE" }
        rootPage = pageid
    }
    SanityCheck.println_buffermanager { "BufferManager.releasePage($rootPage) : $SOURCE_FILE" }
    bufferManager.releasePage(rootPage)
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

    fun testCreateValueExistingOk(data: ByteArray, targetKey: Int) {
        if (verbose) {
            println("testCreateValueExistingOk $targetKey ${data.map { it }}")
        }
        val key = kv.createValue(data)
        if (mapping[key] != targetKey) {
            throw Exception("")
        }
    }

    fun testCreateValueNotExistingOk(data: ByteArray) {
        val key = kv.createValue(data)
        if (verbose) {
            println("testCreateValueNotExistingOk $key ${data.map { it }}")
        }
        if (mapping[key] != null) {
            throw Exception("")
        }
        mapping[key] = values.size
        values.add(data)
    }

    fun testHasValueExistingOk(data: ByteArray, targetKey: Int) {
        if (verbose) {
            println("testHasValueYesOk $targetKey ${data.map { it }}")
        }
        val res = kv.hasValue(data)
        if (res != targetKey) {
            throw Exception("$res $targetKey")
        }
    }

    fun testHasValueNotExistingOk(data: ByteArray) {
        if (verbose) {
            println("testHasValueNoOk ${data.map { it }}")
        }
        val res = kv.hasValue(data)
        if (res != null) {
            throw Exception("")
        }
    }

    fun testGetValueOk(data: ByteArray, key: Int) {
        if (verbose) {
            println("testGetValueOk $key ${data.map { it }}")
        }
        val value = kv.getValue(key)
        val target = values[mapping[key]!!]
        if (value.size != target.size) {
            throw Exception("")
        }
        for (i in 0 until value.size) {
            if (value[i] != target[i]) {
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
            kv.getValue(key)
        } catch (e: Throwable) {
            flag = false
        }
        if (flag) {
            throw Exception("")
        }
    }

    while (hasNextRandom() >= 2) {
        val mode = abs(nextRandom() % 6)
        val rng = nextRandom()
        when (mode) {
            0 -> getExistingData(rng) { v, k -> testCreateValueExistingOk(v, k) }
            1 -> getNotExistingData(rng) { v -> testCreateValueNotExistingOk(v) }

            2 -> getExistingData(rng) { v, k -> testHasValueExistingOk(v, k) }
            3 -> getNotExistingData(rng) { v -> testHasValueNotExistingOk(v) }

            4 -> getExistingData(rng) { v, k -> testGetValueOk(v, k) }
            5 -> getNotExistingKey(rng) { k -> testGetValueFail(k) }
        }
    }
    for ((k, v) in mapping) {
        testGetValueOk(values[v], k)
    }
    kv.close()
    if (bufferManager.getNumberOfReferencedPages() != 0) {
        throw Exception("")
    }
    kv = KeyValueStore(bufferManager, rootPage, true)
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
