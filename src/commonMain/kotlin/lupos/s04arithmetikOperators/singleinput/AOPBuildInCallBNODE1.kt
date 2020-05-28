package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallBNODE1(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallBNODE1ID, "AOPBuildInCallBNODE1", arrayOf(child)) {
    override fun toSparql() = "BNODE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2935)
        if (other !is AOPBuildInCallBNODE1) {
Coverage.ifStart(2936)
            return false
        }
Coverage.statementStart(2937)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2938)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2939)
        return {
Coverage.statementStart(2940)
            val a = childA()
Coverage.statementStart(2941)
/*return*/ValueBnode("" + uuid + a.valueToString())
        }
Coverage.statementStart(2942)
    }
    override fun cloneOP() = AOPBuildInCallBNODE1(query, children[0].cloneOP() as AOPBase)
}
