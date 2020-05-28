package lupos.s09physicalOperators.singleinput.modifiers
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorReduced
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIteratorReduced
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPSort
class POPReduced(query: Query, projectedVariables: List<String>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPReducedID, "POPReduced", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?) = other is POPReduced && children[0] == other.children[0]
    override fun toSparql(): String {
Coverage.funStart(11490)
        val sparql = children[0].toSparql()
Coverage.statementStart(11491)
        if (sparql.startsWith("{SELECT ")) {
Coverage.ifStart(11492)
            return "{SELECT REDUCED " + sparql.substring("{SELECT ".length, sparql.length)
        }
Coverage.statementStart(11493)
        return "{SELECT REDUCED * {" + sparql + "}}"
    }
    override fun cloneOP() = POPReduced(query, projectedVariables, children[0].cloneOP())
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11494)
        if (projectedVariables.size == 1) {
Coverage.ifStart(11495)
            val child = children[0].evaluate()
Coverage.statementStart(11496)
            val reduced = ColumnIteratorReduced(child.columns[projectedVariables[0]]!!)
Coverage.statementStart(11497)
            return IteratorBundle(mapOf(projectedVariables[0] to reduced))
        } else if (projectedVariables.size > 0) {
Coverage.statementStart(11498)
            val child = children[0].evaluate()
Coverage.statementStart(11499)
            val reduced = RowIteratorReduced(child.rows)
Coverage.statementStart(11500)
            return IteratorBundle(reduced)
        } else {
Coverage.statementStart(11501)
            return children[0].evaluate()
        }
Coverage.statementStart(11502)
    }
}
