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
import lupos.buffer_manager.MyIntArray
import lupos.shared.IBufferManager
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.BufferManagerPage
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

public class KeyValueStore public constructor(
    bufferManager: IBufferManager,
    @JvmField
    internal val rootPageID: Int,
    initFromRootPage: Boolean,
    instance: Luposdate3000Instance,
) {

    @JvmField
    internal val rootPage: ByteArray

    @JvmField
    internal val bufferManager: IBufferManager = bufferManager

    @JvmField
    internal var lastPage: Int

    @JvmField
    internal var lastPageBuf: ByteArray

    @JvmField
    internal var lastPageOffset: Int

    @JvmField
    internal var nextID: Int

    @JvmField
    internal var mappingID2Page: MyIntArray

    @JvmField
    internal var mappingID2Off: MyIntArray

    init {
        rootPage = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:61"/*SOURCE_FILE_END*/, rootPageID)
        var id1: Int
        var id2: Int
        if (initFromRootPage) {
            lastPage = BufferManagerPage.readInt4(rootPage, 0)
            lastPageBuf = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:66"/*SOURCE_FILE_END*/, lastPage)
            lastPageOffset = BufferManagerPage.readInt4(rootPage, 4)
            nextID = BufferManagerPage.readInt4(rootPage, 8)
            id1 = BufferManagerPage.readInt4(rootPage, 12)
            id2 = BufferManagerPage.readInt4(rootPage, 16)
        } else {
            lastPageOffset = 4
            lastPage = bufferManager.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:73"/*SOURCE_FILE_END*/)
            lastPageBuf = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:74"/*SOURCE_FILE_END*/, lastPage)
            BufferManagerPage.writeInt4(rootPage, 0, lastPage)
            BufferManagerPage.writeInt4(rootPage, 4, lastPageOffset)
            nextID = 0
            BufferManagerPage.writeInt4(rootPage, 8, nextID)
            id1 = bufferManager.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:79"/*SOURCE_FILE_END*/)
            id2 = bufferManager.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:80"/*SOURCE_FILE_END*/)
            BufferManagerPage.writeInt4(rootPage, 12, id1)
            BufferManagerPage.writeInt4(rootPage, 16, id2)
        }
        mappingID2Page = MyIntArray(bufferManager, id1, initFromRootPage, instance)
        mappingID2Off = MyIntArray(bufferManager, id2, initFromRootPage, instance)
    }

    @ProguardTestAnnotation
    public fun delete() {
        bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:90"/*SOURCE_FILE_END*/, lastPage)
        var pageid: Int
        if (nextID == 0) {
            pageid = lastPage
        } else {
            pageid = mappingID2Page[0]
            while (pageid != lastPage) {
                val page = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:97"/*SOURCE_FILE_END*/, pageid)
                val nextPage = BufferManagerPage.readInt4(page, 0)
                bufferManager.deletePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:99"/*SOURCE_FILE_END*/, pageid)
                pageid = nextPage
            }
        }
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:103"/*SOURCE_FILE_END*/ }, { pageid == lastPage })
        bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:104"/*SOURCE_FILE_END*/, lastPage)
        bufferManager.deletePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:105"/*SOURCE_FILE_END*/, lastPage)
        mappingID2Page.delete()
        mappingID2Off.delete()
        bufferManager.deletePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:108"/*SOURCE_FILE_END*/, rootPageID)
    }

    @ProguardTestAnnotation
    public fun close() {
        mappingID2Page.close()
        mappingID2Off.close()
        bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:115"/*SOURCE_FILE_END*/, rootPageID)
        bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:116"/*SOURCE_FILE_END*/, lastPage)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun readData(data: ByteArrayWrapper, page: Int, off: Int) {
        var p = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:121"/*SOURCE_FILE_END*/, page)
        var pid = page
        val l = BufferManagerPage.readInt4(p, off)
        ByteArrayWrapperExt.setSize(data, l)
        var bufoff = 0
        var toread = l
        var pageoff = off + 4
        while (toread > 0) {
            var available = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - pageoff
            if (available == 0) {
                val id = BufferManagerPage.readInt4(p, 0)
                bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:132"/*SOURCE_FILE_END*/, pid)
                p = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:133"/*SOURCE_FILE_END*/, id)
                pid = id
                pageoff = 4
                available = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - pageoff
            }
            val len = if (available < toread) {
                available
            } else {
                toread
            }
            BufferManagerPage.copyInto(p, ByteArrayWrapperExt.getBuf(data), bufoff, pageoff, pageoff + len)
            bufoff += len
            pageoff += len
            toread -= len
        }
        bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:148"/*SOURCE_FILE_END*/, pid)
    }

    private inline fun writeData(data: ByteArrayWrapper, crossinline action: (page: Int, off: Int) -> Unit) {
        if (lastPageOffset >= BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 8) {
            val pageid = bufferManager.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:153"/*SOURCE_FILE_END*/)
            BufferManagerPage.writeInt4(lastPageBuf, 0, pageid)
            bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:155"/*SOURCE_FILE_END*/, lastPage)
            lastPageBuf = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:156"/*SOURCE_FILE_END*/, pageid)
            lastPage = pageid
            BufferManagerPage.writeInt4(rootPage, 0, lastPage)
            lastPageOffset = 4
            BufferManagerPage.writeInt4(rootPage, 4, lastPageOffset)
        }
        val resPage = lastPage
        val resOff = lastPageOffset
        BufferManagerPage.writeInt4(lastPageBuf, lastPageOffset, ByteArrayWrapperExt.getSize(data))
        lastPageOffset += 4
        BufferManagerPage.writeInt4(rootPage, 4, lastPageOffset)
        var dataoff = 0
        var towrite = ByteArrayWrapperExt.getSize(data)
        while (towrite > 0) {
            var available = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - lastPageOffset
            if (available == 0) {
                val pageid = bufferManager.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:172"/*SOURCE_FILE_END*/)
                BufferManagerPage.writeInt4(lastPageBuf, 0, pageid)
                bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:174"/*SOURCE_FILE_END*/, lastPage)
                lastPageBuf = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:175"/*SOURCE_FILE_END*/, pageid)
                lastPage = pageid
                BufferManagerPage.writeInt4(rootPage, 0, lastPage)
                lastPageOffset = 4
                BufferManagerPage.writeInt4(rootPage, 4, lastPageOffset)
                available = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - lastPageOffset
            }
            val len = if (available < towrite) {
                available
            } else {
                towrite
            }
            BufferManagerPage.copyFrom(lastPageBuf, ByteArrayWrapperExt.getBuf(data), lastPageOffset, dataoff, dataoff + len)
            towrite -= len
            lastPageOffset += len
            BufferManagerPage.writeInt4(rootPage, 4, lastPageOffset)
            dataoff += len
        }
        action(resPage, resOff)
    }

    public fun createValue(data: ByteArrayWrapper): Int {
        var res = nextID++
        BufferManagerPage.writeInt4(rootPage, 8, nextID)
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
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:211"/*SOURCE_FILE_END*/ }, { value < nextID })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:212"/*SOURCE_FILE_END*/ }, { value >= 0 })
        readData(data, mappingID2Page[value], mappingID2Off[value])
    }
}
