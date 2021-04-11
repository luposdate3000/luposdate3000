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
package lupos.s04arithmetikOperators.generated

import kotlin.jvm.JvmField
import lupos.dictionary.DictionaryExt
import lupos.dictionary.DictionaryHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

public class AOPBuildInCallIRI public constructor(query: IQuery, child0: AOPBase, @JvmField public var prefix: String, ) : AOPBase(query, EOperatorIDExt.AOPBuildInCallIRIID, "AOPBuildInCallIRI", arrayOf(child0, )) {
    override fun toSparql(): String = "IRI(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallIRI && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPBuildInCallIRI(query, children[0].cloneOP() as AOPBase, prefix)
    override fun evaluateID(row: IteratorBundle): () -> Int {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_2: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> Int = (children[0] as AOPBase).evaluateID(row)
        return {
            var res: Int
            val childIn0: Int = child0()
            query.getDictionary().getValue(tmp_0, childIn0)
            val tmp_1: ETripleComponentType = DictionaryHelper.byteArrayToType(tmp_0)
            when (tmp_1) {
                ETripleComponentTypeExt.IRI -> {
                    tmp_0.copyInto(tmp_2)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING -> {
                    val tmp_4: String = DictionaryHelper.byteArrayToString(tmp_0)
                    val tmp_5: String = if (prefix.length > 0 && !prefix.endsWith('/')) {
                        "$prefix/$tmp_4"
                    } else {
                        "$prefix$tmp_4"
                    }
                    DictionaryHelper.iriToByteArray(tmp_2, tmp_5)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    val tmp_7_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                    val tmp_7_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                    if (tmp_7_type == "http://www.w3.org/2001/XMLSchema#string") {
                        val tmp_8: String = if (prefix.length > 0 && !prefix.endsWith('/')) {
                            "$prefix/$tmp_7_content"
                        } else {
                            "$prefix$tmp_7_content"
                        }
                        DictionaryHelper.iriToByteArray(tmp_2, tmp_8)
                        res = query.getDictionary().createValue(tmp_2)
                    } else {
                        DictionaryHelper.errorToByteArray(tmp_2)
                        res = query.getDictionary().createValue(tmp_2)
                    }
                }
                else -> {
                    res = DictionaryExt.errorValue
                }
            }
            res
        }
    }
}
