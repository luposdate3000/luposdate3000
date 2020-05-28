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
Coverage.funStart(2555)
        if (other !is AOPOr) {
Coverage.ifStart(2556)
            return false
        }
Coverage.statementStart(2557)
        for (i in children.indices) {
Coverage.forLoopStart(2558)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2559)
                return false
            }
Coverage.statementStart(2560)
        }
Coverage.statementStart(2561)
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
Coverage.funStart(2562)
        val childA = (children[0] as AOPBase).evaluateID(row)
Coverage.statementStart(2563)
        val childB = (children[1] as AOPBase).evaluateID(row)
Coverage.statementStart(2564)
        if ((children[0] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2565)
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2566)
                return {
Coverage.statementStart(2567)
                    val a = childA()
Coverage.statementStart(2568)
                    val b = childB()
Coverage.statementStart(2569)
                    /*return*/truthTable2[a * 3 + b]
                }
Coverage.statementStart(2570)
            } else {
Coverage.ifStart(2571)
                return {
Coverage.statementStart(2572)
                    val a = childA()
Coverage.statementStart(2573)
                    val b = query.dictionary.toBooleanOrError(childB())
Coverage.statementStart(2574)
                    /*return*/truthTable2[a * 3 + b]
                }
Coverage.statementStart(2575)
            }
Coverage.statementStart(2576)
        } else {
Coverage.ifStart(2577)
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2578)
                return {
Coverage.statementStart(2579)
                    val a = query.dictionary.toBooleanOrError(childA())
Coverage.statementStart(2580)
                    val b = childB()
Coverage.statementStart(2581)
                    /*return*/truthTable2[a * 3 + b]
                }
Coverage.statementStart(2582)
            } else {
Coverage.ifStart(2583)
                return {
Coverage.statementStart(2584)
                    val a = query.dictionary.toBooleanOrError(childA())
Coverage.statementStart(2585)
                    val b = query.dictionary.toBooleanOrError(childB())
Coverage.statementStart(2586)
                    /*return*/truthTable2[a * 3 + b]
                }
Coverage.statementStart(2587)
            }
Coverage.statementStart(2588)
        }
Coverage.statementStart(2589)
    }
    override fun evaluateID(row: IteratorBundle): () -> Value {
Coverage.funStart(2590)
        val childA = (children[0] as AOPBase).evaluateID(row)
Coverage.statementStart(2591)
        val childB = (children[1] as AOPBase).evaluateID(row)
Coverage.statementStart(2592)
        if ((children[0] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2593)
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2594)
                return {
Coverage.statementStart(2595)
                    val a = childA()
Coverage.statementStart(2596)
                    val b = childB()
Coverage.statementStart(2597)
                    /*return*/truthTable[a * 3 + b]
                }
Coverage.statementStart(2598)
            } else {
Coverage.ifStart(2599)
                return {
Coverage.statementStart(2600)
                    val a = childA()
Coverage.statementStart(2601)
                    val b = query.dictionary.toBooleanOrError(childB())
Coverage.statementStart(2602)
                    /*return*/truthTable[a * 3 + b]
                }
Coverage.statementStart(2603)
            }
Coverage.statementStart(2604)
        } else {
Coverage.ifStart(2605)
            if ((children[1] as AOPBase).enforcesBooleanOrError()) {
Coverage.ifStart(2606)
                return {
Coverage.statementStart(2607)
                    val a = query.dictionary.toBooleanOrError(childA())
Coverage.statementStart(2608)
                    val b = childB()
Coverage.statementStart(2609)
                    /*return*/truthTable[a * 3 + b]
                }
Coverage.statementStart(2610)
            } else {
Coverage.ifStart(2611)
                return {
Coverage.statementStart(2612)
                    val a = query.dictionary.toBooleanOrError(childA())
Coverage.statementStart(2613)
                    val b = query.dictionary.toBooleanOrError(childB())
Coverage.statementStart(2614)
                    /*return*/truthTable[a * 3 + b]
                }
Coverage.statementStart(2615)
            }
Coverage.statementStart(2616)
        }
Coverage.statementStart(2617)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPOr(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
