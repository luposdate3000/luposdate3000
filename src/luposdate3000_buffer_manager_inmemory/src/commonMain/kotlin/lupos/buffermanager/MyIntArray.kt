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
package lupos.buffermanager

import lupos.ProguardTestAnnotation
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField

@OptIn(kotlin.contracts.ExperimentalContracts::class)
public class MyIntArray internal constructor(@JvmField private val filename: String, initialize: Boolean) {
    private var bufferManager: BufferManager? = null
    private var bufferManagerPage: Int? = null

    public constructor(bufferManager: BufferManager, id: Int, initialize: Boolean) : this("", initialize) {
        this.bufferManager = bufferManager
        this.bufferManagerPage = id
    }

    @ProguardTestAnnotation
    private var closed = false
    private val lock = MyReadWriteLock()
    private var data = IntArray(0)

    public fun getSize(): Int = data.size

    public operator fun get(idx: Int): Int {
        SanityCheck.check { !closed }
        SanityCheck.check { idx >= 0 }
        SanityCheck.check { idx < data.size }
        var res = 0
        lock.withReadLock {
            res = data[idx]
        }
        return res
    }

    public operator fun set(idx: Int, value: Int) {
        SanityCheck.check { !closed }
        SanityCheck.check { idx >= 0 }
        SanityCheck.check { idx < data.size }
        lock.withWriteLock {
            data[idx] = value
        }
    }

    public fun setSize(size: Int, clean: Boolean) {
        SanityCheck.check { !closed }
        if (size != data.size) {
            lock.withWriteLock {
                var newData = IntArray(size)
                if (data.size < newData.size) {
                    data.copyInto(newData)
                } else {
                    data.copyInto(newData, 0, 0, newData.size)
                }
                data = newData
            }
        }
    }

    public fun setSize(size: Int) {
        SanityCheck.check { !closed }
        if (size != data.size) {
            lock.withWriteLock {
                var newData = IntArray(size)
                if (data.size < newData.size) {
                    data.copyInto(newData)
                } else {
                    data.copyInto(newData, 0, 0, newData.size)
                }
                data = newData
            }
        }
    }

    public fun close() {
        SanityCheck.check { !closed }
        closed = true
    }

    public fun delete() {
        SanityCheck.check { !closed }
        close()
        if (bufferManagerPage != null) {
            bufferManager?.getPage(lupos.SOURCE_FILE, bufferManagerPage!!)
            bufferManager?.deletePage(lupos.SOURCE_FILE, bufferManagerPage!!)
            bufferManager = null
            bufferManagerPage = null
        }
    }
}
