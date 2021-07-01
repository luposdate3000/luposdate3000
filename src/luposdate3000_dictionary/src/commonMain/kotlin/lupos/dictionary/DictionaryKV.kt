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

import lupos.kv.KeyValueStore
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IBufferManager
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.fileformat.DictionaryIntermediateReader
import lupos.shared.inline.BufferManagerPage
import lupos.shared.inline.ByteArrayHelper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.File
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.vk.ValueKeyStore
import kotlin.jvm.JvmField

public class DictionaryKV internal constructor(
    bufferManager: IBufferManager,
    @JvmField
    internal val rootPageID: Int,
    initFromRootPage: Boolean,
    instance: Luposdate3000Instance
) : ADictionary(instance, false) {
    @JvmField
    internal val bufferManager: IBufferManager = bufferManager

    @JvmField
    internal val kv: KeyValueStore

    @JvmField
    internal val vk: ValueKeyStore

    @JvmField
    internal var bNodeCounter = 5

    @JvmField
    internal var uuidCounter = 0

    @JvmField
    internal val rootPage: ByteArray
    public override fun close() {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        kv.close()
        vk.close()
        bufferManager.releasePage("/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:55", rootPageID)
    }

    public override fun delete() {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        kv.delete()
        vk.delete()
        bufferManager.deletePage("/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:61", rootPageID)
        File(instance.BUFFER_HOME + "dict.page").deleteRecursively()
    }

    public override fun isInmemoryOnly(): Boolean = false
    public override fun forEachValue(buffer: ByteArrayWrapper, action: (Int) -> Unit) {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        var flag = flagNoBNode
        var flag2 = 0
        if (isLocal) {
            flag = flag or flagLocal
            flag2 = flag2 or flagLocal
        }
        DictionaryHelper.booleanToByteArray(buffer, true)
        action(DictionaryExt.booleanTrueValue)
        DictionaryHelper.booleanToByteArray(buffer, false)
        action(DictionaryExt.booleanFalseValue)
        DictionaryHelper.errorToByteArray(buffer)
        action(DictionaryExt.errorValue)
        DictionaryHelper.undefToByteArray(buffer)
        action(DictionaryExt.undefValue)
        for (i in 5 until bNodeCounter) {
            DictionaryHelper.bnodeToByteArray(buffer, i)
            action(i or flag2)
        }
        val iter = vk.getIterator(buffer)
        while (iter.hasNext()) {
            val id = iter.next()
            action(id or flag)
        }
        iter.close()
    }
    init {
        rootPage = bufferManager.getPage("/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:70", rootPageID)
        var kvPage: Int
        var vkPage: Int
        if (initFromRootPage) {
            bNodeCounter = BufferManagerPage.readInt4(rootPage, 0)
            kvPage = BufferManagerPage.readInt4(rootPage, 4)
            vkPage = BufferManagerPage.readInt4(rootPage, 8)
            uuidCounter = BufferManagerPage.readInt4(rootPage, 12)
        } else {
            kvPage = bufferManager.allocPage("/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:79")
            vkPage = bufferManager.allocPage("/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:80")
            BufferManagerPage.writeInt4(rootPage, 0, bNodeCounter)
            BufferManagerPage.writeInt4(rootPage, 4, kvPage)
            BufferManagerPage.writeInt4(rootPage, 8, vkPage)
            BufferManagerPage.writeInt4(rootPage, 12, uuidCounter)
        }
        kv = KeyValueStore(bufferManager, kvPage, initFromRootPage, instance)
        vk = ValueKeyStore(bufferManager, vkPage, initFromRootPage)
    }

    public override fun createNewBNode(): Int {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        val res: Int = bNodeCounter++
        BufferManagerPage.writeInt4(rootPage, 0, bNodeCounter)
        return res
    }

    public override fun createNewUUID(): Int {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        val res: Int = uuidCounter++
        BufferManagerPage.writeInt4(rootPage, 12, uuidCounter)
        return res
    }

    public override fun getValue(buffer: ByteArrayWrapper, value: Int) {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        when (value) {
            DictionaryExt.booleanTrueValue -> DictionaryHelper.booleanToByteArray(buffer, true)
            DictionaryExt.booleanFalseValue -> DictionaryHelper.booleanToByteArray(buffer, false)
            DictionaryExt.errorValue -> DictionaryHelper.errorToByteArray(buffer)
            DictionaryExt.undefValue -> DictionaryHelper.undefToByteArray(buffer)
            DictionaryExt.nullValue -> throw Exception("invalid call")
            else -> {
                if ((value and flagNoBNode) == flagNoBNode) {
                    kv.getValue(buffer, value and maskValue)
                } else {
                    SanityCheck.check { value < bNodeCounter }
                    SanityCheck.check { value >= 0 }
                    ByteArrayWrapperExt.setSize(buffer, 8)
                    ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.BLANK_NODE)
                    ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 4, value and maskValue)
                }
            }
        }
        SanityCheck.check({ ByteArrayWrapperExt.getSize(buffer) >= 4 }, { "" + value })
    }

    public override fun createValue(buffer: ByteArrayWrapper): Int {
        SanityCheck.check({ ByteArrayWrapperExt.getSize(buffer) >= 4 })
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        when (DictionaryHelper.byteArrayToType(buffer)) {
            ETripleComponentTypeExt.BLANK_NODE -> {
                val res = if (ByteArrayWrapperExt.getSize(buffer) == 8) {
                    DictionaryHelper.byteArrayToBnode_I(buffer)
                } else {
                    createNewBNode(DictionaryHelper.byteArrayToBnode_S(buffer))
                }
                return res
            }
            ETripleComponentTypeExt.BOOLEAN -> {
                val res = if (DictionaryHelper.byteArrayToBoolean(buffer)) {
                    DictionaryExt.booleanTrueValue
                } else {
                    DictionaryExt.booleanFalseValue
                }
                return res
            }
            ETripleComponentTypeExt.ERROR -> return DictionaryExt.errorValue
            ETripleComponentTypeExt.UNDEF -> return DictionaryExt.undefValue
            else -> {
                val res = vk.createValue(
                    buffer,
                    value = {
                        kv.createValue(buffer)
                    }
                )
                return res or flagNoBNode
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public override fun importFromDictionaryFile(filename: String): Pair<IntArray, Int> {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        var mymapping = IntArray(0)
        var lastId = -1
        fun addEntry(id: Int, i: Int) {
            SanityCheck.check { lastId == id - 1 }
            if (lastId != id - 1) {
                throw Exception("ERROR !! $lastId -> $id")
            }
            lastId = id
            if (lastId % 10000 == 0 && lastId != 0) {
                println("imported $lastId dictionaryItems")
            }
            if (mymapping.size <= id) {
                var newSize = 1
                while (newSize <= id) {
                    newSize *= 2
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
                        val type = DictionaryHelper.byteArrayToType(buffer)
                        if (type == ETripleComponentTypeExt.BLANK_NODE) {
                            val id = createNewBNode()
                            addEntry(originalID, id)
                            ready = false
                        }
                    }
                }
                ready
            },
            next = {
                SanityCheck.check { ready }
                ready = false
                buffer
            },
            onNotFound = {
                val id = kv.createValue(it)
                addEntry(originalID, id or flagNoBNode)
                id
            },
            onFound = { _, id ->
                addEntry(originalID, id or flagNoBNode)
            }
        )
        SanityCheck.check { !ready }
        println("imported $lastId dictionaryItems")
        return Pair(mymapping, lastId + 1)
    }

    public override fun hasValue(buffer: ByteArrayWrapper): Int? {
        SanityCheck.check { isLocal != (instance.nodeGlobalDictionary == this) }
        val type = DictionaryHelper.byteArrayToType(buffer)
        SanityCheck.check { type != ETripleComponentTypeExt.BLANK_NODE }
        SanityCheck.check { type != ETripleComponentTypeExt.BOOLEAN }
        SanityCheck.check { type != ETripleComponentTypeExt.ERROR }
        SanityCheck.check { type != ETripleComponentTypeExt.UNDEF }
        val res = vk.hasValue(buffer)
        if (res == ValueKeyStore.ID_NULL) {
            return null
        }
        return res or flagNoBNode
    }
}
