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

import lupos.fileformat.DictionaryIntermediateReader
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.SanityCheck

public class DictionaryInMemory : ADictionary {
    private var dataI2V = Array<ByteArrayWrapper>(1) { ByteArrayWrapper() }
    private var dataV2I = mutableMapOf<ByteArrayWrapper, Int>()
    private var bNodeCounter = 5
    private val isLocal: Boolean

    internal constructor(isLocal: Boolean) : super() {
        this.isLocal = isLocal
    }

    public override fun isInmemoryOnly(): Boolean = true
    public override fun close() {
    }

    public override fun delete() {
        close()
    }

    @Suppress("NOTHING_TO_INLINE")
    public override fun importFromDictionaryFile(filename: String): IntArray {
        var mymapping = IntArray(0)
        var lastId = -1
        val buffer = ByteArrayWrapper()
        DictionaryIntermediateReader(filename).readAll(buffer) { id ->
            val type = DictionaryHelper.byteArrayToType(buffer)
            val i = if (type == ETripleComponentTypeExt.BLANK_NODE) {
                createNewBNode()
            } else {
                var res = createValue(buffer)
                if (isLocal) {
                    res = res or ADictionary.flagLocal
                }
                res
            }
            SanityCheck.check { lastId == id - 1 }
            lastId = id
            if (mymapping.size <= id) {
                var newSize = 1
                while (newSize <= id) {
                    newSize = newSize * 2
                }
                val tmp = mymapping
                mymapping = IntArray(newSize)
                tmp.copyInto(mymapping)
            }
            mymapping[id] = i
        }
        return mymapping
    }

    public override fun createNewBNode(): Int {
        var res: Int = bNodeCounter++ or ADictionary.flagBNode
        if (isLocal) {
            res = res or ADictionary.flagLocal
        }
        return res
    }

    public override fun getValue(buffer: ByteArrayWrapper, value: Int) {
        if ((value and ADictionary.flagBNode) == ADictionary.flagBNode) {
            buffer.setSize(8)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.BLANK_NODE)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 4, value and ADictionary.noFlags)
        } else {
            val buf = dataI2V[value and ADictionary.noFlags]!!
            buf.copyInto(buffer)
        }
    }

    public override fun createValue(buffer: ByteArrayWrapper): Int {
        SanityCheck.check { DictionaryHelper.byteArrayToType(buffer) != ETripleComponentTypeExt.BLANK_NODE }
        if (isLocal) {
            val tmp = nodeGlobalDictionary.hasValue(buffer)
            if (tmp != null) {
                return tmp
            }
        }
        var res = dataV2I[buffer]
        if (res == null) {
            res = dataV2I.size
            val bufferCopy = ByteArrayWrapper()
            buffer.copyInto(bufferCopy)
            dataV2I[bufferCopy] = res
            if (dataI2V.size <= res) {
                val tmp = dataI2V
                dataI2V = Array<ByteArrayWrapper>(dataI2V.size * 2) { bufferCopy }
                tmp.copyInto(dataI2V)
            }
            dataI2V[res] = bufferCopy
        }
        if (isLocal) {
            res = res or ADictionary.flagLocal
        }
        return res
    }

    public override fun hasValue(buffer: ByteArrayWrapper): Int? {
        SanityCheck.check { DictionaryHelper.byteArrayToType(buffer) != ETripleComponentTypeExt.BLANK_NODE }
        if (isLocal) {
            val tmp = nodeGlobalDictionary.hasValue(buffer)
            if (tmp != null) {
                return tmp
            }
        }
        var res = dataV2I[buffer]
        if (res == null) {
            return null
        }
        if (isLocal) {
            res = res or ADictionary.flagLocal
        }
        return res
    }
}
