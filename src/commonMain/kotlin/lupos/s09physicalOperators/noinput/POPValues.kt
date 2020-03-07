package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase


class POPValues : POPBase {
    @JvmField
    val variables = mutableListOf<Variable>()
    @JvmField
    val stringVars = mutableListOf<String>()
    @JvmField
    val data = mutableListOf<Map<Variable, Value>>()

    override fun toSparql(): String {
        var res = "VALUES("
        for (v in stringVars)
            res += AOPVariable(query, v).toSparql() + " "
        res += ") {"
        for (m in data) {
            res += "("
            for (v in variables) {
                val s = m[v]
                if (s == null || s == query.dictionary.undefValue)
                    res += "UNDEF "
                else
                    res += resultSet.getValueString(s) + " "
            }
            res += ")"
        }
        res += "}"
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPValues)
            return false
        if (data.size != other.data.size)
            return false
        for (i in data.indices) {
            val m1 = data[i]
            val m2 = other.data[i]
            if (m1.keys.size != m2.keys.size)
                return false
            for (k in m1.keys) {
                if (m1[k] != m2[k])
                    return false
            }
        }
        return true
    }

    override fun cloneOP() = POPValues(query, variables.map { resultSet.getVariable(it) }, data.map {
        val res = mutableMapOf<String, String?>()
        for ((k, v) in (it as Map<Variable, Value>))
            res[resultSet.getVariable(k as Variable) as String] = resultSet.getValueString(v as Value) as String?
        res
    })

    constructor(query: Query, v: List<String>, d: List<Map<String, String?>>) : super(query, EOperatorID.POPValuesID, "POPValues", ResultSet(query.dictionary), arrayOf()) {
        v.forEach {
            stringVars.add(it)
            variables.add(resultSet.createVariable(it))
        }
        d.forEach {
            val entry = mutableMapOf<Variable, Value>()
            data.add(entry)
            for ((k, v) in it) {
                if (v == null)
                    entry[resultSet.createVariable(k)] = resultSet.dictionary.undefValue
                else
                    entry[resultSet.createVariable(k)] = resultSet.createValue(v)
            }
        }
    }

    constructor(query: Query, values: LOPValues) : super(query, EOperatorID.POPValuesID, "POPValues", ResultSet(query.dictionary), arrayOf()) {
        for (name in values.variables) {
            stringVars.add(name.name)
            variables.add(resultSet.createVariable(name.name))
        }
        for (v in values.children) {
            require(v is AOPValue)
            val it = v.children.iterator()
            val entry = mutableMapOf<Variable, Value>()
            data.add(entry)
            for (v2 in variables) {
                entry[v2] = resultSet.createValue((it.next() as AOPConstant).valueToString())
            }
        }
    }

    override fun getProvidedVariableNames() = stringVars.distinct()

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf()
    }

    override fun evaluate() = Trace.trace<Channel<ResultRow>>({ "POPValues.evaluate" }, {
        val channel = Channel<ResultRow>(CoroutinesHelper.channelType)
        CoroutinesHelper.run {
            try {
                for (rsOld in data) {
                    var rsNew = resultSet.createResultRow()
                    for ((variable, value) in rsOld)
                        resultSet.setValue(rsNew, variable, value)
                    channel.send(resultFlowProduce({ this@POPValues }, { rsNew }))
                }
                channel.close()
            } catch (e: Throwable) {
                channel.close(e)
            }
        }
        return channel
    })

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPValues")
        val xmlvariables = XMLElement("variables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("bindings")
        res.addContent(bindings)
        for (v in variables)
            xmlvariables.addContent(XMLElement("variable").addAttribute("name", resultSet.getVariable(v)))
        for (d in data) {
            val b = XMLElement("binding")
            bindings.addContent(b)
            for ((k, v) in d) {
                val value = resultSet.getValueString(v)
                if (value != null)
                    b.addContent(XMLElement("value").addAttribute("name", resultSet.getVariable(k)).addAttribute("content", value))
            }
        }
        return res
    }
}
