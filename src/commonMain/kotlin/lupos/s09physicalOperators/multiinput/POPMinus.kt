package lupos.s09physicalOperators.multiinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPSort

class POPMinus(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPMinusID, "POPMinus", arrayOf(childA, childB), ESortPriority.MINUS) {
    override fun cloneOP() = POPMinus(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP())
    override fun toSparql() = "{" + children[0].toSparql() + "} MINUS {" + children[1].toSparql() + "}"
    override fun equals(other: Any?): Boolean = other is POPMinus && children[0] == other.children[0] && children[1] == other.children[1]
    override suspend fun evaluate(): IteratorBundle {
        val variables = getProvidedVariableNames()
        SanityCheck.check { children[0].getProvidedVariableNames().containsAll(variables) }
        SanityCheck.check { children[1].getProvidedVariableNames().containsAll(variables) }
        val outMap = mutableMapOf<String, ColumnIterator>()
        val sortA = POPSort(query, projectedVariables, projectedVariables.map { AOPVariable(query, it) }.toTypedArray(), true, children[0])
        val sortB = POPSort(query, projectedVariables, projectedVariables.map { AOPVariable(query, it) }.toTypedArray(), true, children[1])
        val childA = sortA.evaluate()
        val childB = sortB.evaluate()
        for (variable in variables) {
            outMap[variable] = ColumnIteratorDebug(uuid, variable, ColumnIteratorMultiIterator(listOf(childA.columns[variable]!!, childB.columns[variable]!!)))
        }
        return IteratorBundle(outMap)
    }
}
