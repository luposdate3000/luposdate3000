package lupos.s04arithmetikOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallIRI(query: Query, child: AOPBase, @JvmField var prefix: String = "") : AOPBase(query, EOperatorID.AOPBuildInCallIRIID, "AOPBuildInCallIRI", arrayOf(child)) {
    override fun toSparql() = "IRI(" + children[0].toSparql() + ")"
    override fun applyPrefix(prefix: String, iri: String) {
Coverage.funStart(3040)
        if (prefix == "") {
Coverage.ifStart(3041)
            this.prefix = iri
Coverage.statementStart(3042)
        }
Coverage.statementStart(3043)
        children[0].applyPrefix(prefix, iri)
Coverage.statementStart(3044)
    }
    override fun toXMLElement() = super.toXMLElement().addAttribute("prefix", prefix)
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3045)
        if (other !is AOPBuildInCallIRI) {
Coverage.ifStart(3046)
            return false
        }
Coverage.statementStart(3047)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3048)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3049)
        return {
Coverage.statementStart(3050)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3051)
            val a = childA()
Coverage.statementStart(3052)
            if (a is ValueIri) {
Coverage.ifStart(3053)
                res = a
Coverage.statementStart(3054)
            } else if (a is ValueSimpleLiteral || a is ValueTypedLiteral && a.type_iri == "http://www.w3.org/2001/XMLSchema#string") {
Coverage.ifStart(3055)
                val b = a as ValueStringBase
Coverage.statementStart(3056)
                if (prefix != "" && !prefix.endsWith("/")) {
Coverage.ifStart(3057)
                    res = ValueIri(prefix + "/" + b.content)
Coverage.statementStart(3058)
                } else {
Coverage.ifStart(3059)
                    res = ValueIri(prefix + b.content)
Coverage.statementStart(3060)
                }
Coverage.statementStart(3061)
            }
Coverage.statementStart(3062)
/*return*/res
        }
Coverage.statementStart(3063)
    }
    override fun cloneOP() = AOPBuildInCallIRI(query, children[0].cloneOP() as AOPBase, prefix)
}
