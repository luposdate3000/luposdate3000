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

import lupos.fileformat.DictionaryIntermediateReader
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ValueDefinition

public class DictionaryInMemory : ADictionary {
    private var dataI2V = Array<ByteArrayWrapper>(1) { ByteArrayWrapper() }
    private var dataV2I = mutableMapOf<ByteArrayWrapper, Int>()
    private var bNodeCounter = 5
    private val isLocal: Boolean

    internal constructor(isLocal: Boolean) : super() {
        this.isLocal = isLocal
    }

    @Suppress("NOTHING_TO_INLINE")
    public override fun importFromDictionaryFile(filename: String): IntArray {
        var mymapping = IntArray(0)
        var lastId = -1
        val buffer = ByteArrayWrapper()
        DictionaryIntermediateReader(filename).readAll(buffer) { id ->
            val type = DictionaryHelper.byteArrayToType(buffer)
            val i = if (type == ETripleComponentTypeExt.BLANK_NODE) {
                createNewBNode()
            } else {
                var res = kv.createValue(buffer)
                if (isLocal) {
                    res = res or DictionaryShared.flagLocal
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
        return mymapping
    }

    public override fun createNewBNode(): Int {
        val res: Int = bNodeCounter++ or DictionaryShared.flagBNode
        if (isLocal) {
            res = res or DictionaryShared.flagLocal
        }
        ByteArrayHelper.writeInt4(rootPage, 0, bNodeCounter)
        return res
    }

    public override fun getValue(value: Int): ValueDefinition {
        val buffer = dataI2V(value and DictionaryShared.noFlags)!!
        return DictionaryHelper.byteArrayToValueDefinition(buffer)
    }

    public override fun getValue(
        value: Int,
        onBNode: (value: Int) -> Unit,
        onBoolean: (value: Boolean) -> Unit,
        onLanguageTaggedLiteral: (content: String, lang: String) -> Unit,
        onSimpleLiteral: (content: String) -> Unit,
        onTypedLiteral: (content: String, type: String) -> Unit,
        onDecimal: (value: String) -> Unit,
        onFloat: (value: Double) -> Unit,
        onDouble: (value: Double) -> Unit,
        onInteger: (value: String) -> Unit,
        onIri: (value: String) -> Unit,
        onError: () -> Unit,
        onUndefined: () -> Unit
    ) {
        val buffer = dataI2V(value and DictionaryShared.noFlags)!!
        DictionaryHelper.byteArrayToCallback(value, buffer, onBNode, onBoolean, onLanguageTaggedLiteral, onSimpleLiteral, onTypedLiteral, onDecimal, onFloat, onDouble, onInteger, onIri, onError, onUndefined)
    }

    public override fun createValue(value: String?): Int {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.valueToByteArray(buffer, value)
        var res = dataV2I[buffer]
        if (res == null) {
            res = dataV2I.size
            dataV2I[buffer] = res
            if (dataI2V.size <= res) {
                val tmp = dataI2V
                dataI2V = Array<ByteArrayWrapper>(dataI2V.size * 2) { buffer }
                tmp.copyInto(dataI2V)
            }
        }
        if (isLocal) {
            res = res or DictionaryShared.flagLocal
        }
        return res
    }

    internal override inline fun createValue(value: ValueDefinition): Int {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.valueToByteArray(buffer, value)
        var res = dataV2I[buffer]
        if (res == null) {
            res = dataV2I.size
            dataV2I[buffer] = res
            if (dataI2V.size <= res) {
                val tmp = dataI2V
                dataI2V = Array<ByteArrayWrapper>(dataI2V.size * 2) { buffer }
                tmp.copyInto(dataI2V)
            }
        }
        if (isLocal) {
            res = res or DictionaryShared.flagLocal
        }
        return res
    }

    internal inline fun hasValue(value: ValueDefinition): Int? {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.valueToByteArray(buffer, value)
        var res = dataV2I[buffer]
        if (res == null) {
            return null
        }
        if (isLocal) {
            res = res or DictionaryShared.flagLocal
        }
        return res
    }
}
