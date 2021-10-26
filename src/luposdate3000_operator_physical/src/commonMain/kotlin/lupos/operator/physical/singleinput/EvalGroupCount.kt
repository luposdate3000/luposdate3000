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
import lupos.operator.arithmetik.AOPBase
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalGroupCount {

    public operator fun invoke(
        child: IteratorBundle,
        bindings: MutableList<Pair<String, AOPBase>>,
        keyColumnNames: Array<String>,
        dict: IDictionary,
    ): IteratorBundle {
        val localVariables = child.names
        val buffer = ByteArrayWrapper()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val keyColumns: Array<ColumnIterator> = Array(keyColumnNames.size) { child.columns[keyColumnNames[it]]!! }
        val valueColumnNames = mutableListOf<String>()
        for (name in localVariables) {
            if (!keyColumnNames.contains(name)) {
                valueColumnNames.add(name)
            }
        }
        val valueColumns = Array(valueColumnNames.size) { child.columns[valueColumnNames[it]]!! }
        val iterator = keyColumns[0]
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
        outMap[keyColumnNames[0]] = ColumnIteratorMultiValue(arrK, arrK.size)
        outMap[bindings.toList().first().first] = ColumnIteratorMultiValue(arrV, arrV.size)
        return IteratorBundle(outMap)
    }
}
