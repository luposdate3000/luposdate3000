package lupos.s04arithmetikOperators.singleinput
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
class AOPFunctionCallString(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPFunctionCallStringID, "AOPFunctionCallString", arrayOf(child)) {
    override fun toSparql(): String = "<http://www.w3.org/2001/XMLSchema#String>(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPFunctionCallString && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            when (val a = childA()) {
                is ValueSimpleLiteral -> {
                    res = ValueTypedLiteral(a.delimiter, a.content, "http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueInteger -> {
                    res = ValueTypedLiteral("\"", a.value.toString(), "http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueBoolean -> {
                    res = ValueTypedLiteral("\"", a.value.toString(), "http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueDecimal -> {
                    res = ValueTypedLiteral("\"", a.value.toString(), "http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueDouble -> {
                    res = ValueTypedLiteral("\"", a.value.toString(), "http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueFloat -> {
                    res = ValueTypedLiteral("\"", a.value.toString(), "http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueLanguageTaggedLiteral -> {
                    res = ValueTypedLiteral(a.delimiter, a.content, "http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueTypedLiteral -> {
                    res = ValueTypedLiteral(a.delimiter, a.content, "http://www.w3.org/2001/XMLSchema#string")
                }
            }
            res
        }
    }
    override fun cloneOP(): IOPBase = AOPFunctionCallString(query, children[0].cloneOP() as AOPBase)
}
