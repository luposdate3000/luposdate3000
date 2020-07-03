package lupos.s04arithmetikOperators.singleinput

import kotlin.math.roundToInt
import lupos.s00misc.BigDecimal
import lupos.s00misc.BigInteger
import lupos.s00misc.Coverage
import lupos.s00misc.DecimalHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.decimalZero
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallROUND(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallROUNDID, "AOPBuildInCallROUND", arrayOf(child)) {
    override fun toSparql() = "ROUND(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallROUND && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            try {
                if (a is ValueDouble) {
                    res = ValueDouble(a.toDouble().roundToInt().toDouble())
                } else if (a is ValueFloat) {
                    res = ValueFloat(a.toDouble().roundToInt().toDouble())
                } else if (a is ValueDecimal) {
                    res = ValueDecimal(DecimalHelper.round(a.value))
                    SanityCheck.println({ "AOPBuildInCallROUND A ${a.value.toString()} = ${(res as ValueDecimal).value.toString()}" })
                } else if (a is ValueInteger) {
                    res = a
                }
            } catch (e: Throwable) {
                SanityCheck.println({ "TODO exception 33" })
                e.printStackTrace()
            }
/*return*/res
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = AOPBuildInCallROUND(query, children[0].cloneOP() as AOPBase)
}
