package lupos.s09physicalOperators.singleinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPFilterExact : POPBase {
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

    override fun evaluate() = Trace.trace<Unit>({ "POPFilterExact.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                for (nextRow in children[0].channel)
                    if (nextRow[filterVariable] == valueR)
                        channel.send(nextRow)
                channel.close()
                children[0].channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children[0].channel.close(e)
            }
        }
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPFilterExact")
        res.addAttribute("name", variable.name)
        res.addAttribute("value", value)
        res.addContent(childrenToXML())
        return res
    }
}
