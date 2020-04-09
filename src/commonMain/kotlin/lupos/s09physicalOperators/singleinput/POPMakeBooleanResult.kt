package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPMakeBooleanResult(query: Query, projectedVariables: List<String>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPMakeBooleanResultID, "POPMakeBooleanResult", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun equals(other: Any?): Boolean = other is POPMakeBooleanResult && children[0] == other.children[0]
    override fun toSparqlQuery() = "ASK{" + children[0].toSparql() + "}"
    override fun cloneOP() = POPMakeBooleanResult(query, projectedVariables, children[0].cloneOP())
    override fun getProvidedVariableNamesInternal() = mutableListOf("?boolean")
    override fun getRequiredVariableNames() = listOf<String>()
    override suspend fun evaluate(): ColumnIteratorRow {
//TODO rows without any column
        val variables = children[0].getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        val tmp: ColumnIteratorRepeatValue
        if (variables.size > 0) {
            tmp = ColumnIteratorRepeatValue(1, query.dictionary.createValue(ValueBoolean(child.columns[variables[0]]!!.next() != null)))
        } else {
            tmp = ColumnIteratorRepeatValue(1, query.dictionary.createValue(ValueBoolean(child.hasNext())))
        }
        tmp.close = {
            tmp._close()
            for (variable in variables) {
                child.columns[variable]!!.close()
            }
        }
        outMap["?boolean"] = ColumnIteratorDebug(uuid, "?success", tmp)
        return ColumnIteratorRow(outMap)
    }
}
