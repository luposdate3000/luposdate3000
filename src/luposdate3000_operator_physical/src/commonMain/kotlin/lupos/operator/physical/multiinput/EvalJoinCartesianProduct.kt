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

import lupos.operator.base.iterator.ColumnIteratorChildIteratorEmpty
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.operator.base.iterator.ColumnIteratorRepeatIterator
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.operator.base.multiinput.LOPJoin_Helper
import lupos.shared.ColumnIteratorChildIterator
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.SanityCheck
import lupos.shared.inline.ColumnIteratorChildIteratorExt
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorEmpty
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.IQuery
public object EvalJoinCartesianProduct {
    public operator fun invoke(
query:IQuery,
        childA: IteratorBundle,
        childB: IteratorBundle,
        optional: Boolean,
    ): IteratorBundle {
        val columns = LOPJoin_Helper.getColumns(childA.names.toList(), childB.names.toList())
        val columnsINAO = mutableListOf<ColumnIterator>() // only in childA
        val columnsINBO = mutableListOf<ColumnIterator>() // only in childB
        val outO = Array(2) { mutableListOf<ColumnIteratorChildIterator>() } // only in one of the childs
        val outMap = mutableMapOf<String, ColumnIterator>()
        val res: IteratorBundle?
        for (name in columns[1]) {
            columnsINAO.add(childA.columns[name]!!)
        }
        for (name in columns[2]) {
            columnsINBO.add(childB.columns[name]!!)
        }
        val count: Int
        if (columnsINAO.size == 0 && columnsINBO.size == 0) {
            try {
                res = IteratorBundle(childA.count() * childB.count())
            } catch (e: Throwable) {
                e.printStackTrace()
                throw e
            }
        } else if (columnsINAO.size == 0) {
            if (childA.count() > 0) {
                for (columnIndex in 0 until columnsINBO.size) {
                    outMap[columns[2][columnIndex]] = ColumnIteratorRepeatIterator(childA.count(), columnsINBO[columnIndex])
                }
            } else {
                for (v in childB.columns.values) {
                    v.close()
                }
                for (columnIndex in 0 until columnsINBO.size) {
                    outMap[columns[2][columnIndex]] = ColumnIteratorEmpty()
                }
            }
            res = IteratorBundle(outMap)
        } else if (columnsINBO.size == 0) {
            if (childB.count() > 0) {
                for (columnIndex in 0 until columnsINAO.size) {
                    outMap[columns[1][columnIndex]] = ColumnIteratorRepeatIterator(childB.count(), columnsINAO[columnIndex])
                }
            } else {
                for (v in childA.columns.values) {
                    v.close()
                }
                for (columnIndex in 0 until columnsINAO.size) {
                    outMap[columns[2][columnIndex]] = ColumnIteratorEmpty()
                }
            }
            res = IteratorBundle(outMap)
        } else {
            val data: Array<MutableList<DictionaryValueType>> = Array(columnsINBO.size) { mutableListOf() }
            loopC@ while (true) {
                for (columnIndex in 0 until columnsINBO.size) {
                    val value = columnsINBO[columnIndex].next()
                    if (value == DictionaryValueHelper.nullValue) {
                        break@loopC
                    }
                    data[columnIndex].add(value)
                }
            }
            for (v in childB.columns.values) {
                v.close()
            }
            count = data[0].size
            if (count == 0) {
                if (optional) {
                    for (i in 0 until columns[1].size + columns[2].size) {
                        val iterator = object : ColumnIteratorChildIterator(query) {
                            override /*suspend*/ fun close() {
                                __close()
                            }

                            @Suppress("NOTHING_TO_INLINE")
                            /*suspend*/ inline fun __close() {
                                if (label != 0) {
                                    _close()
                                    for (v in childA.columns.values) {
                                        v.close()
                                    }
                                }
                            }

                            override /*suspend*/ fun next(): DictionaryValueType {
                                return ColumnIteratorChildIteratorExt.nextHelper(query,
                                    this,
                                    {
                                        var done = false
                                        for (columnIndex in 0 until columnsINAO.size) {
                                            val value = columnsINAO[columnIndex].next()
                                            if (value == DictionaryValueHelper.nullValue) {
                                                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinCartesianProduct.kt:128"/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
                                                done = true
                                                for (v in childA.columns.values) {
                                                    v.close()
                                                }
                                                break
                                            }
                                            outO[0][columnIndex].addChild(ColumnIteratorRepeatValue(1, value))
                                        }
                                        if (!done) {
                                            for (columnIndex in 0 until columnsINBO.size) {
                                                outO[1][columnIndex].addChild(ColumnIteratorRepeatValue(1, DictionaryValueHelper.undefValue))
                                            }
                                        }
                                    },
                                    { __close() }
                                )
                            }
                        }
                        if (i < columns[1].size) {
                            outO[0].add(iterator)
                            outMap[columns[1][i]] = iterator
                        } else {
                            outO[1].add(iterator)
                            outMap[columns[2][i - columns[1].size]] = iterator
                        }
                    }
                } else {
                    for (v in childA.columns.values) {
                        v.close()
                    }
                    for (i in 0 until columns[1].size + columns[2].size) {
                        val iterator = ColumnIteratorChildIteratorEmpty(query)
                        if (i < columns[1].size) {
                            outO[0].add(iterator)
                            outMap[columns[1][i]] = iterator
                        } else {
                            outO[1].add(iterator)
                            outMap[columns[2][i - columns[1].size]] = iterator
                        }
                    }
                }
            } else {
                for (i in 0 until columns[1].size + columns[2].size) {
                    val iterator = object : ColumnIteratorChildIterator(query) {
                        override /*suspend*/ fun close() {
                            __close()
                        }

                        @Suppress("NOTHING_TO_INLINE")
                        /*suspend*/ inline fun __close() {
                            if (label != 0) {
                                _close()
                                for (v in childA.columns.values) {
                                    v.close()
                                }
                            }
                        }

                        override /*suspend*/ fun next(): DictionaryValueType {
                            return ColumnIteratorChildIteratorExt.nextHelper(
query,
                                this,
                                {
                                    var done = false
                                    for (columnIndex in 0 until columnsINAO.size) {
                                        val value = columnsINAO[columnIndex].next()
                                        if (value == DictionaryValueHelper.nullValue) {
                                            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/EvalJoinCartesianProduct.kt:196"/*SOURCE_FILE_END*/ }, { columnIndex == 0 })
                                            done = true
                                            for (v in childA.columns.values) {
                                                v.close()
                                            }
                                            break
                                        }
                                        outO[0][columnIndex].addChild(ColumnIteratorRepeatValue(count, value))
                                    }
                                    if (!done) {
                                        for (columnIndex in 0 until columnsINBO.size) {
                                            outO[1][columnIndex].addChild(ColumnIteratorMultiValue(data[columnIndex]))
                                        }
                                    }
                                },
                                { __close() }
                            )
                        }
                    }
                    if (i < columns[1].size) {
                        outO[0].add(iterator)
                        outMap[columns[1][i]] = iterator
                    } else {
                        outO[1].add(iterator)
                        outMap[columns[2][i - columns[1].size]] = iterator
                    }
                }
            }
            res = IteratorBundle(outMap)
        }
        return res
    }
}
