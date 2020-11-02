package lupos.s09physicalOperators.singleinput.modifiers

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorReduced
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIteratorReduced
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPReduced(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorID.POPReducedID, "POPReduced", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = children[0].getPartitionCount(variable)
    override fun equals(other: Any?) = other is POPReduced && children[0] == other.children[0]
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return "{SELECT REDUCED " + sparql.substring("{SELECT ".length, sparql.length)
        }
        return "{SELECT REDUCED * {" + sparql + "}}"
    }

    override fun cloneOP(): IOPBase = POPReduced(query, projectedVariables, children[0].cloneOP())
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        val child = children[0].evaluate(parent)
        if (projectedVariables.size == 1) {
            val reduced = ColumnIteratorReduced(child.columns[projectedVariables[0]]!!)
            return IteratorBundle(mapOf(projectedVariables[0] to reduced))
        } else if (projectedVariables.size > 0) {
            val reduced = RowIteratorReduced(child.rows)
            return IteratorBundle(reduced)
        } else {
            return child
        }
    }
}
