package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPAnd(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPAndID, "AOPAnd", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " && " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2029)
        if (other !is AOPAnd) {
Coverage.ifStart(2030)
            return false
        }
Coverage.statementStart(2031)
        for (i in children.indices) {
Coverage.forLoopStart(2032)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2033)
                return false
            }
Coverage.statementStart(2034)
        }
Coverage.statementStart(2035)
        return true
    }
    companion object {
        val truthTable = arrayOf(
                ResultSetDictionary.booleanTrueValue,//T,T
                ResultSetDictionary.booleanFalseValue,//T,F
                ResultSetDictionary.errorValue,//T,E
                ResultSetDictionary.booleanFalseValue,//F,T
                ResultSetDictionary.booleanFalseValue,//F,F
                ResultSetDictionary.booleanFalseValue,//F,E
                ResultSetDictionary.errorValue,//E,T
                ResultSetDictionary.booleanFalseValue,//E,F
                ResultSetDictionary.errorValue//E,E
        )
        val truthTable2 = arrayOf(
                ResultSetDictionary.booleanTrueValue2,//T,T
                ResultSetDictionary.booleanFalseValue2,//T,F
                ResultSetDictionary.errorValue2,//T,E
                ResultSetDictionary.booleanFalseValue2,//F,T
                ResultSetDictionary.booleanFalseValue2,//F,F
                ResultSetDictionary.booleanFalseValue2,//F,E
                ResultSetDictionary.errorValue2,//E,T
                ResultSetDictionary.booleanFalseValue2,//E,F
                ResultSetDictionary.errorValue2//E,E
        )
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2036)
        val childA = (children[0] as AOPBase).evaluateID(row)
Coverage.statementStart(2037)
        val childB = (children[1] as AOPBase).evaluateID(row)
Coverage.statementStart(2038)
        if ((children[0] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2039)
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2040)
                return {
Coverage.statementStart(2041)
                    val a = childA()
Coverage.statementStart(2042)
                    val b = childB()
Coverage.statementStart(2043)
                    /*return*/truthTable2[a * 3 + b]
                }
Coverage.statementStart(2044)
            } else {
Coverage.ifStart(2045)
                return {
Coverage.statementStart(2046)
                    val a = childA()
Coverage.statementStart(2047)
                    val b = query.dictionary.toBooleanOrError(childB())
Coverage.statementStart(2048)
                    /*return*/truthTable2[a * 3 + b]
                }
Coverage.statementStart(2049)
            }
Coverage.statementStart(2050)
        } else {
Coverage.ifStart(2051)
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2052)
                return {
Coverage.statementStart(2053)
                    val a = query.dictionary.toBooleanOrError(childA())
Coverage.statementStart(2054)
                    val b = childB()
Coverage.statementStart(2055)
                    /*return*/truthTable2[a * 3 + b]
                }
Coverage.statementStart(2056)
            } else {
Coverage.ifStart(2057)
                return {
Coverage.statementStart(2058)
                    val a = query.dictionary.toBooleanOrError(childA())
Coverage.statementStart(2059)
                    val b = query.dictionary.toBooleanOrError(childB())
Coverage.statementStart(2060)
                    /*return*/truthTable2[a * 3 + b]
                }
Coverage.statementStart(2061)
            }
Coverage.statementStart(2062)
        }
Coverage.statementStart(2063)
    }
    override fun evaluateID(row: IteratorBundle): () -> Value {
Coverage.funStart(2064)
        val childA = (children[0] as AOPBase).evaluateID(row)
Coverage.statementStart(2065)
        val childB = (children[1] as AOPBase).evaluateID(row)
Coverage.statementStart(2066)
        if ((children[0] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2067)
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2068)
                return {
Coverage.statementStart(2069)
                    val a = childA()
Coverage.statementStart(2070)
                    val b = childB()
Coverage.statementStart(2071)
                    /*return*/truthTable[a * 3 + b]
                }
Coverage.statementStart(2072)
            } else {
Coverage.ifStart(2073)
                return {
Coverage.statementStart(2074)
                    val a = childA()
Coverage.statementStart(2075)
                    val b = query.dictionary.toBooleanOrError(childB())
Coverage.statementStart(2076)
                    /*return*/truthTable[a * 3 + b]
                }
Coverage.statementStart(2077)
            }
Coverage.statementStart(2078)
        } else {
Coverage.ifStart(2079)
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2080)
                return {
Coverage.statementStart(2081)
                    val a = query.dictionary.toBooleanOrError(childA())
Coverage.statementStart(2082)
                    val b = childB()
Coverage.statementStart(2083)
                    /*return*/truthTable[a * 3 + b]
                }
Coverage.statementStart(2084)
            } else {
Coverage.ifStart(2085)
                return {
Coverage.statementStart(2086)
                    val a = query.dictionary.toBooleanOrError(childA())
Coverage.statementStart(2087)
                    val b = query.dictionary.toBooleanOrError(childB())
Coverage.statementStart(2088)
                    /*return*/truthTable[a * 3 + b]
                }
Coverage.statementStart(2089)
            }
Coverage.statementStart(2090)
        }
Coverage.statementStart(2091)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPAnd(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
