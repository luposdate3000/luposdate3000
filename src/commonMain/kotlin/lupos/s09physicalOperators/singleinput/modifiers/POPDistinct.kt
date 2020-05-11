package lupos.s09physicalOperators.singleinput.modifiers

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorDistinct
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIteratorReduced
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPSort

class POPDistinct(query: Query, projectedVariables: List<String>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPDistinctID, "POPDistinct", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?) = other is POPDistinct && children[0] == other.children[0]
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return "{SELECT DISTINCT " + sparql.substring("{SELECT ".length, sparql.length)
        }
        return "{SELECT DISTINCT * {" + sparql + "}}"
    }

    override fun cloneOP() = POPDistinct(query, projectedVariables, children[0].cloneOP())
    override suspend fun evaluate(): IteratorBundle {
        val sort = POPSort(query, projectedVariables, arrayOf<AOPVariable>(), true, children[0])
        val child = sort.evaluate()
        val reduced = RowIteratorReduced(child.rows)
        return IteratorBundle(reduced)
    }
}
