package lupos.s09physicalOperators.noinput

import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.POPBase


class POPValues : POPBase {
    override val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
    private val resultSet: ResultSet
    private val variables = mutableListOf<Variable>()
    private var iterator: Iterator<Map<Variable, Value>>
    private val rs: ResultSet
    val stringVars = mutableListOf<String>()
    val data = mutableListOf<Map<Variable, Value>>()

    constructor(dictionary: ResultSetDictionary, v: List<String>, d: List<Map<String, String>>) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        rs = ResultSet(dictionary)
        v.forEach {
            stringVars.add(it)
            variables.add(resultSet.createVariable(it))
        }
        d.forEach {
            val entry = mutableMapOf<Variable, Value>()
            data.add(entry)
            for ((k, v) in it) {
                entry[resultSet.createVariable(k)] = resultSet.createValue(v)
            }
        }
        iterator = data.iterator()
    }

    constructor(dictionary: ResultSetDictionary, values: LOPValues) : super() {
        this.dictionary = dictionary
        resultSet = ResultSet(dictionary)
        rs = ResultSet(dictionary)
        val rr = rs.createResultRow()
        for (name in values.variables) {
            stringVars.add(name.name)
            variables.add(resultSet.createVariable(name.name))
        }
        for (v in values.values) {
            val it = v.child.children.iterator()
            val entry = mutableMapOf<Variable, Value>()
            data.add(entry)
            for (v2 in variables) {
                val value = POPExpression(dictionary, it.next()).evaluate(rs, rr)
                entry[v2] = resultSet.createValue(value)
            }
        }
        iterator = data.iterator()
    }

    override fun getProvidedVariableNames(): List<String> {
        return stringVars
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getResultSet(): ResultSet {
        return resultSet
    }

    override fun hasNext(): Boolean {
        try {
            Trace.start("POPValues.hasNext")
            return iterator.hasNext()
        } finally {
            Trace.stop("POPValues.hasNext")
        }
    }

    override fun next(): ResultRow {
        try {
            Trace.start("POPValues.next")
            val rsOld = iterator.next()
            var rsNew = resultSet.createResultRow()
            for ((variable, value) in rsOld) {
                rsNew[variable] = value
            }
            return rsNew
        } finally {
            Trace.stop("POPValues.next")
        }
    }

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
                val value = resultSet.getValue(v)
                if (value != null)
                    b.addContent(XMLElement("value").addAttribute("name", resultSet.getVariable(k)).addAttribute("content", value))
            }
        }
        return res
    }
}
