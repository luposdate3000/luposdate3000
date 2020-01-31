package lupos.s07physicalOperators.multiinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Variable
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.POPBaseNullableIterator
import lupos.s07physicalOperators.singleinput.POPTemporaryStore


class POPJoinHashMap : POPBaseNullableIterator {
    val child: Array<OPBase>
    val optional: Boolean
    val joinVariables: Set<String>
    val map: Array<MutableMap<String, MutableList<ResultRow>>>
    val queue = mutableListOf<ResultRow>()
    var hadOptionals = false
    private val resultSet: Array<ResultSet>
    private val variables: Array<MutableList<Pair<Variable, Variable>>>
    private val variablesJ: Array<MutableList<Pair<Variable, Variable>>>
    private val resultSetNew = ResultSet()

    override fun getProvidedVariableNames(): List<String> {
        return child[0].getProvidedVariableNames() + child[1].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return child[0].getRequiredVariableNames() + child[1].getRequiredVariableNames()
    }

    constructor(childA: OPBase, childB: OPBase, optional: Boolean) : super() {
        map = arrayOf(mutableMapOf<String, MutableList<ResultRow>>(), mutableMapOf<String, MutableList<ResultRow>>())
        child = arrayOf(childA, childB)
        this.optional = optional
        resultSet = arrayOf(childA.getResultSet(), childB.getResultSet())
        var variablesA = resultSet[0].getVariableNames()
        var variablesB = resultSet[1].getVariableNames()
        joinVariables = variablesA.intersect(variablesB)
        variablesA = variablesA.subtract(joinVariables)
        variablesB = variablesB.subtract(joinVariables)
        variables = arrayOf(mutableListOf<Pair<Variable, Variable>>(), mutableListOf<Pair<Variable, Variable>>())
        variablesJ = arrayOf(mutableListOf<Pair<Variable, Variable>>(), mutableListOf<Pair<Variable, Variable>>())
        for (name in variablesA)
            variables[0].add(Pair(resultSet[0].createVariable(name), resultSetNew.createVariable(name)))
        for (name in variablesB)
            variables[1].add(Pair(resultSet[1].createVariable(name), resultSetNew.createVariable(name)))
        for (name in joinVariables) {
            variablesJ[0].add(Pair(resultSet[0].createVariable(name), resultSetNew.createVariable(name)))
            variablesJ[1].add(Pair(resultSet[1].createVariable(name), resultSetNew.createVariable(name)))
        }
    }

    override fun getResultSet(): ResultSet {
        return resultSetNew
    }

    fun joinHelper(rowA: ResultRow, rowsB: List<ResultRow>, idx: Int) {
        for (rowB in rowsB) {
            val row = resultSetNew.createResultRow()
            for (p in variables[idx])
                row[p.second] = resultSetNew.createValue(resultSet[idx].getValue(rowA[p.first]))
            for (p in variables[1 - idx])
                row[p.second] = resultSetNew.createValue(resultSet[1 - idx].getValue(rowB[p.first]))
            for (p in variablesJ[idx])
                row[p.second] = resultSetNew.createValue(resultSet[idx].getValue(rowA[p.first]))
            for (p in variablesJ[1 - idx]) {
                val v = resultSet[1 - idx].getValue(rowB[p.first])
                if (v != resultSet[1 - idx].getUndefValue())
                    row[p.second] = resultSetNew.createValue(v)
            }
            queue.add(row)
        }
    }

    fun joinHelper(idx: Int) {
        val rowA = child[idx].next()
        var keys = mutableSetOf<String>()
        keys.add("")
        var exactkey = ""
        for (k in variablesJ[idx]) {
            val v = resultSet[idx].getValue(rowA[k.first])
            val kk = if (v == resultSet[idx].getUndefValue())
                "-"
            else
                v + "-"
            exactkey += kk
            val newkeys = mutableSetOf<String>()
            for (x in keys) {
                if (kk == "-") {
                    newkeys.add(x + "-")
                    for ((a, b) in map[1 - idx])
                        if (a.startsWith(x)) {
                            newkeys.add(a.substring(0, a.indexOf("-", x.length + 1) + 1))
                        }
                } else {
                    newkeys.add(x + kk)
                    newkeys.add(x + "-")
                }
            }
            keys = newkeys
        }
        var t = map[idx][exactkey]
        if (t == null)
            t = mutableListOf<ResultRow>()
        t.add(rowA)
        map[idx][exactkey] = t
        for (key in keys) {
            val rowsB = map[1 - idx][key]
            if (rowsB != null)
                joinHelper(rowA, rowsB, idx)
        }
    }

    override fun nnext(): ResultRow? {
        try {
            Trace.start("POPJoinHashMap.nnext")
            while (true) {
                if (!queue.isEmpty())
                    return queue.removeAt(0)
                else if (child[0].hasNext())
                    joinHelper(0)
                else if (child[1].hasNext())
                    joinHelper(1)
                else if (optional && !hadOptionals) {
                    for ((k, v) in map[0]) {
                        if (map[1][k] == null) {
                            for (rowA in v) {
                                val row = resultSetNew.createResultRow()
                                for (p in variables[1])
                                    row[p.second] = resultSetNew.createValue(resultSetNew.getUndefValue())
                                for (p in variables[0])
                                    row[p.second] = resultSetNew.createValue(resultSet[0].getValue(rowA[p.first]))
                                for (p in variablesJ[0])
                                    row[p.second] = resultSetNew.createValue(resultSet[0].getValue(rowA[p.first]))
                                queue.add(row)
                            }
                            map[1][k] = mutableListOf<ResultRow>()
                        }
                    }
                    hadOptionals = true
                } else
                    return null
            }
        } finally {
            Trace.stop("POPJoinHashMap.nnext")
        }
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPJoinHashMap")
        res.addAttribute("optional", "" + optional)
        res.addContent(XMLElement("childA").addContent(child[0].toXMLElement()))
        res.addContent(XMLElement("childB").addContent(child[1].toXMLElement()))
        return res
    }
}
