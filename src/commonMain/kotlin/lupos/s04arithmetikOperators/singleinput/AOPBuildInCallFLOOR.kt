package lupos.s04arithmetikOperators.singleinput
import kotlin.jvm.JvmField
import kotlin.math.floor
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

impoert lupos.s04logicalOperators.iterator.*


class AOPBuildInCallFLOOR(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallFLOORID, "AOPBuildInCallFLOOR", arrayOf(child)) {
    override fun toSparql() = "FLOOR(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallFLOOR)
            return false
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
var res = ValueError()
            val a = childA()
            try {
                if (a is ValueDouble)
                    res = ValueDouble(floor(a.toDouble()))
                else if (a is ValueDecimal)
                    res = ValueDecimal(floor(a.toDouble()))
                else if (a is ValueInteger)
                    res = a
            } catch (e: Throwable) {
            }
        res
        }
    }

    override fun cloneOP() = AOPBuildInCallFLOOR(query, children[0].cloneOP() as AOPBase)
}
