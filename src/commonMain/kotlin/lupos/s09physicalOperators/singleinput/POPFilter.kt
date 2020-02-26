package lupos.s09physicalOperators.singleinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.Trace
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s09physicalOperators.POPBase


class POPFilter : POPBase {
    override val operatorID = EOperatorID.POPFilterID
    override val classname = "POPFilter"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    override fun childrenToVerifyCount() = 1

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

    constructor(dictionary: ResultSetDictionary, filter: AOPBase, child: OPBase) : super() {
        this.dictionary = dictionary
        children[0] = child
        children[1] = filter
        resultSet = children[0].resultSet
    }

    override fun getProvidedVariableNames(): List<String> {
        val res = children[0].getProvidedVariableNames().distinct()
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = children[1].getRequiredVariableNamesRecoursive()
        return res
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPFilter.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                for (nextRow in children[0].channel) {
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
                children[0].channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children[0].channel.close(e)
            }
        }
    })

}
