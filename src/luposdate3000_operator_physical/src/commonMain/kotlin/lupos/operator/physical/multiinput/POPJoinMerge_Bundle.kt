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
package lupos.operator.physical.multiinput

import lupos.shared.ColumnIteratorChildIterator
import lupos.shared.DictionaryValueHelper
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

internal class POPJoinMerge_Bundle(@JvmField val columnsINJ0: List<ColumnIterator>, @JvmField val columnsINJ1: List<ColumnIterator>, @JvmField val columnsOUTJ: ColumnIteratorChildIterator) : IteratorBundle(0) {
    override /*suspend*/ fun hasNext2(): Boolean {
        val tmp = columnsOUTJ.next() != DictionaryValueHelper.nullValue
        if (!tmp) {
            _hasNext2Close()
        }
        return tmp
    }

    @JvmField
    var localHasnext2closeI = 0

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ private fun _hasNext2Close() {
        localHasnext2closeI = 0
        while (localHasnext2closeI < columnsINJ0.size) {
            columnsINJ0[localHasnext2closeI].close()
            localHasnext2closeI++
        }
        localHasnext2closeI = 0
        while (localHasnext2closeI < columnsINJ1.size) {
            columnsINJ1[localHasnext2closeI].close()
            localHasnext2closeI++
        }
    }

    override /*suspend*/ fun hasNext2Close() {
        _hasNext2Close()
    }
}
