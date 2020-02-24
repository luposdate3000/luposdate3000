package lupos.s09physicalOperators.singleinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase


class POPFilter : POPBase {
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

    constructor(dictionary: ResultSetDictionary, filter: POPExpression, child: OPBase) : super() {
        this.dictionary = dictionary
        children[0] = child
        children[1] = filter
        resultSet = children[0].resultSet
    }

    override fun getProvidedVariableNames()=children[0].getProvidedVariableNames().distinct()

    override fun getRequiredVariableNames(): List<String> {
        return children[1].getRequiredVariableNames()
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPFilter.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                for (nextRow in children[0].channel) {
                    resultFlowConsume({ this@POPFilter }, { children[0] }, { nextRow })
                    try {
                        if ((children[1] as POPExpression).evaluateBoolean(resultSet, nextRow))
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

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPFilter")
        res.addContent(childrenToXML())
        return res
    }
}
