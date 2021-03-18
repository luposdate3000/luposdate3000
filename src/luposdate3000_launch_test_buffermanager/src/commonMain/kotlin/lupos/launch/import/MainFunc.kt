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
package lupos.launch.import

import lupos.buffermanager.BufferManager
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.Parallel

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(seedString: String): Unit = Parallel.runBlocking {
    val seed = seedString.toInt()
    val random = Random(seed)
    while (true) {
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

        fun getPageID_Existing_Mapped(rnd: Int, action: (Int) -> Unit) {
            val ids = mutableListOf<Int>()
            ids.addAll(mappedPages.keys)
            if (ids.size > 0) {
                val pageid = ids[rnd % ids.size]
                action(pageid)
            }
        }

        fun getPageID_Existing_NotMapped(rnd: Int, action: (Int) -> Unit) {
            val ids = mutableListOf<Int>()
            ids.addAll(pageIds)
            ids.removeAll(mappedPages.keys)
            if (ids.size > 0) {
                val pageid = ids[rnd % ids.size]
                action(pageid)
            }
        }

        fun getPageID_NotExisting(rnd: Int, action: (Int) -> Unit) {
            val ids = MutableList<Int>(1000) { it }
            ids.removeAll(pageIds)
            if (ids.size > 0) {
                val pageid = ids[rnd % ids.size]
                action(pageid)
            }
        }

        fun getPageID_Existing(rnd: Int, action: (Int) -> Unit) {
            if (pageIds.size > 0) {
                val pageid = pageIds[rnd % pageIds.size]
                action(pageid)
            }
        }

        fun getPageID_Existing_SingleMapped(rnd: Int, action: (Int) -> Unit) {
            val ids = mutableListOf<Int>()
            for ((k, v) in mappedPagesCtr) {
                if (v == 1) {
                    ids.add(k)
                }
            }
            if (ids.size > 0) {
                val pageid = ids[rnd % ids.size]
                action(pageid)
            }
        }

        fun getPageID_Existing_MultiMapped(rnd: Int, action: (Int) -> Unit) {
            val ids = mutableListOf<Int>()
            for ((k, v) in mappedPagesCtr) {
                if (v > 1) {
                    ids.add(k)
                }
            }
            if (ids.size > 0) {
                val pageid = ids[rnd % ids.size]
                action(pageid)
            }
        }
        testCreateNewPageOk()
        val cnt = random.nextInt(0, 65536)
        for (i in 0 until cnt) {
            var mode = random.nextInt(0, 10)
            var rnd = random.nextInt()
            when (mode) {
                0 -> getPageID_Existing_Mapped(rnd) { testReleasePageOk(it) }
                1 -> getPageID_Existing_NotMapped(rnd) { testReleasePageFail(it) }
                2 -> getPageID_NotExisting(rnd) { testReleasePageFail(it) }

                3 -> getPageID_Existing(rnd) { testGetPageOk(it) }
                4 -> getPageID_NotExisting(rnd) { testGetPageFail(it) }

                5 -> testCreateNewPageOk()

                6 -> getPageID_Existing_SingleMapped(rnd) { testDeletePageOk(it) }
                7 -> getPageID_Existing_NotMapped(rnd) { testDeletePageFail(it) }
                8 -> getPageID_Existing_MultiMapped(rnd) { testDeletePageFail(it) }
                9 -> getPageID_NotExisting(rnd) { testDeletePageFail(it) }
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
}
