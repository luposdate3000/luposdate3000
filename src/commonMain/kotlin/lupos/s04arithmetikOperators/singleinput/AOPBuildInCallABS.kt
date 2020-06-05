package lupos.s04arithmetikOperators.singleinput

import kotlin.math.abs
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallABS(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallABSID, "AOPBuildInCallABS", arrayOf(child)) {
    override fun toSparql() = "ABS(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallABS && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueDouble) {
                res = ValueDouble(abs(a.value))
            } else if (a is ValueDecimal) {
                res = ValueDecimal(abs(a.value))
            } else if (a is ValueFloat) {
                res = ValueFloat(abs(a.value))
            } else if (a is ValueInteger) {
                res = ValueInteger(abs(a.value))
            }
/*return*/res
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = AOPBuildInCallABS(query, children[0].cloneOP() as AOPBase)
}
