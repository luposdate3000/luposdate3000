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

package lupos.shared.fileformat
import kotlin.jvm.JvmField
import lupos.shared.DictionaryValueType
import lupos.shared.dynamicArray.ByteArrayWrapper

internal class DictionaryIntermediateRow(@JvmField internal val id: DictionaryValueType, @JvmField internal val data: ByteArrayWrapper) : Comparable<DictionaryIntermediateRow> {
    override operator fun compareTo(other: DictionaryIntermediateRow): Int {
        return data.compareTo(other.data)
    }

    override fun equals(other: Any?): Boolean {
        return other is DictionaryIntermediateRow && compareTo(other) == 0
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }
}
