package lupos.s09physicalOperators.singleinput

import kotlinx.coroutines.*
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase


class POPFilter : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    val filter: POPExpression

    constructor(dictionary: ResultSetDictionary, filter: POPExpression, child: OPBase) : super() {
        this.dictionary = dictionary
        children[0] = child
        this.filter = filter
        resultSet = children[0].resultSet
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return filter.getRequiredVariableNames()
    }

    override fun evaluate() {
        for (c in children)
            c.evaluate()
        runBlocking {
            for (nextRow in children[0].channel) {
                if (filter.evaluateBoolean(resultSet, nextRow)) {
                    channel.send(nextRow)
                }
            }
            channel.close()
            for (c in children)
                c.channel.close()
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPFilter")
        res.addContent(XMLElement("filter").addContent(filter.toXMLElement()))
        res.addContent(childrenToXML())
        return res
    }
}
