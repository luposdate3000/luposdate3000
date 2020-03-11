package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
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
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase


class POPBind(query: Query, @JvmField val name: AOPVariable, value: AOPBase, child: OPBase) : POPBase(query, EOperatorID.POPBindID, "POPBind", ResultSet(query.dictionary), arrayOf(child, value)) {

    override fun equals(other: Any?): Boolean {
        if (other !is POPBind)
            return false
        if (name != other.name)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun toSparql(): String {
        if (children[1] is AOPConstant && (children[1] as AOPConstant).value is ValueUndef)
            return children[0].toSparql()
        var res = "{SELECT "
        for (v in children[0].getProvidedVariableNames())
            res += AOPVariable(query, v).toSparql() + " "
        res += "(" + children[1].toSparql() + " as " + name.toSparql() + "){"
        res += children[0].toSparql()
        res += "}}"
        return res
    }

    override fun cloneOP() = POPBind(query, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())


    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNames(): List<String> {
        return (children[0].getProvidedVariableNames() + name.name).distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[1].getRequiredVariableNames()
    }

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPBind.evaluate" }, {
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
                children0Channel.forEach {rowOld->
                    resultFlowConsume({ this@POPBind }, { children[0] }, { rowOld })
                    var rsNew = resultSet.createResultRow()
                    for (i in variablesOld.indices)
                        resultSet.copy(rsNew, variablesNew[i]!!, rowOld, variablesOld[i]!!, children[0].resultSet)
                    try {
                        val value = (children[1] as AOPBase).calculate(children[0].resultSet, rowOld).valueToString()
                        resultSet.setValue(rsNew, variableBound, value)
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
                children0Channel.close()
            }
        }
        return ResultIterator(next = {
                channel.receive()
        }, close = {
            channel.close()
        })
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name.name)
}
