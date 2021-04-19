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
import lupos.fileformat.DictionaryIntermediateReader
import lupos.kv.KeyValueStore
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.SanityCheck
import lupos.shared_inline.BufferManagerPage
import lupos.shared_inline.ByteArrayHelper
import lupos.shared_inline.DictionaryHelper
import lupos.shared_inline.File
import lupos.vk.ValueKeyStore
import kotlin.jvm.JvmField

public class DictionaryKV : ADictionary {
    @JvmField
    internal val bufferManager: BufferManager

    @JvmField
    internal val kv: KeyValueStore

    @JvmField
    internal val vk: ValueKeyStore

    @JvmField
    internal var bNodeCounter = 5

    @JvmField
    internal val rootPageID: Int

    @JvmField
    internal val rootPage: ByteArray
    public override fun close() {
        kv.close()
        vk.close()
        bufferManager.releasePage(lupos.SOURCE_FILE, rootPageID)
    }

    public override fun delete() {
        kv.delete()
        vk.delete()
        bufferManager.deletePage(lupos.SOURCE_FILE, rootPageID)
        File(BufferManagerExt.bufferPrefix + "dict.page").deleteRecursively()
    }

    public override fun isInmemoryOnly(): Boolean = false

    internal constructor(bufferManager: BufferManager, rootPageID: Int, initFromRootPage: Boolean) : super() {
        isLocal = false
        this.bufferManager = bufferManager
        rootPage = bufferManager.getPage(lupos.SOURCE_FILE, rootPageID)
        this.rootPageID = rootPageID
        var kvPage = 0
        var vkPage = 0
        if (initFromRootPage) {
            bNodeCounter = BufferManagerPage.readInt4(rootPage, 0)
            kvPage = BufferManagerPage.readInt4(rootPage, 4)
            vkPage = BufferManagerPage.readInt4(rootPage, 8)
        } else {
            kvPage = bufferManager.allocPage(lupos.SOURCE_FILE)
            vkPage = bufferManager.allocPage(lupos.SOURCE_FILE)
            BufferManagerPage.writeInt4(rootPage, 0, bNodeCounter)
            BufferManagerPage.writeInt4(rootPage, 4, kvPage)
            BufferManagerPage.writeInt4(rootPage, 8, vkPage)
        }
        kv = KeyValueStore(bufferManager, kvPage, initFromRootPage)
        vk = ValueKeyStore(bufferManager, vkPage, initFromRootPage)
    }

    public override fun createNewBNode(): Int {
        var res: Int = bNodeCounter++
        BufferManagerPage.writeInt4(rootPage, 0, bNodeCounter)
        return res
    }

    public override fun getValue(buffer: ByteArrayWrapper, value: Int) {
        when (value) {
            DictionaryExt.booleanTrueValue -> DictionaryHelper.booleanToByteArray(buffer, true)
            DictionaryExt.booleanFalseValue -> DictionaryHelper.booleanToByteArray(buffer, false)
            DictionaryExt.errorValue -> DictionaryHelper.errorToByteArray(buffer)
            DictionaryExt.undefValue -> DictionaryHelper.undefToByteArray(buffer)
            DictionaryExt.nullValue -> throw Exception("invalid call")
            else -> {
                if ((value and ADictionary.flagNoBNode) == ADictionary.flagNoBNode) {
                    kv.getValue(buffer, value and ADictionary.maskValue)
                } else {
                    SanityCheck.check { value < bNodeCounter }
                    SanityCheck.check { value >= 0 }
                    buffer.setSize(8)
                    ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.BLANK_NODE)
                    ByteArrayHelper.writeInt4(buffer.getBuf(), 4, value and ADictionary.maskValue)
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
                var res = vk.createValue(
                    buffer,
                    value = {
                        kv.createValue(buffer)
                    }
                )
                return res or ADictionary.flagNoBNode
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public override fun importFromDictionaryFile(filename: String): Pair<IntArray, Int> {
        var mymapping = IntArray(0)
        var lastId = -1
        fun addEntry(id: Int, i: Int) {
            SanityCheck.check { lastId == id - 1 }
            if (lastId != id - 1) {
                throw Exception("ERROR !! $lastId -> $id")
            }
            lastId = id
            if (lastId % 10000 == 0) {
                println("imported $lastId dictionaryItems")
            }
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

        val buffer = ByteArrayWrapper()
        val reader = DictionaryIntermediateReader(filename)
        var ready = false
        var originalID = 0
        vk.createValues(
            hasNext = {
                loop@ while (!ready && reader.hasNext()) {
                    reader.next(buffer) { id ->
                        originalID = id
                        ready = true
                    }
                    val type = DictionaryHelper.byteArrayToType(buffer)
                    if (type == ETripleComponentTypeExt.BLANK_NODE) {
                        val id = createNewBNode()
                        addEntry(originalID, id)
                        ready = false
                    }
                }
                ready
            },
            next = {
                SanityCheck.check { ready }
                ready = false
                buffer
            },
            value = { it ->
                val id = kv.createValue(it)
                addEntry(originalID, id or ADictionary.flagNoBNode)
                id
            }
        )
        println("imported dictionary with $lastId items")
        return Pair(mymapping, lastId + 1)
    }

    public override fun hasValue(buffer: ByteArrayWrapper): Int? {
        val type = DictionaryHelper.byteArrayToType(buffer)
        SanityCheck.check { type != ETripleComponentTypeExt.BLANK_NODE }
        SanityCheck.check { type != ETripleComponentTypeExt.BOOLEAN }
        SanityCheck.check { type != ETripleComponentTypeExt.ERROR }
        SanityCheck.check { type != ETripleComponentTypeExt.UNDEF }
        var res = vk.hasValue(buffer)
        if (res == ValueKeyStore.ID_NULL) {
            return null
        }
        return res or ADictionary.flagNoBNode
    }
}
