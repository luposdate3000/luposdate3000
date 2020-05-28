package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallSTRLEN(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRLENID, "AOPBuildInCallSTRLEN", arrayOf(child)) {
    override fun toSparql() = "STRLEN(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3268)
        if (other !is AOPBuildInCallSTRLEN) {
Coverage.ifStart(3269)
            return false
        }
Coverage.statementStart(3270)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3271)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3272)
        return {
Coverage.statementStart(3273)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3274)
            val a = childA()
Coverage.statementStart(3275)
            if (a is ValueSimpleLiteral) {
Coverage.ifStart(3276)
                res = ValueInteger(a.content.length)
Coverage.statementStart(3277)
            } else if (a is ValueTypedLiteral) {
Coverage.ifStart(3278)
                res = ValueInteger(a.content.length)
Coverage.statementStart(3279)
            } else if (a is ValueLanguageTaggedLiteral) {
Coverage.ifStart(3280)
                res = ValueInteger(a.content.length)
Coverage.statementStart(3281)
            }
Coverage.statementStart(3282)
/*return*/res
        }
Coverage.statementStart(3283)
    }
    override fun cloneOP() = AOPBuildInCallSTRLEN(query, children[0].cloneOP() as AOPBase)
}
