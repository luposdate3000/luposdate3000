package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallHOURS(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallHOURSID, "AOPBuildInCallHOURS", arrayOf(child)) {
    override fun toSparql() = "HOURS(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3028)
        if (other !is AOPBuildInCallHOURS) {
Coverage.ifStart(3029)
            return false
        }
Coverage.statementStart(3030)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3031)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3032)
        return {
Coverage.statementStart(3033)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3034)
            val a = childA()
Coverage.statementStart(3035)
            if (a is ValueDateTime) {
Coverage.ifStart(3036)
                res = ValueInteger(a.hours)
Coverage.statementStart(3037)
            }
Coverage.statementStart(3038)
/*return*/res
        }
Coverage.statementStart(3039)
    }
    override fun cloneOP() = AOPBuildInCallHOURS(query, children[0].cloneOP() as AOPBase)
}
