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
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        return {
            var res: ValueDefinition = ResultSetDictionary.booleanFalseValue2
            var a = childA()
            var b = childB()
            if (a == ResultSetDictionary.booleanTrueValue || b == ResultSetDictionary.booleanTrueValue) {
                res = ResultSetDictionary.booleanTrueValue2
            } else {
                if (a == ResultSetDictionary.undefValue) {
                    a = ResultSetDictionary.errorValue
                } else {
                    try {
                        if (query.dictionary.getValue(a).toBoolean())
                            a = ResultSetDictionary.booleanTrueValue
                    } catch (e: Throwable) {
                        a = ResultSetDictionary.errorValue
                    }
                }
                if (a == ResultSetDictionary.booleanTrueValue) {
                    res = ResultSetDictionary.booleanTrueValue2
                } else {
                    if (b != ResultSetDictionary.undefValue) {
                        try {
                            if (query.dictionary.getValue(b).toBoolean())
                                b = ResultSetDictionary.booleanTrueValue
                        } catch (e: Throwable) {
                            b = ResultSetDictionary.errorValue
                        }
                        if (b == ResultSetDictionary.booleanTrueValue) {
                            res = ResultSetDictionary.booleanTrueValue2
                        }
                    }
                }
            }
/*return*/res
        }
    }

    override fun evaluateID(row: ColumnIteratorRow): () -> Value {
        val childA = (children[0] as AOPBase).evaluateID(row)
        val childB = (children[1] as AOPBase).evaluateID(row)
        return {
            var res: Value = ResultSetDictionary.booleanFalseValue
            var a = childA()
            var b = childB()
            if (a == ResultSetDictionary.booleanTrueValue || b == ResultSetDictionary.booleanTrueValue) {
                res = ResultSetDictionary.booleanTrueValue
            } else {
                if (a == ResultSetDictionary.undefValue) {
                    a = ResultSetDictionary.errorValue
                } else {
                    try {
                        if (query.dictionary.getValue(a).toBoolean())
                            a = ResultSetDictionary.booleanTrueValue
                    } catch (e: Throwable) {
                        a = ResultSetDictionary.errorValue
                    }
                }
                if (a == ResultSetDictionary.booleanTrueValue) {
                    res = ResultSetDictionary.booleanTrueValue
                } else {
                    if (b != ResultSetDictionary.undefValue) {
                        try {
                            if (query.dictionary.getValue(b).toBoolean())
                                b = ResultSetDictionary.booleanTrueValue
                        } catch (e: Throwable) {
                            b = ResultSetDictionary.errorValue
                        }
                        if (b == ResultSetDictionary.booleanTrueValue) {
                            res = ResultSetDictionary.booleanTrueValue
                        }
                    }
                }
            }
/*return*/res
        }
    }

    override fun cloneOP() = AOPOr(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
