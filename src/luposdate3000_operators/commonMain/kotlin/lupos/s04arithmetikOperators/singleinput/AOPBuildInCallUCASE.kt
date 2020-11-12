package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallUCASE(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallUCASEID, "AOPBuildInCallUCASE", arrayOf(child)) {
    override fun toSparql() = "UCASE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallUCASE && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueLanguageTaggedLiteral) {
                res = ValueLanguageTaggedLiteral(a.delimiter, a.content.toUpperCase(), a.language)
            } else if (a is ValueTypedLiteral) {
                res = ValueTypedLiteral(a.delimiter, a.content.toUpperCase(), a.type_iri)
            } else if (a is ValueSimpleLiteral) {
                res = ValueSimpleLiteral(a.delimiter, a.content.toUpperCase())
            }
/*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallUCASE(query, children[0].cloneOP() as AOPBase)
}
