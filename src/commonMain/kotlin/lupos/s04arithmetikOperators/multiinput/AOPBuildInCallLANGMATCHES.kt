package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallLANGMATCHES(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallLANGMATCHESID, "AOPBuildInCallLANGMATCHES", arrayOf(child, childB)) {
    override fun toSparql() = "LANGMATCHES(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2146)
        if (other !is AOPBuildInCallLANGMATCHES) {
Coverage.ifStart(2147)
            return false
        }
Coverage.statementStart(2148)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2149)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2150)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2151)
        return {
Coverage.statementStart(2152)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2153)
            val a = childA()
Coverage.statementStart(2154)
            val b = childB()
Coverage.statementStart(2155)
            if (a is ValueSimpleLiteral && b is ValueSimpleLiteral) {
Coverage.ifStart(2156)
                res = ValueBoolean(a.content == b.content)
Coverage.statementStart(2157)
            }
Coverage.statementStart(2158)
/*return*/res
        }
Coverage.statementStart(2159)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPBuildInCallLANGMATCHES(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
