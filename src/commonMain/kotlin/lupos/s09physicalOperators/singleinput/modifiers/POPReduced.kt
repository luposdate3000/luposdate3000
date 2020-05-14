package lupos.s09physicalOperators.singleinput.modifiers

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
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

class POPReduced(query: Query, projectedVariables: List<String>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPReducedID, "POPReduced", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun equals(other: Any?) = other is POPReduced && children[0] == other.children[0]
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return "{SELECT REDUCED " + sparql.substring("{SELECT ".length, sparql.length)
        }
        return "{SELECT REDUCED * {" + sparql + "}}"
    }

    override fun cloneOP() = POPReduced(query, projectedVariables, children[0].cloneOP())
    override suspend fun evaluate(): IteratorBundle {
        if (projectedVariables.size > 0) {
val                child = children[0].evaluate()
            val reduced = RowIteratorReduced(child.rows)
            return IteratorBundle(reduced)
        } else {
            return children[0].evaluate()
        }
    }
}
