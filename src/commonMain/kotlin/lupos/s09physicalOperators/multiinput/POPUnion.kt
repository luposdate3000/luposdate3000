package lupos.s09physicalOperators.multiinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
class POPUnion(query: Query, projectedVariables: List<String>, childA: OPBase, childB: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPUnionID, "POPUnion", arrayOf(childA, childB), ESortPriority.UNION) {
    override fun cloneOP() = POPUnion(query, projectedVariables, children[0].cloneOP(), children[1].cloneOP())
    override fun toSparql() = "{" + children[0].toSparql() + "} UNION {" + children[1].toSparql() + "}"
    override fun equals(other: Any?): Boolean = other is POPUnion && children[0] == other.children[0] && children[1] == other.children[1]
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11001)
        val variables = getProvidedVariableNames()
Coverage.statementStart(11002)
        SanityCheck.check { children[0].getProvidedVariableNames().containsAll(variables) }
Coverage.statementStart(11003)
        SanityCheck.check { children[1].getProvidedVariableNames().containsAll(variables) }
Coverage.statementStart(11004)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11005)
        val childA = children[0].evaluate()
Coverage.statementStart(11006)
        val childB = children[1].evaluate()
Coverage.statementStart(11007)
        if (variables.size > 0) {
Coverage.ifStart(11008)
            for (variable in variables) {
Coverage.forLoopStart(11009)
                outMap[variable] = ColumnIteratorDebug(uuid, variable, ColumnIteratorMultiIterator(listOf(childA.columns[variable]!!, childB.columns[variable]!!)))
Coverage.statementStart(11010)
            }
Coverage.statementStart(11011)
            return IteratorBundle(outMap)
        } else {
Coverage.statementStart(11012)
            SanityCheck.check { childA.hasCountMode() && childB.hasCountMode() }
Coverage.statementStart(11013)
            var res = IteratorBundle(0)
Coverage.statementStart(11014)
            res.hasNext = { childA.hasNext() || childB.hasNext() }
Coverage.statementStart(11015)
            return res
        }
Coverage.statementStart(11016)
    }
}
