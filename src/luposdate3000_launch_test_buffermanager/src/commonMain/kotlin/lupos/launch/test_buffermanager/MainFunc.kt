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
package lupos.launch.test_buffermanager

import lupos.buffermanager.BufferManager
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.DateHelperRelative
import lupos.s00misc.File
import lupos.s00misc.Parallel
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
            var testCase = "test_buffermanager_${tmp.toString(16)}.data"
            if (verbose) {
                println("case $tests :: $cnt $testCase")
            }
            try {
                dataoff = 0
                executeTest(nextRandom = { data[dataoff++] }, hasNextRandom = { dataoff < cnt })
            } catch (e: Throwable) {
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
        executeTest(nextRandom = { data[dataoff++] }, hasNextRandom = { dataoff < data.size })
    }
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Boolean) {
    val bufferManager = BufferManager("")

    val pageIds = mutableListOf<Int>()
    val mappedPages = mutableMapOf<Int, ByteArray>()
    val mappedPagesCtr = mutableMapOf<Int, Int>()

    fun testReleasePageOk(pageid: Int) {
        if (verbose) {
            println("testReleasePageOk $pageid")
        }
        bufferManager.releasePage(pageid)
        val cnt = mappedPagesCtr[pageid]
        if (cnt == null) {
            throw Exception("")
        }
        if (cnt == 1) {
            mappedPages.remove(pageid)
            mappedPagesCtr.remove(pageid)
        } else {
            mappedPagesCtr[pageid] = cnt - 1
        }
    }

    fun testReleasePageFail(pageid: Int) {
        if (verbose) {
            println("testReleasePageFail $pageid")
        }
        var flag = true
        try {
            bufferManager.releasePage(pageid)
        } catch (e: Throwable) {
            flag = false
        }
        if (flag) {
            throw Exception("")
        }
    }

    fun testGetPageOk(pageid: Int) {
        if (verbose) {
            println("testGetPageOk $pageid")
        }
        val page = bufferManager.getPage(pageid)
        val id = ByteArrayHelper.readInt4(page, 0)
        if (id != pageid) {
            throw Exception("")
        }
        val pp = mappedPages[pageid]
        mappedPages[pageid] = page
        if (pp != null) {
            val cnt = mappedPagesCtr[pageid]!!
            mappedPagesCtr[pageid] = cnt + 1
            if (pp != page) {
                throw Exception("")
            }
        } else {
            mappedPagesCtr[pageid] = 1
        }
    }

    fun testGetPageFail(pageid: Int) {
        if (verbose) {
            println("testGetPageFail $pageid")
        }
        var flag = true
        try {
            bufferManager.getPage(pageid)
        } catch (e: Throwable) {
            flag = false
        }
        if (flag) {
            throw Exception("")
        }
    }

    fun testCreateNewPageOk() {
        bufferManager.createPage { page, pageid ->
            if (verbose) {
                println("testCreateNewPageOk $pageid")
            }
            ByteArrayHelper.writeInt4(page, 0, pageid)
            if (pageIds.contains(pageid)) {
                throw Exception("")
            }
            pageIds.add(pageid)
            if (mappedPages[pageid] != null) {
                throw Exception("")
            }
            mappedPages[pageid] = page
            if (mappedPagesCtr[pageid] != null) {
                throw Exception("")
            }
            mappedPagesCtr[pageid] = 1
        }
    }

    fun testDeletePageOk(pageid: Int) {
        if (verbose) {
            println("testDeletePageOk $pageid")
        }
        bufferManager.deletePage(pageid)
        mappedPagesCtr.remove(pageid)
        mappedPages.remove(pageid)
        pageIds.remove(pageid)
    }

    fun testDeletePageFail(pageid: Int) {
        if (verbose) {
            println("testDeletePageFail $pageid")
        }
        var flag = true
        try {
            bufferManager.deletePage(pageid)
        } catch (e: Throwable) {
            flag = false
        }
        if (flag) {
            throw Exception("")
        }
    }

    fun getPageID_Existing_Mapped(rng: Int, action: (Int) -> Unit) {
        val ids = mutableListOf<Int>()
        ids.addAll(mappedPages.keys)
        if (ids.size > 0) {
            val pageid = ids[abs(rng % ids.size)]
            action(pageid)
        }
    }

    fun getPageID_Existing_NotMapped(rng: Int, action: (Int) -> Unit) {
        val ids = mutableListOf<Int>()
        ids.addAll(pageIds)
        ids.removeAll(mappedPages.keys)
        if (ids.size > 0) {
            val pageid = ids[abs(rng % ids.size)]
            action(pageid)
        }
    }

    fun getPageID_NotExisting(rng: Int, action: (Int) -> Unit) {
        val ids = MutableList<Int>(1000) { it }
        ids.removeAll(pageIds)
        if (ids.size > 0) {
            val pageid = ids[abs(rng % ids.size)]
            action(pageid)
        }
    }

    fun getPageID_Existing(rng: Int, action: (Int) -> Unit) {
        if (pageIds.size > 0) {
            val pageid = pageIds[abs(rng % pageIds.size)]
            action(pageid)
        }
    }

    fun getPageID_Existing_SingleMapped(rng: Int, action: (Int) -> Unit) {
        val ids = mutableListOf<Int>()
        for ((k, v) in mappedPagesCtr) {
            if (v == 1) {
                ids.add(k)
            }
        }
        if (ids.size > 0) {
            val pageid = ids[abs(rng % ids.size)]
            action(pageid)
        }
    }

    fun getPageID_Existing_MultiMapped(rng: Int, action: (Int) -> Unit) {
        val ids = mutableListOf<Int>()
        for ((k, v) in mappedPagesCtr) {
            if (v > 1) {
                ids.add(k)
            }
        }
        if (ids.size > 0) {
            val pageid = ids[abs(rng % ids.size)]
            action(pageid)
        }
    }
    testCreateNewPageOk()
    while (hasNextRandom()) {
        val mode = abs(nextRandom() % 10)
        if (!hasNextRandom()) {
            break
        }
        val rng = nextRandom()
        when (mode) {
            0 -> getPageID_Existing_Mapped(rng) { testReleasePageOk(it) }
            1 -> getPageID_Existing_NotMapped(rng) { testReleasePageFail(it) }
            2 -> getPageID_NotExisting(rng) { testReleasePageFail(it) }

            3 -> getPageID_Existing(rng) { testGetPageOk(it) }
            4 -> getPageID_NotExisting(rng) { testGetPageFail(it) }

            5 -> testCreateNewPageOk()

            6 -> getPageID_Existing_SingleMapped(rng) { testDeletePageOk(it) }
            7 -> getPageID_Existing_NotMapped(rng) { testDeletePageFail(it) }
            8 -> getPageID_Existing_MultiMapped(rng) { testDeletePageFail(it) }
            9 -> getPageID_NotExisting(rng) { testDeletePageFail(it) }
        }
        for ((k, v) in mappedPages) {
            val id = ByteArrayHelper.readInt4(v, 0)
            if (id != k) {
                throw Exception("")
            }
        }
    }
    var ids = mutableMapOf<Int, Int>()
    ids.putAll(mappedPagesCtr)
    for ((k, v) in ids) {
        for (i in 0 until v) {
            testReleasePageOk(k)
        }
    }
    for (pageid in pageIds) {
        val page = bufferManager.getPage(pageid)
        val id = ByteArrayHelper.readInt4(page, 0)
        if (id != pageid) {
            throw Exception("")
        }
        bufferManager.deletePage(pageid)
    }
    if (mappedPages.size != 0) {
        throw Exception("")
    }
    if (mappedPagesCtr.size != 0) {
        throw Exception("")
    }
}
