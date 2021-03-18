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
import lupos.s00misc.File
import lupos.s00misc.Parallel
import kotlin.random.Random

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    var seed = 0
    try {
        seed = arg.toInt()
// randomized testing
        val random = Random(seed)
        while (true) {
            val cnt = random.nextInt(0, 65536)
            val data = IntArray(cnt)
            for (i in 0 until cnt) {
                data[i] = random.nextInt()
            }
            try {
                executeTest(data)
            } catch (e: Throwable) {
                File("erroredTests").mkdirs()
                File("erroredTests/test_buffermanager_${data.contentHashCode().toString(16)}.data").withOutputStream {
                    for (i in 0 until data.size) {
                        it.writeInt(data[i])
                    }
                }
            }
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
        executeTest(data)
    }
}

private fun executeTest(data: IntArray) {
    val bufferManager = BufferManager("")

    val pageIds = mutableListOf<Int>()
    val mappedPages = mutableMapOf<Int, ByteArray>()
    val mappedPagesCtr = mutableMapOf<Int, Int>()

    fun testReleasePageOk(pageid: Int) {
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
        bufferManager.deletePage(pageid)
        mappedPagesCtr.remove(pageid)
        mappedPages.remove(pageid)
        pageIds.remove(pageid)
    }

    fun testDeletePageFail(pageid: Int) {
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
            val pageid = ids[rng % ids.size]
            action(pageid)
        }
    }

    fun getPageID_Existing_NotMapped(rng: Int, action: (Int) -> Unit) {
        val ids = mutableListOf<Int>()
        ids.addAll(pageIds)
        ids.removeAll(mappedPages.keys)
        if (ids.size > 0) {
            val pageid = ids[rng % ids.size]
            action(pageid)
        }
    }

    fun getPageID_NotExisting(rng: Int, action: (Int) -> Unit) {
        val ids = MutableList<Int>(1000) { it }
        ids.removeAll(pageIds)
        if (ids.size > 0) {
            val pageid = ids[rng % ids.size]
            action(pageid)
        }
    }

    fun getPageID_Existing(rng: Int, action: (Int) -> Unit) {
        if (pageIds.size > 0) {
            val pageid = pageIds[rng % pageIds.size]
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
            val pageid = ids[rng % ids.size]
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
            val pageid = ids[rng % ids.size]
            action(pageid)
        }
    }
    testCreateNewPageOk()
    var dataoff = 0
    while (true) {
        if (dataoff == data.size) {
            break
        }
        val mode = data[dataoff++] % 10
        if (dataoff == data.size) {
            break
        }
        val rng = data[dataoff++]
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
            testReleasePageOk(v)
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
    if (pageIds.size > 0) {
        throw Exception("")
    }
    if (mappedPages.size > 0) {
        throw Exception("")
    }
    if (mappedPagesCtr.size > 0) {
        throw Exception("")
    }
}
