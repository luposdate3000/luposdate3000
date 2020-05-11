package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPAddition(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPAdditionID, "AOPAddition", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " + " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPAddition) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            try {
                if (a is ValueDouble || b is ValueDouble) {
                    res = ValueDouble(a.toDouble() + b.toDouble())
                } else if (a is ValueDecimal || b is ValueDecimal) {
                    res = ValueDecimal(a.toDouble() + b.toDouble())
                } else if (a is ValueInteger || b is ValueInteger) {
                    res = ValueInteger(a.toInt() + b.toInt())
                }
            } catch (e: Throwable) {
            }
/*return*/res
        }
    }

    override fun cloneOP() = AOPAddition(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
