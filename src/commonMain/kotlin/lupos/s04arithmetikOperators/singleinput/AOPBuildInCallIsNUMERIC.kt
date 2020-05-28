package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallIsNUMERIC(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIsNUMERICID, "AOPBuildInCallIsNUMERIC", arrayOf(child)) {
    override fun toSparql() = "isNumeric(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3088)
        if (other !is AOPBuildInCallIsNUMERIC) {
Coverage.ifStart(3089)
            return false
        }
Coverage.statementStart(3090)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3091)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3092)
        return {
Coverage.statementStart(3093)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3094)
            val a = childA()
Coverage.statementStart(3095)
            if (a !is ValueUndef && a !is ValueError) {
Coverage.ifStart(3096)
                res = ValueBoolean(a is ValueNumeric)
Coverage.statementStart(3097)
            }
Coverage.statementStart(3098)
/*return*/res
        }
Coverage.statementStart(3099)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPBuildInCallIsNUMERIC(query, children[0].cloneOP() as AOPBase)
}
