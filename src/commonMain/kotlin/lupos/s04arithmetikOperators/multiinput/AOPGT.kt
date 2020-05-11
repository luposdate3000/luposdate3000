package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPGT(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPGTID, "AOPGT", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " > " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPGT) {
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
            var res: ValueDefinition = ResultSetDictionary.errorValue2
            val a = childA()
            val b = childB()
            try {
                if (a.compareTo(b) > 0) {
                    res = ResultSetDictionary.booleanTrueValue2
                } else {
                    res = ResultSetDictionary.booleanFalseValue2
                }
            } catch (e: Throwable) {
            }
/*return*/res
        }
    }

    override fun evaluateID(row: IteratorBundle): () -> Value {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: Value = ResultSetDictionary.errorValue
            val a = childA()
            val b = childB()
            try {
                if (a.compareTo(b) > 0) {
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
    override fun cloneOP() = AOPGT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
