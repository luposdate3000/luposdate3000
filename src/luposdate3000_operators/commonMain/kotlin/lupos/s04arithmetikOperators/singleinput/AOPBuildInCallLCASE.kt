package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallLCASE(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallLCASEID, "AOPBuildInCallLCASE", arrayOf(child)) {
    override fun toSparql(): String = "LCASE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallLCASE && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            when (a) {
                is ValueLanguageTaggedLiteral -> {
                    res = ValueLanguageTaggedLiteral(a.delimiter, a.content.toLowerCase(), a.language)
                }
                is ValueTypedLiteral -> {
                    res = ValueTypedLiteral(a.delimiter, a.content.toLowerCase(), a.type_iri)
                }
                is ValueSimpleLiteral -> {
                    res = ValueSimpleLiteral(a.delimiter, a.content.toLowerCase())
                }
            }
/*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallLCASE(query, children[0].cloneOP() as AOPBase)
}
