package lupos.s09physicalOperators.singleinput.modifiers
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIteratorReduced
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPSort
class POPDistinct(query: Query, projectedVariables: List<String>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPDistinctID, "POPDistinct", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?) = other is POPDistinct && children[0] == other.children[0]
    override fun toSparql(): String {
Coverage.funStart(11412)
        val sparql = children[0].toSparql()
Coverage.statementStart(11413)
        if (sparql.startsWith("{SELECT ")) {
Coverage.ifStart(11414)
            return "{SELECT DISTINCT " + sparql.substring("{SELECT ".length, sparql.length)
        }
Coverage.statementStart(11415)
        return "{SELECT DISTINCT * {" + sparql + "}}"
    }
    override fun cloneOP() = POPDistinct(query, projectedVariables, children[0].cloneOP())
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11416)
        if (projectedVariables.size > 0) {
Coverage.ifStart(11417)
            var child: IteratorBundle
Coverage.statementStart(11418)
            SanityCheck.check { mySortPriority.size <= projectedVariables.size }
Coverage.statementStart(11419)
            if (mySortPriority.size == projectedVariables.size) {
Coverage.ifStart(11420)
                SanityCheck.check { mySortPriority.map { it.variableName }.containsAll(projectedVariables) }
Coverage.statementStart(11421)
                child = children[0].evaluate()
Coverage.statementStart(11422)
            } else {
Coverage.ifStart(11423)
                val sort = POPSort(query, projectedVariables, arrayOf<AOPVariable>(), true, children[0])
Coverage.statementStart(11424)
                child = sort.evaluate()
Coverage.statementStart(11425)
            }
Coverage.statementStart(11426)
            val reduced = RowIteratorReduced(child.rows)
Coverage.statementStart(11427)
            return IteratorBundle(reduced)
        } else {
Coverage.statementStart(11428)
            return children[0].evaluate()
        }
Coverage.statementStart(11429)
    }
}
