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

public abstract class ADictionary : IDictionary {
    private val bnodeMapToGlobal = mutableMapOf<Int, Int>()

    internal companion object {
        internal const val flagLocal = 0x40000000
        internal const val flagBNode = 0x20000000
        internal const val noFlags = 0x1FFFFFFF
    }

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
        if ((value and DictionaryShared.flagLocal) != DictionaryShared.flagLocal) {
            res = value
        } else {
            if ((value and DictionaryShared.flagBNode) == DictionaryShared.flagBNode) {
                val tmp = bnodeMapToGlobal[value]
                if (tmp == null) {
                    res = nodeGlobalDictionary.createNewBNode()
                    bnodeMapToGlobal[value] = res
                } else {
                    res = tmp
                }
            } else {
                res = nodeGlobalDictionary.createValue(getValue(value))
            }
        }
        return res
    }
}
