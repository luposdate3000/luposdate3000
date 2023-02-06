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
import lupos.shared.UnableToOutputResultException
import lupos.shared.dictionary.DictionaryNotImplemented
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.MyPrintWriter
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundleRoot

public class QueryResultToTurtleStream : IResultFormat {

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ private fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IDictionary, lock: MyLock?, output: IMyOutputStream, timeoutInMs: Long) {
        val variablesIndices = intArrayOf(variables.indexOf("s"), variables.indexOf("p"), variables.indexOf("o"))
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
            val line = Array(3) { "" }
            for (i in 0 until 3) {
                dictionary.getValue(buffer, rowBuf[i])
                DictionaryHelper.byteArrayToCallback(
                    buffer,
                    onBNode = { value ->
                        line[i] = "_:${value.toString(16).padStart(16, '0')}"
                    },
                    onBoolean = { value ->
                        line[i] = "$value"
                    },
                    onLanguageTaggedLiteral = { content, lang ->
                        line[i] = "\"$content\"@$lang"
                    },
                    onSimpleLiteral = { content ->
                        line[i] = "\"$content\""
                    },
                    onTypedLiteral = { content, type ->
                        line[i] = "\"$content\"^^<$type>"
                    },
                    onDecimal = { value ->
                        line[i] = value
                    },
                    onFloat = { value ->
                        line[i] = "$value"
                    },
                    onDouble = { value ->
                        line[i] = "$value"
                    },
                    onInteger = { value ->
                        line[i] = value
                    },
                    onIri = { value ->
                        line[i] = "<$value>"
                    },
                    onError = {
                    },
                    onUndefined = {
                    }
                )
            }
            resultWriter.print("${line[variablesIndices[0]]} ${line[variablesIndices[1]]} ${line[variablesIndices[2]]} .\n")
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
        val flag = query.getDictionaryUrl() == null && query.getDictionary() !is DictionaryNotImplemented && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process
        val key = "${query.getTransactionID()}"
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
            query.setDictionaryUrl("${query.getInstance().LUPOS_PROCESS_URLS_ALL[0]}/distributed/query/dictionary?key=$key")
        }
        for ((columnProjectionOrder, child) in rootNode.nodes) {
            val columnNames: List<String>
            if (columnProjectionOrder.isNotEmpty()) {
                columnNames = columnProjectionOrder
                if (SanityCheck.enabled) { if (!(child.names.toSet().containsAll(columnNames))) { throw Exception("SanityCheck failed") } }
            } else {
                columnNames = child.names.toList()
            }
            val variables = columnNames.toTypedArray()
            if (variables.size != 3 || !variables.contains("s") || !variables.contains("p") || !variables.contains("o")) {
                throw UnableToOutputResultException()
            } else {
                val columns = variables.map { child.columns[it]!! }.toTypedArray()
                writeAllRows(variables, columns, rootNode.query.getDictionary(), null, output, timeoutInMs)
            }
        }
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
        }
    }
}
