package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
public class AOPBuildInCallSTRSTARTS public constructor(query: IQuery, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallSTRSTARTSID, "AOPBuildInCallSTRSTARTS", arrayOf(child, childB)) {
    override fun toSparql(): String = "STRSTARTS(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallSTRSTARTS && children[0] == other.children[0] && children[1] == other.children[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            val b = childB()
            if (a is ValueStringBase && b is ValueSimpleLiteral) {
                res = ValueBoolean(a.content.startsWith(b.content))
            }
            res
        }
    }
    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPBuildInCallSTRSTARTS(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
