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
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2970)
        if (other !is AOPBuildInCallDATATYPE) {
Coverage.ifStart(2971)
            return false
        }
Coverage.statementStart(2972)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2973)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2974)
        return {
Coverage.statementStart(2975)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2976)
            val a = childA()
Coverage.statementStart(2977)
            when (a) {
                is ValueSimpleLiteral -> {
Coverage.whenCaseStart(2979)
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#string")
Coverage.statementStart(2980)
                }
                is ValueLanguageTaggedLiteral -> {
Coverage.whenCaseStart(2981)
                    res = ValueIri("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString")
Coverage.statementStart(2982)
                }
                is ValueTypedLiteral -> {
Coverage.whenCaseStart(2983)
                    res = ValueIri(a.type_iri)
Coverage.statementStart(2984)
                }
                is ValueBoolean -> {
Coverage.whenCaseStart(2985)
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#boolean")
Coverage.statementStart(2986)
                }
                is ValueDateTime -> {
Coverage.whenCaseStart(2987)
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#dateTime")
Coverage.statementStart(2988)
                }
                is ValueDecimal -> {
Coverage.whenCaseStart(2989)
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#decimal")
Coverage.statementStart(2990)
                }
                is ValueDouble -> {
Coverage.whenCaseStart(2991)
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#double")
Coverage.statementStart(2992)
                }
                is ValueInteger -> {
Coverage.whenCaseStart(2993)
                    res = ValueIri("http://www.w3.org/2001/XMLSchema#integer")
Coverage.statementStart(2994)
                }
            }
Coverage.statementStart(2995)
/*return*/res
        }
Coverage.statementStart(2996)
    }
    override fun cloneOP() = AOPBuildInCallDATATYPE(query, children[0].cloneOP() as AOPBase)
}
