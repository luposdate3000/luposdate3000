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
package lupos.operator.physical.singleinput.modifiers

import lupos.operator.base.POPLimitHandler
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public object EvalLimit {
    public operator fun invoke(child: IteratorBundle, limit: Int, handler: POPLimitHandler?): IteratorBundle {
        val variables = child.columns.keys
        val outMap = mutableMapOf<String, ColumnIterator>()
        for (variable in variables) {
            val tmp = object : ColumnIterator() {
                @JvmField
                var count = 0

                @JvmField
                val iterator = child.columns[variable]!!

                @JvmField
                var label = 1
                override /*suspend*/ fun next(): DictionaryValueType {
                    return if (label != 0) {
                        if (count == limit) {
                            _close()
                            DictionaryValueHelper.nullValue
                        } else {
                            count++
                            val res = iterator.next()
                            if (count == limit && res != DictionaryValueHelper.nullValue) {
                                handler?.setFinished()
                            }
                            res
                        }
                    } else {
                        DictionaryValueHelper.nullValue
                    }
                }

                @Suppress("NOTHING_TO_INLINE")
                /*suspend*/ fun _close() {
                    if (label != 0) {
                        label = 0
                        iterator.close()
                    }
                }

                override /*suspend*/ fun close() {
                    _close()
                }
            }
            outMap[variable] = tmp
        }
        return IteratorBundle(outMap)
    }
}
