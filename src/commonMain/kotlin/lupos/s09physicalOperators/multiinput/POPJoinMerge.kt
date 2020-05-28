package lupos.s09physicalOperators.multiinput
import kotlin.jvm.JvmField
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
class POPJoinMerge(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeID, "POPJoinMerge", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
Coverage.funStart(10390)
        if (optional) {
Coverage.ifStart(10391)
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
Coverage.statementStart(10392)
        return children[0].toSparql() + children[1].toSparql()
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(10393)
        if (other !is POPJoinMerge) {
Coverage.ifStart(10394)
            return false
        }
Coverage.statementStart(10395)
        if (optional != other.optional) {
Coverage.ifStart(10396)
            return false
        }
Coverage.statementStart(10397)
        for (i in children.indices) {
Coverage.forLoopStart(10398)
            if (!children[i].equals(other.children[i])) {
Coverage.ifStart(10399)
                return false
            }
Coverage.statementStart(10400)
        }
Coverage.statementStart(10401)
        return true
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(10402)
        SanityCheck.check { !optional }
Coverage.statementStart(10403)
//setup columns
Coverage.statementStart(10404)
        val child = Array(2) { children[it].evaluate() }
Coverage.statementStart(10405)
        val columnsINO = Array(2) { mutableListOf<ColumnIterator>() }
Coverage.statementStart(10406)
        val columnsINJ = Array(2) { mutableListOf<ColumnIterator>() }
Coverage.statementStart(10407)
        val columnsOUT = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }
Coverage.statementStart(10408)
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
Coverage.statementStart(10409)
        val outIterators = mutableListOf<ColumnIteratorChildIterator>()
Coverage.statementStart(10410)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(10411)
        val tmp = mutableListOf<String>()
Coverage.statementStart(10412)
        tmp.addAll(children[1].getProvidedVariableNames())
Coverage.statementStart(10413)
        var t: ColumnIteratorChildIterator
Coverage.statementStart(10414)
        for (name in children[0].getProvidedVariableNames()) {
Coverage.forLoopStart(10415)
            if (tmp.contains(name)) {
Coverage.ifStart(10416)
                if (projectedVariables.contains(name)) {
Coverage.ifStart(10417)
                    t = ColumnIteratorChildIterator()
Coverage.statementStart(10418)
                    outMap[name] = ColumnIteratorDebug(uuid, name, t)
Coverage.statementStart(10419)
                    outIterators.add(t)
Coverage.statementStart(10420)
                    columnsOUTJ.add(t)
Coverage.statementStart(10421)
                    for (i in 0 until 2) {
Coverage.forLoopStart(10422)
                        columnsINJ[i].add(0, child[i].columns[name]!!)
Coverage.statementStart(10423)
                    }
Coverage.statementStart(10424)
                } else {
Coverage.ifStart(10425)
                    for (i in 0 until 2) {
Coverage.forLoopStart(10426)
                        columnsINJ[i].add(child[i].columns[name]!!)
Coverage.statementStart(10427)
                    }
Coverage.statementStart(10428)
                }
Coverage.statementStart(10429)
                tmp.remove(name)
Coverage.statementStart(10430)
            } else {
Coverage.ifStart(10431)
                t = ColumnIteratorChildIterator()
Coverage.statementStart(10432)
                outIterators.add(t)
Coverage.statementStart(10433)
                outMap[name] = ColumnIteratorDebug(uuid, name, t)
Coverage.statementStart(10434)
                columnsOUT[0].add(t)
Coverage.statementStart(10435)
                columnsINO[0].add(child[0].columns[name]!!)
Coverage.statementStart(10436)
            }
Coverage.statementStart(10437)
        }
Coverage.statementStart(10438)
        for (name in tmp) {
Coverage.forLoopStart(10439)
            t = ColumnIteratorChildIterator()
Coverage.statementStart(10440)
            outIterators.add(t)
Coverage.statementStart(10441)
            outMap[name] = ColumnIteratorDebug(uuid, name, t)
Coverage.statementStart(10442)
            columnsOUT[1].add(t)
Coverage.statementStart(10443)
            columnsINO[1].add(child[1].columns[name]!!)
Coverage.statementStart(10444)
        }
Coverage.statementStart(10445)
        SanityCheck.check { columnsINJ[0].size > 0 }
Coverage.statementStart(10446)
        SanityCheck.check { columnsINJ[0].size == columnsINJ[1].size }
Coverage.statementStart(10447)
        var emptyColumnsWithJoin = columnsOUT[0].size == 0 && columnsOUT[1].size == 0 && columnsOUTJ.size == 0
Coverage.statementStart(10448)
        if (emptyColumnsWithJoin) {
Coverage.ifStart(10449)
            t = ColumnIteratorChildIterator()
Coverage.statementStart(10450)
            outIterators.add(t)
Coverage.statementStart(10451)
            columnsOUTJ.add(t)
Coverage.statementStart(10452)
        }
Coverage.statementStart(10453)
        val key = Array(2) { i -> Array(columnsINJ[i].size) { columnsINJ[i][it].next() } }
Coverage.statementStart(10454)
        var done = findNextKey(key, columnsINJ, columnsINO)
Coverage.statementStart(10455)
        if (!done) {
Coverage.ifStart(10456)
            val keyCopy = Array(columnsINJ[0].size) { key[0][it] }
Coverage.statementStart(10457)
            for (iterator in outIterators) {
Coverage.forLoopStart(10458)
                iterator.onNoMoreElements = {
Coverage.statementStart(10459)
                    //BenchmarkUtils.start(EBenchmark.JOIN_MERGE_NO_MORE_ELEMENTS)
Coverage.statementStart(10460)
                    for (i in 0 until columnsINJ[0].size) {
Coverage.forLoopStart(10461)
                        keyCopy[i] = key[0][i]
Coverage.statementStart(10462)
                    }
Coverage.statementStart(10463)
                    val data = Array(2) { Array(columnsINO[it].size) { MyListValue() } }
Coverage.statementStart(10464)
                    val countA = sameElements(key[0], keyCopy, columnsINJ[0], columnsINO[0], data[0])
Coverage.statementStart(10465)
                    val countB = sameElements(key[1], keyCopy, columnsINJ[1], columnsINO[1], data[1])
Coverage.statementStart(10466)
                    findNextKey(key, columnsINJ, columnsINO)
Coverage.statementStart(10467)
                    if (key[0][0] == null || key[1][0] == null) {
Coverage.ifStart(10468)
                        for (iterator2 in outIterators) {
Coverage.forLoopStart(10469)
                            iterator2.onNoMoreElements = iterator2::_onNoMoreElements
Coverage.statementStart(10470)
                        }
Coverage.statementStart(10471)
                    }
Coverage.statementStart(10472)
//BenchmarkUtils.start(EBenchmark.JOIN_MERGE_CROSS_PRODUCT)
Coverage.statementStart(10473)
                    POPJoin.crossProduct(data, keyCopy, columnsOUT, columnsOUTJ, countA, countB)
Coverage.statementStart(10474)
//BenchmarkUtils.elapsedSeconds(EBenchmark.JOIN_MERGE_CROSS_PRODUCT)
Coverage.statementStart(10475)
//BenchmarkUtils.elapsedSeconds(EBenchmark.JOIN_MERGE_NO_MORE_ELEMENTS)
Coverage.statementStart(10476)
                }
Coverage.statementStart(10477)
            }
Coverage.statementStart(10478)
        }
Coverage.statementStart(10479)
        val res = IteratorBundle(outMap)
Coverage.statementStart(10480)
        if (emptyColumnsWithJoin) {
Coverage.ifStart(10481)
            res.hasNext = {
Coverage.statementStart(10482)
                /*return*/columnsOUTJ[0].next() != null
            }
Coverage.statementStart(10483)
        }
Coverage.statementStart(10484)
        return res
    }
    inline suspend fun sameElements(key: Array<Value?>, keyCopy: Array<Value?>, columnsINJ: MutableList<ColumnIterator>, columnsINO: MutableList<ColumnIterator>, data: Array<MyListValue>): Int {
Coverage.funStart(10485)
        var count = 0
Coverage.statementStart(10486)
//BenchmarkUtils.start(EBenchmark.JOIN_MERGE_SAME_ELEMENTS)
Coverage.statementStart(10487)
        SanityCheck.check { keyCopy[0] != null }
Coverage.statementStart(10488)
        loop@ while (true) {
Coverage.whileLoopStart(10489)
            count++
Coverage.statementStart(10490)
            for (i in 0 until columnsINO.size) {
Coverage.forLoopStart(10491)
                data[i].add(columnsINO[i].next()!!)
Coverage.statementStart(10492)
            }
Coverage.statementStart(10493)
            for (i in 0 until columnsINJ.size) {
Coverage.forLoopStart(10494)
                key[i] = columnsINJ[i].next()
Coverage.statementStart(10495)
                SanityCheck.check { key[i] != ResultSetDictionary.undefValue }
Coverage.statementStart(10496)
            }
Coverage.statementStart(10497)
            for (i in 0 until columnsINJ.size) {
Coverage.forLoopStart(10498)
                if (key[i] != keyCopy[i]) {
Coverage.ifStart(10499)
                    break@loop
                }
Coverage.statementStart(10500)
            }
Coverage.statementStart(10501)
        }
Coverage.statementStart(10502)
//BenchmarkUtils.elapsedSeconds(EBenchmark.JOIN_MERGE_SAME_ELEMENTS)
Coverage.statementStart(10503)
        return count
    }
    inline suspend fun findNextKey(key: Array<Array<Value?>>, columnsINJ: Array<MutableList<ColumnIterator>>, columnsINO: Array<MutableList<ColumnIterator>>): Boolean {
Coverage.funStart(10504)
        var done = true
Coverage.statementStart(10505)
//BenchmarkUtils.start(EBenchmark.JOIN_MERGE_NEXT_KEY)
Coverage.statementStart(10506)
        if (key[0][0] != null && key[1][0] != null) {
Coverage.ifStart(10507)
            done = false
Coverage.statementStart(10508)
            loop@ while (true) {
Coverage.whileLoopStart(10509)
                for (i in 0 until columnsINJ[0].size) {
Coverage.forLoopStart(10510)
                    val a = key[0][i]!!
Coverage.statementStart(10511)
                    val b = key[1][i]!!
Coverage.statementStart(10512)
                    if (a < b) {
Coverage.ifStart(10513)
                        for (j in 0 until columnsINO[0].size) {
Coverage.forLoopStart(10514)
                            columnsINO[0][j].next()
Coverage.statementStart(10515)
                        }
Coverage.statementStart(10516)
                        for (j in 0 until columnsINJ[0].size) {
Coverage.forLoopStart(10517)
                            key[0][j] = columnsINJ[0][j].next()
Coverage.statementStart(10518)
                            SanityCheck.check { key[0][j] != ResultSetDictionary.undefValue }
Coverage.statementStart(10519)
                            done = key[0][j] == null
Coverage.statementStart(10520)
                            if (done) {
Coverage.ifStart(10521)
                                SanityCheck.check { j == 0 }
Coverage.statementStart(10522)
                                break@loop
                            }
Coverage.statementStart(10523)
                        }
Coverage.statementStart(10524)
                        continue@loop
                    } else if (a > b) {
Coverage.statementStart(10525)
                        for (j in 0 until columnsINO[1].size) {
Coverage.forLoopStart(10526)
                            columnsINO[1][j].next()
Coverage.statementStart(10527)
                        }
Coverage.statementStart(10528)
                        for (j in 0 until columnsINJ[1].size) {
Coverage.forLoopStart(10529)
                            key[1][j] = columnsINJ[1][j].next()
Coverage.statementStart(10530)
                            SanityCheck.check { key[1][j] != ResultSetDictionary.undefValue }
Coverage.statementStart(10531)
                            done = key[1][j] == null
Coverage.statementStart(10532)
                            if (done) {
Coverage.ifStart(10533)
                                SanityCheck.check { j == 0 }
Coverage.statementStart(10534)
                                break@loop
                            }
Coverage.statementStart(10535)
                        }
Coverage.statementStart(10536)
                        continue@loop
                    }
Coverage.statementStart(10537)
                }
Coverage.statementStart(10538)
                break@loop
            }
Coverage.statementStart(10539)
        }
Coverage.statementStart(10540)
//BenchmarkUtils.elapsedSeconds(EBenchmark.JOIN_MERGE_NEXT_KEY)
Coverage.statementStart(10541)
        return done
    }
    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMerge(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
