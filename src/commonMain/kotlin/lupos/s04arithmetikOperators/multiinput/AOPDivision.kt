package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPDivision(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPDivisionID, "AOPDivision", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " / " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2224)
        if (other !is AOPDivision) {
Coverage.ifStart(2225)
            return false
        }
Coverage.statementStart(2226)
        for (i in children.indices) {
Coverage.forLoopStart(2227)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2228)
                return false
            }
Coverage.statementStart(2229)
        }
Coverage.statementStart(2230)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2231)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2232)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2233)
        return {
Coverage.statementStart(2234)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2235)
            val a = childA()
Coverage.statementStart(2236)
            val b = childB()
Coverage.statementStart(2237)
            try {
Coverage.statementStart(2238)
                if (a is ValueDouble || b is ValueDouble) {
Coverage.ifStart(2239)
                    if (b.toDouble() != 0.0) {
Coverage.ifStart(2240)
                        res = ValueDouble(a.toDouble() / b.toDouble())
Coverage.statementStart(2241)
                    }
Coverage.statementStart(2242)
                }
Coverage.statementStart(2243)
                if (a is ValueDecimal || b is ValueDecimal) {
Coverage.ifStart(2244)
                    if (b.toDouble() != 0.0) {
Coverage.ifStart(2245)
                        res = ValueDecimal(a.toDouble() / b.toDouble())
Coverage.statementStart(2246)
                    }
Coverage.statementStart(2247)
                }
Coverage.statementStart(2248)
                if (a is ValueInteger || b is ValueInteger) {
Coverage.ifStart(2249)
                    if (b.toInt() != 0) {
Coverage.ifStart(2250)
                        res = ValueDecimal(a.toDouble() / b.toDouble())
Coverage.statementStart(2251)
                    }
Coverage.statementStart(2252)
                }
Coverage.statementStart(2253)
            } catch (e: Throwable) {
Coverage.statementStart(2254)
            }
Coverage.statementStart(2255)
/*return*/res
        }
Coverage.statementStart(2256)
    }
    override fun cloneOP() = AOPDivision(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
