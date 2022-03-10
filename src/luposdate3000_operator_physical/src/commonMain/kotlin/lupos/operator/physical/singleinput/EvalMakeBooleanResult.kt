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
package lupos.operator.physical.singleinput

import lupos.operator.base.POPLimitHandler
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.shared.DictionaryValueHelper
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public object EvalMakeBooleanResult {
    public operator fun invoke(
        child: IteratorBundle,
        handler: POPLimitHandler?,
    ): IteratorBundle {
        val variables = child.names
        val flag: Boolean
        val outMap = mutableMapOf<String, ColumnIterator>()
        if (variables.isNotEmpty()) {
            flag = child.columns[variables[0]]!!.next() != DictionaryValueHelper.nullValue
            for (variable in variables) {
                child.columns[variable]!!.close()
            }
        } else {
            flag = child.hasNext2()
            child.hasNext2Close()
        }
        val value = if (flag) {
            handler?.setFinished()
            DictionaryValueHelper.booleanTrueValue
        } else {
            DictionaryValueHelper.booleanFalseValue
        }
        outMap["?boolean"] = ColumnIteratorRepeatValue(1, value)
        return IteratorBundle(outMap)
    }
}
