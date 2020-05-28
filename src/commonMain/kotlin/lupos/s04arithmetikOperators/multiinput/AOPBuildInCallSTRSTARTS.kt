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
class AOPBuildInCallSTRSTARTS(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRSTARTSID, "AOPBuildInCallSTRSTARTS", arrayOf(child, childB)) {
    override fun toSparql() = "STRSTARTS(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2210)
        if (other !is AOPBuildInCallSTRSTARTS) {
Coverage.ifStart(2211)
            return false
        }
Coverage.statementStart(2212)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2213)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2214)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2215)
        return {
Coverage.statementStart(2216)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2217)
            val a = childA()
Coverage.statementStart(2218)
            val b = childB()
Coverage.statementStart(2219)
            if (a is ValueStringBase && b is ValueSimpleLiteral) {
Coverage.ifStart(2220)
                res = ValueBoolean(a.content.startsWith(b.content))
Coverage.statementStart(2221)
            }
Coverage.statementStart(2222)
/*return*/res
        }
Coverage.statementStart(2223)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPBuildInCallSTRSTARTS(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
