package lupos.s04arithmetikOperators.singleinput

import kotlin.math.abs
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallABS(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallABSID, "AOPBuildInCallABS", arrayOf(child)) {
    override fun toSparql(): String = "ABS(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallABS && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            when (val a = childA()) {
                is ValueDouble -> {
                    res = ValueDouble(abs(a.value))
                }
                is ValueFloat -> {
                    res = ValueFloat(abs(a.value))
                }
                is ValueDecimal -> {
                    res = ValueDecimal(a.value.abs())
                }
                is ValueInteger -> {
                    res = ValueInteger(a.value.abs())
                }
            }
/*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallABS(query, children[0].cloneOP() as AOPBase)
}
