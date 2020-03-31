package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPMakeBooleanResult(query: Query, child: OPBase) : POPBase(query, EOperatorID.POPMakeBooleanResultID, "POPMakeBooleanResult", arrayOf(child)) {
    override fun equals(other: Any?): Boolean = other is POPMakeBooleanResult && children[0] == other.children[0]
    override fun toSparqlQuery() = "ASK{" + children[0].toSparql() + "}"
    override fun cloneOP() = POPMakeBooleanResult(query, children[0].cloneOP())
    override fun getProvidedVariableNames() = mutableListOf("?boolean")
    override fun getRequiredVariableNames() = listOf<String>()
    override suspend fun evaluate(): ColumnIteratorRow {
//TODO rows without any column
        val variables = children[0].getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate()
        val tmp = ColumnIteratorRepeatValue(1, query.dictionary.createValue(ValueBoolean(child.columns[variables[0]]!!.next() != null)))
        tmp.close = {
            tmp._close()
            for (variable in variables) {
                child.columns[variable]!!.close()
            }
        }
        outMap["?boolean"] = tmp
        return ColumnIteratorRow(outMap)
    }
}
