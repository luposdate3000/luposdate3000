package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallBOUND(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallBOUNDID, "AOPBuildInCallBOUND", arrayOf(child)) {
    override fun toSparql() = "BOUND(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2943)
        if (other !is AOPBuildInCallBOUND) {
Coverage.ifStart(2944)
            return false
        }
Coverage.statementStart(2945)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2946)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2947)
        return {
Coverage.statementStart(2948)
            val a = childA()
Coverage.statementStart(2949)
/*return*/ValueBoolean(a !is ValueUndef && a !is ValueError)
        }
Coverage.statementStart(2950)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPBuildInCallBOUND(query, children[0].cloneOP() as AOPBase)
}
