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
import lupos.parser.json.*
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.IQuery
import lupos.shared.MemoryTable
import lupos.shared.MemoryTableParser
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.MyStringStream
import lupos.shared.myPrintStackTrace

public class MemoryTableFromJson : MemoryTableParser {
    override operator fun invoke(data: String, query: IQuery): MemoryTable? {
        try {
            fun valueToString(v: ASTvalue): String {
                return (v as ASTstring).STRING!!.drop(1).dropLast(1)
            }

            fun typeToString(v: ASTmember): String {
                return v.STRING!!.drop(1).dropLast(1)
            }

            fun iterateMembers(parent: ASTobject): List<ASTmember> {
                return listOf(parent.variable0!!.variable0!!) + parent.variable0!!.variable1!!.value
            }

            fun iterateElements(parent: ASTarray): List<ASTvalue> {
                return listOf(parent.variable0!!.variable0!!) + parent.variable0!!.variable1!!.value
            }

            fun findMemberByName(n: String, parent: ASTobject): ASTvalue {
                for (member in iterateMembers(parent)) {
                    if (typeToString(member) == n) {
                        return member.variable1!!
                    }
                }
                TODO("malformed json ... missing key $n")
            }

            fun maybeFindMemberByName(n: String, parent: ASTobject): ASTvalue? {
                for (member in iterateMembers(parent)) {
                    if (typeToString(member) == n) {
                        return member.variable1
                    }
                }
                return null
            }

            val dataStream = MyStringStream(data)
            val parserObject = JsonParser(dataStream)
            parserObject.parserDefinedParse()
            val jsonSparql = parserObject.getResult() as ASTobject
            val jsonHead = findMemberByName("head", jsonSparql) as ASTobject
            val jsonHeadVars = findMemberByName("vars", jsonHead) as ASTarray
            val variables = iterateElements(jsonHeadVars).map { valueToString(it) }
            val res = MemoryTable(variables.toTypedArray())
            res.query = query
            val dictionary = res.query!!.getDictionary()
            val jsonBoolean = maybeFindMemberByName("boolean", jsonSparql)
            if (jsonBoolean != null) {
                res.booleanResult = jsonBoolean is ASTtrue
            } else {
                val jsonResults = findMemberByName("results", jsonSparql) as ASTobject
                val buffer = ByteArrayWrapper()
                for (jsonResult in iterateElements(findMemberByName("bindings", jsonResults) as ASTarray)) {
                    val row = DictionaryValueTypeArray(variables.size) { DictionaryValueHelper.undefValue }
                    res.data.add(row)
                    for (jsonBinding in iterateMembers(jsonResult as ASTobject)) {
                        val column = variables.indexOf(typeToString(jsonBinding))
                        val jsonBindingContent = jsonBinding.variable1!! as ASTobject
                        val childType = valueToString(findMemberByName("type", jsonBindingContent))
                        when (childType) {
                            "bnode" -> {
                                row[column] = dictionary.createNewBNode(valueToString(findMemberByName("value", jsonBindingContent)))
                            }
                            "uri" -> {
                                DictionaryHelper.iriToByteArray(buffer, valueToString(findMemberByName("value", jsonBindingContent)))
                                val tmp = dictionary.createValue(buffer)
                                row[column] = tmp
                            }
                            "typed-literal" -> {
                                DictionaryHelper.typedToByteArray(buffer, valueToString(findMemberByName("value", jsonBindingContent)), valueToString(findMemberByName("datatype", jsonBindingContent)))
                                val tmp = dictionary.createValue(buffer)
                                row[column] = tmp
                            }
                            "literal" -> {
                                DictionaryHelper.stringToByteArray(buffer, valueToString(findMemberByName("value", jsonBindingContent)))
                                val tmp = dictionary.createValue(buffer)
                                row[column] = tmp
                            }
                            else -> {
                                TODO("malformed json value type $childType")
                            }
                        }
                    }
                }
            }
            return res
        } catch (e: Throwable) {
            try {
                throw Exception(data, e)
            } catch (e2: Throwable) {
                e2.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/MemoryTableFromJson.kt:120"/*SOURCE_FILE_END*/)
            }
            return null
        }
    }
}
