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
package lupos.operator.physical.singleinput

import com.ionspin.kotlin.bignum.integer.BigInteger
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalGroupCount1 {

    public operator fun invoke(
        child: IteratorBundle,
        binding: String,
        keyColumnName: String,
        dict: IDictionary,
    ): IteratorBundle {
        val buffer = ByteArrayWrapper()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val iterator = child.columns.values.first()
        val map = mutableMapOf<DictionaryValueType, Int>()
        while (true) {
            val value = iterator.next()
            if (value == DictionaryValueHelper.nullValue) {
                iterator.close()
                break
            }
            val v = map[value]
            if (v == null) {
                map[value] = 1
            } else {
                map[value] = v + 1
            }
        }
        val arrK = DictionaryValueTypeArray(map.size)
        val arrV = DictionaryValueTypeArray(map.size)
        var i = 0
        for ((k, v) in map) {
            arrK[i] = k
            DictionaryHelper.integerToByteArray(buffer, BigInteger(v))
            arrV[i] = dict.createValue(buffer)
            i++
        }
        outMap[keyColumnName] = ColumnIteratorMultiValue(arrK, arrK.size)
        outMap[binding] = ColumnIteratorMultiValue(arrV, arrV.size)
        return IteratorBundle(outMap)
    }
}
