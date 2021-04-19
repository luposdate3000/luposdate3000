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
package lupos.s11outputResult

import lupos.dictionary.DictionaryExt
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.EPartitionModeExt
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s00misc.communicationHandler
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s05tripleStore.tripleStoreManager
import lupos.shared_inline.DictionaryHelper

public object QueryResultToXMLElement {
    public /*suspend*/ fun toXML(rootNode: IOPBase): XMLElement {
        val buffer = ByteArrayWrapper()
        val query = rootNode.getQuery()
        val flag = query.getDictionaryUrl() == null
        val key = "${query.getTransactionID()}"
        if (flag && tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
            communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/register", mapOf("key" to "$key"))
            query.setDictionaryUrl("${tripleStoreManager.getLocalhost()}/distributed/query/dictionary?key=$key")
        }
        val res = mutableListOf<XMLElement>()
        val nodes: Array<IOPBase>
        val columnProjectionOrder: List<List<String>>
        if (rootNode is OPBaseCompound) {
            nodes = Array<IOPBase>(rootNode.children.size) { rootNode.children[it] }
            columnProjectionOrder = rootNode.columnProjectionOrder
        } else {
            nodes = arrayOf(rootNode)
            columnProjectionOrder = listOf(listOf())
        }
        for (i in nodes.indices) {
            val node = nodes[i]
            val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
            val nodeHead = XMLElement("head")
            nodeSparql.addContent(nodeHead)
            if (node is OPNothing) {
                val nodeResults = XMLElement("results")
                nodeSparql.addContent(nodeResults)
                for (variable in node.getProvidedVariableNames()) {
                    nodeHead.addContent(XMLElement("variable").addAttribute("name", variable))
                }
            } else {
                val columnNames: List<String>
                if (columnProjectionOrder[i].isNotEmpty()) {
                    columnNames = columnProjectionOrder[i]
                    SanityCheck.check { node.getProvidedVariableNames().containsAll(columnNames) }
                } else {
                    columnNames = node.getProvidedVariableNames()
                }
                val child = node.evaluateRoot(Partition())
                val variables = columnNames.toTypedArray()
                if (variables.size == 1 && variables[0] == "?boolean") {
                    query.getDictionary().getValue(buffer, child.columns["?boolean"]!!.next())
                    val value = DictionaryHelper.byteArrayToValueDefinition(buffer).valueToString()!!
                    val datatype = "http://www.w3.org/2001/XMLSchema#boolean"
                    SanityCheck.check { value.endsWith("\"^^<$datatype>") }
                    nodeSparql.addContent(XMLElement("boolean").addContent(value.substring(1, value.length - ("\"^^<$datatype>").length)))
                    child.columns["?boolean"]!!.close()
                } else {
                    val bnodeMap = mutableMapOf<String, String>()
                    val nodeResults = XMLElement("results")
                    nodeSparql.addContent(nodeResults)
                    for (variable in variables) {
                        nodeHead.addContent(XMLElement("variable").addAttribute("name", variable))
                    }
                    if (variables.isEmpty()) {
                        for (j in 0 until child.count()) {
                            val nodeResult = XMLElement("result")
                            nodeResults.addContent(nodeResult)
                        }
                    } else {
                        val columns = variables.map { child.columns[it]!! }.toTypedArray()
                        loop@ while (true) {
                            val nodeResult = XMLElement("result")
                            for (variableIndex in variables.indices) {
                                val valueID = columns[variableIndex].next()
                                if (valueID == DictionaryExt.nullValue) {
                                    for (element in columns) {
                                        element.close()
                                    }
                                    break@loop
                                }
                                if (valueID != DictionaryExt.undefValue && valueID != DictionaryExt.errorValue) {
                                    query.getDictionary().getValue(buffer, valueID)
                                    val value = DictionaryHelper.byteArrayToValueDefinition(buffer).valueToString()
                                    SanityCheck.check { value != null }
                                    val nodeBinding = XMLElement("binding").addAttribute("name", variables[variableIndex])
                                    if (value!!.length > 1) {
                                        if (value.startsWith("\"") && !value.endsWith("\"")) {
                                            val idx = value.lastIndexOf("\"^^<")
                                            if (idx >= 0) {
                                                val data = value.substring(1, idx)
                                                val type = value.substring(idx + 4, value.length - 1)
                                                nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("datatype", type))
                                            } else {
                                                val idx2 = value.lastIndexOf("\"@")
                                                SanityCheck.check { idx2 >= 0 }
                                                val data = value.substring(1, idx2)
                                                val lang = value.substring(idx2 + 2, value.length)
                                                nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("xml:lang", lang))
                                            }
                                        } else if (value.startsWith("<") && value.endsWith(">")) {
                                            nodeBinding.addContent(XMLElement("uri").addContent(value.substring(1, value.length - 1)))
                                        } else if (value.startsWith("_:")) {
                                            if (bnodeMap[value] == null) {
                                                bnodeMap[value] = "" + bnodeMap.keys.size
                                            }
                                            val name = bnodeMap[value]!!
                                            nodeBinding.addContent(XMLElement("bnode").addContent(name))
                                        } else {
                                            nodeBinding.addContent(XMLElement("literal").addContent(value.substring(1, value.length - 1)))
                                        }
                                    }
                                    nodeResult.addContent(nodeBinding)
                                }
                            }
                            nodeResults.addContent(nodeResult)
                        }
                    }
                }
            }
            res.add(nodeSparql)
        }
        if (res.size == 1) {
            if (flag && tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
                communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
            }
            return res[0]
        }
        val compountResult = XMLElement("")
        for (r in res) {
            compountResult.addContent(r)
        }
        if (flag && tripleStoreManager.getPartitionMode() == EPartitionModeExt.Process) {
            communicationHandler.sendData(tripleStoreManager.getLocalhost(), "/distributed/query/dictionary/remove", mapOf("key" to "$key"))
        }
        return compountResult
    }
}
