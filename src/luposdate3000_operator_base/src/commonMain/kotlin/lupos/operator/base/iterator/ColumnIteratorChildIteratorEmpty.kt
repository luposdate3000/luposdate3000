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
package lupos.operator.base.iterator

import lupos.shared.ColumnIteratorChildIterator
import lupos.shared.DictionaryValueType
import lupos.shared.inline.ColumnIteratorChildIteratorExt
import lupos.shared.IQuery
public class ColumnIteratorChildIteratorEmpty(query:IQuery) : ColumnIteratorChildIterator(query) {
    override /*suspend*/ fun close() {
        _close()
    }

    override /*suspend*/ fun next(): DictionaryValueType {
        return ColumnIteratorChildIteratorExt.nextHelper(query,this, {}, { _close() })
    }
}
