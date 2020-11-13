package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallCONTAINS(query: IQuery, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallCONTAINSID, "AOPBuildInCallCONTAINS", arrayOf(child, childB)) {
    override fun toSparql(): String = "CONTAINS(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallCONTAINS && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            if (a is ValueStringBase && b is ValueSimpleLiteral) {
                res = ValueBoolean(a.content.contains(b.content))
            }
            res
        }
    }

    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPBuildInCallCONTAINS(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
