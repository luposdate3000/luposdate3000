package lupos.s09physicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorDebug
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

open class POPValues : POPBase {
    @JvmField
    val variables: List<String>
    @JvmField
    val data: Map<String, MyListValue>

    override fun toSparql(): String {
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

    override fun cloneOP() = POPValues(query, projectedVariables, variables, data)

    constructor(query: Query, projectedVariables: List<String>, v: List<String>, d: MutableList<List<String?>>) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        variables = v
        var columns = Array(variables.size) { MyListValue() }
        data = mutableMapOf<String, MyListValue>()
        for (variableIndex in 0 until variables.size) {
            data[variables[variableIndex]] = columns[variableIndex]
        }
        d.forEach {
            for (variableIndex in 0 until variables.size) {
                columns[variableIndex].add(query.dictionary.createValue(it[variableIndex]))
            }
        }
    }

    constructor(query: Query, projectedVariables: List<String>, v: List<String>, d: Map<String, MyListValue>) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        variables = v
        data = d
    }

    constructor(query: Query, projectedVariables: List<String>, values: LOPValues) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        val tmpVariables = mutableListOf<String>()
        for (name in values.variables) {
            tmpVariables.add(name.name)
        }
        variables = tmpVariables
        var columns = Array(variables.size) { MyListValue() }
        data = mutableMapOf<String, MyListValue>()
        for (variableIndex in 0 until variables.size) {
            data[variables[variableIndex]] = columns[variableIndex]
        }
        for (v in values.children) {
            SanityCheck.check({ v is AOPValue })
            val it = v.children.iterator()
            for (variableIndex in 0 until variables.size) {
                columns[variableIndex].add((it.next() as AOPConstant).value)
            }
        }
    }

    override fun getProvidedVariableNamesInternal() = variables.distinct()
    override fun getRequiredVariableNames() = mutableListOf<String>()
    override suspend fun evaluate(): IteratorBundle {
        val outMap = mutableMapOf<String, ColumnIterator>()
        for (name in variables) {
            val tmp = ColumnIteratorMultiValue(data[name]!!)
            tmp.close = {
                tmp._close()
                for (variable in variables) {
                    outMap[variable]!!.close()
                }
            }
            outMap[name] = ColumnIteratorDebug(uuid, name, tmp)
        }
        return IteratorBundle(outMap)
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val xmlvariables = XMLElement("variables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("bindings")
        res.addContent(bindings)
        for (variable in variables) {
            xmlvariables.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        var columns = Array(variables.size) { data[variables[it]] }
        if (columns.size > 0) {
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
        }
        return res
    }
}
