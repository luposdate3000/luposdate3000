package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField

class AOPBuildInCallIRI(query: IQuery, child: AOPBase, @JvmField var prefix: String = "") : AOPBase(query, EOperatorID.AOPBuildInCallIRIID, "AOPBuildInCallIRI", arrayOf(child)) {
    override fun toSparql() = "IRI(" + children[0].toSparql() + ")"
    override fun applyPrefix(prefix: String, iri: String) {
        if (prefix == "") {
            this.prefix = iri
        }
        children[0].applyPrefix(prefix, iri)
    }

    override /*suspend*/ fun toXMLElement() = super.toXMLElement().addAttribute("prefix", prefix)
    override fun equals(other: Any?) = other is AOPBuildInCallIRI && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueIri) {
                res = a
            } else if (a is ValueSimpleLiteral || a is ValueTypedLiteral && a.type_iri == "http://www.w3.org/2001/XMLSchema#string") {
                val b = a as ValueStringBase
                res = if (prefix != "" && !prefix.endsWith("/")) {
                    ValueIri(prefix + "/" + b.content)
                } else {
                    ValueIri(prefix + b.content)
                }
            }
/*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallIRI(query, children[0].cloneOP() as AOPBase, prefix)
}
