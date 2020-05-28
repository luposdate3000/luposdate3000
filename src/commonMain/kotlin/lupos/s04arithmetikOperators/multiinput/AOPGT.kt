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
class AOPGT(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPGTID, "AOPGT", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " > " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2325)
        if (other !is AOPGT) {
Coverage.ifStart(2326)
            return false
        }
Coverage.statementStart(2327)
        for (i in children.indices) {
Coverage.forLoopStart(2328)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2329)
                return false
            }
Coverage.statementStart(2330)
        }
Coverage.statementStart(2331)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2332)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2333)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2334)
        return {
Coverage.statementStart(2335)
            var res: ValueDefinition = ResultSetDictionary.errorValue2
Coverage.statementStart(2336)
            val a = childA()
Coverage.statementStart(2337)
            val b = childB()
Coverage.statementStart(2338)
            try {
Coverage.statementStart(2339)
                if (a.compareTo(b) > 0) {
Coverage.ifStart(2340)
                    res = ResultSetDictionary.booleanTrueValue2
Coverage.statementStart(2341)
                } else {
Coverage.ifStart(2342)
                    res = ResultSetDictionary.booleanFalseValue2
Coverage.statementStart(2343)
                }
Coverage.statementStart(2344)
            } catch (e: Throwable) {
Coverage.statementStart(2345)
            }
Coverage.statementStart(2346)
/*return*/res
        }
Coverage.statementStart(2347)
    }
    override fun evaluateID(row: IteratorBundle): () -> Value {
Coverage.funStart(2348)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2349)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2350)
        return {
Coverage.statementStart(2351)
            var res: Value = ResultSetDictionary.errorValue
Coverage.statementStart(2352)
            val a = childA()
Coverage.statementStart(2353)
            val b = childB()
Coverage.statementStart(2354)
            try {
Coverage.statementStart(2355)
                if (a.compareTo(b) > 0) {
Coverage.ifStart(2356)
                    res = ResultSetDictionary.booleanTrueValue
Coverage.statementStart(2357)
                } else {
Coverage.ifStart(2358)
                    res = ResultSetDictionary.booleanFalseValue
Coverage.statementStart(2359)
                }
Coverage.statementStart(2360)
            } catch (e: Throwable) {
Coverage.statementStart(2361)
            }
Coverage.statementStart(2362)
/*return*/res
        }
Coverage.statementStart(2363)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPGT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
