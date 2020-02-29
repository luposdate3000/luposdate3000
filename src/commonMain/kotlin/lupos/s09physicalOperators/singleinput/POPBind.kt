package lupos.s09physicalOperators.singleinput

import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPBind(override val dictionary: ResultSetDictionary, val name: AOPVariable, value: AOPBase, child: OPBase) : POPBase() {
    override val operatorID = EOperatorID.POPBindID
    override val classname = "POPBind"
    override val resultSet = ResultSet(dictionary)
    override val children: Array<OPBase> = arrayOf(child, value)

    override fun equals(other: Any?): Boolean {
        if (other !is POPBind)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (name != other.name)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun cloneOP() = POPBind(dictionary, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())


    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNames(): List<String> {
        return (children[0].getProvidedVariableNames() + name.name).distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[1].getRequiredVariableNames()
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPBind.evaluate" }, {
        val variablesOld: Array<Variable?>
        val variablesNew: Array<Variable?>
        val variableBound: Variable
        val variableNames = children[0].getProvidedVariableNames()
        variablesOld = Array(variableNames.size, init = fun(_: Int) = (null as Variable?))
        variablesNew = Array(variableNames.size + 1, init = fun(_: Int) = (null as Variable?))
        var i = 0
        variableBound = resultSet.createVariable(name.name)
        for (n in variableNames) {
            variablesOld[i] = children[0].resultSet.createVariable(n)
            variablesNew[i] = resultSet.createVariable(n)
            i++
        }
        variablesNew[i] = variableBound
        val children0Channel = children[0].evaluate()
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (rsOld in children0Channel) {
                    resultFlowConsume({ this@POPBind }, { children[0] }, { rsOld })
                    var rsNew = resultSet.createResultRow()
                    for (i in variablesOld.indices)
                        rsNew[variablesNew[i]!!] = rsOld[variablesOld[i]!!]
                    try {
                        val value = (children[1] as AOPBase).calculate(children[0].resultSet, rsOld).valueToString()
                        if (value == null)
                            resultSet.setUndefValue(rsNew, variableBound)
                        else
                            rsNew[variableBound] = resultSet.createValue(value)
                    } catch (e: Throwable) {
                        resultSet.setUndefValue(rsNew, variableBound)
                        GlobalLogger.log(ELoggerType.DEBUG, { "silent :: " })
                        GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
                    }
                    channel.send(resultFlowProduce({ this@POPBind }, { rsNew }))
                }
                channel.close()
                children0Channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children0Channel.close(e)
            }
        }
        return channel
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name.name)
}
