package lupos.s04arithmetikOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

impoert lupos.s04logicalOperators.iterator.*


class AOPBuildInCallURI(query: Query, child: AOPBase, @JvmField var prefix: String = "") : AOPBase(query, EOperatorID.AOPBuildInCallURIID, "AOPBuildInCallURI", arrayOf(child)) {
    override fun toSparql() = "URI(" + children[0].toSparql() + ")"
    override fun applyPrefix(prefix: String, iri: String) {
        if (prefix == "")
            this.prefix = iri
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("prefix", prefix)
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallURI)
            return false
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
var res = ValueError()
            val a = childA()
            if (a is ValueIri)
                res = a
            if (a is ValueSimpleLiteral || a is ValueTypedLiteral && a.type_iri == "http://www.w3.org/2001/XMLSchema#string") {
                val b = a as ValueStringBase
                if (prefix != "" && !prefix.endsWith("/"))
                    res = ValueIri(prefix + "/" + b.content)
                else
                    res = ValueIri(prefix + b.content)
            }
        res
        }
    }

    override fun cloneOP() = AOPBuildInCallURI(query, children[0].cloneOP() as AOPBase, prefix)
}
