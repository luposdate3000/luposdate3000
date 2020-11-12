package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.math.abs

class AOPBuildInCallABS(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallABSID, "AOPBuildInCallABS", arrayOf(child)) {
    override fun toSparql() = "ABS(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallABS && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueDouble) {
                res = ValueDouble(abs(a.value))
            } else if (a is ValueFloat) {
                res = ValueFloat(abs(a.value))
            } else if (a is ValueDecimal) {
                res = ValueDecimal(a.value.abs())
            } else if (a is ValueInteger) {
                res = ValueInteger(a.value.abs())
            }
/*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallABS(query, children[0].cloneOP() as AOPBase)
}
