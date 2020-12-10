package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
class AOPBuildInCallBNODE1(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallBNODE1ID, "AOPBuildInCallBNODE1", arrayOf(child)) {
    override fun toSparql(): String = "BNODE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallBNODE1 && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            val a = childA()
            ValueBnode("" + uuid + a.valueToString())
        }
    }
    override fun cloneOP(): IOPBase = AOPBuildInCallBNODE1(query, children[0].cloneOP() as AOPBase)
}
