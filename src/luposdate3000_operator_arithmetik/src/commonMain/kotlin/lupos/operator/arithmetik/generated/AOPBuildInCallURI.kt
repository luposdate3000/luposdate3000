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
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorIDExt
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.IQuery
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class AOPBuildInCallURI public constructor(query: IQuery, child0: AOPBase, @JvmField public var prefix: String) : AOPBase(query, EOperatorIDExt.AOPBuildInCallURIID, "AOPBuildInCallURI", arrayOf(child0)) {
    override fun toSparql(): String = "URI(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallURI && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPBuildInCallURI(query, children[0].cloneOP() as AOPBase, prefix)
init{
println("AOPBuildInCallURI .. constructor $prefix")
}
    override fun evaluateID(row: IteratorBundle): () -> DictionaryValueType {
        val tmp_0: ByteArrayWrapper = ByteArrayWrapper()
        val tmp_2: ByteArrayWrapper = ByteArrayWrapper()
        val child0: () -> DictionaryValueType = (children[0] as AOPBase).evaluateID(row)
        return {
println("AOPBuildInCallURI .. $prefix")
            val res: DictionaryValueType
            val childIn0: DictionaryValueType = child0()
            query.getDictionary().getValue(tmp_0, childIn0)
            when (DictionaryHelper.byteArrayToType(tmp_0)) {
                ETripleComponentTypeExt.BLANK_NODE, ETripleComponentTypeExt.BOOLEAN, ETripleComponentTypeExt.DATE_TIME, ETripleComponentTypeExt.DECIMAL, ETripleComponentTypeExt.DOUBLE, ETripleComponentTypeExt.ERROR, ETripleComponentTypeExt.FLOAT, ETripleComponentTypeExt.INTEGER, ETripleComponentTypeExt.STRING_LANG, ETripleComponentTypeExt.UNDEF -> {
                    DictionaryHelper.errorToByteArray(tmp_2)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.IRI -> {
                    ByteArrayWrapperExt.copyInto(tmp_0, tmp_2, false)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING -> {
                    val tmp_12: String = DictionaryHelper.byteArrayToString(tmp_0)
                    val tmp_13: String = if (prefix.isNotEmpty() && !prefix.endsWith('/')) {
                        "$prefix/$tmp_12"
                    } else {
                        "$prefix$tmp_12"
                    }
                    DictionaryHelper.iriToByteArray(tmp_2, tmp_13)
                    res = query.getDictionary().createValue(tmp_2)
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    val tmp_16_content: String = DictionaryHelper.byteArrayToTyped_Content(tmp_0)
                    val tmp_16_type: String = DictionaryHelper.byteArrayToTyped_Type(tmp_0)
                    res = if (tmp_16_type == "http://www.w3.org/2001/XMLSchema#string") {
                        val tmp_17: String = if (prefix.isNotEmpty() && !prefix.endsWith('/')) {
                            "$prefix/$tmp_16_content"
                        } else {
                            "$prefix$tmp_16_content"
                        }
                        DictionaryHelper.iriToByteArray(tmp_2, tmp_17)
                        query.getDictionary().createValue(tmp_2)
                    } else {
                        DictionaryHelper.errorToByteArray(tmp_2)
                        query.getDictionary().createValue(tmp_2)
                    }
                }
                else -> {
                    res = DictionaryValueHelper.errorValue
                }
            }
            res
        }
    }
}
