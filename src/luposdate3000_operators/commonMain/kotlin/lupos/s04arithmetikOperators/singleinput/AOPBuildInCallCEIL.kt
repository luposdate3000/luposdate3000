package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.math.ceil

class AOPBuildInCallCEIL(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallCEILID, "AOPBuildInCallCEIL", arrayOf(child)) {
    //return integer which is equal or larger than input
    override fun toSparql() = "CEIL(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallCEIL && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            try {
                if (a is ValueDouble) {
                    res = ValueDouble(ceil(a.toDouble()))
                } else if (a is ValueFloat) {
                    res = ValueFloat(ceil(a.toDouble()))
                } else if (a is ValueDecimal) {
                    res = ValueDecimal(a.value.ceil())
                } else if (a is ValueInteger) {
                    res = a
                }
            } catch (e: Throwable) {
                SanityCheck.println { "TODO exception 36" }
                e.printStackTrace()
            }
/*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallCEIL(query, children[0].cloneOP() as AOPBase)
}
