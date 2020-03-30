package lupos.s04arithmetikOperators.multiinput
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

impoert lupos.s04logicalOperators.iterator.*


class AOPGEQ(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPGEQID, "AOPGEQ", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " >= " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPGEQ)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
var res = ValueError()
            val a = childA()
            val b = childB()
            try {
                res = ValueBoolean(a.compareTo(b) >= 0)
            } catch (e: Throwable) {
            }
        res
        }
    }

    override fun cloneOP() = AOPGEQ(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
