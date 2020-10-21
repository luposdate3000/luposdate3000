package lupos.s04arithmetikOperators.singleinput


import lupos.s00misc.EOperatorID

import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallBNODE1(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallBNODE1ID, "AOPBuildInCallBNODE1", arrayOf(child)) {
    override fun toSparql() = "BNODE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallBNODE1 && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            val a = childA()
/*return*/ValueBnode("" + uuid + a.valueToString())
        }

    }

    override fun cloneOP() :IOPBase= AOPBuildInCallBNODE1(query, children[0].cloneOP() as AOPBase)
}
