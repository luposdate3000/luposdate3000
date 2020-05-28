package lupos.s09physicalOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s15tripleStoreDistributed.DistributedTripleStore
class POPModify(query: Query, projectedVariables: List<String>, insert: List<LOPTriple>, delete: List<LOPTriple>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPModifyID, "POPModify", arrayOf(child), ESortPriority.PREVENT_ANY) {
    val modify = Array<Pair<LOPTriple, EModifyType>>(insert.size + delete.size) {
        if (it < insert.size) {
Coverage.ifStart(11927)
            Pair(insert[it], EModifyType.INSERT)
        } else {
Coverage.ifStart(11928)
            Pair(delete[it - insert.size], EModifyType.DELETE)
        }
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(11929)
        if (other !is POPModify) {
Coverage.ifStart(11930)
            return false
        }
Coverage.statementStart(11931)
        if (modify.size != other.modify.size) {
Coverage.ifStart(11932)
            return false
        }
Coverage.statementStart(11933)
        for (i in 0 until modify.size) {
Coverage.forLoopStart(11934)
            if (modify[i] != other.modify[i]) {
Coverage.ifStart(11935)
                return false
            }
Coverage.statementStart(11936)
        }
Coverage.statementStart(11937)
        if (children[0] != other.children[0]) {
Coverage.ifStart(11938)
            return false
        }
Coverage.statementStart(11939)
        return true
    }
    override fun toSparql(): String {
Coverage.funStart(11940)
        var res = StringBuilder()
Coverage.statementStart(11941)
        var insertions = StringBuilder()
Coverage.statementStart(11942)
        var deletions = StringBuilder()
Coverage.statementStart(11943)
        for (m in modify) {
Coverage.forLoopStart(11944)
            if (m.second == EModifyType.INSERT) {
Coverage.ifStart(11945)
                insertions.append(m.first.toSparql() + ".")
Coverage.statementStart(11946)
            } else {
Coverage.ifStart(11947)
                deletions.append(m.first.toSparql() + ".")
Coverage.statementStart(11948)
            }
Coverage.statementStart(11949)
        }
Coverage.statementStart(11950)
        var istring = insertions.toString()
Coverage.statementStart(11951)
        var dstring = deletions.toString()
Coverage.statementStart(11952)
        if (istring.length > 0) {
Coverage.ifStart(11953)
            res.append("INSERT{")
Coverage.statementStart(11954)
            res.append(istring)
Coverage.statementStart(11955)
            res.append("}")
Coverage.statementStart(11956)
        }
Coverage.statementStart(11957)
        if (dstring.length > 0) {
Coverage.ifStart(11958)
            res.append("DELETE{")
Coverage.statementStart(11959)
            res.append(dstring)
Coverage.statementStart(11960)
            res.append("}")
Coverage.statementStart(11961)
        }
Coverage.statementStart(11962)
        res.append("WHERE{")
Coverage.statementStart(11963)
        res.append(children[0].toSparql())
Coverage.statementStart(11964)
        res.append("}")
Coverage.statementStart(11965)
        return res.toString()
    }
    override fun toSparqlQuery() = toSparql()
    override fun getProvidedVariableNames() = listOf<String>("?success")
    override fun getProvidedVariableNamesInternal() = children[0].getProvidedVariableNames()
    override fun getRequiredVariableNames(): List<String> {
Coverage.funStart(11966)
        val res = mutableListOf<String>()
Coverage.statementStart(11967)
        for (action in modify) {
Coverage.forLoopStart(11968)
            if (action.first.graphVar) {
Coverage.ifStart(11969)
                res.add(action.first.graph)
Coverage.statementStart(11970)
            }
Coverage.statementStart(11971)
            for (i in 0 until 3) {
Coverage.forLoopStart(11972)
                val tmp = action.first.children[i]
Coverage.statementStart(11973)
                if (tmp is AOPVariable) {
Coverage.ifStart(11974)
                    res.add(tmp.name)
Coverage.statementStart(11975)
                }
Coverage.statementStart(11976)
            }
Coverage.statementStart(11977)
        }
Coverage.statementStart(11978)
        return res.intersect(children[0].getProvidedVariableNames()).distinct()
    }
    override fun cloneOP(): POPModify {
Coverage.funStart(11979)
        val insert = mutableListOf<LOPTriple>()
Coverage.statementStart(11980)
        val delete = mutableListOf<LOPTriple>()
Coverage.statementStart(11981)
        for (action in modify) {
Coverage.forLoopStart(11982)
            if (action.second == EModifyType.INSERT) {
Coverage.ifStart(11983)
                insert.add(action.first)
Coverage.statementStart(11984)
            } else {
Coverage.ifStart(11985)
                delete.add(action.first)
Coverage.statementStart(11986)
            }
Coverage.statementStart(11987)
        }
Coverage.statementStart(11988)
        return POPModify(query, projectedVariables, insert, delete, children[0].cloneOP())
    }
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11989)
        val variables = children[0].getProvidedVariableNames()
