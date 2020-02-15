package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator


class POPSort : POPBaseNullableIterator {
override val resultSet: ResultSet
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private var data: MutableList<ResultRow>? = null
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>? = null
    var sortBy: Variable
    val sortOrder: Boolean

    constructor(dictionary: ResultSetDictionary, sortBy: LOPVariable, sortOrder: Boolean, child: OPBase) : super() {
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

    override fun nnext(): ResultRow? = Trace.trace({ "POPSort.nnext" }, {
        if (data == null) {
            val tmpMutableMap = mutableMapOf<String, MutableList<ResultRow>>()
            while (children[0].hasNext()) {
                val rsOld = children[0].next()
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
            data = mutableListOf<ResultRow>()
            val allKeys = Array<String>(tmpMutableMap.keys.size) { "" }
            var i = 0
            for (k in tmpMutableMap.keys) {
                allKeys[i] = k
                i++
            }
            allKeys.sort()
            for (k in allKeys) {
                data!!.addAll(tmpMutableMap[k]!!)
            }
            reset()
        }
        if (iterator == null || !iterator!!.hasNext())
            return null
        return iterator!!.next()
    }) as ResultRow?

    fun reset() {
        iterator = data!!.listIterator()
    }

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
