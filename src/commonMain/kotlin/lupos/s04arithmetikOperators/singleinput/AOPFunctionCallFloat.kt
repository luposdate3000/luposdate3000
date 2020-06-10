package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPFunctionCallFloat(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPFunctionCallFloatID, "AOPFunctionCallFloat", arrayOf(child)) {
    override fun toSparql() = "<http://www.w3.org/2001/XMLSchema#float>(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPFunctionCallFloat && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            when (a) {
                is ValueFloat -> {
                    res = a
                }
                is ValueInteger -> {
                    res = ValueFloat(a.toDouble())
                }
                is ValueBoolean -> {
                    if (a.value) {
                        res = ValueFloat(1.0)
                    } else {
                        res = ValueFloat(0.0)
                    }
                }
                is ValueDecimal -> {
                    res = ValueFloat(a.value)
                }
                is ValueDouble -> {
                    res = ValueFloat(a.value)
                }
                is ValueSimpleLiteral -> {
                    res = ValueFloat(a.content.toDouble())
                }
                is ValueLanguageTaggedLiteral -> {
                    res = ValueFloat(a.content.toDouble())
                }
                is ValueTypedLiteral -> {
                    res = ValueFloat(a.content.toDouble())
                }
            }
            /*return*/res
        }
        /*Coverage Unreachable*/
    }

    override fun cloneOP() = AOPFunctionCallFloat(query, children[0].cloneOP() as AOPBase)
}
