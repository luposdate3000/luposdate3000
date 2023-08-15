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
package lupos.shared

import lupos.shared.Luposdate3000Exception
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import kotlin.jvm.JvmField

public class ValueComparatorASC(@JvmField public val query: IQuery) : Comparator<DictionaryValueType> {
    @JvmField
    internal var bufferA = ByteArrayWrapper()

    @JvmField
    internal var bufferB = ByteArrayWrapper()
    override fun compare(a: DictionaryValueType, b: DictionaryValueType): Int {
        query.getDictionary().getValue(bufferA, a)
        query.getDictionary().getValue(bufferB, b)
        try {
            return DictionaryHelper.byteArrayCompareAny(bufferA, bufferB)
        } catch (e: Luposdate3000Exception) {
            return (b - a).toInt()
        } catch (e: Throwable) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/ValueComparatorASC.kt:37"/*SOURCE_FILE_END*/)
            return (b - a).toInt()
        }
/*Coverage Unreachable*/
    }
}
