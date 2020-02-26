package lupos.s09physicalOperators.multiinput
import lupos.s00misc.EOperatorID

import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.POPBase


class POPJoinHashMap : POPBase {
    override val operatorID=EOperatorID.POPJoinHashMapID
    override val classname = "POPJoinHashMap"
    override val dictionary: ResultSetDictionary
    override val resultSet: ResultSet
    override val children: Array<OPBase>
    val optional: Boolean
    val joinVariables = mutableListOf<String>()
    val map: Array<MutableMap<String, MutableList<ResultRow>>>
    val variables: Array<MutableList<Pair<Variable, Variable>>>
    val variablesJ: Array<MutableList<Pair<Variable, Variable>>>

    override fun equals(other: Any?): Boolean {
        if (other !is POPJoinHashMap)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (optional != other.optional)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }


    constructor(dictionary: ResultSetDictionary, childA: OPBase, childB: OPBase, optional: Boolean) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        map = arrayOf(mutableMapOf<String, MutableList<ResultRow>>(), mutableMapOf<String, MutableList<ResultRow>>())
        children = arrayOf(childA, childB)
        this.optional = optional
        require(children[0].resultSet.dictionary == dictionary || (!(this.children[0] is POPBase)))
        require(children[1].resultSet.dictionary == dictionary || (!(this.children[1] is POPBase)))
        val variablesAt = children[0].getProvidedVariableNames()
        val variablesBt = children[1].getProvidedVariableNames()
        val variablesA = MutableList(variablesAt.size) { variablesAt[it] }
        val variablesB = MutableList(variablesBt.size) { variablesBt[it] }
        variablesA.forEach {
            if (variablesB.contains(it))
                joinVariables.add(it)
        }
        val variablesA2 = variablesA.minus(joinVariables)
        val variablesB2 = variablesB.minus(joinVariables)
        variables = arrayOf(mutableListOf<Pair<Variable, Variable>>(), mutableListOf<Pair<Variable, Variable>>())
        variablesJ = arrayOf(mutableListOf<Pair<Variable, Variable>>(), mutableListOf<Pair<Variable, Variable>>())
        for (name in variablesA2)
            variables[0].add(Pair(children[0].resultSet.createVariable(name), resultSet.createVariable(name)))
        for (name in variablesB2)
            variables[1].add(Pair(children[1].resultSet.createVariable(name), resultSet.createVariable(name)))
        for (name in joinVariables) {
            variablesJ[0].add(Pair(children[0].resultSet.createVariable(name), resultSet.createVariable(name)))
            variablesJ[1].add(Pair(children[1].resultSet.createVariable(name), resultSet.createVariable(name)))
        }
    }

    suspend fun joinHelper(rowA: ResultRow, rowsB: List<ResultRow>, idx: Int) {
        for (rowB in rowsB) {
            val row = resultSet.createResultRow()
            for (p in variables[idx])
                row[p.second] = rowA[p.first]
            for (p in variables[1 - idx])
                row[p.second] = rowB[p.first]
            for (p in variablesJ[idx])
                row[p.second] = rowA[p.first]
            for (p in variablesJ[1 - idx]) {
                if (!children[1 - idx].resultSet.isUndefValue(rowB, p.first))
                    row[p.second] = rowB[p.first]
            }
            channel.send(resultFlowProduce({ this@POPJoinHashMap }, { row }))
        }
    }

    suspend fun joinHelper(idx: Int) {
        try {
            for (rowA in children[idx].channel) {
                resultFlowConsume({ this@POPJoinHashMap }, { children[idx] }, { rowA })
                var keys = mutableSetOf<String>()
                keys.add("")
                var exactkey = ""
                for (k in variablesJ[idx]) {
                    val v = children[idx].resultSet.getValue(rowA[k.first])
                    val kk = if (children[idx].resultSet.isUndefValue(rowA, k.first))
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
            }
        } catch (e: Throwable) {
            if (idx == 0 || !optional)
                throw e
        }
    }

    override fun evaluate() = Trace.trace<Unit>({ "POPJoinHashMap.evaluate" }, {
        for (c in children)
            c.evaluate()
        CoroutinesHelper.run {
            try {
                joinHelper(0)
                joinHelper(1)
                if (optional) {
                    for ((k, v) in map[0]) {
                        if (map[1][k] == null) {
                            for (rowA in v) {
                                val row = resultSet.createResultRow()
                                for (p in variables[1])
                                    resultSet.setUndefValue(row, p.second)
                                for (p in variables[0])
                                    row[p.second] = rowA[p.first]
                                for (p in variablesJ[0])
                                    row[p.second] = rowA[p.first]
                                channel.send(resultFlowProduce({ this@POPJoinHashMap }, { row }))
                            }
                        }
                    }
                }
                channel.close()
                for (c in children)
                    c.channel.close()
            } catch (e: Throwable) {
                channel.close(e)
                for (c in children)
                    c.channel.close(e)
            }
        }
    })

    override fun toXMLElement() = super.toXMLElement().addAttribute("optional", "" + optional)
}
