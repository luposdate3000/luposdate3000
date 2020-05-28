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
class AOPNEQ(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPNEQID, "AOPNEQ", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " != " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2496)
        if (other !is AOPNEQ) {
Coverage.ifStart(2497)
            return false
        }
Coverage.statementStart(2498)
        for (i in children.indices) {
Coverage.forLoopStart(2499)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2500)
                return false
            }
Coverage.statementStart(2501)
        }
Coverage.statementStart(2502)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2503)
        val childA = (children[0] as AOPBase).evaluateID(row)
Coverage.statementStart(2504)
        val childB = (children[1] as AOPBase).evaluateID(row)
Coverage.statementStart(2505)
        return {
Coverage.statementStart(2506)
            var res = ResultSetDictionary.booleanTrueValue2
Coverage.statementStart(2507)
            val a = childA()
Coverage.statementStart(2508)
            val b = childB()
Coverage.statementStart(2509)
            if (a == b) {
Coverage.ifStart(2510)
                res = ResultSetDictionary.booleanFalseValue2
Coverage.statementStart(2511)
            }
Coverage.statementStart(2512)
/*return*/res
        }
Coverage.statementStart(2513)
    }
    override fun evaluateID(row: IteratorBundle): () -> Value {
Coverage.funStart(2514)
        val childA = (children[0] as AOPBase).evaluateID(row)
Coverage.statementStart(2515)
        val childB = (children[1] as AOPBase).evaluateID(row)
Coverage.statementStart(2516)
        return {
Coverage.statementStart(2517)
            var res = ResultSetDictionary.booleanTrueValue
Coverage.statementStart(2518)
            val a = childA()
Coverage.statementStart(2519)
            val b = childB()
Coverage.statementStart(2520)
            if (a == b) {
Coverage.ifStart(2521)
                res = ResultSetDictionary.booleanFalseValue
Coverage.statementStart(2522)
            }
Coverage.statementStart(2523)
/*return*/res
        }
Coverage.statementStart(2524)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPNEQ(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
