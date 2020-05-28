package lupos.s09physicalOperators.multiinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
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
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
class POPJoinCartesianProduct(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase, @JvmField val optional: Boolean) : POPBase(query, projectedVariables, EOperatorID.POPJoinCartesianProductID, "POPJoinCartesianProduct", arrayOf(childA, childB), ESortPriority.JOIN) {
    override fun toSparql(): String {
Coverage.funStart(10015)
        if (optional) {
Coverage.ifStart(10016)
            return "OPTIONAL{" + children[0].toSparql() + children[1].toSparql() + "}"
        }
Coverage.statementStart(10017)
        return children[0].toSparql() + children[1].toSparql()
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(10018)
        if (other !is POPJoinCartesianProduct) {
Coverage.ifStart(10019)
            return false
        }
Coverage.statementStart(10020)
        if (optional != other.optional) {
Coverage.ifStart(10021)
            return false
        }
Coverage.statementStart(10022)
        for (i in children.indices) {
Coverage.forLoopStart(10023)
            if (!children[i].equals(other.children[i])) {
Coverage.ifStart(10024)
                return false
            }
Coverage.statementStart(10025)
        }
Coverage.statementStart(10026)
        return true
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(10027)
        val columns = LOPJoin.getColumns(children[0].getProvidedVariableNames(), children[1].getProvidedVariableNames())
Coverage.statementStart(10028)
        require(columns[0].size == 0)
Coverage.statementStart(10029)
        val childA = children[0].evaluate()
Coverage.statementStart(10030)
        val childB = children[1].evaluate()
Coverage.statementStart(10031)
        val columnsINAO = mutableListOf<ColumnIterator>()//only in childA
Coverage.statementStart(10032)
        val columnsINBO = mutableListOf<ColumnIterator>()//only in childB
Coverage.statementStart(10033)
        val outO = Array(2) { mutableListOf<ColumnIteratorChildIterator>() }//only in one of the childs
Coverage.statementStart(10034)
        val outIterators = mutableListOf<ColumnIteratorChildIterator>()
Coverage.statementStart(10035)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(10036)
        var res: IteratorBundle?
Coverage.statementStart(10037)
        var t: ColumnIteratorChildIterator
Coverage.statementStart(10038)
        for (name in columns[1]) {
Coverage.forLoopStart(10039)
            t = ColumnIteratorChildIterator()
Coverage.statementStart(10040)
            outIterators.add(t)
Coverage.statementStart(10041)
            outMap[name] = ColumnIteratorDebug(uuid, name, t)
Coverage.statementStart(10042)
            outO[0].add(t)
Coverage.statementStart(10043)
            columnsINAO.add(childA.columns[name]!!)
Coverage.statementStart(10044)
        }
Coverage.statementStart(10045)
        for (name in columns[2]) {
Coverage.forLoopStart(10046)
            t = ColumnIteratorChildIterator()
Coverage.statementStart(10047)
            outIterators.add(t)
Coverage.statementStart(10048)
            outMap[name] = ColumnIteratorDebug(uuid, name, t)
Coverage.statementStart(10049)
            outO[1].add(t)
Coverage.statementStart(10050)
            columnsINBO.add(childB.columns[name]!!)
Coverage.statementStart(10051)
        }
Coverage.statementStart(10052)
        var count: Int
Coverage.statementStart(10053)
        if (columnsINAO.size == 0 && columnsINBO.size == 0) {
Coverage.ifStart(10054)
            res = IteratorBundle(childA.count * childB.count)
Coverage.statementStart(10055)
        } else if (columnsINAO.size == 0) {
Coverage.ifStart(10056)
            if (childA.count > 0) {
Coverage.ifStart(10057)
                for (columnIndex in 0 until columnsINBO.size) {
Coverage.forLoopStart(10058)
                    outO[1][columnIndex].childs.add(ColumnIteratorRepeatIterator(childA.count, columnsINBO[columnIndex]))
Coverage.statementStart(10059)
                }
Coverage.statementStart(10060)
            }
Coverage.statementStart(10061)
            res = IteratorBundle(outMap)
Coverage.statementStart(10062)
        } else if (columnsINBO.size == 0) {
Coverage.ifStart(10063)
            if (childB.count > 0) {
Coverage.ifStart(10064)
                for (columnIndex in 0 until columnsINAO.size) {
Coverage.forLoopStart(10065)
                    outO[0][columnIndex].childs.add(ColumnIteratorRepeatIterator(childB.count, columnsINAO[columnIndex]))
Coverage.statementStart(10066)
                }
Coverage.statementStart(10067)
            }
Coverage.statementStart(10068)
            res = IteratorBundle(outMap)
Coverage.statementStart(10069)
        } else {
Coverage.ifStart(10070)
            val data = Array(columnsINBO.size) { MyListValue() }
Coverage.statementStart(10071)
            loopC@ while (true) {
Coverage.whileLoopStart(10072)
                for (columnIndex in 0 until columnsINBO.size) {
Coverage.forLoopStart(10073)
                    val value = columnsINBO[columnIndex].next()
Coverage.statementStart(10074)
                    if (value == null) {
Coverage.ifStart(10075)
                        break@loopC
                    }
Coverage.statementStart(10076)
                    data[columnIndex].add(value)
Coverage.statementStart(10077)
                }
Coverage.statementStart(10078)
            }
Coverage.statementStart(10079)
            count = data[0].size
Coverage.statementStart(10080)
            if (count > 0 || optional) {
Coverage.ifStart(10081)
                for (iterator in outIterators) {
Coverage.forLoopStart(10082)
                    iterator.close = {
Coverage.statementStart(10083)
                        iterator._close()
Coverage.statementStart(10084)
                        for (variable in children[0].getProvidedVariableNames()) {
Coverage.forLoopStart(10085)
                            childA.columns[variable]!!.close()
Coverage.statementStart(10086)
                        }
Coverage.statementStart(10087)
                        for (variable in children[1].getProvidedVariableNames()) {
Coverage.forLoopStart(10088)
                            childB.columns[variable]!!.close()
Coverage.statementStart(10089)
                        }
Coverage.statementStart(10090)
                    }
Coverage.statementStart(10091)
                    iterator.onNoMoreElements = {
Coverage.statementStart(10092)
                        var done = false
Coverage.statementStart(10093)
                        for (columnIndex in 0 until columnsINAO.size) {
Coverage.forLoopStart(10094)
                            val value = columnsINAO[columnIndex].next()
Coverage.statementStart(10095)
                            if (value == null) {
Coverage.ifStart(10096)
                                SanityCheck.check { columnIndex == 0 }
Coverage.statementStart(10097)
                                done = true
Coverage.statementStart(10098)
                                break
                            }
Coverage.statementStart(10099)
                            outO[0][columnIndex].childs.add(ColumnIteratorRepeatValue(count, value))
Coverage.statementStart(10100)
                        }
Coverage.statementStart(10101)
                        if (!done) {
Coverage.ifStart(10102)
                            for (columnIndex in 0 until columnsINBO.size) {
Coverage.forLoopStart(10103)
                                outO[1][columnIndex].childs.add(ColumnIteratorMultiValue(data[columnIndex]))
Coverage.statementStart(10104)
                            }
Coverage.statementStart(10105)
                        }
Coverage.statementStart(10106)
                    }
Coverage.statementStart(10107)
                }
Coverage.statementStart(10108)
            }
Coverage.statementStart(10109)
            res = IteratorBundle(outMap)
Coverage.statementStart(10110)
        }
Coverage.statementStart(10111)
        return res
    }
    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
    override fun cloneOP() = POPJoinCartesianProduct(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP(), optional)
}
