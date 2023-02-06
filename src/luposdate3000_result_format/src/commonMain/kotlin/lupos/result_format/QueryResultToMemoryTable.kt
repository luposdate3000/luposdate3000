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
import lupos.shared.MemoryTable
import lupos.shared.MyLock
import lupos.shared.SanityCheck
import lupos.shared.dictionary.DictionaryNotImplemented
import lupos.shared.dictionary.IDictionary
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundleRoot

public class QueryResultToMemoryTable : IResultFormat {

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ private fun writeAllRows(variables: Array<String>, columns: Array<ColumnIterator>, dictionary: IDictionary, lock: MyLock?, output: MemoryTable, timeoutInMs: Long) {
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
            lock?.lock()
            output.data.add(DictionaryValueTypeArray(variables.size) { rowBuf[it] })
            lock?.unlock()
        }
        for (element in columns) {
            element.close()
        }
    }

    override operator fun invoke(rootNode: IteratorBundleRoot, output: IMyOutputStream, timeoutInMs: Long): List<MemoryTable> {
        return invokeInternal(rootNode, timeoutInMs)
    }

    override operator fun invoke(rootNode: IteratorBundleRoot, output: IMyOutputStream): List<MemoryTable> {
        return invokeInternal(rootNode, -1)
    }

    override operator fun invoke(rootNode: IteratorBundleRoot): List<MemoryTable> {
        return invokeInternal(rootNode, -1)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal fun invokeInternal(rootNode: IteratorBundleRoot, timeoutInMs: Long): List<MemoryTable> {
        val query = rootNode.query
        val flag = query.getDictionaryUrl() == null && query.getDictionary() !is DictionaryNotImplemented && query.getInstance().LUPOS_PARTITION_MODE == EPartitionModeExt.Process
        val key = "${query.getTransactionID()}"
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/register", mapOf("key" to key), query.getTransactionID().toInt())
            query.setDictionaryUrl("${query.getInstance().LUPOS_PROCESS_URLS_ALL[0]}/distributed/query/dictionary?key=$key")
        }
        val resultList = mutableListOf<MemoryTable>()
        for ((columnProjectionOrder, child) in rootNode.nodes) {
            val columnNames: List<String>
            if (columnProjectionOrder.isNotEmpty()) {
                columnNames = columnProjectionOrder
                if (SanityCheck.enabled) { if (!(child.names.toSet().containsAll(columnNames))) { throw Exception("SanityCheck failed") } }
            } else {
                columnNames = child.names.toList()
            }
            val variables = columnNames.toTypedArray()
            if (variables.size == 1 && variables[0] == "?boolean") {
                val buffer = ByteArrayWrapper()
                query.getDictionary().getValue(buffer, child.columns["?boolean"]!!.next())
                val value = DictionaryHelper.byteArrayToBoolean(buffer)
                val res = MemoryTable(Array(0) { "" })
                res.query = rootNode.query
                res.booleanResult = value
                resultList.add(res)
                child.columns["?boolean"]!!.close()
            } else {
                if (variables.isEmpty()) {
                    val res = MemoryTable(Array(0) { "" })
                    res.query = rootNode.query
                    for (j in 0 until child.count()) {
                        res.data.add(DictionaryValueTypeArray(0))
                    }
                    resultList.add(res)
                } else {
                    val output = MemoryTable(variables)
                    output.query = rootNode.query
                    val columns = variables.map { child.columns[it]!! }.toTypedArray()
                    writeAllRows(variables, columns, rootNode.query.getDictionary(), null, output, timeoutInMs)
                    resultList.add(output)
                }
            }
        }
        if (flag) {
            query.getInstance().communicationHandler!!.sendData(query.getInstance().LUPOS_PROCESS_URLS_ALL[0], "/distributed/query/dictionary/remove", mapOf("key" to key), query.getTransactionID().toInt())
        }
        return resultList
    }
}
