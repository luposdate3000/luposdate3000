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
package lupos.dictionary

import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.SanityCheck
import lupos.s01io.BufferManager
import lupos.s01io.BufferManagerExt

public class KeyValueStore {
    private val bufferManager: BufferManager = BufferManagerExt.getBuffermanager("KeyValueStore")
    private var bNodeCounter = 5
    private var lastPage: Int = 0
    private var lastPageBuf: ByteArray = ByteArray(0)
    private var lastPageOffset: Int = 0
    private var nextID = 0
    private var mappingID2Page = IntArray(100)
    private var mappingID2Off = IntArray(100)
    private var mappingSorted = IntArray(100)

    init {
        bufferManager.createPage { page, id ->
            lastPageBuf = page
            lastPage = id
            lastPageOffset = 4
        }
    }

    private inline fun readData(page: Int, off: Int): ByteArray {
        var p = bufferManager.getPage(page)
        var pid = page
        val l = ByteArrayHelper.readInt4(p, off)
        val buf = ByteArray(l)
        var bufoff = 0
        var toread = l
        var pageoff = off + 4
        while (toread > 0) {
            var available = p.size - pageoff
            if (available == 0) {
                var id = ByteArrayHelper.readInt4(p, 0)
                bufferManager.releasePage(pid)
                p = bufferManager.getPage(id)
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
        bufferManager.releasePage(pid)
        return buf
    }

    private inline fun writeData(data: ByteArray, crossinline action: (page: Int, off: Int) -> Unit) {
        if (lastPageOffset >= lastPageBuf.size - 8) {
            bufferManager.createPage { page, id ->
                ByteArrayHelper.writeInt4(lastPageBuf, 0, id)
                bufferManager.releasePage(lastPage)
                lastPageBuf = page
                lastPage = id
            }
            lastPageOffset = 4
        }
        var resPage = lastPage
        var resOff = lastPageOffset
        ByteArrayHelper.writeInt4(lastPageBuf, lastPageOffset, data.size)
        lastPageOffset += 4
        var dataoff = 0
        var towrite = data.size
        while (towrite > 0) {
            var available = lastPageBuf.size - lastPageOffset
            if (available == 0) {
                bufferManager.createPage { page, id ->
                    ByteArrayHelper.writeInt4(lastPageBuf, 0, id)
                    bufferManager.releasePage(lastPage)
                    lastPageBuf = page
                    lastPage = id
                }
                lastPageOffset = 4
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
            dataoff += len
        }
        action(resPage, resOff)
    }

    private fun cmp(a: ByteArray, b: ByteArray): Int {
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
        var l = left
        var r = right
        var loop = true
        while (loop && r >= l) {
            var m = (r - l) / 2 + l
            val d = readData(mappingID2Page[m], mappingID2Off[m])
            val t = cmp(d, data)
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
                onFound(m)
            }
        }
        if (r < l) {
            var res = l
            SanityCheck {
                if (res > left) {
                    val it = readData(mappingID2Page[res - 1], mappingID2Off[res - 1])
                    SanityCheck.check { cmp(it, data) < 0 }
                }
                if (res <= right) {
                    val it = readData(mappingID2Page[res], mappingID2Off[res])
                    SanityCheck.check { cmp(it, data) > 0 }
                }
            }
            onNotFound(res)
        }
    }

    public fun createValue(data: ByteArray): Int {
        var res = 0
        hasData(
            data, 0, nextID - 1,
            onFound = {
                res = it
            },
            onNotFound = {
                res = nextID++
                writeData(data) { page, off ->
                    if (res >= mappingID2Page.size) {
                        var tmp = IntArray(mappingID2Page.size * 2)
                        mappingID2Page.copyInto(tmp)
                        mappingID2Page = tmp
                        tmp = IntArray(mappingID2Off.size * 2)
                        mappingID2Off.copyInto(tmp)
                        mappingID2Off = tmp
                        tmp = IntArray(mappingSorted.size * 2)
                        mappingSorted.copyInto(tmp)
                        mappingSorted = tmp
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
        return readData(mappingID2Page[value], mappingID2Off[value])
    }
}
