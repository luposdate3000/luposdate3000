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
package lupos.s09physicalOperators.singleinput

import lupos.dictionary.DictionaryExt
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.EPOPDebugModeExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.ITERATOR_DEBUG_MODE
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.UnreachableException
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s09physicalOperators.POPBase
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
        val child = getChildren()[0].evaluate(parent)
        when (ITERATOR_DEBUG_MODE) {
            EPOPDebugModeExt.NONE -> {
                return child
            }
            EPOPDebugModeExt.DEBUG1 -> {
                val target = getChildren()[0].getProvidedVariableNames()
                SanityCheck.println { "POPDebug-child-mode ... $uuid ${getChildren()[0].getUUID()}" }
                if (child.hasColumnMode()) {
                    try {
                        child.columns
                    } catch (e: Throwable) {
                        e.printStackTrace()
                        SanityCheck.println { "debugchildclassname::" + getChildren()[0].getClassname() }
                        throw e
                    }
                    val columnMode = mutableListOf<String>()
                    for (k in child.columns.keys) {
                        columnMode.add(k)
                    }
                    SanityCheck.check { columnMode.containsAll(target) }
                    SanityCheck.check { target.containsAll(columnMode) }
                } else if (child.hasRowMode()) {
                    val rowMode = child.rows.columns.toMutableList()
                    SanityCheck.check { rowMode.containsAll(target) }
                    SanityCheck.check { target.containsAll(rowMode) }
                }
                return child
            }
            EPOPDebugModeExt.DEBUG2 -> {
                val target = getChildren()[0].getProvidedVariableNames()
                SanityCheck.println { "POPDebug-child-mode ... $uuid ${getChildren()[0].getUUID()}" }
                when {
                    child.hasColumnMode() -> {
                        try {
                            child.columns
                        } catch (e: Throwable) {
                            e.printStackTrace()
                            SanityCheck.println { "debugchildclassname2::" + getChildren()[0].getClassname() }
                            throw e
                        }
                        val outMap = mutableMapOf<String, ColumnIterator>()
                        val columnMode = mutableListOf<String>()
                        for ((k, v) in child.columns) {
                            columnMode.add(k)
                            var counter = 0
                            SanityCheck.println { "$uuid $k opened" }
                            val iterator = object : ColumnIterator() {
                                @JvmField
                                var label = 1
                                override /*suspend*/ fun next(): Int {
                                    return if (label != 0) {
                                        SanityCheck.println { "$uuid $k next call" }
                                        val res = v.next()
                                        if (res == DictionaryExt.nullValue) {
                                            SanityCheck.println { "$uuid $k next return closed $counter ${parent.data} DictionaryExt.nullValue" }
                                        } else {
                                            counter++
                                            SanityCheck.println { "$uuid $k next return $counter ${parent.data} ${res.toString(16)}" }
                                        }
                                        res
                                    } else {
                                        DictionaryExt.nullValue
                                    }
                                }

                                override /*suspend*/ fun nextSIP(minValue: Int, result: IntArray) {
                                    if (label != 0) {
                                        SanityCheck.println { "$uuid $k next call minValue SIP" }
                                        v.nextSIP(minValue, result)
                                        val res = result[1]
                                        if (res == DictionaryExt.nullValue) {
                                            SanityCheck.println { "$uuid $k next return closed $counter ${parent.data} DictionaryExt.nullValue" }
                                        } else {
                                            counter++
                                            SanityCheck.println { "$uuid $k next return $counter ${parent.data} ${res.toString(16)}" }
                                        }
                                    } else {
                                        result[0] = 0
                                        result[1] = DictionaryExt.nullValue
                                    }
                                }

                                override /*suspend*/ fun skipSIP(skipCount: Int): Int {
                                    return if (label != 0) {
                                        SanityCheck.println { "$uuid $k next call skip SIP" }
                                        val res = v.skipSIP(skipCount)
                                        if (res == DictionaryExt.nullValue) {
                                            SanityCheck.println { "$uuid $k next return closed $counter ${parent.data} DictionaryExt.nullValue" }
                                        } else {
                                            counter++
                                            SanityCheck.println { "$uuid $k next return $counter ${parent.data} ${res.toString(16)}" }
                                        }
                                        res
                                    } else {
                                        DictionaryExt.nullValue
                                    }
                                }

                                override /*suspend*/ fun close() {
                                    if (label != 0) {
                                        label = 0
                                        SanityCheck.println { "$uuid $k closed $counter ${parent.data}" }
                                        v.close()
                                    }
                                }
                            }
                            outMap[k] = iterator
                        }
                        SanityCheck.check { columnMode.containsAll(target) }
                        SanityCheck.check({ target.containsAll(columnMode) }, { "$uuid $target $columnMode" })
                        return IteratorBundle(outMap)
                    }
                    child.hasRowMode() -> {
                        val rowMode = child.rows.columns.toMutableList()
                        SanityCheck.check { rowMode.containsAll(target) }
                        SanityCheck.check { target.containsAll(rowMode) }
                        val iterator = RowIterator()
                        var counter = 0
                        iterator.columns = child.rows.columns
                        iterator.next = {
                            SanityCheck.println { "$uuid next call" }
                            val res = child.rows.next()
                            iterator.buf = child.rows.buf
                            if (res < 0) {
                                SanityCheck.println { "$uuid next return closed $counter ${parent.data} DictionaryExt.nullValue" }
                            } else {
                                counter++
                                SanityCheck.println { "$uuid next return $counter ${parent.data} ${iterator.buf.map { it.toString(16) }}" }
                            }
                            res
                        }
                        iterator.close = {
                            SanityCheck.println { "$uuid closed $counter ${parent.data}" }
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
}
