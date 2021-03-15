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

package lupos.fileformat

import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.File

abstract class DictionaryIntermediate(val filename: String) {
    var streamOut: IMyOutputStream?
    var streamIn: IMyInputStream?
    abstract fun close()

    companion object {
        const val filenameEnding = ".dictionary"
        fun delete() {
            File("$filename$filenameEnding").deleteRecursively()
        }

        fun encodeFromParser(value: String, type: ETripleComponentType): String {
// sparql -> encoding in file
            when (type) {
                ETripleComponentTypeExt.IRI -> return encodeIri(value)
                ETripleComponentTypeExt.BLANK_NODE -> return value
                ETripleComponentTypeExt.STRING -> return encodeString(value)
                ETripleComponentTypeExt.INTEGER -> return encodeInteger(value)
                ETripleComponentTypeExt.DECIMAL -> return encodeDecimal(value)
                ETripleComponentTypeExt.DOUBLE -> return encodeDouble(value)
                ETripleComponentTypeExt.BOOLEAN -> return encodeBoolean(value)
                ETripleComponentTypeExt.STRING_TYPED -> {
                    var idx = value.indexOf("^^")
                    return encodeTyped(value.substring(0, idx), value.substring(idx + 2))
                }
                ETripleComponentTypeExt.STRING_LANG -> return {
                    var idx = value.indexOf("@")
                    return encodeLang(value.substring(0, idx), value.substring(idx + 1))
                }
                else -> throw Exception("unrechable")
            }
        }

        // encode* from given values -> encoding in the file
// decode* encoding in file -> split as expected by dictionary
        fun encodeIri(value: String): String = value
        fun decodeIri(value: String): String = value.substring(1, value.length - 1)
        fun encodeString(value: String): String = value
        fun decodeStringAsTyped(value: String): Pair<String, String> = Pair(value, "")
        fun encodeInteger(value: String): String = value
        fun decodeInteger(value: String): String = value
        fun encodeDecimal(value: String): String = value
        fun decodeDecimal(value: String): String = value
        fun encodeDouble(value: String): String = value
        fun decodeDouble(value: String): String = value
        fun encodeBoolean(value: String): String = value
        fun decodeBoolean(value: String): String = value
        fun encodeTyped(value: String, type: String): String = "$type^^$value"
        fun decodeTyped(value: String): Pair<String, String> {
            var idx = value.indexOf("^^")
            return Pair(value.substring(idx + 2), value.substring(0, idx))
        }

        fun encodeLang(value: String, lang: String): String = "$lang@$value"
        fun decodeLang(value: String): Pair<String, String> {
            var idx = value.indexOf("@")
            return Pair(value.substring(idx + 1), value.substring(0, idx))
        }
    }
}
