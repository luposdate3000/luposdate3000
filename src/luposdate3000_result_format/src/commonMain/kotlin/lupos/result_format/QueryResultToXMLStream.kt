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

import lupos.shared.DateHelperRelative
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EPartitionModeExt
import lupos.shared.IMyOutputStream
import lupos.shared.MyLock
import lupos.shared.SanityCheck
import lupos.shared.dictionary.DictionaryNotImplemented
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.MyPrintWriter
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundleRoot

public class QueryResultToXMLStream : IResultFormat {
    @Suppress("NOTHING_TO_INLINE")
    private /*suspend*/ fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IDictionary, lock: MyLock?, output: IMyOutputStream, timeoutInMs: Long) {
        val rowBuf = DictionaryValueTypeArray(variables.size)
        val resultWriter = MyPrintWriter(true)
        val buffer = ByteArrayWrapper()
        val startTime = DateHelperRelative.markNow()
        loop@ while (timeoutInMs <= 0 || DateHelperRelative.elapsedMilliSeconds(startTime) < timeoutInMs) {
            for (variableIndex in variables.indices) {
                val valueID = columns[variableIndex].next()
                if (valueID == DictionaryValueHelper.nullValue) {
                    break@loop
                }
                rowBuf[variableIndex] = valueID
            }
            resultWriter.print("  <result>\n")
            for (variableIndex in variables.indices) {
                dictionary.getValue(buffer, rowBuf[variableIndex])
                DictionaryHelper.byteArrayToCallback(
                    buffer,
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <bnode>")
                        resultWriter.print(value.toString(16).padStart(16, '0'))
                        resultWriter.print("</bnode>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal>")
                        resultWriter.print(value.toString())
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { content, lang ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal xml:lang=\"")
                        resultWriter.print(lang)
                        resultWriter.print("\">")
                        resultWriter.print(content)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { content ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal>")
                        resultWriter.print(content)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { content, type ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal datatype=\"")
                        resultWriter.print(type)
                        resultWriter.print("\">")
                        resultWriter.print(content)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#decimal\">")
                        resultWriter.print(value)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#float\">")
                        resultWriter.print(value)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#double\">")
                        resultWriter.print(value)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">")
                        resultWriter.print(value)
                        resultWriter.print("</literal>\n   </binding>\n")
                    },
                    { value ->
                        resultWriter.print("   <binding name=\"")
                        resultWriter.print(variables[variableIndex])
                        resultWriter.print("\">\n    <uri>")
                        resultWriter.print(value)
                        resultWriter.print("</uri>\n   </binding>\n")
                    },
                    {}, {}
                )
            }
            resultWriter.print("  </result>\n")
            lock?.lock()
            output.print(resultWriter.toString())
            lock?.unlock()
            resultWriter.clearBuffer()
        }
        for (element in columns) {
            element.close()
        }
    }

    override operator fun invoke(rootNode: IteratorBundleRoot, output: IMyOutputStream, timeoutInMs: Long) {
        invokeInternal(rootNode, output, timeoutInMs)
    }

    override operator fun invoke(rootNode: IteratorBundleRoot, output: IMyOutputStream) {
        invokeInternal(rootNode, output, -1)
    }

    override operator fun invoke(rootNode: IteratorBundleRoot) {
        TODO()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun invokeInternal(rootNode: IteratorBundleRoot, output: IMyOutputStream, timeoutInMs: Long) {
        val query = rootNode.query
        val dict = query.getDictionary()
        val flag = query.getDictionaryUrl() == null && dict !is DictionaryNotImplemented && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process
        val key = "${query.getTransactionID()}"
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
            query.setDictionaryUrl("${query.getInstance().LUPOS_PROCESS_URLS_ALL[0]}/distributed/query/dictionary?key=$key")
        }
        for ((columnProjectionOrder, child) in rootNode.nodes) {
            output.print("<sparql xmlns=\"http://www.w3.org/2005/sparql-results#\">\n")
            val columnNames: List<String>
            if (columnProjectionOrder.isNotEmpty()) {
                columnNames = columnProjectionOrder
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_result_format/src/commonMain/kotlin/lupos/result_format/QueryResultToXMLStream.kt:168"/*SOURCE_FILE_END*/ }, { child.names.toSet().containsAll(columnNames) }, { "${columnNames.map { it }} vs ${child.names}" })
            } else {
                columnNames = child.names.toList()
            }
            val variables = columnNames.toTypedArray()
            if (variables.size == 1 && variables[0] == "?boolean") {
                output.print(" <head/>\n")
                val buffer = ByteArrayWrapper()
                dict.getValue(buffer, child.columns["?boolean"]!!.next())
                output.print(" <boolean>")
                output.print(DictionaryHelper.byteArrayToBoolean(buffer))
                output.print("</boolean>\n")
                child.columns["?boolean"]!!.close()
            } else {
                if (variables.isEmpty()) {
                    output.print(" <head/>\n <results>\n")
                    for (j in 0 until child.count()) {
                        output.print("  <result/>\n")
                    }
                    output.print(" </results>\n")
                } else {
                    output.print(" <head>\n")
                    for (variable in variables) {
                        output.print("  <variable name=\"")
                        output.print(variable)
                        output.print("\"/>\n")
                    }
                    output.print(" </head>\n <results>\n")
                    val columns = variables.map { child.columns[it]!! }.toTypedArray()
                    writeAllRows(variables, columns, rootNode.query.getDictionary(), null, output, timeoutInMs)
                    output.print(" </results>\n")
                }
            }
            output.print("</sparql>\n")
        }
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
        }
    }
}
