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
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPFilter(override val dictionary: ResultSetDictionary, filter: AOPBase, child: OPBase) : POPBase() {
    override val operatorID = EOperatorID.POPFilterID
    override val classname = "POPFilter"
    override val resultSet = child.resultSet
    override val children = arrayOf(child, filter)
    override fun childrenToVerifyCount() = 1

override fun toSparql():String{
return "{" +children[0].toSparql()+ " FILTER ("+children[1].toSparql()+")}"
}

    override fun equals(other: Any?): Boolean {
        if (other !is POPFilter)
            return false
        if (dictionary !== other.dictionary)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    override fun cloneOP() = POPFilter(dictionary, children[1].cloneOP() as AOPBase, children[0].cloneOP())

    override fun getProvidedVariableNames() = children[0].getProvidedVariableNames()

    override fun getRequiredVariableNames() = children[1].getRequiredVariableNamesRecoursive()

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPFilter.evaluate" }, {
        val children0Channel = children[0].evaluate()
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (nextRow in children0Channel) {
                    resultFlowConsume({ this@POPFilter }, { children[0] }, { nextRow })
                    try {
                        val expression = children[1] as AOPBase
                        val condition = expression.calculate(resultSet, nextRow) as AOPBoolean
                        if (condition.value)
                            channel.send(resultFlowProduce({ this@POPFilter }, { nextRow }))
                    } catch (e: Throwable) {
                        GlobalLogger.log(ELoggerType.DEBUG, { "silent :: " })
                        GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
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
