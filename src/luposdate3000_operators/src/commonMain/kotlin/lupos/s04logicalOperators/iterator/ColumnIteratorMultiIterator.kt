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
package lupos.s04logicalOperators.iterator
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import kotlin.jvm.JvmField
public class ColumnIteratorMultiIterator(@JvmField public val childs: List<ColumnIterator>) : ColumnIterator() {
    @JvmField
    public var index: Int = 0
    @JvmField
    public var label: Int = 1
    @Suppress("NOTHING_TO_INLINE") /*suspend*/ internal inline fun _close() {
        if (label != 0) {
            label = 0
            for (c in childs) {
                c.close()
            }
        }
    }
    override /*suspend*/ fun close() {
        _close()
    }
    override /*suspend*/ fun next(): Int {
        return if (label == 1) {
            var res = childs[index].next()
            while (res == ResultSetDictionaryExt.nullValue && ++index < childs.size) {
                res = childs[index].next()
            }
            if (res == ResultSetDictionaryExt.nullValue) {
                _close()
            }
            res
        } else {
            ResultSetDictionaryExt.nullValue
        }
    }
}
