package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPMultiplication(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPMultiplicationID, "AOPMultiplication", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " * " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPMultiplication)
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
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            try {
                if (a is ValueDouble || b is ValueDouble)
                    res = ValueDouble(a.toDouble() * b.toDouble())
                else if (a is ValueDecimal || b is ValueDecimal)
                    res = ValueDecimal(a.toDouble() * b.toDouble())
                else if (a is ValueInteger || b is ValueInteger)
                    res = ValueInteger(a.toInt() * b.toInt())
            } catch (e: Throwable) {
            }
            res
        }
    }

    override fun cloneOP() = AOPMultiplication(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
