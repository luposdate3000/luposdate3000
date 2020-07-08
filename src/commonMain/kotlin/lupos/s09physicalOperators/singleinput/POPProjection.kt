package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPProjection(query: Query, projectedVariables: List<String>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPProjectionID, "POPProjection", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun toSparql(): String {
        var res = "{SELECT "
        for (c in projectedVariables) {
            res += AOPVariable(query, c).toSparql() + " "
        }
        res += "{"
        res += children[0].toSparql()
        res += "}}"
        return res
    }

    override fun cloneOP() = POPProjection(query, projectedVariables, children[0].cloneOP())
    override fun equals(other: Any?) = other is POPProjection && projectedVariables == other.projectedVariables && children[0] == other.children[0]
    override fun getProvidedVariableNamesInternal(): List<String> = projectedVariables
    override fun getRequiredVariableNames(): List<String> = projectedVariables
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        val variables = getProvidedVariableNames()
        val child = children[0].evaluate(parent)
        val outMap = mutableMapOf<String, ColumnIterator>()
        if (variables.containsAll(children[0].getProvidedVariableNames())) {
            return child
        }
        if (variables.size == 0) {
            val variables2 = children[0].getProvidedVariableNames()
            SanityCheck {
                SanityCheck.check { variables2.size > 0 }
                for (variable in variables2) {
                    SanityCheck.check { child.columns[variable] != null }
                }
            }
            val column = child.columns[variables2[0]]!!
            val res = IteratorBundle(outMap)
            res.hasNext2 = {
                /*return*/                column.next() != null
            }
            res.hasNext2Close = {
                column.close()
            }
            return res
        } else {
            for (variable in variables) {
                SanityCheck.check { child.columns[variable] != null }
                outMap[variable] = ColumnIteratorDebug(uuid, variable, child.columns[variable]!!)
            }
            return IteratorBundle(outMap)
        }
/*Coverage Unreachable*/
    }
}
