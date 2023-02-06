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
import lupos.shared.SanityCheck
import lupos.shared.dictionary.DictionaryNotImplemented
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundleRoot

public class QueryResultToEmptyStream : IResultFormat {
    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ private fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, timeoutInMs: Long) {
        val rowBuf = DictionaryValueTypeArray(variables.size)
        val startTime = DateHelperRelative.markNow()
        loop@ while (timeoutInMs <= 0 || DateHelperRelative.elapsedMilliSeconds(startTime) < timeoutInMs) {
            for (variableIndex in variables.indices) {
                val valueID = columns[variableIndex].next()
                if (valueID == DictionaryValueHelper.nullValue) {
                    break@loop
                }
                rowBuf[variableIndex] = valueID
            }
        }
        for (element in columns) {
            element.close()
        }
    }

    override operator fun invoke(rootNode: IteratorBundleRoot, output: IMyOutputStream, timeoutInMs: Long) {
        invokeInternal(rootNode, timeoutInMs)
    }

    override operator fun invoke(rootNode: IteratorBundleRoot, output: IMyOutputStream) {
        invokeInternal(rootNode, -1)
    }

    override operator fun invoke(rootNode: IteratorBundleRoot) {
        invokeInternal(rootNode, -1)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun invokeInternal(rootNode: IteratorBundleRoot, timeoutInMs: Long) {
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
                SanityCheck.check(
                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_result_format/src/commonMain/kotlin/lupos/result_format/QueryResultToEmptyStream.kt:73"/*SOURCE_FILE_END*/ },
                    { child.names.toSet().containsAll(columnNames) },
                    { "$columnNames vs ${child.names}" }
                )
            } else {
                columnNames = child.names.toList()
            }
            val variables = columnNames.toTypedArray()
            if (variables.size == 1 && variables[0] == "?boolean") {
                child.columns["?boolean"]!!.next()
            } else {
                if (variables.isEmpty()) {
                    child.count()
                } else {
                    val columns = variables.map { child.columns[it]!! }.toTypedArray()
                    writeAllRows(variables, columns, timeoutInMs)
                }
            }
        }
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
        }
    }
}
