package lupos.s09physicalOperators.noinput
import lupos.s04logicalOperators.IQuery
import kotlin.jvm.JvmField

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.ResultSetDictionaryExt

import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

open class POPValues : POPBase {
    override fun getPartitionCount(variable: String): Int = 1

    @JvmField
    val variables: List<String>

    @JvmField
    val data: Map<String, MutableList<Int>>

    @JvmField
    val rows: Int
    override fun toSparql(): String {
        var res = "VALUES("
        for (v in variables) {
            res += v + " "
        }
        res += ") {"
        var columns = Array(variables.size) { data[variables[it]] }
        if (columns.size > 0) {
            for (i in 0 until columns[0]!!.size) {
                res += "("
                for (v in 0 until variables.size) {
                    if (columns[v]!![i] == ResultSetDictionaryExt.undefValue) {
                        res += "UNDEF "
                    } else {
                        res += query.getDictionary().getValue(columns[v]!![i]).valueToString() + " "
                    }
                }
                res += ")"
            }
        }
        res += "}"
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPValues) {
            return false
        }
        if (rows != other.rows) {
            return false
        }
        if (rows == -1) {
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
        }
        return true
    }

    override fun cloneOP(): POPValues {
        if (rows != -1) {
            return POPValues(query, rows)
        } else {
            return POPValues(query, projectedVariables, variables, data)
        }

    }

    constructor(query: IQuery, count: Int) : super(query, listOf<String>(), EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        variables = listOf<String>()
        data = mapOf<String, MutableList<Int>>()
        rows = count
    }

    constructor(query: IQuery, projectedVariables: List<String>, v: List<String>, d: MutableList<List<String?>>) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        variables = v
        var columns = Array(variables.size) { mutableListOf<Int>() }
        data = mutableMapOf<String, MutableList<Int>>()
        if (projectedVariables.size == 0) {
            rows = d.size
        } else {
            for (variableIndex in 0 until variables.size) {
                data[variables[variableIndex]] = columns[variableIndex]
            }
            d.forEach {
                for (variableIndex in 0 until variables.size) {
                    columns[variableIndex].add(query.getDictionary().createValue(it[variableIndex]))
                }
            }
            rows = -1
        }
    }

    constructor(query: IQuery, projectedVariables: List<String>, v: List<String>, d: Map<String, MutableList<Int>>) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        variables = v
        data = d
        rows = -1
    }

    constructor(query: IQuery, projectedVariables: List<String>, values: LOPValues) : super(query, projectedVariables, EOperatorID.POPValuesID, "POPValues", arrayOf(), ESortPriority.PREVENT_ANY) {
        if (projectedVariables.size == 0) {
            variables = listOf<String>()
            data = mapOf<String, MutableList<Int>>()
            rows = values.children.size
        } else {
            val tmpVariables = mutableListOf<String>()
            for (name in values.variables) {
                tmpVariables.add(name.name)
            }
            variables = tmpVariables
            var columns = Array(variables.size) { mutableListOf<Int>() }
            data = mutableMapOf<String, MutableList<Int>>()
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
            rows = -1
        }
    }

    override fun getProvidedVariableNamesInternal() = variables.distinct()
    override fun getRequiredVariableNames() = mutableListOf<String>()
    override suspend fun evaluate(parent: Partition): IteratorBundle {
        if (rows == -1) {
            val outMap = mutableMapOf<String, ColumnIterator>()
            for (name in variables) {
                outMap[name] = ColumnIteratorMultiValue(data[name]!!)
            }
            return IteratorBundle(outMap)
        } else {
            return IteratorBundle(rows)
        }

    }

    override suspend fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val xmlvariables = XMLElement("variables")
        res.addAttribute("rows", "" + rows)
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
                    val value = query.getDictionary().getValue(columns[variableIndex]!![i]).valueToString()
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
