package lupos.s07physicalOperators.singleinput
import lupos.s00misc.*

import lupos.s07physicalOperators.singleinput.POPSort
import lupos.s07physicalOperators.singleinput.POPSingleInputBaseNullableIterator
import lupos.s07physicalOperators.singleinput.POPSingleInputBase
import lupos.s07physicalOperators.singleinput.POPRename
import lupos.s07physicalOperators.singleinput.POPProjection
import lupos.s07physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s07physicalOperators.singleinput.POPGroup
import lupos.s07physicalOperators.singleinput.POPFilter
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPBindUndefined
import lupos.s07physicalOperators.singleinput.POPBind
import lupos.s07physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s07physicalOperators.POPBase
import lupos.s03buildOperatorGraph.data.LOPVariable

import lupos.s00misc.XMLElement
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet


class POPTemporaryStore : POPBase {
    private val data = mutableListOf<ResultRow>()
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>
    private val child: POPBase

    constructor(child: POPBase) : super() {
        this.child = child
        iterator = child
        resultSetOld = child.getResultSet()
        for (name in resultSetOld.getVariableNames()) {
            variables.add(Pair(resultSetNew.createVariable(name), resultSetOld.createVariable(name)))
        }
    }

    override fun getProvidedVariableNames(): List<String> {
        return child.getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child.getRequiredVariableNames()
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        return iterator.hasNext()
    }

    override fun next(): ResultRow {
Trace.start(this)
        if (iterator == child) {
            val rsOld = child.next()
            var rsNew = resultSetNew.createResultRow()
            for (variable in variables) {
                rsNew[variable.first] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variable.second]))
            }
            data.add(rsNew)
Trace.stop(this)
            return rsNew
        }
        val rsOld = iterator.next()
        var rsNew = resultSetNew.createResultRow()
        for (variable in variables) {
            rsNew[variable.first] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variable.second]))
        }
        Trace.stop(this)
return rsNew
    }

    fun reset() {
        iterator = data.listIterator()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPTemporaryStore")
        res.addContent(child.toXMLElement())
        return res
    }
}
