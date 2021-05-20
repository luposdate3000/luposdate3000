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

import lupos.shared.ETripleComponentTypeExt
import lupos.shared.SanityCheck
import lupos.shared.dictionary.IDictionary
import lupos.shared.dictionary.nodeGlobalDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.fileformat.DictionaryIntermediateReader
import lupos.shared_inline.DictionaryHelper
import kotlin.jvm.JvmField

public abstract class ADictionary : IDictionary {
    @JvmField
    internal val bnodeMapToGlobal = mutableMapOf<Int, Int>()

    @JvmField
    internal val bnodeMapLocal = mutableMapOf<String, Int>()
    internal var isLocal: Boolean = false
    public override fun createNewBNode(s: String): Int {
        var res = bnodeMapLocal[s]
        if (res != null) {
            return res
        }
        res = createNewBNode()
        bnodeMapLocal[s] = res
        return res
    }

    internal companion object {
        internal const val flagLocal = 0x40000000
        internal const val flagNoBNode = 0x20000000
        internal const val maskValue = 0x1FFFFFFF
    }

    override fun isBnode(value: Int): Boolean = (value and flagNoBNode) != flagNoBNode

    public override fun isLocalValue(value: Int): Boolean {
        return (value and flagLocal) == flagLocal
    }

    override fun valueToGlobal(value: Int): Int {
        val res: Int
        if ((value and flagLocal) != flagLocal) {
            res = value
        } else {
            if ((value and flagNoBNode) == flagNoBNode) {
                val buffer = ByteArrayWrapper()
                getValue(buffer, value)
                res = nodeGlobalDictionary.createValue(buffer)
            } else {
                val tmp = bnodeMapToGlobal[value]
                if (tmp == null) {
                    res = nodeGlobalDictionary.createNewBNode()
                    bnodeMapToGlobal[value] = res
                } else {
                    res = tmp
                }
            }
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    public override fun importFromDictionaryFile(filename: String): Pair<IntArray, Int> {
        var mymapping = IntArray(0)
        var lastId = -1
        val buffer = ByteArrayWrapper()
        DictionaryIntermediateReader(filename).readAll(buffer) { id ->
            if (lastId % 10000 == 0 && lastId != 0) {
                println("imported $lastId dictionaryItems")
            }
            val type = DictionaryHelper.byteArrayToType(buffer)
            val i = if (type == ETripleComponentTypeExt.BLANK_NODE) {
                createNewBNode()
            } else {
                var res = createValue(buffer)
                if (isLocal) {
                    res = res or ADictionary.flagLocal
                }
                res
            }
            SanityCheck.check { lastId == id - 1 }
            lastId = id
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
        println("imported dictionary with $lastId items")
        return Pair(mymapping, lastId + 1)
    }
}
