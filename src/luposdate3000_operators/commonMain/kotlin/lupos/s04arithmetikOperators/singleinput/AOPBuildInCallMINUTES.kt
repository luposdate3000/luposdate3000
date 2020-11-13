package lupos.s04arithmetikOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.MyBigInteger
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle

class AOPBuildInCallMINUTES(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallMINUTESID, "AOPBuildInCallMINUTES", arrayOf(child)) {
    override fun toSparql(): String = "MINUTES(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallMINUTES && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueDateTime) {
                res = ValueInteger(MyBigInteger(a.minutes))
            }
/*return*/res
        }
    }

    override fun cloneOP(): IOPBase = AOPBuildInCallMINUTES(query, children[0].cloneOP() as AOPBase)
}
