package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.CanNotCastLiteralToDoubleException
import lupos.s00misc.DontCareWhichException
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPFunctionCallFloat(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPFunctionCallFloatID, "AOPFunctionCallFloat", arrayOf(child)) {
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
                    res = ValueFloat(a.value.toDouble())
                }
                is ValueDouble -> {
                    res = ValueFloat(a.value)
                }
                is ValueSimpleLiteral -> {
                    try {
                        res = ValueFloat(a.content.toDouble())
                    } catch (e: DontCareWhichException) {
                        throw CanNotCastLiteralToDoubleException()
                    }
                }
                is ValueLanguageTaggedLiteral -> {
                    try {
                        res = ValueFloat(a.content.toDouble())
                    } catch (e: DontCareWhichException) {
                        throw CanNotCastLiteralToDoubleException()
                    }
                }
                is ValueTypedLiteral -> {
                    try {
                        res = ValueFloat(a.content.toDouble())
                    } catch (e: DontCareWhichException) {
                        throw CanNotCastLiteralToDoubleException()
                    }
                }
            }
            /*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPFunctionCallFloat(query, children[0].cloneOP() as AOPBase)
}
