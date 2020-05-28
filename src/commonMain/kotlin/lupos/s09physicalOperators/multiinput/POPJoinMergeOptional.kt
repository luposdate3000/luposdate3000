package lupos.s09physicalOperators.multiinput
import kotlin.jvm.JvmField
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
class POPJoinMergeOptional(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinMergeOptionalID, "POPJoinMergeOptional", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
Coverage.funStart(10542)
        if (optional) {
Coverage.ifStart(10543)
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
Coverage.statementStart(10544)
        return children[0].toSparql() + children[1].toSparql()
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(10545)
        if (other !is POPJoinMergeOptional) {
Coverage.ifStart(10546)
            return false
        }
Coverage.statementStart(10547)
        if (optional != other.optional) {
Coverage.ifStart(10548)
            return false
        }
Coverage.statementStart(10549)
        for (i in children.indices) {
Coverage.forLoopStart(10550)
            if (!children[i].equals(other.children[i])) {
Coverage.ifStart(10551)
                return false
            }
Coverage.statementStart(10552)
        }
Coverage.statementStart(10553)
        return true
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(10554)
        SanityCheck.check { optional }
Coverage.statementStart(10555)
//setup columns
Coverage.statementStart(10556)
        val child = Array(2) { children[it].evaluate() }
Coverage.statementStart(10557)
        val columnsINO = Array(2) { mutableListOf<ColumnIterator>() }
Coverage.statementStart(10558)
        val columnsINJ = Array(2) { mutableListOf<ColumnIterator>() }
Coverage.statementStart(10559)
        val columnsOUT = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }
Coverage.statementStart(10560)
        val columnsOUTJ = mutableListOf<ColumnIteratorChildIterator>()
Coverage.statementStart(10561)
        val outIterators = mutableListOf<ColumnIteratorChildIterator>()
Coverage.statementStart(10562)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(10563)
        val tmp = mutableListOf<String>()
Coverage.statementStart(10564)
        tmp.addAll(children[1].getProvidedVariableNames())
Coverage.statementStart(10565)
        var t: ColumnIteratorChildIterator
Coverage.statementStart(10566)
        for (name in children[0].getProvidedVariableNames()) {
Coverage.forLoopStart(10567)
            if (tmp.contains(name)) {
Coverage.ifStart(10568)
                if (projectedVariables.contains(name)) {
Coverage.ifStart(10569)
                    t = ColumnIteratorChildIterator()
Coverage.statementStart(10570)
                    outMap[name] = ColumnIteratorDebug(uuid, name, t)
Coverage.statementStart(10571)
                    outIterators.add(t)
Coverage.statementStart(10572)
                    columnsOUTJ.add(t)
Coverage.statementStart(10573)
                    for (i in 0 until 2) {
Coverage.forLoopStart(10574)
                        columnsINJ[i].add(0, child[i].columns[name]!!)
Coverage.statementStart(10575)
                    }
Coverage.statementStart(10576)
                } else {
Coverage.ifStart(10577)
                    for (i in 0 until 2) {
Coverage.forLoopStart(10578)
                        columnsINJ[i].add(child[i].columns[name]!!)
Coverage.statementStart(10579)
                    }
Coverage.statementStart(10580)
                }
Coverage.statementStart(10581)
                tmp.remove(name)
Coverage.statementStart(10582)
            } else {
Coverage.ifStart(10583)
                t = ColumnIteratorChildIterator()
Coverage.statementStart(10584)
                outIterators.add(t)
Coverage.statementStart(10585)
                outMap[name] = ColumnIteratorDebug(uuid, name, t)
Coverage.statementStart(10586)
                columnsOUT[0].add(t)
Coverage.statementStart(10587)
                columnsINO[0].add(child[0].columns[name]!!)
Coverage.statementStart(10588)
            }
Coverage.statementStart(10589)
        }
Coverage.statementStart(10590)
        for (name in tmp) {
Coverage.forLoopStart(10591)
            t = ColumnIteratorChildIterator()
Coverage.statementStart(10592)
            outIterators.add(t)
Coverage.statementStart(10593)
            outMap[name] = ColumnIteratorDebug(uuid, name, t)
Coverage.statementStart(10594)
            columnsOUT[1].add(t)
Coverage.statementStart(10595)
            columnsINO[1].add(child[1].columns[name]!!)
Coverage.statementStart(10596)
        }
Coverage.statementStart(10597)
        SanityCheck.check { columnsINJ[0].size > 0 }
Coverage.statementStart(10598)
        SanityCheck.check { columnsINJ[0].size == columnsINJ[1].size }
Coverage.statementStart(10599)
        var emptyColumnsWithJoin = columnsOUT[0].size == 0 && columnsOUT[1].size == 0 && columnsOUTJ.size == 0
Coverage.statementStart(10600)
        if (emptyColumnsWithJoin) {
Coverage.ifStart(10601)
            t = ColumnIteratorChildIterator()
Coverage.statementStart(10602)
            outIterators.add(t)
Coverage.statementStart(10603)
            columnsOUTJ.add(t)
Coverage.statementStart(10604)
        }
Coverage.statementStart(10605)
        val key = Array(2) { i -> Array(columnsINJ[i].size) { columnsINJ[i][it].next() } }
Coverage.statementStart(10606)
        var done = findNextKey(key, columnsINJ, columnsINO)
Coverage.statementStart(10607)
        if (!done) {
Coverage.ifStart(10608)
            val keyCopy = Array(columnsINJ[0].size) { key[0][it] }
Coverage.statementStart(10609)
            for (iterator in outIterators) {
Coverage.forLoopStart(10610)
                iterator.onNoMoreElements = {
Coverage.statementStart(10611)
                    for (i in 0 until columnsINJ[0].size) {
Coverage.forLoopStart(10612)
                        keyCopy[i] = key[0][i]
Coverage.statementStart(10613)
                    }
Coverage.statementStart(10614)
                    val data = Array(2) { Array(columnsINO[it].size) { MyListValue() } }
Coverage.statementStart(10615)
                    println("thekey:: ${keyCopy.map { it }}")
Coverage.statementStart(10616)
                    val countA = sameElements(key[0], keyCopy, columnsINJ[0], columnsINO[0], data[0])
Coverage.statementStart(10617)
                    val countB = sameElements(key[1], keyCopy, columnsINJ[1], columnsINO[1], data[1])
Coverage.statementStart(10618)
                    println("thecount :: $countA $countB")
Coverage.statementStart(10619)
                    done = findNextKey(key, columnsINJ, columnsINO)
Coverage.statementStart(10620)
                    if (done) {
Coverage.ifStart(10621)
                        for (iterator2 in outIterators) {
Coverage.forLoopStart(10622)
                            iterator2.onNoMoreElements = iterator2::_onNoMoreElements
Coverage.statementStart(10623)
                        }
Coverage.statementStart(10624)
                    }
Coverage.statementStart(10625)
                    POPJoin.crossProduct(data, keyCopy, columnsOUT, columnsOUTJ, countA, countB)
Coverage.statementStart(10626)
                }
Coverage.statementStart(10627)
            }
Coverage.statementStart(10628)
        }
Coverage.statementStart(10629)
        val res = IteratorBundle(outMap)
Coverage.statementStart(10630)
        if (emptyColumnsWithJoin) {
Coverage.ifStart(10631)
            res.hasNext = {
Coverage.statementStart(10632)
                /*return*/columnsOUTJ[0].next() != null
            }
Coverage.statementStart(10633)
        }
Coverage.statementStart(10634)
        return res
    }
    inline suspend fun sameElements(key: Array<Value?>, keyCopy: Array<Value?>, columnsINJ: MutableList<ColumnIterator>, columnsINO: MutableList<ColumnIterator>, data: Array<MyListValue>): Int {
Coverage.funStart(10635)
        SanityCheck.check { keyCopy[0] != null }
Coverage.statementStart(10636)
        for (i in 0 until columnsINJ.size) {
Coverage.forLoopStart(10637)
            if (key[i] != keyCopy[i]) {
Coverage.ifStart(10638)
/* this is an optional element without a match */
Coverage.statementStart(10639)
                for (j in 0 until columnsINO.size) {
Coverage.forLoopStart(10640)
                    data[j].add(ResultSetDictionary.undefValue)
Coverage.statementStart(10641)
                }
Coverage.statementStart(10642)
                return 1
            }
Coverage.statementStart(10643)
        }
Coverage.statementStart(10644)
        var count = 0
Coverage.statementStart(10645)
/* at least 1 matching row */
Coverage.statementStart(10646)
        loop@ while (true) {
Coverage.whileLoopStart(10647)
            count++
Coverage.statementStart(10648)
            for (i in 0 until columnsINO.size) {
Coverage.forLoopStart(10649)
                data[i].add(columnsINO[i].next()!!)
Coverage.statementStart(10650)
            }
Coverage.statementStart(10651)
            for (i in 0 until columnsINJ.size) {
Coverage.forLoopStart(10652)
                key[i] = columnsINJ[i].next()
Coverage.statementStart(10653)
                SanityCheck.check { key[i] != ResultSetDictionary.undefValue }
Coverage.statementStart(10654)
            }
Coverage.statementStart(10655)
            for (i in 0 until columnsINJ.size) {
Coverage.forLoopStart(10656)
                if (key[i] != keyCopy[i]) {
Coverage.ifStart(10657)
                    break@loop
                }
Coverage.statementStart(10658)
            }
Coverage.statementStart(10659)
        }
Coverage.statementStart(10660)
        return count
    }
    inline suspend fun findNextKey(key: Array<Array<Value?>>, columnsINJ: Array<MutableList<ColumnIterator>>, columnsINO: Array<MutableList<ColumnIterator>>): Boolean {
Coverage.funStart(10661)
        if (key[0][0] != null && key[1][0] != null) {
Coverage.ifStart(10662)
            loop@ while (true) {
Coverage.whileLoopStart(10663)
                for (i in 0 until columnsINJ[0].size) {
Coverage.forLoopStart(10664)
                    val a = key[0][i]!!
Coverage.statementStart(10665)
                    val b = key[1][i]!!
Coverage.statementStart(10666)
                    if (a > b) {
Coverage.ifStart(10667)
                        for (j in 0 until columnsINO[1].size) {
Coverage.forLoopStart(10668)
                            columnsINO[1][j].next()
Coverage.statementStart(10669)
                        }
Coverage.statementStart(10670)
                        for (j in 0 until columnsINJ[1].size) {
Coverage.forLoopStart(10671)
                            key[1][j] = columnsINJ[1][j].next()
Coverage.statementStart(10672)
                            SanityCheck.check { key[1][j] != ResultSetDictionary.undefValue }
Coverage.statementStart(10673)
                            if (key[1][j] == null) {
Coverage.ifStart(10674)
                                SanityCheck.check { j == 0 }
Coverage.statementStart(10675)
                                break@loop
                            }
Coverage.statementStart(10676)
                        }
Coverage.statementStart(10677)
                        continue@loop
                    }
Coverage.statementStart(10678)
                }
Coverage.statementStart(10679)
                break@loop
            }
Coverage.statementStart(10680)
        }
Coverage.statementStart(10681)
        return key[0][0] == null
    }
    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinMergeOptional(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
