package lupos.s04arithmetikOperators.multiinput


import lupos.s00misc.EOperatorID

import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallLANGMATCHES(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallLANGMATCHESID, "AOPBuildInCallLANGMATCHES", arrayOf(child, childB)) {
    override fun toSparql() = "LANGMATCHES(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallLANGMATCHES && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            if (a is ValueSimpleLiteral && b is ValueSimpleLiteral) {
                res = ValueBoolean(a.content == b.content)
            }
/*return*/res
        }

    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP() :IOPBase= AOPBuildInCallLANGMATCHES(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
