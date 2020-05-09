package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query



class AOPGEQ(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPGEQID, "AOPGEQ", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " >= " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPGEQ) {
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
            var res: ValueDefinition = ResultSetDictionary.errorValue2
            val a = childA()
            val b = childB()
            try {
                if (a.compareTo(b) >= 0) {
                    res = ResultSetDictionary.booleanTrueValue2
                } else {
                    res = ResultSetDictionary.booleanFalseValue2
                }
            } catch (e: Throwable) {
            }
/*return*/res
        }
    }

    override fun evaluateID(row: ColumnIteratorRow): () -> Value {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: Value = ResultSetDictionary.errorValue
            val a = childA()
            val b = childB()
            try {
                if (a.compareTo(b) >= 0) {
                    res = ResultSetDictionary.booleanTrueValue
                } else {
                    res = ResultSetDictionary.booleanFalseValue
                }
            } catch (e: Throwable) {
            }
/*return*/res
        }
    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPGEQ(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
