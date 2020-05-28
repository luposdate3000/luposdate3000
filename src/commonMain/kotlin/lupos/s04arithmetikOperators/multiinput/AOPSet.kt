package lupos.s04arithmetikOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPSet(query: Query, childs: List<AOPBase>) : AOPBase(query, EOperatorID.AOPSetID, "AOPSet", Array(childs.size) { childs[it] }) {
    override fun toSparql(): String {
Coverage.funStart(2618)
        var res = ""
Coverage.statementStart(2619)
        res += "("
Coverage.statementStart(2620)
        if (children.size > 0) {
Coverage.ifStart(2621)
            res += children[0].toSparql()
Coverage.statementStart(2622)
        }
Coverage.statementStart(2623)
        for (i in 1 until children.size) {
Coverage.forLoopStart(2624)
            res += "," + children[i].toSparql()
Coverage.statementStart(2625)
        }
Coverage.statementStart(2626)
        res += ")"
Coverage.statementStart(2627)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2628)
        if (other !is AOPSet) {
Coverage.ifStart(2629)
            return false
        }
Coverage.statementStart(2630)
        for (i in children.indices) {
Coverage.forLoopStart(2631)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2632)
                return false
            }
Coverage.statementStart(2633)
        }
Coverage.statementStart(2634)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2635)
        TODO("not implemented")
Coverage.statementStart(2636)
    }
    override fun cloneOP() = AOPSet(query, List(children.size) { children[it].cloneOP() as AOPBase })
}
