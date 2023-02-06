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
package lupos.operator.physical

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.SanityCheck
import kotlin.jvm.JvmField

internal class MapKey(@JvmField val data: DictionaryValueTypeArray) {
    override fun hashCode(): Int {
        var res = 0
        for (element in data) {
            res += element.hashCode()
        }
        return res
    }

    override fun equals(other: Any?) = other is MapKey && data.contentEquals(other.data)
    fun equalsFuzzy(other: Any?): Boolean {
        if (SanityCheck.enabled) { if (!(other is MapKey)) { throw Exception("SanityCheck failed") } }
        for (i in data.indices) {
            if (data[i] != DictionaryValueHelper.undefValue && (other as MapKey).data[i] != DictionaryValueHelper.undefValue && data[i] != other.data[i]) {
                return false
            }
        }
        return true
    }
}
