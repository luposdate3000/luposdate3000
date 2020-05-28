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
class AOPSubtraction(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPSubtractionID, "AOPSubtraction", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " - " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2637)
        if (other !is AOPSubtraction) {
Coverage.ifStart(2638)
            return false
        }
Coverage.statementStart(2639)
        for (i in children.indices) {
Coverage.forLoopStart(2640)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2641)
                return false
            }
Coverage.statementStart(2642)
        }
Coverage.statementStart(2643)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2644)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2645)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2646)
        return {
Coverage.statementStart(2647)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2648)
            val a = childA()
Coverage.statementStart(2649)
            val b = childB()
Coverage.statementStart(2650)
            try {
Coverage.statementStart(2651)
                if (a is ValueDouble || b is ValueDouble) {
Coverage.ifStart(2652)
                    res = ValueDouble(a.toDouble() - b.toDouble())
Coverage.statementStart(2653)
                } else if (a is ValueDecimal || b is ValueDecimal) {
Coverage.ifStart(2654)
                    res = ValueDecimal(a.toDouble() - b.toDouble())
Coverage.statementStart(2655)
                } else if (a is ValueInteger || b is ValueInteger) {
Coverage.ifStart(2656)
                    res = ValueInteger(a.toInt() - b.toInt())
Coverage.statementStart(2657)
                }
Coverage.statementStart(2658)
            } catch (e: Throwable) {
Coverage.statementStart(2659)
            }
Coverage.statementStart(2660)
/*return*/res
        }
Coverage.statementStart(2661)
    }
    override fun cloneOP() = AOPSubtraction(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
