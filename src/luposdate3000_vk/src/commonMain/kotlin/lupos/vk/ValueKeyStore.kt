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
package lupos.vk
import lupos.ProguardTestAnnotation
import lupos.buffermanager.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
import lupos.buffermanager.BufferManager
import lupos.buffermanager.BufferManagerPage
import lupos.s00misc.ByteArrayWrapper
import kotlin.math.min

public class ValueKeyStore {
    internal companion object {
        const val PAGEID_NULL_PTR = -1
        const val PAGE_TYPE_LEAF = 0
        const val ID_NULL = -1
    }
    private val rootPageID: Int
    private val bufferManager: BufferManager
    private var firstLeafID = ValueKeyStore.PAGEID_NULL_PTR
    private var lastLeafID = ValueKeyStore.PAGEID_NULL_PTR
    private val lastValueBuffer = ByteArrayWrapper()

    constructor(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) {
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        val rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        if (initFromRootPage) {
            firstLeafID = rootPage.readInt4(0)
            lastLeafID = rootPage.readInt4(4)
            val reader = ValueKeyStoreIterator(bufferManager, lastLeaf, lastValueBuffer)
            while (reader.hasNext()) {
                reader.next()
            }
            reader.close()
        } else {
            val writer = ValueKeyStoreWriter(bufferManager)
            writer.close()
            firstLeafID = writer.rootPageID
            lastLeafID = writer.lastPageID
            rootPage.writeInt4(0, firstLeafID)
            rootPage.writeInt4(4, lastLeafID)
        }
        bufferManager.releasePage(lupos.SOURCE_FILE, rootPageID)
    }
    @ProguardTestAnnotation
    public fun delete() {
var node = firstLeafID
        while (node != ValueKeyStore.PAGEID_NULL_PTR) {
            val page = bufferManager.getPage(lupos.SOURCE_FILE, node)
            nextPageID = page.readInt4(4)
            bufferManager.deletePage(lupos.SOURCE_FILE, node)
            node = nextPageID
        }
        val rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        bufferManager.deletePage(lupos.SOURCE_FILE, rootPageID)
    }
    @ProguardTestAnnotation
    public fun close() {
    }
@ProguardTestAnnotation
public fun getIterator(buffer:ByteArrayWrapper):ValueKeyStoreIterator{
return ValueKeyStoreIterator(bufferManager, firstLeafID, buffer)
}
    public fun hasValue(data: ByteArrayWrapper): Int? {
        val buffer = ByteArrayWrapper()
        var iterator = ValueKeyStoreIterator(bufferManager, firstLeafID, buffer)
        var res = 0
        while (iterator.hasNext() && buffer <data) {
            res = iterator.next()
        }
        if (buffer == data) {
            return res
        }
        iterator.close()
        return null
    }
    public fun createValue(data: ByteArrayWrapper, value: () -> Int): Int {
        SanityCheck.check { hasValue(data) == null }
        var res = -1
        val buffer = ByteArrayWrapper()
        val writer = ValueKeyStoreWriter(bufferManager)
        val reader = ValueKeyStoreIterator(bufferManager, firstLeaf, buffer)
        if (reader.hasNext()) {
            while (reader.hasNext()) {
                val id = reader.next()
                if (data == buffer) {
                    writer.write(buffer, id)
                    break
                    res = id
                } else if (data < buffer) {
                    res = value()
                    writer.write(data, res)
                    break
                } else {
                    writer.write(buffer, id)
                }
            }
            while (reader.hasNext()) {
                val id = reader.next()
                writer.write(buffer, id)
            }
            buffer.copyInto(lastValueBuffer)
        } else {
            res = value()
            writer.write(data, res)
            data.copyInto(lastValueBuffer)
        }
        reader.close()
        writer.close()
        var node = firstLeafID
        while (node != ValueKeyStore.PAGEID_NULL_PTR) {
            val page = bufferManager.getPage(lupos.SOURCE_FILE, node)
            nextPageID = page.readInt4(4)
            bufferManager.deletePage(lupos.SOURCE_FILE, node)
            node = nextPageID
        }
        firstLeafID = writer.rootPageID
        lastLeafID = writer.lastPageID
        val rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        rootPage.writeInt4(0, firstLeafID)
        rootPage.writeInt4(4, lastLeafID)
        bufferManager.releasePage(lupos.SOURCE_FILE, rootPageID)
    }

}

