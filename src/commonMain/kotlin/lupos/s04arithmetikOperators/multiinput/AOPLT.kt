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
class AOPLT(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPLTID, "AOPLT", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " < " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2432)
        if (other !is AOPLT) {
Coverage.ifStart(2433)
            return false
        }
Coverage.statementStart(2434)
        for (i in children.indices) {
Coverage.forLoopStart(2435)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2436)
                return false
            }
Coverage.statementStart(2437)
        }
Coverage.statementStart(2438)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2439)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2440)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2441)
        return {
Coverage.statementStart(2442)
            var res: ValueDefinition = ResultSetDictionary.errorValue2
Coverage.statementStart(2443)
            val a = childA()
Coverage.statementStart(2444)
            val b = childB()
Coverage.statementStart(2445)
            try {
Coverage.statementStart(2446)
                if (a.compareTo(b) < 0) {
Coverage.ifStart(2447)
                    res = ResultSetDictionary.booleanTrueValue2
Coverage.statementStart(2448)
                } else {
Coverage.ifStart(2449)
                    res = ResultSetDictionary.booleanFalseValue2
Coverage.statementStart(2450)
                }
Coverage.statementStart(2451)
            } catch (e: Throwable) {
Coverage.statementStart(2452)
            }
Coverage.statementStart(2453)
/*return*/res
        }
Coverage.statementStart(2454)
    }
    override fun evaluateID(row: IteratorBundle): () -> Value {
Coverage.funStart(2455)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2456)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2457)
        return {
Coverage.statementStart(2458)
            var res: Value = ResultSetDictionary.errorValue
Coverage.statementStart(2459)
            val a = childA()
Coverage.statementStart(2460)
            val b = childB()
Coverage.statementStart(2461)
            try {
Coverage.statementStart(2462)
                if (a.compareTo(b) < 0) {
Coverage.ifStart(2463)
                    res = ResultSetDictionary.booleanTrueValue
Coverage.statementStart(2464)
                } else {
Coverage.ifStart(2465)
                    res = ResultSetDictionary.booleanFalseValue
Coverage.statementStart(2466)
                }
Coverage.statementStart(2467)
            } catch (e: Throwable) {
Coverage.statementStart(2468)
            }
Coverage.statementStart(2469)
/*return*/res
        }
Coverage.statementStart(2470)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPLT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
