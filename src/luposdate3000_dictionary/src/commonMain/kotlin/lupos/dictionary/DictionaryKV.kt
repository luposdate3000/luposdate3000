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
import lupos.kv.KeyValueStore
import lupos.vk.ValueKeyStore
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.File
import lupos.s00misc.SanityCheck

public class DictionaryKV : ADictionary {
    private val bufferManager: BufferManager
    private val kv: KeyValueStore
    private val vk: ValueKeyStore
    private var bNodeCounter = 5
    private val rootPageID: Int
    private val rootPage: BufferManagerPage
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
            bNodeCounter = rootPage.readInt4(0)
            kvPage = rootPage.readInt4(4)
            vkPage = rootPage.readInt4(8)
        } else {
kvPage=bufferManager.allocPage(lupos.SOURCE_FILE)
vkPage=bufferManager.allocPage(lupos.SOURCE_FILE)
            rootPage.writeInt4(0, bNodeCounter)
            rootPage.writeInt4(4, kvPage)
            rootPage.writeInt4(8, vkPage)
        }
        kv = KeyValueStore(bufferManager, kvPage, initFromRootPage)
        vk = ValueKeyStore(bufferManager, vkPage, initFromRootPage)
    }

    public override fun createNewBNode(): Int {
        var res: Int = bNodeCounter++
        rootPage.writeInt4(0, bNodeCounter)
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
var res=vk.createValue(
buffer,
value={
kv.createValue(buffer)
}
)
                return res or ADictionary.flagNoBNode
            }
        }
    }

    public override fun hasValue(buffer: ByteArrayWrapper): Int? {
        val type = DictionaryHelper.byteArrayToType(buffer)
        SanityCheck.check { type != ETripleComponentTypeExt.BLANK_NODE }
        SanityCheck.check { type != ETripleComponentTypeExt.BOOLEAN }
        SanityCheck.check { type != ETripleComponentTypeExt.ERROR }
        SanityCheck.check { type != ETripleComponentTypeExt.UNDEF }
        var res = vk.hasValue(buffer)
        if (res == ValueKeyStore.ID_NULL ) {
            return null
        }
        return res or ADictionary.flagNoBNode
    }
}
