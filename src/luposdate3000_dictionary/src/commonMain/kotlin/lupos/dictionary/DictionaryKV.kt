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
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IBufferManager
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.BufferManagerPage
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.File
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.inline.fileformat.DictionaryIntermediateReader
import lupos.vk.ValueKeyStore
import kotlin.jvm.JvmField

public class DictionaryKV internal constructor(
    @JvmField
    internal val bufferManager: IBufferManager,
    @JvmField
    internal val rootPageID: Int,
    initFromRootPage: Boolean,
    instance: Luposdate3000Instance,
    unusedVar: Int,
) : ADictionary(instance, false) {

    @JvmField
    internal val kv: KeyValueStore

    @JvmField
    internal val vk: ValueKeyStore

    @JvmField
    internal var bNodeCounter = DictionaryValueHelper.FIRST_BNODE

    @JvmField
    internal var stepSizeForCounters: Int = instance.LUPOS_PROCESS_URLS_ALL.size

    @JvmField
    internal var uuidCounter: Int = 0

    @JvmField
    internal val rootPage: ByteArray = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:61"/*SOURCE_FILE_END*/, rootPageID)
    public override fun close() {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:64"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) },
            { "$isLocal ${instance.nodeGlobalDictionary} $this" }
        )
        kv.close()
        vk.close()
        bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:70"/*SOURCE_FILE_END*/, rootPageID)
    }

    public override fun delete() {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:75"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        kv.delete()
        vk.delete()
        bufferManager.deletePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:80"/*SOURCE_FILE_END*/, rootPageID)
        File(instance.BUFFER_HOME + "dict.page").deleteRecursively()
    }

    public override fun isInmemoryOnly(): Boolean = false
    public override fun forEachValue(buffer: ByteArrayWrapper, action: (DictionaryValueType) -> Unit) {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:87"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        var flag: DictionaryValueType = DictionaryValueHelper.flagNoBNode
        var flag2: DictionaryValueType = 0
        if (isLocal) {
            flag = flag or DictionaryValueHelper.flagLocal
            flag2 = flag2 or DictionaryValueHelper.flagLocal
        }
        DictionaryHelper.booleanToByteArray(buffer, true)
        action(DictionaryValueHelper.booleanTrueValue)
        DictionaryHelper.booleanToByteArray(buffer, false)
        action(DictionaryValueHelper.booleanFalseValue)
        DictionaryHelper.errorToByteArray(buffer)
        action(DictionaryValueHelper.errorValue)
        DictionaryHelper.undefToByteArray(buffer)
        action(DictionaryValueHelper.undefValue)
        if (!instance.allowDistributedBNodeAssignment) {
            for (i in DictionaryValueHelper.FIRST_BNODE until bNodeCounter) {
                DictionaryHelper.bnodeToByteArray(buffer, i)
                action(i or flag2)
            }
        }
        val iter = vk.getIterator(buffer)
        while (iter.hasNext()) {
            val id = DictionaryValueHelper.fromInt(iter.next())
            action(id or flag)
        }
        iter.close()
    }

    internal companion object {
        internal const val offsetBNodeCounter = 0
        internal val offsetkvPage = offsetBNodeCounter + DictionaryValueHelper.getSize()
        internal val offsetvkPage = offsetkvPage + 4
        internal val offsetuuidCounter = offsetvkPage + 4
        internal operator fun invoke(
            bufferManager: IBufferManager,
            rootPageID: Int,
            initFromRootPage: Boolean,
            instance: Luposdate3000Instance,
        ): DictionaryCacheLayer {
            return DictionaryCacheLayer(
                instance,
                DictionaryKV(
                    bufferManager,
                    rootPageID,
                    initFromRootPage,
                    instance,
                    0,
                )
            )
        }
    }

    init {
        val kvPage: Int
        val vkPage: Int
        if (initFromRootPage) {
            bNodeCounter = DictionaryValueHelper.fromByteArray(rootPage, offsetBNodeCounter)
            kvPage = BufferManagerPage.readInt4(rootPage, offsetkvPage)
            vkPage = BufferManagerPage.readInt4(rootPage, offsetvkPage)
            uuidCounter = BufferManagerPage.readInt4(rootPage, offsetuuidCounter)
        } else {
            kvPage = bufferManager.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:151"/*SOURCE_FILE_END*/)
            vkPage = bufferManager.allocPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:152"/*SOURCE_FILE_END*/)
            if (instance.allowDistributedBNodeAssignment) {
                bNodeCounter = DictionaryValueHelper.fromInt(instance.LUPOS_PROCESS_ID)
                uuidCounter = instance.LUPOS_PROCESS_ID
                while (bNodeCounter < DictionaryValueHelper.FIRST_BNODE) {
                    bNodeCounter += stepSizeForCounters
                }
            }
            DictionaryValueHelper.toByteArray(rootPage, offsetBNodeCounter, bNodeCounter)
            BufferManagerPage.writeInt4(rootPage, offsetkvPage, kvPage)
            BufferManagerPage.writeInt4(rootPage, offsetvkPage, vkPage)
            BufferManagerPage.writeInt4(rootPage, offsetuuidCounter, uuidCounter)
        }
        kv = KeyValueStore(bufferManager, kvPage, initFromRootPage, instance)
        vk = ValueKeyStore(bufferManager, vkPage, initFromRootPage)
    }

    public override fun createNewBNode(): DictionaryValueType {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:171"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        val res: DictionaryValueType = bNodeCounter
        if (instance.allowDistributedBNodeAssignment) {
            bNodeCounter += stepSizeForCounters
        } else {
            bNodeCounter++
        }
        DictionaryValueHelper.toByteArray(rootPage, offsetBNodeCounter, bNodeCounter)
        return res
    }

    public override fun createNewUUID(): Int {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:186"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        val res: Int = uuidCounter
        if (instance.allowDistributedBNodeAssignment) {
            uuidCounter += stepSizeForCounters
        } else {
            uuidCounter++
        }

        BufferManagerPage.writeInt4(rootPage, offsetuuidCounter, uuidCounter)
        return res
    }

    public override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType) {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:202"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        if ((value and DictionaryValueHelper.flagNoBNode) == DictionaryValueHelper.flagNoBNode) {
            kv.getValue(buffer, DictionaryValueHelper.toInt(value and DictionaryValueHelper.maskValue))
        } else {
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:209"/*SOURCE_FILE_END*/ },
                { value < bNodeCounter }
            )
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:213"/*SOURCE_FILE_END*/ },
                { value >= 0 }
            )
            DictionaryHelper.bnodeToByteArray(buffer, value and DictionaryValueHelper.maskValue)
        }
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:219"/*SOURCE_FILE_END*/ },
            { ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize() },
            { "" + value }
        )
    }

    public override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:227"/*SOURCE_FILE_END*/ },
            { ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize() }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:231"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        when (DictionaryHelper.byteArrayToType(buffer)) {
            ETripleComponentTypeExt.BLANK_NODE -> {
                return if (ByteArrayWrapperExt.getSize(buffer) == 8) {
                    DictionaryHelper.byteArrayToBnode_I(buffer)
                } else {
                    createNewBNode(DictionaryHelper.byteArrayToBnode_S(buffer))
                }
            }
            ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.UNDEF -> {
                TODO("this should have been catched by the cache layer")
            }
            else -> {
                val res = vk.createValue(
                    buffer,
                    value = {
                        kv.createValue(buffer)
                    }
                )
                val r = DictionaryValueHelper.fromInt(res) or DictionaryValueHelper.flagNoBNode
                return r
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public override fun importFromDictionaryFile(filename: String): Pair<DictionaryValueTypeArray, Int> {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:261"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        var mymapping = DictionaryValueTypeArray(0)
        val lastId: DictionaryValueType = -1

        val buffer = ByteArrayWrapper()
        val reader = DictionaryIntermediateReader(filename)
        var ready = false
        var originalID: DictionaryValueType = 0
        vk.createValues(
            hasNext = {
                loop@ while (!ready && reader.hasNext()) {
                    reader.next(buffer) { id ->
                        originalID = id
                        ready = true
                        val type = DictionaryHelper.byteArrayToType(buffer)
                        if (type == ETripleComponentTypeExt.BLANK_NODE) {
                            val id = createNewBNode()
                            mymapping = addEntry(originalID, id, mymapping)
                            ready = false
                        }
                    }
                    if (ready && instance.useDictionaryInlineEncoding) {
                        val res = DictionaryInlineValues.getValueByContent(buffer)
                        if (res != DictionaryValueHelper.nullValue) {
                            mymapping = addEntry(originalID, res, mymapping)
                            ready = false
                        }
                    }
                }
                ready
            },
            next = {
                SanityCheck.check(
                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:296"/*SOURCE_FILE_END*/ },
                    { ready }
                )
                ready = false
                buffer
            },
            onNotFound = {
                val id = kv.createValue(it)
                mymapping = addEntry(originalID, DictionaryValueHelper.fromInt(id) or DictionaryValueHelper.flagNoBNode, mymapping)
                id
            },
            onFound = { _, id ->
                mymapping = addEntry(originalID, DictionaryValueHelper.fromInt(id) or DictionaryValueHelper.flagNoBNode, mymapping)
            }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:312"/*SOURCE_FILE_END*/ },
            { !ready }
        )
        println("imported $lastId dictionaryItems")
        return Pair(mymapping, DictionaryValueHelper.toInt(lastId + 1))
    }

    public override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:321"/*SOURCE_FILE_END*/ },
            { isLocal != (instance.nodeGlobalDictionary == this) }
        )
        val type = DictionaryHelper.byteArrayToType(buffer)
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:326"/*SOURCE_FILE_END*/ },
            { type != ETripleComponentTypeExt.BLANK_NODE }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:330"/*SOURCE_FILE_END*/ },
            { type != ETripleComponentTypeExt.BOOLEAN }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:334"/*SOURCE_FILE_END*/ },
            { type != ETripleComponentTypeExt.ERROR }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:338"/*SOURCE_FILE_END*/ },
            { type != ETripleComponentTypeExt.UNDEF }
        )
        val res = vk.hasValue(buffer)
        if (res == ValueKeyStore.ID_NULL) {
            return DictionaryValueHelper.nullValue
        }
        return DictionaryValueHelper.fromInt(res) or DictionaryValueHelper.flagNoBNode
    }
}
