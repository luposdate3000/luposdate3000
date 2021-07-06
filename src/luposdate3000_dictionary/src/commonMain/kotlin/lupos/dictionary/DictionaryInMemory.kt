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

public class DictionaryInMemory internal constructor(isLocal: Boolean, instance: Luposdate3000Instance) : ADictionary(instance, isLocal) {
    @JvmField
    internal val uuid = UUID_Counter.getNextUUID()

    @JvmField
    internal var dataI2V = Array(1) { ByteArrayWrapper() }

    @JvmField
    internal var dataV2I = mutableMapOf<ByteArrayWrapper, DictionaryValueType>()

    @JvmField
    internal var bNodeCounter: DictionaryValueType = DictionaryValueHelper.FIRST_BNODE

    @JvmField
    internal var uuidCounter: Int = 0

    public override fun isInmemoryOnly(): Boolean = true
    public override fun close() {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:46"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) })
    }

    public override fun delete() {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:50"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) })
        close()
    }

    public override fun createNewBNode(): DictionaryValueType {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:55"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) })
        var res: DictionaryValueType = bNodeCounter++
        if (isLocal) {
            res = res or DictionaryValueHelper.flagLocal
        }
        return res
    }

    public override fun createNewUUID(): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:64"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) })
        var res: Int = uuidCounter++
        return res
    }
    public override fun forEachValue(buffer: ByteArrayWrapper, action: (DictionaryValueType) -> Unit) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:69"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) })
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
        for (i in DictionaryValueHelper.FIRST_BNODE until bNodeCounter) {
            DictionaryHelper.bnodeToByteArray(buffer, i)
            action(i or flag2)
        }
        for ((k, v) in dataV2I) {
            ByteArrayWrapperExt.copyInto(k, buffer)
            action(v or flag)
        }
    }
    public override fun getValue(buffer: ByteArrayWrapper, value: DictionaryValueType) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:94"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) })
        when (value) {
            DictionaryValueHelper.booleanTrueValue -> DictionaryHelper.booleanToByteArray(buffer, true)
            DictionaryValueHelper.booleanFalseValue -> DictionaryHelper.booleanToByteArray(buffer, false)
            DictionaryValueHelper.errorValue -> DictionaryHelper.errorToByteArray(buffer)
            DictionaryValueHelper.undefValue -> DictionaryHelper.undefToByteArray(buffer)
            DictionaryValueHelper.nullValue -> throw Exception("invalid call")
            else -> {
                if (isLocal == ((value and DictionaryValueHelper.flagLocal) == DictionaryValueHelper.flagLocal)) {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:103"/*SOURCE_FILE_END*/ }, { (value and DictionaryValueHelper.maskValue) >= 0 }, { " $value >= 0" })
                    if ((value and DictionaryValueHelper.flagNoBNode) == DictionaryValueHelper.flagNoBNode) {
                        val buf = dataI2V[DictionaryValueHelper.toInt(value and DictionaryValueHelper.maskValue)]
                        ByteArrayWrapperExt.copyInto(buf, buffer)
                        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:107"/*SOURCE_FILE_END*/ }, { ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize() }, { "xxx" + value })
                        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:108"/*SOURCE_FILE_END*/ }, { (value and DictionaryValueHelper.maskValue) < dataV2I.size }, { "$value < ${dataV2I.size}" })
                    } else {
                        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:110"/*SOURCE_FILE_END*/ }, { (value and DictionaryValueHelper.maskValue) < bNodeCounter }, { "$value < $bNodeCounter" })
                        DictionaryHelper.bnodeToByteArray(buffer, value and DictionaryValueHelper.maskValue)
                    }
                } else {
                    instance.nodeGlobalDictionary!!.getValue(buffer, value)
                }
            }
        }
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:118"/*SOURCE_FILE_END*/ }, { ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize() }, { "" + value })
    }

    public override fun createValue(buffer: ByteArrayWrapper): DictionaryValueType {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:122"/*SOURCE_FILE_END*/ }, { ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize() })
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:123"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) })
        when (DictionaryHelper.byteArrayToType(buffer)) {
            ETripleComponentTypeExt.BLANK_NODE -> {
                val tmp = if (DictionaryHelper.headerDecodeFlag(buffer) == 0x80) {
                    DictionaryHelper.byteArrayToBnode_I(buffer)
                } else {
                    createNewBNode(DictionaryHelper.byteArrayToBnode_S(buffer))
                }
                return tmp
            }
            ETripleComponentTypeExt.BOOLEAN -> {
                val tmp = if (DictionaryHelper.byteArrayToBoolean(buffer)) {
                    DictionaryValueHelper.booleanTrueValue
                } else {
                    DictionaryValueHelper.booleanFalseValue
                }
                return tmp
            }
            ETripleComponentTypeExt.ERROR -> {
                return DictionaryValueHelper.errorValue
            }
            ETripleComponentTypeExt.UNDEF -> {
                return DictionaryValueHelper.undefValue
            }
            else -> {
                if (isLocal) {
                    val tmp = instance.nodeGlobalDictionary!!.hasValue(buffer)
                    if (tmp != null) {
                        return tmp
                    }
                }
                var res = dataV2I[buffer]
                if (res == null) {
                    res = DictionaryValueHelper.fromInt(dataV2I.size)
                    val bufferCopy = ByteArrayWrapper()
                    ByteArrayWrapperExt.copyInto(buffer, bufferCopy)
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
                return res or DictionaryValueHelper.flagNoBNode
            }
        }
    }

    public override fun hasValue(buffer: ByteArrayWrapper): DictionaryValueType? {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:176"/*SOURCE_FILE_END*/ }, { ByteArrayWrapperExt.getSize(buffer) >= DictionaryHelper.headerSize() })
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:177"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) })
        val type = DictionaryHelper.byteArrayToType(buffer)
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:179"/*SOURCE_FILE_END*/ }, { !isLocal })
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:180"/*SOURCE_FILE_END*/ }, { type != ETripleComponentTypeExt.BLANK_NODE })
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:181"/*SOURCE_FILE_END*/ }, { type != ETripleComponentTypeExt.BOOLEAN })
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:182"/*SOURCE_FILE_END*/ }, { type != ETripleComponentTypeExt.ERROR })
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/DictionaryInMemory.kt:183"/*SOURCE_FILE_END*/ }, { type != ETripleComponentTypeExt.UNDEF })
        val res = dataV2I[buffer]
        if (res == null) {
            return null
        }
        return res or DictionaryValueHelper.flagNoBNode
    }
}
