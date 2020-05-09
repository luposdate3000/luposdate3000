package lupos.s04arithmetikOperators.singleinput

import kotlin.math.roundToInt
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallROUND(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallROUNDID, "AOPBuildInCallROUND", arrayOf(child)) {
    override fun toSparql() = "ROUND(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallROUND) {
            return false
        }
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            try {
                if (a is ValueDouble) {
                    res = ValueDouble(a.toDouble().roundToInt().toDouble())
                } else if (a is ValueDecimal) {
                    res = ValueDecimal(a.toDouble().roundToInt().toDouble())
                } else if (a is ValueInteger) {
                    res = a
                }
            } catch (e: Throwable) {
            }
/*return*/res
        }
    }

    override fun cloneOP() = AOPBuildInCallROUND(query, children[0].cloneOP() as AOPBase)
}
