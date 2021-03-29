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

import lupos.buffermanager.BufferManager
import lupos.buffermanager.BufferManagerExt
import lupos.buffermanager.BufferManagerPage
import lupos.fileformat.DictionaryIntermediateReader
import lupos.kv.KeyValueStore
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.File
import lupos.s00misc.SanityCheck

public class DictionaryKV : ADictionary {
    private val bufferManager: BufferManager
    private val kv: KeyValueStore
    private var bNodeCounter = 5
    private val rootPageID: Int
    private val rootPage: BufferManagerPage
    public override fun close() {
        kv.close()
        bufferManager.releasePage(lupos.SOURCE_FILE, rootPageID)
    }

    public override fun delete() {
        kv.delete()
        bufferManager.deletePage(lupos.SOURCE_FILE, rootPageID)
        File(BufferManagerExt.bufferPrefix + "dict.page").deleteRecursively()
    }

    public override fun isInmemoryOnly(): Boolean = false

    internal constructor(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) : super() {
        this.bufferManager = bufferManager
        rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        this.rootPageID = rootPageID
        var kvPage = 0
        if (initFromRootPage) {
            bNodeCounter = rootPage.readInt4(0)
            kvPage = rootPage.readInt4(4)
        } else {
            rootPage.writeInt4(0, bNodeCounter)
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                kvPage = pageid
            }
            bufferManager.releasePage(lupos.SOURCE_FILE, kvPage)
            rootPage.writeInt4(4, kvPage)
        }
        kv = KeyValueStore(bufferManager, kvPage, initFromRootPage)
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
                var res = kv.createValue(buffer)
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
        rootPage.writeInt4(0, bNodeCounter)
        return res
    }

    public override fun getValue(buffer: ByteArrayWrapper, value: Int) {
        var noFlag = value and ADictionary.noFlags
        when (noFlag) {
            0 -> DictionaryHelper.booleanToByteArray(buffer, true)
            1 -> DictionaryHelper.booleanToByteArray(buffer, false)
            2 -> DictionaryHelper.errorToByteArray(buffer)
            3 -> DictionaryHelper.undefToByteArray(buffer)
            4 -> throw Exception("invalid call")
            else -> {
                if ((value and ADictionary.flagBNode) == ADictionary.flagBNode) {
                    SanityCheck.check { value < bNodeCounter }
                    SanityCheck.check { value >= 0 }
                    buffer.setSize(8)
                    ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.BLANK_NODE)
                    ByteArrayHelper.writeInt4(buffer.getBuf(), 4, noFlag)
                } else {
                    kv.getValue(buffer, noFlag)
                }
            }
        }
    }

    public override fun createValue(buffer: ByteArrayWrapper): Int {
        val type = DictionaryHelper.byteArrayToType(buffer)
        SanityCheck.check { type != ETripleComponentTypeExt.BLANK_NODE }
        when (type) {
            ETripleComponentTypeExt.BOOLEAN -> {
                if (DictionaryHelper.byteArrayToBoolean(buffer)) {
                    return DictionaryExt.booleanTrueValue
                } else {
                    return DictionaryExt.booleanFalseValue
                }
            }
            ETripleComponentTypeExt.ERROR -> return DictionaryExt.errorValue
            ETripleComponentTypeExt.UNDEF -> return DictionaryExt.undefValue
            else -> {
                var res = kv.createValue(buffer)
                return res
            }
        }
    }

    public override fun hasValue(buffer: ByteArrayWrapper): Int? {
        val type = DictionaryHelper.byteArrayToType(buffer)
        SanityCheck.check { type != ETripleComponentTypeExt.BLANK_NODE }
        when (type) {
            ETripleComponentTypeExt.BOOLEAN -> {
                if (DictionaryHelper.byteArrayToBoolean(buffer)) {
                    return DictionaryExt.booleanTrueValue
                } else {
                    return DictionaryExt.booleanFalseValue
                }
            }
            ETripleComponentTypeExt.ERROR -> return DictionaryExt.errorValue
            ETripleComponentTypeExt.UNDEF -> return DictionaryExt.undefValue
            else -> {
                var res = kv.hasValue(buffer)
                if (res == null) {
                    return null
                }
                return res
            }
        }
    }
}
