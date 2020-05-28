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
class AOPBuildInCallURI(query: Query, child: AOPBase, @JvmField var prefix: String = "") : AOPBase(query, EOperatorID.AOPBuildInCallURIID, "AOPBuildInCallURI", arrayOf(child)) {
    override fun toSparql() = "URI(" + children[0].toSparql() + ")"
    override fun applyPrefix(prefix: String, iri: String) {
Coverage.funStart(3324)
        if (prefix == "") {
Coverage.ifStart(3325)
            this.prefix = iri
Coverage.statementStart(3326)
        }
Coverage.statementStart(3327)
    }
    override fun toXMLElement() = super.toXMLElement().addAttribute("prefix", prefix)
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3328)
        if (other !is AOPBuildInCallURI) {
Coverage.ifStart(3329)
            return false
        }
Coverage.statementStart(3330)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3331)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3332)
        return {
Coverage.statementStart(3333)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3334)
            val a = childA()
Coverage.statementStart(3335)
            if (a is ValueIri) {
Coverage.ifStart(3336)
                res = a
Coverage.statementStart(3337)
            }
Coverage.statementStart(3338)
            if (a is ValueSimpleLiteral || a is ValueTypedLiteral && a.type_iri == "http://www.w3.org/2001/XMLSchema#string") {
Coverage.ifStart(3339)
                val b = a as ValueStringBase
Coverage.statementStart(3340)
                if (prefix != "" && !prefix.endsWith("/")) {
Coverage.ifStart(3341)
                    res = ValueIri(prefix + "/" + b.content)
Coverage.statementStart(3342)
                } else {
Coverage.ifStart(3343)
                    res = ValueIri(prefix + b.content)
Coverage.statementStart(3344)
                }
Coverage.statementStart(3345)
            }
Coverage.statementStart(3346)
/*return*/res
        }
Coverage.statementStart(3347)
    }
    override fun cloneOP() = AOPBuildInCallURI(query, children[0].cloneOP() as AOPBase, prefix)
}
