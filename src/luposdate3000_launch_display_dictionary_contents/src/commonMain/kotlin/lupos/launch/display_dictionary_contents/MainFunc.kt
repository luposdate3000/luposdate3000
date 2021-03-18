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
package lupos.launch.display_dictionary_contents

import lupos.fileformat.DictionaryIntermediate
import lupos.fileformat.DictionaryIntermediateReader
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.Parallel

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(inputFileName: String): Unit = Parallel.runBlocking {
    DictionaryIntermediateReader(inputFileName).readAll { type, id, value ->
        when (type) {
            ETripleComponentTypeExt.IRI -> println("$id :=: $type : ${DictionaryIntermediate.decodeIri(value)}")
            ETripleComponentTypeExt.BLANK_NODE -> println("$id :=: $type : $value")
            ETripleComponentTypeExt.STRING -> println("$id :=: $type : ${DictionaryIntermediate.decodeString(value)}")
            ETripleComponentTypeExt.INTEGER -> println("$id :=: $type : ${DictionaryIntermediate.decodeInteger(value)}")
            ETripleComponentTypeExt.DECIMAL -> println("$id :=: $type : ${DictionaryIntermediate.decodeDecimal(value)}")
            ETripleComponentTypeExt.DOUBLE -> println("$id :=: $type : ${DictionaryIntermediate.decodeDouble(value)}")
            ETripleComponentTypeExt.BOOLEAN -> println("$id :=: $type : ${DictionaryIntermediate.decodeBoolean(value)}")
            ETripleComponentTypeExt.STRING_TYPED -> println("$id :=: $type : ${DictionaryIntermediate.decodeTyped(value)}")
            ETripleComponentTypeExt.STRING_LANG -> println("$id :=: $type : ${DictionaryIntermediate.decodeLang(value)}")
            else -> throw Exception("unrechable")
        }
    }
}
