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
    public var isLocal: Boolean,
) : IDictionary {
    @JvmField
    internal val bnodeMapToGlobal = mutableMapOf<DictionaryValueType, DictionaryValueType>()

    @JvmField
    internal val bnodeMapLocal = mutableMapOf<String, DictionaryValueType>()

    override fun createNewBNode(s: String): DictionaryValueType {
        if (SanityCheck.enabled) { if (!(isLocal != (instance.nodeGlobalDictionary == this))) { throw Exception("SanityCheck failed") } }
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

    override fun isLocalValue(value: DictionaryValueType): Boolean {
        if (SanityCheck.enabled) { if (!(isLocal != (instance.nodeGlobalDictionary == this))) { throw Exception("SanityCheck failed") } }
        return (value and DictionaryValueHelper.flagLocal) == DictionaryValueHelper.flagLocal
    }

    override fun valueToGlobal(value: DictionaryValueType): DictionaryValueType {
        if (SanityCheck.enabled) { if (!(isLocal != (instance.nodeGlobalDictionary == this))) { throw Exception("SanityCheck failed") } }
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

    internal companion object {
        internal fun addEntry(id: DictionaryValueType, i: DictionaryValueType, mymapping2: DictionaryValueTypeArray): DictionaryValueTypeArray {
            var mymapping = mymapping2
            if (id % 10000 == DictionaryValueHelper.NULL && id != DictionaryValueHelper.NULL) {
                println("imported $id dictionaryItems")
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
            return mymapping
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    override fun importFromDictionaryFile(filename: String): Pair<DictionaryValueTypeArray, Int> {
        var mymapping = DictionaryValueTypeArray(0)
        var lastid = DictionaryValueHelper.NULL
        val buffer = ByteArrayWrapper()
        DictionaryIntermediateReader(filename).readAll(buffer) { id ->
            if (id > lastid) {
                lastid = id
            }
            if (id % 10000 == DictionaryValueHelper.NULL && id != DictionaryValueHelper.NULL) {
                println("imported $id dictionaryItems")
            }
            val type = DictionaryHelper.byteArrayToType(buffer)
            val i = when (type) {
                ETripleComponentTypeExt.BOOLEAN -> {
                    val b = DictionaryHelper.byteArrayToBoolean(buffer)
                    if (b) {
                        DictionaryValueHelper.booleanTrueValue
                    } else {
                        DictionaryValueHelper.booleanFalseValue
                    }
                }
                ETripleComponentTypeExt.BLANK_NODE -> {
                    createNewBNode()
                }
                else -> {
                    var res = createValue(buffer)
                    if (isLocal) {
                        res = res or DictionaryValueHelper.flagLocal
                    }
                    res
                }
            }
            mymapping = addEntry(id, i, mymapping)
        }
        println("imported $lastid dictionaryItems")
        return Pair(mymapping, DictionaryValueHelper.toInt(lastid + 1))
    }
}
