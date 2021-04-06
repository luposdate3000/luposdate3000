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

public object DictionaryExt {
    const public val booleanTrueValue :Int= (0x00000000) /*lowest 5 values*/ /*required to be 0 for_ truth table loopups*/
    const public val booleanFalseValue :Int= (0x00000001) /*lowest 5 values*/ /*required to be 1 for_ truth table loopups*/
    const public val errorValue :Int= (0x00000002) /*lowest 5 values*/ /*required to be 2 for_ truth table loopups*/
    const public val undefValue :Int= (0x00000003) /*lowest 5 values*/
    const public val nullValue :Int= (0x00000004) /*lowest 5 values*/ /*symbol for no more results, previously 'null'*/

    @JvmField
    public val booleanTrueValue2 :ValueBoolean= ValueBoolean(true)

    @JvmField
    public val booleanFalseValue2 :ValueBoolean= ValueBoolean(false)

    @JvmField
    public val errorValue2 :ValueError= ValueError()

    @JvmField
    public val undefValue2 :ValueUndef= ValueUndef()

    @JvmField
    public val booleanTrueValue3 :ByteArrayWrapper= ByteArrayWrapper()

    @JvmField
    public val booleanFalseValue3 :ByteArrayWrapper= ByteArrayWrapper()

    @JvmField
    public val errorValue3 :ByteArrayWrapper= ByteArrayWrapper()

    @JvmField
    public val undefValue3 :ByteArrayWrapper= ByteArrayWrapper()

    init {
        DictionaryHelper.booleanToByteArray(booleanTrueValue3, true)
        DictionaryHelper.booleanToByteArray(booleanFalseValue3, false)
        DictionaryHelper.errorToByteArray(errorValue3)
        DictionaryHelper.undefToByteArray(undefValue3)
    }
}
