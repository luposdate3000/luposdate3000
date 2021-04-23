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
package lupos.launch.test_triple_index_insert_sequentiel

import lupos.buffer_manager.BufferManager
import lupos.buffer_manager.BufferManagerExt
import lupos.operator.base.Query
import lupos.shared.AflCore
import lupos.shared.Parallel
import lupos.shared.TripleStoreIndex
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.operator.iterator.IteratorBundle
import lupos.triple_store_id_triple.TripleStoreIndexIDTriple
import kotlin.jvm.JvmField
import kotlin.math.abs

@JvmField
internal val verbose = false

@JvmField
internal val bufferSize = 100000

@JvmField
internal val maxLimit = 100000000

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("triple_index_insert_sequentiel.${BufferManagerExt.isInMemoryOnly}", 100.0, ::executeTest)(arg)
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    BufferManagerExt.allowInitFromDisk = false
    var bufferManager = BufferManager()
    val rootPage = bufferManager.allocPage(lupos.SOURCE_FILE)
    val order = intArrayOf(0, 1, 2)
    var index: TripleStoreIndex = TripleStoreIndexIDTriple(bufferManager, rootPage, false)
    var counterBuf = 0 // all s are '0' and p are '1', objects count from 0 until '$counter'
    var counterStore = 0 // all s are '0' and p are '1', objects count from 0 until '$counter'
    val insertBuffer = IntArray(bufferSize)
    var insertBufferSize = 0

    fun testInsertOk() {
        if (verbose) {
            println("testInsertOk")
        }
        if (insertBufferSize > 0) {
            counterStore += insertBufferSize / 3
            index.insertAsBulk(insertBuffer, order, insertBufferSize)
            insertBufferSize = 0
        }
    }

    fun prepareInsert() {
        if (insertBufferSize + 3 > insertBuffer.size) {
            testInsertOk()
        }
        var myS = 0
        var myP = 1
        var myO = 0x40000000.toInt() + counterBuf++
        if (verbose) {
            println("prepareInsert $myS $myP $myO")
        }
        insertBuffer[insertBufferSize++] = myS
        insertBuffer[insertBufferSize++] = myP
        insertBuffer[insertBufferSize++] = myO
    }

    fun verifyH(a: List<Int>, b: IntArray) {
        var ai = 0
        var bi = 0
        var flag = a.size != b.size
        if (!flag) {
            var i = 0
            while (!flag && i < a.size) {
                flag = a[i] != b[i]
                i++
            }
        }
        while (bi < b.size && ai < a.size) {
            if (b[bi] < a[ai]) {
                println("null       != 0x${b[bi].toString(16).padStart(8, '0')}")
                bi++
            } else if (b[bi] > a[ai]) {
                println("0x${a[ai].toString(16).padStart(8, '0')} != null      ")
                ai++
            } else {
                if (flag) {
                    println("0x${a[ai].toString(16).padStart(8, '0')} != 0x${b[bi].toString(16).padStart(8, '0')}")
                }
                ai++
                bi++
            }
        }
        while (bi < b.size) {
            println("null       != 0x${b[bi].toString(16).padStart(8, '0')}")
            bi++
        }
        while (ai < a.size) {
            println("0x${a[ai].toString(16).padStart(8, '0')} != null      ")
            ai++
        }
        if (flag) {
            println("actual != target")
            throw Exception("")
        }
    }

    fun verifyS(bundle: IteratorBundle, filter: IntArray) {
        if (verbose) {
            println("verifyS")
        }
        val iter = bundle.columns["s"]!!
        val target = IntArray(counterStore) { 0 }
        val actual = mutableListOf<Int>()
        var value = iter.next()
        while (value != DictionaryExt.nullValue) {
            actual.add(value)
            value = iter.next()
        }
        verifyH(actual, target)
    }

    fun verifyP(bundle: IteratorBundle, filter: IntArray) {
        if (verbose) {
            println("verifyP")
        }
        val iter = bundle.columns["p"]!!
        val target = IntArray(counterStore) { 1 }
        val actual = mutableListOf<Int>()
        var value = iter.next()
        while (value != DictionaryExt.nullValue) {
            actual.add(value)
            value = iter.next()
        }
        verifyH(actual, target)
    }

    fun verifyO(bundle: IteratorBundle, filter: IntArray) {
        if (verbose) {
            println("verifyO")
        }
        val iter = bundle.columns["o"]!!
        val target = IntArray(counterStore) { 0x40000000.toInt() + it }
        val actual = mutableListOf<Int>()
        var value = iter.next()
        while (value != DictionaryExt.nullValue) {
            actual.add(value)
            value = iter.next()
        }
        verifyH(actual, target)
    }

    fun testGetIterator_spo_Ok(filter: IntArray) {
        if (verbose) {
            println("testGetIterator_spo_Ok")
        }
        val query = Query()
        val bundle = index.getIterator(query, intArrayOf(), listOf("s", "p", "o"))
        if (bundle.columns.size != 3) {
            throw Exception("")
        }
        verifyS(bundle, filter)
        verifyP(bundle, filter)
        verifyO(bundle, filter)
    }
    if (hasNextRandom() > 0) {
        val limit = abs(nextRandom() % maxLimit)
        for (i in 0 until limit) {
            prepareInsert()
        }
    }
    testInsertOk()
    testGetIterator_spo_Ok(intArrayOf())
    index.close()
    if (bufferManager.getNumberOfReferencedPages() != 0) {
        throw Exception("")
    }
    index = TripleStoreIndexIDTriple(bufferManager, rootPage, true)
    testGetIterator_spo_Ok(intArrayOf())
    index.delete()
    if (bufferManager.getNumberOfReferencedPages() != 0) {
        throw Exception("")
    }
    if (bufferManager.getNumberOfAllocatedPages() != 0) {
        throw Exception("")
    }
    bufferManager.close()
}
