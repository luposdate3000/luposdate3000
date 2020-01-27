package lupos.s07physicalOperators.singleinput

import lupos.s00misc.*

import lupos.s07physicalOperators.singleinput.POPSingleInputBase
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


class POPRename : POPSingleInputBase {
    var nameTo: LOPVariable
    var nameFrom: LOPVariable
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>

    constructor(nameTo: LOPVariable, nameFrom: LOPVariable, child: POPBase) : super(child) {
        this.nameTo = nameTo
        this.nameFrom = nameFrom
        resultSetOld = child.getResultSet()
        val variableNames = resultSetOld.getVariableNames()
        variablesOld = Array<Variable?>(variableNames.size, init = fun(_: Int) = (null as Variable?))
        variablesNew = Array<Variable?>(variableNames.size, init = fun(_: Int) = (null as Variable?))
        var i = 0
        for (name in variableNames) {
            variablesOld[i] = resultSetOld.createVariable(name)
            if (name == nameFrom.name)
                variablesNew[i] = resultSetNew.createVariable(nameTo.name)
            else
                variablesNew[i] = resultSetNew.createVariable(name)
            i++
        }
    }

    override fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        val variables = child.getProvidedVariableNames()
        for (v in variables) {
            if (v == nameFrom.name)
                res.add(nameTo.name)
            else
                res.add(v)
        }
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = mutableListOf<String>()
        val variables = child.getProvidedVariableNames()
        for (v in variables) {
            res.add(v)
        }
        res.add(nameFrom.name)
        return res
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        val res = child.hasNext()
        return res
    }

    override fun next(): ResultRow {
        try {
            Trace.start(this)
            var rsNew = resultSetNew.createResultRow()
            val rsOld = child.next()
            for (i in variablesNew.indices) {
                // TODO reuse resultSet
                rsNew[variablesNew[i]!!] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variablesOld[i]!!]))
            }
            return rsNew
        } finally {
            Trace.stop(this)
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPRename")
        res.addAttribute("nameTo", nameTo.name)
        res.addAttribute("nameFrom", nameFrom.name)
        res.addContent(child.toXMLElement())
        return res
    }
}
