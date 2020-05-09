package lupos.s09physicalOperators.multiinput
import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s01io.*
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorAggregate
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorDistinct
import lupos.s04logicalOperators.iterator.ColumnIteratorMergeSort
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorQueue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.iterator.ColumnIteratorStore1
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore2b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3a
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3b
import lupos.s04logicalOperators.iterator.ColumnIteratorStore3c
import lupos.s09physicalOperators.POPBase




class POPJoinMerge(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeID, "POPJoinMerge", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
        if (optional) {
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
        return children[0].toSparql() + children[1].toSparql()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPJoinMerge) {
            return false
        }
        if (optional != other.optional) {
            return false
        }
        for (i in children.indices) {
            if (!children[i].equals(other.children[i])) {
                return false
            }
        }
        return true
    }

    override suspend fun evaluate(): ColumnIteratorRow {
        SanityCheck.check { !optional }
//setup columns
        val child = Array(2) { children[it].evaluate() }
        val columnsINO = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsINJ = Array(2) { mutableListOf<ColumnIterator>() }
        val columnsOUT = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
        val outIterators = mutableListOf<ColumnIteratorChildIterator>()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val tmp = mutableListOf<String>()
        tmp.addAll(children[1].getProvidedVariableNames())
        var t: ColumnIteratorChildIterator
        for (name in children[0].getProvidedVariableNames()) {
            if (tmp.contains(name)) {
                if (projectedVariables.contains(name)) {
                    t = ColumnIteratorChildIterator()
                    outMap[name] = ColumnIteratorDebug(uuid, name, t)
                    outIterators.add(t)
                    columnsOUTJ.add(t)
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
                t = ColumnIteratorChildIterator()
                outIterators.add(t)
                outMap[name] = ColumnIteratorDebug(uuid, name, t)
                columnsOUT[0].add(t)
                columnsINO[0].add(child[0].columns[name]!!)
            }
        }
        for (name in tmp) {
            t = ColumnIteratorChildIterator()
            outIterators.add(t)
            outMap[name] = ColumnIteratorDebug(uuid, name, t)
            columnsOUT[1].add(t)
            columnsINO[1].add(child[1].columns[name]!!)
        }
        SanityCheck.check { columnsINJ[0].size > 0 }
        SanityCheck.check { columnsINJ[0].size == columnsINJ[1].size }
        var emptyColumnsWithJoin = columnsOUT[0].size == 0 && columnsOUT[1].size == 0 && columnsOUTJ.size == 0
        if (emptyColumnsWithJoin) {
            t = ColumnIteratorChildIterator()
            outIterators.add(t)
            columnsOUTJ.add(t)
        }
        val key = Array(2) { i -> Array(columnsINJ[i].size) { columnsINJ[i][it].next() } }
        var done = findNextKey(key, columnsINJ, columnsINO)
        if (!done) {
            val keyCopy = Array(columnsINJ[0].size) { key[0][it] }
            for (iterator in outIterators) {
                iterator.onNoMoreElements = {
                    //BenchmarkUtils.start(EBenchmark.JOIN_MERGE_NO_MORE_ELEMENTS)
                    for (i in 0 until columnsINJ[0].size) {
                        keyCopy[i] = key[0][i]
                    }
                    val data = Array(2) { Array(columnsINO[it].size) { MyListValue() } }
                    val countA = sameElements(key[0], keyCopy, columnsINJ[0], columnsINO[0], data[0])
                    val countB = sameElements(key[1], keyCopy, columnsINJ[1], columnsINO[1], data[1])
                    findNextKey(key, columnsINJ, columnsINO)
                    if (key[0][0] == null || key[1][0] == null) {
                        for (iterator2 in outIterators) {
                            iterator2.onNoMoreElements = iterator2::_onNoMoreElements
                        }
                    }
//BenchmarkUtils.start(EBenchmark.JOIN_MERGE_CROSS_PRODUCT)
                    POPJoin.crossProduct(data, keyCopy, columnsOUT, columnsOUTJ, countA, countB)
//BenchmarkUtils.elapsedSeconds(EBenchmark.JOIN_MERGE_CROSS_PRODUCT)
//BenchmarkUtils.elapsedSeconds(EBenchmark.JOIN_MERGE_NO_MORE_ELEMENTS)
                }
            }
        }
        val res = ColumnIteratorRow(outMap)
        if (emptyColumnsWithJoin) {
            res.hasNext = {
                /*return*/columnsOUTJ[0].next() != null
            }
        }
        return res
    }

    inline suspend fun sameElements(key: Array<Value?>, keyCopy: Array<Value?>, columnsINJ: MutableList<ColumnIterator>, columnsINO: MutableList<ColumnIterator>, data: Array<MyListValue>): Int {
        var count = 0
//BenchmarkUtils.start(EBenchmark.JOIN_MERGE_SAME_ELEMENTS)
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
//BenchmarkUtils.elapsedSeconds(EBenchmark.JOIN_MERGE_SAME_ELEMENTS)
        return count
    }

    inline suspend fun findNextKey(key: Array<Array<Value?>>, columnsINJ: Array<MutableList<ColumnIterator>>, columnsINO: Array<MutableList<ColumnIterator>>): Boolean {
        var done = true
//BenchmarkUtils.start(EBenchmark.JOIN_MERGE_NEXT_KEY)
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
//BenchmarkUtils.elapsedSeconds(EBenchmark.JOIN_MERGE_NEXT_KEY)
        return done
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMerge(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
