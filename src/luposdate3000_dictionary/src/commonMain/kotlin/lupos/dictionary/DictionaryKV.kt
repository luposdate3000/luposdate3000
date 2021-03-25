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

import lupos.ProguardTestAnnotation
import lupos.buffermanager.BufferManager
import lupos.buffermanager.BufferManagerExt
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
    private val rootPage: ByteArray
    private val isLocal: Boolean

    internal constructor(isLocal: Boolean) : super() {
        this.isLocal = isLocal
        bufferManager = BufferManagerExt.getBuffermanager("dictionary")
        val file = File(BufferManagerExt.bufferPrefix + "dict.page")
        var rootPageID = 0
        var initFromRootPage = file.exists()
        if (initFromRootPage) {
            file.withInputStream {
                rootPageID = it.readInt()
            }
            rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        } else {
            var p: ByteArray? = null
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                p = page
                rootPageID = pageid
            }
            this.rootPage = p!!
            file.withOutputStream {
                it.writeInt(rootPageID)
            }
        }
        this.rootPageID = rootPageID
        var kvPage = 0
        if (initFromRootPage) {
            bNodeCounter = ByteArrayHelper.readInt4(rootPage, 0)
            kvPage = ByteArrayHelper.readInt4(rootPage, 4)
        } else {
            ByteArrayHelper.writeInt4(rootPage, 0, bNodeCounter)
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                kvPage = pageid
            }
            bufferManager.releasePage(lupos.SOURCE_FILE, kvPage)
        }
        kv = KeyValueStore(bufferManager, kvPage, initFromRootPage)
    }

    @ProguardTestAnnotation
    internal constructor(isLocal: Boolean, bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) : super() {
        this.isLocal = isLocal
        this.bufferManager = bufferManager
        this.rootPageID = rootPageID
        rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        var kvPage = 0
        if (initFromRootPage) {
            bNodeCounter = ByteArrayHelper.readInt4(rootPage, 0)
            kvPage = ByteArrayHelper.readInt4(rootPage, 4)
        } else {
            ByteArrayHelper.writeInt4(rootPage, 0, bNodeCounter)
            bufferManager.createPage(lupos.SOURCE_FILE) { page, pageid ->
                kvPage = pageid
            }
            bufferManager.releasePage(lupos.SOURCE_FILE, kvPage)
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
        ByteArrayHelper.writeInt4(rootPage, 0, bNodeCounter)
        return res
    }

    public override fun getValue(buffer: ByteArrayWrapper, value: Int) {
        if ((value and ADictionary.flagBNode) == ADictionary.flagBNode) {
            buffer.setSize(8)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.BLANK_NODE)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 4, value and ADictionary.noFlags)
        } else {
            kv.getValue(buffer, value and ADictionary.noFlags)
        }
    }

    public override fun createValue(buffer: ByteArrayWrapper): Int {
        var res = kv.createValue(buffer)
        if (isLocal) {
            res = res or ADictionary.flagLocal
        }
        return res
    }

    public override fun hasValue(buffer: ByteArrayWrapper): Int? {
        if (isLocal) {
            val tmp = nodeGlobalDictionary.hasValue(buffer)
            if (tmp != null) {
                return tmp
            }
        }
        var res = kv.hasValue(buffer)
        if (res == null) {
            return null
        }
        if (isLocal) {
            res = res or ADictionary.flagLocal
        }
        return res
    }
}
