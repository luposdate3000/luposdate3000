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
class AOPBuildInCallLCASE(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallLCASEID, "AOPBuildInCallLCASE", arrayOf(child)) {
    override fun toSparql() = "LCASE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3114)
        if (other !is AOPBuildInCallLCASE) {
Coverage.ifStart(3115)
            return false
        }
Coverage.statementStart(3116)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3117)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3118)
        return {
Coverage.statementStart(3119)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3120)
            val a = childA()
Coverage.statementStart(3121)
            if (a is ValueLanguageTaggedLiteral) {
Coverage.ifStart(3122)
                res = ValueLanguageTaggedLiteral(a.delimiter, a.content.toLowerCase(), a.language)
Coverage.statementStart(3123)
            } else if (a is ValueTypedLiteral) {
Coverage.ifStart(3124)
                res = ValueTypedLiteral(a.delimiter, a.content.toLowerCase(), a.type_iri)
Coverage.statementStart(3125)
            } else if (a is ValueSimpleLiteral) {
Coverage.ifStart(3126)
                res = ValueSimpleLiteral(a.delimiter, a.content.toLowerCase())
Coverage.statementStart(3127)
            }
Coverage.statementStart(3128)
/*return*/res
        }
Coverage.statementStart(3129)
    }
    override fun cloneOP() = AOPBuildInCallLCASE(query, children[0].cloneOP() as AOPBase)
}
