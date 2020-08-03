package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.Luposdate3000Exception
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPNEQ(query: Query, childA: AOPBase, childB: AOPBase) : AOPBinaryOperationFixedName(query, EOperatorID.AOPNEQID, "AOPNEQ", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " != " + children[1].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPNEQ && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        return {
            var res: ValueDefinition = ResultSetDictionary.booleanFalseValue2
            val a1 = childA()
            val b1 = childB()
            if (a1 != b1) {
if(ResultSetDictionary.isGlobalBNode(a1)||ResultSetDictionary.isGlobalBNode(b1)){ 
res = ResultSetDictionary.booleanTrueValue2
}else{
                val a = query.dictionary.getValue(a1)
                val b = query.dictionary.getValue(b1)
                try {
                    if (a != b) {
                        res = ResultSetDictionary.booleanTrueValue2
                    }
                } catch (e: Luposdate3000Exception) {
                    res = ResultSetDictionary.errorValue2
                } catch (e: Throwable) {
                    res = ResultSetDictionary.errorValue2
                    e.printStackTrace()
      }
          }
            }
/*return*/res
        }
/*Coverage Unreachable*/
    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPNEQ(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
