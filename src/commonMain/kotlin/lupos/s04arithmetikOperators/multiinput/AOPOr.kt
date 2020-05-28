package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
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

    companion object {
        val truthTable = arrayOf(
                ResultSetDictionary.booleanTrueValue,//T,T
                ResultSetDictionary.booleanTrueValue,//T,F
                ResultSetDictionary.errorValue,//T,E
                ResultSetDictionary.booleanTrueValue,//F,T
                ResultSetDictionary.booleanFalseValue,//F,F
                ResultSetDictionary.errorValue,//F,E
                ResultSetDictionary.errorValue,//E,T
                ResultSetDictionary.errorValue,//E,F
                ResultSetDictionary.errorValue//E,E
        )
        val truthTable2 = arrayOf(
                ResultSetDictionary.booleanTrueValue2,//T,T
                ResultSetDictionary.booleanTrueValue2,//T,F
                ResultSetDictionary.errorValue2,//T,E
                ResultSetDictionary.booleanTrueValue2,//F,T
                ResultSetDictionary.booleanFalseValue2,//F,F
                ResultSetDictionary.errorValue2,//F,E
                ResultSetDictionary.errorValue2,//E,T
                ResultSetDictionary.errorValue2,//E,F
                ResultSetDictionary.errorValue2//E,E
        )
    }

    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        if ((children[0] as AOPBase).enforcesBooleanOrError()) {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = childA()
                    val b = childB()
                    /*return*/truthTable2[a * 3 + b]
                }
/*Coverage Unreachable*/
            } else {
                return {
                    val a = childA()
                    val b = query.dictionary.toBooleanOrError(childB())
                    /*return*/truthTable2[a * 3 + b]
                }
/*Coverage Unreachable*/
            }
/*Coverage Unreachable*/
        } else {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = query.dictionary.toBooleanOrError(childA())
                    val b = childB()
                    /*return*/truthTable2[a * 3 + b]
                }
/*Coverage Unreachable*/
            } else {
                return {
                    val a = query.dictionary.toBooleanOrError(childA())
                    val b = query.dictionary.toBooleanOrError(childB())
                    /*return*/truthTable2[a * 3 + b]
                }
/*Coverage Unreachable*/
            }
/*Coverage Unreachable*/
        }
/*Coverage Unreachable*/
    }

    override fun evaluateID(row: IteratorBundle): () -> Value {
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        if ((children[0] as AOPBase).enforcesBooleanOrError()) {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = childA()
                    val b = childB()
                    /*return*/truthTable[a * 3 + b]
                }
/*Coverage Unreachable*/
            } else {
                return {
                    val a = childA()
                    val b = query.dictionary.toBooleanOrError(childB())
                    /*return*/truthTable[a * 3 + b]
                }
/*Coverage Unreachable*/
            }
/*Coverage Unreachable*/
        } else {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = query.dictionary.toBooleanOrError(childA())
                    val b = childB()
                    /*return*/truthTable[a * 3 + b]
                }
/*Coverage Unreachable*/
            } else {
                return {
                    val a = query.dictionary.toBooleanOrError(childA())
                    val b = query.dictionary.toBooleanOrError(childB())
                    /*return*/truthTable[a * 3 + b]
                }
/*Coverage Unreachable*/
            }
/*Coverage Unreachable*/
        }
/*Coverage Unreachable*/
    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPOr(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