Coverage.statementStart(11990)
        val child = children[0].evaluate()
Coverage.statementStart(11991)
        val columns = Array(variables.size) { child.columns[variables[it]]!! }
Coverage.statementStart(11992)
        val row = Array(variables.size) { ResultSetDictionary.undefValue }
Coverage.statementStart(11993)
        val data = mutableMapOf<String, Array<Array<MyListValue>>>()
Coverage.statementStart(11994)
        loop@ while (true) {
Coverage.whileLoopStart(11995)
            for (columnIndex in 0 until variables.size) {
Coverage.forLoopStart(11996)
                val value = columns[columnIndex].next()
Coverage.statementStart(11997)
                if (value == null) {
Coverage.ifStart(11998)
                    SanityCheck.check { columnIndex == 0 }
Coverage.statementStart(11999)
                    break@loop
                }
Coverage.statementStart(12000)
                row[columnIndex] = value
Coverage.statementStart(12001)
            }
Coverage.statementStart(12002)
            for (action in modify) {
Coverage.forLoopStart(12003)
                var graphVarIdx = 0
Coverage.statementStart(12004)
                if (action.first.graphVar) {
Coverage.ifStart(12005)
                    SanityCheck.check { variables.contains(action.first.graph) }
Coverage.statementStart(12006)
                    while (variables[graphVarIdx] != action.first.graph) {
Coverage.whileLoopStart(12007)
                        graphVarIdx++
Coverage.statementStart(12008)
                    }
Coverage.statementStart(12009)
                }
Coverage.statementStart(12010)
                var graphName: String
Coverage.statementStart(12011)
                if (action.first.graphVar) {
Coverage.ifStart(12012)
                    graphName = query.dictionary.getValue(row[graphVarIdx]).valueToString()!!
Coverage.statementStart(12013)
                } else {
Coverage.ifStart(12014)
                    graphName = action.first.graph
Coverage.statementStart(12015)
                }
Coverage.statementStart(12016)
                if (data[graphName] == null) {
Coverage.ifStart(12017)
                    data[graphName] = Array(EModifyType.values().size) { Array(3) { MyListValue() } }
Coverage.statementStart(12018)
                }
Coverage.statementStart(12019)
                val target = data[graphName]!![action.second.ordinal]
Coverage.statementStart(12020)
                loop2@ for (columnIndex in 0 until 3) {
Coverage.forLoopStart(12021)
                    val tmp = action.first.children[columnIndex]
Coverage.statementStart(12022)
                    if (tmp is AOPConstant) {
Coverage.ifStart(12023)
                        target[columnIndex].add(tmp.value)
Coverage.statementStart(12024)
                    } else {
Coverage.ifStart(12025)
                        for (columnIndex2 in 0 until variables.size) {
Coverage.forLoopStart(12026)
                            if (variables[columnIndex2] == (tmp as AOPVariable).name) {
Coverage.ifStart(12027)
                                target[columnIndex].add(row[columnIndex2])
Coverage.statementStart(12028)
                                continue@loop2
                            }
Coverage.statementStart(12029)
                        }
Coverage.statementStart(12030)
                        SanityCheck.check { false }
Coverage.statementStart(12031)
                    }
Coverage.statementStart(12032)
                }
Coverage.statementStart(12033)
            }
Coverage.statementStart(12034)
        }
Coverage.statementStart(12035)
        for ((graphName, iterator) in data) {
Coverage.forLoopStart(12036)
            val store = DistributedTripleStore.getNamedGraph(query, graphName)
Coverage.statementStart(12037)
            for (type in EModifyType.values()) {
Coverage.forLoopStart(12038)
                if (iterator[type.ordinal][0].size > 0) {
Coverage.ifStart(12039)
                    store.modify(Array(3) { ColumnIteratorMultiValue(iterator[type.ordinal][it]) }, type)
Coverage.statementStart(12040)
                }
Coverage.statementStart(12041)
            }
Coverage.statementStart(12042)
        }
Coverage.statementStart(12043)
        return IteratorBundle(mapOf("?success" to ColumnIteratorRepeatValue(1, query.dictionary.createValue(ValueBoolean(true)))))
    }
}
