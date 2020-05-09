package lupos.s04arithmetikOperators.multiinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
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

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
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
                    val b = query.dictionary.toBooleanOrError(childB())
                    /*return*/truthTable2[a * 3 + b]
                }
            }
        } else {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = query.dictionary.toBooleanOrError(childA())
                    val b = childB()
                    /*return*/truthTable2[a * 3 + b]
                }
            } else {
                return {
                    val a = query.dictionary.toBooleanOrError(childA())
                    val b = query.dictionary.toBooleanOrError(childB())
                    /*return*/truthTable2[a * 3 + b]
                }
            }
        }
    }

    override fun evaluateID(row: ColumnIteratorRow): () -> Value {
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
                    val b = query.dictionary.toBooleanOrError(childB())
                    /*return*/truthTable[a * 3 + b]
                }
            }
        } else {
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
                return {
                    val a = query.dictionary.toBooleanOrError(childA())
                    val b = childB()
                    /*return*/truthTable[a * 3 + b]
                }
            } else {
                return {
                    val a = query.dictionary.toBooleanOrError(childA())
                    val b = query.dictionary.toBooleanOrError(childB())
                    /*return*/truthTable[a * 3 + b]
                }
            }
        }
    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPOr(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
