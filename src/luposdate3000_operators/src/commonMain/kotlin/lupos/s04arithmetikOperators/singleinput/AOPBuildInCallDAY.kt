package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.MyBigInteger
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
public class AOPBuildInCallDAY public constructor(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallDAYID, "AOPBuildInCallDAY", arrayOf(child)) {
    override fun toSparql(): String = "DAY(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallDAY && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueDateTime) {
                res = ValueInteger(MyBigInteger(a.day))
            }
            res
        }
    }
    override fun cloneOP(): IOPBase = AOPBuildInCallDAY(query, children[0].cloneOP() as AOPBase)
}
