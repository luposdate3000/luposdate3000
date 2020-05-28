package lupos.s09physicalOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase
class POPMakeBooleanResult(query: Query, projectedVariables: List<String>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPMakeBooleanResultID, "POPMakeBooleanResult", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun equals(other: Any?): Boolean = other is POPMakeBooleanResult && children[0] == other.children[0]
    override fun toSparqlQuery() = "ASK{" + children[0].toSparql() + "}"
    override fun cloneOP() = POPMakeBooleanResult(query, projectedVariables, children[0].cloneOP())
    override fun getProvidedVariableNamesInternal() = mutableListOf("?boolean")
    override fun getRequiredVariableNames() = listOf<String>()
    override suspend fun evaluate(): IteratorBundle {
Coverage.funStart(11900)
        var flag: Boolean
Coverage.statementStart(11901)
        val outMap = mutableMapOf<String, ColumnIterator>()
Coverage.statementStart(11902)
        val variables = children[0].getProvidedVariableNames()
Coverage.statementStart(11903)
        var child: IteratorBundle? = null
Coverage.statementStart(11904)
        if (children[0] is OPNothing) {
Coverage.ifStart(11905)
            flag = false
Coverage.statementStart(11906)
        } else if (children[0] is OPEmptyRow) {
Coverage.ifStart(11907)
            flag = true
Coverage.statementStart(11908)
        } else {
Coverage.ifStart(11909)
            child = children[0].evaluate()
Coverage.statementStart(11910)
            if (variables.size > 0) {
Coverage.ifStart(11911)
                flag = child.columns[variables[0]]!!.next() != null
Coverage.statementStart(11912)
            } else {
Coverage.ifStart(11913)
                flag = child.hasNext()
Coverage.statementStart(11914)
            }
Coverage.statementStart(11915)
        }
Coverage.statementStart(11916)
        val tmp = ColumnIteratorRepeatValue(1, query.dictionary.createValue(ValueBoolean(flag)))
Coverage.statementStart(11917)
        if (child != null) {
Coverage.ifStart(11918)
            tmp.close = {
Coverage.statementStart(11919)
                tmp._close()
Coverage.statementStart(11920)
                for (variable in variables) {
Coverage.forLoopStart(11921)
                    child.columns[variable]!!.close()
Coverage.statementStart(11922)
                }
Coverage.statementStart(11923)
            }
Coverage.statementStart(11924)
        }
Coverage.statementStart(11925)
        outMap["?boolean"] = ColumnIteratorDebug(uuid, "?success", tmp)
Coverage.statementStart(11926)
        return IteratorBundle(outMap)
    }
}
