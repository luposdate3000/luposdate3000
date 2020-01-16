package lupos.s5physicalOperators

import lupos.s1buildSyntaxTree.sparql1_1.ASTValue
import lupos.s1buildSyntaxTree.sparql1_1.ASTValues
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s2buildOperatorGraph.data.LOPValues
import lupos.s2buildOperatorGraph.data.LOPExpression
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
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

    constructor(values: LOPValues) : super() {
        for (name in values.variables) {
            stringVars.add(name.name)
            variables.add(resultSet.createVariable(name.name))
        }
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

    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n"
    }
}
