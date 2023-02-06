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
package lupos.buffer_manager

import lupos.ProguardTestAnnotation
import lupos.shared.IBufferManager
import lupos.shared.Luposdate3000Instance
import lupos.shared.MyReadWriteLock
import lupos.shared.SanityCheck
import lupos.shared.inline.File
import java.io.RandomAccessFile
import kotlin.jvm.JvmField

@OptIn(kotlin.contracts.ExperimentalContracts::class)
public actual class MyIntArray internal actual constructor(@JvmField internal val filename: String, initialize: Boolean) {
    @JvmField
    internal var bufferManager: IBufferManager? = null

    @JvmField
    internal var bufferManagerPage: Int? = null

    public actual constructor(bufferManager: IBufferManager, id: Int, initialize: Boolean, instance: Luposdate3000Instance) : this(instance.BUFFER_HOME + id + BufferManagerExt.fileEndingIntArray, initialize) {
        this.bufferManager = bufferManager
        this.bufferManagerPage = id
    }

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

    init {
        if (initialize) {
            _size = datafile.readInt()
        } else {
            datafile.writeInt(0)
        }
    }

    public actual operator fun get(idx: Int): Int {
if(SanityCheck.enabled){if(!( !closed )){throw Exception("SanityCheck failed")}}
if(SanityCheck.enabled){if(!( idx >= 0 )){throw Exception("SanityCheck failed")}}
if(SanityCheck.enabled){if(!( idx < _size )){throw Exception("SanityCheck failed")}}
        var res = 0
        lock.withWriteLock {
            datafile.seek(idx * 4L + 4L)
            res = datafile.readInt()
        }
        return res
    }

    public actual operator fun set(idx: Int, value: Int) {
if(SanityCheck.enabled){if(!( !closed )){throw Exception("SanityCheck failed")}}
if(SanityCheck.enabled){if(!( idx >= 0 )){throw Exception("SanityCheck failed")}}
if(SanityCheck.enabled){if(!( idx < _size )){throw Exception("SanityCheck failed")}}
        lock.withWriteLock {
            datafile.seek(idx * 4L + 4L)
            datafile.writeInt(value)
        }
    }

    public actual fun setSize(size: Int, clean: Boolean) {
if(SanityCheck.enabled){if(!( !closed )){throw Exception("SanityCheck failed")}}
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
if(SanityCheck.enabled){if(!( !closed )){throw Exception("SanityCheck failed")}}
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
if(SanityCheck.enabled){if(!( !closed )){throw Exception("SanityCheck failed")}}
        closed = true
        datafile.close()
    }

    public actual fun delete() {
if(SanityCheck.enabled){if(!( !closed )){throw Exception("SanityCheck failed")}}
        close()
        if (bufferManagerPage != null) {
            bufferManager?.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_buffer_manager_persistent_cached/src/jvmMain/kotlin/lupos/buffer_manager/MyIntArray.kt:122"/*SOURCE_FILE_END*/, bufferManagerPage!!)
            bufferManager?.deletePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_buffer_manager_persistent_cached/src/jvmMain/kotlin/lupos/buffer_manager/MyIntArray.kt:123"/*SOURCE_FILE_END*/, bufferManagerPage!!)
            bufferManager = null
            bufferManagerPage = null
        }
        File(filename).deleteRecursively()
    }
}
