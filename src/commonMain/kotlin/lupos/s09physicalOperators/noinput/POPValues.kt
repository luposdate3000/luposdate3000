package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlowProduce
import lupos.s00misc.SanityCheck
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s09physicalOperators.POPBase

open class POPValues : POPBase {
    @JvmField
    val variables = mutableListOf<Variable>()
    @JvmField
    val data = mutableListOf<ResultRow>()

    override fun toSparql(): String {
        var res = "VALUES("
        for (v in variables)
            res += AOPVariable(query, resultSet.getVariable(v)).toSparql() + " "
        res += ") {"
        for (m in data) {
            res += "("
            for (v in variables) {
                val s = resultSet.getValue(m, v)
                if (s == query.dictionary.undefValue)
                    res += "UNDEF "
                else
                    res += resultSet.getValueObject(s).valueToString() + " "
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
            if (m1 != m2)
                return false
        }
        return true
    }

    override fun cloneOP() = POPValues(query, variables.map { resultSet.getVariable(it) }, data.map {
        val res = mutableListOf<String?>()
        for (v in variables.indices)
            res.add(resultSet.getValueObject(it, variables[v]).valueToString())
        res
    }.toMutableList())

    constructor(query: Query, v: List<String>, d: MutableList<List<String?>>) : super(query, EOperatorID.POPValuesID, "POPValues", ResultSet(query.dictionary), arrayOf()) {
        v.forEach {
            variables.add(resultSet.createVariable(it))
        }
        d.forEach {
            val entry = resultSet.createResultRow()
            for (v1 in it.indices)
                resultSet.setValue(entry, variables[v1], it[v1])
            data.add(entry)
        }
    }

    constructor(query: Query, values: LOPValues) : super(query, EOperatorID.POPValuesID, "POPValues", ResultSet(query.dictionary), arrayOf()) {
        for (name in values.variables)
            variables.add(resultSet.createVariable(name.name))
        for (v in values.children) {
            SanityCheck.check({ v is AOPValue })
            val it = v.children.iterator()
            val entry = resultSet.createResultRow()
            for (v2 in variables)
                resultSet.setValue(entry, v2, (it.next() as AOPConstant).value.valueToString())
            data.add(entry)
        }
    }

    override fun getProvidedVariableNames() = variables.map { resultSet.getVariable(it) }.distinct()
    override fun getRequiredVariableNames() = mutableListOf<String>()
    override fun evaluate() = Trace.trace<ResultIterator>({ "POPValues.evaluate" }, {
        //row based
        val iterator = data.iterator()
        val res = ResultIterator()
        res.next = {
            Trace.traceSuspend<ResultChunk>({ "POPValues.next" }, {
                val outbuffer = ResultChunk(resultSet)
                while (outbuffer.canAppend() && iterator.hasNext()) {
                    outbuffer.append(iterator.next())
                }
                if (!iterator.hasNext())
                    res.close()
                resultFlowProduce({ this@POPValues }, { outbuffer })
            })
        }
        return res
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
            for (v in variables) {
                val value = resultSet.getValueObject(d, v).valueToString()
                if (value != null)
                    b.addContent(XMLElement("value").addAttribute("name", resultSet.getVariable(v)).addAttribute("content", value))
                else
                    b.addContent(XMLElement("value").addAttribute("name", resultSet.getVariable(v)))
            }
        }
        return res
    }
}
