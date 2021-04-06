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
package lupos.dictionary

import lupos.dictionary.DictionaryHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueUndef
import kotlin.jvm.JvmField

@PublishedApi
internal object DictionaryExt {
    const val booleanTrueValue = (0x00000000) /*lowest 5 values*/ /*required to be 0 for_ truth table loopups*/
    const val booleanFalseValue = (0x00000001) /*lowest 5 values*/ /*required to be 1 for_ truth table loopups*/
    const val errorValue = (0x00000002) /*lowest 5 values*/ /*required to be 2 for_ truth table loopups*/
    const val undefValue = (0x00000003) /*lowest 5 values*/
    const val nullValue = (0x00000004) /*lowest 5 values*/ /*symbol for no more results, previously 'null'*/

    @JvmField
    val booleanTrueValue2 = ValueBoolean(true)

    @JvmField
    val booleanFalseValue2 = ValueBoolean(false)

    @JvmField
    val errorValue2 = ValueError()

    @JvmField
    val undefValue2 = ValueUndef()

    @JvmField
    val booleanTrueValue3 = ByteArrayWrapper()

    @JvmField
    val booleanFalseValue3 = ByteArrayWrapper()

    @JvmField
    val errorValue3 = ByteArrayWrapper()

    @JvmField
    val undefValue3 = ByteArrayWrapper()

    init {
        DictionaryHelper.booleanToByteArray(booleanTrueValue3, true)
        DictionaryHelper.booleanToByteArray(booleanFalseValue3, false)
        DictionaryHelper.errorToByteArray(errorValue3)
        DictionaryHelper.undefToByteArray(undefValue3)
    }
}
