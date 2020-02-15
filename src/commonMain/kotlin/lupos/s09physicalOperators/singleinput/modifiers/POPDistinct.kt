package lupos.s09physicalOperators.singleinput.modifiers

import kotlinx.coroutines.*
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPDistinct : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private var data: MutableList<ResultRow>? = null
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>? = null

    constructor(dictionary: ResultSetDictionary, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        for (name in children[0].resultSet.getVariableNames()) {
            variables.add(Pair(resultSet.createVariable(name), children[0].resultSet.createVariable(name)))
        }
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun evaluate() {
        for (c in children)
            c.evaluate()
        runBlocking {
            if (data == null) {
                val tmpMutableMap = mutableMapOf<String, ResultRow>()
                for (rsOld in children[0].channel) {
                    val rsNew = resultSet.createResultRow()
                    var key: String = ""
                    for (variable in variables) {
                        rsNew[variable.first] = rsOld[variable.second]
                        key += "-" + rsOld[variable.second]
                    }
                    tmpMutableMap[key] = rsNew
                }
                data = mutableListOf<ResultRow>()
                for (k in tmpMutableMap.keys) {
                    data!!.add(tmpMutableMap[k]!!)
                }
                iterator = data!!.listIterator()
            }
            if (iterator == null || !iterator!!.hasNext()) {
            } else {
                channel.send(iterator!!.next())
            }
            channel.close()
            for (c in children)
                c.channel.close()
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPDistinct")
        res.addContent(childrenToXML())
        return res
    }
}
