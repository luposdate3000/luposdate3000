package lupos.s07physicalOperators

import lupos.s00misc.*
import lupos.s07physicalOperators.POPExpression
import lupos.s07physicalOperators.POPEmptyRow
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s07physicalOperators.POPBase
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.data.LOPValues
import lupos.s03buildOperatorGraph.data.LOPExpression

import lupos.s00misc.XMLElement
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.Variable
import lupos.s06resultRepresentation.ResultSet


class POPValues : POPBase {
    private val resultSet = ResultSet()
    private val variables = mutableListOf<Variable>()
    private var iterator: Iterator<LOPExpression>
    private val rs = ResultSet()
    private val rr = rs.createResultRow()
    private val stringVars = mutableListOf<String>()
    private val values: List<LOPExpression>

    constructor(values: LOPValues) : super() {
        for (name in values.variables) {
            stringVars.add(name.name)
            variables.add(resultSet.createVariable(name.name))
        }
        this.values = values.values
        iterator = values.values.iterator()
    }

    override fun getProvidedVariableNames(): List<String> {
        return stringVars
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun hasNext(): Boolean {
        return iterator.hasNext()
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPValues.next")
            val rsOld = iterator.next()
            var rsNew = resultSet.createResultRow()
            val it = rsOld.child.children.iterator()
            for (variable in variables) {
                rsNew[variable] = resultSet.createValue(POPExpression(it.next()).evaluate(rs, rr))
            }
            return rsNew
        } finally {
            Trace.stop("POPValues.next")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPValues")
        val xmlvariables = XMLElement("LocalVariables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("LocalBindings")
        res.addContent(bindings)
        for (v in variables)
            xmlvariables.addContent(XMLElement("LocalVariable").addAttribute("name", resultSet.getVariable(v)))
        for (v in values) {
            val it = v.child.children.iterator()
            for (v2 in variables)
                bindings.addContent(XMLElement("LocalBinding").addAttribute("name", resultSet.getVariable(v2)).addContent(POPExpression(it.next()).evaluate(rs, rr)))
        }
        return res
    }
}
