package lupos.s09physicalOperators.multiinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPUnion(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPUnionID, "POPUnion", arrayOf(childA, childB), ESortPriority.UNION) {
    override fun cloneOP() = POPUnion(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP())
    override fun toSparql() = "{" + children[0].toSparql() + "} UNION {" + children[1].toSparql() + "}"
    override fun equals(other: Any?): Boolean = other is POPUnion && children[0] == other.children[0] && children[1] == other.children[1]
    override suspend fun evaluate(): ColumnIteratorRow {
        val variables = getProvidedVariableNames()
        SanityCheck.check { children[0].getProvidedVariableNames().containsAll(variables) }
        SanityCheck.check { children[1].getProvidedVariableNames().containsAll(variables) }
        val outMap = mutableMapOf<String, ColumnIterator>()
        val childA = children[0].evaluate()
        val childB = children[1].evaluate()
        for (variable in variables) {
            outMap[variable] = ColumnIteratorDebug(uuid, variable, ColumnIteratorMultiIterator(listOf(childA.columns[variable]!!, childB.columns[variable]!!)))
        }
        return ColumnIteratorRow(outMap)
    }
}
