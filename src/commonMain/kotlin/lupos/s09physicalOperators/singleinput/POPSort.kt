package lupos.s09physicalOperators.singleinput

import lupos.s00misc.CoroutinesHelper
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
import lupos.s09physicalOperators.POPBase


class POPSort : POPBase {
    override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private var data: MutableList<ResultRow>? = null
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>? = null
    var sortBy: Variable
    val sortOrder: Boolean
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

    constructor(dictionary: ResultSetDictionary, sortBy: AOPVariable, sortOrder: Boolean, child: OPBase) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        children[0] = child
        this.sortOrder = sortOrder
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        for (name in children[0].resultSet.getVariableNames()) {
            variables.add(Pair(resultSet.createVariable(name), children[0].resultSet.createVariable(name)))
        }
        this.sortBy = resultSet.createVariable(sortBy.name)
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getProvidedVariableNames()
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPSort.evaluate" }, {
        children[0].evaluate()
        CoroutinesHelper.run {
            try {
                val tmpMutableMap = mutableMapOf<String, MutableList<ResultRow>>()
                for (rsOld in children[0].channel) {
                    resultFlowConsume({ this@POPSort }, { children[0] }, { rsOld })
                    val rsNew = resultSet.createResultRow()
                    var key: String = ""
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
                        tmp = mutableListOf<ResultRow>()
                        tmpMutableMap[key] = tmp
                    }
                    tmp.add(rsNew)
                }
                val allKeys = Array<String>(tmpMutableMap.keys.size) { "" }
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
                children[0].channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                children[0].channel.close(e)
            }
        }
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPSort")
        res.addAttribute("by", resultSet.getVariable(sortBy))
        if (sortOrder)
            res.addAttribute("order", "ASC")
        else
            res.addAttribute("order", "DESC")
        res.addContent(childrenToXML())
        return res
    }
}
