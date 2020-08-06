package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
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
import lupos.s04logicalOperators.iterator.ColumnIteratorEmpty
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPJoinMerge(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeID, "POPJoinMerge", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?) = other is POPJoinMerge && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { !optional }
//setup columns
        SanityCheck.println({ "$uuid open $classname" })
        val child = Array(2) { children[it].evaluate(parent) }
        val columnsINO = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsINJ = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsOUT = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>() //Key_in_outMap, which_outIteratorsCounter (J,O0,O1,none)
        var outIteratorsCounterJ = 0
        var outIteratorsCounterO0 = 0
        var outIteratorsCounterO1 = 0
        val outIteratorsAllocated = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(children[1].getProvidedVariableNames())
        var t: ColumnIteratorChildIterator
        for (name in children[0].getProvidedVariableNames()) {
            if (tmp.contains(name)) {
                if (projectedVariables.contains(name)) {
                    outIterators.add(Pair(name, 0))
                    outIteratorsCounterJ++
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
                outIteratorsCounterO0++
                columnsINO[0].add(child[0].columns[name]!!)
            }
        }
        for (name in tmp) {
            outIterators.add(Pair(name, 2))
            columnsINO[1].add(child[1].columns[name]!!)
        }
        SanityCheck.check { columnsINJ[0].size > 0 }
        SanityCheck.check { columnsINJ[0].size == columnsINJ[1].size }
        var emptyColumnsWithJoin = (outIteratorsCounterJ + outIteratorsCounterO0 + outIteratorsCounterO1) == 0
        if (emptyColumnsWithJoin) {
            outIterators.add(Pair("", 3))
        }
        val key = Array(2) { i -> Array(columnsINJ[i].size) { columnsINJ[i][it].next() } }
        var done = findNextKey(key, columnsINJ, columnsINO)
        if (done) {
            SanityCheck.println({ "$uuid close $classname" })
            for (closeIndex2 in 0 until 2) {
                for (closeIndex in 0 until columnsINJ[closeIndex2].size) {
                    columnsINJ[closeIndex2][closeIndex].close()
                }
                for (closeIndex in 0 until columnsINO[closeIndex2].size) {
                    columnsINO[closeIndex2][closeIndex].close()
                }
            }
            for (iteratorConfig in outIterators) {
                outMap[iteratorConfig.first] = ColumnIteratorEmpty()
            }
        } else {
            val keyCopy = Array(columnsINJ[0].size) { key[0][it] }
            for (iteratorConfig in outIterators) {
                val iterator = object : ColumnIteratorChildIterator() {
                    inline fun __close() {
                        if (label != 0) {
                            _close()
                            SanityCheck.println({ "$uuid close $classname" })
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

                    override fun close() {
                        __close()
                    }

                    override fun next(): Value? {
                        return next_helper {
                            for (i in 0 until columnsINJ[0].size) {
                                keyCopy[i] = key[0][i]
                            }
                            val data = Array(2) { Array(columnsINO[it].size) { MyListValue() } }
                            val countA = sameElements(key[0], keyCopy, columnsINJ[0], columnsINO[0], data[0])
                            val countB = sameElements(key[1], keyCopy, columnsINJ[1], columnsINO[1], data[1])
                            findNextKey(key, columnsINJ, columnsINO)
                            if (key[0][0] == null || key[1][0] == null) {
                                for (iterator2 in outIteratorsAllocated) {
                                    iterator2.closeOnNoMoreElements()
                                }
                                SanityCheck.println({ "$uuid close $classname" })
                                for (closeIndex2 in 0 until 2) {
                                    for (closeIndex in 0 until columnsINJ[closeIndex2].size) {
                                        columnsINJ[closeIndex2][closeIndex].close()
                                    }
                                    for (closeIndex in 0 until columnsINO[closeIndex2].size) {
                                        columnsINO[closeIndex2][closeIndex].close()
                                    }
                                }
                            }
                            POPJoin.crossProduct(data, keyCopy, columnsOUT, columnsOUTJ, countA, countB)
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
                        columnsOUT[0].add(iterator)
                    }
                    2 -> {
                        outMap[iteratorConfig.first] = iterator
                        columnsOUT[1].add(iterator)
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
            res = IteratorBundle(0)
            res.hasNext2 = {
                val tmp = columnsOUTJ[0].next() != null
                if (!tmp) {
                    SanityCheck.println({ "$uuid close $classname" })
                    for (closeIndex2 in 0 until 2) {
                        for (closeIndex in 0 until columnsINJ[closeIndex2].size) {
                            columnsINJ[closeIndex2][closeIndex].close()
                        }
                        for (closeIndex in 0 until columnsINO[closeIndex2].size) {
                            columnsINO[closeIndex2][closeIndex].close()
                        }
                    }
                }
/*return*/tmp
            }
            res.hasNext2Close = {
                for (closeIndex2 in 0 until 2) {
                    for (closeIndex in 0 until columnsINJ[closeIndex2].size) {
                        columnsINJ[closeIndex2][closeIndex].close()
                    }
                    for (closeIndex in 0 until columnsINO[closeIndex2].size) {
                        columnsINO[closeIndex2][closeIndex].close()
                    }
                }
            }
        } else {
            res = IteratorBundle(outMap)
        }
        return res
    }

    /*inline*/  fun sameElements(key: Array<Value?>, keyCopy: Array<Value?>, columnsINJ: MutableList<ColumnIterator>, columnsINO: MutableList<ColumnIterator>, data: Array<MyListValue>): Int {
        var count = 0
        SanityCheck.check { keyCopy[0] != null }
        loop@ while (true) {
            count++
            for (i in 0 until columnsINO.size) {
                data[i].add(columnsINO[i].next()!!)
            }
            for (i in 0 until columnsINJ.size) {
                key[i] = columnsINJ[i].next()
                SanityCheck.check { key[i] != ResultSetDictionary.undefValue }
            }
            for (i in 0 until columnsINJ.size) {
                if (key[i] != keyCopy[i]) {
                    break@loop
                }
            }
        }
        return count
    }

    /*inline*/ fun findNextKey(key: Array<Array<Value?>>, columnsINJ: Array<MutableList<ColumnIterator>>, columnsINO: Array<MutableList<ColumnIterator>>): Boolean {
        var done = true
        if (key[0][0] != null && key[1][0] != null) {
            done = false
            loop@ while (true) {
                for (i in 0 until columnsINJ[0].size) {
                    val a = key[0][i]!!
                    val b = key[1][i]!!
                    if (a < b) {
                        for (j in 0 until columnsINO[0].size) {
                            columnsINO[0][j].next()
                        }
                        for (j in 0 until columnsINJ[0].size) {
                            key[0][j] = columnsINJ[0][j].next()
                            SanityCheck.check { key[0][j] != ResultSetDictionary.undefValue }
                            done = key[0][j] == null
                            if (done) {
                                SanityCheck.check { j == 0 }
                                break@loop
                            }
                        }
                        continue@loop
                    } else if (a > b) {
                        for (j in 0 until columnsINO[1].size) {
                            columnsINO[1][j].next()
                        }
                        for (j in 0 until columnsINJ[1].size) {
                            key[1][j] = columnsINJ[1][j].next()
                            SanityCheck.check { key[1][j] != ResultSetDictionary.undefValue }
                            done = key[1][j] == null
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

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMerge(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
