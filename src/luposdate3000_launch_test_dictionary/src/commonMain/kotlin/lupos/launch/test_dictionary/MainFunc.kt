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
package lupos.launch.test_dictionary

import lupos.buffermanager.BufferManager
import lupos.buffermanager.BufferManagerExt
import lupos.dictionary.ADictionary
import lupos.dictionary.DictionaryExt
import lupos.dictionary.DictionaryFactory
import lupos.dictionary.EDictionaryTypeExt
import lupos.dictionary.IDictionary
import lupos.dictionary.nodeGlobalDictionary
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.Parallel
import lupos.test.AflCore
import kotlin.math.abs
import kotlin.math.max

private val verbose = false

// private val maxSize = 16
private val maxSize = 16384

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("dictionary.${BufferManagerExt.isInMemoryOnly}", 1.0, ::executeTest)(arg)
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    for (isLocal in listOf(true, false)) {
        for (dictType in 0 until EDictionaryTypeExt.values_size) {
            resetRandom()
            BufferManagerExt.allowInitFromDisk = false
            var bufferManager = BufferManager()
            if (isLocal) {
                nodeGlobalDictionary = object : ADictionary() {
                    override fun close() {}
                    override fun delete() {}
                    override fun createNewBNode(): Int = throw Exception("not implemented")
                    override fun createValue(buffer: ByteArrayWrapper): Int = throw Exception("not implemented")
                    override fun getValue(buffer: ByteArrayWrapper, value: Int) = throw Exception("not implemented")
                    override fun hasValue(buffer: ByteArrayWrapper): Int? = null
                    override fun importFromDictionaryFile(filename: String): IntArray = throw Exception("not implemented")
                    override fun isInmemoryOnly(): Boolean = true
                }
            }
            var rootPage = -1
            fun createDict(initFromRootPage: Boolean): IDictionary {
                when (dictType) {
                    EDictionaryTypeExt.KV -> {
                        if (rootPage == -1) {
                            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                                rootPage = pageid
                            }
                            bufferManager.releasePage(lupos.SOURCE_FILE, rootPage)
                        }
                        return DictionaryFactory.createDictionary(dictType, false, bufferManager, rootPage, initFromRootPage)
                    }
                    else -> return DictionaryFactory.createDictionary(dictType, isLocal, bufferManager, -1, false)
                }
            }
            if (verbose) {
                println("executeTest $isLocal ${EDictionaryTypeExt.names[dictType]}--------------------------------------")
            }
            var dict = createDict(false)
            val values = mutableListOf<ByteArrayWrapper>(
                DictionaryExt.booleanTrueValue3,
                DictionaryExt.booleanFalseValue3,
                DictionaryExt.errorValue3,
                DictionaryExt.undefValue3,
            )
            val mapping = mutableMapOf<Int, Int>(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 3,
            ) // dict.id -> values.index

            var usedGenerators = mutableMapOf<Int, MutableSet<Int>>() // len -> seed

            fun getExistingData(rng: Int, action: (ByteArrayWrapper, Int) -> Unit) {
                val ids = mutableListOf<Int>()
                ids.addAll(mapping.keys)
                if (ids.size > 0) {
                    val key = ids[abs(rng % ids.size)]
                    action(values[mapping[key]!!], key)
                }
            }

            fun getNotExistingKey(rng: Int, action: (Int) -> Unit) {
                val ids = MutableList<Int>(1000) { it }
                ids.removeAll(mapping.values)
                if (ids.size > 0) {
                    val key = ids[abs(rng % ids.size)]
                    action(key)
                }
            }

            fun getNotExistingData(rng: Int, action: (ByteArrayWrapper) -> Unit) {
                var len = max(abs(rng / 256) % maxSize, 4)
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
                val res = ByteArrayWrapper()
                res.setSize(len)
                for (i in 0 until len) {
                    res.getBuf()[i] = (i + seed).toByte()
                }
                action(res)
            }

            fun testCreateValueExistingOk(data: ByteArrayWrapper, targetKey: Int) {
                if (verbose) {
                    println("testCreateValueExistingOk $targetKey ${data.getBuf().map { it }.subList(0, data.getSize())}")
                }
                val key = dict.createValue(data)
                if (key != targetKey) {
                    throw Exception("${key.toString(2)} ${targetKey.toString(2)}")
                }
            }

            fun testCreateValueNotExistingOk(data: ByteArrayWrapper) {
                val key = dict.createValue(data)
                if (verbose) {
                    println("testCreateValueNotExistingOk $key ${data.getBuf().map { it }.subList(0, data.getSize())}")
                }
                if (mapping[key] != null) {
                    throw Exception("")
                }
                mapping[key] = values.size
                values.add(data)
            }

            fun testHasValueExistingOk(data: ByteArrayWrapper, targetKey: Int) {
                if (verbose) {
                    println("testHasValueYesOk $targetKey ${data.getBuf().map { it }.subList(0, data.getSize())}")
                }
                val res = dict.hasValue(data)
                if (res != targetKey) {
                    throw Exception("$res $targetKey")
                }
            }

            fun testHasValueNotExistingOk(data: ByteArrayWrapper) {
                if (verbose) {
                    println("testHasValueNoOk ${data.getBuf().map { it }.subList(0, data.getSize())}")
                }
                val res = dict.hasValue(data)
                if (res != null) {
                    throw Exception("")
                }
            }

            fun testGetValueOk(target: ByteArrayWrapper, key: Int) {
                if (verbose) {
                    println("testGetValueOk $key ${target.getBuf().map { it }.subList(0, target.getSize())}")
                }
                val value = ByteArrayWrapper()
                dict.getValue(value, key)
                if (value.getSize() != target.getSize()) {
                    throw Exception("${value.getSize()} ${target.getSize()}")
                }
                for (i in 0 until value.getSize()) {
                    if (value.getBuf()[i] != target.getBuf()[i]) {
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
                    dict.getValue(buffer, key)
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
            if (!dict.isInmemoryOnly() && !BufferManagerExt.isInMemoryOnly) {
                dict.close()
                if (bufferManager.getNumberOfReferencedPages() != 0) {
                    throw Exception("")
                }
                dict = createDict(true)
            }
            for ((k, v) in mapping) {
                testGetValueOk(values[v], k)
            }
            dict.delete()
            if (bufferManager.getNumberOfReferencedPages() != 0) {
                throw Exception("")
            }
            if (bufferManager.getNumberOfAllocatedPages() != 0) {
                throw Exception("")
            }
            bufferManager.close()
        }
    }
}
