package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPIn(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPInID, "AOPIn", arrayOf(childA, childB)) {
    override fun toSparql() = "( " + children[0].toSparql() + " IN " + children[1].toSparql() + " )"
    override fun equals(other: Any?) = other is AOPIn && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        SanityCheck.check { children[1] is AOPSet }
        val childsB = Array(children[1].children.size) { (children[1].children[it] as AOPBase).evaluate(row) }
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            var found = false
            var noError = true
            for (childB in childsB) {
                try {
                    if (childB() == a) {
                        found = true
                        break
                    }
                } catch (e: Throwable) {
                    noError = false
                   SanityCheck.println("TODO exception 22")
                    e.printStackTrace()
                }
            }
            if (found || noError) {
                res = ValueBoolean(found)
            }
/*return*/res
        }
/*Coverage Unreachable*/
    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPIn(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
