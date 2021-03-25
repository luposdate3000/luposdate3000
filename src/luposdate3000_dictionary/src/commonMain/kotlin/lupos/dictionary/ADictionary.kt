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

import lupos.s00misc.ByteArrayWrapper
import lupos.s03resultRepresentation.ValueDefinition

public abstract class ADictionary : IDictionary {
    private val bnodeMapToGlobal = mutableMapOf<Int, Int>()

    internal companion object {
        internal const val flagLocal = 0x40000000
        internal const val flagBNode = 0x20000000
        internal const val noFlags = 0x1FFFFFFF
    }

    override fun isBnode(value: Int): Boolean = (value and flagBNode) == flagBNode

    override fun toBooleanOrError(value: Int): Int {
        var res: Int = DictionaryExt.errorValue
        if (value < DictionaryExt.undefValue && value >= 0) {
            res = value
        } else {
            try {
                res = if (getValue(value).toBoolean()) {
                    DictionaryExt.booleanTrueValue
                } else {
                    DictionaryExt.booleanFalseValue
                }
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        return res
    }

    override fun valueToGlobal(value: Int): Int {
        val res: Int
        if ((value and flagLocal) != flagLocal) {
            res = value
        } else {
            if ((value and flagBNode) == flagBNode) {
                val tmp = bnodeMapToGlobal[value]
                if (tmp == null) {
                    res = nodeGlobalDictionary.createNewBNode()
                    bnodeMapToGlobal[value] = res
                } else {
                    res = tmp
                }
            } else {
                val buffer = ByteArrayWrapper()
                getValue(buffer, value)
                res = nodeGlobalDictionary.createValue(buffer)
            }
        }
        return res
    }

    public override fun createValue(value: String?): Int {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.valueToByteArray(buffer, value)
        return createValue(buffer)
    }

    public override fun createValue(value: ValueDefinition): Int {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.valueToByteArray(buffer, value)
        return createValue(buffer)
    }

    public override fun hasValue(value: ValueDefinition): Int? {
        val buffer = ByteArrayWrapper()
        DictionaryHelper.valueToByteArray(buffer, value)
        return hasValue(buffer)
    }

    public override fun getValue(value: Int): ValueDefinition {
        val buffer = ByteArrayWrapper()
        getValue(buffer, value)
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
        val buffer = ByteArrayWrapper()
        getValue(buffer, value)
        DictionaryHelper.byteArrayToCallback(value, buffer, onBNode, onBoolean, onLanguageTaggedLiteral, onSimpleLiteral, onTypedLiteral, onDecimal, onFloat, onDouble, onInteger, onIri, onError, onUndefined)
    }
}
