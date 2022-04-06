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
package lupos.launch.test_triple_index

import lupos.buffer_manager.BufferManager
import lupos.buffer_manager.BufferManagerExt
import lupos.operator.base.Query
import lupos.shared.AflCore
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.Luposdate3000Instance
import lupos.shared.Parallel
import lupos.shared.TripleStoreIndex
import lupos.shared.operator.iterator.IteratorBundle
import lupos.triple_store_id_triple.TripleStoreIndexIDTriple
import kotlin.jvm.JvmField
import kotlin.math.abs

internal const val verbose = false

@JvmField
internal var duplicates = 0L

@JvmField
internal var totalinserts = 0L

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("triple_index.${BufferManagerExt.isInMemoryOnly}", 10000.0, ::executeTest)(arg)
}

internal fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    val instance = Luposdate3000Instance()
    instance.allowInitFromDisk = false
    var maxClearCalls = 10
    BufferManagerExt.allowInitFromDisk = false
    instance.bufferManager = BufferManager(instance)
    val rootPage = instance.bufferManager!!.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_launch_test_triple_index/src/commonMain/kotlin/lupos/launch/test_triple_index/MainFunc.kt:52"/*SOURCE_FILE_END*/)
    val order = intArrayOf(0, 1, 2)
    var index: TripleStoreIndex = TripleStoreIndexIDTriple(instance.bufferManager!!, rootPage, false)
    val dataBuffer = mutableSetOf<DictionaryValueType>() // 2Bytes S, 1 Byte P, 1 Byte O -> this allows fast and easy sorting
    val insertBuffer = DictionaryValueTypeArray(3000)
    var insertBufferSize = 0
    val deleteBuffer = DictionaryValueTypeArray(3000)
    var deleteBufferSize = 0
    fun mergeSPO(s: DictionaryValueType, p: DictionaryValueType, o: DictionaryValueType): DictionaryValueType {
        return DictionaryValueHelper.fromInt((DictionaryValueHelper.toInt(s) and 0x7fff0000) or ((DictionaryValueHelper.toInt(p) and 0x7f000000) shr 16) or ((DictionaryValueHelper.toInt(o) and 0x7f000000) shr 24))
    }

    fun splitSPO(v: DictionaryValueType, action: (DictionaryValueType, DictionaryValueType, DictionaryValueType) -> Unit) {
        action(DictionaryValueHelper.fromInt(DictionaryValueHelper.toInt(v) and 0x7fff0000), DictionaryValueHelper.fromInt(((DictionaryValueHelper.toInt(v) and 0x00007f00) shl 16)), DictionaryValueHelper.fromInt(((DictionaryValueHelper.toInt(v) and 0x0000007f) shl 24)))
    }

    fun filterArrToFun(filter: DictionaryValueTypeArray): (DictionaryValueType) -> Boolean {
        var res: (DictionaryValueType) -> Boolean = { true }
        when (filter.size) {
            1 -> {
                res = { it ->
                    var x = true
                    splitSPO(it) { s, p, o ->
                        x = s == filter[0]
                    }
                    x
                }
            }
            2 -> {
                res = { it ->
                    var x = true
                    splitSPO(it) { s, p, o ->
                        x = s == filter[0] && p == filter[1]
                    }
                    x
                }
            }
            3 -> {
                res = { it ->
                    var x = true
                    splitSPO(it) { s, p, o ->
                        x = s == filter[0] && p == filter[1] && o == filter[2]
                    }
                    x
                }
            }
        }
        return res
    }

    fun testInsertOk() {
        if (verbose) {
            println("testInsertOk ${insertBuffer.take(insertBufferSize).map { "0x${it.toString(16).padStart(8, '0')}" }}")
        }
        if (insertBufferSize > 0) {
            var i = 0
            while (i < insertBufferSize) {
                val tmp = mergeSPO(insertBuffer[i + 0], insertBuffer[i + 1], insertBuffer[i + 2])
                val x = dataBuffer.add(tmp)
                if (verbose) {
                    println("dataBuffer.add(0x${insertBuffer[i + 0].toString(16).padStart(8, '0')},0x${insertBuffer[i + 1].toString(16).padStart(8, '0')},0x${insertBuffer[i + 2].toString(16).padStart(8, '0')}) -> $x")
                }
                i += 3
                totalinserts++
                if (!x) {
                    duplicates++
                }
            }
            index.insertAsBulk(insertBuffer, order, insertBufferSize, false)
            insertBufferSize = 0
        }
    }

    fun prepareInsert(rng: Int) {
        if (insertBufferSize + 3 > insertBuffer.size) {
            testInsertOk()
        }
        var myRng = DictionaryValueHelper.fromInt(abs(rng % 2147483647)) // prevent sign extension - and mapping from "-2147483648" to itself
        var myS: DictionaryValueType = 0
        var myP: DictionaryValueType = 0
        var myO: DictionaryValueType = 0
        splitSPO(myRng) { s, p, o ->
            myS = s
            myP = p
            myO = o
        }
        myRng = mergeSPO(myS, myP, myO)
        if (verbose) {
            println("prepareInsert $myRng")
        }
        splitSPO(myRng) { s, p, o ->
            if (myS != s || myP != p || myO != o) {
                TODO("0b${myS.toString(2)} 0b${s.toString(2)} : 0b${myP.toString(2)} 0b${p.toString(2)} : 0b${myO.toString(2)} 0b${o.toString(2)} : $myRng")
            }
        }
        insertBuffer[insertBufferSize++] = myS
        insertBuffer[insertBufferSize++] = myP
        insertBuffer[insertBufferSize++] = myO
    }

    fun testDeleteOk() {
        if (verbose) {
            println("testDeleteOk ${deleteBuffer.take(deleteBufferSize).map { "0x${it.toString(16).padStart(8, '0')}" }}")
        }
        if (deleteBufferSize > 0) {
            var i = 0
            while (i < deleteBufferSize) {
                val tmp = mergeSPO(deleteBuffer[i + 0], deleteBuffer[i + 1], deleteBuffer[i + 2])
                val x = dataBuffer.remove(tmp)
                if (verbose) {
                    println("dataBuffer.remove(0x${deleteBuffer[i + 0].toString(16).padStart(8, '0')},0x${deleteBuffer[i + 1].toString(16).padStart(8, '0')},0x${deleteBuffer[i + 2].toString(16).padStart(8, '0')}) -> $x")
                }
                i += 3
            }
            index.removeAsBulk(deleteBuffer, order, deleteBufferSize, false)
            deleteBufferSize = 0
        }
    }

    fun prepareDelete(rng: Int) {
        if (verbose) {
            println("prepareDelete $rng")
        }
        if (deleteBufferSize + 3 > deleteBuffer.size) {
            testDeleteOk()
        }
        var myRng = DictionaryValueHelper.fromInt(
            if (rng == 0) {
                1
            } else if (rng < 0) {
                -rng
            } else {
                rng
            }
        )
        var myS: DictionaryValueType = 0
        var myP: DictionaryValueType = 0
        var myO: DictionaryValueType = 0
        splitSPO(myRng) { s, p, o ->
            myS = s
            myP = p
            myO = o
        }
        myRng = mergeSPO(myS, myP, myO)
        splitSPO(myRng) { s, p, o ->
            if (myS != s || myP != p || myO != o) {
                TODO("")
            }
        }
        deleteBuffer[deleteBufferSize++] = myS
        deleteBuffer[deleteBufferSize++] = myP
        deleteBuffer[deleteBufferSize++] = myO
    }

    fun testFlushOk() {
        if (verbose) {
            println("testFlushOk")
        }
        index.flush()
    }

    fun testClearOk() {
        if (maxClearCalls > 0) {
            maxClearCalls--
            if (verbose) {
                println("testClearOk")
            }
            dataBuffer.clear()
            index.clear()
        }
    }

    fun verifyH(a: List<DictionaryValueType>, b: List<DictionaryValueType>) {
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
            TODO("")
        }
    }

    fun verifyS(bundle: IteratorBundle, filter: DictionaryValueTypeArray) {
        if (verbose) {
            println("verifyS")
        }
        val iter = bundle.columns["s"]!!
        val target = dataBuffer.filter(filterArrToFun(filter)).sorted().map {
            var res: DictionaryValueType = 0
            splitSPO(it) { s, p, o ->
                res = s
            }
            res
        }
        val actual = mutableListOf<DictionaryValueType>()
        var value = iter.next()
        while (value != DictionaryValueHelper.nullValue) {
            actual.add(value)
            value = iter.next()
        }
        verifyH(actual, target)
    }

    fun verifyP(bundle: IteratorBundle, filter: DictionaryValueTypeArray) {
        if (verbose) {
            println("verifyP")
        }
        val iter = bundle.columns["p"]!!
        val target = dataBuffer.filter(filterArrToFun(filter)).sorted().map {
            var res: DictionaryValueType = 0
            splitSPO(it) { s, p, o ->
                res = p
            }
            res
        }
        val actual = mutableListOf<DictionaryValueType>()
        var value = iter.next()
        while (value != DictionaryValueHelper.nullValue) {
            actual.add(value)
            value = iter.next()
        }
        verifyH(actual, target)
    }

    fun verifyO(bundle: IteratorBundle, filter: DictionaryValueTypeArray) {
        if (verbose) {
            println("verifyO")
        }
        val iter = bundle.columns["o"]!!
        val target = dataBuffer.filter(filterArrToFun(filter)).sorted().map {
            var res: DictionaryValueType = 0
            splitSPO(it) { s, p, o ->
                res = o
            }
            res
        }
        val actual = mutableListOf<DictionaryValueType>()
        var value = iter.next()
        while (value != DictionaryValueHelper.nullValue) {
            actual.add(value)
            value = iter.next()
        }
        verifyH(actual, target)
    }

    fun verifyCount(bundle: IteratorBundle, filter: DictionaryValueTypeArray) {
        if (verbose) {
            println("verifyCount")
        }
        if (bundle.count() != dataBuffer.filter(filterArrToFun(filter)).size) {
            TODO("")
        }
    }

    fun trimListToFilter(filter: Int, list: List<String>): List<String> {
        return when (filter) {
            1 -> listOf(list[1], list[2])
            2 -> listOf(list[2])
            3 -> listOf()
            else -> list
        }
    }

    fun testGetIterator_sxx_Ok(filter: DictionaryValueTypeArray) {
        if (verbose) {
            println("testGetIterator_sxx_Ok ${filter.map { it }}")
        }
        val query = Query(instance)
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("s", "_", "_")))
        when (filter.size) {
            0 -> {
                if (bundle.columns.size != 1) {
                    TODO("")
                }
                verifyS(bundle, filter)
            }
            1, 2, 3 -> verifyCount(bundle, filter)
        }
    }

    fun testGetIterator_spx_Ok(filter: DictionaryValueTypeArray) {
        if (verbose) {
            println("testGetIterator_spx_Ok ${filter.map { it }}")
        }
        val query = Query(instance)
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("s", "p", "_")))
        when (filter.size) {
            0 -> {
                if (bundle.columns.size != 2) {
                    TODO("")
                }
                verifyS(bundle, filter)
                verifyP(bundle, filter)
            }
            1 -> {
                if (bundle.columns.size != 1) {
                    TODO("")
                }
                verifyP(bundle, filter)
            }
            2, 3 -> verifyCount(bundle, filter)
        }
    }

    fun testGetIterator_spo_Ok(filter: DictionaryValueTypeArray) {
        if (verbose) {
            println("testGetIterator_spo_Ok ${filter.map { it }}")
        }
        val query = Query(instance)
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("s", "p", "o")))
        when (filter.size) {
            0 -> {
                if (bundle.columns.size != 3) {
                    TODO("")
                }
                verifyS(bundle, filter)
                verifyP(bundle, filter)
                verifyO(bundle, filter)
            }
            1 -> {
                if (bundle.columns.size != 2) {
                    TODO("")
                }
                verifyP(bundle, filter)
                verifyO(bundle, filter)
            }
            2 -> {
                if (bundle.columns.size != 1) {
                    TODO("")
                }
                verifyO(bundle, filter)
            }
            3 -> verifyCount(bundle, filter)
        }
    }

    fun testGetIterator_xxx_Ok(filter: DictionaryValueTypeArray) {
        if (verbose) {
            println("testGetIterator_xxx_Ok ${filter.map { it }}")
        }
        val query = Query(instance)
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("_", "_", "_")))
        verifyCount(bundle, filter)
    }

    fun getFilter(mode: Int, rng: Int, action: (DictionaryValueTypeArray) -> Unit) {
        if (dataBuffer.size == 0) {
            action(DictionaryValueHelper.DictionaryValueTypeArrayOf())
        } else {
            val tmp = dataBuffer.toList()[abs(rng % dataBuffer.size)]
            when (mode) {
                0 -> {
                    action(DictionaryValueHelper.DictionaryValueTypeArrayOf())
                }
                1 -> {
                    splitSPO(tmp) { s, p, o ->
                        action(DictionaryValueHelper.DictionaryValueTypeArrayOf(s))
                    }
                }
                2 -> {
                    splitSPO(tmp) { s, p, o ->
                        action(DictionaryValueHelper.DictionaryValueTypeArrayOf(s, p))
                    }
                }
                3 -> {
                    splitSPO(tmp) { s, p, o ->
                        action(DictionaryValueHelper.DictionaryValueTypeArrayOf(s, p, o))
                    }
                }
            }
        }
    }
    while (hasNextRandom() >= 3) {
        val mode = abs(nextRandom() % 101)
        prepareInsert(nextRandom())
        val rng = nextRandom()
        when (mode) {
            0, 1 -> prepareDelete(rng)
            2 -> testDeleteOk()
            3 -> testInsertOk()
            4 -> testFlushOk()
            5 -> testClearOk()
            6 -> getFilter(0, rng) { testGetIterator_sxx_Ok(it) }
            7 -> getFilter(0, rng) { testGetIterator_spx_Ok(it) }
            8 -> getFilter(0, rng) { testGetIterator_spo_Ok(it) }
            9 -> getFilter(0, rng) { testGetIterator_xxx_Ok(it) }
            10 -> getFilter(1, rng) { testGetIterator_sxx_Ok(it) }
            11 -> getFilter(1, rng) { testGetIterator_spx_Ok(it) }
            12 -> getFilter(1, rng) { testGetIterator_spo_Ok(it) }
            13 -> getFilter(1, rng) { testGetIterator_xxx_Ok(it) }
            14 -> getFilter(2, rng) { testGetIterator_sxx_Ok(it) }
            15 -> getFilter(2, rng) { testGetIterator_spx_Ok(it) }
            16 -> getFilter(2, rng) { testGetIterator_spo_Ok(it) }
            17 -> getFilter(2, rng) { testGetIterator_xxx_Ok(it) }
            18 -> getFilter(3, rng) { testGetIterator_sxx_Ok(it) }
            19 -> getFilter(3, rng) { testGetIterator_spx_Ok(it) }
            20 -> getFilter(3, rng) { testGetIterator_spo_Ok(it) }
            21 -> getFilter(3, rng) { testGetIterator_xxx_Ok(it) }
            else -> prepareInsert(rng)
        }
    }
    testDeleteOk()
    testInsertOk()
    getFilter(0, 0) { testGetIterator_spo_Ok(it) }
    index.close()
    if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
        TODO("")
    }
    index = TripleStoreIndexIDTriple(instance.bufferManager!!, rootPage, true)
    getFilter(0, 0) { testGetIterator_spo_Ok(it) }
    index.delete()
    if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
        TODO("")
    }
    if (instance.bufferManager!!.getNumberOfAllocatedPages() != 0) {
        TODO("")
    }
    instance.bufferManager!!.close()
}
