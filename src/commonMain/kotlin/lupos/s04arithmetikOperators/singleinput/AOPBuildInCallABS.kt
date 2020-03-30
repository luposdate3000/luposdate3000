package lupos.s04arithmetikOperators.singleinput
import kotlin.jvm.JvmField
import kotlin.math.abs
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

impoert lupos.s04logicalOperators.iterator.*


class AOPBuildInCallABS(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallABSID, "AOPBuildInCallABS", arrayOf(child)) {
    override fun toSparql() = "ABS(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallABS)
            return false
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
var res = ValueError()
            val a = childA()
            if (a is ValueDouble)
                res = ValueDouble(abs(a.value))
            else if (a is ValueDecimal)
                res = ValueDecimal(abs(a.value))
            else if (a is ValueInteger)
                res = ValueInteger(abs(a.value))
        res
        }
    }

    override fun cloneOP() = AOPBuildInCallABS(query, children[0].cloneOP() as AOPBase)
}
