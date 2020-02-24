package lupos.s09physicalOperators.singleinput

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.ELoggerType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPBind


class POPGroup : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    var by: List<AOPVariable>
    var bindings = mutableListOf<Pair<Variable, POPExpression>>()

    override fun equals(other: Any?): Boolean {
        if (other !is POPGroup)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (!by.equals(other.by))
            return false
        if (!bindings.equals(other.bindings))
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    constructor(dictionary: ResultSetDictionary, by: List<AOPVariable>, bindings: POPBind?, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        this.by = by
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        var tmpBind: OPBase? = bindings
        while (tmpBind != null && tmpBind is POPBind) {
            this.bindings.add(Pair(resultSet.createVariable(tmpBind.name.name), tmpBind.children[1] as POPExpression))
            resultSet.createVariable(tmpBind.name.name)
            tmpBind = tmpBind.children[0]
        }
        this.bindings = this.bindings.asReversed()
        for (v in by)
            resultSet.createVariable(v.name)
    }

    override fun getProvidedVariableNames(): List<String> {
        val tmp = mutableListOf<String>()
        for (v in by)
            tmp.add(v.name)
        for (v in bindings)
            tmp.add(resultSet.getVariable(v.first))
        return tmp
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        require(additionalProvided.isEmpty())
        val localProvide = additionalProvided + children[0].getProvidedVariableNames()
        val localRequire = mutableListOf<String>()
        for (v in by)
            localRequire.add(v.name)
        for (b in bindings)
            localRequire += b.second.getRequiredVariableNames()
        for (c in children)
            c.syntaxVerifyAllVariableExists(localProvide, autocorrect)
        val res = localProvide.containsAll(localRequire)
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("undefined Variable")
            }
        }
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPGroup.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                val tmpMutableMap = mutableMapOf<String, MutableList<ResultRow>>()
                val variables = mutableListOf<Pair<Variable, Variable>>()
                for (v in by)
                    variables.add(Pair(resultSet.createVariable(v.name), children[0].resultSet.createVariable(v.name)))
                for (rsOld in children[0].channel) {
                    resultFlowConsume({ this@POPGroup }, { children[0] }, { rsOld })
                    var key: String = "|"
                    for (variable in variables)
                        key = key + children[0].resultSet.getValue(rsOld[variable.second]) + "|"
                    var tmp = tmpMutableMap[key]
                    if (tmp == null) {
                        tmp = mutableListOf<ResultRow>()
                        tmpMutableMap[key] = tmp
                    }
                    tmp.add(rsOld)
                }
                if (tmpMutableMap.keys.size == 0) {
                    val rsNew = resultSet.createResultRow()
                    for (b in variables)
                        resultSet.setUndefValue(rsNew, b.first)
                    for (b in bindings)
                        resultSet.setUndefValue(rsNew, b.first)
                    channel.send(resultFlowProduce({ this@POPGroup }, { rsNew }))
                }
                for (k in tmpMutableMap.keys) {
                    val rsOld = tmpMutableMap[k]!!.first()
                    val rsNew = resultSet.createResultRow()
                    for (variable in variables)
                        rsNew[variable.first] = rsOld[variable.second]
                    for (b in bindings) {
                        try {
                            val value = b.second.evaluate(children[0].resultSet, tmpMutableMap[k]!!)
                            if (value == null)
                                resultSet.setUndefValue(rsNew, b.first)
                            else
                                rsNew[b.first] = resultSet.createValue(value)
                        } catch (e: Throwable) {
                            resultSet.setUndefValue(rsNew, b.first)
                            GlobalLogger.log(ELoggerType.DEBUG, { "silent :: " })
                            GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
                        }
                    }
                    channel.send(resultFlowProduce({ this@POPGroup }, { rsNew }))
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
        val res = XMLElement("POPGroup")
        val byxml = XMLElement("by")
        res.addContent(byxml)
        for (b in by)
            byxml.addContent(XMLElement("variable").addAttribute("name", b.name))
        val xmlbindings = XMLElement("bindings")
        res.addContent(xmlbindings)
        for (b in bindings) {
            xmlbindings.addContent(XMLElement("binding").addAttribute("name", resultSet.getVariable(b.first)).addContent(b.second.toXMLElement()))
        }
        res.addContent(childrenToXML())
        return res
    }
}
