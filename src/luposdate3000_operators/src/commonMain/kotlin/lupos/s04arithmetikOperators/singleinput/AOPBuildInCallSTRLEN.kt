package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.MyBigInteger
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
public class AOPBuildInCallSTRLEN(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRLENID, "AOPBuildInCallSTRLEN", arrayOf(child)) {
    override fun toSparql(): String = "STRLEN(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSTRLEN && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            when (val a = childA()) {
                is ValueSimpleLiteral -> {
                    res = ValueInteger(MyBigInteger(a.content.length))
                }
                is ValueTypedLiteral -> {
                    res = ValueInteger(MyBigInteger(a.content.length))
                }
                is ValueLanguageTaggedLiteral -> {
                    res = ValueInteger(MyBigInteger(a.content.length))
                }
            }
            res
        }
    }
    override fun cloneOP(): IOPBase = AOPBuildInCallSTRLEN(query, children[0].cloneOP() as AOPBase)
}
