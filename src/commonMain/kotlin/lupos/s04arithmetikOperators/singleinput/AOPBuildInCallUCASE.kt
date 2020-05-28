package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallUCASE(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallUCASEID, "AOPBuildInCallUCASE", arrayOf(child)) {
    override fun toSparql() = "UCASE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3308)
        if (other !is AOPBuildInCallUCASE) {
Coverage.ifStart(3309)
            return false
        }
Coverage.statementStart(3310)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3311)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3312)
        return {
Coverage.statementStart(3313)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3314)
            val a = childA()
Coverage.statementStart(3315)
            if (a is ValueLanguageTaggedLiteral) {
Coverage.ifStart(3316)
                res = ValueLanguageTaggedLiteral(a.delimiter, a.content.toUpperCase(), a.language)
Coverage.statementStart(3317)
            } else if (a is ValueTypedLiteral) {
Coverage.ifStart(3318)
                res = ValueTypedLiteral(a.delimiter, a.content.toUpperCase(), a.type_iri)
Coverage.statementStart(3319)
            } else if (a is ValueSimpleLiteral) {
Coverage.ifStart(3320)
                res = ValueSimpleLiteral(a.delimiter, a.content.toUpperCase())
Coverage.statementStart(3321)
            }
Coverage.statementStart(3322)
/*return*/res
        }
Coverage.statementStart(3323)
    }
    override fun cloneOP() = AOPBuildInCallUCASE(query, children[0].cloneOP() as AOPBase)
}
