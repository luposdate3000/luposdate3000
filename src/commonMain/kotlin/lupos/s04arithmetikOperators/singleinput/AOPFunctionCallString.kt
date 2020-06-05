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

class AOPFunctionCallString(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPFunctionCallStringID, "AOPFunctionCallString", arrayOf(child)) {
    override fun toSparql() = "<http://www.w3.org/2001/XMLSchema#String>(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPFunctionCallString && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            when (a) {
                is ValueSimpleLiteral -> {
                    res = ValueTypedLiteral(a.delimiter,a.content,"http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueInteger -> {
                    res = ValueTypedLiteral("\"",a.value.toString(),"http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueBoolean -> {
			res = ValueTypedLiteral("\"",a.value.toString(),"http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueDecimal -> {
			res = ValueTypedLiteral("\"",a.value.toString(),"http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueDouble -> {
			res = ValueTypedLiteral("\"",a.value.toString(),"http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueFloat -> {
			res = ValueTypedLiteral("\"",a.value.toString(),"http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueSimpleLiteral -> {
res=ValueTypedLiteral(a.delimiter,a.content,"http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueLanguageTaggedLiteral -> {
res=ValueTypedLiteral(a.delimiter,a.content,"http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueTypedLiteral -> {
if(a.type_iri=="http://www.w3.org/2001/XMLSchema#string"){
res=a
}
                }
}
                /*return*/res
            }
            /*Coverage Unreachable*/
        }

        override fun cloneOP() = AOPFunctionCallString(query, children[0].cloneOP() as AOPBase)
    }
