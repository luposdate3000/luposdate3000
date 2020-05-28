package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallTIMEZONE(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallTIMEZONEID, "AOPBuildInCallTIMEZONE", arrayOf(child)) {
    override fun toSparql() = "TIMEZONE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3284)
        if (other !is AOPBuildInCallTIMEZONE) {
Coverage.ifStart(3285)
            return false
        }
Coverage.statementStart(3286)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3287)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3288)
        return {
Coverage.statementStart(3289)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3290)
            val a = childA()
Coverage.statementStart(3291)
            if (a is ValueDateTime) {
Coverage.ifStart(3292)
                res = ValueSimpleLiteral("\"", a.getTimeZone())
Coverage.statementStart(3293)
            }
Coverage.statementStart(3294)
/*return*/res
        }
Coverage.statementStart(3295)
    }
    override fun cloneOP() = AOPBuildInCallTIMEZONE(query, children[0].cloneOP() as AOPBase)
}
