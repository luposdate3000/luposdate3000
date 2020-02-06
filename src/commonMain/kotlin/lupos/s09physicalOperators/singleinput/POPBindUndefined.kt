package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.POPBind


class POPBindUndefined : POPBase {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val name: LOPVariable
    private val resultSetOld: ResultSet
    private val resultSetNew: ResultSet
    private val variablesOld: Array<Variable?>
    private val variablesNew: Array<Variable?>
    private val variableBound: Variable

    constructor(dictionary: ResultSetDictionary, name: LOPVariable, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSetNew = ResultSet(dictionary)
        children[0] = child
        this.name = name
        resultSetOld = children[0].getResultSet()
        val variableNames = resultSetOld.getVariableNames()
        variablesOld = Array<Variable?>(variableNames.size, init = fun(_: Int) = (null as Variable?))
        variablesNew = Array<Variable?>(variableNames.size + 1, init = fun(_: Int) = (null as Variable?))
        var i = 0
        variableBound = resultSetNew.createVariable(name.name)
        for (n in variableNames) {
            variablesOld[i] = resultSetOld.createVariable(n)
            variablesNew[i] = resultSetNew.createVariable(n)
            i++
        }
        variablesNew[i] = variableBound
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(name.name) + children[0].getRequiredVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPBindUndefined.hasNext")
            val res = children[0].hasNext()
            return res
        } finally {
            Trace.stop("POPBindUndefined.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPBindUndefined.next")
            var rsNew = resultSetNew.createResultRow()
            val rsOld = children[0].next()
            for (i in variablesOld.indices) {
                // TODO reuse resultSet
                rsNew[variablesNew[i]!!] = resultSetNew.createValue(resultSetOld.getValue(rsOld[variablesOld[i]!!]))
            }
            resultSetNew.setUndefValue(rsNew, variableBound)
            return rsNew
        } finally {
            Trace.stop("POPBindUndefined.next")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPBindUndefined")
        res.addAttribute("name", name.name)
        res.addContent(childrenToXML())
        return res
    }
}
