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
import lupos.operator.base.IPOPLimit
import lupos.operator.base.OPBase
import lupos.operator.physical.ITERATOR_DEBUG_MODE
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EOperatorIDExt
import lupos.shared.EPOPDebugModeExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.UnreachableException
import lupos.shared.myPrintStackTraceAndThrowAgain
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

public class POPDebug public constructor(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPDebugID, "POPDebug", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = getChildren()[0].getPartitionCount(variable)
    override fun equals(other: Any?): Boolean = other is POPDebug && getChildren()[0] == other.getChildren()[0]
    override fun cloneOP(): IOPBase = POPDebug(query, projectedVariables, getChildren()[0].cloneOP())
    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun getProvidedVariableNames(): List<String> = getChildren()[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> = (getChildren()[0] as POPBase).getProvidedVariableNamesInternal()
    override fun toSparql(): String = getChildren()[0].toSparql()
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val child0 = getChildren()[0] as POPBase
        val child = child0.evaluate(parent)
        when (ITERATOR_DEBUG_MODE) {
            EPOPDebugModeExt.NONE -> {
                return child
            }
            EPOPDebugModeExt.DEBUG1 -> {
                val target = child0.projectedVariables
                if (child.hasColumnMode()) {
                    try {
                        child.columns
                    } catch (e: Throwable) {
                        e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPDebug.kt:59"/*SOURCE_FILE_END*/)
                    }
                    val columnMode = mutableListOf<String>()
                    for (k in child.columns.keys) {
                        columnMode.add(k)
                    }
                    if (SanityCheck.enabled) { if (!(columnMode.containsAll(target))) { TODO("POPDebug.kt $uuid providing $columnMode, but should be $target") } }
                    if (SanityCheck.enabled) { if (!(target.containsAll(columnMode))) { TODO("POPDebug.kt $uuid providing $columnMode, but should be $target") } }
                    val outMap = mutableMapOf<String, ColumnIterator>()
                    for ((columnName, childIter) in child.columns) {
                        val iterator = object : ColumnIterator() {
                            override /*suspend*/ fun next(): DictionaryValueType {
                                var res = childIter.next()
// println("POPDebug.kt .. $uuid $columnName $res $parent")
                                return res
                            }

                            override /*suspend*/ fun nextSIP(minValue: DictionaryValueType, resultValue: DictionaryValueTypeArray, resultSkip: IntArray) {
                                childIter.nextSIP(minValue, resultValue, resultSkip)
                            }

                            override /*suspend*/ fun skipSIP(skipCount: Int): DictionaryValueType {
                                return childIter.skipSIP(skipCount)
                            }

                            override /*suspend*/ fun close() {
// println("POPDebug.kt .. close $uuid $columnName")
                                childIter.close()
                            }
                        }
                        outMap[columnName] = iterator
                    }
                    return IteratorBundle(outMap)
                } else if (child.hasRowMode()) {
                    val rowMode = child.rows.columns.toMutableList()
                    if (SanityCheck.enabled) { if (!(rowMode.containsAll(target))) { throw Exception("SanityCheck failed") } }
                    if (SanityCheck.enabled) { if (!(target.containsAll(rowMode))) { throw Exception("SanityCheck failed") } }
                }
                return child
            }
            EPOPDebugModeExt.DEBUG2 -> {
                val target = child0.projectedVariables
                when {
                    child.hasColumnMode() -> {
                        try {
                            child.columns
                        } catch (e: Throwable) {
                            e.myPrintStackTraceAndThrowAgain(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPDebug.kt:106"/*SOURCE_FILE_END*/)
                        }
                        val outMap = mutableMapOf<String, ColumnIterator>()
                        val columnMode = mutableListOf<String>()
                        for ((k, v) in child.columns) {
                            columnMode.add(k)
                            var counter = 0
                            val iterator = object : ColumnIterator() {
                                @JvmField
                                var label = 1
                                override /*suspend*/ fun next(): DictionaryValueType {
                                    return if (label != 0) {
                                        val res = v.next()
                                        if (res == DictionaryValueHelper.nullValue) {
                                        } else {
                                            counter++
                                        }
                                        res
                                    } else {
                                        DictionaryValueHelper.nullValue
                                    }
                                }

                                override /*suspend*/ fun nextSIP(minValue: DictionaryValueType, resultValue: DictionaryValueTypeArray, resultSkip: IntArray) {
                                    if (label != 0) {
                                        v.nextSIP(minValue, resultValue, resultSkip)
                                        val res = resultValue[0]
                                        if (res == DictionaryValueHelper.nullValue) {
                                        } else {
                                            counter++
                                        }
                                    } else {
                                        resultSkip[0] = 0
                                        resultValue[0] = DictionaryValueHelper.nullValue
                                    }
                                }

                                override /*suspend*/ fun skipSIP(skipCount: Int): DictionaryValueType {
                                    return if (label != 0) {
                                        val res = v.skipSIP(skipCount)
                                        if (res == DictionaryValueHelper.nullValue) {
                                        } else {
                                            counter++
                                        }
                                        res
                                    } else {
                                        DictionaryValueHelper.nullValue
                                    }
                                }

                                override /*suspend*/ fun close() {
                                    if (label != 0) {
                                        label = 0
                                        v.close()
                                    }
                                }
                            }
                            outMap[k] = iterator
                        }
                        if (SanityCheck.enabled) { if (!(columnMode.containsAll(target))) { throw Exception("SanityCheck failed") } }
                        if (SanityCheck.enabled) { if (!(target.containsAll(columnMode))) { throw Exception("SanityCheck failed") } }
                        return IteratorBundle(outMap)
                    }
                    child.hasRowMode() -> {
                        val rowMode = child.rows.columns.toMutableList()
                        if (SanityCheck.enabled) { if (!(rowMode.containsAll(target))) { throw Exception("SanityCheck failed") } }
                        if (SanityCheck.enabled) { if (!(target.containsAll(rowMode))) { throw Exception("SanityCheck failed") } }
                        val iterator = RowIterator()
                        var counter = 0
                        iterator.columns = child.rows.columns
                        iterator.next = {
                            val res = child.rows.next()
                            iterator.buf = child.rows.buf
                            if (res < 0) {
                            } else {
                                counter++
                            }
                            res
                        }
                        iterator.close = {
                            child.rows.close()
                            iterator._close()
                        }
                        return IteratorBundle(iterator)
                    }
                    else -> {
                        return child
                    }
                }
            }
            else -> {
                throw UnreachableException()
            }
        }
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): OPBase? = (children[0] as POPBase).toLocalOperatorGraph(parent, onFoundLimit, onFoundSort)
}
