package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallIsNUMERIC(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIsNUMERICID, "AOPBuildInCallIsNUMERIC", arrayOf(child)) {
    override fun toSparql() = "isNumeric(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallIsNUMERIC && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a !is ValueUndef && a !is ValueError) {
                res = ValueBoolean(a is ValueNumeric)
            }
/*return*/res
        }
    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP(): IOPBase = AOPBuildInCallIsNUMERIC(query, children[0].cloneOP() as AOPBase)
}