public class ValueKeyStoreWriter {
    var rootPageID: Int = ValueKeyStore.PAGEID_NULL_PTR
    var lastPageID: Int = ValueKeyStore.PAGEID_NULL_PTR
    var pageid: Int = ValueKeyStore.PAGEID_NULL_PTR
    var page: BufferManagerPage
    var offset = 12
    val lastBuffer = ByteArrayWrapper()
    private val bufferManager: BufferManager
    constructor(bufferManager: BufferManager) {
        this.bufferManager = bufferManager
        var nextpageid = ValueKeyStore.PAGEID_NULL_PTR
        var nextpage: BufferManagerPage? = null
        bufferManager.createPage(lupos.SOURCE_FILE) { pageid2, page2 ->
            nextpageid = pageid2
            nextpage = page2
        }
        pageid = nextpageid
        page = nextpage!!
        rootPageID = pageid
        page.writeInt(0, ValueKeyStore.PAGE_TYPE_LEAF)
        page.writeInt(4, ValueKeyStore.PAGEID_NULL_PTR)
        page.writeInt(8, 12)
    }
    public fun write(id: Int, buffer: ByteArrayWrapper, onNextEntryPoint: () -> Unit) {
        SanityCheck.check { offet <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 12 }
        var common = buffer.commonBytes(lastBuffer)
        buffer.copyInto(lastBuffer)
        page.writeInt4(offset, id)
        page.writeInt4(offset + 4, buffer.getSize())
        page.writeInt4(offset + 8, common)
        lastPageID = pageid
        offset += 12
        val len = buffer.getSize()
        var bufferOffset = common
        var writeEntryPoint = false
        while (bufferOffset <len) {
            if (offset == BUFFER_MANAGER_PAGE_SIZE_IN_BYTES) {
                var nextpageid = ValueKeyStore.PAGEID_NULL_PTR
                var nextpage: BufferManagerPage? = null
                bufferManager.createPage(lupos.SOURCE_FILE) { pageid2, page2 ->
                    nextpageid = pageid2
                    nextpage = page2
                }
                page.writeInt(4, nextpageid)
                BufferManager.releasePage(lupos.SOURCE_FILE, pageid)
                pageid = nextpageid
                page = nextpage!!
                page.writeInt(0, ValueKeyStore.PAGE_TYPE_LEAF)
                page.writeInt(4, ValueKeyStore.PAGEID_NULL_PTR)
                offset = 12
                writeEntryPoint = true
            }
            val l1 = min(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - offset, len - bufferOffset)
            page.copyFrom(buffer.getBuf(), offset, bufferOffset, bufferOffset + l1)
            bufferOffset += l1
            offset += l1
            if (writeEntryPoint) {
                if (offset > BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 12) {
                    var nextpageid = ValueKeyStore.PAGEID_NULL_PTR
                    var nextpage: BufferManagerPage? = null
                    bufferManager.createPage(lupos.SOURCE_FILE) { pageid2, page2 ->
                        nextpageid = pageid2
                        nextpage = page2
                    }
                    page.writeInt(4, nextpageid)
                    BufferManager.releasePage(lupos.SOURCE_FILE, pageid)
                    pageid = nextpageid
                    page = nextpage!!
                    page.writeInt(0, ValueKeyStore.PAGE_TYPE_LEAF)
                    page.writeInt(4, ValueKeyStore.PAGEID_NULL_PTR)
                    offset = 12
                }
                page.writeInt(8, offset)
                lastBuffer.setSize(0)
                onNextEntryPoint()
            }
        }
    }
    fun close() {
        if (offset <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 12) {
            page.writeInt(offset, ValueKeyStore.ID_NULL)
        }
        BufferManager.releasePage(lupos.SOURCE_FILE, pageid)
    }
}
public class ValueKeyStoreIterator(private val bufferManager: BufferManager, startPageID: Int, private val buffer: ByteArrayWrapper) {
    var pageid = startPageID
    var page = BufferManager.getPage(lupos.SOURCE_FILE, pageid)
    var nextPageID = page.readInt4(4)
    var offset = page.readInt4(8)
    fun hasNext(): Boolean {
        if (offset> BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 12) {
            BufferManager.releasePage(lupos.SOURCE_FILE, pageid)
            pageid = nextPageID
            if (pageid != ValueKeyStore.PAGEID_NULL_PTR) {
                page = BufferManager.getPage(lupos.SOURCE_FILE, pageid)
                nextPageID = page.readInt4(4)
                offset = page.readInt4(8)
                SanityCheck.check { page.readInt4(0) == ValueKeyStore.PAGE_TYPE_LEAF }
                return true
            } else {
                return false
            }
        } else {
            SanityCheck.check { page.readInt4(0) == ValueKeyStore.PAGE_TYPE_LEAF }
            return page.readInt4(offset) != ValueKeyStore.ID_NULL
        }
    }
    fun next(): Int {
        SanityCheck.check { page.readInt4(0) == ValueKeyStore.PAGE_TYPE_LEAF }
        val id = page.readInt4(offset)
        val len = page.readInt4(offset + 4)
        var bufferOffset = page.readInt4(offset + 8)
        offset += 12
        buffer.setSizeCopy(len)
        while (bufferOffset <len) {
            if (offset == BUFFER_MANAGER_PAGE_SIZE_IN_BYTES) {
                BufferManager.releasePage(lupos.SOURCE_FILE, pageid)
                pageid = nextPageID
                SanityCheck.check { pageid != ValueKeyStore.PAGEID_NULL_PTR }
                page = BufferManager.getPage(lupos.SOURCE_FILE, pageid)
                nextPageID = page.readInt4(4)
                offset = 12
            }
            val l1 = min(BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - offset, len - bufferOffset)
            page.copyInto(buffer.getBuf(), bufferOffset, offset, offset + l1)
            bufferOffset += l1
            offset += l1
        }
        return id
    }
    fun close() {
        if (pageid != ValueKeyStore.PAGEID_NULL_PTR) {
            BufferManager.releasePage(lupos.SOURCE_FILE, pageid)
        }
    }
}
