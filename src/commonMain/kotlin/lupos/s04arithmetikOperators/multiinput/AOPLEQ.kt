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
class AOPLEQ(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPLEQID, "AOPLEQ", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " <= " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2393)
        if (other !is AOPLEQ) {
Coverage.ifStart(2394)
            return false
        }
Coverage.statementStart(2395)
        for (i in children.indices) {
Coverage.forLoopStart(2396)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2397)
                return false
            }
Coverage.statementStart(2398)
        }
Coverage.statementStart(2399)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2400)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2401)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2402)
        return {
Coverage.statementStart(2403)
            var res: ValueDefinition = ResultSetDictionary.errorValue2
Coverage.statementStart(2404)
            val a = childA()
Coverage.statementStart(2405)
            val b = childB()
Coverage.statementStart(2406)
            try {
Coverage.statementStart(2407)
                if (a.compareTo(b) <= 0) {
Coverage.ifStart(2408)
                    res = ResultSetDictionary.booleanTrueValue2
Coverage.statementStart(2409)
                } else {
Coverage.ifStart(2410)
                    res = ResultSetDictionary.booleanFalseValue2
Coverage.statementStart(2411)
                }
Coverage.statementStart(2412)
            } catch (e: Throwable) {
Coverage.statementStart(2413)
            }
Coverage.statementStart(2414)
/*return*/res
        }
Coverage.statementStart(2415)
    }
    override fun evaluateID(row: IteratorBundle): () -> Value {
Coverage.funStart(2416)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2417)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2418)
        return {
Coverage.statementStart(2419)
            var res: Value = ResultSetDictionary.errorValue
Coverage.statementStart(2420)
            val a = childA()
Coverage.statementStart(2421)
            val b = childB()
Coverage.statementStart(2422)
            try {
Coverage.statementStart(2423)
                if (a.compareTo(b) <= 0) {
Coverage.ifStart(2424)
                    res = ResultSetDictionary.booleanTrueValue
Coverage.statementStart(2425)
                } else {
Coverage.ifStart(2426)
                    res = ResultSetDictionary.booleanFalseValue
Coverage.statementStart(2427)
                }
Coverage.statementStart(2428)
            } catch (e: Throwable) {
Coverage.statementStart(2429)
            }
Coverage.statementStart(2430)
/*return*/res
        }
Coverage.statementStart(2431)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPLEQ(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
