package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPOr(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPOrID, "AOPOr", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " || " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPOr) {
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
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            var a: ValueDefinition = ValueError()
            var b: ValueDefinition = ValueError()
            try {
                a = ValueBoolean(childA().toBoolean())
            } catch (e: Throwable) {
            }
            try {
                b = ValueBoolean(childB().toBoolean())
            } catch (e: Throwable) {
            }
            if (a is ValueBoolean && b is ValueBoolean) {
                res = ValueBoolean(a.value || b.value)
            } else if (a is ValueError && b is ValueBoolean && b.value == true) {
                res = b
            } else if (b is ValueError && a is ValueBoolean && a.value == true) {
                res = a
            }
/*return*/             res
        }
    }

    override fun cloneOP() = AOPOr(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
