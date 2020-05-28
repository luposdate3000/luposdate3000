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
class AOPBuildInCallMINUTES(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallMINUTESID, "AOPBuildInCallMINUTES", arrayOf(child)) {
    override fun toSparql() = "MINUTES(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3152)
        if (other !is AOPBuildInCallMINUTES) {
Coverage.ifStart(3153)
            return false
        }
Coverage.statementStart(3154)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3155)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3156)
        return {
Coverage.statementStart(3157)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3158)
            val a = childA()
Coverage.statementStart(3159)
            if (a is ValueDateTime) {
Coverage.ifStart(3160)
                res = ValueInteger(a.minutes)
Coverage.statementStart(3161)
            }
Coverage.statementStart(3162)
/*return*/res
        }
Coverage.statementStart(3163)
    }
    override fun cloneOP() = AOPBuildInCallMINUTES(query, children[0].cloneOP() as AOPBase)
}
