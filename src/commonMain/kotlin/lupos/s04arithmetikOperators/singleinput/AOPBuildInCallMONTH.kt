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
class AOPBuildInCallMONTH(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallMONTHID, "AOPBuildInCallMONTH", arrayOf(child)) {
    override fun toSparql() = "MONTH(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3164)
        if (other !is AOPBuildInCallMONTH) {
Coverage.ifStart(3165)
            return false
        }
Coverage.statementStart(3166)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3167)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3168)
        return {
Coverage.statementStart(3169)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3170)
            val a = childA()
Coverage.statementStart(3171)
            if (a is ValueDateTime) {
Coverage.ifStart(3172)
                res = ValueInteger(a.month)
Coverage.statementStart(3173)
            }
Coverage.statementStart(3174)
/*return*/res
        }
Coverage.statementStart(3175)
    }
    override fun cloneOP() = AOPBuildInCallMONTH(query, children[0].cloneOP() as AOPBase)
}
