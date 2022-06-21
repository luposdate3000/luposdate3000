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
package lupos.launch.benchmark_ml_python_interface

import lupos.operator.base.Query
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.Luposdate3000Instance
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public object EvalCounter {
    public operator fun invoke(query: Query, child: IteratorBundle, instance: Luposdate3000Instance): IteratorBundle {
val the_limit=200000
val the_global_limit=20000000
        if (child.hasCountMode()) {
            query.machineLearningCounter++
            query.machineLearningCounterGlobal++
            return child
        }
        val variables = child.columns.keys
        val outMap = mutableMapOf<String, ColumnIterator>()
        var first = true
        for (variable in variables) {
            val tmp = if (first) {
                first = false
                object : ColumnIterator() {
                    var local_ctr = 0;

                    @JvmField
                    val iterator = child.columns[variable]!!

                    @JvmField
                    var label = 1
                    override /*suspend*/ fun next(): DictionaryValueType {
                        return if (label != 0) {
                            query.machineLearningCounter++
                            local_ctr++
query.machineLearningCounterGlobal++
                            if (local_ctr > the_limit||query.machineLearningCounterGlobal>the_global_limit) {
query.machineLearningAbort=true
                                DictionaryValueHelper.nullValue
                            } else {
                                iterator.next()
                            }
                        } else {
                            DictionaryValueHelper.nullValue
                        }
                    }

                    @Suppress("NOTHING_TO_INLINE")
                    /*suspend*/ inline fun _close() {
                        if (label != 0) {
                            label = 0
                            iterator.close()
                        }
                    }

                    override /*suspend*/ fun close() {
                        _close()
                    }
                }
            } else {
                object : ColumnIterator() {
                    var local_ctr = 0;

                    @JvmField
                    val iterator = child.columns[variable]!!

                    @JvmField
                    var label = 1
                    override /*suspend*/ fun next(): DictionaryValueType {
                        return if (label != 0) {
query.machineLearningCounterGlobal++
                            local_ctr++
                            if (local_ctr > the_limit||query.machineLearningCounterGlobal>the_global_limit) {
query.machineLearningAbort=true
                                DictionaryValueHelper.nullValue
                            } else {
                                iterator.next()
                            }
                        } else {
                            DictionaryValueHelper.nullValue
                        }
                    }

                    @Suppress("NOTHING_TO_INLINE")
                    /*suspend*/ inline fun _close() {
                        if (label != 0) {
                            label = 0
                            iterator.close()
                        }
                    }

                    override /*suspend*/ fun close() {
                        _close()
                    }
                }
            }
            outMap[variable] = tmp
        }
        return IteratorBundle(outMap)
    }
}
