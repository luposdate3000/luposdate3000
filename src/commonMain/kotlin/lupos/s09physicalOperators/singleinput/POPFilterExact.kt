package lupos.s09physicalOperators.singleinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.resultFlowConsume
import lupos.s04arithmetikOperators.resultFlowProduce
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPFilterExact : POPBase {
    override val operatorID = EOperatorID.POPFilterExactID
    override val classname = "POPFilterExact"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val variable: AOPVariable
    val value: String
    val valueR: Value
    private val filterVariable: Variable
    override fun equals(other: Any?): Boolean {
        if (other !is POPFilterExact)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (variable != other.variable)
            return false
        if (value != other.value)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    constructor(dictionary: ResultSetDictionary, variable: AOPVariable, value: String, child: OPBase) : super() {
        this.dictionary = dictionary
        children[0] = child
        this.variable = variable
        this.value = value
        resultSet = children[0].resultSet
        valueR = resultSet.createValue(value)
        require(resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        filterVariable = resultSet.createVariable(variable.name)
    }

    override fun getRequiredVariableNames(): MutableList<String> {
        return mutableListOf(variable.name)
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPFilterExact.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                for (nextRow in children[0].channel) {
                    resultFlowConsume({ this@POPFilterExact }, { children[0] }, { nextRow })
                    if (nextRow[filterVariable] == valueR)
                        channel.send(resultFlowProduce({ this@POPFilterExact }, { nextRow }))
                }
                channel.close()
                children[0].channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children[0].channel.close(e)
            }
        }
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("name", variable.name).addAttribute("value", value)
}
