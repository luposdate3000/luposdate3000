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
package lupos.endpoint

import lupos.shared.DictionaryValueHelper
import lupos.shared.IQuery
import lupos.shared.MemoryTable
import lupos.shared.MemoryTableParser
import lupos.shared.XMLElementFromXML
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper

public class MemoryTableFromXML : MemoryTableParser {
    override operator fun invoke(data: String, query: IQuery): MemoryTable? {
        val xml = XMLElementFromXML()(data)
        if (xml == null || xml.tag != "sparql") {
            return null
        }
        try {
            val xmlHead = xml["head"]!!
            val variables = xmlHead.childs.map { it.attributes["name"]!! }
            val res = MemoryTable(variables.toTypedArray())
            res.query = query
            val dictionary = res.query!!.getDictionary()
            val xmlBoolean = xml["boolean"]
            if (xmlBoolean != null) {
                res.booleanResult = xmlBoolean.content.toBoolean()
            } else {
                val xmlResults = xml["results"]!!
                val buffer = ByteArrayWrapper()
                for (xmlResult in xmlResults.childs) {
                    if (xmlResult.tag == "result") {
                        val row = IntArray(variables.size) { DictionaryValueHelper.undefValue }
                        res.data.add(row)
                        for (xmlBinding in xmlResult.childs) {
                            if (xmlBinding.tag == "binding") {
                                val column = variables.indexOf(xmlBinding.attributes["name"]!!)
                                val child = xmlBinding.childs.first()
                                when (child.tag) {
                                    "bnode" -> {
                                        row[column] = dictionary.createNewBNode(child.content)
                                    }
                                    "uri" -> {
                                        DictionaryHelper.iriToByteArray(buffer, child.content)
                                        val tmp = dictionary.createValue(buffer)
                                        row[column] = tmp
                                    }
                                    "literal" -> {
                                        val lang = child.attributes["xml:lang"]
                                        if (lang != null) {
                                            DictionaryHelper.langToByteArray(buffer, child.content, lang)
                                            val tmp = dictionary.createValue(buffer)
                                            row[column] = tmp
                                        } else {
                                            val datatype = child.attributes["datatype"]
                                            if (datatype != null) {
                                                DictionaryHelper.typedToByteArray(buffer, child.content, datatype)
                                                val tmp = dictionary.createValue(buffer)
                                                row[column] = tmp
                                            } else {
                                                DictionaryHelper.stringToByteArray(buffer, child.content)
                                                val tmp = dictionary.createValue(buffer)
                                                row[column] = tmp
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return res
        } catch (e: Throwable) {
            e.printStackTrace()
            return null
        }
    }
}
