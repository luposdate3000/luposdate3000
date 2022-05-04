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
package lupos.test_buffermanager
import lupos.shared.BufferManagerPage
import lupos.shared.BufferManagerPageWrapper
import lupos.shared.IBufferManager
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import kotlin.math.abs

public fun executeBufferManagerTest(nextRandom: () -> Int, hasNextRandom: () -> Int, @Suppress("UNUSED_PARAMETER") resetRandom: () -> Unit, BufferManager: (Luposdate3000Instance) -> IBufferManager, isInMemoryOnly: Boolean, allowInitFromDisk: (Boolean) -> Unit, isUnitTest: Boolean) {
    if (!SanityCheck.enabled) {
        return
    }
    val verbose = false
    allowInitFromDisk(false)
    val instance = Luposdate3000Instance()
    if (isUnitTest) {
        instance.BUFFER_HOME = "build/tmp/executeBufferManagerTest"
    }
    instance.allowInitFromDisk = false
    val zeroBytes = ByteArray(BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
    instance.bufferManager = BufferManager(instance)
    val pageIds = mutableListOf<Int>()
    val mappedPages = mutableMapOf<Int, BufferManagerPageWrapper>()
    val mappedPagesCtr = mutableMapOf<Int, Int>()

    fun testReleasePageOk(pageid: Int) {
        if (verbose) {
            println("testReleasePageOk $pageid")
        }
        instance.bufferManager!!.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:47"/*SOURCE_FILE_END*/, pageid)
        val cnt = mappedPagesCtr[pageid]
        if (cnt == null) {
            TODO()
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
            instance.bufferManager!!.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:66"/*SOURCE_FILE_END*/, pageid)
        } catch (e: Throwable) {
            // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:68"/*SOURCE_FILE_END*/ ) this is handled correctly
            flag = false
        }
        if (flag) {
            TODO()
        }
    }

    fun testGetPageOk(pageid: Int) {
        if (verbose) {
            println("testGetPageOk $pageid")
        }
        val page = instance.bufferManager!!.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:80"/*SOURCE_FILE_END*/, pageid)
        val id = BufferManagerPage.readInt4(page, 0)
        if (id != pageid) {
            TODO()
        }
        val pp = mappedPages[pageid]
        mappedPages[pageid] = page
        if (pp != null) {
            val cnt = mappedPagesCtr[pageid]!!
            mappedPagesCtr[pageid] = cnt + 1
            if (pp != page) {
                TODO()
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
            instance.bufferManager!!.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:104"/*SOURCE_FILE_END*/, pageid)
        } catch (e: Throwable) {
            // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:106"/*SOURCE_FILE_END*/ ) this is handled correctly
            flag = false
        }
        if (flag) {
            TODO()
        }
    }

    fun testCreateNewPageOk() {
        val pageid = instance.bufferManager!!.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:115"/*SOURCE_FILE_END*/)
        val page = instance.bufferManager!!.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:116"/*SOURCE_FILE_END*/, pageid)
        BufferManagerPage.copyFrom(page, zeroBytes, 0, 0, BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES)
        if (verbose) {
            println("testCreateNewPageOk $pageid")
        }
        BufferManagerPage.writeInt4(page, 0, pageid)
        if (pageIds.contains(pageid)) {
            TODO()
        }
        pageIds.add(pageid)
        if (mappedPages[pageid] != null) {
            TODO()
        }
        mappedPages[pageid] = page
        if (mappedPagesCtr[pageid] != null) {
            TODO()
        }
        mappedPagesCtr[pageid] = 1
    }

    fun testDeletePageOk(pageid: Int) {
        if (verbose) {
            println("testDeletePageOk $pageid")
        }
        instance.bufferManager!!.deletePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:140"/*SOURCE_FILE_END*/, pageid)
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
            instance.bufferManager!!.deletePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:152"/*SOURCE_FILE_END*/, pageid)
        } catch (e: Throwable) {
            // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:154"/*SOURCE_FILE_END*/ ) this is handled correctly
            flag = false
        }
        if (flag) {
            TODO()
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
        val ids = MutableList(1000) { it }
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
        if (instance.bufferManager!!.getNumberOfAllocatedPages() != pageIds.size) {
            TODO()
        }
        for ((k, v) in mappedPages) {
            val id = BufferManagerPage.readInt4(v, 0)
            if (id != k) {
                TODO()
            }
        }
    }
    val ids = mutableMapOf<Int, Int>()
    ids.putAll(mappedPagesCtr)
    for ((k, v) in ids) {
        for (i in 0 until v) {
            testReleasePageOk(k)
        }
    }
    if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
        TODO()
    }
    for (pageid in pageIds) {
        val page = instance.bufferManager!!.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:262"/*SOURCE_FILE_END*/, pageid)
        val id = BufferManagerPage.readInt4(page, 0)
        if (id != pageid) {
            TODO()
        }
        instance.bufferManager!!.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:267"/*SOURCE_FILE_END*/, pageid)
    }
    if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
        TODO()
    }
    if (!isInMemoryOnly) {
        allowInitFromDisk(true)
        instance.bufferManager!!.close()
        instance.bufferManager = BufferManager(instance)
    }
    if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
        TODO()
    }
    if (instance.bufferManager!!.getNumberOfAllocatedPages() != pageIds.size) {
        TODO()
    }
    for (pageid in pageIds) {
        val page = instance.bufferManager!!.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:284"/*SOURCE_FILE_END*/, pageid)
        val id = BufferManagerPage.readInt4(page, 0)
        if (id != pageid) {
            TODO()
        }
        instance.bufferManager!!.deletePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_buffermanager/src/commonMain/kotlin/lupos/test_buffermanager/executeBufferManagerTest.kt:289"/*SOURCE_FILE_END*/, pageid)
    }
    if (instance.bufferManager!!.getNumberOfReferencedPages() != 0) {
        TODO()
    }
    if (instance.bufferManager!!.getNumberOfAllocatedPages() != 0) {
        TODO()
    }
    if (mappedPages.isNotEmpty()) {
        TODO()
    }
    if (mappedPagesCtr.isNotEmpty()) {
        TODO()
    }
    instance.bufferManager!!.close()
}
