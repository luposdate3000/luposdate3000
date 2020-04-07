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

class AOPLT(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPLTID, "AOPLT", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " < " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPLT) {
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
if(a.compareTo(b) < 0)
res=ResultSetDictionary.booleanTrueValue2
else
res=ResultSetDictionary.booleanFalseValue2
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
if(a.compareTo(b) < 0)
res=ResultSetDictionary.booleanTrueValue
else
res=ResultSetDictionary.booleanFalseValue
            } catch (e: Throwable) {
            }
/*return*/res
        }
    }
    override fun cloneOP() = AOPLT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
