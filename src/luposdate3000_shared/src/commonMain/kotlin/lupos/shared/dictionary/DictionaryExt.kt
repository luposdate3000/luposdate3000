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
package lupos.shared.dictionary

import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import kotlin.jvm.JvmField

public object DictionaryExt {

    @JvmField
    public val booleanTrueValue3: ByteArrayWrapper = ByteArrayWrapper()

    @JvmField
    public val booleanFalseValue3: ByteArrayWrapper = ByteArrayWrapper()

    @JvmField
    public val errorValue3: ByteArrayWrapper = ByteArrayWrapper()

    @JvmField
    public val undefValue3: ByteArrayWrapper = ByteArrayWrapper()

    init {
        DictionaryHelper.booleanToByteArray(booleanTrueValue3, true)
        DictionaryHelper.booleanToByteArray(booleanFalseValue3, false)
        DictionaryHelper.errorToByteArray(errorValue3)
        DictionaryHelper.undefToByteArray(undefValue3)
    }
}
