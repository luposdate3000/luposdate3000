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
package lupos.kv

import lupos.ProguardTestAnnotation
import lupos.SOURCE_FILE
import lupos.buffermanager.BufferManager
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.SanityCheck

public class MyIntArray {
    private val bufferManager: BufferManager
    private val rootPageID: Int
    private val valuesPerPage: Int
    private val rootPage: ByteArray
    private var capacity: Int = 0
    private var _size: Int = 0
    public val size: Int
        get() = _size

    public constructor(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) {
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        rootPage = bufferManager.getPage(SOURCE_FILE, rootPageID)
        valuesPerPage = rootPage.size / 4
        if (initFromRootPage) {
            capacity = ByteArrayHelper.readInt4(rootPage, rootPage.size - 4)
            _size = ByteArrayHelper.readInt4(rootPage, rootPage.size - 8)
        }
    }

    public fun setSize(size: Int) {
        setSize(size, true)
    }

    public fun setSize(size: Int, clean: Boolean) {
        val capacity = ((size + valuesPerPage - 1) / valuesPerPage) * valuesPerPage
        var previousDataPages = (this.capacity + valuesPerPage - 1) / valuesPerPage
        var newDataPages = (capacity + valuesPerPage - 1) / valuesPerPage
        if (newDataPages > previousDataPages) {
            var previousInnerPages = (previousDataPages + valuesPerPage - 1) / valuesPerPage
            var newInnerPages = (newDataPages + valuesPerPage - 1) / valuesPerPage
            for (i in previousInnerPages until newInnerPages) {
                var id = 0
                bufferManager.createPage(SOURCE_FILE) { page, pageid ->
                    id = pageid
                    ByteArrayHelper.writeInt4(rootPage, i * 4, pageid)
                }
                bufferManager.releasePage(SOURCE_FILE, id)
            }
            var pid = -1
            var p: ByteArray? = null
            for (i in previousDataPages until newDataPages) {
                val offA2 = i
                val offB1 = offA2 % valuesPerPage
                val offB2 = offA2 / valuesPerPage
                val offC1 = offB2 % valuesPerPage
                val offC2 = offB2 / valuesPerPage
                val pageC = rootPage
                val pageBId = ByteArrayHelper.readInt4(pageC, offC1 * 4)
                if (pid != pageBId) {
                    if (pid != -1) {
                        bufferManager.releasePage(SOURCE_FILE, pid)
                    }
                    pid = pageBId
                    p = bufferManager.getPage(SOURCE_FILE, pageBId)
                }
                val pageB = p!!
                var id = -1
                bufferManager.createPage(SOURCE_FILE) { page, pageid ->
                    id = pageid
                }
                bufferManager.releasePage(SOURCE_FILE, id)
                ByteArrayHelper.writeInt4(pageB, offB1 * 4, id)
            }
            if (pid != -1) {
                bufferManager.releasePage(SOURCE_FILE, pid)
            }
            this.capacity = capacity
            ByteArrayHelper.writeInt4(rootPage, rootPage.size - 4, capacity)
        }
        var tmp = _size
        _size = size
        if (clean) {
            for (i in tmp until size) {
                set(i, 0)
            }
        }
        ByteArrayHelper.writeInt4(rootPage, rootPage.size - 8, size)
    }

    private inline fun idxToPage(idx: Int, crossinline action: (Int, ByteArray) -> Unit) {
        val offA1 = idx % valuesPerPage
        val offA2 = idx / valuesPerPage
        val offB1 = offA2 % valuesPerPage
        val offB2 = offA2 / valuesPerPage
        val offC1 = offB2 % valuesPerPage
        val offC2 = offB2 / valuesPerPage
        SanityCheck.check { offC2 == 0 }
        val pageC = rootPage
        val pageBId = ByteArrayHelper.readInt4(pageC, offC1 * 4)
        val pageB = bufferManager.getPage(SOURCE_FILE, pageBId)
        val pageAId = ByteArrayHelper.readInt4(pageB, offB1 * 4)
        bufferManager.releasePage(SOURCE_FILE, pageBId)
        val pageA = bufferManager.getPage(SOURCE_FILE, pageAId)
// println("$idx -> root@$offC1 $pageBId@$offB1 -> $pageAId@$offA1")
        action(offA1 * 4, pageA)
        bufferManager.releasePage(SOURCE_FILE, pageAId)
    }

    public operator fun get(idx: Int): Int {
        SanityCheck.check { idx >= 0 }
        SanityCheck.check { idx < _size }
        var res: Int = 0
        idxToPage(idx) { off, page ->
            res = ByteArrayHelper.readInt4(page, off)
        }
        return res
    }

    public operator fun set(idx: Int, value: Int) {
        SanityCheck.check { idx >= 0 }
        SanityCheck.check { idx < _size }
        idxToPage(idx) { off, page ->
            ByteArrayHelper.writeInt4(page, off, value)
        }
    }

    @ProguardTestAnnotation
    public fun close() {
        bufferManager.releasePage(SOURCE_FILE, rootPageID)
    }

    @ProguardTestAnnotation
    public fun delete() {
        val dataPages = (capacity + valuesPerPage - 1) / valuesPerPage
        val innerPages = (dataPages + valuesPerPage - 1) / valuesPerPage
        for (i in 0 until innerPages) {
            val pageid = ByteArrayHelper.readInt4(rootPage, i * 4)
            val page = bufferManager.getPage(SOURCE_FILE, pageid)
            for (j in 0 until valuesPerPage) {
                if (i * valuesPerPage + j < dataPages) {
                    val pageid2 = ByteArrayHelper.readInt4(page, j * 4)
                    bufferManager.getPage(SOURCE_FILE, pageid2)
                    bufferManager.deletePage(SOURCE_FILE, pageid2)
                }
            }
            bufferManager.deletePage(SOURCE_FILE, pageid)
        }
        bufferManager.deletePage(SOURCE_FILE, rootPageID)
    }
}
