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
import lupos.s00misc.File
import lupos.s00misc.MyReadWriteLock
import lupos.s00misc.SanityCheck
import java.io.RandomAccessFile
import kotlin.jvm.JvmField

@OptIn(kotlin.contracts.ExperimentalContracts::class)
public actual class MyIntArray internal actual constructor(@JvmField private val filename: String, initialize: Boolean) {
    public actual constructor(bufferManager: BufferManager, id: Int, initialize: Boolean) : this(BufferManagerExt.bufferPrefix + bufferManager.name + "." + id + BufferManagerExt.fileEndingIntArray, initialize)

    @ProguardTestAnnotation
    private var closed = false
    private val lock = MyReadWriteLock()
    private val datafile = RandomAccessFile(filename, "rw")
    private var _size = 0
    public actual fun getSize(): Int = _size

    init {
        if (initialize) {
            _size = datafile.readInt()
        }
    }

    public actual operator fun get(idx: Int): Int {
        SanityCheck.check { !closed }
        SanityCheck.check { idx >= 0 }
        SanityCheck.check { idx < _size }
        var res = 0
        lock.withWriteLock {
            datafile.seek(idx * 4 + 4L)
            res = datafile.readInt()
        }
        return res
    }

    public actual operator fun set(idx: Int, value: Int) {
        SanityCheck.check { !closed }
        SanityCheck.check { idx >= 0 }
        SanityCheck.check { idx < _size }
        lock.withWriteLock {
            datafile.seek(idx * 4 + 4L)
            datafile.writeInt(value)
        }
    }

    public actual fun setSize(size: Int, clean: Boolean) {
        SanityCheck.check { !closed }
        if (clean) {
            datafile.seek(_size * 4 + 4L)
            for (i in _size until size) {
                datafile.writeInt(0)
            }
        }
        _size = size
        datafile.seek(0)
        datafile.writeInt(size)
    }

    public actual fun setSize(size: Int) {
        SanityCheck.check { !closed }
        datafile.seek(_size * 4 + 4L)
        for (i in _size until size) {
            datafile.writeInt(0)
        }
        _size = size
        datafile.seek(0L)
        datafile.writeInt(size)
    }

    public actual fun close() {
        closed = true
        datafile.close()
    }

    public actual fun delete() {
        close()
        File(filename).deleteRecursively()
    }
}
