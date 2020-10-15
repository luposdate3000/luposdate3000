package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallLANG(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallLANGID, "AOPBuildInCallLANG", arrayOf(child)) {
    override fun toSparql() = "LANG(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallLANG && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueLanguageTaggedLiteral) {
                res = ValueSimpleLiteral(a.delimiter, a.language)
            } else if (a is ValueStringBase || a is ValueNumeric || a is ValueBoolean || a is ValueDateTime) {
                res = ValueSimpleLiteral("\"", "")
            }
/*return*/res
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = AOPBuildInCallLANG(query, children[0].cloneOP() as AOPBase)
}
