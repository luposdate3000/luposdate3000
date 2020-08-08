package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
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
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPJoinMergeOptional(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeOptionalID, "POPJoinMergeOptional", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?) = other is POPJoinMergeOptional && optional == other.optional && children[0] == other.children[0] && children[1] == other.children[1]
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        SanityCheck.check { optional }
//setup columns
        val child = Array(2) { children[it].evaluate(parent) }
        val columnsINO = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsINJ = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsOUT = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<Pair<String, Int>>() //J,O0,O1,J-ohne-Map
        val outIteratorsAllocated = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(children[1].getProvidedVariableNames())
        var t: ColumnIteratorChildIterator
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
        var emptyColumnsWithJoin = outIterators.size == 0
        if (emptyColumnsWithJoin) {
            outIterators.add(Pair("", 3))
        }
        val key = Array(2) { i -> Array(columnsINJ[i].size) { columnsINJ[i][it].next() } }
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
        } else {
            val keyCopy = Array(columnsINJ[0].size) { key[0][it] }
            for (iteratorConfig in outIterators) {
                val iterator = object : ColumnIteratorChildIterator() {
                    override suspend fun close() {
                        _close()
                    }

                    override suspend fun next(): Value {
                        return next_helper {
                            for (i in 0 until columnsINJ[0].size) {
                                keyCopy[i] = key[0][i]
                            }
                            val data = Array(2) { Array(columnsINO[it].size) { MyListValue() } }
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
                            POPJoin.crossProduct(data, keyCopy, columnsOUT, columnsOUTJ, countA, countB)
                        }
                    }
                }
                outIteratorsAllocated.add(iterator)
                when (iteratorConfig.second) {
                    0 -> {
                        columnsOUTJ.add(iterator)
                        outMap[iteratorConfig.first] = iterator
                    }
                    1 -> {
                        columnsOUT[0].add(iterator)
                        outMap[iteratorConfig.first] = iterator
                    }
                    2 -> {
                        columnsOUT[1].add(iterator)
                        outMap[iteratorConfig.first] = iterator
                    }
                    3 -> {
                        columnsOUTJ.add(iterator)
                    }
                }
            }
        }
        val res = IteratorBundle(outMap)
        if (emptyColumnsWithJoin) {
            res.hasNext2 = {
                /*return*/columnsOUTJ[0].next() != ResultSetDictionary.nullValue
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
        }
        return res
    }

    suspend    /*inline*/ fun sameElements(key: Array<Value>, keyCopy: Array<Value>, columnsINJ: MutableList<ColumnIterator>, columnsINO: MutableList<ColumnIterator>, data: Array<MyListValue>): Int {
        SanityCheck.check { keyCopy[0] != ResultSetDictionary.nullValue }
        for (i in 0 until columnsINJ.size) {
            if (key[i] != keyCopy[i]) {
                /* this is an optional element without a match */
                for (j in 0 until columnsINO.size) {
                    data[j].add(ResultSetDictionary.undefValue)
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

    suspend    /*inline*/ fun findNextKey(key: Array<Array<Value>>, columnsINJ: Array<MutableList<ColumnIterator>>, columnsINO: Array<MutableList<ColumnIterator>>): Boolean {
        if (key[0][0] != ResultSetDictionary.nullValue && key[1][0] != ResultSetDictionary.nullValue) {
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
                            SanityCheck.check { key[1][j] != ResultSetDictionary.undefValue }
                            if (key[1][j] == ResultSetDictionary.nullValue) {
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
        return key[0][0] == ResultSetDictionary.nullValue
    }

    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMergeOptional(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
