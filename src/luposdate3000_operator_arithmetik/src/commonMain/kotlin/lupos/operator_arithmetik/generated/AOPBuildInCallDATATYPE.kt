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
package lupos.operator.arithmetik.generated

import lupos.operator.arithmetik.AOPBase
import lupos.operator.logical.IOPBase
import lupos.operator.logical.IQuery
import lupos.operator.logical.iterator.IteratorBundle
import lupos.shared.ByteArrayWrapper
import lupos.shared.EOperatorIDExt
import lupos.shared.ETripleComponentType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.dictionary.DictionaryExt
import lupos.shared_inline.DictionaryHelper

public class AOPBuildInCallDATATYPE public constructor(query: IQuery, child0: AOPBase, ) : AOPBase(query, EOperatorIDExt.AOPBuildInCallDATATYPEID, "AOPBuildInCallDATATYPE", arrayOf(child0, )) {
    override fun toSparql(): String = "DATATYPE(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallDATATYPE && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPBuildInCallDATATYPE(query, children[0].cloneOP() as AOPBase)
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
                ETripleComponentTypeExt.BLANK_NODE -> {
                    DictionaryHelper.errorToByteArray(tmp_2)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.BOOLEAN -> {
                    DictionaryHelper.stringToByteArray(tmp_2, "http://www.w3.org/2001/XMLSchema#boolean")
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DATE_TIME -> {
                    DictionaryHelper.stringToByteArray(tmp_2, "http://www.w3.org/2001/XMLSchema#dateTime")
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    DictionaryHelper.stringToByteArray(tmp_2, "http://www.w3.org/2001/XMLSchema#decimal")
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    DictionaryHelper.stringToByteArray(tmp_2, "http://www.w3.org/2001/XMLSchema#double")
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.ERROR -> {
                    DictionaryHelper.errorToByteArray(tmp_2)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.FLOAT -> {
                    DictionaryHelper.stringToByteArray(tmp_2, "http://www.w3.org/2001/XMLSchema#float")
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.INTEGER -> {
                    DictionaryHelper.stringToByteArray(tmp_2, "http://www.w3.org/2001/XMLSchema#integer")
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.IRI -> {
                    DictionaryHelper.errorToByteArray(tmp_2)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING -> {
                    DictionaryHelper.stringToByteArray(tmp_2, "http://www.w3.org/2001/XMLSchema#string")
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    DictionaryHelper.stringToByteArray(tmp_2, "http://www.w3.org/1999/02/22-rdf-syntax-ns#langString")
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    val tmp_14_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                    val tmp_14_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                    val tmp_15: String = tmp_14_type
                    DictionaryHelper.stringToByteArray(tmp_2, tmp_15)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.UNDEF -> {
                    DictionaryHelper.errorToByteArray(tmp_2)
                    res = query.getDictionary().createValue(tmp_2)
                }
                else -> {
                    res = DictionaryExt.errorValue
                }
            }
            res
        }
    }
}
