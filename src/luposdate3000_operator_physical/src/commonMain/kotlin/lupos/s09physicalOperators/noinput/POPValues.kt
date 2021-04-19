/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.s09physicalOperators.noinput

import lupos.dictionary.DictionaryExt
import lupos.modulename.DictionaryHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorMultiValue
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s09physicalOperators.POPBase
import kotlin.jvm.JvmField

public open class POPValues : POPBase {
    override fun getPartitionCount(variable: String): Int = 1

    @JvmField
    public val variables: List<String>

    @JvmField
    public val data: Map<String, MutableList<Int>>

    @JvmField
    public val rows: Int
    override fun toSparql(): String {
        val buffer = ByteArrayWrapper()
        var res = "VALUES("
        for (v in variables) {
            res += "$v "
        }
        res += ") {"
        val columns = Array(variables.size) { data[variables[it]] }
        if (columns.isNotEmpty()) {
            for (i in 0 until columns[0]!!.size) {
                res += "("
                for (v in variables.indices) {
                    res += if (columns[v]!![i] == DictionaryExt.undefValue) {
                        "UNDEF "
                    } else {
                        query.getDictionary().getValue(buffer, columns[v]!![i])
                        DictionaryHelper.byteArrayToValueDefinition(buffer).valueToString() + " "
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
                val columns1 = Array(variables.size) { data[variables[it]] }
                val columns2 = Array(variables.size) { other.data[variables[it]] }
                for (vIndex in variables.indices) {
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
        return if (rows != -1) {
            POPValues(query, rows)
        } else {
            POPValues(query, projectedVariables, variables, data)
        }
    }

    public constructor(query: IQuery, count: Int) : super(query, listOf<String>(), EOperatorIDExt.POPValuesID, "POPValues", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
        variables = listOf()
        data = mapOf()
        rows = count
    }

    public constructor(query: IQuery, projectedVariables: List<String>, v: List<String>, d: MutableList<List<String?>>) : super(query, projectedVariables, EOperatorIDExt.POPValuesID, "POPValues", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
        variables = v
        val columns = Array(variables.size) { mutableListOf<Int>() }
        data = mutableMapOf()
        val buffer = ByteArrayWrapper()
        if (projectedVariables.isEmpty()) {
            rows = d.size
        } else {
            for (variableIndex in variables.indices) {
                data[variables[variableIndex]] = columns[variableIndex]
            }
            d.forEach {
                for (variableIndex in variables.indices) {
                    DictionaryHelper.sparqlToByteArray(buffer, it[variableIndex]!!)
                    columns[variableIndex].add(query.getDictionary().createValue(buffer))
                }
            }
            rows = -1
        }
    }

    public constructor(query: IQuery, projectedVariables: List<String>, v: List<String>, d: Map<String, MutableList<Int>>) : super(query, projectedVariables, EOperatorIDExt.POPValuesID, "POPValues", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
        variables = v
        data = d
        rows = -1
    }

    public constructor(query: IQuery, projectedVariables: List<String>, values: LOPValues) : super(query, projectedVariables, EOperatorIDExt.POPValuesID, "POPValues", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
        if (projectedVariables.isEmpty()) {
            variables = listOf()
            data = mapOf()
            rows = values.children.size
        } else {
            val tmpVariables = mutableListOf<String>()
            for (name in values.variables) {
                tmpVariables.add(name.name)
            }
            variables = tmpVariables
            val columns = Array(variables.size) { mutableListOf<Int>() }
            data = mutableMapOf()
            for (variableIndex in variables.indices) {
                data[variables[variableIndex]] = columns[variableIndex]
            }
            for (v in values.children) {
                SanityCheck.check { v is AOPValue }
                val it = v.getChildren().iterator()
                for (variableIndex in 0 until variables.size) {
                    columns[variableIndex].add((it.next() as AOPConstant).value)
                }
            }
            rows = -1
        }
    }

    override fun getProvidedVariableNamesInternal(): List<String> = variables.distinct()
    override fun getRequiredVariableNames(): MutableList<String> = mutableListOf()
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        return if (rows == -1) {
            val outMap = mutableMapOf<String, ColumnIterator>()
            for (name in variables) {
                outMap[name] = ColumnIteratorMultiValue(data[name]!!)
            }
            IteratorBundle(outMap)
        } else {
            IteratorBundle(rows)
        }
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        val res = super.toXMLElement(partial)
        val xmlvariables = XMLElement("variables")
        res.addAttribute("rows", "" + rows)
        res.addContent(xmlvariables)
        val bindings = XMLElement("bindings")
        res.addContent(bindings)
        for (variable in variables) {
            xmlvariables.addContent(XMLElement("variable").addAttribute("name", variable))
        }
        val columns = Array(variables.size) { data[variables[it]] }
        val buffer = ByteArrayWrapper()
        if (columns.isNotEmpty()) {
            for (i in 0 until columns[0]!!.size) {
                val b = XMLElement("binding")
                bindings.addContent(b)
                for (variableIndex in variables.indices) {
                    query.getDictionary().getValue(buffer, columns[variableIndex]!![i])
                    val value = DictionaryHelper.byteArrayToValueDefinition(buffer).valueToString()
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
