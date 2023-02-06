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
package lupos.result_format

import lupos.shared.DictionaryValueHelper
import lupos.shared.EPartitionModeExt
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.dictionary.DictionaryNotImplemented
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.iterator.IteratorBundleRoot

public object QueryResultToXMLElement {
    public /*suspend*/ fun toXML(rootNode: IteratorBundleRoot): XMLElement {
        val buffer = ByteArrayWrapper()
        val query = rootNode.query
        val flag = query.getDictionaryUrl() == null && query.getDictionary() !is DictionaryNotImplemented && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process
        val key = "${query.getTransactionID()}"
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
            query.setDictionaryUrl("${query.getInstance().LUPOS_PROCESS_URLS_ALL[0]}/distributed/query/dictionary?key=$key")
        }
        val res = mutableListOf<XMLElement>()
        for ((columnProjectionOrder, child) in rootNode.nodes) {
            val nodeSparql = XMLElement("sparql").addAttribute("xmlns", "http://www.w3.org/2005/sparql-results#")
            val nodeHead = XMLElement("head")
            nodeSparql.addContent(nodeHead)
            val columnNames: List<String>
            if (columnProjectionOrder.isNotEmpty()) {
                columnNames = columnProjectionOrder
                if (SanityCheck.enabled) { if (!(child.names.toSet().containsAll(columnNames))) { throw Exception("SanityCheck failed") } }
            } else {
                columnNames = child.names.toList()
            }
            val variables = columnNames.toTypedArray()
            if (variables.size == 1 && variables[0] == "?boolean") {
                query.getDictionary().getValue(buffer, child.columns["?boolean"]!!.next())
                val value = DictionaryHelper.byteArrayToSparql(buffer)
                val datatype = "http://www.w3.org/2001/XMLSchema#boolean"
                if (SanityCheck.enabled) { if (!(value.endsWith("\"^^<$datatype>"))) { throw Exception("SanityCheck failed") } }
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
                            if (valueID == DictionaryValueHelper.nullValue) {
                                for (element in columns) {
                                    element.close()
                                }
                                break@loop
                            }
                            if (valueID != DictionaryValueHelper.undefValue && valueID != DictionaryValueHelper.errorValue) {
                                query.getDictionary().getValue(buffer, valueID)
                                val value = DictionaryHelper.byteArrayToSparql(buffer)
                                val nodeBinding = XMLElement("binding").addAttribute("name", variables[variableIndex])
                                if (value.length > 1) {
                                    if (value.startsWith("\"") && !value.endsWith("\"")) {
                                        val idx = value.lastIndexOf("\"^^<")
                                        if (idx >= 0) {
                                            val data = value.substring(1, idx)
                                            val type = value.substring(idx + 4, value.length - 1)
                                            nodeBinding.addContent(XMLElement("literal").addContent(data).addAttribute("datatype", type))
                                        } else {
                                            val idx2 = value.lastIndexOf("\"@")
                                            if (SanityCheck.enabled) { if (!(idx2 >= 0)) { throw Exception("SanityCheck failed") } }
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
            res.add(nodeSparql)
        }
        if (res.size == 1) {
            if (flag) {
                query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
            }
            return res[0]
        }
        val compountResult = XMLElement("")
        for (r in res) {
            compountResult.addContent(r)
        }
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
        }
        return compountResult
    }
}
