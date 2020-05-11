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

class AOPEQ(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPEQID, "AOPEQ", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " = " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPEQ) {
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
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        return {
            var res = ResultSetDictionary.booleanTrueValue2
            val a = childA()
            val b = childB()
            if (a != b) {
                res = ResultSetDictionary.booleanFalseValue2
            }
/*return*/res
        }
    }

    override fun evaluateID(row: IteratorBundle): () -> Value {
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        return {
            var res = ResultSetDictionary.booleanTrueValue
            val a = childA()
            val b = childB()
            if (a != b) {
                res = ResultSetDictionary.booleanFalseValue
            }
/*return*/res
        }
    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPEQ(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
