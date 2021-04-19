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
import lupos.shared_inline.SanityCheck
import kotlin.jvm.JvmField
import kotlin.math.max

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
    private var _size = 0
    public fun getSize(): Int = _size

    public operator fun get(idx: Int): Int {
        SanityCheck.check { !closed }
        SanityCheck.check { idx >= 0 }
        SanityCheck.check { idx < _size }
        var res = 0
        lock.withReadLock {
            res = data[idx]
        }
        return res
    }

    public operator fun set(idx: Int, value: Int) {
        SanityCheck.check { !closed }
        SanityCheck.check { idx >= 0 }
        SanityCheck.check { idx < _size }
        lock.withWriteLock {
            data[idx] = value
        }
    }

    public fun setSize(size: Int, clean: Boolean) {
        SanityCheck.check { !closed }
        if (size != _size) {
            lock.withWriteLock {
                if (data.size < size) {
                    var newSize = max(_size, 1)
                    while (newSize < size) {
                        newSize *= 2
                    }
                    var newData = IntArray(newSize)
                    if (_size < newSize) {
                        data.copyInto(newData)
                    } else {
                        data.copyInto(newData, 0, 0, newSize)
                    }
                    data = newData
                } else if (clean) {
                    for (i in _size until size) {
                        data[i] = 0
                    }
                }
                _size = size
            }
        }
    }

    public fun setSize(size: Int) {
        SanityCheck.check { !closed }
        if (size != _size) {
            lock.withWriteLock {
                if (data.size < size) {
                    var newSize = max(_size, 1)
                    while (newSize < size) {
                        newSize *= 2
                    }
                    var newData = IntArray(newSize)
                    if (_size < newSize) {
                        data.copyInto(newData)
                    } else {
                        data.copyInto(newData, 0, 0, newSize)
                    }
                    data = newData
                }
                _size = size
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
