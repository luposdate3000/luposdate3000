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
public class AOPFunctionCallDouble public constructor(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPFunctionCallDoubleID, "AOPFunctionCallDouble", arrayOf(child)) {
    override fun toSparql(): String = "<http://www.w3.org/2001/XMLSchema#double>(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPFunctionCallDouble && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            when (val a = childA()) {
                is ValueDouble -> {
                    res = a
                }
                is ValueInteger -> {
                    res = ValueDouble(a.toDouble())
                }
                is ValueBoolean -> {
                    res = if (a.value) {
                        ValueDouble(1.0)
                    } else {
                        ValueDouble(0.0)
                    }
                }
                is ValueDecimal -> {
                    res = ValueDouble(a.value.toDouble())
                }
                is ValueFloat -> {
                    res = ValueDouble(a.value)
                }
                is ValueSimpleLiteral -> {
                    try {
                        res = ValueDouble(a.content.toDouble())
                    } catch (e: DontCareWhichException) {
                        throw CanNotCastLiteralToDoubleException()
                    }
                }
                is ValueLanguageTaggedLiteral -> {
                    try {
                        res = ValueDouble(a.content.toDouble())
                    } catch (e: DontCareWhichException) {
                        throw CanNotCastLiteralToDoubleException()
                    }
                }
                is ValueTypedLiteral -> {
                    try {
                        res = ValueDouble(a.content.toDouble())
                    } catch (e: DontCareWhichException) {
                        throw CanNotCastLiteralToDoubleException()
                    }
                }
            }
            res
        }
    }
    override fun cloneOP(): IOPBase = AOPFunctionCallDouble(query, children[0].cloneOP() as AOPBase)
}
