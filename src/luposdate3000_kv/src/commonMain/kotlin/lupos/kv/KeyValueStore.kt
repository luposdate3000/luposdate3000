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
import lupos.buffermanager.BufferManager
import lupos.buffermanager.MyIntArray
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.Parallel
import lupos.s00misc.SanityCheck

public class KeyValueStore {
    private val rootPageID: Int
    private val rootPage: ByteArray
    private val bufferManager: BufferManager
    private var lastPage: Int
    private var lastPageBuf: ByteArray
    private var lastPageOffset: Int
    private var nextID: Int
    private var mappingID2Page: MyIntArray
    private var mappingID2Off: MyIntArray
    private var mappingSorted: MyIntArray

    private val counters = IntArray(12)

    init {
        SanityCheck {
            Parallel.launch {
                while (true) {
                    println("KeyValueStore.counters ${counters.map { it }}")
                    Parallel.delay(1000)
                }
            }
        }
    }

    public constructor(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) {
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        if (initFromRootPage) {
            lastPage = ByteArrayHelper.readInt4(rootPage, 0)
            lastPageBuf = bufferManager.getPage(lupos.SOURCE_FILE, lastPage)
            lastPageOffset = ByteArrayHelper.readInt4(rootPage, 4)
            nextID = ByteArrayHelper.readInt4(rootPage, 8)
            var id1 = ByteArrayHelper.readInt4(rootPage, 12)
            var id2 = ByteArrayHelper.readInt4(rootPage, 16)
            var id3 = ByteArrayHelper.readInt4(rootPage, 20)
            mappingID2Page = MyIntArray(bufferManager, id1, initFromRootPage)
            mappingID2Off = MyIntArray(bufferManager, id2, initFromRootPage)
            mappingSorted = MyIntArray(bufferManager, id3, initFromRootPage)
        } else {
            lastPageBuf = ByteArray(0)
            lastPage = 0
            lastPageOffset = 0
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                lastPageBuf = page
                lastPage = pageid
                lastPageOffset = 4
            }
            ByteArrayHelper.writeInt4(rootPage, 0, lastPage)
            ByteArrayHelper.writeInt4(rootPage, 4, lastPageOffset)
            nextID = 0
            ByteArrayHelper.writeInt4(rootPage, 8, nextID)
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
            ByteArrayHelper.writeInt4(rootPage, 12, id1)
            ByteArrayHelper.writeInt4(rootPage, 16, id2)
            ByteArrayHelper.writeInt4(rootPage, 20, id3)
        }
    }

    @ProguardTestAnnotation
    public fun delete() {
        counters[0]++

        var id1 = ByteArrayHelper.readInt4(rootPage, 12)
        var id2 = ByteArrayHelper.readInt4(rootPage, 16)
        var id3 = ByteArrayHelper.readInt4(rootPage, 20)
        bufferManager.getPage(lupos.SOURCE_FILE, id1)
        bufferManager.deletePage(lupos.SOURCE_FILE, id1)
        bufferManager.getPage(lupos.SOURCE_FILE, id2)
        bufferManager.deletePage(lupos.SOURCE_FILE, id2)
        bufferManager.getPage(lupos.SOURCE_FILE, id3)
        bufferManager.deletePage(lupos.SOURCE_FILE, id3)
        bufferManager.releasePage(lupos.SOURCE_FILE, lastPage)
        var pageid = -1
        if (nextID == 0) {
            pageid = lastPage
        } else {
            pageid = mappingID2Page[0]
            while (pageid != lastPage) {
                val page = bufferManager.getPage(lupos.SOURCE_FILE, pageid)
                var nextPage = ByteArrayHelper.readInt4(page, 0)
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
        counters[1]++
        mappingID2Page.close()
        mappingID2Off.close()
        mappingSorted.close()
        bufferManager.releasePage(lupos.SOURCE_FILE, rootPageID)
        bufferManager.releasePage(lupos.SOURCE_FILE, lastPage)
    }

    private inline fun readData(page: Int, off: Int): ByteArray {
        counters[2]++
        var p = bufferManager.getPage(lupos.SOURCE_FILE, page)
        var pid = page
        val l = ByteArrayHelper.readInt4(p, off)
        val buf = ByteArray(l)
        var bufoff = 0
        var toread = l
        var pageoff = off + 4
        while (toread > 0) {
            counters[3]++
            var available = p.size - pageoff
            if (available == 0) {
                var id = ByteArrayHelper.readInt4(p, 0)
                bufferManager.releasePage(lupos.SOURCE_FILE, pid)
                p = bufferManager.getPage(lupos.SOURCE_FILE, id)
                pid = id
                pageoff = 4
                available = p.size - pageoff
            }
            var len = if (available < toread) {
                available
            } else {
                toread
            }
            p.copyInto(buf, bufoff, pageoff, pageoff + len)
            bufoff += len
            pageoff += len
            toread -= len
        }
        bufferManager.releasePage(lupos.SOURCE_FILE, pid)
        return buf
    }

    private inline fun writeData(data: ByteArray, crossinline action: (page: Int, off: Int) -> Unit) {
        counters[4]++
        if (lastPageOffset >= lastPageBuf.size - 8) {
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                ByteArrayHelper.writeInt4(lastPageBuf, 0, pageid)
                bufferManager.releasePage(lupos.SOURCE_FILE, lastPage)
                lastPageBuf = page
                lastPage = pageid
                ByteArrayHelper.writeInt4(rootPage, 0, lastPage)
            }
            lastPageOffset = 4
            ByteArrayHelper.writeInt4(rootPage, 4, lastPageOffset)
        }
        var resPage = lastPage
        var resOff = lastPageOffset
        ByteArrayHelper.writeInt4(lastPageBuf, lastPageOffset, data.size)
        lastPageOffset += 4
        ByteArrayHelper.writeInt4(rootPage, 4, lastPageOffset)
        var dataoff = 0
        var towrite = data.size
        while (towrite > 0) {
            counters[5]++
            var available = lastPageBuf.size - lastPageOffset
            if (available == 0) {
                bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                    ByteArrayHelper.writeInt4(lastPageBuf, 0, pageid)
                    bufferManager.releasePage(lupos.SOURCE_FILE, lastPage)
                    lastPageBuf = page
                    lastPage = pageid
                    ByteArrayHelper.writeInt4(rootPage, 0, lastPage)
                }
                lastPageOffset = 4
                ByteArrayHelper.writeInt4(rootPage, 4, lastPageOffset)
                available = lastPageBuf.size - lastPageOffset
            }
            var len = if (available < towrite) {
                available
            } else {
                towrite
            }
            data.copyInto(lastPageBuf, lastPageOffset, dataoff, dataoff + len)
            towrite -= len
            lastPageOffset += len
            ByteArrayHelper.writeInt4(rootPage, 4, lastPageOffset)
            dataoff += len
        }
        action(resPage, resOff)
    }

    private fun cmp(a: ByteArray, b: ByteArray): Int {
        counters[6]++
        var t = 0
        if (t == 0) {
            t = a.size - b.size
        }
        var i = 0
        while (t == 0 && i < a.size) {
            t = a[i] - b[i]
            i++
        }
        return t
    }

    private inline fun hasData(data: ByteArray, left: Int, right: Int, crossinline onFound: (Int/*the id to return*/) -> Unit, onNotFound: (Int/*the smallest index, which value is larger than the target*/) -> Unit) {
        counters[7]++
        // println("hasData $left $right")
        var l = left
        var r = right
        var loop = true
/*
SanityCheck.check{l<=r}
if(right>0){
val m2=mappingSorted[right]
val d=readData(mappingID2Page[m2], mappingID2Off[m2])
val t = cmp(d, data)
if(t==0){
onFound(m2)
loop=false
}else if(t<0){
onNotFound(right)
loop=false
}
}
*/
        while (loop && r >= l) {
            counters[8]++
            var m = (r - l) / 2 + l
            val m2 = mappingSorted[m]
            val d = readData(mappingID2Page[m2], mappingID2Off[m2])
            val t = cmp(d, data)
            // println("readData #$m id:$m2 ($t) ${d.map{it}}")
            if (t < 0) {
                if (m == l) {
                    m++
                }
                l = m
            } else if (t > 0) {
                if (m == r) {
                    m--
                }
                r = m
            } else {
                loop = false
                // println("onFound(#$m id:$m2)")
                onFound(m2)
            }
        }
        if (r < l) {
            var res = l
            SanityCheck {
                if (res > left) {
                    val res2 = mappingSorted[res - 1]
                    val it = readData(mappingID2Page[res2], mappingID2Off[res2])
                    SanityCheck.check { cmp(it, data) < 0 }
                }
                if (res <= right) {
                    val res2 = mappingSorted[res]
                    val it = readData(mappingID2Page[res2], mappingID2Off[res2])
                    SanityCheck.check { cmp(it, data) > 0 }
                }
            }
            onNotFound(res)
        }
    }

    public fun createValue(data: ByteArray): Int {
        counters[9]++
        var res = 0
        hasData(
            data, 0, nextID - 1,
            onFound = {
                res = it
            },
            onNotFound = {
                res = nextID++
                ByteArrayHelper.writeInt4(rootPage, 8, nextID)
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

    public fun hasValue(data: ByteArray): Int? {
        counters[10]++
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

    public fun getValue(value: Int): ByteArray {
        counters[11]++
        SanityCheck.check { value < nextID }
        SanityCheck.check { value >= 0 }
        return readData(mappingID2Page[value], mappingID2Off[value])
    }
}
