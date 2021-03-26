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
    private var mappingSorted: MyIntArray

    public constructor(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) {
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        if (initFromRootPage) {
            lastPage = rootPage.readInt4(0)
            lastPageBuf = bufferManager.getPage(lupos.SOURCE_FILE, lastPage)
            lastPageOffset = rootPage.readInt4(4)
            nextID = rootPage.readInt4(8)
            var id1 = rootPage.readInt4(12)
            var id2 = rootPage.readInt4(16)
            var id3 = rootPage.readInt4(20)
            mappingID2Page = MyIntArray(bufferManager, id1, initFromRootPage)
            mappingID2Off = MyIntArray(bufferManager, id2, initFromRootPage)
            mappingSorted = MyIntArray(bufferManager, id3, initFromRootPage)
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
            var id1 = 0
            var id2 = 0
            var id3 = 0
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                id1 = pageid
            }
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                id2 = pageid
            }
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                id3 = pageid
            }
            bufferManager.releasePage(lupos.SOURCE_FILE, id1)
            bufferManager.releasePage(lupos.SOURCE_FILE, id2)
            bufferManager.releasePage(lupos.SOURCE_FILE, id3)
            mappingID2Page = MyIntArray(bufferManager, id1, initFromRootPage)
            mappingID2Off = MyIntArray(bufferManager, id2, initFromRootPage)
            mappingSorted = MyIntArray(bufferManager, id3, initFromRootPage)
            rootPage.writeInt4(12, id1)
            rootPage.writeInt4(16, id2)
            rootPage.writeInt4(20, id3)
        }
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
        mappingSorted.delete()
        bufferManager.deletePage(lupos.SOURCE_FILE, rootPageID)
    }

    @ProguardTestAnnotation
    public fun close() {
        mappingID2Page.close()
        mappingID2Off.close()
        mappingSorted.close()
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

    private inline fun hasData(data: ByteArrayWrapper, left: Int, right: Int, crossinline onFound: (Int/*the id to return*/) -> Unit, onNotFound: (Int/*the smallest index, which value is larger than the target*/) -> Unit) {
        val d = ByteArrayWrapper()
        var l = left
        var r = right
        var m = (r - l) / 2 + l
        while (r >= l) {
            m = (r - l) / 2 + l
            val m2 = mappingSorted[m]
            readData(d, mappingID2Page[m2], mappingID2Off[m2])
            val t = d.compareTo(data)
            if (t < 0) {
                if (m == l) {
                    m++
                }
                l = m
            } else if (t > 0) {
                if (m == l) {
                    break
                }
                r = m
            } else {
                onFound(m2)
                return
            }
        }
        SanityCheck {
            if (m > left) {
                val m2 = mappingSorted[m - 1]
                readData(d, mappingID2Page[m2], mappingID2Off[m2])
                SanityCheck.check({ d.compareTo(data) < 0 }, { "$l $r $m $left $right ${d.getBuf().map { it }.subList(0, d.getSize())} ${data.getBuf().map { it }.subList(0, data.getSize())}" })
            }
            if (m <= right) {
                val m2 = mappingSorted[m]
                readData(d, mappingID2Page[m2], mappingID2Off[m2])
                SanityCheck.check({ d.compareTo(data) > 0 }, { "$l $r $m $left $right ${d.getBuf().map { it }.subList(0, d.getSize())} ${data.getBuf().map { it }.subList(0, data.getSize())}" })
            }
        }
        onNotFound(m)
    }

    public fun createValue(data: ByteArrayWrapper): Int {
        var res = 0
        hasData(
            data, 0, nextID - 1,
            onFound = {
                res = it
            },
            onNotFound = {
                res = nextID++
                rootPage.writeInt4(8, nextID)
                writeData(data) { page, off ->
                    if (res >= mappingID2Page.getSize()) {
                        mappingID2Page.setSize(res + 1, false)
                        mappingID2Off.setSize(res + 1, false)
                        mappingSorted.setSize(res + 1, false)
                    }
                    mappingID2Page[res] = page
                    mappingID2Off[res] = off
                    var i = res
                    while (i > it) {
                        mappingSorted[i] = mappingSorted[i - 1]
                        i--
                    }
                    mappingSorted[i] = res
                }
            }
        )
        return res
    }

    public fun hasValue(data: ByteArrayWrapper): Int? {
        var res: Int? = null
        hasData(
            data, 0, nextID - 1,
            onFound = {
                res = it
            },
            onNotFound = {
            }
        )
        return res
    }

    public fun getValue(data: ByteArrayWrapper, value: Int) {
        SanityCheck.check { value < nextID }
        SanityCheck.check { value >= 0 }
        readData(data, mappingID2Page[value], mappingID2Off[value])
    }
}
