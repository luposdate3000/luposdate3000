package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import kotlin.math.ceil
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallCEIL(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallCEILID, "AOPBuildInCallCEIL", arrayOf(child)) {
    override fun toSparql() = "CEIL(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallCEIL)
            return false
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            try {
                if (a is ValueDouble)
                    res = ValueDouble(ceil(a.toDouble()))
                else if (a is ValueDecimal)
                    res = ValueDecimal(ceil(a.toDouble()))
                else if (a is ValueInteger)
                    res = a
            } catch (e: Throwable) {
            }
            res
        }
    }

    override fun cloneOP() = AOPBuildInCallCEIL(query, children[0].cloneOP() as AOPBase)
}
