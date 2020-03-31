package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallUCASE(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallUCASEID, "AOPBuildInCallUCASE", arrayOf(child)) {
    override fun toSparql() = "UCASE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallUCASE) {
            return false
        }
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
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
            res
        }
    }

    override fun cloneOP() = AOPBuildInCallUCASE(query, children[0].cloneOP() as AOPBase)
}
