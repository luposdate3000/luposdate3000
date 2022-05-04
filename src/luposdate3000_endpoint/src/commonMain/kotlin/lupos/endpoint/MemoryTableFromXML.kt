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
import lupos.parser.xml.*
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.IQuery
import lupos.shared.MemoryTable
import lupos.shared.MemoryTableParser
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.MyStringStream
import lupos.shared.myPrintStackTrace

public class MemoryTableFromXML : MemoryTableParser {
    override operator fun invoke(data: String, query: IQuery): MemoryTable? {
        try {
            fun findChildElements(parent: ASTelement): List<ASTelement> {
                when (val v = parent.variable2!!) {
                    is ASTClassOfInterfaceOfListOfelementOrtextAndTAG -> {
                        when (val v2 = v.variable0!!) {
                            is ASTListOfelement -> {
                                return v2.value!!
                            }
                            is ASTtext -> return listOf()
                        }
                    }
                    is ASTcloseimmediately -> return listOf()
                }
                return listOf()
            }

            fun findChildElementByName(n: String, parent: ASTelement): ASTelement {
                for (e in findChildElements(parent)) {
                    if (e.TAG == n) {
                        return e
                    }
                }
                TODO("malformed xml")
            }

            fun maybeFindChildElementByName(n: String, parent: ASTelement): ASTelement? {
                for (e in findChildElements(parent)) {
                    if (e.TAG == n) {
                        return e
                    }
                }
                return null
            }

            fun contentOfXMLElement(parent: ASTelement): String {
                when (val v = parent.variable2!!) {
                    is ASTClassOfInterfaceOfListOfelementOrtextAndTAG -> {
                        when (val v2 = v.variable0!!) {
                            is ASTListOfelement -> return ""
                            is ASTtext -> return v2.TEXT!!
                        }
                    }
                    is ASTcloseimmediately -> return ""
                }
                return ""
            }
            fun attributesOfElement(n: String, parent: ASTelement): List<String> {
                return parent.variable1!!.value.filter { it.KEY == n }.map {
                    when (val i = it.variable1!!) {
                        is ASTvalue1 -> i.VALUE1!!
                        is ASTvalue2 -> i.VALUE2!!
                    }
                }
            }
            fun attributeOfElement(n: String, parent: ASTelement): String {
                return attributesOfElement(n, parent).getOrElse(0, { i -> "" })
            }
            fun maybeAttributeOfElement(n: String, parent: ASTelement): String? {
                return attributesOfElement(n, parent).getOrElse(0, { i -> null })
            }

            val dataStream = MyStringStream(data)
            val parserObject = XMLParser(dataStream)
            parserObject.parserDefinedParse()
            val xml = parserObject.getResult()
            val xmlSparql = xml.variable1!!
            if (xmlSparql.TAG != "sparql") {
                return null
            }
            val xmlHead = findChildElementByName("head", xmlSparql)!!
            val variables = findChildElements(xmlHead).map { attributesOfElement("name", it) }.flatten()
            val res = MemoryTable(variables.toTypedArray())
            res.query = query
            val dictionary = res.query!!.getDictionary()
            val xmlBoolean = maybeFindChildElementByName("boolean", xmlSparql)
            if (xmlBoolean != null) {
                res.booleanResult = contentOfXMLElement(xmlBoolean).toBoolean()
            } else {
                val xmlResults = findChildElementByName("results", xmlSparql)
                val buffer = ByteArrayWrapper()
                for (xmlResult in findChildElements(xmlResults)) {
                    if (xmlResult.TAG == "result") {
                        val row = DictionaryValueTypeArray(variables.size) { DictionaryValueHelper.undefValue }
                        res.data.add(row)
                        for (xmlBinding in findChildElements(xmlResult)) {
                            if (xmlBinding.TAG == "binding") {
                                val column = variables.indexOf(attributeOfElement("name", xmlBinding))
                                val child = findChildElements(xmlBinding).first()
                                when (child.TAG) {
                                    "bnode" -> {
                                        row[column] = dictionary.createNewBNode(contentOfXMLElement(child))
                                    }
                                    "uri" -> {
                                        DictionaryHelper.iriToByteArray(buffer, contentOfXMLElement(child))
                                        val tmp = dictionary.createValue(buffer)
                                        row[column] = tmp
                                    }
                                    "literal" -> {
                                        val lang = maybeAttributeOfElement("xml:lang", child)
                                        if (lang != null) {
                                            DictionaryHelper.langToByteArray(buffer, contentOfXMLElement(child), lang)
                                            val tmp = dictionary.createValue(buffer)
                                            row[column] = tmp
                                        } else {
                                            val datatype = maybeAttributeOfElement("datatype", child)
                                            if (datatype != null) {
                                                DictionaryHelper.typedToByteArray(buffer, contentOfXMLElement(child), datatype)
                                                val tmp = dictionary.createValue(buffer)
                                                row[column] = tmp
                                            } else {
                                                DictionaryHelper.stringToByteArray(buffer, contentOfXMLElement(child))
                                                val tmp = dictionary.createValue(buffer)
                                                row[column] = tmp
                                            }
                                        }
                                    } else -> {
                                        TODO("malformed xml .. unknown type ${child.TAG}")
                                    }
                                }
                            } else {
                                TODO("malformed xml .. unknown type ${xmlBinding.TAG}")
                            }
                        }
                    } else {
                        TODO("malformed xml .. unknown type ${xmlResult.TAG}")
                    }
                }
            }
            return res
        } catch (e: Throwable) {
            try {
                throw Exception(data, e)
            } catch (e2: Throwable) {
                e2.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/MemoryTableFromXML.kt:164"/*SOURCE_FILE_END*/)
            }
            return null
        }
    }
}
