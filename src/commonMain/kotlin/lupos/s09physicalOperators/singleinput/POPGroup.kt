package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.ELoggerType
import lupos.s00misc.EOperatorID
import lupos.s00misc.GlobalLogger
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.SanityCheck
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPBind


class POPGroup : POPBase {
    @JvmField
    var by: List<AOPVariable>
    @JvmField
    var bindings = mutableListOf<Pair<Variable, AOPBase>>()

    override fun toSparql(): String {
        var res = children[0].toSparql()
        res += " GROUP BY "
        for (b in by)
            res += b.toSparql() + " "
        for ((k, v) in bindings)
            res += "(" + v.toSparql() + " AS " + AOPVariable(query, resultSet.getVariable(k)).toSparql() + ")"
        return res
    }

    override fun cloneOP(): POPGroup {
        if (bindings.size > 0) {
            var tmpBindings = POPBind(query, AOPVariable(query, resultSet.getVariable(bindings[0].first)), bindings[0].second, OPNothing(query))
            for (bp in 1 until bindings.size)
                tmpBindings = POPBind(query, AOPVariable(query, resultSet.getVariable(bindings[0].first)), bindings[0].second, tmpBindings)
            return POPGroup(query, by, tmpBindings, children[0].cloneOP())
        } else
            return POPGroup(query, by, null, children[0].cloneOP())
    }

    constructor(query: Query, by: List<AOPVariable>, bindings: POPBind?, child: OPBase) : super(query, EOperatorID.POPGroupID, "POPGroup", ResultSet(query.dictionary), arrayOf(child)) {
        this.by = by
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

    override fun equals(other: Any?): Boolean = other is POPGroup && by.equals(other.by) && bindings.equals(other.bindings) && children[0] == other.children[0]
    override fun getProvidedVariableNames() = (MutableList(by.size) { by[it].name } + MutableList(bindings.size) { resultSet.getVariable(bindings[it].first) }).distinct()
    override fun getRequiredVariableNames(): List<String> {
        var res = MutableList(by.size) { by[it].name }
        for (b in bindings)
            res.addAll(b.second.getRequiredVariableNamesRecoursive())
        return res.distinct()
    }

    override fun syntaxVerifyAllVariableExists(additionalProvided: List<String>, autocorrect: Boolean) {
        SanityCheck.check({ additionalProvided.isEmpty() })
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
            if (autocorrect)
                syntaxVerifyAllVariableExistsAutocorrect()
            else
                throw Exception("$classname undefined Variable")
        }
    }

    fun getAggregations(node: OPBase, count: Int): MutableList<AOPAggregationBase> {
        var res = mutableListOf<AOPAggregationBase>()
        for (n in node.children)
            res.addAll(getAggregations(n, count))
        if (node is AOPAggregationBase) {
            node.count.set(count)
            node.a.set(ValueUndef())
            res.add(node)
        }
        return res
    }

    override fun evaluate() = Trace.trace<ResultIterator>({ "POPGroup.evaluate" }, {
        val child = children[0].evaluate()
        val channel = Channel<ResultChunk>(CoroutinesHelper.channelType)
        val variables = Array(by.size) { Pair(resultSet.createVariable(by[it].name), children[0].resultSet.createVariable(by[it].name)) }
        val res = ResultIterator()
        CoroutinesHelper.run {
            Trace.trace({ "POPGroup.next" }, {
                var outbuf = ResultChunk(resultSet)
                try {
                    val tmpMutableMap = mutableMapOf<String, MutableList<ResultRow>>()
                    child.forEach { oldRows ->
                        for (oldRow in resultFlowConsume({ this@POPGroup }, { children[0] }, { oldRows })) {
                            var key = "|"
                            for (variable in variables)
                                key = key + children[0].resultSet.getValue(oldRow, variable.second) + "|"
                            var tmp = tmpMutableMap[key]
                            if (tmp == null) {
                                tmp = mutableListOf()
                                tmpMutableMap[key] = tmp
                            }
                            tmp.add(oldRow)
                        }
                    }
                    if (tmpMutableMap.keys.size == 0) {
                        outbuf.append(resultSet.createResultRow())
                    } else {
                        for (k in tmpMutableMap.keys) {
                            val oldRow = tmpMutableMap[k]!!.first()
                            val row = resultSet.createResultRow()
                            for (variable in variables)
                                resultSet.copy(row, variable.first, oldRow, variable.second, children[0].resultSet)
                            for (b in bindings) {
                                try {
                                    val aggregations = getAggregations(b.second, tmpMutableMap[k]!!.count())
                                    for (a in aggregations)
                                        for (resultRow in tmpMutableMap[k]!!)
                                            a.calculate(children[0].resultSet, resultRow)
                                    val tmpbuf = ResultChunk(resultSet)
                                    tmpbuf.size = 1
                                    val a = (b.second as AOPBase).calculate(children[0].resultSet, tmpbuf)
                                    resultSet.setValue(row, b.first, a.data[0])
                                } catch (e: Throwable) {
                                    e.printStackTrace()
                                    GlobalLogger.log(ELoggerType.DEBUG, { "silent :: " })
                                    GlobalLogger.stacktrace(ELoggerType.DEBUG, e)
                                }
                            }
                            if (!outbuf.canAppend()) {
                                channel.send(resultFlowProduce({ this@POPGroup }, { outbuf }))
                                outbuf = ResultChunk(resultSet)
                            }
                            outbuf.append(row)
                        }
                    }
                } finally {
                    channel.send(resultFlowProduce({ this@POPGroup }, { outbuf }))
                    channel.close()
                    child.close()
                    res._close()
                }
            })
        }
        res.next = {
            channel.receive()
        }
        res.close = {
            channel.close()
            child.close()
            res._close()
        }
        return res
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
        for (b in bindings)
            xmlbindings.addContent(XMLElement("binding").addAttribute("name", resultSet.getVariable(b.first)).addContent(b.second.toXMLElement()))
        res.addContent(childrenToXML())
        return res
    }
}
