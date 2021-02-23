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
package lupos.s09physicalOperators

import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField

internal class MapKey(@JvmField val data: IntArray) {
    override fun hashCode(): Int {
        var res = 0
        for (element in data) {
            res += element.hashCode()
        }
        return res
    }

    override fun equals(other: Any?) = other is MapKey && data.contentEquals(other.data)
    fun equalsFuzzy(other: Any?): Boolean {
        SanityCheck.check { other is MapKey }
        for (i in data.indices) {
            if (data[i] != ResultSetDictionaryExt.undefValue && (other as MapKey).data[i] != ResultSetDictionaryExt.undefValue && data[i] != other.data[i]) {
                return false
            }
        }
        return true
    }
}
