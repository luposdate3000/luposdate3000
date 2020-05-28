package lupos.s04arithmetikOperators.noinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPValue(query: Query, childs: List<AOPConstant>) : AOPBase(query, EOperatorID.AOPValueID, "AOPValue", Array(childs.size) { childs[it] }) {
    override fun toSparql(): String {
Coverage.funStart(2690)
        var res = ""
Coverage.statementStart(2691)
        res += "("
Coverage.statementStart(2692)
        if (children.size > 0) {
Coverage.ifStart(2693)
            res += children[0].toSparql()
Coverage.statementStart(2694)
        }
Coverage.statementStart(2695)
        for (i in 1 until children.size) {
Coverage.forLoopStart(2696)
            res += "," + children[i].toSparql()
Coverage.statementStart(2697)
        }
Coverage.statementStart(2698)
        res += ")"
Coverage.statementStart(2699)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2700)
        if (other !is AOPValue) {
Coverage.ifStart(2701)
            return false
        }
Coverage.statementStart(2702)
        for (i in children.indices) {
Coverage.forLoopStart(2703)
            if (children[i] != other.children[i]) {
Coverage.ifStart(2704)
                return false
            }
Coverage.statementStart(2705)
        }
Coverage.statementStart(2706)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2707)
        TODO("not implemented")
Coverage.statementStart(2708)
    }
    override fun cloneOP() = this
}
