package lupos.s04arithmetikOperators.multiinput
import lupos.s04logicalOperators.IQuery

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultSetDictionaryExt

import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query

class AOPAnd(query: IQuery, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPAndID, "AOPAnd", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " && " + children[1].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPAnd && children[0] == other.children[0] && children[1] == other.children[1]

internal    companion object {
        val truthTable = arrayOf(
                ResultSetDictionaryExt.booleanTrueValue,//T,T
                ResultSetDictionaryExt.booleanFalseValue,//T,F
                ResultSetDictionaryExt.errorValue,//T,E
                ResultSetDictionaryExt.booleanFalseValue,//F,T
                ResultSetDictionaryExt.booleanFalseValue,//F,F
                ResultSetDictionaryExt.booleanFalseValue,//F,E
                ResultSetDictionaryExt.errorValue,//E,T
                ResultSetDictionaryExt.booleanFalseValue,//E,F
                ResultSetDictionaryExt.errorValue//E,E
        )
        val truthTable2 = arrayOf(
                ResultSetDictionaryExt.booleanTrueValue2,//T,T
                ResultSetDictionaryExt.booleanFalseValue2,//T,F
                ResultSetDictionaryExt.errorValue2,//T,E
                ResultSetDictionaryExt.booleanFalseValue2,//F,T
                ResultSetDictionaryExt.booleanFalseValue2,//F,F
                ResultSetDictionaryExt.booleanFalseValue2,//F,E
                ResultSetDictionaryExt.errorValue2,//E,T
                ResultSetDictionaryExt.booleanFalseValue2,//E,F
                ResultSetDictionaryExt.errorValue2//E,E
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

            } else {
                return {
                    val a = childA()
                    val b = query.getDictionary().toBooleanOrError(childB())
                    /*return*/truthTable2[a * 3 + b]
                }

            }

        } else {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = query.getDictionary().toBooleanOrError(childA())
                    val b = childB()
                    /*return*/truthTable2[a * 3 + b]
                }

            } else {
                return {
                    val a = query.getDictionary().toBooleanOrError(childA())
                    val b = query.getDictionary().toBooleanOrError(childB())
                    /*return*/truthTable2[a * 3 + b]
                }

            }

        }

    }

    override fun evaluateID(row: IteratorBundle): () -> Int {
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        if ((children[0] as AOPBase).enforcesBooleanOrError()) {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = childA()
                    val b = childB()
                    /*return*/truthTable[a * 3 + b]
                }

            } else {
                return {
                    val a = childA()
                    val b = query.getDictionary().toBooleanOrError(childB())
                    /*return*/truthTable[a * 3 + b]
                }

            }

        } else {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = query.getDictionary().toBooleanOrError(childA())
                    val b = childB()
                    /*return*/truthTable[a * 3 + b]
                }

            } else {
                return {
                    val a = query.getDictionary().toBooleanOrError(childA())
                    val b = query.getDictionary().toBooleanOrError(childB())
                    /*return*/truthTable[a * 3 + b]
                }

            }

        }

    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP() :IOPBase= AOPAnd(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
