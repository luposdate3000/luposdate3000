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
        var flag: Boolean
        val outMap = mutableMapOf<String, ColumnIterator>()
        val variables = children[0].getProvidedVariableNames()
        var child: IteratorBundle? = null
        if (children[0] is OPNothing) {
            flag = false
        } else if (children[0] is OPEmptyRow) {
            flag = true
        } else {
            child = children[0].evaluate()
            if (variables.size > 0) {
                flag = child.columns[variables[0]]!!.next() != null
            } else {
                flag = child.hasNext()
            }
        }
        val tmp = ColumnIteratorRepeatValue(1, query.dictionary.createValue(ValueBoolean(flag)))
        if (child != null) {
            tmp.close = {
                tmp._close()
                for (variable in variables) {
                    child.columns[variable]!!.close()
                }
            }
        }
        outMap["?boolean"] = ColumnIteratorDebug(uuid, "?success", tmp)
        return IteratorBundle(outMap)
    }
}
