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
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.fileformat.DictionaryIntermediateReader
import kotlin.jvm.JvmField

public abstract class ADictionary(
    @JvmField
    public val instance: Luposdate3000Instance,
    @JvmField
    internal var isLocal: Boolean,
) : IDictionary {
    @JvmField
    internal val bnodeMapToGlobal = mutableMapOf<DictionaryValueType, DictionaryValueType>()

    @JvmField
    internal val bnodeMapLocal = mutableMapOf<String, DictionaryValueType>()

    public override fun createNewBNode(s: String): DictionaryValueType {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/ADictionary.kt:42"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) })
        var res = bnodeMapLocal[s]
        if (res != null) {
            return res
        }
        res = createNewBNode()
        bnodeMapLocal[s] = res
        return res
    }

    override fun isBnode(value: DictionaryValueType): Boolean {
        return (value and DictionaryValueHelper.flagNoBNode) != DictionaryValueHelper.flagNoBNode
    }

    public override fun isLocalValue(value: DictionaryValueType): Boolean {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/ADictionary.kt:57"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) }, { "$this $isLocal" })
        return (value and DictionaryValueHelper.flagLocal) == DictionaryValueHelper.flagLocal
    }

    override fun valueToGlobal(value: DictionaryValueType): DictionaryValueType {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/ADictionary.kt:62"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) })
        val res: DictionaryValueType
        if ((value and DictionaryValueHelper.flagLocal) != DictionaryValueHelper.flagLocal) {
            res = value
        } else {
            if ((value and DictionaryValueHelper.flagNoBNode) == DictionaryValueHelper.flagNoBNode) {
                val buffer = ByteArrayWrapper()
                getValue(buffer, value)
                res = instance.nodeGlobalDictionary!!.createValue(buffer)
            } else {
                val tmp = bnodeMapToGlobal[value]
                if (tmp == null) {
                    res = instance.nodeGlobalDictionary!!.createNewBNode()
                    bnodeMapToGlobal[value] = res
                } else {
                    res = tmp
                }
            }
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    public override fun importFromDictionaryFile(filename: String): Pair<DictionaryValueTypeArray, Int> {
        SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/ADictionary.kt:86"/*SOURCE_FILE_END*/ }, { isLocal != (instance.nodeGlobalDictionary == this) })
        var mymapping = DictionaryValueTypeArray(0)
        var lastId: DictionaryValueType = -1
        fun addEntry(id: DictionaryValueType, i: DictionaryValueType) {
            SanityCheck.check({ /*SOURCE_FILE_START*/"D:/ideaprojects/luposdate3000/src/luposdate3000_dictionary/src/commonMain/kotlin/lupos/dictionary/ADictionary.kt:90"/*SOURCE_FILE_END*/ }, { lastId == id - 1 })
            if (lastId != id - 1) {
                throw Exception("ERROR !! $lastId -> $id")
            }
            lastId = id
            if (lastId % 10000 == DictionaryValueHelper.NULL && lastId != DictionaryValueHelper.NULL) {
                println("imported $lastId dictionaryItems")
            }
            if (mymapping.size <= id) {
                var newSize = 1
                while (newSize <= id) {
                    newSize *= 2
                }
                val tmp = mymapping
                mymapping = DictionaryValueTypeArray(newSize)
                tmp.copyInto(mymapping)
            }
            mymapping[DictionaryValueHelper.toInt(id)] = i
        }
        val buffer = ByteArrayWrapper()
        DictionaryIntermediateReader(filename).readAll(buffer) { id ->
            if (lastId % 10000 == DictionaryValueHelper.NULL && lastId != DictionaryValueHelper.NULL) {
                println("imported $lastId dictionaryItems")
            }
            val type = DictionaryHelper.byteArrayToType(buffer)
            val i = if (type == ETripleComponentTypeExt.BLANK_NODE) {
                createNewBNode()
            } else {
                var res = createValue(buffer)
                if (isLocal) {
                    res = res or DictionaryValueHelper.flagLocal
                }
                res
            }
            addEntry(id, i)
        }
        println("imported $lastId dictionaryItems")
        return Pair(mymapping, DictionaryValueHelper.toInt(lastId + 1))
    }
}
