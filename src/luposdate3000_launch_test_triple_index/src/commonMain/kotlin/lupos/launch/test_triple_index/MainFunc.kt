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

import lupos.buffermanager.BufferManager
import lupos.dictionary.DictionaryExt
import lupos.s00misc.DateHelperRelative
import lupos.s00misc.File
import lupos.s00misc.Parallel
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s05tripleStore.TripleStoreIndex
import lupos.s05tripleStore.TripleStoreIndexIDTriple
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow

private val verbose = false

private class MyRandom(var seed: Long) {
    val bits = 32
    fun nextInt(): Int {
        seed = (seed * 0x5DEECE66DL + 0xBL) and ((1L shl 48) - 1)
        return (seed shr (48 - bits)).toInt()
    }
}

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
/*
 if arg is an long, than random tests are executed
 otherwise it is assumed, that arg is a filename, which is then used as input data
 */
    var seed = 0L
    var dataoff: Int = 0
    try {
        val data = IntArray(65536)
        seed = arg.toLong()
        // randomized testing
        var tests = 0
        var errors = 0
        val timer = DateHelperRelative.markNow()
        val random = MyRandom(seed)
        Parallel.launch {
            while (true) {
                val time = DateHelperRelative.elapsedSeconds(timer)
                println("tests $tests, errors $errors testsPerSecond ${tests / time}")
                Parallel.delay(1000)
            }
        }
        while (true) {
            val maxlen = min((tests + 2).toDouble().pow(1.0 / 2.0).toInt(), data.size)
            val cnt = abs(random.nextInt() % maxlen)
            for (i in 0 until cnt) {
                val tmp = random.nextInt()
                data[i] = tmp
            }
            var tmp = data.contentHashCode()
            if (tmp < 0) {
                tmp = -tmp
            }
            var testCase = "test_triple_index_${tmp.toString(16)}.data"
            if (verbose) {
                println("case $tests :: $cnt $testCase")
            }
            try {
                dataoff = 0
                executeTest(nextRandom = { data[dataoff++] }, hasNextRandom = { cnt - dataoff })
            } catch (e: Throwable) {
                e.printStackTrace()
                errors++
                File("erroredTests").mkdirs()
                println("errored $tests :: $dataoff $testCase")
                File("erroredTests/$testCase").withOutputStream {
                    for (i in 0 until dataoff) {
                        it.writeInt(data[i])
                    }
                }
            }
            tests++
        }
    } catch (e: Throwable) {
// verification of single testcase
        val f = File(arg)
        val data = IntArray((f.length() / 4).toInt())
        f.withInputStream {
            for (i in 0 until data.size) {
                data[i] = it.readInt()
            }
        }
        var dataoff = 0
        executeTest(nextRandom = { data[dataoff++] }, hasNextRandom = { data.size - dataoff })
    }
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int) {
    var bufferManager = BufferManager()
    var rootPage = -1
    bufferManager.createPage { page, pageid ->
        rootPage = pageid
    }
    bufferManager.releasePage(rootPage)
    val order = intArrayOf(0, 1, 2)
    var index: TripleStoreIndex = TripleStoreIndexIDTriple(bufferManager, rootPage, false)
    val dataBuffer = mutableSetOf<Int>() // 2Bytes S, 1 Byte P, 1 Byte O -> this allows fast and easy sorting
    val insertBuffer = IntArray(3000)
    var insertBufferSize = 0
    val deleteBuffer = IntArray(3000)
    var deleteBufferSize = 0
    fun mergeSPO(s: Int, p: Int, o: Int): Int = (s and 0x8fff0000.toInt()) + ((p and 0x8f000000.toInt()) shr 16) + ((o and 0x8f000000.toInt()) shr 24)
    fun splitSPO(v: Int, action: (Int, Int, Int) -> Unit) = action(v and 0x8fff0000.toInt(), (v and 0x00008f00.toInt()) shl 16, (v and 0x0000008f.toInt()) shl 24)
    fun filterArrToFun(filter: IntArray): (Int) -> Boolean {
        var res: (Int) -> Boolean = { true }
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
        var i = 0
        while (i < insertBufferSize) {
            dataBuffer.add(mergeSPO(insertBuffer[i + 0], insertBuffer[i + 1], insertBuffer[i + 2]))
            i += 3
        }
        index.insertAsBulk(insertBuffer, order, insertBufferSize)
        insertBufferSize = 0
    }

    fun prepareInsert(rng: Int) {
        if (insertBufferSize + 3 > insertBuffer.size) {
            testInsertOk()
        }
        var myRng = abs(rng)
        var myS = 0
        var myP = 0
        var myO = 0
        splitSPO(myRng) { s, p, o ->
            myS = s
            myP = p
            myO = o
        }
        myRng = mergeSPO(myS, myP, myO)
        splitSPO(myRng) { s, p, o ->
            if (myS != s || myP != p || myO != o) {
                throw Exception("")
            }
        }
        insertBuffer[insertBufferSize++] = myS
        insertBuffer[insertBufferSize++] = myP
        insertBuffer[insertBufferSize++] = myO
    }

    fun testDeleteOk() {
        var i = 0
        while (i < deleteBufferSize) {
            dataBuffer.remove(mergeSPO(deleteBuffer[i + 0], deleteBuffer[i + 1], deleteBuffer[i + 2]))
            i += 3
        }
        index.removeAsBulk(deleteBuffer, order, deleteBufferSize)
        deleteBufferSize = 0
    }

    fun prepareDelete(rng: Int) {
        if (deleteBufferSize + 3 > deleteBuffer.size) {
            testDeleteOk()
        }
        var myRng = if (rng == 0) {
            1
        } else if (rng < 0) {
            -rng
        } else {
            rng
        }
        var myS = 0
        var myP = 0
        var myO = 0
        splitSPO(myRng) { s, p, o ->
            myS = s
            myP = p
            myO = o
        }
        myRng = mergeSPO(myS, myP, myO)
        splitSPO(myRng) { s, p, o ->
            if (myS != s || myP != p || myO != o) {
                throw Exception("")
            }
        }
        deleteBuffer[deleteBufferSize++] = myS
        deleteBuffer[deleteBufferSize++] = myP
        deleteBuffer[deleteBufferSize++] = myO
    }

    fun testFlushOk() {
        index.flush()
    }

    fun testClearOk() {
        dataBuffer.clear()
        index.clear()
    }

    fun verifyS(bundle: IteratorBundle, filter: IntArray) {
        val iter = bundle.columns["s"]!!
        val list = dataBuffer.filter(filterArrToFun(filter)).sorted()
        var listIdx = 0
        var value = iter.next()
        while (value != DictionaryExt.nullValue) {
            splitSPO(list[listIdx]) { s, p, o ->
                if (s != value) {
                    throw Exception("")
                }
            }
            listIdx++
            value = iter.next()
        }
        if (listIdx < list.size) {
            throw Exception("")
        }
    }

    fun verifyP(bundle: IteratorBundle, filter: IntArray) {
        val iter = bundle.columns["p"]!!
        val list = dataBuffer.filter(filterArrToFun(filter)).sorted()
        var listIdx = 0
        var value = iter.next()
        while (value != DictionaryExt.nullValue) {
            splitSPO(list[listIdx]) { s, p, o ->
                if (p != value) {
                    throw Exception("")
                }
            }
            listIdx++
            value = iter.next()
        }
        if (listIdx < list.size) {
            throw Exception("")
        }
    }

    fun verifyO(bundle: IteratorBundle, filter: IntArray) {
        val iter = bundle.columns["o"]!!
        val list = dataBuffer.filter(filterArrToFun(filter)).sorted()
        var listIdx = 0
        var value = iter.next()
        while (value != DictionaryExt.nullValue) {
            splitSPO(list[listIdx]) { s, p, o ->
                if (o != value) {
                    throw Exception("")
                }
            }
            listIdx++
            value = iter.next()
        }
        if (listIdx < list.size) {
            throw Exception("")
        }
    }

    fun verifyCount(bundle: IteratorBundle, filter: IntArray) {
        if (bundle.count() != dataBuffer.filter(filterArrToFun(filter)).size) {
            throw Exception("")
        }
    }

    fun trimListToFilter(filter: Int, list: List<String>): List<String> {
        when (filter) {
            1 -> return listOf(list[1], list[2])
            2 -> return listOf(list[2])
            3 -> return listOf()
            else -> return list
        }
    }

    fun testGetIterator_sxx_Ok(filter: IntArray) {
        val query = Query()
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("s", "_", "_")))
        when (filter.size) {
            0 -> {
                if (bundle.columns.size != 1) {
                    throw Exception("")
                }
                verifyS(bundle, filter)
            }
            1, 2, 3 -> verifyCount(bundle, filter)
        }
    }

    fun testGetIterator_xpx_Ok(filter: IntArray) {
        val query = Query()
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("_", "p", "_")))
        when (filter.size) {
            0, 1 -> {
                if (bundle.columns.size != 1) {
                    throw Exception("")
                }
                verifyP(bundle, filter)
            }
            2, 3 -> verifyCount(bundle, filter)
        }
    }

    fun testGetIterator_xxo_Ok(filter: IntArray) {
        val query = Query()
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("_", "_", "o")))
        when (filter.size) {
            0, 1, 2 -> {
                if (bundle.columns.size != 1) {
                    throw Exception("")
                }
                verifyO(bundle, filter)
            }
            3 -> verifyCount(bundle, filter)
        }
    }

    fun testGetIterator_spx_Ok(filter: IntArray) {
        val query = Query()
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("s", "p", "_")))
        when (filter.size) {
            0 -> {
                if (bundle.columns.size != 2) {
                    throw Exception("")
                }
                verifyS(bundle, filter)
                verifyP(bundle, filter)
            }
            1 -> {
                if (bundle.columns.size != 1) {
                    throw Exception("")
                }
                verifyP(bundle, filter)
            }
            2, 3 -> verifyCount(bundle, filter)
        }
    }

    fun testGetIterator_xpo_Ok(filter: IntArray) {
        val query = Query()
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("_", "p", "o")))
        when (filter.size) {
            0, 1 -> {
                if (bundle.columns.size != 2) {
                    throw Exception("")
                }
                verifyP(bundle, filter)
                verifyO(bundle, filter)
            }
            2 -> {
                if (bundle.columns.size != 1) {
                    throw Exception("")
                }
                verifyO(bundle, filter)
            }
            3 -> verifyCount(bundle, filter)
        }
    }

    fun testGetIterator_sxo_Ok(filter: IntArray) {
        val query = Query()
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("s", "_", "o")))
        when (filter.size) {
            0 -> {
                if (bundle.columns.size != 2) {
                    throw Exception("")
                }
                verifyS(bundle, filter)
                verifyO(bundle, filter)
            }
            1, 2 -> {
                if (bundle.columns.size != 1) {
                    throw Exception("")
                }
                verifyO(bundle, filter)
            }
            3 -> verifyCount(bundle, filter)
        }
    }

    fun testGetIterator_spo_Ok(filter: IntArray) {
        val query = Query()
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("s", "p", "o")))
        when (filter.size) {
            0 -> {
                if (bundle.columns.size != 3) {
                    throw Exception("")
                }
                verifyS(bundle, filter)
                verifyP(bundle, filter)
                verifyO(bundle, filter)
            }
            1 -> {
                if (bundle.columns.size != 2) {
                    throw Exception("")
                }
                verifyP(bundle, filter)
                verifyO(bundle, filter)
            }
            2 -> {
                if (bundle.columns.size != 1) {
                    throw Exception("")
                }
                verifyO(bundle, filter)
            }
            3 -> verifyCount(bundle, filter)
        }
    }

    fun testGetIterator_xxx_Ok(filter: IntArray) {
        val query = Query()
        val bundle = index.getIterator(query, filter, trimListToFilter(filter.size, listOf("_", "_", "_")))
        verifyCount(bundle, filter)
    }

    fun getFilter(mode: Int, rng: Int, action: (IntArray) -> Unit) {
        if (dataBuffer.size == 0) {
            action(intArrayOf())
        } else {
            val tmp = dataBuffer.toList()[abs(rng) % dataBuffer.size]
            when (mode) {
                0 -> {
                    action(intArrayOf())
                }
                1 -> {
                    splitSPO(tmp) { s, p, o ->
                        action(intArrayOf(s))
                    }
                }
                2 -> {
                    splitSPO(tmp) { s, p, o ->
                        action(intArrayOf(s, p))
                    }
                }
                3 -> {
                    splitSPO(tmp) { s, p, o ->
                        action(intArrayOf(s, p, o))
                    }
                }
            }
        }
    }
    while (hasNextRandom() >= 3) {
        val mode = abs(nextRandom() % 38)
        prepareInsert(nextRandom())
        val rng = nextRandom()
        when (mode) {
            0 -> prepareDelete(rng)
            1 -> testDeleteOk()
            2 -> prepareInsert(rng)
            3 -> testInsertOk()
            4 -> testFlushOk()
            5 -> testClearOk()
            6 -> getFilter(0, rng) { testGetIterator_sxx_Ok(it) }
            7 -> getFilter(0, rng) { testGetIterator_xpx_Ok(it) }
            8 -> getFilter(0, rng) { testGetIterator_xxo_Ok(it) }
            9 -> getFilter(0, rng) { testGetIterator_spx_Ok(it) }
            10 -> getFilter(0, rng) { testGetIterator_xpo_Ok(it) }
            11 -> getFilter(0, rng) { testGetIterator_sxo_Ok(it) }
            12 -> getFilter(0, rng) { testGetIterator_spo_Ok(it) }
            13 -> getFilter(0, rng) { testGetIterator_xxx_Ok(it) }
            14 -> getFilter(1, rng) { testGetIterator_sxx_Ok(it) }
            15 -> getFilter(1, rng) { testGetIterator_xpx_Ok(it) }
            16 -> getFilter(1, rng) { testGetIterator_xxo_Ok(it) }
            17 -> getFilter(1, rng) { testGetIterator_spx_Ok(it) }
            18 -> getFilter(1, rng) { testGetIterator_xpo_Ok(it) }
            19 -> getFilter(1, rng) { testGetIterator_sxo_Ok(it) }
            20 -> getFilter(1, rng) { testGetIterator_spo_Ok(it) }
            21 -> getFilter(1, rng) { testGetIterator_xxx_Ok(it) }
            22 -> getFilter(2, rng) { testGetIterator_sxx_Ok(it) }
            23 -> getFilter(2, rng) { testGetIterator_xpx_Ok(it) }
            24 -> getFilter(2, rng) { testGetIterator_xxo_Ok(it) }
            25 -> getFilter(2, rng) { testGetIterator_spx_Ok(it) }
            26 -> getFilter(2, rng) { testGetIterator_xpo_Ok(it) }
            27 -> getFilter(2, rng) { testGetIterator_sxo_Ok(it) }
            28 -> getFilter(2, rng) { testGetIterator_spo_Ok(it) }
            29 -> getFilter(2, rng) { testGetIterator_xxx_Ok(it) }
            30 -> getFilter(3, rng) { testGetIterator_sxx_Ok(it) }
            31 -> getFilter(3, rng) { testGetIterator_xpx_Ok(it) }
            32 -> getFilter(3, rng) { testGetIterator_xxo_Ok(it) }
            33 -> getFilter(3, rng) { testGetIterator_spx_Ok(it) }
            34 -> getFilter(3, rng) { testGetIterator_xpo_Ok(it) }
            35 -> getFilter(3, rng) { testGetIterator_sxo_Ok(it) }
            36 -> getFilter(3, rng) { testGetIterator_spo_Ok(it) }
            37 -> getFilter(3, rng) { testGetIterator_xxx_Ok(it) }
        }
    }
    testDeleteOk()
    testInsertOk()
    getFilter(0, 0) { testGetIterator_spo_Ok(it) }
    index.close()
    index = TripleStoreIndexIDTriple(bufferManager, rootPage, true)
    getFilter(0, 0) { testGetIterator_spo_Ok(it) }
    index.delete()
    if (bufferManager.getNumberOfAllocatedPages() != 0) {
        throw Exception("")
    }
    bufferManager.close()
}
