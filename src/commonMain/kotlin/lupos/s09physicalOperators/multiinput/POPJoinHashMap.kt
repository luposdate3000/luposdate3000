package lupos.s09physicalOperators.multiinput
import lupos.s04logicalOperators.OPBase
import lupos.s03resultRepresentation.ResultSetDictionary

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Variable
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator


class POPJoinHashMap : POPBaseNullableIterator {
    override val children: Array<OPBase>
    val optional: Boolean
    val joinVariables: Set<String>
    val map: Array<MutableMap<String, MutableList<ResultRow>>>
    val queue = mutableListOf<ResultRow>()
    var hadOptionals = false
    val resultSet: Array<ResultSet>
    val variables: Array<MutableList<Pair<Variable, Variable>>>
    val variablesJ: Array<MutableList<Pair<Variable, Variable>>>
    val resultSetNew: ResultSet
    override val dictionary: ResultSetDictionary
    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames() + children[1].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getProvidedVariableNames()
    }

    constructor(dictionary: ResultSetDictionary, childA: OPBase, childB: OPBase, optional: Boolean) : super() {
        this.dictionary = dictionary
        resultSetNew = ResultSet(dictionary)
        map = arrayOf(mutableMapOf<String, MutableList<ResultRow>>(), mutableMapOf<String, MutableList<ResultRow>>())
        children = arrayOf(childA, childB)
        this.optional = optional
        resultSet = arrayOf(childA.getResultSet(), childB.getResultSet())
        require(resultSet[0].dictionary == dictionary || (!(this.children[0] is POPBase)))
        require(resultSet[1].dictionary == dictionary || (!(this.children[1] is POPBase)))

        var variablesA: Set<String> = mutableSetOf<String>()
        var variablesB: Set<String> = mutableSetOf<String>()
        for (s in childA.getProvidedVariableNames())
            (variablesA as MutableSet).add(s)
        for (s in childB.getProvidedVariableNames())
            (variablesB as MutableSet).add(s)
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
                row[p.second] = rowA[p.first]
            for (p in variables[1 - idx])
                row[p.second] = rowB[p.first]
            for (p in variablesJ[idx])
                row[p.second] = rowA[p.first]
            for (p in variablesJ[1 - idx]) {
                if (!resultSet[1 - idx].isUndefValue(rowB, p.first))
                    row[p.second] = rowB[p.first]
            }
            queue.add(row)
        }
    }

    inline fun joinHelper(idx: Int) {
        try {
            val rowA = children[idx].next()
            var keys = mutableSetOf<String>()
            keys.add("")
            var exactkey = ""
            for (k in variablesJ[idx]) {
                val v = resultSet[idx].getValue(rowA[k.first])
                val kk = if (resultSet[idx].isUndefValue(rowA, k.first))
                    "-"
                else
                    v + "-"
                exactkey += kk
                val newkeys = mutableSetOf<String>()
                for (x in keys) {
                    if (kk == "-") {
                        newkeys.add(x + "-")
                        for (a in map[1 - idx].keys)
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
                if (map[idx][key] == null)
                    map[idx][key] = mutableListOf<ResultRow>()
                val rowsB = map[1 - idx][key]
                if (rowsB != null)
                    joinHelper(rowA, rowsB, idx)
            }
        } catch (e: Throwable) {
            if (idx == 0 || !optional)
                throw e
        }
    }

    override fun nnext(): ResultRow? {
        try {
            Trace.start("POPJoinHashMap.nnext")
            while (true) {
                if (!queue.isEmpty())
                    return queue.removeAt(0)
                else if (children[0].hasNext())
                    joinHelper(0)
                else if (children[1].hasNext())
                    joinHelper(1)
                else if (optional && !hadOptionals) {
                    for ((k, v) in map[0]) {
                        if (map[1][k] == null) {
                            for (rowA in v) {
                                val row = resultSetNew.createResultRow()
                                for (p in variables[1])
                                    resultSetNew.setUndefValue(row, p.second)
                                for (p in variables[0])
                                    row[p.second] = rowA[p.first]
                                for (p in variablesJ[0])
                                    row[p.second] = rowA[p.first]
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
        res.addContent(childrenToXML())
        return res
    }
}
