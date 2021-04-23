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

import lupos.operator.base.iterator.ColumnIteratorChildIterator
import lupos.operator.base.iterator.ColumnIteratorChildIteratorEmpty
import lupos.operator.base.iterator.ColumnIteratorMultiValue
import lupos.operator.base.iterator.ColumnIteratorRepeatIterator
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.operator.base.multiinput.LOPJoin_Helper
import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.ColumnIteratorEmpty
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPJoinCartesianProduct public constructor(query: IQuery, projectedVariables: List<String>, childA: IOPBase, childB: IOPBase, @JvmField public val optional: Boolean) : POPBase(query, projectedVariables, EOperatorIDExt.POPJoinCartesianProductID, "POPJoinCartesianProduct", arrayOf(childA, childB), ESortPriorityExt.JOIN) {
    override fun getPartitionCount(variable: String): Int {
        return if (children[0].getProvidedVariableNames().contains(variable)) {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                SanityCheck.check { children[0].getPartitionCount(variable) == children[1].getPartitionCount(variable) }
                children[0].getPartitionCount(variable)
            } else {
                children[0].getPartitionCount(variable)
            }
        } else {
            if (children[1].getProvidedVariableNames().contains(variable)) {
                children[1].getPartitionCount(variable)
            } else {
                throw Exception("unknown variable $variable")
            }
        }
    }

    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?): Boolean = other is POPJoinCartesianProduct && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val columns = LOPJoin_Helper.getColumns(children[0].getProvidedVariableNames(), children[1].getProvidedVariableNames())
        SanityCheck {
            for (v in children[0].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
            for (v in children[1].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
        }
        SanityCheck.check { columns[0].size == 0 }
        val childA = children[0].evaluate(parent)
        val childB = children[1].evaluate(parent)
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
            val data = Array(columnsINBO.size) { mutableListOf<Int>() }
            loopC@ while (true) {
                for (columnIndex in 0 until columnsINBO.size) {
                    val value = columnsINBO[columnIndex].next()
                    if (value == DictionaryExt.nullValue) {
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
                        val iterator = object : ColumnIteratorChildIterator() {
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

                            override /*suspend*/ fun next(): Int {
                                return nextHelper(
                                    {
                                        var done = false
                                        for (columnIndex in 0 until columnsINAO.size) {
                                            val value = columnsINAO[columnIndex].next()
                                            if (value == DictionaryExt.nullValue) {
                                                SanityCheck.check { columnIndex == 0 }
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
                                                outO[1][columnIndex].addChild(ColumnIteratorRepeatValue(1, DictionaryExt.undefValue))
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
                        val iterator = ColumnIteratorChildIteratorEmpty()
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
                    val iterator = object : ColumnIteratorChildIterator() {
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

                        override /*suspend*/ fun next(): Int {
                            return nextHelper(
                                {
                                    var done = false
                                    for (columnIndex in 0 until columnsINAO.size) {
                                        val value = columnsINAO[columnIndex].next()
                                        if (value == DictionaryExt.nullValue) {
                                            SanityCheck.check { columnIndex == 0 }
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

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("optional", "" + optional)
    override fun cloneOP(): IOPBase = POPJoinCartesianProduct(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
