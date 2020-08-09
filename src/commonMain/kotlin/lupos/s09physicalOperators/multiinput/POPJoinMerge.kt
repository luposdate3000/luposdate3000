package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIteratorEmpty
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPJoinMerge(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeID, "POPJoinMerge", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql() = children[0].toSparql() + children[1].toSparql()
    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMerge(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
    override fun equals(other: Any?) = other is POPJoinMerge && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { !optional }
        //setup columns
        SanityCheck.println({ "$uuid open $classname" })
        val child0 = children[0].evaluate(parent)
        val child1 = children[1].evaluate(parent)
        val columnsINO0 = mutableListOf<ColumnIterator>()
        val columnsINO1 = mutableListOf<ColumnIterator>()
        val columnsINJ0 = mutableListOf<ColumnIterator>()
        val columnsINJ1 = mutableListOf<ColumnIterator>()
        val columnsOUT0 = mutableListOf<ColumnIteratorChildIterator>()
        val columnsOUT1 = mutableListOf<ColumnIteratorChildIterator>()
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>() //Key_in_outMap, which_outIteratorsCounter (J,O0,O1,none)
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(children[1].getProvidedVariableNames())
        for (name in children[0].getProvidedVariableNames()) {
            if (tmp.contains(name)) {
                if (projectedVariables.contains(name)) {
                    outIterators.add(Pair(name, 0))
                    columnsINJ0.add(0, child0.columns[name]!!)
                    columnsINJ1.add(0, child1.columns[name]!!)
                } else {
                    columnsINJ0.add(child0.columns[name]!!)
                    columnsINJ1.add(child1.columns[name]!!)
                }
                tmp.remove(name)
            } else {
                outIterators.add(Pair(name, 1))
                columnsINO0.add(child0.columns[name]!!)
            }
        }
        for (name in tmp) {
            outIterators.add(Pair(name, 2))
            columnsINO1.add(child1.columns[name]!!)
        }
        SanityCheck.check { columnsINJ0.size > 0 }
        SanityCheck.check { columnsINJ0.size == columnsINJ1.size }
        val emptyColumnsWithJoin = outIterators.size == 0
        if (emptyColumnsWithJoin) {
            outIterators.add(Pair("", 3))
        }
        val key0 = IntArray(columnsINJ0.size) { columnsINJ0[it].next() }
        val key1 = IntArray(columnsINJ1.size) { columnsINJ1[it].next() }
        val done = findNextKey(key0, key1, columnsINJ0, columnsINJ1, columnsINO0, columnsINO1)
        if (done) {
            SanityCheck.println({ "$uuid close $classname" })
            for (closeIndex in 0 until columnsINJ0.size) {
                columnsINJ0[closeIndex].close()
            }
            for (closeIndex in 0 until columnsINJ1.size) {
                columnsINJ1[closeIndex].close()
            }
            for (closeIndex in 0 until columnsINO0.size) {
                columnsINO0[closeIndex].close()
            }
            for (closeIndex in 0 until columnsINO1.size) {
                columnsINO1[closeIndex].close()
            }
            for (iteratorConfig in outIterators) {
                outMap[iteratorConfig.first] = ColumnIteratorEmpty()
            }
            for (iteratorConfig in outIterators) {
                val iterator = ColumnIteratorChildIteratorEmpty()
                when (iteratorConfig.second) {
                    0 -> {
                        outMap[iteratorConfig.first] = iterator
                        columnsOUTJ.add(iterator)
                    }
                    1 -> {
                        outMap[iteratorConfig.first] = iterator
                        columnsOUT0.add(iterator)
                    }
                    2 -> {
                        outMap[iteratorConfig.first] = iterator
                        columnsOUT1.add(iterator)
                    }
                    3 -> {
                        columnsOUTJ.add(iterator)
                    }
                }
            }
        } else {
            val outIteratorsAllocated = mutableListOf<ColumnIteratorChildIterator>()
            val keyCopy = IntArray(columnsINJ0.size) { key0[it] }
            for (iteratorConfig in outIterators) {
                val iterator = object : ColumnIteratorChildIterator() {
                    @JvmField
                    val data0 = Array(columnsINO0.size) { IntArray(100) }

                    @JvmField
                    val data1 = Array(columnsINO1.size) { IntArray(100) }
                    suspend inline fun __close() {
                        if (label != 0) {
                            for (iterator2 in outIteratorsAllocated) {
                                iterator2.closeOnNoMoreElements()
                            }
                            for (closeIndex in 0 until columnsINJ0.size) {
                                columnsINJ0[closeIndex].close()
                            }
                            for (closeIndex in 0 until columnsINJ1.size) {
                                columnsINJ1[closeIndex].close()
                            }
                            for (closeIndex in 0 until columnsINO0.size) {
                                columnsINO0[closeIndex].close()
                            }
                            for (closeIndex in 0 until columnsINO1.size) {
                                columnsINO1[closeIndex].close()
                            }
                        }
                    }

                    override suspend fun close() {
                        SanityCheck.println({ "$uuid close $classname" })
                        _close()
                        __close()
                    }

                    override suspend fun next(): Value {
                        return next_helper {
                            for (i in 0 until columnsINJ0.size) {
                                keyCopy[i] = key0[i]
                            }
                            val countA = sameElements(key0, keyCopy, columnsINJ0, columnsINO0, data0)
                            val countB = sameElements(key1, keyCopy, columnsINJ1, columnsINO1, data1)
                            val done2 = findNextKey(key0, key1, columnsINJ0, columnsINJ1, columnsINO0, columnsINO1)
                            if (done2) {
                                __close()
                            }
                            POPJoin.crossProduct(data0, data1, keyCopy, columnsOUT0, columnsOUT1, columnsOUTJ, countA, countB)
                        }
                    }
                }
                when (iteratorConfig.second) {
                    0 -> {
                        outMap[iteratorConfig.first] = iterator
                        columnsOUTJ.add(iterator)
                    }
                    1 -> {
                        outMap[iteratorConfig.first] = iterator
                        columnsOUT0.add(iterator)
                    }
                    2 -> {
                        outMap[iteratorConfig.first] = iterator
                        columnsOUT1.add(iterator)
                    }
                    3 -> {
                        columnsOUTJ.add(iterator)
                    }
                }
                outIteratorsAllocated.add(iterator)
            }
        }
        val res: IteratorBundle
        if (emptyColumnsWithJoin) {
            res = object : IteratorBundle(0) {
                @JvmField
                val columnsINJ00 = columnsINJ0
                @JvmField
                val columnsINJ01 = columnsINJ1
                @JvmField
                val columnsOUTJ00 = columnsOUTJ[0]
                override suspend fun hasNext2(): Boolean {
                    val tmp = columnsOUTJ00.next() != ResultSetDictionary.nullValue
                    if (!tmp) {
                        _hasNext2Close()
                    }
                    return tmp
                }

                suspend inline fun _hasNext2Close() {
                    SanityCheck.println({ "$uuid close $classname" })
                    for (closeIndex in 0 until columnsINJ00.size) {
                        columnsINJ00[closeIndex].close()
                    }
                    for (closeIndex in 0 until columnsINJ01.size) {
                        columnsINJ01[closeIndex].close()
                    }
                }

                suspend override fun hasNext2Close() {
                    _hasNext2Close()
                }
            }
        } else {
            res = IteratorBundle(outMap)
        }
        return res
    }

    suspend fun sameElements(key: IntArray, keyCopy: IntArray, columnsINJ: MutableList<ColumnIterator>, columnsINO: MutableList<ColumnIterator>, data: Array<IntArray>): Int {
        var count = 0
        SanityCheck.check { keyCopy[0] != ResultSetDictionary.nullValue }
        loop@ while (true) {
            if (count >= data[0].size) {
                for (i in 0 until data.size) {
                    val x = data[i]
                    val d = IntArray(count * 2) {
                        val res: Int
                        if (it < count) {
                            res = x[it]
                        } else {
                            res = 0
                        }
                        /*return*/res
                    }
                    data[i] = d
                }
            }
            for (i in 0 until columnsINO.size) {
                data[i][count] = columnsINO[i].next()
            }
            for (i in 0 until columnsINJ.size) {
                key[i] = columnsINJ[i].next()
                SanityCheck.check { key[i] != ResultSetDictionary.undefValue }
            }
            count++
            for (i in 0 until columnsINJ.size) {
                if (key[i] != keyCopy[i]) {
                    break@loop
                }
            }
        }
        return count
    }

    suspend inline fun findNextKey(key0: IntArray, key1: IntArray, columnsINJ0: MutableList<ColumnIterator>, columnsINJ1: MutableList<ColumnIterator>, columnsINO0: MutableList<ColumnIterator>, columnsINO1: MutableList<ColumnIterator>): Boolean {
        var done = true
        if (key0[0] != ResultSetDictionary.nullValue && key1[0] != ResultSetDictionary.nullValue) {
            done = false
            loop@ while (true) {
                for (i in 0 until columnsINJ0.size) {
                    val a = key0[i]
                    val b = key1[i]
                    if (a < b) {
                        for (j in 0 until columnsINO0.size) {
                            columnsINO0[j].next()
                        }
                        for (j in 0 until columnsINJ0.size) {
                            key0[j] = columnsINJ0[j].next()
                            SanityCheck.check { key0[j] != ResultSetDictionary.undefValue }
                            done = key0[j] == ResultSetDictionary.nullValue
                            if (done) {
                                SanityCheck.check { j == 0 }
                                break@loop
                            }
                        }
                        continue@loop
                    } else if (a > b) {
                        for (j in 0 until columnsINO1.size) {
                            columnsINO1[j].next()
                        }
                        for (j in 0 until columnsINJ1.size) {
                            key1[j] = columnsINJ1[j].next()
                            SanityCheck.check { key1[j] != ResultSetDictionary.undefValue }
                            done = key1[j] == ResultSetDictionary.nullValue
                            if (done) {
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
        return done
    }
}
