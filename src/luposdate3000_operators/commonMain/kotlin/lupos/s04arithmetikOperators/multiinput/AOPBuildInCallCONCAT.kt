package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallCONCAT(query: IQuery, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallCONCATID, "AOPBuildInCallCONCAT", arrayOf(child, childB)) {
    override fun toSparql() = "CONCAT(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallCONCAT && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            if (a is ValueLanguageTaggedLiteral && b is ValueLanguageTaggedLiteral && a.language == b.language) {
                res = ValueLanguageTaggedLiteral(a.delimiter, a.content + b.content, a.language)
            } else if (a is ValueTypedLiteral && b is ValueTypedLiteral && a.type_iri == "http://www.w3.org/2001/XMLSchema#string" && a.type_iri == b.type_iri) {
                res = ValueTypedLiteral(a.delimiter, a.content + b.content, a.type_iri)
            } else if (a is ValueStringBase && b is ValueStringBase) {
                res = ValueSimpleLiteral(a.delimiter, a.content + b.content)
            }
/*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallCONCAT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
