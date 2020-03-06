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
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPBind


class POPGroup : POPBase {
    override val operatorID = EOperatorID.POPGroupID
    override val classname = "POPGroup"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    @JvmField
    var by: List<AOPVariable>
    @JvmField
    var bindings = mutableListOf<Pair<Variable, AOPBase>>()

    override fun toSparql(): String {
        var res = children[0].toSparql()
        res += " GROUP BY "
        for (b in by)
            res += b.toSparql() + " "
        for ((k, v) in bindings) {
            res += "(" + v.toSparql() + " AS " + AOPVariable(resultSet.getVariable(k)).toSparql() + ")"
        }
        return res
    }

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

    override fun cloneOP(): POPGroup {
        if (bindings.size > 0) {
            var tmpBindings = POPBind(dictionary, AOPVariable(resultSet.getVariable(bindings[0].first)), bindings[0].second, OPNothing())
            for (bp in 1 until bindings.size)
                tmpBindings = POPBind(dictionary, AOPVariable(resultSet.getVariable(bindings[0].first)), bindings[0].second, tmpBindings)
            return POPGroup(dictionary, by, tmpBindings, children[0].cloneOP())
        } else
            return POPGroup(dictionary, by, null, children[0].cloneOP())
    }

    constructor(dictionary: ResultSetDictionary, by: List<AOPVariable>, bindings: POPBind?, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        this.by = by
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        var tmpBind: OPBase? = bindings
        while (tmpBind != null && tmpBind is POPBind) {
            this.bindings.add(Pair(resultSet.createVariable(tmpBind.name.name), tmpBind.children[1] as AOPBase))
            resultSet.createVariable(tmpBind.name.name)
            tmpBind = tmpBind.children[0]
        }
        this.bindings = this.bindings.asReversed()
        for (v in by)
            resultSet.createVariable(v.name)
    }

    override fun getProvidedVariableNames() = (MutableList(by.size) { by[it].name } + MutableList(bindings.size) { resultSet.getVariable(bindings[it].first) }).distinct()
    override fun getRequiredVariableNames(): List<String> {
        var res = MutableList(by.size) { by[it].name }
        for (b in bindings)
            res.addAll(b.second.getRequiredVariableNamesRecoursive())
        return res.distinct()
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
                throw Exception("$classname undefined Variable")
            }
        }
    }

    fun setAggregationMode(node: OPBase, mode: Boolean, count: Int) {
        for (n in node.children)
            setAggregationMode(n, mode, count)
        if (node is AOPAggregationBase) {
            node.count.set(count)
            node.collectMode.set(mode)
            if (mode)
                node.a.set(null)
        }
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPGroup.evaluate" }, {
        val children0Channel = children[0].evaluate()
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                val tmpMutableMap = mutableMapOf<String, MutableList<ResultRow>>()
                val variables = mutableListOf<Pair<Variable, Variable>>()
                for (v in by)
                    variables.add(Pair(resultSet.createVariable(v.name), children[0].resultSet.createVariable(v.name)))
                for (rsOld in children0Channel) {
                    resultFlowConsume({ this@POPGroup }, { children[0] }, { rsOld })
                    var key = "|"
                    for (variable in variables)
                        key = key + children[0].resultSet.getValue(rsOld[variable.second]) + "|"
                    var tmp = tmpMutableMap[key]
                    if (tmp == null) {
                        tmp = mutableListOf()
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
                            setAggregationMode(b.second, true, tmpMutableMap[k]!!.count())
                            for (resultRow in tmpMutableMap[k]!!)
                                (b.second as AOPBase).calculate(children[0].resultSet, resultRow)
                            setAggregationMode(b.second, false, tmpMutableMap[k]!!.count())
                            val a = (b.second as AOPBase).calculate(children[0].resultSet, children[0].resultSet.createResultRow())
                            val value = a.valueToString()
                            if (value == null)
                                resultSet.setUndefValue(rsNew, b.first)
                            else
                                rsNew[b.first] = resultSet.createValue(value)
                        } catch (e: Throwable) {
                            e.printStackTrace()
                            resultSet.setUndefValue(rsNew, b.first)
                            GlobalLogger.log(ELoggerType.DEBUG, { "silent :: " })
                            GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
                        }
                    }
                    channel.send(resultFlowProduce({ this@POPGroup }, { rsNew }))
                }
                channel.close()
                children0Channel.close()
            } catch (e: Throwable) {
                e.printStackTrace()
                channel.close(e)
                children0Channel.close(e)
            }
        }
        return channel
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPGroup")
        res.addAttribute("uuid", "" + uuid)
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
