package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPIn(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPInID, "AOPIn", arrayOf(childA, childB)) {
    override fun toSparql() = "( " + children[0].toSparql() + " IN " + children[1].toSparql() + " )"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPIn) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        require(children[1] is AOPSet)
        val childsB = Array(children[1].children.size) { (children[1].children[it] as AOPBase).evaluate(row) }
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            var found = false
            var noError = true
            for (childB in childsB) {
                try {
                    if (childB() == a) {
                        found = true
                        break
                    }
                } catch (e: Throwable) {
                    noError = false
                }
            }
            if (found || noError) {
                res = ValueBoolean(found)
            }
            res
        }
    }

    override fun cloneOP() = AOPIn(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
