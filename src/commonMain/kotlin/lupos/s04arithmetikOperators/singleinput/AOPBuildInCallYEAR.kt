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
class AOPBuildInCallYEAR(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallYEARID, "AOPBuildInCallYEAR", arrayOf(child)) {
    override fun toSparql() = "YEAR(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3348)
        if (other !is AOPBuildInCallYEAR) {
Coverage.ifStart(3349)
            return false
        }
Coverage.statementStart(3350)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3351)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3352)
        return {
Coverage.statementStart(3353)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3354)
            val a = childA()
Coverage.statementStart(3355)
            if (a is ValueDateTime) {
Coverage.ifStart(3356)
                res = ValueInteger(a.year)
Coverage.statementStart(3357)
            }
Coverage.statementStart(3358)
/*return*/res
        }
Coverage.statementStart(3359)
    }
    override fun cloneOP() = AOPBuildInCallYEAR(query, children[0].cloneOP() as AOPBase)
}
