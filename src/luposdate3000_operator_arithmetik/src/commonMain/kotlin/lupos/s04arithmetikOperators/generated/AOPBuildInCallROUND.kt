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

import lupos.dictionary.DictionaryExt
import lupos.dictionary.DictionaryHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.MyBigDecimal
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

public class AOPBuildInCallROUND public constructor(query: IQuery, child0: AOPBase,) : AOPBase(query, EOperatorIDExt.AOPBuildInCallROUNDID, "AOPBuildInCallROUND", arrayOf(child0,)) {
    override fun toSparql(): String = "ROUND(${children[0].toSparql()})"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallROUND && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = AOPBuildInCallROUND(query, children[0].cloneOP() as AOPBase)
    override fun evaluateID(row: IteratorBundle): () -> Int {
        val tmp_0 = ByteArrayWrapper()
        val tmp_2 = ByteArrayWrapper()
        val child0 = (children[0] as AOPBase).evaluateID(row)
        return {
            var res: Int
            val childIn0 = child0()
            query.getDictionary().getValue(tmp_0, childIn0)
            val tmp_1 = DictionaryHelper.byteArrayToType(tmp_0)
            if (tmp_1 == ETripleComponentTypeExt.INTEGER) {
                tmp_0.copyInto(tmp_2)
                res = query.getDictionary().createValue(tmp_2)
            } else if (tmp_1 == ETripleComponentTypeExt.DECIMAL) {
                val tmp_4 = MyBigDecimal(DictionaryHelper.byteArrayToDecimal(tmp_0))
                val tmp_5 = tmp_4.round()
                DictionaryHelper.decimalToByteArray(tmp_2, tmp_5.toString())
                res = query.getDictionary().createValue(tmp_2)
            } else if (tmp_1 == ETripleComponentTypeExt.DOUBLE) {
                val tmp_7 = DictionaryHelper.byteArrayToDouble(tmp_0)
                val tmp_8 = tmp_7.roundToInt()
                DictionaryHelper.doubleToByteArray(tmp_2, tmp_8)
                res = query.getDictionary().createValue(tmp_2)
            } else if (tmp_1 == ETripleComponentTypeExt.FLOAT) {
                val tmp_10 = DictionaryHelper.byteArrayToFloat(tmp_0)
                val tmp_11 = tmp_10.roundToInt()
                DictionaryHelper.floatToByteArray(tmp_2, tmp_11)
                res = query.getDictionary().createValue(tmp_2)
            } else {
                res = DictionaryExt.errorValue
            }
            res
        }
    }
}
