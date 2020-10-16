package lupos.s09physicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean

import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRepeatValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class POPMakeBooleanResult(query: Query, projectedVariables: List<String>, child: OPBase) : POPBase(query, projectedVariables, EOperatorID.POPMakeBooleanResultID, "POPMakeBooleanResult", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check { children[0].getPartitionCount(variable) == 1 }
        return 1
    }

    override fun equals(other: Any?): Boolean = other is POPMakeBooleanResult && children[0] == other.children[0]
    override fun toSparqlQuery() = "ASK{" + children[0].toSparql() + "}"
    override fun cloneOP() = POPMakeBooleanResult(query, projectedVariables, children[0].cloneOP())
    override fun getProvidedVariableNamesInternal() = mutableListOf("?boolean")
    override fun getRequiredVariableNames() = listOf<String>()
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        var flag: Boolean
        val outMap = mutableMapOf<String, ColumnIterator>()
        val variables = children[0].getProvidedVariableNames()
        if (children[0] is OPNothing) {
            flag = false
        } else if (children[0] is OPEmptyRow) {
            flag = true
        } else {
            val child = children[0].evaluate(parent)
            if (variables.size > 0) {
                flag = child.columns[variables[0]]!!.next() != ResultSetDictionary.nullValue
                for (variable in variables) {
                    child.columns[variable]!!.close()
                }
            } else {
                flag = child.hasNext2()
                child.hasNext2Close()
            }
        }
        outMap["?boolean"] = ColumnIteratorRepeatValue(1, query.dictionary.createValue(ValueBoolean(flag)))
        return IteratorBundle(outMap)
    }
}
