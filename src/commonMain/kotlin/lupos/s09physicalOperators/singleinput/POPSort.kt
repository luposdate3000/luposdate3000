package lupos.s09physicalOperators.singleinput

import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
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
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPSort : POPBase {
    override val operatorID = EOperatorID.POPSortID
    override val classname = "POPSort"
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    var data: MutableList<ResultRow>? = null
    @JvmField val variables = mutableListOf<Pair<Variable, Variable>>()
    var iterator: Iterator<ResultRow>? = null
    var sortBy: Variable
    @JvmField val sortOrder: Boolean
    override fun equals(other: Any?): Boolean {
        if (other !is POPSort)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (sortBy != other.sortBy)
            return false
        if (sortOrder != other.sortOrder)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }

    fun getRecoursiveSortVariables(): List<String> {
        val c = children[0]
        if (c is POPSort)
            return c.getRecoursiveSortVariables() + resultSet.getVariable(sortBy)
        return listOf(resultSet.getVariable(sortBy))
    }

    override fun toSparql(): String {
        val variables = getRecoursiveSortVariables()
        var child: OPBase = this
        for (i in 0 until variables.size)
            child = child.children[0]
        require(child !is POPSort)
        val sparql = child.toSparql()
        var res = ""
        if (sparql.startsWith("{SELECT "))
            res = sparql.substring(0, sparql.length - 1)
        else
            res = "{SELECT *{" + sparql + "}"
        res += " ORDER BY "
        if (sortOrder)
            res += "ASC("
        else
            res += "DESC("
        for (v in variables)
            res += AOPVariable(v).toSparql() + " "
        res += ")"
        res += "}"
        return res
    }


    override fun cloneOP() = POPSort(dictionary, AOPVariable(resultSet.getVariable(sortBy)), sortOrder, children[0].cloneOP())

    constructor(dictionary: ResultSetDictionary, sortBy: AOPVariable, sortOrder: Boolean, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        this.sortOrder = sortOrder
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        for (name in children[0].getProvidedVariableNames())
            variables.add(Pair(resultSet.createVariable(name), children[0].resultSet.createVariable(name)))
        this.sortBy = resultSet.createVariable(sortBy.name)
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPSort.evaluate" }, {
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        val children0Channel = children[0].evaluate()
        CoroutinesHelper.run {
            try {
                val tmpMutableMap = mutableMapOf<String, MutableList<ResultRow>>()
                for (rsOld in children0Channel) {
                    resultFlowConsume({ this@POPSort }, { children[0] }, { rsOld })
                    val rsNew = resultSet.createResultRow()
                    var key = ""
                    for (variable in variables) {
                        rsNew[variable.first] = rsOld[variable.second]
                        if (variable.first == sortBy) {
                            val tmp = children[0].resultSet.getValue(rsOld[variable.second])
                            if (tmp == null)
                                key = ""
                            else
                                key = tmp
                        }
                    }
                    var tmp = tmpMutableMap[key]
                    if (tmp == null) {
                        tmp = mutableListOf()
                        tmpMutableMap[key] = tmp
                    }
                    tmp.add(rsNew)
                }
                val allKeys = Array(tmpMutableMap.keys.size) { "" }
                var i = 0
                for (k in tmpMutableMap.keys) {
                    allKeys[i] = k
                    i++
                }
                allKeys.sort()
                for (k in allKeys)
                    for (c in tmpMutableMap[k]!!)
                        channel.send(resultFlowProduce({ this@POPSort }, { c }))
                channel.close()
                children0Channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children0Channel.close(e)
            }
        }
        return channel
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPSort")
        res.addAttribute("uuid", "" + uuid)
        res.addAttribute("by", resultSet.getVariable(sortBy))
        if (sortOrder)
            res.addAttribute("order", "ASC")
        else
            res.addAttribute("order", "DESC")
        res.addContent(childrenToXML())
        return res
    }
}
