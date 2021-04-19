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
import lupos.shared_inline.File
import java.io.RandomAccessFile
import kotlin.jvm.JvmField

@OptIn(kotlin.contracts.ExperimentalContracts::class)
public actual class MyIntArray internal actual constructor(@JvmField internal val filename: String, initialize: Boolean) {
    @JvmField
    internal var bufferManager: BufferManager? = null

    @JvmField
    internal var bufferManagerPage: Int? = null

    @ProguardTestAnnotation
    @JvmField
    internal var closed = false

    @JvmField
    internal val lock = MyReadWriteLock()

    @JvmField
    internal val datafile = RandomAccessFile(filename, "rw")

    @JvmField
    internal var _size = 0

    public actual fun getSize(): Int = _size

    public actual constructor(bufferManager: BufferManager, id: Int, initialize: Boolean) : this(BufferManagerExt.bufferPrefix + bufferManager.name + "." + id + BufferManagerExt.fileEndingIntArray, initialize) {
        this.bufferManager = bufferManager
        this.bufferManagerPage = id
    }

    init {
        if (initialize) {
            _size = datafile.readInt()
        } else {
            datafile.writeInt(0)
        }
    }

    public actual operator fun get(idx: Int): Int {
        SanityCheck.check { !closed }
        SanityCheck.check { idx >= 0 }
        SanityCheck.check { idx < _size }
        var res = 0
        lock.withWriteLock {
            datafile.seek(idx * 4L + 4L)
            res = datafile.readInt()
        }
        return res
    }

    public actual operator fun set(idx: Int, value: Int) {
        SanityCheck.check { !closed }
        SanityCheck.check { idx >= 0 }
        SanityCheck.check { idx < _size }
        lock.withWriteLock {
            datafile.seek(idx * 4L + 4L)
            datafile.writeInt(value)
        }
    }

    public actual fun setSize(size: Int, clean: Boolean) {
        SanityCheck.check { !closed }
        if (size != _size) {
            if (clean) {
                datafile.seek(_size * 4L + 4L)
                for (i in _size until size) {
                    datafile.writeInt(0)
                }
            }
            _size = size
            datafile.seek(0)
            datafile.writeInt(size)
        }
    }

    public actual fun setSize(size: Int) {
        SanityCheck.check { !closed }
        if (size != _size) {
            datafile.seek(_size * 4L + 4L)
            for (i in _size until size) {
                datafile.writeInt(0)
            }
            _size = size
            datafile.seek(0L)
            datafile.writeInt(size)
        }
    }

    public actual fun close() {
        SanityCheck.check { !closed }
        closed = true
        datafile.close()
    }

    public actual fun delete() {
        SanityCheck.check { !closed }
        close()
        if (bufferManagerPage != null) {
            bufferManager?.getPage(lupos.SOURCE_FILE, bufferManagerPage!!)
            bufferManager?.deletePage(lupos.SOURCE_FILE, bufferManagerPage!!)
            bufferManager = null
            bufferManagerPage = null
        }
        File(filename).deleteRecursively()
    }
}
