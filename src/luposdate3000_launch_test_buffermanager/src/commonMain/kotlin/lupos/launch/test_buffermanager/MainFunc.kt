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

import lupos.buffermanager.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
import lupos.buffermanager.BufferManager
import lupos.buffermanager.BufferManagerExt
import lupos.buffermanager.BufferManagerPage
import lupos.s00misc.Parallel
import lupos.test.AflCore
import kotlin.math.abs

private val verbose = false

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("buffermanager.${BufferManagerExt.isInMemoryOnly}", 1.0, ::executeTest)(arg)
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    val zeroBytes = ByteArray(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
    BufferManagerExt.allowInitFromDisk = false
    var bufferManager = BufferManager()
    val pageIds = mutableListOf<Int>()
    val mappedPages = mutableMapOf<Int, BufferManagerPage>()
    val mappedPagesCtr = mutableMapOf<Int, Int>()

    fun testReleasePageOk(pageid: Int) {
        if (verbose) {
            println("testReleasePageOk $pageid")
        }
        bufferManager.releasePage(lupos.SOURCE_FILE, pageid)
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
            bufferManager.releasePage(lupos.SOURCE_FILE, pageid)
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
        val page = bufferManager.getPage(lupos.SOURCE_FILE, pageid)
        val id = page.readInt4(0)
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
            bufferManager.getPage(lupos.SOURCE_FILE, pageid)
        } catch (e: Throwable) {
            flag = false
        }
        if (flag) {
            throw Exception("")
        }
    }

    fun testCreateNewPageOk() {
        val pageid = bufferManager.allocPage(lupos.SOURCE_FILE)
        val page = bufferManager.getPage(lupos.SOURCE_FILE, pageid)
        page.copyFrom(zeroBytes, 0, 0, BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
        if (verbose) {
            println("testCreateNewPageOk $pageid")
        }
        page.writeInt4(0, pageid)
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

    fun testDeletePageOk(pageid: Int) {
        if (verbose) {
            println("testDeletePageOk $pageid")
        }
        bufferManager.deletePage(lupos.SOURCE_FILE, pageid)
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
            bufferManager.deletePage(lupos.SOURCE_FILE, pageid)
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
    while (hasNextRandom() >= 2) {
        val mode = abs(nextRandom() % 10)
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
        if (bufferManager.getNumberOfAllocatedPages() != pageIds.size) {
            throw Exception("")
        }
        for ((k, v) in mappedPages) {
            val id = v.readInt4(0)
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
    if (bufferManager.getNumberOfReferencedPages() != 0) {
        throw Exception("")
    }
    for (pageid in pageIds) {
        val page = bufferManager.getPage(lupos.SOURCE_FILE, pageid)
        val id = page.readInt4(0)
        if (id != pageid) {
            throw Exception("")
        }
        bufferManager.releasePage(lupos.SOURCE_FILE, pageid)
    }
    if (bufferManager.getNumberOfReferencedPages() != 0) {
        throw Exception("")
    }
    if (!BufferManagerExt.isInMemoryOnly) {
        BufferManagerExt.allowInitFromDisk = true
        bufferManager.close()
        bufferManager = BufferManager()
    }
    if (bufferManager.getNumberOfReferencedPages() != 0) {
        throw Exception("")
    }
    if (bufferManager.getNumberOfAllocatedPages() != pageIds.size) {
        throw Exception("")
    }
    for (pageid in pageIds) {
        val page = bufferManager.getPage(lupos.SOURCE_FILE, pageid)
        val id = page.readInt4(0)
        if (id != pageid) {
            throw Exception("")
        }
        bufferManager.deletePage(lupos.SOURCE_FILE, pageid)
    }
    if (bufferManager.getNumberOfReferencedPages() != 0) {
        throw Exception("")
    }
    if (bufferManager.getNumberOfAllocatedPages() != 0) {
        throw Exception("")
    }
    if (mappedPages.size != 0) {
        throw Exception("")
    }
    if (mappedPagesCtr.size != 0) {
        throw Exception("")
    }
    bufferManager.close()
}
