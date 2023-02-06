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
package lupos.operator.physical.noinput

import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPValue
import lupos.operator.base.IPOPLimit
import lupos.operator.logical.noinput.LOPValues
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.PartitionHelper
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public open class POPValues : POPBase {
    override fun getPartitionCount(variable: String): Int = 1

    @JvmField
    public val variables: List<String>

    @JvmField
    public val data: Map<String, MutableList<DictionaryValueType>>

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
                    res += if (columns[v]!![i] == DictionaryValueHelper.undefValue) {
                        "UNDEF "
                    } else {
                        query.getDictionary().getValue(buffer, columns[v]!![i])
                        DictionaryHelper.byteArrayToSparql(buffer)
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
        val columns = Array(variables.size) { mutableListOf<DictionaryValueType>() }
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

    public constructor(query: IQuery, projectedVariables: List<String>, v: List<String>, d: Map<String, MutableList<DictionaryValueType>>) : super(query, projectedVariables, EOperatorIDExt.POPValuesID, "POPValues", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
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
            val columns = Array(variables.size) { mutableListOf<DictionaryValueType>() }
            data = mutableMapOf()
            for (variableIndex in variables.indices) {
                data[variables[variableIndex]] = columns[variableIndex]
            }
            for (v in values.children) {
                if (SanityCheck.enabled) { if (!(v is AOPValue)) { throw Exception("SanityCheck failed") } }
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
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle = EvalValues(rows, data)
    override /*suspend*/ fun toXMLElement(partial: Boolean, partition: PartitionHelper): XMLElement {
        val res = super.toXMLElement(partial, partition)
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
                    val value = DictionaryHelper.byteArrayToSparql(buffer)
                    b.addContent(XMLElement("value").addAttribute("name", variables[variableIndex]).addAttribute("content", value))
                }
            }
        }
        return res
    }

    override fun usesDictionary(): Boolean {
        return true
    }

    override fun toLocalOperatorGraph(parent: Partition, onFoundLimit: (IPOPLimit) -> Unit, onFoundSort: () -> Unit): POPBase? = this
}
