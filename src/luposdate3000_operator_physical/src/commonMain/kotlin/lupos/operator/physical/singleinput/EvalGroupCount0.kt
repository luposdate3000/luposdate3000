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
import lupos.shared.ColumnIteratorValue
import lupos.shared.DictionaryValueHelper
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalGroupCount0 {

    public operator fun invoke(
        child: IteratorBundle,
        binding: String,
        dict: IDictionary,
    ): IteratorBundle {
        val buffer = ByteArrayWrapper()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val iterator = child.columns.values.first()
        var counter = DictionaryValueHelper.NULL
        while (true) {
            val value = iterator.next()
            if (value == DictionaryValueHelper.nullValue) {
                iterator.close()
                break
            }
            counter++
        }
        DictionaryHelper.integerToByteArray(buffer, BigInteger(counter))
        outMap[binding] = ColumnIteratorValue(dict.createValue(buffer))
        return IteratorBundle(outMap)
    }
}
