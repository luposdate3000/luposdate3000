package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined


class POPFilterExact : POPBaseNullableIterator {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val variable: LOPVariable
    val value: String
    val valueR: Value
    private val resultSet: ResultSet
    private val filterVariable: Variable

    constructor(dictionary: ResultSetDictionary, variable: LOPVariable, value: String, child: OPBase) : super() {
        this.dictionary = dictionary
        children[0] = child
        this.variable = variable
        this.value = value
        resultSet = children[0].getResultSet()
        valueR = resultSet.createValue(value)
        require(resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        filterVariable = resultSet.createVariable(variable.name)
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return listOf(variable.name)
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun nnext(): ResultRow? {
        try {
            Trace.start("POPFilterExact.nnext")
            while (true) {
                if (!children[0].hasNext())
                    return null
                val nextRow = children[0].next()
                if (nextRow[filterVariable] == valueR)
                    return nextRow
            }
        } finally {
            Trace.stop("POPFilterExact.nnext")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPFilterExact")
        res.addAttribute("name", variable.name)
        res.addAttribute("value", value)
        res.addContent(childrenToXML())
        return res
    }
}
