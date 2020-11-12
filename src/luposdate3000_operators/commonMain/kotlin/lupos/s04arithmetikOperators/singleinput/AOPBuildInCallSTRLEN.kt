package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.MyBigInteger
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallSTRLEN(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRLENID, "AOPBuildInCallSTRLEN", arrayOf(child)) {
    override fun toSparql() = "STRLEN(" + children[0].toSparql() + ")"
    override fun equals(other: Any?) = other is AOPBuildInCallSTRLEN && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueSimpleLiteral) {
                res = ValueInteger(MyBigInteger(a.content.length))
            } else if (a is ValueTypedLiteral) {
                res = ValueInteger(MyBigInteger(a.content.length))
            } else if (a is ValueLanguageTaggedLiteral) {
                res = ValueInteger(MyBigInteger(a.content.length))
            }
/*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallSTRLEN(query, children[0].cloneOP() as AOPBase)
}
