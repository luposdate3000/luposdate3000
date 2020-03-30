package lupos.s04arithmetikOperators.multiinput
import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

impoert lupos.s04logicalOperators.iterator.*


class AOPNotIn(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPNotInID, "AOPNotIn", arrayOf(childA, childB)) {
    override fun toSparql() = "( " + children[0].toSparql() + " NOT IN " + children[1].toSparql() + " )"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPNotIn)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
var res = ValueError()
            val a = childA()
            val b = (children[1] as AOPBase)
            var found = false
            try {
                if (b is AOPSet) {
                    for (c in b.children) {
                        val tmp = (c as AOPBase).calculate(row)
                        if (tmp.data[0] == a) {
                            found = true
                            break
                        }
                    }
                }
                res = ValueBoolean(!found)
            } catch (e: Throwable) {
            }
        res
        }
    }

    override fun cloneOP() = AOPNotIn(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
