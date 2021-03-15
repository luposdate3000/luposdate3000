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

import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.File
import lupos.s00misc.IMyInputStream
import lupos.s00misc.IMyOutputStream

public abstract class DictionaryIntermediate(internal val filename: String) {
    internal var streamOut: IMyOutputStream? = null
    internal var streamIn: IMyInputStream? = null
    public abstract fun close()

    public companion object {
        internal const val filenameEnding = ".dictionary"
        public fun delete(filename: String) {
            File("$filename$filenameEnding").deleteRecursively()
        }

        public fun encodeFromParser(value: String, type: ETripleComponentType): String {
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
                ETripleComponentTypeExt.STRING_LANG -> {
                    var idx = value.indexOf("@")
                    return encodeLang(value.substring(0, idx), value.substring(idx + 1))
                }
                else -> throw Exception("unrechable")
            }
        }

        // encode* from given values -> encoding in the file
        // decode* encoding in file -> split as expected by dictionary
        public fun encodeIri(value: String): String = value
        public fun decodeIri(value: String): String = value.substring(1, value.length - 1)
        public fun encodeString(value: String): String = value
        public fun decodeStringAsTyped(value: String): Pair<String, String> = Pair(value, "")
        public fun encodeInteger(value: String): String = value
        public fun decodeInteger(value: String): String = value
        public fun encodeDecimal(value: String): String = value
        public fun decodeDecimal(value: String): String = value
        public fun encodeDouble(value: String): String = value
        public fun decodeDouble(value: String): String = value
        public fun encodeBoolean(value: String): String = value
        public fun decodeBoolean(value: String): String = value
        public fun encodeTyped(value: String, type: String): String = "$type^^$value"
        public fun decodeTyped(value: String): Pair<String, String> {
            var idx = value.indexOf("^^")
            return Pair(value.substring(idx + 2), value.substring(0, idx))
        }

        public fun encodeLang(value: String, lang: String): String = "$lang@$value"
        public fun decodeLang(value: String): Pair<String, String> {
            var idx = value.indexOf("@")
            return Pair(value.substring(idx + 1), value.substring(0, idx))
        }
    }
}
