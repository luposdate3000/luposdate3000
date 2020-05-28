package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPNotIn(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPNotInID, "AOPNotIn", arrayOf(childA, childB)) {
    override fun toSparql() = "( " + children[0].toSparql() + " NOT IN " + children[1].toSparql() + " )"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2525)
        if (other !is AOPNotIn) {
Coverage.ifStart(2526)
            return false
        }
Coverage.statementStart(2527)
        for (i in children.indices) {
Coverage.forLoopStart(2528)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2529)
                return false
            }
Coverage.statementStart(2530)
        }
Coverage.statementStart(2531)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2532)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2533)
        SanityCheck.check { children[1] is AOPSet }
Coverage.statementStart(2534)
        val childsB = Array(children[1].children.size) { (children[1].children[it] as AOPBase).evaluate(row) }
Coverage.statementStart(2535)
        return {
Coverage.statementStart(2536)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2537)
            val a = childA()
Coverage.statementStart(2538)
            var found = false
Coverage.statementStart(2539)
            var noError = true
Coverage.statementStart(2540)
            for (childB in childsB) {
Coverage.forLoopStart(2541)
                try {
Coverage.statementStart(2542)
                    if (childB() == a) {
Coverage.ifStart(2543)
                        found = true
Coverage.statementStart(2544)
                        break
                    }
Coverage.statementStart(2545)
                } catch (e: Throwable) {
Coverage.statementStart(2546)
                    noError = false
Coverage.statementStart(2547)
                }
Coverage.statementStart(2548)
            }
Coverage.statementStart(2549)
            found = !found
Coverage.statementStart(2550)
            if (found || noError) {
Coverage.ifStart(2551)
                res = ValueBoolean(found)
Coverage.statementStart(2552)
            }
Coverage.statementStart(2553)
/*return*/res
        }
Coverage.statementStart(2554)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPNotIn(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
