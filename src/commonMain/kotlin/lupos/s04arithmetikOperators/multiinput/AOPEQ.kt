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
class AOPEQ(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPEQID, "AOPEQ", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " = " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2257)
        if (other !is AOPEQ) {
Coverage.ifStart(2258)
            return false
        }
Coverage.statementStart(2259)
        for (i in children.indices) {
Coverage.forLoopStart(2260)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2261)
                return false
            }
Coverage.statementStart(2262)
        }
Coverage.statementStart(2263)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2264)
        val childA = (children[0] as AOPBase).evaluateID(row)
Coverage.statementStart(2265)
        val childB = (children[1] as AOPBase).evaluateID(row)
Coverage.statementStart(2266)
        return {
Coverage.statementStart(2267)
            var res = ResultSetDictionary.booleanTrueValue2
Coverage.statementStart(2268)
            val a = childA()
Coverage.statementStart(2269)
            val b = childB()
Coverage.statementStart(2270)
            if (a != b) {
Coverage.ifStart(2271)
                res = ResultSetDictionary.booleanFalseValue2
Coverage.statementStart(2272)
            }
Coverage.statementStart(2273)
/*return*/res
        }
Coverage.statementStart(2274)
    }
    override fun evaluateID(row: IteratorBundle): () -> Value {
Coverage.funStart(2275)
        val childA = (children[0] as AOPBase).evaluateID(row)
Coverage.statementStart(2276)
        val childB = (children[1] as AOPBase).evaluateID(row)
Coverage.statementStart(2277)
        return {
Coverage.statementStart(2278)
            var res = ResultSetDictionary.booleanTrueValue
Coverage.statementStart(2279)
            val a = childA()
Coverage.statementStart(2280)
            val b = childB()
Coverage.statementStart(2281)
            if (a != b) {
Coverage.ifStart(2282)
                res = ResultSetDictionary.booleanFalseValue
Coverage.statementStart(2283)
            }
Coverage.statementStart(2284)
/*return*/res
        }
Coverage.statementStart(2285)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPEQ(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
