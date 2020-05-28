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
class AOPBuildInCallTZ(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallTZID, "AOPBuildInCallTZ", arrayOf(child)) {
    override fun toSparql() = "TZ(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3296)
        if (other !is AOPBuildInCallTZ) {
Coverage.ifStart(3297)
            return false
        }
Coverage.statementStart(3298)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3299)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3300)
        return {
Coverage.statementStart(3301)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3302)
            val a = childA()
Coverage.statementStart(3303)
            if (a is ValueDateTime) {
Coverage.ifStart(3304)
                res = ValueSimpleLiteral("\"", a.getTZ())
Coverage.statementStart(3305)
            }
Coverage.statementStart(3306)
/*return*/res
        }
Coverage.statementStart(3307)
    }
    override fun cloneOP() = AOPBuildInCallTZ(query, children[0].cloneOP() as AOPBase)
}
