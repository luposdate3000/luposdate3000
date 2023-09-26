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
import lupos.shared.BufferManagerPage
import lupos.shared.BufferManagerPageWrapper
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IBufferManager
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.File
import lupos.shared.inline.MyThreadReadWriteLock
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
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
    internal val lock = MyThreadReadWriteLock()

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
    internal val rootPage: BufferManagerPageWrapper = bufferManager.getPage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:63"/*SOURCE_FILE_END*/, rootPageID)

    @JvmField
    internal var closed = false
    override fun close() {
        lock.withWriteLock {
            if (SanityCheck.enabled) { if (!(!closed)) { throw Exception("SanityCheck failed") } }
            closed = true
            kv.close()
            vk.close()
            bufferManager.releasePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:73"/*SOURCE_FILE_END*/, rootPageID)
        }
    }

    override fun delete() {
        lock.withWriteLock {
            kv.delete()
            vk.delete()
            bufferManager.deletePage(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryKV.kt:81"/*SOURCE_FILE_END*/, rootPageID)
            File(instance.BUFFER_HOME + "dict.page").deleteRecursively()
        }
    }

    override fun isInmemoryOnly(): Boolean = false
    override fun forEachValue(buffer: ByteArrayWrapper, action: (DictionaryValueType) -> Unit) {
        lock.withReadLock {
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
                ),
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

    override fun createNewBNode(): DictionaryValueType {
        var res: DictionaryValueType = bNodeCounter
        lock.withWriteLock {
            res = bNodeCounter
            if (instance.allowDistributedBNodeAssignment) {
                bNodeCounter += stepSizeForCounters
            } else {
                bNodeCounter++
            }
            DictionaryValueHelper.toByteArray(rootPage, offsetBNodeCounter, bNodeCounter)
        }
        return res
    }

    override fun createNewUUID(): Int {
        var res: Int = uuidCounter
        lock.withWriteLock {
            res = uuidCounter
            if (instance.allowDistributedBNodeAssignment) {
                uuidCounter += stepSizeForCounters
            } else {
                uuidCounter++
            }
            BufferManagerPage.writeInt4(rootPage, offsetuuidCounter, uuidCounter)
        }
        return res
    }

    override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType) {
        lock.withReadLock {
            if (SanityCheck.enabled) { if (!((value and DictionaryValueHelper.maskValue) >= 0)) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!((value and DictionaryValueHelper.flagNoBNode) == DictionaryValueHelper.flagNoBNode)) { throw Exception("SanityCheck failed") } }
            kv.getValue(buffer, DictionaryValueHelper.toInt(value and DictionaryValueHelper.maskValue))
            if (SanityCheck.enabled) { if (!(ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize())) { throw Exception("SanityCheck failed") } }
        }
    }

    override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType {
        var res = 0
        lock.withWriteLock {
            if (SanityCheck.enabled) { if (!(ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize())) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(DictionaryHelper.byteArrayToType(buffer) !in listOf(ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.UNDEF, ETripleComponentTypeExt.BLANK_NODE))) { throw Exception("SanityCheck failed") } }
            res = vk.createValue(
                buffer,
                value = {
                    kv.createValue(buffer)
                },
            )
        }
        val result = DictionaryValueHelper.fromInt(res) or DictionaryValueHelper.flagNoBNode
        return result
    }

    override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType {
        val type = DictionaryHelper.byteArrayToType(buffer)
        var r = DictionaryValueHelper.nullValue
        lock.withReadLock {
            if (SanityCheck.enabled) { if (!(type != ETripleComponentTypeExt.BLANK_NODE)) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(type != ETripleComponentTypeExt.BOOLEAN)) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(type != ETripleComponentTypeExt.ERROR)) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(type != ETripleComponentTypeExt.UNDEF)) { throw Exception("SanityCheck failed") } }
            val res = vk.hasValue(buffer)
            if (res != ValueKeyStore.ID_NULL) {
                r = DictionaryValueHelper.fromInt(res) or DictionaryValueHelper.flagNoBNode
            }
        }
        return r
    }
}
