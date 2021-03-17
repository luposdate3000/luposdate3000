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
import lupos.s00misc.File

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(filename: String): Unit = Parallel.runBlocking {
    val bufferManager = BufferManager("")

    val pageIds = mutableListOf<Int>()
    val mappedPages = mutableMapOf<Int, ByteArray>()
    val mappedPagesCtr = mutableMapOf<Int, Int>()

    val f = File(filename).withInputStream { input ->
        while (true) { // TODO input available
// TODO check all mapped pages
            val mode = input.readInt()
            when (mode) {
                0 -> { // release existing mapped page
                    val ids = mutableListOf<Int>()
                    ids.addAll(mappedPages.keys)
                    if (ids.size > 0) {
                        val pageid = ids[input.readInt() % ids.size]
                        bufferManager.releasePage(pageid)
                        val cnt = mappedPagesCtr[pageid]
                        if (cnt == null) {
                            throw Excepton("")
                        }
                        if (cnt == 1) {
                            mappedPages.remove(pageid)
                            mappedPagesCtr.remove(pageid)
                        } else {
                            mappedPagesCtr[pageid] = cnt - 1
                        }
                    }
                }
                4 -> { // release existing not mapped page
                    val ids = mutableListOf<Int>()
                    ids.addAll(pageIds)
                    ids.removeAll(mappedPages.keys)
                    if (ids.size > 0) {
                        val pageid = ids[input.readInt() % ids.size]
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
                }
                5 -> { // release not existing page
                    val ids = MutableList<Int>(1000) { it }
                    ids.removeAll(pageIds)
                    if (ids.size > 0) {
                        val pageid = ids[input.readInt() % ids.size]
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
                }
                1 -> { // get existing page
                    if (pageIds.size > 0) {
                        val pageid = pageIds[input.readInt() % pageIds.size]
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
                                throw Excepton("")
                            }
                        } else {
                            mappedPagesCtr[pageid] = 1
                        }
                    }
                }
                6 -> { // get not existing page
                }
                2 -> { // create new page
                    bufferManager.createPage { page, pageid ->
                        ByteArrayHelper.writeInt4(page, 0, pageid)
                        if (pageIds.contains(pageid)) {
                            throw Excepton("")
                        }
                        pageIds.add(pageid)
                        if (mappedPages[pageid] != null) {
                            throw Excepton("")
                        }
                        mappedPages[pageid] = page
                        if (mappedPagesCtr[pageid] != null) {
                            throw Excepton("")
                        }
                        mappedPagesCtr[pageid] = 1
                    }
                }
                3 -> { // delete existing singlemapped page
                    val ids = mutableSetOf<Int>()
                    for ((k, v) in mappedPagesCtr) {
                        if (v == 1) {
                            ids.add(k)
                        }
                    }
                    if (ids.size > 0) {
                        val pageid = ids[input.readInt() % ids.size]
                        bufferManager.deletePage(pageid)
                        mappedPagesCtr.remove(pageid)
                        mappedPages.remove(pageid)
                        pageIds.remove(pageid)
                    }
                }
                7 -> { // delete existing not mapped page
                }
                8 -> { // delete existing multiple mapped page
                }
                9 -> { // delete not existing page
                }
            }
        }
// TODO unmap all
// TODO check all pages
    }
}
