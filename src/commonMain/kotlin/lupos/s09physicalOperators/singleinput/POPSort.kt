package lupos.s09physicalOperators.singleinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPModify
import lupos.s09physicalOperators.singleinput.POPProjection


class POPSort : POPBaseNullableIterator {
    override val children: Array<OPBase> = arrayOf(OPNothing())
    private var data: MutableList<ResultRow>? = null
    private val resultSetOld: ResultSet
    private val resultSetNew = ResultSet()
    private val variables = mutableListOf<Pair<Variable, Variable>>()
    private var iterator: Iterator<ResultRow>? = null
    var sortBy: Variable
    val sortOrder: Boolean

    constructor(sortBy: LOPVariable, sortOrder: Boolean, child: OPBase) : super() {
        children[0] = child
        this.sortOrder = sortOrder
        resultSetOld = children[0].getResultSet()
        for (name in resultSetOld.getVariableNames()) {
            variables.add(Pair(resultSetNew.createVariable(name), resultSetOld.createVariable(name)))
        }
        this.sortBy = resultSetNew.createVariable(sortBy.name)
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    override fun nnext(): ResultRow? {
        try {
            Trace.start("POPSort.nnext")
            if (data == null) {
                val tmpMutableMap = mutableMapOf<String, MutableList<ResultRow>>()
                while (children[0].hasNext()) {
                    val rsOld = children[0].next()
                    val rsNew = resultSetNew.createResultRow()
                    var key: String = ""
                    for (variable in variables) {
                        val tmp = resultSetOld.getValue(rsOld[variable.second])
                        rsNew[variable.first] = resultSetNew.createValue(tmp)
                        if (variable.first == sortBy)
                            key = tmp
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
        } finally {
            Trace.stop("POPSort.nnext")
        }
    }

    fun reset() {
        iterator = data!!.listIterator()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPSort")
        res.addAttribute("by", resultSetNew.getVariable(sortBy))
        if (sortOrder)
            res.addAttribute("order", "ASC")
        else
            res.addAttribute("order", "DESC")
        res.addContent(childrenToXML())
        return res
    }
}
