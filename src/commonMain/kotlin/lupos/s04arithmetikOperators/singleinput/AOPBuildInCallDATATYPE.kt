package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallDATATYPE(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallDATATYPEID, "AOPBuildInCallDATATYPE", arrayOf(child)) {
    override fun toSparql() = "DATATYPE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallDATATYPE && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            when (a) {
                is ValueSimpleLiteral -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#string")
                }
                is ValueLanguageTaggedLiteral -> {
                    res = ValueIri("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString")
                }
                is ValueTypedLiteral -> {
                    res = ValueIri(a.type_iri)
                }
                is ValueBoolean -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#boolean")
                }
                is ValueDateTime -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#dateTime")
                }
                is ValueDecimal -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#decimal")
                }
                is ValueFloat -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#float")
                }
                is ValueDouble -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#double")
                }
                is ValueInteger -> {
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#integer")
                }
            }
/*return*/res
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = AOPBuildInCallDATATYPE(query, children[0].cloneOP() as AOPBase)
}
