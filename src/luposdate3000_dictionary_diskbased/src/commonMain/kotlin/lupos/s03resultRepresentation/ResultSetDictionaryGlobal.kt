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
package lupos.s03resultRepresentation

import lupos.fileformat.DictionaryIntermediateReader
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.SanityCheck

public val nodeGlobalDictionary: ResultSetDictionaryGlobal = ResultSetDictionaryGlobal()

public class ResultSetDictionaryGlobal {
    private val kv = KeyValueStore()
    private var bNodeCounter = 5
    private var lastPage: Int = 0
    private var lastPageBuf: ByteArray = ByteArray(0)
    private var lastPageOffset: Int = 0
    private var nextID = 0
    private var mappingID2Page = IntArray(100)
    private var mappingID2Off = IntArray(100)
    private var mappingSorted = IntArray(100)

    public fun debugAllDictionaryContent() {
    }

    public fun importFromDictionaryFile(filename: String, mapping: IntArray) {
        importFromDictionaryFileH(filename, mapping)
    }

    public fun importFromDictionaryFile(filename: String) {
        importFromDictionaryFileH(filename, null)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun importFromDictionaryFileH(filename: String, mapping: IntArray?) {
        var lastId = -1
        DictionaryIntermediateReader(filename).readAll { type, id, value ->
            val i = if (type == ETripleComponentTypeExt.BLANK_NODE) {
                createNewBNode(value)
            } else {
                kv.createValue(ResultSetDictionaryHelper.intermediateToByteArray(value, type))
            }
            SanityCheck.check { lastId == id - 1 }
            lastId = id
            if (mapping != null) {
                mapping[id] = i
            }
        }
    }

    public fun prepareBulk(total: Int, typed: IntArray) {
    }

    public fun createNewBNode(value: String): Int {
        val res: Int = (ResultSetDictionaryShared.flaggedValueGlobalBnode or (bNodeCounter++))
        return res
    }

    public fun createNewBNode(): Int {
        val res: Int = (ResultSetDictionaryShared.flaggedValueGlobalBnode or (bNodeCounter++))
        return res
    }

    public fun getValue(value: Int): ValueDefinition {
        val tmp = kv.getValue(value)
        return ResultSetDictionaryHelper.byteArrayToValueDefinition(tmp)
    }

    internal inline fun getValue(
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
        val tmp = kv.getValue(value)
        ResultSetDictionaryHelper.byteArrayToCallback(value, tmp, onBNode, onBoolean, onLanguageTaggedLiteral, onSimpleLiteral, onTypedLiteral, onDecimal, onFloat, onDouble, onInteger, onIri, onError, onUndefined)
    }

    public fun createValue(value: String?): Int {
        return kv.createValue(ResultSetDictionaryHelper.valueToByteArray(value))
    }

    internal inline fun createValue(value: ValueDefinition): Int {
        return kv.createValue(ResultSetDictionaryHelper.valueToByteArray(value))
    }

    internal inline fun hasValue(value: ValueDefinition): Int? {
        return kv.hasValue(ResultSetDictionaryHelper.valueToByteArray(value))
    }
}
