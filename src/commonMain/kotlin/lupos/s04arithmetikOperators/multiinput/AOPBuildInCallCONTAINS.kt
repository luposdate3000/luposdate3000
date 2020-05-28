package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallCONTAINS(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallCONTAINSID, "AOPBuildInCallCONTAINS", arrayOf(child, childB)) {
    override fun toSparql() = "CONTAINS(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2114)
        if (other !is AOPBuildInCallCONTAINS) {
Coverage.ifStart(2115)
            return false
        }
Coverage.statementStart(2116)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2117)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2118)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2119)
        return {
Coverage.statementStart(2120)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2121)
            val a = childA()
Coverage.statementStart(2122)
            val b = childB()
Coverage.statementStart(2123)
            if (a is ValueStringBase && b is ValueSimpleLiteral) {
Coverage.ifStart(2124)
                res = ValueBoolean(a.content.contains(b.content))
Coverage.statementStart(2125)
            }
Coverage.statementStart(2126)
/*return*/res
        }
Coverage.statementStart(2127)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPBuildInCallCONTAINS(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
