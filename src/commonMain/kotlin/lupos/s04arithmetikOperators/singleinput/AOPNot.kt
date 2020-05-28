package lupos.s04arithmetikOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPNot(query: Query, @JvmField var child: AOPBase) : AOPBase(query, EOperatorID.AOPNotID, "AOPNot", arrayOf(child)) {
    override fun toSparql() = "!(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3360)
        if (other !is AOPNot) {
Coverage.ifStart(3361)
            return false
        }
Coverage.statementStart(3362)
        for (i in children.indices) {
Coverage.forLoopStart(3363)
            if (children[i] != other.children[i]) {
Coverage.ifStart(3364)
                return false
            }
Coverage.statementStart(3365)
        }
Coverage.statementStart(3366)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3367)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3368)
        return {
Coverage.statementStart(3369)
            val a = childA()
Coverage.statementStart(3370)
            /*return*/ValueBoolean(!a.toBoolean())
        }
Coverage.statementStart(3371)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPNot(query, children[0].cloneOP() as AOPBase)
}
