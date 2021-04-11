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
import lupos.buffermanager.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
import lupos.buffermanager.BufferManager
import lupos.buffermanager.BufferManagerPage
import lupos.buffermanager.MyIntArray
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.SanityCheck

public class KeyValueStore {
    private val rootPageID: Int
    private val rootPage: BufferManagerPage
    private val bufferManager: BufferManager
    private var lastPage: Int
    private var lastPageBuf: BufferManagerPage
    private var lastPageOffset: Int
    private var nextID: Int
    private var mappingID2Page: MyIntArray
    private var mappingID2Off: MyIntArray

    public constructor(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) {
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        var id1 = 0
        var id2 = 0
        if (initFromRootPage) {
            lastPage = rootPage.readInt4(0)
            lastPageBuf = bufferManager.getPage(lupos.SOURCE_FILE, lastPage)
            lastPageOffset = rootPage.readInt4(4)
            nextID = rootPage.readInt4(8)
            id1 = rootPage.readInt4(12)
            id2 = rootPage.readInt4(16)
        } else {
            var tmpPage: BufferManagerPage? = null
            lastPage = 0
            lastPageOffset = 0
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                tmpPage = page
                lastPage = pageid
                lastPageOffset = 4
            }
            lastPageBuf = tmpPage!!
            rootPage.writeInt4(0, lastPage)
            rootPage.writeInt4(4, lastPageOffset)
            nextID = 0
            rootPage.writeInt4(8, nextID)
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                id1 = pageid
            }
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                id2 = pageid
            }
            bufferManager.releasePage(lupos.SOURCE_FILE, id1)
            bufferManager.releasePage(lupos.SOURCE_FILE, id2)
            rootPage.writeInt4(12, id1)
            rootPage.writeInt4(16, id2)
        }
        mappingID2Page = MyIntArray(bufferManager, id1, initFromRootPage)
        mappingID2Off = MyIntArray(bufferManager, id2, initFromRootPage)
    }

    @ProguardTestAnnotation
    public fun delete() {
        bufferManager.releasePage(lupos.SOURCE_FILE, lastPage)
        var pageid = -1
        if (nextID == 0) {
            pageid = lastPage
        } else {
            pageid = mappingID2Page[0]
            while (pageid != lastPage) {
                val page = bufferManager.getPage(lupos.SOURCE_FILE, pageid)
                var nextPage = page.readInt4(0)
                bufferManager.deletePage(lupos.SOURCE_FILE, pageid)
                pageid = nextPage
            }
        }
        SanityCheck.check { pageid == lastPage }
        bufferManager.getPage(lupos.SOURCE_FILE, lastPage)
        bufferManager.deletePage(lupos.SOURCE_FILE, lastPage)
        mappingID2Page.delete()
        mappingID2Off.delete()
        bufferManager.deletePage(lupos.SOURCE_FILE, rootPageID)
    }

    @ProguardTestAnnotation
    public fun close() {
        mappingID2Page.close()
        mappingID2Off.close()
        bufferManager.releasePage(lupos.SOURCE_FILE, rootPageID)
        bufferManager.releasePage(lupos.SOURCE_FILE, lastPage)
    }

    private inline fun readData(data: ByteArrayWrapper, page: Int, off: Int) {
        var p = bufferManager.getPage(lupos.SOURCE_FILE, page)
        var pid = page
        val l = p.readInt4(off)
        data.setSize(l)
        var bufoff = 0
        var toread = l
        var pageoff = off + 4
        while (toread > 0) {
            var available = BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - pageoff
            if (available == 0) {
                var id = p.readInt4(0)
                bufferManager.releasePage(lupos.SOURCE_FILE, pid)
                p = bufferManager.getPage(lupos.SOURCE_FILE, id)
                pid = id
                pageoff = 4
                available = BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - pageoff
            }
            var len = if (available < toread) {
                available
            } else {
                toread
            }
            p.copyInto(data.getBuf(), bufoff, pageoff, pageoff + len)
            bufoff += len
            pageoff += len
            toread -= len
        }
        bufferManager.releasePage(lupos.SOURCE_FILE, pid)
    }

    private inline fun writeData(data: ByteArrayWrapper, crossinline action: (page: Int, off: Int) -> Unit) {
        if (lastPageOffset >= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 8) {
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                lastPageBuf.writeInt4(0, pageid)
                bufferManager.releasePage(lupos.SOURCE_FILE, lastPage)
                lastPageBuf = page
                lastPage = pageid
                rootPage.writeInt4(0, lastPage)
            }
            lastPageOffset = 4
            rootPage.writeInt4(4, lastPageOffset)
        }
        var resPage = lastPage
        var resOff = lastPageOffset
        lastPageBuf.writeInt4(lastPageOffset, data.getSize())
        lastPageOffset += 4
        rootPage.writeInt4(4, lastPageOffset)
        var dataoff = 0
        var towrite = data.getSize()
        while (towrite > 0) {
            var available = BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - lastPageOffset
            if (available == 0) {
                bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                    lastPageBuf.writeInt4(0, pageid)
                    bufferManager.releasePage(lupos.SOURCE_FILE, lastPage)
                    lastPageBuf = page
                    lastPage = pageid
                    rootPage.writeInt4(0, lastPage)
                }
                lastPageOffset = 4
                rootPage.writeInt4(4, lastPageOffset)
                available = BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - lastPageOffset
            }
            var len = if (available < towrite) {
                available
            } else {
                towrite
            }
            lastPageBuf.copyFrom(data.getBuf(), lastPageOffset, dataoff, dataoff + len)
            towrite -= len
            lastPageOffset += len
            rootPage.writeInt4(4, lastPageOffset)
            dataoff += len
        }
        action(resPage, resOff)
    }

    public fun createValue(data: ByteArrayWrapper): Int {
        var res = 0
        res = nextID++
        rootPage.writeInt4(8, nextID)
        writeData(data) { page, off ->
            if (res >= mappingID2Page.getSize()) {
                mappingID2Page.setSize(res + 1, false)
                mappingID2Off.setSize(res + 1, false)
            }
            mappingID2Page[res] = page
            mappingID2Off[res] = off
        }
        return res
    }

    public fun getValue(data: ByteArrayWrapper, value: Int) {
        SanityCheck.check { value < nextID }
        SanityCheck.check { value >= 0 }
        readData(data, mappingID2Page[value], mappingID2Off[value])
    }
}
