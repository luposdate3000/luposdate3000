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
class AOPBuildInCallDAY(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallDAYID, "AOPBuildInCallDAY", arrayOf(child)) {
    override fun toSparql() = "DAY(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2997)
        if (other !is AOPBuildInCallDAY) {
Coverage.ifStart(2998)
            return false
        }
Coverage.statementStart(2999)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3000)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3001)
        return {
Coverage.statementStart(3002)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3003)
            val a = childA()
Coverage.statementStart(3004)
            if (a is ValueDateTime) {
Coverage.ifStart(3005)
                res = ValueInteger(a.day)
Coverage.statementStart(3006)
            }
Coverage.statementStart(3007)
/*return*/res
        }
Coverage.statementStart(3008)
    }
    override fun cloneOP() = AOPBuildInCallDAY(query, children[0].cloneOP() as AOPBase)
}
