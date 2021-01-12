package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
public class AOPIn public constructor(query: IQuery, childA: IAOPBase, childB: IAOPBase) : AOPBase(query, EOperatorIDExt.AOPInID, "AOPIn", arrayOf(childA, childB)) {
    override fun toSparql(): String = "( " + children[0].toSparql() + " IN " + children[1].toSparql() + " )"
    override fun equals(other: Any?): Boolean = other is AOPIn && children[0] == other.getChildren()[0] && children[1] == other.getChildren()[1]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        SanityCheck.check { children[1] is AOPSet }
        val childsB = Array(children[1].getChildren().size) { (children[1].getChildren()[it] as AOPBase).evaluate(row) }
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            var found = false
            var noError = true
            for (childB in childsB) {
                try {
                    if (childB() == a) {
                        found = true
                        break
                    }
                } catch (e: Throwable) {
                    noError = false
                    SanityCheck.println { "TODO exception 22" }
                    e.printStackTrace()
                }
            }
            if (found || noError) {
                res = ValueBoolean(found)
            }
            res
        }
    }
    override fun enforcesBooleanOrError(): Boolean = true
    override fun cloneOP(): IOPBase = AOPIn(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
