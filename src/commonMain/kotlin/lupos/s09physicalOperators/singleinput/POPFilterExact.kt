package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator


class POPFilterExact : POPBaseNullableIterator {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val variable: LOPVariable
    val value: String
    val valueR: Value
    private val filterVariable: Variable

    constructor(dictionary: ResultSetDictionary, variable: LOPVariable, value: String, child: OPBase) : super() {
        this.dictionary = dictionary
        children[0] = child
        this.variable = variable
        this.value = value
        resultSet = children[0].resultSet
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

    override fun nnext(): ResultRow? = Trace.trace({ "POPFilterExact.nnext" }, {
        while (true) {
            if (!children[0].hasNext())
                return null
            val nextRow = children[0].next()
            if (nextRow[filterVariable] == valueR)
                return nextRow
        }
    }) as ResultRow?

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPFilterExact")
        res.addAttribute("name", variable.name)
        res.addAttribute("value", value)
        res.addContent(childrenToXML())
        return res
    }
}
