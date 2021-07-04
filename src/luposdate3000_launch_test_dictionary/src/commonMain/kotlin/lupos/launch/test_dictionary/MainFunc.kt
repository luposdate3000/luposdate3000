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

import lupos.buffer_manager.BufferManager
import lupos.buffer_manager.BufferManagerExt
import lupos.dictionary.ADictionary
import lupos.dictionary.DictionaryFactory
import lupos.shared.AflCore

import lupos.shared.ETripleComponentTypeExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.Parallel
import lupos.shared.SanityCheck
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.DictionaryValueType
import lupos.shared.inline.ByteArrayHelper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField
import kotlin.math.abs
import kotlin.math.max

@JvmField
internal val verbose = false

@JvmField
internal val maxSize = 16384

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("dictionary.${BufferManagerExt.isInMemoryOnly}", 1.0, ::executeTest)(arg)
}

internal fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    if (!SanityCheck.enabled) {
        return
    }
    for (isLocal in listOf(true, false)) {
        for (dictType in 0 until EDictionaryTypeExt.values_size) {
            if (isLocal && dictType == EDictionaryTypeExt.KV) {
                continue
            }
            var instance = Luposdate3000Instance()
            instance.allowInitFromDisk = false
            resetRandom()
            BufferManagerExt.allowInitFromDisk = false
            instance.bufferManager = BufferManager(instance)
            if (isLocal) {
                instance.nodeGlobalDictionary?.close()
                instance.nodeGlobalDictionary = object : ADictionary(instance, false) {
                    override fun forEachValue(buffer: ByteArrayWrapper, action: (DictionaryValueType) -> Unit): Unit = TODO()
                    override fun createNewUUID(): DictionaryValueType = TODO()
                    override fun close() {}
                    override fun delete() {}
                    override fun createNewBNode(): DictionaryValueType = TODO()
                    override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType = TODO()
                    override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType) = TODO()
                    override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType? = null
                    override fun isInmemoryOnly(): Boolean = true
                }
            }
            var rootPage = -1
            fun createDict(initFromRootPage: Boolean): IDictionary {
                when (dictType) {
                    EDictionaryTypeExt.KV -> {
                        if (rootPage == -1) {
                            rootPage = instance.bufferManager!!.allocPage("/src/luposdate3000/src/luposdate3000_launch_test_dictionary/src/commonMain/kotlin/lupos/launch/test_dictionary/MainFunc.kt:74")
                        }
                        return DictionaryFactory.createDictionary(dictType, false, instance.bufferManager!!, rootPage, initFromRootPage, instance)
                    }
                    else -> return DictionaryFactory.createDictionary(dictType, isLocal, instance.bufferManager!!, -1, false, instance)
                }
            }
            if (verbose) {
                println("executeTest $isLocal ${EDictionaryTypeExt.names[dictType]}--------------------------------------")
            }
            var dict = createDict(false)
            if (!isLocal) {
                instance.nodeGlobalDictionary = dict
            }
            val values = mutableListOf<ByteArrayWrapper>(
                DictionaryExt.booleanTrueValue3,
                DictionaryExt.booleanFalseValue3,
                DictionaryExt.errorValue3,
                DictionaryExt.undefValue3,
            )
            val mapping = mutableMapOf<DictionaryValueType, Int>(
                DictionaryValueHelper.fromInt(0) to 0,
                DictionaryValueHelper.fromInt(1) to 1,
                DictionaryValueHelper.fromInt(2) to 2,
                DictionaryValueHelper.fromInt(3) to 3,
            ) // dict.id -> values.index

            var usedGenerators = mutableMapOf<Int, MutableSet<Int>>() // len -> seed

            fun getExistingData(rng: Int, action: (ByteArrayWrapper, DictionaryValueType) -> Unit) {
                val ids = mutableListOf<DictionaryValueType>()
                ids.addAll(mapping.keys)
                if (ids.size > 0) {
                    val key = ids[abs(rng % ids.size)]
                    action(values[mapping[DictionaryValueHelper.toInt(key)]!!], key)
                }
            }

            fun getNotExistingKey(rng: Int, action: (DictionaryValueType) -> Unit) {
                val ids = MutableList<DictionaryValueType>(1000) { it }
                ids.removeAll(mapping.values)
                if (ids.size > 0) {
                    val key = ids[abs(rng % ids.size)]
                    action(key)
                }
            }

            fun getNotExistingData(rng: Int, action: (ByteArrayWrapper) -> Unit) {
                var len = max(abs(rng / 256) % maxSize, 4)
                var seed = abs(rng % 256)
                var loop = true
                while (loop) {
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
                    usedGenerators[len]!!.add(seed)
                    val res = ByteArrayWrapper()
                    ByteArrayWrapperExt.setSize(res, len)
                    for (i in 0 until len) {
                        ByteArrayWrapperExt.getBuf(res)[i] = (i + seed).toByte()
                    }
                    var x = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(res), 0)
                    var lastx = 0
                    while (x != lastx) {
                        lastx = x
                        if (x == ETripleComponentTypeExt.BLANK_NODE) {
                            x++
                        } else if (x == ETripleComponentTypeExt.BOOLEAN && len != 5) {
                            x++
                        } else if (x == ETripleComponentTypeExt.BOOLEAN && len == 5) {
                            ByteArrayWrapperExt.getBuf(res)[4] = abs(ByteArrayWrapperExt.getBuf(res)[4] % 2).toByte()
                        } else if (x == ETripleComponentTypeExt.ERROR && len != 4) {
                            x++
                        } else if (x == ETripleComponentTypeExt.UNDEF && len != 4) {
                            x++
                        } else {
                            x = abs(x % ETripleComponentTypeExt.values_size)
                        }
                    }
                    ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(res), 0, x)
                    if (values.contains(res)) {
                        if (seed < 255) {
                            seed++
                        } else {
                            len = (len + 1) % maxSize
                            seed = 0
                        }
                    } else {
                        loop = false
                        if (DictionaryHelper.byteArrayToType(res) == ETripleComponentTypeExt.BLANK_NODE) {
                            throw Exception("")
                        }
                        action(res)
                    }
                }
            }

            fun testCreateValueExistingOk(data: ByteArrayWrapper, targetKey: DictionaryValueType) {
                if (verbose) {
                    println("testCreateValueExistingOk $targetKey $data")
                }
                val key = dict.createValue(data)
                if (key != targetKey) {
                    throw Exception("${key.toString(2)} ${targetKey.toString(2)}")
                }
            }

            fun testCreateValueNotExistingOk(data: ByteArrayWrapper) {
                val key = dict.createValue(data)
                if (verbose) {
                    println("testCreateValueNotExistingOk $key $data")
                }
                if (mapping[DictionaryValueHelper.toInt(key)] != null) {
                    throw Exception("$key")
                }
                mapping[DictionaryValueHelper.toInt(key)] = values.size
                values.add(data)
            }

            fun testHasValueExistingOk(data: ByteArrayWrapper, targetKey: DictionaryValueType) {
                if (verbose) {
                    println("testHasValueYesOk $targetKey $data")
                }
                var res: DictionaryValueType? = null
                var flag = true
                val type = DictionaryHelper.byteArrayToType(data)
                val assumeCrash = isLocal || type in listOf(ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.UNDEF)
                try {
                    res = dict.hasValue(data)
                } catch (e: Throwable) {
                    if (!assumeCrash) {
                        e.printStackTrace()
                    }
                    flag = false
                }
                if (flag == assumeCrash) {
                    throw Exception("$flag $isLocal")
                }
                if (flag) {
                    if (res != targetKey) {
                        throw Exception("$res $targetKey")
                    }
                }
            }

            fun testHasValueNotExistingOk(data: ByteArrayWrapper) {
                if (verbose) {
                    println("testHasValueNoOk $data")
                }
                var res: DictionaryValueType? = null
                var flag = true
                val type = DictionaryHelper.byteArrayToType(data)
                val assumeCrash = isLocal || type in listOf(ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.UNDEF)
                try {
                    res = dict.hasValue(data)
                } catch (e: Throwable) {
                    if (!assumeCrash) {
                        e.printStackTrace()
                    }
                    flag = false
                }
                if (flag == assumeCrash) {
                    throw Exception("")
                }
                if (flag) {
                    if (res != null) {
                        throw Exception("")
                    }
                }
            }

            fun testGetValueOk(target: ByteArrayWrapper, key: DictionaryValueType) {
                if (verbose) {
                    println("testGetValueOk $key ${key.toString(2)} $target")
                }
                val value = ByteArrayWrapper()
                dict.getValue(value, key)
                if (ByteArrayWrapperExt.getSize(value) != ByteArrayWrapperExt.getSize(target)) {
                    throw Exception("${ByteArrayWrapperExt.getSize(value)} ${ByteArrayWrapperExt.getSize(target)} $value")
                }
                for (i in 0 until ByteArrayWrapperExt.getSize(value)) {
                    if (ByteArrayWrapperExt.getBuf(value)[i] != ByteArrayWrapperExt.getBuf(target)[i]) {
                        throw Exception("")
                    }
                }
            }

            fun testGetValueFail(key: DictionaryValueType) {
                if (verbose) {
                    println("testGetValueFail $key")
                }
                var flag = true
                try {
                    val buffer = ByteArrayWrapper()
                    dict.getValue(buffer, key)
                } catch (e: Throwable) {
                    // e.printStackTrace() this is handled correctly
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
                if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
                    throw Exception("")
                }
                dict = createDict(true)
            }
            for ((k, v) in mapping) {
                testGetValueOk(values[v], k)
            }
            dict.delete()
            if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
                throw Exception("")
            }
            if (instance.bufferManager!!.getNumberOfAllocatedPages() != 0) {
                throw Exception("")
            }
            instance.bufferManager!!.close()
        }
    }
}
