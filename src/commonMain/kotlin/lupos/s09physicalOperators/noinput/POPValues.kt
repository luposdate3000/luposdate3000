package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

open class POPValues : POPBase {
    @JvmField
    val variables: List<String>
    @JvmField
    val data: Map<String, MutableList<Value>>

    override fun toSparql(): String {
        require(variables.size > 0)
        var res = "VALUES("
        for (v in variables) {
            res += v + " "
        }
        res += ") {"
        var columns = Array(variables.size) { data[variables[it]] }
        for (i in 0 until columns[0]!!.size) {
            res += "("
            for (v in 0 until variables.size) {
                if (columns[v]!![i] == ResultSetDictionary.undefValue) {
                    res += "UNDEF "
                } else {
                    res += query.dictionary.getValue(columns[v]!![i]).valueToString() + " "
                }
            }
            res += ")"
        }
        res += "}"
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPValues) {
            return false
        }
        require(variables.size > 0)
        if (variables.size != other.variables.size) {
            return false
        }
        for (v in variables) {
            if (!other.variables.contains(v)) {
                return false
            }
            if (data[v]!!.size != other.data[v]!!.size) {
                return false
            }
            var columns1 = Array(variables.size) { data[variables[it]] }
            var columns2 = Array(variables.size) { other.data[variables[it]] }
            for (vIndex in 0 until variables.size) {
                for (i in 0 until columns1[0]!!.size) {
                    if (columns1[vIndex]!![i] != columns2[vIndex]!![i]) {
                        return false
                    }
                }
            }
        }
        return true
    }

    override fun cloneOP() = POPValues(query, variables, data)

    constructor(query: Query, v: List<String>, d: MutableList<List<String?>>) : super(query, EOperatorID.POPValuesID, "POPValues", arrayOf()) {
        variables = v
        require(variables.size > 0)
        var columns = Array(variables.size) { mutableListOf<Value>() }
        data = mutableMapOf<String, MutableList<Value>>()
        for (variableIndex in 0 until variables.size) {
            data[variables[variableIndex]] = columns[variableIndex]
        }
        d.forEach {
            for (variableIndex in 0 until variables.size) {
                columns[variableIndex].add(query.dictionary.createValue(it[variableIndex]))
            }
        }
    }

    constructor(query: Query, v: List<String>, d: Map<String, MutableList<Value>>) : super(query, EOperatorID.POPValuesID, "POPValues", arrayOf()) {
        variables = v
        require(variables.size > 0)
        data = d
    }

    constructor(query: Query, values: LOPValues) : super(query, EOperatorID.POPValuesID, "POPValues", arrayOf()) {
        val tmpVariables = mutableListOf<String>()
        for (name in values.variables) {
            tmpVariables.add(name.name)
        }
        variables = tmpVariables
        require(variables.size > 0)
        var columns = Array(variables.size) { mutableListOf<Value>() }
        data = mutableMapOf<String, MutableList<Value>>()
        for (variableIndex in 0 until variables.size) {
            data[variables[variableIndex]] = columns[variableIndex]
        }
        for (v in values.children) {
            SanityCheck.check({ v is AOPValue })
            val it = v.children.iterator()
            for (variableIndex in 0 until variables.size) {
                columns[variableIndex].add(query.dictionary.createValue((it.next() as AOPConstant).value))
            }
        }
    }

    override fun getProvidedVariableNames() = variables.distinct()
    override fun getRequiredVariableNames() = mutableListOf<String>()
    override suspend fun evaluate(): ColumnIteratorRow {
        val outMap = mutableMapOf<String, ColumnIteratorMultiValue>()
        for (name in variables) {
            val tmp = ColumnIteratorMultiValue(data[name]!!)
            tmp.close = {
                tmp._close()
                for (variable in variables) {
                    outMap[variable]!!.close()
                }
            }
            outMap[name] = tmp
        }
        return ColumnIteratorRow(outMap)
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPValues")
        val xmlvariables = XMLElement("variables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("bindings")
        res.addContent(bindings)
        for (variable in variables) {
            xmlvariables.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        var columns = Array(variables.size) { data[variables[it]] }
        for (i in 0 until columns[0]!!.size) {
            val b = XMLElement("binding")
            bindings.addContent(b)
            for (variableIndex in 0 until variables.size) {
                val value = query.dictionary.getValue(columns[variableIndex]!![i]).valueToString()
                if (value != null) {
                    b.addContent(XMLElement("value").addAttribute("name", variables[variableIndex]).addAttribute("content", value))
                } else {
                    b.addContent(XMLElement("value").addAttribute("name", variables[variableIndex]))
                }
            }
        }
        return res
    }
}
