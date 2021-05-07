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
import lupos.buffer_manager.BufferManager
import lupos.buffer_manager.MyIntArray
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared_inline.BufferManagerPage
import lupos.shared_inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

public class KeyValueStore {
    @JvmField
    internal val rootPageID: Int

    @JvmField
    internal val rootPage: ByteArray

    @JvmField
    internal val bufferManager: BufferManager

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

    public constructor(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) {
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        rootPage = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:58", rootPageID)
        var id1 = 0
        var id2 = 0
        if (initFromRootPage) {
            lastPage = BufferManagerPage.readInt4(rootPage, 0)
            lastPageBuf = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:63", lastPage)
            lastPageOffset = BufferManagerPage.readInt4(rootPage, 4)
            nextID = BufferManagerPage.readInt4(rootPage, 8)
            id1 = BufferManagerPage.readInt4(rootPage, 12)
            id2 = BufferManagerPage.readInt4(rootPage, 16)
        } else {
            lastPageOffset = 4
            lastPage = bufferManager.allocPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:70")
            lastPageBuf = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:71", lastPage)
            BufferManagerPage.writeInt4(rootPage, 0, lastPage)
            BufferManagerPage.writeInt4(rootPage, 4, lastPageOffset)
            nextID = 0
            BufferManagerPage.writeInt4(rootPage, 8, nextID)
            id1 = bufferManager.allocPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:76")
            id2 = bufferManager.allocPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:77")
            BufferManagerPage.writeInt4(rootPage, 12, id1)
            BufferManagerPage.writeInt4(rootPage, 16, id2)
        }
        mappingID2Page = MyIntArray(bufferManager, id1, initFromRootPage)
        mappingID2Off = MyIntArray(bufferManager, id2, initFromRootPage)
    }

    @ProguardTestAnnotation
    public fun delete() {
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:87", lastPage)
        var pageid = -1
        if (nextID == 0) {
            pageid = lastPage
        } else {
            pageid = mappingID2Page[0]
            while (pageid != lastPage) {
                val page = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:94", pageid)
                var nextPage = BufferManagerPage.readInt4(page, 0)
                bufferManager.deletePage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:96", pageid)
                pageid = nextPage
            }
        }
        SanityCheck.check { pageid == lastPage }
        bufferManager.getPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:101", lastPage)
        bufferManager.deletePage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:102", lastPage)
        mappingID2Page.delete()
        mappingID2Off.delete()
        bufferManager.deletePage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:105", rootPageID)
    }

    @ProguardTestAnnotation
    public fun close() {
        mappingID2Page.close()
        mappingID2Off.close()
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:112", rootPageID)
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:113", lastPage)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun readData(data: ByteArrayWrapper, page: Int, off: Int) {
        var p = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:118", page)
        var pid = page
        val l = BufferManagerPage.readInt4(p, off)
        ByteArrayWrapperExt.setSize(data, l)
        var bufoff = 0
        var toread = l
        var pageoff = off + 4
        while (toread > 0) {
            var available = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - pageoff
            if (available == 0) {
                var id = BufferManagerPage.readInt4(p, 0)
                bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:129", pid)
                p = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:130", id)
                pid = id
                pageoff = 4
                available = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - pageoff
            }
            var len = if (available < toread) {
                available
            } else {
                toread
            }
            BufferManagerPage.copyInto(p, data.buf, bufoff, pageoff, pageoff + len)
            bufoff += len
            pageoff += len
            toread -= len
        }
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:145", pid)
    }

    private inline fun writeData(data: ByteArrayWrapper, crossinline action: (page: Int, off: Int) -> Unit) {
        if (lastPageOffset >= BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - 8) {
            val pageid = bufferManager.allocPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:150")
            BufferManagerPage.writeInt4(lastPageBuf, 0, pageid)
            bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:152", lastPage)
            lastPageBuf = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:153", pageid)
            lastPage = pageid
            BufferManagerPage.writeInt4(rootPage, 0, lastPage)
            lastPageOffset = 4
            BufferManagerPage.writeInt4(rootPage, 4, lastPageOffset)
        }
        var resPage = lastPage
        var resOff = lastPageOffset
        BufferManagerPage.writeInt4(lastPageBuf, lastPageOffset, data.size)
        lastPageOffset += 4
        BufferManagerPage.writeInt4(rootPage, 4, lastPageOffset)
        var dataoff = 0
        var towrite = data.size
        while (towrite > 0) {
            var available = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - lastPageOffset
            if (available == 0) {
                val pageid = bufferManager.allocPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:169")
                BufferManagerPage.writeInt4(lastPageBuf, 0, pageid)
                bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:171", lastPage)
                lastPageBuf = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_kv/src/commonMain/kotlin/lupos/kv/KeyValueStore.kt:172", pageid)
                lastPage = pageid
                BufferManagerPage.writeInt4(rootPage, 0, lastPage)
                lastPageOffset = 4
                BufferManagerPage.writeInt4(rootPage, 4, lastPageOffset)
                available = BufferManagerPage.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES - lastPageOffset
            }
            var len = if (available < towrite) {
                available
            } else {
                towrite
            }
            BufferManagerPage.copyFrom(lastPageBuf, data.buf, lastPageOffset, dataoff, dataoff + len)
            towrite -= len
            lastPageOffset += len
            BufferManagerPage.writeInt4(rootPage, 4, lastPageOffset)
            dataoff += len
        }
        action(resPage, resOff)
    }

    public fun createValue(data: ByteArrayWrapper): Int {
        var res = 0
        res = nextID++
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
        SanityCheck.check { value < nextID }
        SanityCheck.check { value >= 0 }
        readData(data, mappingID2Page[value], mappingID2Off[value])
    }
}
