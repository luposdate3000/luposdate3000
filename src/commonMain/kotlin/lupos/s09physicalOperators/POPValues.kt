package lupos.s09physicalOperators
import lupos.s00misc.Trace
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.data.LOPExpression
import lupos.s04logicalOperators.data.LOPValues
import lupos.s04logicalOperators.data.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s06resultRepresentation.ResultRow
import lupos.s06resultRepresentation.ResultSet
import lupos.s06resultRepresentation.Value
import lupos.s06resultRepresentation.Variable
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.POPBaseNullableIterator
import lupos.s09physicalOperators.POPEmptyRow
import lupos.s09physicalOperators.POPExpression
import lupos.s09physicalOperators.POPGraphOperation
import lupos.s09physicalOperators.POPImportFromXml
import lupos.s09physicalOperators.POPInsertData



class POPValues : POPBase {
    private val resultSet = ResultSet()
    private val variables = mutableListOf<Variable>()
    private var iterator: Iterator<Map<Variable, Value>>
    private val rs = ResultSet()
    val stringVars = mutableListOf<String>()
    val data = mutableListOf<Map<Variable, Value>>()

    constructor(v: List<String>, d: List<Map<String, String>>) : super() {
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

    constructor(values: LOPValues) : super() {
        val rr = rs.createResultRow()
        for (name in values.variables) {
            stringVars.add(name.name)
            variables.add(resultSet.createVariable(name.name))
        }
        for (v in values.values) {
            val it = v.child.children.iterator()
            val entry = mutableMapOf<Variable, String>()
            data.add(entry)
            for (v2 in variables) {
                entry[v2] = resultSet.createValue(POPExpression(it.next()).evaluate(rs, rr))
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
            for ((k, v) in d)
                b.addContent(XMLElement("value").addAttribute("name", resultSet.getVariable(k)).addAttribute("content", resultSet.getValue(v)))
        }
        return res
    }
}
