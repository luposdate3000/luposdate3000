package lupos.s5physicalOperators
import lupos.s06resultRepresentation.ResultSet

import lupos.misc.XMLElement

import lupos.s1buildSyntaxTree.sparql1_1.ASTValue
import lupos.s1buildSyntaxTree.sparql1_1.ASTValues
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s2buildOperatorGraph.data.LOPValues
import lupos.s2buildOperatorGraph.data.LOPExpression
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase
import lupos.s5physicalOperators.POPExpression

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
        val rsOld = iterator.next()
        var rsNew = resultSet.createResultRow()
        val it = rsOld.child.children.iterator()
        for (variable in variables) {
            rsNew[variable] = resultSet.createValue(POPExpression(it.next()).evaluate(rs, rr))
        }
        return rsNew
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
