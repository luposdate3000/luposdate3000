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
                )
            )
        }
    }

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

    public override fun isInmemoryOnly(): Boolean = true

    init {
        if (instance.allowDistributedBNodeAssignment) {
            bNodeCounter = DictionaryValueHelper.fromInt(instance.LUPOS_PROCESS_ID)
            uuidCounter = instance.LUPOS_PROCESS_ID
            while (bNodeCounter < DictionaryValueHelper.FIRST_BNODE) {
                bNodeCounter += stepSizeForCounters
            }
        }
    }

    public override fun close() {
    }

    public override fun delete() {
        close()
    }

    public override fun createNewBNode(): DictionaryValueType {
        var res: DictionaryValueType = bNodeCounter
        if (instance.allowDistributedBNodeAssignment) {
            bNodeCounter += stepSizeForCounters
        } else {
            bNodeCounter++
        }
        if (isLocal) {
            res = res or DictionaryValueHelper.flagLocal
        }
        return res
    }

    public override fun createNewUUID(): Int {
        val res = uuidCounter
        if (instance.allowDistributedBNodeAssignment) {
            uuidCounter += stepSizeForCounters
        } else {
            uuidCounter++
        }
        return res
    }

    public override fun forEachValue(buffer: ByteArrayWrapper, action: (DictionaryValueType) -> Unit) {
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

    public override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType) {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:139"/*SOURCE_FILE_END*/ },
            { (value and DictionaryValueHelper.maskValue) >= 0 },
            { " $value >= 0" }
        )
        if ((value and DictionaryValueHelper.flagNoBNode) == DictionaryValueHelper.flagNoBNode) {
// TODO here indexOutOfBounds?!?
            val buf = dataI2V[DictionaryValueHelper.toInt(value and DictionaryValueHelper.maskValue)]
            ByteArrayWrapperExt.copyInto(buf, buffer, false)
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:148"/*SOURCE_FILE_END*/ },
                { ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize() },
                { "xxx$value" }
            )
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:153"/*SOURCE_FILE_END*/ },
                { (value and DictionaryValueHelper.maskValue) < dataV2I.size },
                { "$value < ${dataV2I.size}" }
            )
        } else {
            SanityCheck.check(
                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:159"/*SOURCE_FILE_END*/ },
                { (value and DictionaryValueHelper.maskValue) < bNodeCounter },
                { "$value < $bNodeCounter" }
            )
            DictionaryHelper.bnodeToByteArray(buffer, value and DictionaryValueHelper.maskValue)
        }
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:166"/*SOURCE_FILE_END*/ },
            { ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize() },
            { "" + value }
        )
    }

    public override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:174"/*SOURCE_FILE_END*/ },
            { ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize() }
        )
        when (DictionaryHelper.byteArrayToType(buffer)) {
            ETripleComponentTypeExt.BLANK_NODE -> {
                return if (DictionaryHelper.headerDecodeFlag(buffer) == 0x80) {
                    DictionaryHelper.byteArrayToBnode_I(buffer)
                } else {
                    createNewBNode(DictionaryHelper.byteArrayToBnode_S(buffer))
                }
            }
            ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.UNDEF -> {
                TODO("this should have been catched by the cache layer")
            }
            else -> {
                SanityCheck.check(
                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:190"/*SOURCE_FILE_END*/ },
                    { instance.nodeGlobalDictionary!!.hasValue(buffer) == DictionaryValueHelper.nullValue }
                )
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
                val r = res or DictionaryValueHelper.flagNoBNode
                return r
            }
        }
    }

    public override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType {
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:217"/*SOURCE_FILE_END*/ },
            { ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize() }
        )
        val type = DictionaryHelper.byteArrayToType(buffer)
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:222"/*SOURCE_FILE_END*/ },
            { !isLocal }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:226"/*SOURCE_FILE_END*/ },
            { type != ETripleComponentTypeExt.BLANK_NODE }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:230"/*SOURCE_FILE_END*/ },
            { type != ETripleComponentTypeExt.BOOLEAN }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:234"/*SOURCE_FILE_END*/ },
            { type != ETripleComponentTypeExt.ERROR }
        )
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:238"/*SOURCE_FILE_END*/ },
            { type != ETripleComponentTypeExt.UNDEF }
        )
        val res = dataV2I[buffer] ?: return DictionaryValueHelper.nullValue
        return res or DictionaryValueHelper.flagNoBNode
    }
}
