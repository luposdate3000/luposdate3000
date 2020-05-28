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
class AOPIn(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPInID, "AOPIn", arrayOf(childA, childB)) {
    override fun toSparql() = "( " + children[0].toSparql() + " IN " + children[1].toSparql() + " )"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2364)
        if (other !is AOPIn) {
Coverage.ifStart(2365)
            return false
        }
Coverage.statementStart(2366)
        for (i in children.indices) {
Coverage.forLoopStart(2367)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2368)
                return false
            }
Coverage.statementStart(2369)
        }
Coverage.statementStart(2370)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2371)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2372)
        SanityCheck.check { children[1] is AOPSet }
Coverage.statementStart(2373)
        val childsB = Array(children[1].children.size) { (children[1].children[it] as AOPBase).evaluate(row) }
Coverage.statementStart(2374)
        return {
Coverage.statementStart(2375)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2376)
            val a = childA()
Coverage.statementStart(2377)
            var found = false
Coverage.statementStart(2378)
            var noError = true
Coverage.statementStart(2379)
            for (childB in childsB) {
Coverage.forLoopStart(2380)
                try {
Coverage.statementStart(2381)
                    if (childB() == a) {
Coverage.ifStart(2382)
                        found = true
Coverage.statementStart(2383)
                        break
                    }
Coverage.statementStart(2384)
                } catch (e: Throwable) {
Coverage.statementStart(2385)
                    noError = false
Coverage.statementStart(2386)
                }
Coverage.statementStart(2387)
            }
Coverage.statementStart(2388)
            if (found || noError) {
Coverage.ifStart(2389)
                res = ValueBoolean(found)
Coverage.statementStart(2390)
            }
Coverage.statementStart(2391)
/*return*/res
        }
Coverage.statementStart(2392)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPIn(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
