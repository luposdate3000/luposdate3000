package lupos.s04arithmetikOperators.singleinput


import lupos.s00misc.EOperatorID

import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallTIMEZONE(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallTIMEZONEID, "AOPBuildInCallTIMEZONE", arrayOf(child)) {
    override fun toSparql() = "TIMEZONE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallTIMEZONE && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueDateTime) {
                res = ValueSimpleLiteral("\"", a.getTimeZone())
            }
/*return*/res
        }

    }

    override fun cloneOP() :IOPBase= AOPBuildInCallTIMEZONE(query, children[0].cloneOP() as AOPBase)
}
