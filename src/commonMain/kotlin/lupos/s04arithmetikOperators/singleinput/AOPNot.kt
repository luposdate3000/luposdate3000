package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPNot(query: Query, @JvmField var child: AOPBase) : AOPBase(query, EOperatorID.AOPNotID, "AOPNot", arrayOf(child)) {
    override fun toSparql() = "!(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPNot && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        if ((children[0] as AOPBase).enforcesBooleanOrError()) {
            val childA = (children[0] as AOPBase).evaluateID(row)
            return {
                val a = childA()
                val res: ValueDefinition
                if (a == ResultSetDictionary.errorValue) {
                    res = ResultSetDictionary.errorValue2
                } else {
                    res = ValueBoolean(a == ResultSetDictionary.booleanFalseValue)
                }
                /*return*/res
            }
        } else {
            val childA = (children[0] as AOPBase).evaluate(row)
            return {
                val a = childA()
                var res: ValueDefinition = ResultSetDictionary.errorValue2
                try {
                    res = ValueBoolean(!a.toBoolean())
                } catch (e: Throwable) {
                }
                /*return*/res
            }
        }
        /*Coverage Unreachable*/
    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPNot(query, children[0].cloneOP() as AOPBase)
}
