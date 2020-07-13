package lupos.s09physicalOperators.multiinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPUnion(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPUnionID, "POPUnion", arrayOf(childA, childB), ESortPriority.UNION) {
    override fun cloneOP() = POPUnion(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP())
    override fun toSparql() = "{" + children[0].toSparql() + "} UNION {" + children[1].toSparql() + "}"
    override fun equals(other: Any?): Boolean = other is POPUnion && children[0] == other.children[0] && children[1] == other.children[1]
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        val variables = getProvidedVariableNames()
        SanityCheck.check { children[0].getProvidedVariableNames().containsAll(variables) }
        SanityCheck.check { children[1].getProvidedVariableNames().containsAll(variables) }
        val outMap = mutableMapOf<String, ColumnIterator>()
        val childA = children[0].evaluate(parent)
        val childB = children[1].evaluate(parent)
        if (variables.size > 0) {
            for (variable in variables) {
                outMap[variable] =  ColumnIteratorMultiIterator(listOf(childA.columns[variable]!!, childB.columns[variable]!!))
            }
            return IteratorBundle(outMap)
        } else {
            SanityCheck.check { childA.hasCountMode() && childB.hasCountMode() }
            var res = IteratorBundle(0)
            res.hasNext2 = {
/*return*/ childA.hasNext2() || childB.hasNext2()
            }
            res.hasNext2Close = {
                childA.hasNext2Close()
                childB.hasNext2Close()
            }
            return res
        }
/*Coverage Unreachable*/
    }
}
