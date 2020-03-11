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
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase


class POPFilter(query: Query, filter: AOPBase, child: OPBase) : POPBase(query, EOperatorID.POPFilterID, "POPFilter", child.resultSet, arrayOf(child, filter)) {

    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT "))
            return "{SELECT * {" + sparql + ". FILTER (" + children[1].toSparql() + ")}}"
        return "{SELECT * {" + sparql + " FILTER (" + children[1].toSparql() + ")}}"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPFilter)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun childrenToVerifyCount() = 1
    override fun cloneOP() = POPFilter(query, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun getProvidedVariableNames() = children[0].getProvidedVariableNames()
    override fun getRequiredVariableNames() = children[1].getRequiredVariableNamesRecoursive()

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPFilter.evaluate" }, {
        val children0Channel = children[0].evaluate()
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                val expression = children[1] as AOPBase
                if (expression is AOPConstant) {
                    try {
                        if (expression.value.toBoolean())
                            for (nextRow in children0Channel) {
                                resultFlowConsume({ this@POPFilter }, { children[0] }, { nextRow })
                                channel.send(resultFlowProduce({ this@POPFilter }, { nextRow }))
                            }
                    } catch (e: Throwable) {
                        GlobalLogger.log(ELoggerType.DEBUG, { "silent :: " })
                        GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
                    }
                } else if (expression is AOPNEQ && (expression.children[0] is AOPConstant || expression.children[0] is AOPVariable) && (expression.children[1] is AOPConstant || expression.children[1] is AOPVariable)) {
                    val childA = expression.children[0]
                    val childB = expression.children[1]
                    if (childA is AOPConstant || childB is AOPConstant) {
                        var constID: Value
                        var variableID: Variable
                        if (childA is AOPConstant) {
                            constID = resultSet.createValue(childA.value)
                            variableID = resultSet.createVariable((childB as AOPVariable).name)
                        } else {
                            constID = resultSet.createValue((childB as AOPConstant).value)
                            variableID = resultSet.createVariable((childA as AOPVariable).name)
                        }
                        for (nextRow in children0Channel) {
                            resultFlowConsume({ this@POPFilter }, { children[0] }, { nextRow })
                            try {
                                if (constID == resultSet.getValue(nextRow, variableID))
                                    channel.send(resultFlowProduce({ this@POPFilter }, { nextRow }))
                            } catch (e: Throwable) {
                                GlobalLogger.log(ELoggerType.DEBUG, { "silent :: " })
                                GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
                            }
                        }
                    } else {
                        val variableIDA = resultSet.createVariable((childA as AOPVariable).name)
                        val variableIDB = resultSet.createVariable((childB as AOPVariable).name)
                        for (nextRow in children0Channel) {
                            resultFlowConsume({ this@POPFilter }, { children[0] }, { nextRow })
                            try {
                                if (resultSet.getValue(nextRow, variableIDA) != resultSet.getValue(nextRow, variableIDB))
                                    channel.send(resultFlowProduce({ this@POPFilter }, { nextRow }))
                            } catch (e: Throwable) {
                                GlobalLogger.log(ELoggerType.DEBUG, { "silent :: " })
                                GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
                            }
                        }
                    }
                } else {
                    for (nextRow in children0Channel) {
                        resultFlowConsume({ this@POPFilter }, { children[0] }, { nextRow })
                        try {
                            if (expression.calculate(resultSet, nextRow).toBoolean())
                                channel.send(resultFlowProduce({ this@POPFilter }, { nextRow }))
                        } catch (e: Throwable) {
                            GlobalLogger.log(ELoggerType.DEBUG, { "silent :: " })
                            GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
                        }
                    }
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

}
