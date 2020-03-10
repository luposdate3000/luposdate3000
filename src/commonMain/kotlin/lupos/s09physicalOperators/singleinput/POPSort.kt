package lupos.s09physicalOperators.singleinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowConsume
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.SanityCheck
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase


class POPSort : POPBase {
    @JvmField
    var data: MutableList<ResultRow>? = null
    @JvmField
    val variables = mutableListOf<Pair<Variable, Variable>>()
    @JvmField
    var iterator: Iterator<ResultRow>? = null
    @JvmField
    var sortBy: Variable
    @JvmField
    val sortOrder: Boolean

    constructor(query: Query, sortBy: AOPVariable, sortOrder: Boolean, child: OPBase) : super(query, EOperatorID.POPSortID, "POPSort", ResultSet(query.dictionary), arrayOf(child)) {
        this.sortOrder = sortOrder
        for (name in children[0].getProvidedVariableNames())
            variables.add(Pair(resultSet.createVariable(name), children[0].resultSet.createVariable(name)))
        this.sortBy = resultSet.createVariable(sortBy.name)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPSort)
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
        SanityCheck.check({ child !is POPSort })
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
            res += AOPVariable(query, v).toSparql() + " "
        res += ")"
        res += "}"
        return res
    }


    override fun cloneOP() = POPSort(query, AOPVariable(query, resultSet.getVariable(sortBy)), sortOrder, children[0].cloneOP())

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
                        resultSet.copy(rsNew, variable.first, rsOld, variable.second, children[0].resultSet)
                        if (variable.first == sortBy)
                            key = "" + children[0].resultSet.getValue(rsOld, variable.second)
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
