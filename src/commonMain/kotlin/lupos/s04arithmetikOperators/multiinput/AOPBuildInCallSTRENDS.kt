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
class AOPBuildInCallSTRENDS(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRENDSID, "AOPBuildInCallSTRENDS", arrayOf(child, childB)) {
    override fun toSparql() = "STRENDS(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2178)
        if (other !is AOPBuildInCallSTRENDS) {
Coverage.ifStart(2179)
            return false
        }
Coverage.statementStart(2180)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2181)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2182)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2183)
        return {
Coverage.statementStart(2184)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2185)
            val a = childA()
Coverage.statementStart(2186)
            val b = childB()
Coverage.statementStart(2187)
            if (a is ValueStringBase && b is ValueSimpleLiteral) {
Coverage.ifStart(2188)
                res = ValueBoolean(a.content.endsWith(b.content))
Coverage.statementStart(2189)
            }
Coverage.statementStart(2190)
/*return*/res
        }
Coverage.statementStart(2191)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPBuildInCallSTRENDS(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
