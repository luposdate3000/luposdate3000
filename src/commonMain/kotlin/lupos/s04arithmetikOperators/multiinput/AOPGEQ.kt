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
class AOPGEQ(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPGEQID, "AOPGEQ", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " >= " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2286)
        if (other !is AOPGEQ) {
Coverage.ifStart(2287)
            return false
        }
Coverage.statementStart(2288)
        for (i in children.indices) {
Coverage.forLoopStart(2289)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2290)
                return false
            }
Coverage.statementStart(2291)
        }
Coverage.statementStart(2292)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2293)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2294)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2295)
        return {
Coverage.statementStart(2296)
            var res: ValueDefinition = ResultSetDictionary.errorValue2
Coverage.statementStart(2297)
            val a = childA()
Coverage.statementStart(2298)
            val b = childB()
Coverage.statementStart(2299)
            try {
Coverage.statementStart(2300)
                if (a.compareTo(b) >= 0) {
Coverage.ifStart(2301)
                    res = ResultSetDictionary.booleanTrueValue2
Coverage.statementStart(2302)
                } else {
Coverage.ifStart(2303)
                    res = ResultSetDictionary.booleanFalseValue2
Coverage.statementStart(2304)
                }
Coverage.statementStart(2305)
            } catch (e: Throwable) {
Coverage.statementStart(2306)
            }
Coverage.statementStart(2307)
/*return*/res
        }
Coverage.statementStart(2308)
    }
    override fun evaluateID(row: IteratorBundle): () -> Value {
Coverage.funStart(2309)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2310)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2311)
        return {
Coverage.statementStart(2312)
            var res: Value = ResultSetDictionary.errorValue
Coverage.statementStart(2313)
            val a = childA()
Coverage.statementStart(2314)
            val b = childB()
Coverage.statementStart(2315)
            try {
Coverage.statementStart(2316)
                if (a.compareTo(b) >= 0) {
Coverage.ifStart(2317)
                    res = ResultSetDictionary.booleanTrueValue
Coverage.statementStart(2318)
                } else {
Coverage.ifStart(2319)
                    res = ResultSetDictionary.booleanFalseValue
Coverage.statementStart(2320)
                }
Coverage.statementStart(2321)
            } catch (e: Throwable) {
Coverage.statementStart(2322)
            }
Coverage.statementStart(2323)
/*return*/res
        }
Coverage.statementStart(2324)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPGEQ(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
