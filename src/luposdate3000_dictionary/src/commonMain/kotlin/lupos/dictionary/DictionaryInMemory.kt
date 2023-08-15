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

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.UUID_Counter
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.MyThreadReadWriteLock
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import kotlin.jvm.JvmField

public class DictionaryInMemory internal constructor(
    isLocal: Boolean,
    instance: Luposdate3000Instance,
    unusedVariable: Int,
) : ADictionary(instance, isLocal) {
    internal companion object {
        internal operator fun invoke(
            isLocal: Boolean,
            instance: Luposdate3000Instance,
        ): DictionaryCacheLayer {
            return DictionaryCacheLayer(
                instance,
                DictionaryInMemory(
                    isLocal,
                    instance,
                    0,
                ),
            )
        }
    }

    @JvmField
    internal val lock = MyThreadReadWriteLock()

    @JvmField
    internal val uuid = UUID_Counter.getNextUUID()

    @JvmField
    internal var dataI2V = Array(1) { ByteArrayWrapper() }

    @JvmField
    internal var dataV2I = mutableMapOf<ByteArrayWrapper, DictionaryValueType>()

    @JvmField
    internal var bNodeCounter: DictionaryValueType = DictionaryValueHelper.FIRST_BNODE

    @JvmField
    internal var stepSizeForCounters: Int = instance.LUPOS_PROCESS_URLS_ALL.size

    @JvmField
    internal var uuidCounter: Int = 0

    override fun isInmemoryOnly(): Boolean = true

    init {
        if (instance.allowDistributedBNodeAssignment) {
            bNodeCounter = DictionaryValueHelper.fromInt(instance.LUPOS_PROCESS_ID)
            uuidCounter = instance.LUPOS_PROCESS_ID
            while (bNodeCounter < DictionaryValueHelper.FIRST_BNODE) {
                bNodeCounter += stepSizeForCounters
            }
        }
    }

    override fun close() {
    }

    override fun delete() {
        close()
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
            if (isLocal) {
                res = res or DictionaryValueHelper.flagLocal
            }
        }
        return res
    }

    override fun createNewUUID(): Int {
        var res = uuidCounter
        lock.withWriteLock {
            res = uuidCounter
            if (instance.allowDistributedBNodeAssignment) {
                uuidCounter += stepSizeForCounters
            } else {
                uuidCounter++
            }
        }
        return res
    }

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
            for ((k, v) in dataV2I) {
                ByteArrayWrapperExt.copyInto(k, buffer, false)
                action(v or flag)
            }
        }
    }

    override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType) {
        lock.withReadLock {
            if (SanityCheck.enabled) { if (!((value and DictionaryValueHelper.maskValue) >= 0)) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(dataV2I.size > DictionaryValueHelper.toInt(value and DictionaryValueHelper.maskValue))) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!((value and DictionaryValueHelper.flagNoBNode) == DictionaryValueHelper.flagNoBNode)) { throw Exception("SanityCheck failed") } }
            val buf = dataI2V[DictionaryValueHelper.toInt(value and DictionaryValueHelper.maskValue)]
            ByteArrayWrapperExt.copyInto(buf, buffer, false)
            if (SanityCheck.enabled) { if (!(ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize())) { throw Exception("SanityCheck failed") } }
        }
    }

    override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType {
        var r = DictionaryValueHelper.nullValue
        lock.withWriteLock {
            if (SanityCheck.enabled) { if (!(ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize())) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(DictionaryHelper.byteArrayToType(buffer) !in listOf(ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.UNDEF, ETripleComponentTypeExt.BLANK_NODE))) { throw Exception("SanityCheck failed") } }
            var res = dataV2I[buffer]
            if (res == null) {
                res = DictionaryValueHelper.fromInt(dataV2I.size)
                val bufferCopy = ByteArrayWrapper()
                ByteArrayWrapperExt.copyInto(buffer, bufferCopy, false)
                dataV2I[bufferCopy] = res
                if (dataI2V.size <= res) {
                    val tmp = dataI2V
                    dataI2V = Array(dataI2V.size * 2) { bufferCopy }
                    tmp.copyInto(dataI2V)
                }
                dataI2V[DictionaryValueHelper.toInt(res)] = bufferCopy
            }
            if (isLocal) {
                res = res or DictionaryValueHelper.flagLocal
            }
            r = res or DictionaryValueHelper.flagNoBNode
        }
        return r
    }

    override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType {
        var r = DictionaryValueHelper.nullValue
        lock.withReadLock {
            if (SanityCheck.enabled) { if (!(ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize())) { throw Exception("SanityCheck failed") } }
            val type = DictionaryHelper.byteArrayToType(buffer)
            if (SanityCheck.enabled) { if (!(type != ETripleComponentTypeExt.BLANK_NODE)) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(type != ETripleComponentTypeExt.BOOLEAN)) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(type != ETripleComponentTypeExt.ERROR)) { throw Exception("SanityCheck failed") } }
            if (SanityCheck.enabled) { if (!(type != ETripleComponentTypeExt.UNDEF)) { throw Exception("SanityCheck failed") } }
            var res = dataV2I[buffer]
            if (res != null) {
                if (isLocal) {
                    res = res or DictionaryValueHelper.flagLocal
                }
                r = res or DictionaryValueHelper.flagNoBNode
            }
        }
        return r
    }
}
