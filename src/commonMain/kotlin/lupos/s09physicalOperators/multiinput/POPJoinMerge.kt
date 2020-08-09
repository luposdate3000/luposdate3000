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
    class IteratorBundleImpl(@JvmField val columnsINJ0: List<ColumnIterator>, @JvmField val columnsINJ1: List<ColumnIterator>, @JvmField val columnsOUTJ: ColumnIteratorChildIterator) : IteratorBundle(0) {
        override suspend fun hasNext2(): Boolean {
            val tmp = columnsOUTJ.next() != ResultSetDictionary.nullValue
            if (!tmp) {
                _hasNext2Close()
            }
            return tmp
        }

        suspend inline fun _hasNext2Close() {
            for (it in columnsINJ0) {
                it.close()
            }
            for (it in columnsINJ1) {
                it.close()
            }
        }

        suspend override fun hasNext2Close() {
            _hasNext2Close()
        }
    }

    class ColumnIteratorChildIteratorImpl(
            @JvmField val columnsINJ0: List<ColumnIterator>,
            @JvmField val columnsINJ1: List<ColumnIterator>,
            @JvmField val columnsINO0: List<ColumnIterator>,
            @JvmField val columnsINO1: List<ColumnIterator>,
            @JvmField val columnsOUT0: List<ColumnIteratorChildIterator>,
            @JvmField val columnsOUT1: List<ColumnIteratorChildIterator>,
            @JvmField val columnsOUTJ: List<ColumnIteratorChildIterator>,
            @JvmField val key0: IntArray,
            @JvmField val key1: IntArray
    ) : ColumnIteratorChildIterator() {
        @JvmField
        val data0 = Array(columnsINO0.size) { IntArray(100) }

        @JvmField
        val data1 = Array(columnsINO1.size) { IntArray(100) }

        @JvmField
        val keyCopy = IntArray(columnsINJ0.size) { key0[it] }


        suspend inline fun __close() {
            if (label != 0) {
                for (it in columnsOUT0) {
                    it.closeOnNoMoreElements()
                }
                for (it in columnsOUT1) {
                    it.closeOnNoMoreElements()
                }
                for (it in columnsOUTJ) {
                    it.closeOnNoMoreElements()
                }
                for (it in columnsINJ0) {
                    it.close()
                }
                for (it in columnsINJ1) {
                    it.close()
                }
                for (it in columnsINO0) {
                    it.close()
                }
                for (it in columnsINO1) {
                    it.close()
                }
                _close()
            }
        }

        override suspend fun close() {
            __close()
        }

        override suspend fun next(): Value {
            return next_helper {
                val done2 = findNextKey()
                if (done2) {
                    __close()
                } else {
                    for (i in 0 until columnsINJ0.size) {
                        keyCopy[i] = key0[i]
                    }
                    val countA = sameElements(key0, keyCopy, columnsINJ0, columnsINO0, data0)
                    val countB = sameElements(key1, keyCopy, columnsINJ1, columnsINO1, data1)
                    POPJoin.crossProduct(data0, data1, keyCopy, columnsOUT0, columnsOUT1, columnsOUTJ, countA, countB)
                }
            }
        }

        inline suspend fun sameElements(key: IntArray, keyCopy: IntArray, columnsINJ: List<ColumnIterator>, columnsINO: List<ColumnIterator>, data: Array<IntArray>): Int {
            var count = 0
            SanityCheck.check { keyCopy[0] != ResultSetDictionary.nullValue }
            loop@ while (true) {
                if (columnsINO.size > 0) {
                    if (count >= data[0].size) {
                        for (i in 0 until data.size) {
                            val x = data[i]
                            val d = IntArray(count * 2)
                            for (i in 0 until count) {
                                d[i] = x[i]
                            }
                            data[i] = d
                        }
                    }
                    for (i in 0 until columnsINO.size) {
                        data[i][count] = columnsINO[i].next()
                    }
                }
                for (i in 0 until columnsINJ.size) {
                    key[i] = columnsINJ[i].next()
                    SanityCheck.check { key[i] != ResultSetDictionary.undefValue }
                }
                count++
                for (i in 0 until columnsINJ.size) {
                    if (key[i] != keyCopy[i]) {
                        return count
                    }
                }
            }
        }

        suspend inline fun findNextKey(): Boolean {
            if (key0[0] != ResultSetDictionary.nullValue && key1[0] != ResultSetDictionary.nullValue) {
                loop@ while (true) {
                    for (i in 0 until columnsINJ0.size) {
                        if (key0[i] < key1[i]) {
                            for (j in 0 until columnsINO0.size) {
                                columnsINO0[j].next()
                            }
                            for (j in 0 until columnsINJ0.size) {
                                key0[j] = columnsINJ0[j].next()
                                SanityCheck.check { key0[j] != ResultSetDictionary.undefValue }
                                if (key0[j] == ResultSetDictionary.nullValue) {
                                    SanityCheck.check { j == 0 }
                                    return true
                                }
                            }
                            continue@loop
                        } else if (key0[i] > key1[i]) {
                            for (j in 0 until columnsINO1.size) {
                                columnsINO1[j].next()
                            }
                            for (j in 0 until columnsINJ1.size) {
                                key1[j] = columnsINJ1[j].next()
                                SanityCheck.check { key1[j] != ResultSetDictionary.undefValue }
                                if (key1[j] == ResultSetDictionary.nullValue) {
                                    SanityCheck.check { j == 0 }
                                    return true
                                }
                            }
                            continue@loop
                        }
                    }
                    return false
                }
            } else {
                return true
            }
        }
    }

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
        for (iteratorConfig in outIterators) {
            val iterator = ColumnIteratorChildIteratorImpl(columnsINJ0, columnsINJ1, columnsINO0, columnsINO1, columnsOUT0, columnsOUT1, columnsOUTJ, key0, key1)
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
        val res: IteratorBundle
        if (emptyColumnsWithJoin) {
            res = IteratorBundleImpl(columnsINJ0, columnsINJ1, columnsOUTJ[0])
            for (it in columnsINO0) {
                it.close()
            }
            for (it in columnsINO1) {
                it.close()
            }
        } else {
            res = IteratorBundle(outMap)
        }
        return res
    }

}
