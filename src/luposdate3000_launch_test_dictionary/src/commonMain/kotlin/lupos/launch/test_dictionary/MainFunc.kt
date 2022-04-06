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
import lupos.shared.myPrintStackTrace

import lupos.buffer_manager.BufferManager
import lupos.buffer_manager.BufferManagerExt
import lupos.dictionary.ADictionary
import lupos.dictionary.DictionaryFactory
import lupos.shared.AflCore
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.Parallel
import lupos.shared.SanityCheck
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dictionary.EDictionaryTypeExt
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.math.abs
import kotlin.math.max

internal const val verbose = false

internal const val maxSize = 16384

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
            val instance = Luposdate3000Instance()
            instance.useDictionaryInlineEncoding = false
            instance.allowInitFromDisk = false
            instance.allowDistributedBNodeAssignment = false
            instance.dictionaryCacheCapacity = 0
            resetRandom()
            BufferManagerExt.allowInitFromDisk = false
            instance.bufferManager = BufferManager(instance)
            if (isLocal) {
                instance.nodeGlobalDictionary?.close()
                instance.nodeGlobalDictionary = object : ADictionary(instance, false) {
                    override fun forEachValue(buffer: ByteArrayWrapper, action: (DictionaryValueType) -> Unit): Unit = TODO()
                    override fun createNewUUID(): Int = TODO()
                    override fun close() {}
                    override fun delete() {}
                    override fun createNewBNode(): DictionaryValueType = TODO()
                    override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType = TODO()
                    override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType) = TODO()
                    override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType = DictionaryValueHelper.nullValue
                    override fun isInmemoryOnly(): Boolean = true
                }
            }
            var rootPage = -1
            fun createDict(initFromRootPage: Boolean): IDictionary {
                return when (dictType) {
                    EDictionaryTypeExt.KV -> {
                        if (rootPage == -1) {
                            rootPage = instance.bufferManager!!.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_test_dictionary/src/commonMain/kotlin/lupos/launch/test_dictionary/MainFunc.kt:84"/*SOURCE_FILE_END*/)
                        }
                        DictionaryFactory.createDictionary(dictType, false, instance.bufferManager!!, rootPage, initFromRootPage, instance)
                    }
                    else -> DictionaryFactory.createDictionary(dictType, isLocal, instance.bufferManager!!, -1, false, instance)
                }
            }
            if (verbose) {
                println("executeTest $isLocal ${EDictionaryTypeExt.names[dictType]}--------------------------------------")
            }
            var dict = createDict(false)
            if (!isLocal) {
                instance.nodeGlobalDictionary = dict
            }
            val values = mutableMapOf(
                DictionaryValueHelper.fromInt(0) to DictionaryExt.booleanTrueValue3,
                DictionaryValueHelper.fromInt(1) to DictionaryExt.booleanFalseValue3,
                DictionaryValueHelper.fromInt(2) to DictionaryExt.errorValue3,
                DictionaryValueHelper.fromInt(3) to DictionaryExt.undefValue3,
            )
            val mapping = mutableMapOf(
                DictionaryValueHelper.fromInt(0) to DictionaryValueHelper.fromInt(0),
                DictionaryValueHelper.fromInt(1) to DictionaryValueHelper.fromInt(1),
                DictionaryValueHelper.fromInt(2) to DictionaryValueHelper.fromInt(2),
                DictionaryValueHelper.fromInt(3) to DictionaryValueHelper.fromInt(3),
            ) // dict.id -> values.index

            val usedGenerators = mutableMapOf<Int, MutableSet<Int>>() // len -> seed

            fun getExistingData(rng: Int, action: (ByteArrayWrapper, DictionaryValueType) -> Unit) {
                val ids = mutableListOf<DictionaryValueType>()
                ids.addAll(mapping.keys)
                if (ids.size > 0) {
                    val key = ids[abs(rng % ids.size)]
                    action(values[mapping[key]!!]!!, key)
                }
            }

            fun getNotExistingKey(rng: Int, action: (DictionaryValueType) -> Unit) {
                val ids = MutableList(1000) { DictionaryValueHelper.fromInt(it) }
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
                    usedGenerators[len]!!.add(seed)
                    val res = ByteArrayWrapper()
                    ByteArrayWrapperExt.setSize(res, len, false)
                    for (i in 0 until len) {
                        ByteArrayWrapperExt.getBuf(res)[i] = (i + seed).toByte()
                    }
                    val x = DictionaryHelper.headerDecodeType(res)
                    val flg = DictionaryHelper.headerDecodeFlag(res)
                    if (x == ETripleComponentTypeExt.BLANK_NODE) {
                        DictionaryHelper.errorToByteArray(res)
                    } else if (x == ETripleComponentTypeExt.BOOLEAN) {
                        DictionaryHelper.booleanToByteArray(res, flg % 2 == 0)
                    } else if (x == ETripleComponentTypeExt.ERROR) {
                        DictionaryHelper.errorToByteArray(res)
                    } else if (x == ETripleComponentTypeExt.UNDEF) {
                        DictionaryHelper.undefToByteArray(res)
                    } else if (x < ETripleComponentTypeExt.values_size) {
                        DictionaryHelper.headerEncode(res, x, flg)
                    } else {
                        DictionaryHelper.errorToByteArray(res)
                    }
                    if (values.values.contains(res)) {
                        if (seed < 255) {
                            seed++
                        } else {
                            len = (len + 1) % maxSize
                            seed = 0
                        }
                    } else {
                        loop = false
                        if (DictionaryHelper.byteArrayToType(res) == ETripleComponentTypeExt.BLANK_NODE) {
TODO()
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
                    TODO("${key.toString(2)} ${targetKey.toString(2)}")
                }
            }

            fun testCreateValueNotExistingOk(data: ByteArrayWrapper) {
                val key = dict.createValue(data)
                if (verbose) {
                    println("testCreateValueNotExistingOk $key $data")
                }
                if (mapping[key] != null) {
                    TODO("luposdate3000_launch_test_dictionary.MainFunc.testCreateValueNotExistingOk $key")
                }
                mapping[key] = DictionaryValueHelper.fromInt(values.size)
                values[DictionaryValueHelper.fromInt(values.size)] = data
            }

            fun testGetValueOk(target: ByteArrayWrapper, key: DictionaryValueType) {
                if (verbose) {
                    println("testGetValueOk $key ${key.toString(2)} $target")
                }
                val value = ByteArrayWrapper()
                dict.getValue(value, key)
                if (ByteArrayWrapperExt.getSize(value) != ByteArrayWrapperExt.getSize(target)) {
                    TODO("${ByteArrayWrapperExt.getSize(value)} ${ByteArrayWrapperExt.getSize(target)} $value")
                }
                for (i in 0 until ByteArrayWrapperExt.getSize(value)) {
                    if (ByteArrayWrapperExt.getBuf(value)[i] != ByteArrayWrapperExt.getBuf(target)[i]) {
                        TODO()
                    }
                }
            }

            fun testGetValueFail(key: DictionaryValueType) {
                if (verbose) {
                    println("testGetValueFail $key")
                }
                var flag = true
                val buffer = ByteArrayWrapper()
                try {
                    dict.getValue(buffer, key)
                } catch (e: Throwable) {
                    // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_test_dictionary/src/commonMain/kotlin/lupos/launch/test_dictionary/MainFunc.kt:237"/*SOURCE_FILE_END*/ ) this is handled correctly
                    flag = false
                }
                if (flag && (DictionaryHelper.byteArrayToType(buffer) != ETripleComponentTypeExt.BLANK_NODE)) {
                    TODO("${key.toString(16)} $buffer ${DictionaryHelper.byteArrayToType(buffer)}")
                }
            }

            while (hasNextRandom() >= 2) {
                val mode = abs(nextRandom() % 4)
                val rng = nextRandom()
                when (mode) {
                    0 -> getExistingData(rng) { v, k -> testCreateValueExistingOk(v, k) }
                    1 -> getNotExistingData(rng) { v -> testCreateValueNotExistingOk(v) }

                    2 -> getExistingData(rng) { v, k -> testGetValueOk(v, k) }
                    3 -> getNotExistingKey(rng) { k -> testGetValueFail(k) }
                }
            }
            for ((k, v) in mapping) {
                testGetValueOk(values[v]!!, k)
            }
            if (!dict.isInmemoryOnly() && !BufferManagerExt.isInMemoryOnly) {
                dict.close()
                if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
                    TODO()
                }
                dict = createDict(true)
            }
            for ((k, v) in mapping) {
                testGetValueOk(values[v]!!, k)
            }
            dict.delete()
            if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
                TODO()
            }
            if (instance.bufferManager!!.getNumberOfAllocatedPages() != 0) {
                TODO()
            }
            instance.bufferManager!!.close()
        }
    }
}
