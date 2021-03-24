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
package lupos.s09physicalOperators.multiinput

import lupos.dictionary.DictionaryExt
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIteratorEmpty
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField

public class POPJoinMergeOptional public constructor(query: IQuery, projectedVariables: List<String>, childA: IOPBase, childB: IOPBase, @JvmField public val optional: Boolean) : POPBase(query, projectedVariables, EOperatorIDExt.POPJoinMergeOptionalID, "POPJoinMergeOptional", arrayOf(childA, childB), ESortPriorityExt.JOIN) {
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

    override fun equals(other: Any?): Boolean = other is POPJoinMergeOptional && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck {
            for (v in children[0].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
            for (v in children[1].getProvidedVariableNames()) {
                getPartitionCount(v)
            }
        }
        SanityCheck.check { optional }
// setup columns
        val child = ArrayAllocator(2) { children[it].evaluate(parent) }
        val columnsINO = ArrayAllocator(2) { mutableListOf<ColumnIterator>() }
        val columnsINJ = ArrayAllocator(2) { mutableListOf<ColumnIterator>() }
        val columnsOUT = ArrayAllocator(2) { mutableListOf<ColumnIteratorChildIterator>() }
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>() // J,O0,O1,J-ohne-Map
        val outIteratorsAllocated = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(children[1].getProvidedVariableNames())
        for (name in children[0].getProvidedVariableNames()) {
            if (tmp.contains(name)) {
                if (projectedVariables.contains(name)) {
                    outIterators.add(Pair(name, 0))
                    for (i in 0 until 2) {
                        columnsINJ[i].add(0, child[i].columns[name]!!)
                    }
                } else {
                    for (i in 0 until 2) {
                        columnsINJ[i].add(child[i].columns[name]!!)
                    }
                }
                tmp.remove(name)
            } else {
                outIterators.add(Pair(name, 1))
                columnsINO[0].add(child[0].columns[name]!!)
            }
        }
        for (name in tmp) {
            outIterators.add(Pair(name, 2))
            columnsINO[1].add(child[1].columns[name]!!)
        }
        SanityCheck.check { columnsINJ[0].size > 0 }
        SanityCheck.check { columnsINJ[0].size == columnsINJ[1].size }
        val emptyColumnsWithJoin = outIterators.size == 0
        if (emptyColumnsWithJoin) {
            outIterators.add(Pair("", 3))
        }
        val key = ArrayAllocator(2) { i -> IntArray(columnsINJ[i].size) { columnsINJ[i][it].next() } }
        var done = findNextKey(key, columnsINJ, columnsINO)
        if (done) {
            for (closeIndex2 in 0 until 2) {
                for (closeIndex in 0 until columnsINJ[closeIndex2].size) {
                    columnsINJ[closeIndex2][closeIndex].close()
                }
                for (closeIndex in 0 until columnsINO[closeIndex2].size) {
                    columnsINO[closeIndex2][closeIndex].close()
                }
            }
            for ((first, second) in outIterators) {
                val iterator = ColumnIteratorChildIteratorEmpty()
                outIteratorsAllocated.add(iterator)
                when (second) {
                    0 -> {
                        columnsOUTJ.add(iterator)
                        outMap[first] = iterator
                    }
                    1 -> {
                        columnsOUT[0].add(iterator)
                        outMap[first] = iterator
                    }
                    2 -> {
                        columnsOUT[1].add(iterator)
                        outMap[first] = iterator
                    }
                    3 -> {
                        columnsOUTJ.add(iterator)
                    }
                }
            }
        } else {
            val keyCopy = IntArray(columnsINJ[0].size) { key[0][it] }
            for ((first, second) in outIterators) {
                val iterator = object : ColumnIteratorChildIterator() {
                    override /*suspend*/ fun close() {
                        _close()
                    }

                    override /*suspend*/ fun next(): Int {
                        return nextHelper(
                            {
                                for (i in 0 until columnsINJ[0].size) {
                                    keyCopy[i] = key[0][i]
                                }
                                val data = ArrayAllocator(2) { ArrayAllocator(columnsINO[it].size) { mutableListOf<Int>() } }
                                val countA = sameElements(key[0], keyCopy, columnsINJ[0], columnsINO[0], data[0])
                                val countB = sameElements(key[1], keyCopy, columnsINJ[1], columnsINO[1], data[1])
                                done = findNextKey(key, columnsINJ, columnsINO)
                                if (done) {
                                    for (iterator2 in outIteratorsAllocated) {
                                        iterator2.closeOnNoMoreElements()
                                    }
                                    for (closeIndex2 in 0 until 2) {
                                        for (closeIndex in 0 until columnsINJ[closeIndex2].size) {
                                            columnsINJ[closeIndex2][closeIndex].close()
                                        }
                                        for (closeIndex in 0 until columnsINO[closeIndex2].size) {
                                            columnsINO[closeIndex2][closeIndex].close()
                                        }
                                    }
                                }
                                POPJoin.crossProduct(data[0], data[1], keyCopy, columnsOUT[0], columnsOUT[1], columnsOUTJ, countA, countB)
                            },
                            { _close() }
                        )
                    }
                }
                outIteratorsAllocated.add(iterator)
                when (second) {
                    0 -> {
                        columnsOUTJ.add(iterator)
                        outMap[first] = iterator
                    }
                    1 -> {
                        columnsOUT[0].add(iterator)
                        outMap[first] = iterator
                    }
                    2 -> {
                        columnsOUT[1].add(iterator)
                        outMap[first] = iterator
                    }
                    3 -> {
                        columnsOUTJ.add(iterator)
                    }
                }
            }
        }
        var res = IteratorBundle(outMap)
        if (emptyColumnsWithJoin) {
            res = object : IteratorBundle(0) {
                override /*suspend*/ fun hasNext2(): Boolean {
                    return columnsOUTJ[0].next() != DictionaryExt.nullValue
                }

                override /*suspend*/ fun hasNext2Close() {
                    for (closeIndex2 in 0 until 2) {
                        for (closeIndex in 0 until columnsINJ[closeIndex2].size) {
                            columnsINJ[closeIndex2][closeIndex].close()
                        }
                        for (closeIndex in 0 until columnsINO[closeIndex2].size) {
                            columnsINO[closeIndex2][closeIndex].close()
                        }
                    }
                }
            }
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun sameElements(key: IntArray, keyCopy: IntArray, columnsINJ: MutableList<ColumnIterator>, columnsINO: MutableList<ColumnIterator>, data: Array<MutableList<Int>>): Int {
        SanityCheck.check { keyCopy[0] != DictionaryExt.nullValue }
        for (i in 0 until columnsINJ.size) {
            if (key[i] != keyCopy[i]) {
                /* this is an optional element without a match */
                for (j in 0 until columnsINO.size) {
                    data[j].add(DictionaryExt.undefValue)
                }
                return 1
            }
        }
        var count = 0
        /* at least 1 matching row */
        loop@ while (true) {
            count++
            for (i in 0 until columnsINO.size) {
                data[i].add(columnsINO[i].next())
            }
            for (i in 0 until columnsINJ.size) {
                key[i] = columnsINJ[i].next()
                SanityCheck.check { key[i] != DictionaryExt.undefValue }
            }
            for (i in 0 until columnsINJ.size) {
                if (key[i] != keyCopy[i]) {
                    break@loop
                }
            }
        }
        return count
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun findNextKey(key: Array<IntArray>, columnsINJ: Array<MutableList<ColumnIterator>>, columnsINO: Array<MutableList<ColumnIterator>>): Boolean {
        if (key[0][0] != DictionaryExt.nullValue && key[1][0] != DictionaryExt.nullValue) {
            loop@ while (true) {
                for (i in 0 until columnsINJ[0].size) {
                    val a = key[0][i]
                    val b = key[1][i]
                    if (a > b) {
                        for (j in 0 until columnsINO[1].size) {
                            columnsINO[1][j].next()
                        }
                        for (j in 0 until columnsINJ[1].size) {
                            key[1][j] = columnsINJ[1][j].next()
                            SanityCheck.check { key[1][j] != DictionaryExt.undefValue }
                            if (key[1][j] == DictionaryExt.nullValue) {
                                SanityCheck.check { j == 0 }
                                break@loop
                            }
                        }
                        continue@loop
                    }
                }
                break@loop
            }
        }
        return key[0][0] == DictionaryExt.nullValue
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("optional", "" + optional)
    override fun cloneOP(): IOPBase = POPJoinMergeOptional(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
