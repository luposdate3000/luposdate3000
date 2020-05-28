package lupos.s09physicalOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
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
Coverage.funStart(12044)
        var res = "{SELECT "
Coverage.statementStart(12045)
        for (c in projectedVariables) {
Coverage.forLoopStart(12046)
            res += AOPVariable(query, c).toSparql() + " "
Coverage.statementStart(12047)
        }
Coverage.statementStart(12048)
        res += "{"
Coverage.statementStart(12049)
        res += children[0].toSparql()
Coverage.statementStart(12050)
        res += "}}"
Coverage.statementStart(12051)
        return res
    }
    override fun cloneOP() = POPProjection(query, projectedVariables, children[0].cloneOP())
    override fun equals(other: Any?): Boolean {
Coverage.funStart(12052)
        if (other !is POPProjection) {
Coverage.ifStart(12053)
            return false
        }
Coverage.statementStart(12054)
        if (projectedVariables != other.projectedVariables) {
Coverage.ifStart(12055)
            return false
        }
Coverage.statementStart(12056)
        if (children[0] != other.children[0]) {
Coverage.ifStart(12057)
            return false
        }
Coverage.statementStart(12058)
        return true
    }
    override fun getProvidedVariableNamesInternal(): List<String> = projectedVariables
    override fun getRequiredVariableNames(): List<String> = projectedVariables
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(12059)
        val variables = getProvidedVariableNames()
Coverage.statementStart(12060)
        val child = children[0].evaluate()
Coverage.statementStart(12061)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(12062)
        if (variables.containsAll(children[0].getProvidedVariableNames())) {
Coverage.ifStart(12063)
            return child
        }
Coverage.statementStart(12064)
        if (variables.size == 0) {
Coverage.ifStart(12065)
            val variables2 = children[0].getProvidedVariableNames()
Coverage.statementStart(12066)
            SanityCheck {
Coverage.statementStart(12067)
                SanityCheck.check { variables2.size > 0 }
Coverage.statementStart(12068)
                for (variable in variables2) {
Coverage.forLoopStart(12069)
                    SanityCheck.check { child.columns[variable] != null }
Coverage.statementStart(12070)
                }
Coverage.statementStart(12071)
            }
Coverage.statementStart(12072)
            val column = child.columns[variables2[0]]!!
Coverage.statementStart(12073)
            val res = IteratorBundle(outMap)
Coverage.statementStart(12074)
            res.hasNext = {
Coverage.statementStart(12075)
                /*return*/                column.next() != null
            }
Coverage.statementStart(12076)
            return res
        } else {
Coverage.statementStart(12077)
            for (variable in variables) {
Coverage.forLoopStart(12078)
                SanityCheck.check { child.columns[variable] != null }
Coverage.statementStart(12079)
                outMap[variable] = ColumnIteratorDebug(uuid, variable, child.columns[variable]!!)
Coverage.statementStart(12080)
            }
Coverage.statementStart(12081)
            return IteratorBundle(outMap)
        }
Coverage.statementStart(12082)
    }
}
