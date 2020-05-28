package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallSTRLANG(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRLANGID, "AOPBuildInCallSTRLANG", arrayOf(child, childB)) {
    override fun toSparql() = "STRLANG(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2192)
        if (other !is AOPBuildInCallSTRLANG) {
Coverage.ifStart(2193)
            return false
        }
Coverage.statementStart(2194)
        for (i in children.indices) {
Coverage.forLoopStart(2195)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2196)
                return false
            }
Coverage.statementStart(2197)
        }
Coverage.statementStart(2198)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2199)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2200)
        val childB = (children[1] as AOPBase).evaluate(row)
Coverage.statementStart(2201)
        return {
Coverage.statementStart(2202)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2203)
            val a = childA()
Coverage.statementStart(2204)
            val b = childB()
Coverage.statementStart(2205)
            if (a is ValueSimpleLiteral && b is ValueSimpleLiteral) {
Coverage.ifStart(2206)
                res = ValueLanguageTaggedLiteral(a.delimiter, a.content, b.content)
Coverage.statementStart(2207)
            }
Coverage.statementStart(2208)
/*return*/res
        }
Coverage.statementStart(2209)
    }
    override fun cloneOP() = AOPBuildInCallSTRLANG(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
